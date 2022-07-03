package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.GamePanel;
import Tilemap.Background;

public class MenuState extends GameState{
  
  
    private Background bg;
    private GameStateManager gm;
    
    private int currentOption = 0;
    private String[] options = {
      "Start",
      "Options",
      "Quit"
    };
    
    private String title = "Platformer";
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gm) {
      
      this.gm = gm;
      
      try {
        bg = new Background("/Backgrounds/menubg.gif", 1);
      
        bg.setVector(-0.1, 0);
      
        titleColor = new Color(128, 0, 0);
        titleFont = new Font("Century Gothic", Font.PLAIN, 28);
        
        font = new Font("Arial", Font.PLAIN, 12);
        
        
      }catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Override
    public void init() {
      // TODO Auto-generated method stub

    }

    @Override
    public void update() {
      // TODO Auto-generated method stub
      bg.update();
    }

    @Override
    public void render(Graphics2D g) {
      
      // Draw Background
      bg.draw(g);
      
      //draw title
      g.setColor(titleColor);
      g.setFont(titleFont);
      g.drawString(title, GamePanel.WIDTH/2 - (g.getFontMetrics(titleFont).stringWidth(title))/2, GamePanel.HEIGHT/4);
      
      // draw options
      for (int i = 0; i < options.length; i++) {
        if (currentOption == i) {
          g.setColor(Color.red);
        }else {
          g.setColor(Color.black);
        }
        
        g.setFont(font);
        g.drawString(options[i],
            GamePanel.WIDTH/2 - (g.getFontMetrics(font).stringWidth(options[i]))/2,
            (GamePanel.HEIGHT/4)+60+20*i
            );
      }
    }

    private void select() {
    	if(currentOption == 0) {
    		gsm.setState(GameStateManager.LEVEL1STATE);
    	}
    	if(currentOption == 1) {
    		//help
    	}
    	if(currentOption == 2) {
    		System.exit(0);
    	}
    }
    
    
    @Override
    public void keyPressed(int k) {
      // TODO Auto-generated method stub
      System.out.println(k);
      if (k == 38) currentOption--;
      if (k == 40) currentOption++;
      if(currentOption < 0)currentOption = options.length-1;
      if(currentOption >= options.length) currentOption = 0;
    }

    @Override
    public void keyReleased(int k) {
      // TODO Auto-generated method stub
      
    }
}