package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;
import com.wielik.kappa.input.Input;

public class Player extends Mob {

	private Input inputHandler;
	
	public Player(Input inputHandler, Sprite sprite, int posX, int posY) {
		super(sprite, posX, posY);
		this.inputHandler = inputHandler;
	}
	
	public Player(Input inputHandler, Sprite sprite, int posX, int posY, int speed, int maxHealth) {
		super(sprite, posX, posY, speed, maxHealth);
		this.inputHandler = inputHandler;
	}
	
	@Override
	public void update() {
		if(inputHandler.getKeyboard().isKeyDown(87)) moveBy(0, -speed);
		if(inputHandler.getKeyboard().isKeyDown(83)) moveBy(0, speed);
		if(inputHandler.getKeyboard().isKeyDown(65)) moveBy(-speed, 0);
		if(inputHandler.getKeyboard().isKeyDown(68)) moveBy(speed, 0);
	}
	
	@Override
	public void render(Renderer r) {
		r.drawSprite(sprite, posX, posY);
	}

}
