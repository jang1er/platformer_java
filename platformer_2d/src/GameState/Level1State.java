package GameState;

import java.awt.*;

import TileMap.*;
import Entity.*;
import static Manager.InputManager.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100); 
		
	}
	
	
	public void update() {
		if(getTypedKeyState(ESCAPE))gsm.setState(0); 
		
		if(getTypedKeyState(ARROW_LEFT)) player.setLeft(true);
		if(getTypedKeyState(ARROW_RIGHT)) player.setRight(true);
		if(getTypedKeyState(ARROW_UP)) player.setUp(true);
		if(getTypedKeyState(ARROW_DOWN)) player.setDown(true);
		if(getTypedKeyState(W)) player.setJumping(true);
		if(getTypedKeyState(E)) player.setGliding(true);
		if(getTypedKeyState(R)) player.setScratching();
		if(getTypedKeyState(F)) player.setFiring();
		
		if(getTypedKeyState(ARROW_LEFT)) player.setLeft(false);
		if(getTypedKeyState(ARROW_RIGHT)) player.setRight(false);
		if(getTypedKeyState(ARROW_UP)) player.setUp(false);
		if(getTypedKeyState(ARROW_DOWN)) player.setDown(false);
		if(getTypedKeyState(W)) player.setJumping(false);
		if(getTypedKeyState(E)) player.setGliding(false);
		
		
		
		
		//update player
		player.update();
		tileMap.setPosition(ARROW_DOWN, A);
	}

	@Override
	public void render(Graphics2D g) {
		/*clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);*/
		
		//draw bg
		bg.draw(g);
		
		//draw tilemap
		tileMap.draw(g); 
		
		//draw player
		player.draw(g);
		
	}
	
		
	

	@Override
	public void unloadState() {
		tileMap = null;
		bg = null;
	}
	
	
}
