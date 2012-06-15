package com.olympuspvp.teamolympus.configuration;

import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.type.*;

public class WarConfig {
	
	public static void setCurrentTeam(Player p, String newTeam){
		olyWar.loadData(p).set("Current.team", newTeam);
	}public static String getCurrentTeam(Player p){
		return olyWar.loadData(p).getString("Current.team");
	}
	
	public static void setCurrentClass(Player p, String newClass){
		olyWar.loadData(p).set("Current.class", newClass);
	}public static String getCurrentClass(Player p){
		return olyWar.loadData(p).getString("Current.class");
	}
	
	public static void setTotalKills(Player p, int kills){
		olyWar.loadData(p).set("Records.kills", kills);
	}public static String getTotalKills(Player p){
		return olyWar.loadData(p).getString("Records.kills");
	}
	
	public static void setScore(Player p, int score){
		olyWar.loadData(p).set("Records.score", score);
	}public static String getScore(Player p){
		return olyWar.loadData(p).getString("Records.score");
	}
	
	public static void setClassKills(Player p, ClassType ct, int kills){
		olyWar.loadData(p).set("Records." + ct.getName() +".kills", kills);
	}public static String getClassKills(Player p, ClassType ct){
		return olyWar.loadData(p).getString("Records." + ct.getName() +".score");
	}
	
	public static void setClassScore(Player p, ClassType ct, int kills){
		olyWar.loadData(p).set("Records." + ct.getName() +".kills", kills);
	}public static String getClassScore(Player p, ClassType ct){
		return olyWar.loadData(p).getString("Records." + ct.getName() +".score");
	}
	
}
