package com.olympuspvp.teamolympus.Item;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.damage.DamageListener;

public class StaffLightningInteract {

	private static Block tb;

	public static void run(final Player p){
		tb = p.getTargetBlock(null,100);
		final Location loc = tb.getLocation();
		final LightningStrike l = p.getWorld().strikeLightning(loc);
		final List<Entity> nearby = l.getNearbyEntities(5, 5, 5);
		for(final Entity e : nearby){
			if(!DamageListener.onSameTeam(p, e)){
				if(e instanceof LivingEntity) ((LivingEntity)e).damage(5, p);
			}
		}

	}
}
