import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
//game engine stuff?
public abstract class GameObject{
    protected ID id;
    protected int x, y;
    protected float velx, vely;
    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick(); // each game object will update itself
    public abstract void render(Graphics g); //each game object will render itself
    public abstract ArrayList<Rectangle> getBounds(); //for collision bounds
}