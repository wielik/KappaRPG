package com.wielik.kappa.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.wielik.kappa.gfx.Renderer;

public class Map {
	
	private int tileWidth;
	private int tileHeight;
	private int tileSize;
	private Tiles tileSet;
	private int[] tiles;
		
	public Map(File mapFile, Tiles tileSet) {
		this.tileSet = tileSet;
		loadFromFile(mapFile);
	}
	
	private void loadFromFile(File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find map file:" + file);
			e.printStackTrace();
			return;
		}
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(!line.startsWith("//")) { //ignores comments in file
				if(line.contains(":")) {
					String[] splitString = line.split(":");
					if(splitString[0].equalsIgnoreCase("MapSize")){
						String[] splitMapSize = splitString[1].split(",");
						tileWidth = Integer.parseInt(splitMapSize[0]);
						tileHeight = Integer.parseInt(splitMapSize[1]);
						tiles = new int[tileWidth * tileHeight];
						continue;
					}
					if(splitString[0].equalsIgnoreCase("TileSize")){
						tileSize = Integer.parseInt(splitString[1]);
						continue;
					}
					if(splitString[0].equalsIgnoreCase("FillMap")) {
						int fillTileID = Integer.parseInt(splitString[1]);
						fillMap(fillTileID);
						continue;
					}
				}
				String[] splitString = line.split(",");
				if(splitString.length > 4) {
					System.out.println("Line not formatted correctly while loading map form " + file);
					continue;
				}
				int layer = Integer.parseInt(splitString[0]);
				int tileID = Integer.parseInt(splitString[1]);
				int posX = Integer.parseInt(splitString[2]);
				int posY = Integer.parseInt(splitString[3]);
				tiles[posX + posY * tileWidth] = tileID;
			}
		}
	}
	
	private void fillMap(int ID) {
		for(int i = 0; i < tiles.length; i++) tiles[i] = ID;
	}
	
	public void render(Renderer r) {
		//TODO: jezeli mapa jest duza to renderowaæ tylko to co widzimy bo fpsy spadaja mocno
		int zoom = r.getCamera().getZoom();
		int renders = 0;
		int xPosition = r.getCamera().getX();
		int yPosition = r.getCamera().getY();
		int screenSizeX = r.getPixelWidth();
		int screenSizeY = r.getPixelHeight();
		
		if(xPosition < 0) xPosition = 0;
		if(yPosition < 0) yPosition = 0;
		
		int x0 = xPosition / tileSize;
		int y0 = yPosition / tileSize;
		
		int x1 = (xPosition + screenSizeX) / tileSize;
		int y1 = (yPosition + screenSizeY) / tileSize;
		
		int xIncrement = tileSize * zoom;
		int yIncrement = tileSize * zoom;
		

		for(int x = x0; x < x1; x++) {
			for(int y = y0; y < y1; y++) {
				tileSet.renderTile(r, tiles[x + y * tileWidth], x * xIncrement, y * yIncrement, zoom);
			}
		}
		/*
		for(int x = 0; x < tileWidth; x++) {
			for(int y = 0; y < tileHeight; y++) {
				pixX = x * xIncrement;
				pixY = y * yIncrement;
				if(pixX > (xPosition + screenSizeX) || pixY > (yPosition + screenSizeY) || pixX < xPosition || pixY < yPosition) continue;
				tileSet.renderTile(r, tiles[x + y * tileWidth], pixX, pixY, zoom);
			}
		}
		*/
	}
	
	public Tile getTile(int x, int y) {
		int id = tiles[x + y * tileWidth];
		return tileSet.getTileSet().get(id);
	}
	public Tiles getTileSet() {return tileSet;}
}
