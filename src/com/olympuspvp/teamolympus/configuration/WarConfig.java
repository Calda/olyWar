package com.olympuspvp.teamolympus.configuration;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.Unlockable;
import com.olympuspvp.teamolympus.type.ClassType;

public class WarConfig{

	private static FileConfiguration config;

	public static void loadConfig(){
		final File customConfigFile = new File("plugins/olyWar/config.yml");
		final FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		config = customConfig;
	}

	public static int getNumberOfMaps(){
		return config.getInt("maps.number");
	}

	public static Location getSpawn(){
		final int x = config.getInt("spawn.x");
		final int y = config.getInt("spawn.y");
		final int z = config.getInt("spawn.z");
		final String World = config.getString("spawn.world");
		final float pitch = config.getInt("spawn.pitch");
		final float yaw = config.getInt("spawn.yaw");
		final Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}

	public static Location getRedSpawn(final int mapNumber){
		final int x = config.getInt("maps.map" + mapNumber + ".red.x");
		final int y = config.getInt("maps.map" + mapNumber + ".red.y");
		final int z = config.getInt("maps.map" + mapNumber + ".red.z");
		final String World = config.getString("maps.map" + mapNumber + ".red.world");
		final float pitch = config.getInt("maps.map" + mapNumber + ".red.pitch");
		final float yaw = config.getInt("maps.map" + mapNumber + ".red.yaw");
		final Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}

	public static Location getBlueSpawn(final int mapNumber){
		final int x = config.getInt("maps.map" + mapNumber + ".blue.x");
		final int y = config.getInt("maps.map" + mapNumber + ".blue.y");
		final int z = config.getInt("maps.map" + mapNumber + ".blue.z");
		final String World = config.getString("maps.map" + mapNumber + ".blue.world");
		final float pitch = config.getInt("maps.map" + mapNumber + ".blue.pitch");
		final float yaw = config.getInt("maps.map" + mapNumber + ".blue.yaw");
		final Location loc = new Location(Bukkit.getWorld(World), x, y, z, pitch, yaw);
		return loc;
	}

	public static String getMapName(final int mapNumber){
		String name = config.getString("maps.map" + mapNumber + ".name");
		name = name.replace("&", "¤");
		name = name.replace("¤¤", "&");
		return name;
	}

	public static String getMapType(final int mapNumber){
		final int type = config.getInt("maps.map" + mapNumber + ".type");
		if(type == 1) return "Control Point";
		else if(type == 2) return "Team Deathmatch";
		else return "Control Point";
	}

	// ***************
	// PLAYER METHODS
	// ***************
	@Deprecated
	public static void setTotalKills(final Player p, final int kills){
		olyWar.loadData(p).set("Records.kills", kills);
	}
	@Deprecated
	public static int getTotalKills(final Player p){
		return olyWar.loadData(p).getInt("Records.kills");
	}

	public static void setScore(final Player p, final int score){
		olyWar.loadData(p).set("Records.score", score);
	}

	public static int getScore(final Player p){
		return olyWar.loadData(p).getInt("Records.score");
	}
	@Deprecated
	public static void setClassKills(final Player p, final ClassType ct, final int kills){
		olyWar.loadData(p).set("Records." + ct.getName() + ".kills", kills);
	}
	@Deprecated
	public static int getClassKills(final Player p, final ClassType ct){
		return olyWar.loadData(p).getInt("Records." + ct.getName() + ".kills");
	}

	public static void setClassScore(final Player p, final ClassType ct, final int score){
		olyWar.loadData(p).set("Records." + ct.getName() + ".score", score);
	}

	public static int getClassScore(final Player p, final ClassType ct){
		return olyWar.loadData(p).getInt("Records." + ct.getName() + ".score");
	}

	public static void setClassStatus(final Player p, final ClassType ct, final boolean value){
		olyWar.loadData(p).set("Unlocks.Classes." + ct.getName(), value);
	}
	//unlocks
	public static boolean getClassStatus(final Player p, final ClassType ct){
		return olyWar.loadData(p).getBoolean("Unlocks.Classes." + ct.getName());
	}

	public static void setWeaponStatus(final Player p, final Unlockable u, final boolean value){
		olyWar.loadData(p).set("Unlocks.Weapons." + u.getUnlockingClass().getName() + "." + u.getItemType().toString(), value);
	}

	public static boolean getWeaponStatus(final Player p, final Unlockable u){
		return olyWar.loadData(p).getBoolean("Unlocks.Weapons." + u.getUnlockingClass().getName() + "." + u.getItemType().toString());
	}
}
