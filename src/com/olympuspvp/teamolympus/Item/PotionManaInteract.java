package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Player;

public class PotionManaInteract {

	@SuppressWarnings("deprecation")
	public static void run(final Player p){

		if(p.getFoodLevel() != 20){
			p.setFoodLevel(20);
			p.setItemInHand(null);
			p.updateInventory();
		}

	}

}
