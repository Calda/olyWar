package com.olympuspvp.teamolympus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.server.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.olympuspvp.teamolympus.Item.InteractionListener;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.PortalInteract;
import com.olympuspvp.teamolympus.command.ChooseMap;
import com.olympuspvp.teamolympus.command.Manager;
import com.olympuspvp.teamolympus.configuration.DataEntry;
import com.olympuspvp.teamolympus.configuration.LoginListener;
import com.olympuspvp.teamolympus.configuration.LogoutListener;
import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.damage.DamageListener;
import com.olympuspvp.teamolympus.damage.DeathListener;
import com.olympuspvp.teamolympus.damage.EntityHealthRegain;
import com.olympuspvp.teamolympus.damage.RespawnListener;
import com.olympuspvp.teamolympus.game.MotionListener;
import com.olympuspvp.teamolympus.game.Runtime;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.game.TeamPref;
import com.olympuspvp.teamolympus.scheduler.ADbeat;
import com.olympuspvp.teamolympus.scheduler.Heartbeat;
import com.olympuspvp.teamolympus.scheduler.KOTHbeat;
import com.olympuspvp.teamolympus.type.ClassType;

public class olyWar extends JavaPlugin{

	public static Location spawn;
	public static Location redSpawn;
	public static Location blueSpawn;
	public static boolean gameIsActive = false;
	public static int playersAlive = 0;
	public static int redPlayersAlive = 0;
	public static int bluePlayersAlive = 0;
	public static String mapType = "";
	public static String mapName = "";
	public static Chunk point1;
	public static Chunk point2;
	final public static String map = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "MAP" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD;
	final public static ChatColor[] colors = {
		ChatColor.AQUA, ChatColor.BLUE, ChatColor.DARK_AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_GRAY, ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE,
		ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.YELLOW};
	public static Location[] freeForAllSpawns = null;

	private static HashMap<String, HashMap<DataEntry, Object>> data = new HashMap<String, HashMap<DataEntry, Object>>();

	private static List<String> invisible = new ArrayList<String>();
	public static int currentMapNumber = 0;
	public static Location currentRedSpawn = null;
	public static Location currentBlueSpawn = null;

	@Override
	public void onEnable(){
		WarConfig.loadConfig();
		Runtime.startGame(this);
		spawn = WarConfig.getSpawn();
		final InteractionListener IL = new InteractionListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(IL, this);
		final DamageListener DL = new DamageListener();
		Bukkit.getServer().getPluginManager().registerEvents(DL, this);
		final LoginListener LL = new LoginListener();
		Bukkit.getServer().getPluginManager().registerEvents(LL, this);
		final RespawnListener RL = new RespawnListener();
		Bukkit.getServer().getPluginManager().registerEvents(RL, this);
		final LogoutListener OL = new LogoutListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(OL, this);
		final DeathListener DTHL = new DeathListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(DTHL, this);
		final PortalInteract PI = new PortalInteract();
		Bukkit.getServer().getPluginManager().registerEvents(PI, this);
		final EntityHealthRegain EHR = new EntityHealthRegain();
		Bukkit.getServer().getPluginManager().registerEvents(EHR, this);
		final MotionListener ML = new MotionListener();
		Bukkit.getServer().getPluginManager().registerEvents(ML, this);

		Heartbeat.start(this);
		KOTHbeat.start(this);
		ADbeat.start(this);
		ChooseMap.loadMapNames();
	}@Override
	public void onDisable(){
		PortalInteract.deleteAllPortals();
		data.clear();
		invisible.clear();
		currentMapNumber = 0;
		currentRedSpawn = null;
		currentBlueSpawn = null;
		for(final Player p : Bukkit.getOnlinePlayers()){
			olyWar.setPlayerName(((CraftPlayer)p).getHandle(), olyWar.getName(p));
			p.teleport(olyWar.spawn);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			for(final PotionEffect pe : p.getActivePotionEffects()){
				p.removePotionEffect(pe.getType());
			}
		}System.out.println("[olyWar] disabled");
	}

	@Override
	public boolean onCommand(final CommandSender s, final Command cc, final String cl, final String[] args){
		Manager.run(s, cl, args, this);
		return true;
	}

