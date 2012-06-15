package com.olympuspvp.teamolympus.Item;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class StaffLightningInteract {

	private static Block tb;
	
	public static void run(Player p){
		tb = p.getTargetBlock(null,100);
		Location loc = tb.getLocation();
		LightningStrike l = p.getWorld().strikeLightning(loc);
		List<Entity> nearby = l.getNearbyEntities(5, 5, 5);
		for(Entity e2 : nearby){
			if(e2 instanceof LivingEntity){
				LivingEntity le = (LivingEntity) e2;
				le.damage(8, p);
			}
		}
		
	}
}
