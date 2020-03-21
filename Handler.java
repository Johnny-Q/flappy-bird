import java.util.*;
import java.awt.*;
import javax.swing.*;
//game engine stuff?
public class Handler{
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    public void tick(){
        for(Iterator<GameObject> iter = objects.iterator(); iter.hasNext();) {
            GameObject o = iter.next();
            o.tick();
            if (o.id == ID.Pipe && o.x < 0) {
                iter.remove();
            }
        }
    }
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