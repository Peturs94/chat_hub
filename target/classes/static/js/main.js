//'use strict' lint?

//var usernamePage = document.querySelector("#username-page");
var chatContainer = document.querySelector("#chat-container");
var messageInput = document.querySelector("#message-input");
var connectingElement = document.querySelector(".connecting");
var chatWindow = document.querySelector("#chatWindow");
//var usernameForm = document.querySelector("#usernameForm");
var messageArea = document.querySelector(".message-area");
var sendButton = document.querySelector("#send-button");
var hubid = document.querySelector("#hubID");

var stompClient = null;
var username = null;
var hubId = null;

// On window.load:
// create Stompclient, and websocket connection.
function connect(event) {
        username = document.querySelector("#username").innerHTML;
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    event.preventDefault();
}

// Sends a join message to a subscribed websocket topic
function onConnected(){
    // Subscribe to topic/public/{hubId}
    hubId = hubid.className;
    console.log("/topic/public/" + hubId);
    var sub1 = stompClient.subscribe("/topic/public/" + hubId, onMessageReceived);

    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username,
            channelId: hubId,
            type: "JOIN" })
    );

}

// Prints an error message if websocket connection cannot be established.
function onError(error) {
    console.log(error);
}

// handler for sending messages to the subscribed topic.
function sendMessage(event) {

    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient){
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: "CHAT",
            channelId: hubId
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        console.log(JSON.stringify(chatMessage));
        messageInput.value = "";
    }
    event.preventDefault();
}


// Handler for receiving messages, Joined messages and chat messages,
// prints the received messages on chatwindow.
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement("li");

    if(message.type === "JOIN") {
        message.content = message.sender + " joined!";
    }
    else if(message.type === "LEAVE"){
        message.content = message.sender + " left!";
    }
    else {
        var usernameElement = document.createElement('h4');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement("p");
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    console.log(message.channelId);

    messageArea.appendChild(messageElement);

    messageArea.scrollTop = messageArea.scrollHeight;

}


window.onload = connect;
//usernameForm.addEventListener('submit', connect, true);
sendButton.addEventListener('click', sendMessage, true);