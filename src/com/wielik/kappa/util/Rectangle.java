package com.wielik.kappa.util;

public class Rectangle {
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;
	
	public Rectangle(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {return xPos;}
	public int getY() {return yPos;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
}
