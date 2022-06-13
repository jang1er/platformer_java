package Tilemap;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.*;

import Main.GamePanel;

public class Background {
  
  private BufferedImage image;

  private double x;
  private double y;
  private double dx;
  private double dy;
  
  private double moveScale;
  
  public Background(String path, double ms) {
    try {
      
      image = ImageIO.read(
          getClass().getResourceAsStream(path)
          );
      moveScale = ms;
      System.out.println("loaded image");
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
  
  public void setPosition(double x, double y) {
    this.x = (x * moveScale % GamePanel.WIDTH);
    this.y = (y * moveScale % GamePanel.HEIGHT);
  }
  
  public void setVector(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }
  
  public void update() {
    x += dx;
    while(x <= -GamePanel.WIDTH) x += GamePanel.WIDTH;
    while(x >= GamePanel.WIDTH) x -= GamePanel.WIDTH;
    y += dy;
    while(y <= -GamePanel.HEIGHT) y += GamePanel.HEIGHT;
    while(y >= GamePanel.HEIGHT) y -= GamePanel.HEIGHT;
  }
  
  public void draw(Graphics g) {
    g.drawImage(image, (int)x, (int)y, null);
    
    if (x < 0) {
      g.drawImage(image, (int)(x + GamePanel.WIDTH), (int) y, null);
    }
    
    if(x > 0) {
      g.drawImage(image,(int) x - GamePanel.WIDTH,(int) y, null);
    }
  }
}