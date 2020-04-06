import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class ScoreZone extends GameObject{
    boolean scored = false;
    public ScoreZone(int y){
        super(Game.W + 25, y, ID.ScoreZone);
    }
    public void tick(){
        x -= Game.pipeSpeed;
    }
    public void render(Graphics g){
        //we don't draw anything
    }
    public ArrayList<Rectangle> getBounds(){
        ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
        bounds.add(new Rectangle(x, y - Game.pipeSpace, 3, Game.pipeSpace));
        return bounds;
    }
}