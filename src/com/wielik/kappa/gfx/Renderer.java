package com.wielik.kappa.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.wielik.kappa.map.Map;
import com.wielik.kappa.map.Tile;
import com.wielik.kappa.map.Tiles;
import com.wielik.kappa.util.Camera;

public class Renderer {
	
	private final int ALPHA_COL = 0xffff00ff; //This color won't be renderered, use as alpha channel
	private int pW;  // pixel width
	private int pH;  // pixel height
	private float scale;
	private int[] pixels;
	
	private Camera camera;
	
	public Renderer(int width, int height, BufferedImage image) {
		this.pW = width;
		this.pH = height;
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
 	public void clear() {
		for(int i = 0; i < pixels.length; i++) pixels[i] = 0;
	}
	
	public void drawImage(Image image, int offsetX, int offsetY) {
		renderArray(image.getPixels(), image.getWidth(), image.getHeight(), offsetX, offsetY, 1);
	}
	
	public void drawSprite(Sprite sprite, int offsetX, int offsetY) {
		renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), offsetX, offsetY, 1);
	}
	
	public void drawLevel(Map level, int offsetX, int offsetY) {
		
	}
	
	public void setPixel(int x, int y, int value) {
		if(x < 0 || x >= pW || y < 0 || y >= pH || value == ALPHA_COL) {
			return;
		}
		pixels[x + y * pW] = value;
	}
	
	public void renderArray(int[] pixels, int renderWidth, int renderHeight, int offsetX, int offsetY, int zoom) {
		for(int y = 0; y < renderHeight; y++) {
			for(int x = 0; x < renderWidth; x++) {
				setPixel(x + offsetX, y + offsetY, pixels[x + y * renderWidth]);
			}
		}
	}
}
