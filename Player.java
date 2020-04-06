import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
public class Player extends GameObject{
    private float gravity = 1f;
    private int maxspeed = 10;
    public boolean enabled = true;
    private Color state = Color.yellow;
    public int score = 0;
    Handler h;
    public boolean alive = true;
    public Player(int x, int y, Handler h){
        super (x, y, ID.Player);
        this.h = h;
    }
    public void tick(){
        //use velocity instead of position updates
        //positive y is going down
        x += velx;
        y += vely;
        
        //physics acceleration doner would be proud
        vely += gravity;
        if(vely > maxspeed) vely = maxspeed; //cap the speed so player doesn't go too fast
        y = Game.clamp(y, -50, Game.H-132);


        //check collision
        for(GameObject o: h.objects){
            if(o.id==ID.Pipe && alive){
                //check if all rectangles in this collides with any of the rectangles in the other one
                if(collision(this.getBounds(), o.getBounds())){
                    System.out.println("Collided with pipe");
                    alive = false;
                }
            }
            //add score if collided with scorezone
            else if(o.id == ID.ScoreZone && alive){
                if(collision(this.getBounds(), o.getBounds())){
                    ScoreZone s = (ScoreZone) o;
                    if(!s.scored){
                        score++;
                        System.out.println("Score: " + score);
                        
                        s.scored = true;
                    }
                }
            }
        }
    }

    boolean collision(ArrayList<Rectangle> bounds1, ArrayList<Rectangle> bounds2){
        for(Rectangle b1 : bounds1){
            for(Rectangle b2: bounds2){
                if(b1.intersects(b2)){
                    return true;
                }
            }
        }
        return false;  
    }
    public void render(Graphics g){
        if(!alive){
            state = Color.red;
        }
        g.setColor(state);
        g.fillRect(x, y, 32, 32);
    }
    
    public ArrayList<Rectangle> getBounds(){
        ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
        bounds.add(new Rectangle(x, y, 32, 32));
        return bounds;
    }
}