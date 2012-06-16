package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class WarAxeInteract {

	public static void run(LivingEntity e, LivingEntity damager){
		for(Entity ent : e.getNearbyEntities(2,2,2)){
			if(ent instanceof LivingEntity){
				if(!ent.equals(damager)){
					e.damage(3);
				}
			}
		}e.damage(3);
	}
}
