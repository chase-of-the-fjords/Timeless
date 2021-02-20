package com.chaseplays.game;

import java.util.ArrayList;

import com.chaseplays.engine.screen.Sprite;

public class Cannon {
	
	int x, y;
	
	int interval = 0;
	
	public String direction = "right";
	
	public ArrayList<Cannonball> cannonballs = new ArrayList<Cannonball>();
	
	public Sprite right = new Sprite("/images/character/cannon_right.png");
	public Sprite left = new Sprite("/images/character/cannon_left.png");
	public Sprite up = new Sprite("/images/character/cannon_up.png");
	public Sprite down = new Sprite("/images/character/cannon_down.png");
	
	public Cannon (int x, int y, String direction, int cannonballs) {
		
		this.direction = direction;
		
		this.x = x;
		this.y = y;
		
		for (int s = 0; s < cannonballs; s++) this.cannonballs.add(new Cannonball());
		
	}
	
	public Cannon (int x, int y, String direction, int cannonballs, int interval) {
		
		this.direction = direction;
		
		this.interval = interval;
		
		this.x = x;
		this.y = y;
		
		for (int s = 0; s < cannonballs; s++) this.cannonballs.add(new Cannonball());
		
	}
	
	public void update (SmallWorld e) {
		
		for (int s = 0; s < cannonballs.size(); s++) {
			
			if (direction.equalsIgnoreCase("right")) {
				
				cannonballs.get(s).x = ((this.x + 3 + ((interval + ((40 * e.time) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
				
				cannonballs.get(s).y = this.y + 3;
				
			}
			if (direction.equalsIgnoreCase("left")) {
				
				cannonballs.get(s).x = ((this.x + 3 - ((interval + ((40 * e.time) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
				
				cannonballs.get(s).y = this.y + 3;
				
			}
			if (direction.equalsIgnoreCase("up")) {
				
				cannonballs.get(s).y = ((this.y + 3 - ((interval + ((40 * e.time) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
				
				cannonballs.get(s).x = this.x + 3;
				
			}
			if (direction.equalsIgnoreCase("down")) {
				
				cannonballs.get(s).y = ((this.y + 3 + ((interval + ((40 * e.time) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
				
				cannonballs.get(s).x = this.x + 3;
				
			}
			
		}
		
	}
	
	public void render (SmallWorld e) {
		
		for (int s = 0; s < cannonballs.size(); s++) cannonballs.get(s).render(e);
		
		if (direction.equalsIgnoreCase("right")) {
			
			e.screen.in.render(x, y, right);
			
			for (int s = 0; s < cannonballs.size(); s++) {
				
				cannonballs.get(s).x = ((this.x + 3 + ((interval + ((40 * ((e.time + 1) % 2)) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
				
			}
			
		}
		if (direction.equalsIgnoreCase("left")) {
			
			e.screen.in.render(x, y, left);
			
			for (int s = 0; s < cannonballs.size(); s++) cannonballs.get(s).x = ((this.x + 3 - ((interval + ((40 * ((e.time + 1) % 2)) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
			
		}
		if (direction.equalsIgnoreCase("up")) {
			
			e.screen.in.render(x, y, up);
			
			for (int s = 0; s < cannonballs.size(); s++) cannonballs.get(s).y = ((this.y + 3 - ((interval + ((40 * ((e.time + 1) % 2)) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
			
		}
		if (direction.equalsIgnoreCase("down")) {
			
			e.screen.in.render(x, y, down);
			
			for (int s = 0; s < cannonballs.size(); s++) cannonballs.get(s).y = ((this.y + 3 + ((interval + ((40 * ((e.time + 1) % 2)) / cannonballs.size()) + e.distance + ((80 / cannonballs.size()) * s))) % 80));
			
		}
		
		for (int s = 0; s < cannonballs.size(); s++) cannonballs.get(s).renderFaded(e);
		
		update(e);
		
	}
	
}
