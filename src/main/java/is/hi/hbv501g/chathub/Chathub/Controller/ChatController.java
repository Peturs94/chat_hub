package is.hi.hbv501g.chathub.Chathub.Controller;


import is.hi.hbv501g.chathub.Chathub.Model.ChatMessage;
import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.service.MessageService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    MessageService msgService;

    @Autowired
    public ChatController(MessageService msgService){
        this.msgService = msgService;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload JSONObject obj, SimpMessageHeaderAccessor headerAccessor){
        ChatMessage.MessageType type = ChatMessage.MessageType.valueOf((String) obj.get("type"));
        System.out.println(obj.get("sender"));
        String sender = (String) obj.get("sender");
        String content = (String) obj.get("content");
        String channelId = (String) obj.get("channelId");
        System.out.println("ChannelId = " + channelId);
        ChatMessage msg = new ChatMessage(type, content, sender, channelId);
        msgService.save(msg);
        messagingTemplate.convertAndSend("/topic/public/" + channelId , obj);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload JSONObject obj, SimpMessageHeaderAccessor headerAccessor){
        //add Username in websocket session.
        String sender = (String) obj.get("sender");
        String channelId = (String) obj.get("channelId");
        ChatMessage.MessageType type = ChatMessage.MessageType.valueOf((String) obj.get("type"));
        String content = (String) obj.get("content");

        headerAccessor.getSessionAttributes().put("username", sender);
        headerAccessor.getSessionAttributes().put("channelId", channelId);
        ChatMessage msg = new ChatMessage(type, content, sender, channelId);

        messagingTemplate.convertAndSend("/topic/public/" + channelId, obj);
    }


}
