package com.wielik.kappa.input;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseWheelListener, MouseMotionListener{

	private static final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private int mouseX, mouseY;
	private int scroll;
	
	
	public Mouse(Component comp) {
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.addMouseWheelListener(this);
		
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() < NUM_BUTTONS) buttons[e.getButton()] = true;
		else System.out.println("Mouse button " + e.getButton() + " not supported. Out of range of " + NUM_BUTTONS);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() < NUM_BUTTONS) buttons[e.getButton()] = true;
		else System.out.println("Mouse button " + e.getButton() + " not supported. Out of range of " + NUM_BUTTONS);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() < NUM_BUTTONS) buttons[e.getButton()] = false;
		else System.out.println("Mouse button " + e.getButton() + " not supported. Out of range of " + NUM_BUTTONS);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	


}
