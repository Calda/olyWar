package com.olympuspvp.teamolympus.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class NoSprint{

	public static void check(){
		for(final Player p : Bukkit.getServer().getOnlinePlayers()){
			if(p.isSprinting()) p.setSprinting(false);
		}
	}

}
