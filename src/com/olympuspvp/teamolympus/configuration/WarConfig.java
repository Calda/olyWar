package com.olympuspvp.teamolympus.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.Unlockable;
import com.olympuspvp.teamolympus.type.*;

public class WarConfig {
	private static FileConfiguration config;
	
	
	
	
	
	
	//***************
	//PLAYER METHODS
	//***************
	public static void setCurrentTeam(Player p, String newTeam){
		olyWar.loadData(p).set("Current.team", newTeam);
	}public static String getCurrentTeam(Player p){
		return olyWar.loadData(p).getString("Current.team");
	}
	
	public static void setCurrentPreference(Player p, String newPref){
		olyWar.loadData(p).set("Current.pref", newPref);
	}public static String getCurrentPreference(Player p){
		return olyWar.loadData(p).getString("Current.pref");
	}
	
	public static void setCurrentClass(Player p, String newClass){
		olyWar.loadData(p).set("Current.class", newClass);
	}public static String getCurrentClass(Player p){
		return olyWar.loadData(p).getString("Current.class");
	}
	
	public static void setTotalKills(Player p, int kills){
		olyWar.loadData(p).set("Records.kills", kills);
	}public static int getTotalKills(Player p){
		return olyWar.loadData(p).getInt("Records.kills");
	}
	
	public static void setScore(Player p, int score){
		olyWar.loadData(p).set("Records.score", score);
	}public static int getScore(Player p){
		return olyWar.loadData(p).getInt("Records.score");
	}
	
	public static void setClassKills(Player p, ClassType ct, int kills){
		olyWar.loadData(p).set("Records." + ct.getName() +".kills", kills);
	}public static int getClassKills(Player p, ClassType ct){
		return olyWar.loadData(p).getInt("Records." + ct.getName() +".kills");
	}
	
	public static void setClassScore(Player p, ClassType ct, int score){
		olyWar.loadData(p).set("Records." + ct.getName() +".score", score);
	}public static int getClassScore(Player p, ClassType ct){
		return olyWar.loadData(p).getInt("Records." + ct.getName() +".score");
	}
	
	public static void setClassStatus(Player p, ClassType ct, boolean value){
		olyWar.loadData(p).set("Unlocks.Classes." + ct.getName(), value);
	}public static boolean getClassStatus(Player p, ClassType ct){
		return olyWar.loadData(p).getBoolean("Unlocks.Classes." + ct.getName());
	}
	
	public static void setWeaponStatus(Player p, Unlockable u, boolean value){
		olyWar.loadData(p).set("Unlocks.Weapons." + u.getUnlockingClass().getName() + "." + u.getItemType().toString(), value);
	}public static boolean getWeaponStatus(Player p, Unlockable u){
		return olyWar.loadData(p).getBoolean("Unlocks.Weapons." + u.getUnlockingClass().getName() + "." + u.getItemType().toString());
	}
}

