package GameState;

import java.util.ArrayList;

public class GameStateManager {

  private ArrayList<GameState> gameStates;
  private int currentState;
  
  public static final int MENUSTATE = 0;
  public static final int LEVEL1STATE = 1;
  
  public GameStateManager() {
    gameStates = new ArrayList<GameState>();
    
    currentState = MENUSTATE;
    gameStates.add(new MenuState(this));
    gameStates.add(new Level1State(this));
    
    // initialise menustate
    gameStates.get(MENUSTATE).init();
  }
  
  public void setState(int state) {
	// load resources of new state
    gameStates.get(state).init();
    System.out.println("updating new state to: " + state);
    
    // cache previous state / hand over control to new state
    int oldstate = currentState;
    currentState = state;
    
    // unload resources of previous state
    gameStates.get(oldstate).unloadState();
  }
  
  public void update() {
    gameStates.get(currentState).update();
  }
  
  public void render(java.awt.Graphics2D g) {
    gameStates.get(currentState).render(g);
  }
}
