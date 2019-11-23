//'use strict' lint?

var usernamePage = document.querySelector("#username-page");
var chatContainer = document.querySelector("#chat-container");
var messageInput = document.querySelector("#message-input");
var connectingElement = document.querySelector(".connecting");
var chatWindow = document.querySelector("#chatWindow");
var usernameForm = document.querySelector("#usernameForm");
var messageArea = document.querySelector("#message-area");
var sendButton = document.querySelector("#send-button");

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector("#name").value.trim();

    if(username){
        usernamePage.style.display = "none";
        chatWindow.classList.remove("container-fluid");

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected(){
    // Subscribe to the public topic
    stompClient.subscribe("/topic/public", onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username,
                               type: "JOIN" })
    );

    connectingElement.style.display = "none";
}

function onError(error) {
    connectingElement.textContent = "Could not connect to websocket server. PLease refresh";
    connectingElement.style.color = "red";
}


function sendMessage(event) {

    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient){
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: "CHAT"
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = "";
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement("li");

    if(message.type === "JOIN") {
        message.content = message.sender + " joined!";
    } else if (message.type === 'LEAVE') {
        message.content = message.sender + ' left!';
    } else {


        var usernameElement = document.createElement('h4');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement("p");
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;

}



usernameForm.addEventListener('submit', connect, true);
sendButton.addEventListener('click', sendMessage, true);