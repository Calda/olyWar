

package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.type.ClassType;

public class PotionHealthInteract {

	@SuppressWarnings({"deprecation"})
	public static void run(final Player p){

		//get class type of player
		final ClassType ct = olyWar.getClass(p);
		int max;
		if(ct != null) max = ct.getMaxHealth();
		else max = 20;

		p.setHealth(max);
		p.setItemInHand(null);
		p.updateInventory();

	}

}
