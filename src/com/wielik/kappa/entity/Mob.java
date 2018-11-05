package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;

public class Mob implements GameObject{

	protected int maxHealth;
	protected int health;
	protected int posX;
	protected int posY;
	protected int speed;
	protected Sprite sprite;
	
	protected int updateCounter = 0;
	
	protected boolean moving;
	
	public Mob(Sprite sprite) {
		this(sprite, 0 , 0);
	}
	
	public Mob(Sprite sprite, int posX, int posY) {
		this(sprite, posX, posY, 2, 100);
	}
	
	public Mob(Sprite sprite, int posX, int posY, int speed, int maxHealth) {
		this.sprite = sprite;
		this.posX = posX;
		this.posY = posY;
		this.speed= speed;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
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
	public int getSpeed() {return speed;}
}
