package com.olympuspvp.teamolympus.game;

import org.bukkit.ChatColor;

public enum Team{
	RED(ChatColor.RED + "Red", ChatColor.RED),
	BLUE(ChatColor.BLUE + "Blue", ChatColor.BLUE),
	NONE(ChatColor.GRAY + "Spectator", ChatColor.GRAY);

	String name;
	ChatColor cc;
	Team(final String n, final ChatColor chatc){
		this.name = n;
		this.cc = chatc;
	}

	public String getName(){
		return this.name;
	}
	
	public String getTeamName(){
		return getColor() + "Team " + getName();
	}

	public ChatColor getColor(){
		return this.cc;
	}

	public Team getOpposite(){
		if(this == Team.RED) return Team.BLUE;
		else if(this == Team.BLUE) return Team.RED;
		else return Team.NONE;
	}
}
