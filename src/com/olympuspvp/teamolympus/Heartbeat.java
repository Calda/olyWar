package com.olympuspvp.teamolympus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Heartbeat {

	public void addMana(){
		Player[] players = Bukkit.getOnlinePlayers();
		if(players.length > 0){
			for(Player p : players){
					int currentMana = p.getFoodLevel();
					int newMana = currentMana++;
					p.setFoodLevel(newMana);
					System.out.println(p.getName() + "'s mana has been restored");
			}
		}
	}
}