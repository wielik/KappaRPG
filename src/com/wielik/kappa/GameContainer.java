package com.wielik.kappa;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import com.wielik.kappa.entity.GameObject;
import com.wielik.kappa.entity.Mob;
import com.wielik.kappa.entity.Player;
import com.wielik.kappa.gfx.Image;
import com.wielik.kappa.gfx.Renderer;
import com.wielik.kappa.gfx.Sprite;
import com.wielik.kappa.gfx.SpriteSheet;
import com.wielik.kappa.gfx.Window;
import com.wielik.kappa.input.Input;
import com.wielik.kappa.input.Keyboard;
import com.wielik.kappa.input.Mouse;
import com.wielik.kappa.map.Map;
import com.wielik.kappa.map.Tiles;
import com.wielik.kappa.util.Camera;

public class GameContainer implements Runnable {

	private final String NAME = "Kappa RPG";
	private final int WIDTH = 480;
	private final int HEIGHT = 360;
	private final float SCALE = 2f;
	
	private final double UPDATE_RATE = 60.0; //per second
	private boolean UNLOCKED_FPS = true;
	
	private boolean running;
	private Thread gameThread;
	private Game game;
	private Camera playerCamera;
	
	private Window window;
	private Renderer renderer;
	private Input inputHandler;
	
	private Sprite testPlayerSprite;
	private SpriteSheet testSheet;
	private SpriteSheet playerSheet;
	private Tiles testTiles;
	
	private Player testPlayer;
	private Map testMap;
	
	private ArrayList<GameObject> gameObjects;
	
	public GameContainer(Game game) {
		this.game = game;
	}
	
	public void start() {
		window = new Window(WIDTH, HEIGHT, SCALE, NAME);
		renderer = new Renderer(WIDTH, HEIGHT, window.getImage());
		inputHandler= new Input(window.getCanvas());
		
		gameObjects = new ArrayList<GameObject>();
		
		testSheet = new SpriteSheet("/test_images/test_tiles.png");
		playerSheet = new SpriteSheet("/test_images/test_player.png");
		
		testTiles = new Tiles(testSheet, 32);
		testPlayerSprite = new Sprite(playerSheet, 0, 162, 39, 46);
		
		testMap = new Map(new File("res/test_maps/test_map.txt"), testTiles);
		testPlayer = new Player(inputHandler, testPlayerSprite, 0, 0);
		
		playerCamera = new Camera(0, 0, WIDTH, HEIGHT);
		playerCamera.attach(testPlayer);
		renderer.setCamera(playerCamera);
		gameObjects.add(testPlayer);
		
		if(!running) {
			gameThread = new Thread(this);
			gameThread.start();
			running = true;
		}
		
		game.init();
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
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
		playerCamera.update();
	}
	
	public void render() {
		renderer.clear();
		testMap.render(renderer);
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).render(renderer);
		}
		game.render();
		window.drawScreen();
	}
}
