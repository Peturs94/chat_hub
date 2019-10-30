package is.hi.hbv501g.chathub.Chathub.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Hub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    //private List<String> interests;

    public Hub(String name /*, List<String> interests*/) {
        this.name = name;
        //this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<String> getInterests() {
        return interests;
    }*/

    //?? viljum við hafa þetta?
    /*public void addInterests(List<String> addedInterests) {
        List<String> currentInterests = this.interests;
        currentInterests.addAll(addedInterests);
        this.interests = currentInterests;
    }*/

    //??
    /*public void removeInterests(){

    }*/
}
