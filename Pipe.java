import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class Pipe extends GameObject{

    //y will be center of the spacing
    private final int space = 75;
    private final int W = 50, H = 1000;
    private final int pipeSpeed = 5;
    public Pipe(int y){
        super(Game.W, y, ID.Pipe);
    }
    public void tick(){
        x-=pipeSpeed;
    }
    public void render(Graphics g){
        g.setColor(Color.green);
        /*
        //top pipe
        g.fillRect(x, y-space-H, W, H);
        //bottom pipe
        g.fillRect(x, y+space, W, H);
        */
        g.fillRect(x, y, W, H);
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, W, H);
    }
}