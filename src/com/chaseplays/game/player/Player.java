package com.chaseplays.game.player;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.action.Action;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.game.Cannonball;
import com.chaseplays.game.SmallWorld;
import com.chaseplays.game.levels.Level;

public class Player {
	
	public int x = 50;
	public int y = 77;
	
	public int jumpHeight = 0;
	
	public int xOffset = -8, yOffset = -4;
	
	public int appleX, appleY;
	
	public int jump = 0;
	
	public Sprite sprite = new Sprite("/images/character/player_right.png");
	
	public Sprite lookRight = new Sprite("/images/character/player_right.png");
	public Sprite lookLeft = new Sprite("/images/character/player_left.png");
	
	
	
	public Action fall = new Action(40, 0);
	
	public Action move = new Action(24, 0);
	
	public Action jumping = new Action(25, 0);
	
	public Action turbojump = new Action(10, 0);
	
	public int turboLeft = 4;
	
	public boolean falling = false;
	
	public int fallRate = 0;
	
	public boolean justJumped = false;
	
	public Player () {
		
	}
	
	public void setup (Engine e) {
		
		move.start();
		fall.start();
		
	}
	
	public void update (Engine e) {
		
		Sprite level = SmallWorld.game.level.times[SmallWorld.game.time].level;
		
		if (touching(e, 0xFF00FF55) && !turbojump.active) {
			
			turbojump.start();
			
			turboLeft = 8;
			
			jumping.stop();
			
			jump = 0;
			
		}
		
		if (touching(e, 0xFF0000FF) && !turbojump.active) {
			
			turbojump.start();
			
			turboLeft = 33;
			
			jumping.stop();
			
			jump = 0;
			
		}
		
		if (turbojump.able() && turboLeft != 0) {
			
			if (headRoom(e)) {
			
				jumpHeight++;
				
				turboLeft--;
				
				turbojump.gather();
			
			} else {
				
				turboLeft = 0;
				
			}
			
		} else if (turboLeft == 0 && turbojump.active && headRoom(e)) {
			
			turbojump.stop();
			
			jumping.start();
			
			y-=jumpHeight;
			
			jumpHeight = 0;
			
			jump = 0;
			
		} else if (turboLeft == 0 && turbojump.active && !headRoom(e)) {
			
			turbojump.stop();
			
			y-=jumpHeight;
			
			jumpHeight = 0;
			
		}
		
		if (move.able()) {
			
			if (e.key.right.pressed) {
				
				boolean moveable = true;
				
				for (int h = 0; h < 10; h++) {
					
					if (level.pixels[(x + 6) + ((y + h - jumpHeight) * 160)] == 0xFF000000) moveable = false;
					
				}
				
				if (moveable) x++;
				
				sprite = lookRight;
				
			}
			
			if (e.key.left.pressed) {
				
				boolean moveable = true;
				
				for (int h = 0; h < 10; h++) {
					
					if (level.pixels[(x - 1) + ((y + h - jumpHeight) * 160)] == 0xFF000000) moveable = false;
					
				}
				
				if (moveable) x--;
				
				sprite = lookLeft;
				
			}
			
			if (e.key.up.pressed) {
				
				if (touching(e, 0xFFFFFF00) && headRoom(e)) {
					
					y--;
					
				}
				else if (grounded(e)) {
					
					if (!jumping.active) {
						
						jumping.start();
						
						jumpHeight = 0;
						
						jump = 0;
						
					}
					
				}
				
			}
			
			if (e.key.down.pressed) {
				
				Sprite level2 = SmallWorld.game.level.times[SmallWorld.game.time].level;
				
				boolean nextLevel = false;
				boolean solidGround = false;
				
				for (int w = 0; w < 6; w++) {
					
					for (int h = 0; h < 11; h++) {
						
						if (h == 10 && level2.pixels[(x + w) + ((y + h) * e.width)] == 0xFF000000) {
							
							solidGround = true;
							
						}
						
						if (level2.pixels[(x + w) + ((y + h - jumpHeight) * e.width)] == 0xFFFFFF00) {
							
							nextLevel = true;
							
						}
						
					}
					
				}
				
				if (nextLevel && !solidGround) y++;
				
			}
			
			move.gather();
			
		}
		
		if (jumping.able()) {
			
			if (!headRoom(e) && jump <= 5) {
				System.out.println("GO" + jump);
				
				jump = 6 + ((6 - (jump - 1)) % 6);
			}
			if (grounded(e) && jump != 0) {
				
				y-=jumpHeight;
				
				jumpHeight = 0;
				
				jump = 0;
				
				jumping.stop();
				
			}
			
			jumping:
			
			switch (jump) {
				
				case 0: jumpHeight = 1;
				break jumping;
						
				case 1: jumpHeight = 2;
				break jumping;
				
				case 2: jumpHeight = 3;
				break jumping;
				
				case 3: jumpHeight = 4;
				break jumping;
				
				case 4: jumpHeight = 4;
				break jumping;
				
				case 5: jumpHeight = 5;
				break jumping;
				
				case 6: jumpHeight = 5;
				break jumping;
				
				case 7: jumpHeight = 5;
				break jumping;
				
				case 8: jumpHeight = 4;
				break jumping;
				
				case 9: jumpHeight = 4;
				break jumping;
				
				case 10: jumpHeight = 3;
				break jumping;
				
				case 11: jumpHeight = 2;
				break jumping;
				
				case 12: jumpHeight = 1;
				break jumping;
				
				case 13: jumpHeight = 0;
				jumping.stop();
				fall = new Action(25, 0);
				fall.start();
				justJumped = true;
				break jumping;	
				
				default: System.out.println("ERROR");
				
			}
			
			jump++;
			
			jumping.gather();
			
		} else if (!jumping.active) {
			
			justJumped = false;
			
		}
		
		if (grounded(e)) falling = false;
		
		if (fall.able()) {
			
			boolean solidGround = grounded(e);
			
			if (!solidGround && !jumping.active && !justJumped && !turbojump.active) {
				
				y++;
				
				if (falling && fall.updateEvery == 40) fall.updateEvery = 35;
				else if (falling && fall.updateEvery == 35) fall.updateEvery = 30;
				else if (falling && fall.updateEvery == 30) fall.updateEvery = 25;
				else if (falling && fall.updateEvery == 25) fall.updateEvery = 20;
				else if (falling && fall.updateEvery == 20) fall.updateEvery = 15;
				else if (falling && fall.updateEvery == 15) fall.updateEvery = 10;
				
				falling = true;
				
			} else if (solidGround && jumpHeight > 0) {
				
				falling = false;
				
				jumping.stop();
				
				y -= jumpHeight;
				
				jumpHeight = 0;
				
			} else if (!solidGround) {
				
				falling = false;
				
				fall = new Action(40, 0);
				fall.start();
				
			} else if (solidGround) {
				
				fall.updateEvery = 40;
				
				falling = false;
				
			}
			
			fall.gather();
			
		}
		
		if (touching(e, 0xFFFF0000) || e.key.r.typed) {
			
			this.x = SmallWorld.game.levels.get(SmallWorld.game.currentLevel).spawnX;
			this.y = SmallWorld.game.levels.get(SmallWorld.game.currentLevel).spawnY;
			
			SmallWorld.game.time = 0;
			
			SmallWorld.game.flashing.start();
			
			SmallWorld.game.flash = 50;
			
			SmallWorld.game.distance = 0;
			
			SmallWorld.game.unlocked[SmallWorld.game.currentLevel] = false;
			
		}
		
		if (SmallWorld.game.level.cannons.size() > 0) {
			
			for (int s = 0; s < SmallWorld.game.level.cannons.size(); s++) {
				
				for (int c = 0; c < SmallWorld.game.level.cannons.get(s).cannonballs.size(); c++) {
					
					if (touching(SmallWorld.game.level.cannons.get(s).cannonballs.get(c))) {
						
						this.x = SmallWorld.game.levels.get(SmallWorld.game.currentLevel).spawnX;
						this.y = SmallWorld.game.levels.get(SmallWorld.game.currentLevel).spawnY;
						
						SmallWorld.game.time = 0;
						
						SmallWorld.game.flashing.start();
						
						SmallWorld.game.flash = 50;
						
						SmallWorld.game.distance = 0;
						
						SmallWorld.game.unlocked[SmallWorld.game.currentLevel] = false;
						
					}
					
				}
				
			}
			
		}
		
		if (touching(SmallWorld.game.level)) {
			
			if (SmallWorld.game.unlocked[SmallWorld.game.currentLevel] == false) SmallWorld.game.playSound(SmallWorld.game.apple);
			
			SmallWorld.game.unlocked[SmallWorld.game.currentLevel] = true;
			
		}
		
	}
	
