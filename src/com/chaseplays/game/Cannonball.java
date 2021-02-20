package com.chaseplays.game;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;

public class Cannonball {
	
	public int x, y;
	
	public Sprite sprite = new Sprite("/images/character/cannonball.png");
	
	public void render (Engine e) {
		
		e.screen.in.render(x, y, sprite);
		
	}
	
	public void renderFaded (Engine e) {
		
		e.screen.in.render(x, y, sprite.opacity(0.2));
		
	}
	
}
