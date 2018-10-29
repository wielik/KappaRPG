package com.wielik.kappa;

import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Window;

public class GameContainer implements Runnable {

	private final String NAME = "Kappa RPG";
	private final int WIDTH = 480;
	private final int HEIGHT = 360;
	private final float SCALE = 2f;
	
	private final double UPDATE_RATE = 60.0; //per second
	private boolean UNLOCKED_FPS = false;
	
	private boolean running;
	private Thread gameThread;
	private Game game;
	
	private Window window;
	private Renderer renderer;
	
	public GameContainer(Game game) {
		this.game = game;
	}
	
	public void start() {
		window = new Window(WIDTH, HEIGHT, SCALE, NAME);
		renderer = new Renderer(WIDTH, HEIGHT, window.getImage());
		
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
		double lastTime = System.nanoTime();
		double nowTime = 0;
		double unprocessedTime = 0;
		double counterTime = 0;
		double passedTime = 0;
		int frames = 0;
		int updates = 0;
		boolean shouldRender = false;
		
		while(running) {
			nowTime = System.nanoTime();
			passedTime = (nowTime - lastTime)/1000000000.0;
			lastTime = nowTime;
			unprocessedTime += passedTime;
			counterTime += passedTime;
			
			if(unprocessedTime >= (1/UPDATE_RATE)) {
				unprocessedTime -= unprocessedTime;
				update();
				updates++;
				shouldRender = true;
			}
			if(shouldRender || UNLOCKED_FPS) {
				render();
				frames++;
				shouldRender = false;
			}
			if(counterTime >= 1) {
				System.out.println("FPS: " + frames + " UPS: " + updates);
				frames = 0;
				updates = 0;
				counterTime = 0;
			}
		}
	}

	public void update() {
		game.update();
	}
	
	public void render() {
		game.render();
		window.drawScreen();
		renderer.clear();
	}
}
