package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Player;

public class PotionManaInteract {

	@SuppressWarnings("deprecation")
	public static void run(Player p){
		
		p.setFoodLevel(20);
		p.setItemInHand(null);
		p.updateInventory();
		
	}
	
}
