package com.chaseplays.game.levels;

import java.util.ArrayList;

import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.engine.text.Text;
import com.chaseplays.game.Cannon;
import com.chaseplays.game.SmallWorld;

public class Level {
	
	public int spawnX, spawnY;
	
	public Time[] times = new Time[2];
	public Text[] dates = new Text[2];
	
	public ArrayList<Cannon> cannons = new ArrayList<Cannon>();
	
	public int appleX, appleY;
	
	public int appleTime;
	
	public static Sprite apple = new Sprite("/images/apple.png");
	
	public Level (int x, int y, String dir, String past_date, String future_date, int appleX, int appleY, int appleTime) {
		
		this.spawnX = x;
		this.spawnY = y;
		
		this.appleX = appleX;
		this.appleY = appleY;
		
		this.appleTime = appleTime;
		
		times[1] = new Time(dir + "_past_level.png", dir + "_past_visible.png");
		times[0] = new Time(dir + "_future_level.png", dir + "_future_visible.png");
		
		dates[1] = new Text(80, 149, past_date, SmallWorld.font, 0xFF663931);
		dates[1].setDirection("center");
		dates[0] = new Text(80, 149, future_date, SmallWorld.font, 0xFF663931);
		dates[0].setDirection("center");
		
	}
	
	public void addCannon (Cannon c) {
		
		this.cannons.add(c);
		
	}
	
	public void addCannon (int x, int y, String direction, int cannonballs) {
		
		this.cannons.add(new Cannon(x, y, direction, cannonballs));
		
	}
	
	public void addCannon (int x, int y, String direction, int cannonballs, int interval) {
		
		this.cannons.add(new Cannon(x, y, direction, cannonballs, interval));
		
	}
	
	public void update (SmallWorld e) {
		
		if (cannons.size() > 0) for (int s = 0; s < cannons.size(); s++) cannons.get(s).update(e);
		
	}
	
	public void render (SmallWorld e) {
		
		e.screen.on.render(0, 0, times[SmallWorld.game.time].visible.opacity((100.0 - (double) SmallWorld.game.fade) / 100));
		
		if (cannons.size() > 0) for (int s = 0; s < cannons.size(); s++) cannons.get(s).render(e);
		
		if (e.time == this.appleTime && e.unlocked[e.currentLevel] == false) e.screen.in.render(appleX, appleY, apple);
		
	}
	
}
