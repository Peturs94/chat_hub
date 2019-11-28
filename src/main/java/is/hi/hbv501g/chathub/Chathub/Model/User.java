package is.hi.hbv501g.chathub.Chathub.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String uName;
    private String password;
    private String mood;


    public User(){

    }

    public User(String uName, String password, String mood){
        this.uName = uName;
        this.password = password;
        this.mood=mood;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // The user doesn't choose it's mood on instantiation
    // so initial mood will be "anything"
    public String getMood() {
        if(mood == null) return "Anything";
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
