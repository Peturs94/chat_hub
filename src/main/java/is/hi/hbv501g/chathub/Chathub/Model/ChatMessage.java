package is.hi.hbv501g.chathub.Chathub.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private MessageType type;
    private String content;
    private String sender;
    private String channelId;
    //Bæta við timestamp!


    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ChatMessage(){

    }

    public ChatMessage(MessageType type, String content, String sender, String channelId){
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.channelId = channelId;
    }


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String ChannelId) {
        this.channelId = channelId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

