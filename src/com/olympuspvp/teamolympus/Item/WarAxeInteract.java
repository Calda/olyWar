package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WarAxeInteract {

	public static void run(LivingEntity e, Player damager){
		for(Entity ent : e.getNearbyEntities(3,3,3)){
			if(ent instanceof Player){
				if(damager.canSee((Player)ent) && !((Player)ent).equals(damager)){
					((Player)ent).damage(3);
				}
			}else if(ent instanceof LivingEntity){
				((LivingEntity)ent).damage(3);
			}
		}e.damage(3);
	}
}
