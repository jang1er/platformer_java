package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

// custom imports
import GameState.GameStateManager;
import Manager.InputManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

  // dimensions
  public static final int WIDTH = 320;
  public static final int HEIGHT = 240;
  public static final int SCALE = 2;

  // game Thread
  private Thread thread;
  private boolean running;
  private double GameTicks = 60;


  // image
  private BufferedImage image;
  private Graphics2D g;

  // game state manager
  private GameStateManager gsm;


  public GamePanel() {
    super();
    // set Window Size
    setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    setFocusable(true);
    requestFocus();
  }

  public void addNotify() {
    super.addNotify();
    if (thread == null) {
      thread = new Thread(this);
      addKeyListener(this);
      thread.start();
    }

  }

  private void init() {

    // create image --> Game is drawn on here
    image = new BufferedImage(
        WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_RGB
        );
    // get graphics component of game image
    g = (Graphics2D) image.getGraphics();

    // starts game clock
    running = true;

    // adds new GameStateManager
    gsm = new GameStateManager();
  }

  @Override
  public void run() {

    init();

    //game loop setup
    double ns = 1000000000 / GameTicks;
    double delta = 0;
    int frames = 0;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    int ticks = 0;

    // game loop
    while(running) {
      long now = System.nanoTime();
      delta +=  (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1) {
        update();
        ticks++;
        delta--;
      }
      if(running)
        render();
      frames++;


      if(System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        //System.out.println("FPS: " + frames + ", ticks: " + ticks);
        frames = 0;
        ticks = 0;
      }
    }

  }

  private void update() {
    gsm.update();
  }

  private void render() {
    gsm.render(g);


    // Draw To Screen
    Graphics g2 = getGraphics();
    g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    g2.dispose();
  }


  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }


  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    InputManager.Input_Pressed(e.getKeyCode());
  }


  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    InputManager.Input_Released(e.getKeyCode());

  }

}
