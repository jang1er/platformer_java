package Tilemap;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {

	//position
	private double x;
	private double y;
	
	//bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	//map
	private int[] [] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[] [] tiles;
	
	//drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		tween = 0.07;
	}
	
	public void loadTiles(String s) {
	
		try {
			
			tileset = ImageIO.read(
					getClass().getResourceAsStream(s)
		);
		numTilesAcross = tileset.getWidth() / tileSize;
		tiles = new Tile[2][numTilesAcross];
		
		BufferedImage subimage;
		for(int col = 0; col < numTilesAcross; col++) {
			subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
			
			tiles[0][col] = new Tile(subimage, Tile.NORMAL);
			subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
			
			tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


		
		
	
	
	public void loadMap(String s) {
		
		try {
		
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
