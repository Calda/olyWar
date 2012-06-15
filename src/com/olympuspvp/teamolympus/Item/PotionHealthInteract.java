

package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.type.ClassType;

public class PotionHealthInteract {

	@SuppressWarnings({ "deprecation", "unused" })
	public static void run(Player p){

		//get class type of player
		ClassType ct = null;
		int max;
		if(ct == null){
			max = 20;
		}else{
			max = ct.getMaxHealth();
		}
		p.setHealth(max);
		p.setItemInHand(null);
		p.updateInventory();

	}

}
