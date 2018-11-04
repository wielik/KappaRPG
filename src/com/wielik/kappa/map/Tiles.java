package com.wielik.kappa.map;

import java.util.ArrayList;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.SpriteSheet;

public class Tiles {
	private SpriteSheet sheet;
	private ArrayList<Tile> tilesList = new ArrayList<Tile>();
	
	public Tiles(SpriteSheet sheet, int tileSize) {
		this.sheet = sheet;
		if(sheet.getLoadedSprites() == null) sheet.loadSprites(tileSize, tileSize);
		for(int i = 0; i < sheet.getLoadedSprites().length; i++) {
			tilesList.add(new Tile(sheet.getSprite(i)));
		}
	}
	
	public void renderTile(Renderer r, int tileID, int xPos, int yPos, int zoom) {
		if(tileID >= 0 && tileID < tilesList.size()) {
			r.drawSprite(tilesList.get(tileID).getSprite(), xPos, yPos);
		}
		else{
			System.err.println("Couldn't draw a tile in tileset. TileID " + tileID + " is out of range of " + tilesList.size() + "." );
		}
	}
	
	public ArrayList<Tile> getTileSet() {return tilesList;}
}
