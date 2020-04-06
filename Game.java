import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Game extends Canvas implements Runnable{
    //dimensions for game
    public static final int H = 600, W = H*9/12;
    private Thread t; // thread for the game to run on - single threaded game
    private boolean running = false;
    private Handler h;
    private KeyInput ki;
    private Player p;
    
    //pipe settings
    public static int pipeSpeed = 5, pipeSpace = 75;
    
    //death stuff
    Scanner sc;
    String msg = "";
    public Game() throws Exception{
        h = new Handler();
        p = new Player(50, 50, h);
        h.add(p);
        ki = new KeyInput(h);
        addKeyListener(ki);
        sc = new Scanner(new File("death.txt"), StandardCharsets.UTF_8.name());
        new Window(W, H, "REEEE", this);
    }
    public synchronized void start(){
        t = new Thread(this);
        running = true;
        t.run(); //start the game on the game thread
    }

    public synchronized void stop(){
        try{
            t.join();
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
        long timer2 = System.currentTimeMillis();
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
            //triggers every second
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;

                if(p.alive){
                    //randomly place the pipes every second
                    int y = (int)(Math.random()*350)+100;
                    h.add(new Pipe(y, p));
                    h.add(new ScoreZone(y));
                }
            }
            if(System.currentTimeMillis() - timer2 > 35){
                timer2 += 35;
                //typing effect for death message
                if(!p.alive){
                    removeKeyListener(ki);
                    //get the death message and update it
                    if(sc.hasNext()){
                        msg=sc.nextLine();
                        System.out.println(msg);
                    }
                }
            }
        } 
        stop();
        //end templated game loop
    }

    public void tick(){
        h.tick();
    }
    public void render(){
        //allow pre rendering of frames (to make game smoother)
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //draw background
        g.setColor(Color.black);
        g.fillRect(0, 0, W, H);

        h.render(g);

        //fill in the floor
        g.setColor(Color.green);
        g.fillRect(0, 500, W, 100);
        //do score board
        g.setColor(Color.white);
        g.drawString("Score: " + p.score, W/2-100, 50);
        //render the player last so it appears on top
        p.render(g);
        if(!p.alive){
            g.setColor(Color.red);
            g.drawString(msg, p.x-(msg.length()*3/2), p.y);
        }
        bs.show();
        g.dispose(); // clear the memory usage
    }
    public static int clamp(int curr, int min, int max){
        curr = curr < min ? min : curr;
        curr = curr > max ? max : curr;
        return curr;
    }
    public static void main(String[] args){
        try{
            new Game();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}