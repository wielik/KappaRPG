package com.wielik.kappa.input;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, FocusListener {

	public Keyboard(Component comp) {
		comp.addKeyListener(this);
		comp.addFocusListener(this);
	}
	
	private static final int NUM_KEYS = 256;
	private boolean[] keysDown = new boolean[NUM_KEYS];			//keys pressed on this frame
	private boolean[] keysDown_last = new boolean[NUM_KEYS];	//keys pressed on last frame

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < NUM_KEYS) keysDown[e.getKeyCode()] = true;
		else System.err.println("Key code pressed is out of range of " + NUM_KEYS);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < NUM_KEYS) keysDown[e.getKeyCode()] = false;
		else System.err.println("Key code released is out of range of " + NUM_KEYS);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		for(int i = 0; i < NUM_KEYS; i++) keysDown_last[i] = keysDown[i] = false;
	}
	
	@Override
	public void focusGained(FocusEvent e) {}
	
	private void update() {
		for(int i = 0; i < NUM_KEYS; i++) keysDown_last[i] = keysDown[i];
	}
	
	public boolean isKeyDown(int keyCode) {
		return keysDown[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keysDown[keyCode] && keysDown_last[keyCode];
	}
	
	public boolean isKeyTapped(int keyCode) {
		return keysDown[keyCode] && !keysDown_last[keyCode];
	}

}
