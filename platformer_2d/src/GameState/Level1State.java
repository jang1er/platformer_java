package GameState;

import java.awt.*;
import Main.GamePanel;
import Tilemap.*;

public class Level1State extends GameState {

	private TileMap tileMap;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		System.out.println("loaded tileset");
		tileMap.loadMap("/Maps/level1-1.map");
		System.out.println("loaded map level 1");
		tileMap.setPosition(0, 0);
		
	}
	
	
	public void update() {}
	public void draw(Graphics2D g) {
	
		//clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw tilemap
		tileMap.draw(g);
	}	
	
	public void keyPressed(int k ) {}
	public void keyReleased(int k) {}

	@Override
	public void render(Graphics2D g) {
		//clear screen
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
				
				//draw tilemap
				tileMap.draw(g);
		
	}
	
	
}
