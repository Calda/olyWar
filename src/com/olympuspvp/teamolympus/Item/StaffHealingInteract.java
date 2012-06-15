package com.olympuspvp.teamolympus.Item;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class StaffHealingInteract {

	public static void run(Player p){
		int playerHealth = p.getHealth();
		playerHealth += 6;
		if(playerHealth > 20){
			playerHealth = 20;
		}
		p.setHealth(playerHealth);
		List<Entity> nearby = p.getNearbyEntities(10, 10, 10);
		for(Entity ent : nearby){
			if(ent instanceof Player){
				Player heal = (Player) ent;
				int health = heal.getHealth();
				//GetCurrentClass
				//check to make sure health doesn't go over max
				health += 6;
				if(health > 20){
					health = 20;
				}
				heal.setHealth(health);
			}
		}
		
	}
	
}
