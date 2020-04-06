import java.util.*;
import java.awt.*;
import javax.swing.*;
//game engine stuff?
public class Handler{
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    //call the tick method for each object
    public void tick(){
        //need to use iter because I have to delete objects while I'm iterating
        for(Iterator<GameObject> iter = objects.iterator(); iter.hasNext();) {
            GameObject o = iter.next();
            o.tick();
            //remove pipes if they get out of the screen
            if ((o.id == ID.Pipe || o.id == ID.ScoreZone) && o.x < -50) {
                iter.remove();
            }
        }
    }
    //call the render method for each object
    public void render(Graphics g){
        for(GameObject o : objects){
            o.render(g);
        }
    }
    public void add(GameObject o){
        this.objects.add(o);
    }
    public void remove(GameObject o){
        this.objects.remove(o);
    }
}