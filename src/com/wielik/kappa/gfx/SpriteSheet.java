package com.wielik.kappa.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private int[] pixels;
	private BufferedImage image;
	
	private int pixelWidth;
	private int pixelHeight;
	private int spriteWidth;
	private int spriteHeight;
	private int spriteSizeX;
	private int spriteSizeY;
	
	private Sprite[] loadedSprites = null;
	
	public SpriteSheet(BufferedImage sheetImage) {
		this.image = sheetImage;
		pixelWidth = sheetImage.getWidth();
		pixelHeight = sheetImage.getHeight();
		
		pixels = new int[pixelWidth * pixelHeight];
		pixels = sheetImage.getRGB(0, 0, pixelWidth, pixelHeight, null, 0, pixelWidth);
	}
	
	public SpriteSheet(String path) {
		BufferedImage image = null;
		System.out.println("Trying to load SpriteSheet from image at" + path + " ...");
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.image = image;
			this.pixelWidth = image.getWidth();
			this.pixelHeight = image.getHeight();
			this.pixels = new int[pixelWidth * pixelHeight];
			image.getRGB(0, 0, pixelWidth, pixelHeight, pixels, 0, pixelWidth);
			System.out.println(" success!");
		} catch (IOException e) {
			System.err.println(" failed!");
			e.printStackTrace();
		}
	}
	
	public void loadSprites(int spriteSizeX, int spriteSizeY) {
		this.spriteSizeX = spriteSizeX;
		this.spriteSizeY = spriteSizeY;
		this.spriteWidth = pixelWidth / spriteSizeX;
		this.spriteHeight = pixelHeight / spriteSizeY;
		
		loadedSprites = new Sprite[spriteWidth * spriteHeight];
		
		int spriteID = 0;
		for(int y = 0; y < pixelHeight; y += spriteSizeY) {
			for(int x = 0; x < pixelWidth; x += spriteSizeX) {
				loadedSprites[spriteID] = new Sprite(this, x, y, spriteSizeX, spriteSizeY);
				spriteID++;
			}
		}
	}
	
	public Sprite getSpriteAt(int xCoord, int yCoord) {
		if(loadedSprites == null) {
			System.err.println("Spritesheet has no loaded sprites. Load sprites first.");
			return null;
		}
		if(xCoord> spriteWidth || xCoord < 0 || yCoord > spriteHeight || yCoord < 0) {
			System.err.println("Theres is no sprite at " + xCoord + ", " + yCoord + ". Out of range.");
			return null;
		}
		return loadedSprites[xCoord + yCoord * spriteSizeY];
	}
	
	public Sprite getSprite(int ID) {
		if(loadedSprites == null) {
			System.err.println("Spritesheet has no loaded sprites. Load sprites first.");
			return null;
		}
		if(ID > (spriteWidth * spriteHeight)) {
			System.err.println("Theres is no sprite at " + ID + " id. Out of range. Max ID = " + (spriteWidth * spriteHeight));
			return null;
		}
		return loadedSprites[ID];
	}
	
	public Sprite[] getLoadedSprites() {
		return loadedSprites;
	}
	
	public int[] getPixels() {return pixels;}
	public BufferedImage getImage() {return image;}
}
