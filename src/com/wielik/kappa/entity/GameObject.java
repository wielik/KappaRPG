package com.wielik.kappa.entity;

import com.wielik.kappa.gfx.Renderer;

public interface GameObject {

	public void update();
	public void render(Renderer r);
	
	public int getX();
	public int getY();
}
