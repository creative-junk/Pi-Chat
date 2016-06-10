package crysoftdynamics.timeline;

/**
 * Created by Maxx on 6/9/2016.
 */
public class Message {
    public String text;
    public String name;
    public String thumbnail;
    public long time;
    public boolean left;
    public String message;

    public Message(boolean left, String message){
        super();
        this.left = left;
        this.message = message;

    }
}
