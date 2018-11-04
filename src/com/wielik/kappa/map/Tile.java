package com.wielik.kappa.map;

import com.wielik.kappa.gfx.Sprite;

public class Tile {
	private boolean walkable;
	private int speedMultiplier;
	
	private Sprite sprite;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Tile(Sprite sprite, boolean isWalkable, int speedMultiplier) {
		this.sprite = sprite;
		this.walkable = isWalkable;
		this.speedMultiplier = speedMultiplier;
	}
	
	public Sprite getSprite() {return sprite;}
	public int getSpeedMultiplier() {return speedMultiplier;}
	public boolean isWalkable() {return walkable;}
}
