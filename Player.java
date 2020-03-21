import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Player extends GameObject{
    private float gravity = 1f;
    private int maxspeed = 10;
    private Handler h;
    public boolean enabled = true;
    private Color state = Color.yellow;
    public Player(int x, int y, Handler h){
        super (x, y, ID.Player);
        this.h = h;
    }
    public void tick(){
        x += velx;
        y += vely;

        vely += gravity;
        if(vely > maxspeed) vely = maxspeed;
        y = Game.clamp(y, -50, Game.H-60);
        //check collision
        for(GameObject o: h.objects){
            if(o.id==ID.Pipe){
                if(this.getBounds().intersects(o.getBounds())){
                    System.out.println("Collision");
                    enabled = false;
                    state = Color.red;
                }
            }
        }
    }
    public void render(Graphics g){
        g.setColor(state);
        g.fillRect(x, y, 32, 32);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }
}