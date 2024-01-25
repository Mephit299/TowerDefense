import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TransferQueue;

public class Game extends Canvas implements Runnable {
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static String title = "A ball!";
    public static BufferedImage icon;

    private BufferStrategy bs;
    private int ups = 60;
    private boolean running = false;
    private Thread thread;

    private ArrayList <Enemy> enemies = new ArrayList <Enemy>();
    private Map map = new Map();
    private int money = 650;
    public ArrayList <Tower> towers = new ArrayList<Tower>();


    public void draw(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        map.draw(g);
        for (int i = 0; i< enemies.size(); i++){
            enemies.get(i).draw(g);
        }
 //       ball.draw(g);
    }

    private void update() {
        enemies = map.move(enemies);
//        ball.update();
    }

    public Game() {
   //     ball = new Ball();
        try {
            icon = ImageIO.read(new File("images/icon.png"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(WIDTH, HEIGHT);
        JFrame frame = new JFrame(title);
        frame.setIconImage(icon);
        frame.add(this);
        frame.addKeyListener(new MyKeyListener());
        this.addMouseMotionListener(new MyMouseMotionListener());
        this.addMouseListener(new MyMouseListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.requestFocus();
        frame.setVisible(true);
    }

    /*
    This should not be changed...
     */
    public void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        draw(g);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        enemies.add(new Enemy(1));
        enemies.add(new Enemy(2));
        enemies.add(new Enemy(3));
        enemies.add(new Enemy(4));
        enemies.add(new Enemy(5));
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double ns = 1000000000.0 / ups;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                // Uppdatera koordinaterna
                update();
                // Rita ut bilden med updaterad data
                render();
                delta--;
            }
        }
        stop();
    }

    /*
    This should be changed....
     */
    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private class MyMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}