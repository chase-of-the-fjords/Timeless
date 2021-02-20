package com.chaseplays.game;

import java.util.ArrayList;

import com.chaseplays.engine.Game;
import com.chaseplays.engine.action.Action;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.engine.sound.Sound;
import com.chaseplays.engine.text.Underblocks;
import com.chaseplays.game.levels.Level;
import com.chaseplays.game.player.Player;

public class SmallWorld extends Game {
	
	public static SmallWorld game = new SmallWorld();
	
	public Player player = new Player();
	
	public int time = 0;
	
	public ArrayList<Level> levels = new ArrayList<Level>();
	
	public Level level;
	
	public int currentLevel = 0;
	
	public Sprite[] world = new Sprite[] {new Sprite("/images/world_day.png"), new Sprite("/images/world_night.png")};
	
	public Sound music = new Sound("dimensional_time.wav");
	
	public Sound apple = new Sound("apple.wav");
	
	public Sound goaaal = new Sound("goaaal.wav");
	
	public Sound miss = new Sound("miss.wav");
	
	public Sprite flashScreen = new Sprite(0xFFFFFFFF, 160, 160);
	
	public Action fps = new Action(1000, 0);
	
	int framesPerSecond = 0;
	
	public Action fading = new Action(6, 0);
	public Action flashing = new Action(5, 0);
	
	public Action cannonball = new Action((2500 / 80), 0);
	
	public int distance = 0;
	
	public int fade = 0;
	
	public int flash = 0;
	
	public static final Underblocks font = new Underblocks("/images/underblocks.png", 5, 1, 15);
	
	public boolean[] unlocked = new boolean[15];
	
	public Sprite appletree = new Sprite("/images/world/appletree.png");
	public Sprite berrybush = new Sprite("/images/world/berrybush.png");
	public Sprite pinetree = new Sprite("/images/world/pinetree.png");
	public Sprite leafbush = new Sprite("/images/world/bush.png");
	public Sprite flowers = new Sprite("/images/world/flowers.png");
	public Sprite pig = new Sprite("/images/world/pig.png");
	public Sprite smoltree = new Sprite("/images/world/smoltree.png");
	public Sprite sunflower = new Sprite("/images/world/sunflower.png");
	public Sprite chickens = new Sprite("/images/world/chickens.png");
	public Sprite palmtree = new Sprite("/images/world/palmtree.png");
	public Sprite tallbush = new Sprite("/images/world/tallbush.png");
	public Sprite bigflower = new Sprite("/images/world/bigflower.png");
	public Sprite pond = new Sprite("/images/world/pond.png");
	public Sprite smolbush = new Sprite("/images/world/smolbush.png");
	public Sprite sheep = new Sprite("/images/world/sheep.png");
	
	public Sprite title = new Sprite("/images/logo.png");
	
	public Action wait = new Action(1000, 0);
	
	public static void main(String[] args) {
		
		game.setDimensions(160, 160, 3);
		
		game.setTitle("Timeless | Ludum Dare 38 (Small World)");
		
		game.start();
		
	}
	
	public void setupGame () {
		
		setIcon("/images/icon.png");
		
		Level intro = new Level(50, 77, "/images/levels/introduction/intro", "8602 BC", "8523 BC", 75, 72, 1);
		Level elevation = new Level(101, 77, "/images/levels/elevation/elevation", "7683 BC", "7473 BC", 40, 66, 1); // 93 64
		Level danger = new Level(50, 77, "/images/levels/danger/danger", "7233 BC", "6995 BC", 47, 93, 1); // 50 92
		Level freefall = new Level(102, 77, "/images/levels/freefall/freefall", "6673 BC", "6433 BC", 51, 50, 1); // 52 50
		Level bounce = new Level(37, 85, "/images/levels/bounce/bounce", "6004 BC", "5854 BC", 76, 78, 1);
		Level danger_jump = new Level(117, 85, "/images/levels/danger_jump/dangerjump", "5312 BC", "5004 BC", 83, 65, 0);
		Level cannon = new Level(38, 53, "/images/levels/cannon/cannon", "4774 BC", "4323 BC", 100, 50, 0);
		Level cannon_down = new Level(100, 66, "/images/levels/cannon_down/down", "3999 BC", "3746 BC", 68, 56, 0);
		Level combine = new Level(37, 69, "/images/levels/combine/combine", "3447 BC", "3040 BC", 76, 62, 0);
		Level ladder = new Level(116, 69, "/images/levels/ladder/ladder", "2848 BC", "2543 BC", 53, 46, 0);
		Level corridor = new Level(37, 69, "/images/levels/corridors/corridor", "2312 BC", "2002 BC", 99, 66, 1);
		Level destruction = new Level(116, 85, "/images/levels/destruction/destruction", "1850 BC", "1400 BC", 100, 50, 1);
		Level underground = new Level(54, 53, "/images/levels/underground/underground", "942 BC", "422 BC", 68, 82, 1);
		Level headsup = new Level(37, 101, "/images/levels/headsup/headsup", "213 BC", "1 BC", 115, 98, 0);
		Level finale = new Level(37, 69, "/images/levels/finale/finale", "?", "?", 117, 66, 0);
		
		cannon.addCannon(115, 51, "left", 1, 30);
		
		cannon_down.addCannon(115 - 64, 30, "down", 1);
		cannon_down.addCannon(115 - 56, 30, "down", 1);
		cannon_down.addCannon(115 - 48, 30, "down", 1);
		cannon_down.addCannon(115 - 40, 30, "down", 1);
		cannon_down.addCannon(115 - 32, 30, "down", 1);
		cannon_down.addCannon(115 - 24, 30, "down", 1);
		
		destruction.addCannon(60, 18, "down", 1);
		
		combine.addCannon(59, 110, "up", 1);
		combine.addCannon(59 + 16, 110, "up", 1, 80);
		combine.addCannon(59 + 32, 110, "up", 1);
		
		levels.add(intro);
		levels.add(elevation);
		levels.add(danger);
		levels.add(freefall);
		levels.add(bounce);
		levels.add(danger_jump);
		levels.add(cannon);
		levels.add(cannon_down);
		levels.add(combine);
		levels.add(ladder);
		levels.add(corridor);
		levels.add(destruction);
		levels.add(underground);
		levels.add(headsup);
		levels.add(finale);
		
		currentLevel = 0; // 115 51
		
		level = levels.get(currentLevel);
		
		player.x = level.spawnX;
		player.y = level.spawnY;
		
		fps.start();
		
		cannonball.start();
		
		player.setup(this);
		
		loopSound(music);
		
	}
	
