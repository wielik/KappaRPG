package com.wielik.kappa.util;

import com.wielik.kappa.entity.GameObject;

public class Camera extends Rectangle {

	private int zoom;
	private GameObject attachedObject;
	private boolean attached;
	
	public Camera(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		zoom = 1;
		attached = false;
	}
	
	public void update() {
		if(attached) {
			xPos = attachedObject.getX() - width/2 + attachedObject.getSprite().getWidth();
			yPos = attachedObject.getY() - height/2 + attachedObject.getSprite().getHeight();
		}
	}
	
	public void attach(GameObject gameObject) {
		attachedObject = gameObject;
		attached = true;
	}
	
	public void deattach() {
		attachedObject = null;
		attached = false;
	}
	
	public void moveToPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void moveBy(int x, int y) {
		this.xPos += x;
		this.yPos += y;
	}
	
	public void setZoom(int zoom) {this.zoom = zoom;}
	public int getZoom() {return zoom;}
	
}
