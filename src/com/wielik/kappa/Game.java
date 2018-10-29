package com.wielik.kappa;

public class Game {
	
	GameContainer gc;
	
	public void update() {
		
	}
	
	public void render() {

	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Game());
		gc.start();
	}
}
