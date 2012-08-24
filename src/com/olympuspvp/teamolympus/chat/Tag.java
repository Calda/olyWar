package com.olympuspvp.teamolympus.chat;

import org.bukkit.ChatColor;

public enum Tag {
	
	NOOB(ChatColor.GRAY + "NOOB", ChatColor.DARK_GRAY),
	NOT_NOOB(ChatColor.GRAY + "NOT A NOOB", ChatColor.DARK_GRAY),
	L33T(ChatColor.GRAY + "L33T", ChatColor.DARK_GRAY),
	L33T_SUPER(ChatColor.GRAY + "SUPER L33T", ChatColor.DARK_GRAY),
	L33T_MEGA(ChatColor.GRAY + "MEGA L33T", ChatColor.DARK_GRAY),
	L33T_HYPER(ChatColor.GRAY + "HYPER L33T", ChatColor.DARK_GRAY),
	DONOR(ChatColor.GREEN + "DONOR", ChatColor.DARK_GREEN),
	ORIGINAL(ChatColor.YELLOW + "ORIGINAL", ChatColor.GOLD),
	ADMIN(ChatColor.RED + "ADMIN", ChatColor.DARK_RED),
	DEV(ChatColor.DARK_AQUA + "DEVELOPER", ChatColor.BLUE),
	ASSASSIN(ChatColor.GRAY + "ASSASSIN", ChatColor.DARK_GRAY),
	ARCHER(ChatColor.GRAY + "ARCHER", ChatColor.DARK_GRAY),
	BERSERKER(ChatColor.GRAY + "BERSERKER", ChatColor.DARK_GRAY),
	CLERIC(ChatColor.GRAY + "CLERIC", ChatColor.DARK_GRAY),
	MAGE(ChatColor.GRAY + "MAGE", ChatColor.DARK_GRAY),
	SORCERER(ChatColor.GRAY + "SORCERER", ChatColor.DARK_GRAY),
	WARRIOR(ChatColor.GRAY + "WARRIOR", ChatColor.DARK_GRAY),
	INFILTRATOR(ChatColor.GRAY + "INFILTRATOR", ChatColor.DARK_GRAY),
	PALADIN(ChatColor.GRAY + "PALADIN", ChatColor.DARK_GRAY),
	HUNTSMAN(ChatColor.GRAY + "PALADIN", ChatColor.DARK_GRAY),
	RANGER(ChatColor.GRAY + "PALADIN", ChatColor.DARK_GRAY),
	ROUGE(ChatColor.GRAY + "PALADIN", ChatColor.DARK_GRAY);
	
	
	String str;
	ChatColor cc;
	
	Tag(String s, ChatColor cc){
		this.str = s;
	}
	
	public String toTag(){
		return cc + "[" + str + cc + "] ";
	}
	
}