	public void update () {
		
		if (player.y > 6) player.update(this);
		
		if (player.y <= 6) {
			
			if (!wait.active) wait.start();
			
			if (wait.able()) screen.cam.pan(0, 160);
			
		}
		
		level.update(this);
		
		if (cannonball.able()) {
			
			cannonball.gather();
			
			distance++;
			
		}
		
		if (distance == 80) distance = 0;
		
		if (key.x.typed) {
			
			Sprite level2 = SmallWorld.game.level.times[(SmallWorld.game.time + 1) % 2].level;
			
			boolean switchable = true;
			
			for (int w = 0; w < 6; w++) {
				
				for (int h = 0; h < 10; h++) {
					
					if (level2.pixels[(player.x + w) + ((player.y + h - player.jumpHeight) * width)] == 0xFF000000 || level2.pixels[(player.x + w) + ((player.y + h - player.jumpHeight) * width)] == 0xFFFF0000) {
						
						switchable = false;
						
					}
					
				}
				
			}
			
			if (!switchable) {
				
				fading.start();
				
				fade = 100;
				
				playSound(miss);
				
			}
			
			flashing.start();
			
			flash = 50;
			
			if (switchable) time = (time + 1) % 2;
			
		}
		
		if (key.z.typed) {
			
			Sprite level2 = SmallWorld.game.level.times[SmallWorld.game.time].level;
			
			boolean nextLevel = false;
			
			for (int w = 0; w < 6; w++) {
				
				for (int h = 0; h < 10; h++) {
					
					if (level2.pixels[(player.x + w) + ((player.y + h) * width)] == 0xFF00FF00) {
						
						nextLevel = true;
						
					}
					
				}
				
			}
			
			if (nextLevel) {
				
				currentLevel++;
				
				level = levels.get(currentLevel);
				
				flashing.start();
				
				flash = 50;
				
				playSound(goaaal);
				
				distance = 0;
				
			}
			
		}
		
	}
	
	public void render () {
		
		screen.in.render(0, 0, world[time]);
		
		if (unlocked[0]) screen.in.render(0, 0, appletree);
		if (unlocked[1]) screen.in.render(0, 0, berrybush);
		if (unlocked[2]) screen.in.render(0, 0, pinetree);
		if (unlocked[3]) screen.in.render(0, 0, leafbush);
		if (unlocked[4]) screen.in.render(0, 0, flowers);
		if (unlocked[5]) screen.in.render(0, 0, pig);
		if (unlocked[6]) screen.in.render(0, 0, smoltree);
		if (unlocked[7]) screen.in.render(0, 0, sunflower);
		if (unlocked[8]) screen.in.render(0, 0, chickens);
		if (unlocked[9]) screen.in.render(0, 0, palmtree);
		if (unlocked[10]) screen.in.render(0, 0, tallbush);
		if (unlocked[11]) screen.in.render(0, 0, bigflower);
		if (unlocked[12]) screen.in.render(0, 0, pond);
		if (unlocked[13]) screen.in.render(0, 0, smolbush);
		if (unlocked[14]) screen.in.render(0, 0, sheep);
		
		level.render(this);
		
		if (fading.able()) {
			
			if (fade != 0) {
				
				fade--;
				
			} else if (fade == 0) {
				
				fading.stop();
				
			}
			
			fading.gather();
			
		}
		
		if (flashing.able()) {
			
			if (flash != 0) {
				
				flash--;
				
			} else if (flash == 0) {
				
				flashing.stop();
				
			}
			
			flashing.gather();
			
		}
		
		if (fading.active) {
			
			screen.in.render(0, 0, SmallWorld.game.level.times[(SmallWorld.game.time + 1) % 2].visible.tinted(0xFFFF0000, 0.6).opacity(((double) fade) / 100.0));
			if (level.appleTime != this.time && unlocked[currentLevel] == false) screen.in.render(level.appleX, level.appleY, Level.apple.tinted(0xFFFF0000, 0.6).opacity(((double) fade) / 100.0));
			
		}
		
		player.render(this);
		
		if (flashing.active) {
			
			screen.in.render(0, 0, flashScreen.opacity(((double) flash) / 100.0));
			
		}
		
		screen.in.renderText(level.dates[time]);
		
		if (player.y <= 6) {
			
			screen.in.render(0, 160, title);
			
		}
		
	}
	
}
