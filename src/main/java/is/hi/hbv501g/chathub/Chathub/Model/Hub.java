package is.hi.hbv501g.chathub.Chathub.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hub {
    private String name;
    private String interest;
    private String description;
    //private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String channelId;
    private String channelType;
    public Hub(){

    }

    public Hub(String name, String interest, String description, String channelType, String channelId) {
        this.name = name;
        this.interest = interest;
        this.description = description;
        this.channelType = channelType;
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
