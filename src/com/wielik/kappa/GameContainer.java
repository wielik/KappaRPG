package com.wielik.kappa;

public class GameContainer implements Runnable {

	private boolean running;
	private Thread gameThread;
	private Game game;
	
	public GameContainer(Game game) {
		this.game = game;
	}
	
	public void start() {
		if(!running) {
			gameThread = new Thread(this);
			gameThread.start();
			running = true;
		}
	}
		
	public void stop() {
		if(running) {
			running = false;
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	@Override
	public void run() {
		while(running) {
			System.out.println("Running");
		}
	}

	public void update() {
		game.update();
	}
	
	public void render() {
		game.render();
	}
}
