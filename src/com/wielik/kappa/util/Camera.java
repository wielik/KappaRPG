package com.wielik.kappa.util;

public class Camera extends Rectangle {

	private float zoom;
	
	
	public Camera(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
	}
	
	public void setZoom(float zoom) {this.zoom = zoom;}
	public float getZoom() {return zoom;}
	
}
