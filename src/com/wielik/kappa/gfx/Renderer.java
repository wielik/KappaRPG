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
		renderArray(image.getPixels(), image.getWidth(), image.getHeight(), offsetX, offsetY, camera.getZoom());
	}
	
	public void drawSprite(Sprite sprite, int offsetX, int offsetY) {
		renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), offsetX, offsetY, camera.getZoom());
	}
	
	public void setPixel(int x, int y, int value) {
		if(value == ALPHA_COL)	return;
		if(x >= camera.getX() && y >= camera.getY() && x <= camera.getX() + camera.getWidth() && y <= camera.getY() + camera.getHeight()) {
			int pixelIndex = (x - camera.getX()) + (y - camera.getY()) * pW;
			if(pixels.length > pixelIndex) pixels[pixelIndex] = value;
		}
	}
	
	public void renderArray(int[] pixels, int renderWidth, int renderHeight, int offsetX, int offsetY, int zoom) {
		
		for(int y = 0; y < renderHeight; y++) {
			for(int x = 0; x < renderWidth; x++) {
				for(int yZoomPosition = 0; yZoomPosition < zoom; yZoomPosition++) {
					for(int xZoomPosition = 0; xZoomPosition < zoom; xZoomPosition++) {
						setPixel((x * zoom) + offsetX + xZoomPosition, (y * zoom) + offsetY + yZoomPosition, pixels[x + y * renderWidth]);
					}
				}
			}
		}
	}
	
	public Camera getCamera() {return camera;}
	public int getPixelWidth() {return pW;}
	public int getPixelHeight() {return pH;}
}
