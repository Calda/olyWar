package com.olympuspvp.teamolympus.game;

import org.bukkit.ChatColor;

public enum Team{
	RED(ChatColor.RED + "Red", ChatColor.DARK_RED, (byte) 14), 
	BLUE(ChatColor.BLUE + "Blue", ChatColor.DARK_RED, (byte) 11),
	NONE(ChatColor.GRAY + "Spectator", ChatColor.GRAY, (byte) 0);

	String name;
	ChatColor cc;
	byte woolColor;
	Team(final String n, final ChatColor chatc, byte woolColor){
		this.name = n;
		this.cc = chatc;
		this.woolColor = woolColor;
	}

	public String getName(){
		return this.name;
	}

	public ChatColor getColor(){
		return this.cc;
	}
	
	public byte getWoolData(){
		return this.woolColor;
	}

	public Team getOpposite(){
		if(this == Team.RED) return Team.BLUE;
		else if(this == Team.BLUE) return Team.RED;
		else return Team.NONE;
	}
}
