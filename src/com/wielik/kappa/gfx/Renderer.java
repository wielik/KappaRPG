package com.wielik.kappa.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderer {
	private int pW;  // pixel width
	private int pH;  // pixel height
	private float scale;
	private int[] pixels;
	
	public Renderer(int width, int height, BufferedImage image) {
		this.pW = width;
		this.pH = height;
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) pixels[i] += 250;
	}
}
