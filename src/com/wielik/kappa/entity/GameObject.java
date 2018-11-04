package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;

public interface GameObject {

	public void update();
	public void render(Renderer r);
	
	public int getX();
	public int getY();
	public Sprite getSprite();
}
