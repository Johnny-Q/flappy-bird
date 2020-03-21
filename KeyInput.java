import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter{
    private Handler handler;
    public KeyInput(Handler h){
        this.handler = h;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(GameObject o:handler.objects){
            if(o.id == ID.Player){
                Player p = (Player) o;
                if(p.enabled){
                    if(key == KeyEvent.VK_SPACE){
                        o.vely = -10;
                    }
                }
                
            }
        }
    }
} 