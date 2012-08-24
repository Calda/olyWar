package com.olympuspvp.teamolympus.game;

import org.bukkit.ChatColor;


public enum TeamPref{
	RED(ChatColor.RED + "Red"), BLUE(ChatColor.BLUE + "Blue"), RANDOM( ChatColor.GRAY + "Random");

	String name;

	TeamPref(final String n){
		this.name = n;
	}

	public String getName(){
		return this.name;
	}

}
