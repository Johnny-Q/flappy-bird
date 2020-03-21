import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
//game engine stuff?
public class Window extends Canvas{
    public Window(int w, int h, String title, Game g){
        JFrame f = new JFrame(title);
        Dimension d = new Dimension(w, h);
        f.setPreferredSize(d);
        f.setMaximumSize(d);
        f.setMinimumSize(d);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.add(g);
        f.setVisible(true);
        g.start();
    }
}