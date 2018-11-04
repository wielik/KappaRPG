package com.wielik.kappa.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	private int pW;
	private int pH;
	private int[] pixels;
	
	public Sprite(int tileSize, int color) {
		this.pW = tileSize;
		this.pH = tileSize;
		pixels = new int[pW * pH];
		for(int i = 0; i < pixels.length; i++) pixels[i] = color;
	}
	
	public Sprite(SpriteSheet sheet, int startX, int startY, int width, int height) {
		this.pW = width;
		this.pH = height;
		pixels = new int[pW * pH];
		sheet.getImage().getRGB(startX, startY, pW, pH, pixels, 0, pW);
	}
	
	public Sprite(BufferedImage image) {
		this.pW = image.getWidth();
		this.pH = image.getHeight();
		pixels = new int[pW * pH];
		image.getRGB(0, 0, pW, pH, pixels, 0, pW);
	}
	
	public int[] getPixels() {return pixels;}
	public int getWidth() {return pW;}
	public int getHeight() {return pH;}
}
