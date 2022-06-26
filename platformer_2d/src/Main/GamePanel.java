package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.time.Instant;
import java.time.Duration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

// custom imports
import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener{

  // dimensions
  public static final int WIDTH = 320;
  public static final int HEIGHT = 240;
  public static final int SCALE = 2;

  // game Thread
  private Timer timer;
  private int ticksPerSecond = 0;
  private int paintsPerSecond = 0;
  
  // image
  private BufferedImage image;
  private Graphics2D g_image;

  // game state manager
  private GameStateManager gsm;


  public GamePanel(JFrame frame) {
    super();
    
    // set Window Size
    setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    setFocusable(true);
    requestFocus();
    
    init();
    
    timer = new Timer(1,new ActionListener() {
    	 private Instant lastTick;
         private int ticks = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (lastTick == null) {
                lastTick = Instant.now();
            }
            if (Duration.between(lastTick, Instant.now()).toMillis() >= 1000) {
                ticksPerSecond = ticks;
                lastTick = Instant.now();
                ticks = 0;
                
                frame.setTitle("FPS: " + paintsPerSecond + " @" + ticksPerSecond + " ticks");
            }
            ticks++;
            
            gsm.update();
            
            repaint();
		}
    	
    });
  }

  public void addNotify() {
    super.addNotify();
    addKeyListener(this);
    timer.start();
  }

  private void init() {

    // create image --> Game is drawn on here
    image = new BufferedImage(
        WIDTH, HEIGHT,
        BufferedImage.TYPE_INT_RGB
        );
    // get graphics component of game image
    g_image = (Graphics2D) image.getGraphics();

    // adds new GameStateManager
    gsm = new GameStateManager();
  }

/* @Override
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
        System.out.println("FPS: " + frames + ", ticks: " + ticks);
        frames = 0;
        ticks = 0;
      }
    }

  }*/
  

  private void update() {
    gsm.update();
  }

  private void render() {
   // gsm.render(g);


    // Draw To Screen
    Graphics g2 = getGraphics();
    g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
    g2.dispose();
  }
  
  
  private Instant lastPaint;
  private int paints = 0;

  @Override
  protected void paintComponent(Graphics g) {
	  
	  super.paintComponent(g);
      if (lastPaint == null) {
          lastPaint = Instant.now();
      }
      if (Duration.between(lastPaint, Instant.now()).toMillis() >= 1000) {
          paintsPerSecond = paints;
          lastPaint = Instant.now();
          paints = 0;
          
      }
      paints++;
	  
	  // gsm renders game on image
	  gsm.render(g_image);
	  
	  // create graphic surface for jpanel
	  Graphics2D g2 = (Graphics2D) g.create();
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
    gsm.keyPressed(e.getKeyCode());
  }


  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    gsm.keyReleased(e.getKeyCode());

  }

}
