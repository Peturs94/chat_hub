package is.hi.hbv501g.chathub.Chathub.Model;

// Data transfer object used to change the users mood.
public class Mood {

    private String name;

    public Mood(){

    }

    public Mood(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
