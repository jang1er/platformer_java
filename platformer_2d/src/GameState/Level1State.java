package GameState;

import java.awt.*;

import TileMap.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		
	}
	
	
	public void update() {}
	
	public void keyPressed(int k ) {}
	public void keyReleased(int k) {}

	@Override
	public void render(Graphics2D g) {
		/*clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);*/
		
		//draw bg
		bg.draw(g);
		
		//draw tilemap
		tileMap.draw(g); 
		
		
	}

	@Override
	public void unloadState() {
		// TODO Auto-generated method stub
		
	}
	
	
}
