package com.wielik.kappa.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private int width;
	private int height;
	private int[] pixels;
	
	public Image(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't load an image at " + path);
		}
		
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		image.flush();
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
