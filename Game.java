import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;


public class Game extends Canvas implements Runnable{
    public static final int H = 600, W = H*9/12;
    private Thread t;
    private boolean running = false;
    private Handler h;
    private KeyInput ki;
    private int pipeSpace = 75;
    private int pipeLength = 1000;
    public Game(){
        h = new Handler();
        h.add(new Player(50, 50, h));
        ki = new KeyInput(h);
        addKeyListener(ki);
        new Window(W, H, "REEEE", this);
    }
    
    public synchronized void start(){
        t = new Thread(this);
        t.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            t.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        // start templated game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();//custom function (we define)
                delta--;
            }
            if(running){
                render();//custom function
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                int y = (int)(Math.random()*350)+100;
                
                h.add(new Pipe(y-pipeSpace-pipeLength));
                h.add(new Pipe(y+pipeSpace));
                
            }
        }
        stop();
        //end templated game loop
    }
    public void tick(){
        h.tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, W, H);

        h.render(g);

        g.dispose();
        bs.show();
    }
    public static int clamp(int curr, int min, int max){
        curr = curr < min ? min : curr;
        curr = curr > max ? max : curr;
        return curr;
    }
    public static void main(String args[]){
        new Game();
    }
}