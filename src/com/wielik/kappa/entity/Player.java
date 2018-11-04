package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;

public class Player extends Mob {

	public Player(int posX, int posY, Sprite sprite) {
		super(posX, posY, sprite);
	}
	
	@Override
	public void render(Renderer r) {
		r.drawSprite(sprite, posX, posY);
	}

}
