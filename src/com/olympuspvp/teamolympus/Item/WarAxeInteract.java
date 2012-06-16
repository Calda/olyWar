package com.olympuspvp.teamolympus.Item;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WarAxeInteract {

	public static void run(LivingEntity e, Player p){
		e.damage(3, p);
		List<Entity> nearby = e.getNearbyEntities(2, 2, 2);
		for(Entity ent : nearby){
			if(ent instanceof Player){
				Player damage = (Player) ent;
				if(damage.equals(p)){
				}else{
					damage.damage(3, p);
				}
			}
		}
	}
}