	/**
	 *@param p the object of the player that a name is desired of
	 *@return The player's colorless name
	 */
	public static String getName(final Player p){
		final String name = p.getName();
		return ChatColor.stripColor(name);
	}

	/**
	 * @return the olyWar chat symbol, to prepend importaint announcements
	 */
	public static String getLogo(){
		return ChatColor.BLUE + "[" + ChatColor.YELLOW + "oly" + ChatColor.GOLD + "War" + ChatColor.BLUE + "] " + ChatColor.GOLD;
	}


	public static FileConfiguration loadData(final Player owner){
		final File customConfigFile = new File("plugins/olyWar/players/" + owner.getName() + ".yml");
		final FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		final String name = customConfig.getString("name");
		if(name == null){

		}
		return customConfig;
	}

	/**
	 * Returns a piece of data from a player, based on e
	 * @param p The player whose data will be returned
	 * @param e The type of data that will be returned
	 * @return the DataEntry desired
	 */
	private static Object getDataEntry(final Player p, final DataEntry e){
		if(playerHasData(p,e)){
			final String name = getName(p);
			return data.get(name).get(e);
		}else return null;
	}

	/**
	 * Adds an empty playerData so that it can be added on top of
	 * @param p the Player to create a NullData for
	 */
	public static void createNullData(final Player p){
		final HashMap<DataEntry, Object> nullData = new HashMap<DataEntry, Object>();
		data.put(getName(p), nullData);
	}

	/**
	 * sets a DataEntry to a new variable
	 * @param p the player whose data is to be modified
	 * @param e the entry that will be modified
	 * @param entry the object that will replace the old entry
	 */
	private static void setDataEntry(final Player p, final DataEntry e, final Object entry){
		if(!playerHasData(p)){
			createNullData(p);
		}data.get(getName(p)).put(e, entry);
	}

	/**
	 * Checks if config data for a player exists
	 * @param p the player the check will operate for
	 * @return true if entry found, otherwise false
	 */
	public static boolean playerHasData(final Player p){
		if(data.containsKey(getName(p))){
			return true;
		}return false;
	}

	/**
	 * Checks is config data for a player exists
	 * @param p the player the check will operate for
	 * @param e the DataEntry that will be checked
	 * @return true if entry found, otherwise false
	 */
	public static boolean playerHasData(final Player p, final DataEntry e){
		if(playerHasData(p)){
			if(data.get(getName(p)).containsKey(e)){
				return true;
			}
		}return false;
	}

	/**
	 * gets the CLASS_CURRENT out of the data hashmap
	 * @param p the player to find the current class of
	 * @return CLASS_CURRENT of Player p
	 */
	public static ClassType getClass(final Player p){
		if(playerHasData(p, DataEntry.CLASS_CURRENT)){
			final ClassType ct = (ClassType) getDataEntry(p, DataEntry.CLASS_CURRENT);
			return ct;
		}else return null;
	}

	/**
	 * sets the CURRENT_CLASS of a player
	 * @param p The player whose class will be set
	 * @param ct ClassType to set
	 */
	public static void setClass(final Player p, final ClassType ct){
		setDataEntry(p, DataEntry.CLASS_CURRENT, ct);
	}

	/**
	 * removes a player's class choice, which will keep them from joining a match
	 * @param p the player whose class will be removed
	 */
	public static void leaveClass(final Player p){
		data.get(getName(p)).remove(DataEntry.CLASS_CURRENT);
	}

	/**
	 * returns the player's CURRENT team
	 * @param p the player
	 * @return the player's current IN-GAME team. If not in a match, will be Team.NONE
	 */
	public static Team getTeam(final Player p){
		if(playerHasData(p, DataEntry.TEAM_CURRENT)){
			final Team t = (Team) getDataEntry(p, DataEntry.TEAM_CURRENT);
			return t;
		}else return Team.NONE;
	}

	/**
	 * Sets the player's current team
	 * @param p the player
	 * @param t the team to set as
	 */
	public static void setTeam(final Player p, final Team t){
		setDataEntry(p, DataEntry.TEAM_CURRENT, t);
	}

