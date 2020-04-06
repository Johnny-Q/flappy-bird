import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
public class Pipe extends GameObject{

    //y will be center of the spacing
    private final int W = 50, H = 1000;
    Player p;
    public Pipe(int y, Player p){
        super(Game.W, y, ID.Pipe);
        this.p = p;
    }
    public void tick(){
        //move the pipe if the player is alive
        if(p.alive){
            x -= Game.pipeSpeed;
        }
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        //top pipe
        //have to use these weird equations because it draws from the top
        g.fillRect(x, y-Game.pipeSpace-H, W, H);
        //bottom pipe
        g.fillRect(x, y+Game.pipeSpace, W, H);
    }
    public ArrayList<Rectangle> getBounds(){
        ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
        bounds.add(new Rectangle(x, y-Game.pipeSpace-H, W, H));
        bounds.add(new Rectangle(x, y+Game.pipeSpace, W, H));
        return bounds;
    }
}