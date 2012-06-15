package com.olympuspvp.teamolympus.Item;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WarAxeInteract {

	public static void run(Player p){
		
		List<Entity> nearby = p.getNearbyEntities(2, 2, 2);
		for(Entity ent : nearby){
			if(ent instanceof Player){
				Player damage = (Player) ent;
				damage.damage(3, p);
			}
		}
		
	}
	
}
