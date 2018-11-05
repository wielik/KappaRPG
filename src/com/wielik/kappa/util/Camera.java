package com.wielik.kappa.util;

import com.wielik.kappa.entity.GameObject;
import com.wielik.kappa.gfx.Sprite;

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
			Sprite attachedSprite = attachedObject.getSprite();
			int spriteOffset_X = 0;
			int spriteOffset_Y = 0;
			if(attachedSprite != null) {
				spriteOffset_X = attachedSprite.getWidth();
				spriteOffset_Y = attachedSprite.getHeight();
			}
			xPos = attachedObject.getX() - width/2 + spriteOffset_X;
			yPos = attachedObject.getY() - height/2 + spriteOffset_Y;
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
