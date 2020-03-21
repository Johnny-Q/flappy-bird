import java.awt.Graphics;
import java.awt.Rectangle;
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
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
  
    //getters and setters
    public void setx(int _x){
      this.x = _x;
    }
    public void sety(int _y){
      this.y = _y;
    }
    public void setvx(float _vx){
      this.velx = _vx;
    }
    public void setvy(float _vy){
      this.vely = _vy;
    }
    public void setID(ID _id){
      this.id = _id;
    }
    public int getx(){
      return x;
    }
    public int gety(){
      return y;
    }
    public float getvx(){
      return velx;
    }
    public float getvy(){
      return vely;
    }
    public ID getid(){
      return id;
    }
}