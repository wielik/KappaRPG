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
	
	public void render(Renderer r, int zoom) {
		//TODO: jezeli mapa jest duza to renderowaæ tylko to co widzimy bo fpsy spadaja mocno
		int xIncrement = tileSize * zoom;
		int yIncrement = tileSize * zoom;
		
		for(int x = 0; x < tileWidth; x++) {
			for(int y = 0; y < tileHeight; y++) {
				tileSet.renderTile(r, tiles[x + y * tileWidth], x * xIncrement, y * yIncrement, zoom);
			}
		}
	}
	
	public Tiles getTileSet() {return tileSet;}
}