	/**
	 * removes a player from an in-game team
	 * @param p the player
	 */
	public static void leaveTeam(final Player p){
		data.get(getName(p)).remove(DataEntry.TEAM_CURRENT);
	}

	/**
	 * returns the player's current Team PREFERENCE
	 * @param p the player
	 * @return the player's current pref team
	 */
	public static TeamPref getPreference(final Player p){
		if(playerHasData(p, DataEntry.TEAM_PREF)){
			final TeamPref tp = (TeamPref) getDataEntry(p, DataEntry.TEAM_PREF);
			return tp;
		}else return null;
	}

	/**
	 * sets the player's pref team
	 * @param p the player
	 * @param tp the player's team pref
	 */
	public static void setPreference(final Player p, final TeamPref tp){
		setDataEntry(p, DataEntry.TEAM_PREF, tp);
	}

	/**
	 * removes a player's team preference, which will keep the player from joining a team.
	 * @param p the player
	 */
	public static void removePreference(final Player p){
		data.get(getName(p)).remove(DataEntry.TEAM_PREF);
	}

	/**
	 * sets the player's in-game team based on their preference
	 * @param p the player
	 */
	public static void loadTeamFromPreference(final Player p){
		final Random r = new Random();
		final TeamPref playerPref = olyWar.getPreference(p);
		final ClassType ct = olyWar.getClass(p);

		if(ct != null){
			boolean apply = true;
			if(playerPref == TeamPref.RED) olyWar.setTeam(p, Team.RED);
			else if(playerPref == TeamPref.BLUE) olyWar.setTeam(p, Team.BLUE);
			else if(playerPref == TeamPref.RANDOM){
				final boolean joinRed = r.nextBoolean();
				if(joinRed) olyWar.setTeam(p, Team.RED);
				else olyWar.setTeam(p, Team.BLUE);
			}else{
				apply = false;
				p.sendMessage(map + "You have not selected a team.");
				p.sendMessage(map + "Use the commands /red, /blue, or /random to join a team!");
			}if(apply) olyWar.applyClass(p);
		}else{
			p.sendMessage(map + "You have not selected a class.");
			p.sendMessage(map + "Use the command /class to choose one from the list.");
		}

	}


	public static int getScore(final Player p){
		if(olyWar.playerHasData(p, DataEntry.POINTS)){
			final int score = (Integer) getDataEntry(p, DataEntry.POINTS);
			return score;
		}else return 0;
	}

	public static void addPoint(final Player p){
		final int points = getScore(p);
		setScore(p, points + 1);
	}

	public static void setScore(final Player p, final int newScore){
		setDataEntry(p, DataEntry.POINTS, newScore);
	}

	public static void setLives(final Player p, final int life){
		setDataEntry(p, DataEntry.LIVES, life);
	}

	public static int getLives(final Player p){
		if(olyWar.playerHasData(p, DataEntry.LIVES)){
			final int lives = (Integer) getDataEntry(p, DataEntry.LIVES);
			return lives;
		}else return 0;
	}

	public static void clearLives(final Player p){
		data.get(getName(p)).remove(DataEntry.LIVES);
	}

	public static void runDeath(final Player p, final olyWar ow){
		for(final PotionEffect pe : p.getActivePotionEffects()){
			p.removePotionEffect(pe.getType());
		}if(olyWar.mapType == "TDM"){
			final int life = getLives(p) - 1;
			setLives(p, life);
			if(life == 0){
				clearLives(p);
				if(getTeam(p) == Team.RED) redPlayersAlive--;
				else bluePlayersAlive--;
				if(redPlayersAlive == 0 || bluePlayersAlive == 0){
					Runtime.gameOverTDM(ow);
				}
			}
		}
	}

