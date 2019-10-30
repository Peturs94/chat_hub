package is.hi.hbv501g.chathub.Chathub.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String email;
    //private List<User> friendList;

    public User(){

    }

    public User(String userName){

        this.userName = userName;
    }

    public long getId() {

        return id;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    //public List<User> getFriendList() {
    //    return friendList;
    //}

    //public void setFriendList(List<User> friendList) {
     //   this.friendList = friendList;
    //}
}
