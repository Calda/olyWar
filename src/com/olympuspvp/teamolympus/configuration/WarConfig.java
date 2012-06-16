package com.olympuspvp.teamolympus.configuration;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.Unlockable;
import com.olympuspvp.teamolympus.type.*;

public class WarConfig {
	private static FileConfiguration config;
	
	public static void loadConfig() {
		File customConfigFile = new File("plugins/olyWar/config.yml");
		FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		config = customConfig;
	}
	
	public static int getNumberOfMaps(){
		return config.getInt("maps.number");
	}
	
	public static Location getSpawn(){
		int x = config.getInt("spawn.x");
		int y = config.getInt("spawn.y");
		int z = config.getInt("spawn.z");
		String World = config.getString("spawn.world");
		float pitch = config.getInt("spawn.pitch");
		float yaw = config.getInt("spawn.yaw");
		Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}
	
	public static Location getRedSpawn(int mapNumber){
		int x = config.getInt("maps.map" + mapNumber + ".red.x");
		int y = config.getInt("maps.map" + mapNumber + ".red.y");
		int z = config.getInt("maps.map" + mapNumber + ".red.z");
		String World = config.getString("maps.map" + mapNumber + ".red.world");
		float pitch = config.getInt("maps.map" + mapNumber + ".red.pitch");
		float yaw = config.getInt("maps.map" + mapNumber + ".red.yaw");
		Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}

	public static Location getBlueSpawn(int mapNumber){
		int x = config.getInt("maps.map" + mapNumber + ".blue.x");
		int y = config.getInt("maps.map" + mapNumber + ".blue.y");
		int z = config.getInt("maps.map" + mapNumber + ".blue.z");
		String World = config.getString("maps.map" + mapNumber + ".blue.world");
		float pitch = config.getInt("maps.map" + mapNumber + ".blue.pitch");
		float yaw = config.getInt("maps.map" + mapNumber + ".blue.yaw");
		Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}
	
	public static String getMapName(int mapNumber){
		String name = config.getString("maps.map" + mapNumber + ".name");
		name = name.replace("&", "¤");
		name = name.replace("¤¤", "&");
		return name;
	}
	
	
	
	
	//***************
	//PLAYER METHODS
	//***************
	@Deprecated
	public static void setCurrentTeam(Player p, String newTeam){
		olyWar.loadData(p).set("Current.team", newTeam);
	}@Deprecated
	public static String getCurrentTeam(Player p){
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

