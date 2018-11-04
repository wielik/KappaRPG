package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;

public class Mob implements GameObject{

	protected int maxHealth;
	protected int health;
	protected int posX;
	protected int posY;
	protected int speed_x;
	protected int speed_y;
	protected Sprite sprite;
	
	protected boolean moving;
	
	public Mob(int posX, int posY, Sprite sprite) {
		this.posX = posX;
		this.posY = posY;
		this.sprite = sprite;
	}
	
	@Override
	public void update() {

	}

	@Override
	public void render(Renderer r) {
		r.drawSprite(sprite, posX, posY);
	}
	
	public void moveBy(int x, int y) {
		this.posX += x;
		this.posY += y;
	}
	public void setX(int x) {this.posX = x;}
	public void setY(int y) {this.posY = y;}
	
	@Override
	public int getX() {return posX;}
	@Override
	public int getY() {return posY;}
	@Override
	public Sprite getSprite() {return sprite;}
	public int getMaxHealth() {return maxHealth;}
	public int getHealth() {return health;}
	public int getSpeed_X() {return speed_x;}
	public int getSpeed_Y() {return speed_y;}
}