	public static void applyClass(final Player p){
		final ClassType ct = olyWar.getClass(p);
		if(ct == null){
			p.sendMessage(map + "You have not selected a class." + ChatColor.YELLOW + " /classes " + ChatColor.GOLD +"to select one.");
			return;
		}p.setHealth(ct.getMaxHealth());
		final PlayerInventory i = p.getInventory();
		i.clear();
		i.setArmorContents(ct.getArmorContents());
		if(ct == ClassType.ARCHER){
			i.setItem(0,new ItemStack(ItemType.BOW.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.ARROW.getMaterial(),ItemType.ARROW.getAmount()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(4,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 1));
		}else if(ct == ClassType.ASSASSIN){
			i.setItem(0,new ItemStack(ItemType.DAGGER.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.POWDER_POISON.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.CLOAK.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(4,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 3));
		}else if(ct == ClassType.BERSERKER){
			i.setItem(0,new ItemStack(ItemType.WAR_AXE.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10*60*20, 1));
		}else if(ct == ClassType.CLERIC){
			i.setItem(0,new ItemStack(ItemType.STAFF_HEALING.getMaterial()));
		}else if(ct == ClassType.HUNTSMAN){
			i.setItem(0,new ItemStack(ItemType.BOW.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.ARROW.getMaterial(),ItemType.ARROW.getAmount()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(4,new ItemStack(ItemType.WOLF_EGG.getMaterial()));
			i.setItem(5,new ItemStack(ItemType.WOLF_EGG.getMaterial()));
			i.setItem(6,new ItemStack(ItemType.WOLF_EGG.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
		}else if(ct == ClassType.MAGE){
			i.setItem(0,new ItemStack(ItemType.STAFF_FIRE.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
		}else if(ct == ClassType.PALADIN){
			i.setItem(0,new ItemStack(ItemType.SWORD_LONG.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10*60*20, 1));
		}else if(ct == ClassType.RANGER){
			i.setItem(0,new ItemStack(ItemType.BOW.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.ARROW.getMaterial(),ItemType.ARROW.getAmount()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
		}else if(ct == ClassType.ROUGE){
			i.setItem(0,new ItemStack(ItemType.THROWING_KNIVES.getMaterial(),ItemType.ARROW.getAmount()));
			i.setItem(1,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 2));
		}else if(ct == ClassType.SORCERER){
			i.setItem(0,new ItemStack(ItemType.STAFF_FIRE.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.STAFF_LIGHTNING.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(3,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10*60*20, 1));
		}else if(ct == ClassType.WARRIOR){
			i.setItem(0,new ItemStack(ItemType.SWORD_SHORT.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
		}else if(ct == ClassType.INFILTRATOR){
			i.setItem(0,new ItemStack(ItemType.STAFF_BLINK.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.PORTAL.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.PORTAL.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 3));
		}
	}
	
	public static boolean isInvisible(Player p){
		return invisible.contains(getName(p));
	}
	
	public static void setVisible(Player p){
		if(isInvisible(p)) invisible.remove(getName(p));
	}

	public static void setInisible(Player p){
		if(!isInvisible(p)) invisible.add(getName(p));
	}
	
	public static void spawnPlayer(final Player p){
		final Team t = olyWar.getTeam(p);
		final ClassType ct = olyWar.getClass(p);
		if(olyWar.mapType.equals("Free For All")){
			p.teleport(getFreeForAllSpawn());
			p.sendMessage(map + "You have spawned as " + ct.getArticle() + " " + ChatColor.GRAY + ct.getName());
		}else if(t == Team.RED){
			setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.RED + getName(p));
			p.teleport(redSpawn, TeleportCause.PLUGIN);
			p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK));
			p.sendMessage(map + "You have spawned as " + ct.getArticle() + " " + t.getColor() + ct.getName());
		}else if(t == Team.BLUE){
			setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.BLUE + getName(p));
			p.teleport(blueSpawn, TeleportCause.PLUGIN);
			p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
			p.sendMessage(map + "You have spawned as " + ct.getArticle() + " " + t.getColor() + ct.getName());
		}
	}

	public static Location getFreeForAllSpawn(){
		final int numberOfSpawns = freeForAllSpawns.length;
		final Random r = new Random();
		return freeForAllSpawns[r.nextInt(numberOfSpawns)];
	}

	public static void setPlayerName(final EntityPlayer player, final String newname){
		player.name = newname;
	}

	public static String toRainbow(final String string){
		final StringBuilder sb = new StringBuilder();
		for(final char c : string.toCharArray()){
			final int r = new Random().nextInt(olyWar.colors.length);
			sb.append(olyWar.colors[r] + Character.toString(c));
		}return sb.toString();
	}
}