package GameState;

import java.awt.Graphics2D;

public abstract class GameState {
  
  protected GameStateManager gsm;


  public abstract void init();
  public abstract void unloadState();
  public abstract void update();
  public abstract void render(Graphics2D g);
}