	public void render (Engine e) {
		
		e.screen.in.render(x + xOffset, y + yOffset - jumpHeight, sprite);
		
	}
	
	public boolean grounded (Engine e) {
		
		Sprite level2 = SmallWorld.game.level.times[SmallWorld.game.time].level;
		
		boolean grounded = false;
		
		for (int w = 0; w < 6; w++) {
			
			if (level2.pixels[(x + w) + ((y + 10 - jumpHeight) * e.width)] == 0xFF000000 || level2.pixels[(x + w) + ((y + 10 - jumpHeight) * e.width)] == 0xFFFFFF00) {
				
				grounded = true;
				
			}
			
		}
		
		return grounded;
		
	}
	
	public boolean headRoom (Engine e) {
		
		Sprite level2 = SmallWorld.game.level.times[SmallWorld.game.time].level;
		
		boolean grounded = true;
		
		for (int w = 0; w < 6; w++) {
			
			if (level2.pixels[(x + w) + ((y - jumpHeight - 1) * e.width)] == 0xFF000000) {
				
				grounded = false;
				
			}
			
		}
		
		return grounded;
		
	}
	
	public boolean touching (Cannonball c) {
		
		if (this.y - jumpHeight > c.y + 3 || this.y - jumpHeight + 10 < c.y) return false;
		if (this.x > c.x + 3 || this.x + 6 < c.x) return false;
		
		return true;
		
	}
	
	public boolean touching (Level apple) {
		
		if (SmallWorld.game.time != SmallWorld.game.level.appleTime) return false;
		
		if (this.y - jumpHeight > apple.appleY + 12 || this.y - jumpHeight + 10 < apple.appleY) return false;
		if (this.x > apple.appleX + 9 || this.x + 6 < apple.appleX) return false;
		
		return true;
		
	}
	
	public boolean touching (Engine e, int color) {
		
		Sprite level2 = SmallWorld.game.level.times[SmallWorld.game.time].level;
		
		boolean touching = false;
		
		for (int w = 0; w < 6; w++) {
			
			for (int h = 0; h < 10; h++) {
				
				if (level2.pixels[(x + w) + ((y + h - jumpHeight) * e.width)] == color) {
					
					touching = true;
					
				}
				
			}
			
		}
		
		return touching;
		
	}
	
}
