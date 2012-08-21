package com.olympuspvp.teamolympus.configuration;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.CraftChunk;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.Unlockable;
import com.olympuspvp.teamolympus.chat.Tag;
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
		World w = null;
		final List<World> ws = Bukkit.getWorlds();
		for(World world : ws){
			if(world.getName() == World) w = world;
		} if(w == null) w = ws.get(0);
		final Location loc = new Location(w, x, y, z, pitch, yaw);
		return loc;
	}

	public static Location getRedSpawn(final int mapNumber){
		final int x = config.getInt("maps.map" + mapNumber + ".red.x");
		final int y = config.getInt("maps.map" + mapNumber + ".red.y");
		final int z = config.getInt("maps.map" + mapNumber + ".red.z");
		final String World = config.getString("maps.map" + mapNumber + ".red.world");
		final float pitch = config.getInt("maps.map" + mapNumber + ".red.pitch");
		final float yaw = config.getInt("maps.map" + mapNumber + ".red.yaw");
		World w = null;
		final List<World> ws = Bukkit.getWorlds();
		for(World world : ws){
			if(world.getName() == World) w = world;
		} if(w == null) w = ws.get(0);
		final Location loc = new Location(w, x, y, z, pitch, yaw);
		return loc;
	}

	public static Location getBlueSpawn(final int mapNumber){
		final int x = config.getInt("maps.map" + mapNumber + ".blue.x");
		final int y = config.getInt("maps.map" + mapNumber + ".blue.y");
		final int z = config.getInt("maps.map" + mapNumber + ".blue.z");
		final String World = config.getString("maps.map" + mapNumber + ".blue.world");
		final float pitch = config.getInt("maps.map" + mapNumber + ".blue.pitch");
		final float yaw = config.getInt("maps.map" + mapNumber + ".blue.yaw");
		World w = null;
		final List<World> ws = Bukkit.getWorlds();
		for(World world : ws){
			if(world.getName() == World) w = world;
		} if(w == null) w = ws.get(0);
		final Location loc = new Location(w, x, y, z, pitch, yaw);
		return loc;
	}

	public static String getMapName(final int mapNumber){
		return config.getString("maps.map" + mapNumber + ".name");
	}

	public static String getMapType(final int mapNumber){
		return config.getString("maps.map" + mapNumber + ".type");
	}

	public static Chunk getChunk1(final int mapNumber){
		final World world = Bukkit.getWorld(config.getString("maps.map" + mapNumber + ".Chunk1.world"));
		final int x = config.getInt("maps.map" + mapNumber + ".Chunk1.x");
		final int y = config.getInt("maps.map" + mapNumber + ".Chunk1.y");
		CraftWorld cw = (CraftWorld) world;
		net.minecraft.server.Chunk chnk = new net.minecraft.server.Chunk(cw.getHandle(), x, y);
		CraftChunk cchunk = new CraftChunk((net.minecraft.server.Chunk) chnk);
		Chunk chunk = cchunk;
		return chunk;
	}

	public static Chunk getChunk2(final int mapNumber){
		final World world = Bukkit.getWorld(config.getString("maps.map" + mapNumber + ".Chunk1.world"));
		final int x = config.getInt("maps.map" + mapNumber + ".Chunk1.x");
		final int y = config.getInt("maps.map" + mapNumber + ".Chunk1.y");
		CraftWorld cw = (CraftWorld) world;
		net.minecraft.server.Chunk chnk = new net.minecraft.server.Chunk(cw.getHandle(), x, y);
		CraftChunk cchunk = new CraftChunk((net.minecraft.server.Chunk) chnk);
		Chunk chunk = cchunk;
		return chunk;
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

	public static void setWeaponPreference(final Player p, final Unlockable u){
		olyWar.loadData(p).set("Unlocks.Weapons.Preference" , u.getItemType().name());
	}

	@SuppressWarnings("deprecation")
	public static Unlockable getWeaponPreference(final Player p, final ClassType ct){
		String pref;
		if(ct == ClassType.PALADIN) pref = olyWar.loadData(p).getString("Unlocks.Weapons.Preference.Paladin");
		else if(ct == ClassType.SORCERER) pref = olyWar.loadData(p).getString("Unlocks.Weapons.Preference.Sorcerer");
		else if(ct == ClassType.RANGER) pref = olyWar.loadData(p).getString("Unlocks.Weapons.Preference.Ranger");
		else pref = olyWar.loadData(p).getString("Unlocks.Weapons.Preference.Assassin");

		if(pref.equals("Fire Sword")) return Unlockable.SWORD_FIRE;
		else if(pref.equals("Magic Sword")) return Unlockable.SWORD_MAGIC;
		else if(pref.equals("Poison Dagger")) return Unlockable.DAGGER_POISON;
		else if(pref.equals("Magic Dagger")) return Unlockable.DAGGER_MAGIC;
		else if(pref.equals("Big Fire Staff")) return Unlockable.STAFF_FIRE_BIG;
		else if(pref.equals("Burning Crossbow")) return Unlockable.CROSSBOW_BURNING;
		else if(pref.equals("Magic Crossbow")) return Unlockable.CROSSBOW_MAGIC;
		else{
			if(ct == ClassType.PALADIN) return Unlockable.SWORD_LONG;
			else if(ct == ClassType.SORCERER) return Unlockable.STAFF_FIRE;
			else if(ct == ClassType.RANGER) return Unlockable.CROSSBOW;
			else return Unlockable.DAGGER;
		}
	}

	public static boolean getTagStatus(Player p, Tag t){
		List<String> unlocked = getTagList(p);
		if(unlocked != null){
			if(unlocked.contains(t.toString())) return true;
			else return false;
		}else return false;
	}

	public static void addUnlockedTag(Player p, Tag t){
		String add = t.toString();
		List<String> unlocked = getTagList(p);
		unlocked.add(add);
		olyWar.loadData(p).set("Unlocks.tags.list", unlocked);
	}

	public static List<String> getTagList(Player p){
		return olyWar.loadData(p).getStringList("Unlocks.tags.list");
	}
	
	public static void setTagPreference(Player p, Tag t){
		olyWar.loadData(p).set("Unlocks.tags.pref", t.toString());
	}
	
	public static String getTagPreference(Player p){
		String pref = Tag.valueOf(olyWar.loadData(p).getString("Unlocks.tags.pref")).toTag();
		if(pref == null) pref = Tag.NOOB.toTag();
		return pref;
	}
}
