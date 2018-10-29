package com.wielik.kappa.gfx;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends JFrame {

	Canvas canvas;
	Graphics g;
	BufferStrategy bs;
	BufferedImage image;
	
	public Window(int width, int height, float scale, String title) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		canvas = new Canvas();
		Dimension size = new Dimension((int) (width * scale), (int) (height * scale));
		canvas.setPreferredSize(size);
		canvas.setMaximumSize(size);
		canvas.setMinimumSize(size);
		
		setTitle(title);
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(canvas, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		g = bs.getDrawGraphics();
	}
	
	public void drawScreen() {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		bs.show();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
