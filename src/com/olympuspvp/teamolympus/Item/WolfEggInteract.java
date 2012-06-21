package com.olympuspvp.teamolympus.Item;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class WolfEggInteract {

	@SuppressWarnings("deprecation")
	public static void run(final Player p){
		final Location spawn = p.getTargetBlock(null, 2).getLocation();
		spawn.setY(spawn.getY() + 1);
		final Wolf w = (Wolf) p.getWorld().spawnCreature(spawn, EntityType.WOLF);
		w.setOwner(p);
		final Random r = new Random();
		if(r.nextInt(10) == 3){
			w.setBaby();
		}
		p.setItemInHand(null);
		p.updateInventory();

	}

}
