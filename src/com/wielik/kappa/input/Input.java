package com.wielik.kappa.input;

import java.awt.Component;

public class Input {

	/*
	 	KeyCodes:
	 	8 - Backspace
	 	13- Enter
	 	16 - Shift
	 	17 - Ctrl
		18 - Alt
		37 - LeftArrow
		38 - UpArrow
		39 - RightArrow
	 	40 - DownArrow
	 	65 - a
	 	68 - d
	 	87 - w
	 	83 - s
	*/
	private Keyboard keyboardHandler;
	private Mouse mouseHandler;
	
	public Input(Component comp) {
		keyboardHandler = new Keyboard(comp);
		mouseHandler = new Mouse(comp);
	}

	public Keyboard getKeyboard() {return keyboardHandler;}
	public Mouse getMouse() {return mouseHandler;}
}
