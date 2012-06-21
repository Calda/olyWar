package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;

public class StaffBlinkInteract{

	public static void run(final Player p, final olyWar ow){

		if(ConsumeMana.consumeMana(p, ItemType.STAFF_BLINK.getManaUsage())){
			p.launchProjectile(EnderPearl.class);
		}
	}
}
