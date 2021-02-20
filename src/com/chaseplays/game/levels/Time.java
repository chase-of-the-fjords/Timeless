package com.chaseplays.game.levels;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;

public class Time {
	
	public Sprite level;
	public Sprite visible;
	
	public Time(String level, String visible) {
		
		this.level = new Sprite(level);
		
		this.visible = new Sprite(visible);
		
	}
	
	public void render (Engine e) {
		
		e.screen.in.render(0, 0, visible);
		
	}
	
}
