package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class WarAxeInteract{

	public static void run(final LivingEntity e, final Player damager){
		for(final Entity ent : e.getNearbyEntities(4, 4, 4)){
			if(ent instanceof LivingEntity){
				if(ent instanceof Player){
					final Player p = (Player) ent;
					if(!p.equals(damager)){
						Team t1 = olyWar.getTeam(damager);
						Team t2 = olyWar.getTeam(p);
						if(t1 != t2) p.damage(3);
					}
				}else ((LivingEntity) ent).damage(3);
			}
		}
		e.damage(3);
	}
}
