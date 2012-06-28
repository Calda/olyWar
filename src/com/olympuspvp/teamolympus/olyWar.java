package com.olympuspvp.teamolympus;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.olympuspvp.teamolympus.Item.CloakInteract;
import com.olympuspvp.teamolympus.Item.InteractionListener;
import com.olympuspvp.teamolympus.Item.PortalInteract;
import com.olympuspvp.teamolympus.command.Manager;
import com.olympuspvp.teamolympus.configuration.LoginListener;
import com.olympuspvp.teamolympus.configuration.LogoutListener;
import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.damage.DamageListener;
import com.olympuspvp.teamolympus.damage.DeathListener;
import com.olympuspvp.teamolympus.game.AutoBalance;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.game.TeamPref;
import com.olympuspvp.teamolympus.type.ClassType;
import com.olympuspvp.teamolympus.game.Runtime;

public class olyWar extends JavaPlugin{

	public static Location spawn;
	public static Location redSpawn;
	public static Location blueSpawn;
	private static String[] array = {};
	public static boolean gameIsActive = false;
	public static String gameType = null;
	public static int playersAlive = 0;
	public static int redPlayersAlive = 0;
	public static int bluePlayersAlive = 0;
	public static List<String> invisible = Arrays.asList(array);
	public static int currentMapNumber = 0;
	public static Location currentRedSpawn = null;
	public static Location currentBlueSpawn = null;

	public static HashMap<String, Team> teams = new HashMap<String, Team>();
	public static HashMap<String, Integer> lives = new HashMap<String, Integer>();
	private static HashMap<String, TeamPref> preference = new HashMap<String, TeamPref>();
	private static HashMap<String, ClassType> players = new HashMap<String, ClassType>();
	private static HashMap<String, Integer> kills = new HashMap<String, Integer>();
	private static HashMap<String, Integer> score = new HashMap<String, Integer>();

<<<<<<< HEAD
	public static List<String> invisible = Arrays.asList(array);
	public static List<String> hasLeftGame = Arrays.asList(array);
	public static int currentMapNumber = 0;
	public static Location currentRedSpawn = null;
	public static Location currentBlueSpawn = null;

=======
>>>>>>> wat
	@Override
	public void onDisable(){
	}

	@Override
	public void onEnable(){
		WarConfig.loadConfig();
<<<<<<< HEAD
		Runtime.startGame(this);
=======
		
>>>>>>> wat
		spawn = WarConfig.getSpawn();

		final InteractionListener IL = new InteractionListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(IL, this);
		final DamageListener DL = new DamageListener();
		Bukkit.getServer().getPluginManager().registerEvents(DL, this);
		final LoginListener LL = new LoginListener();
		Bukkit.getServer().getPluginManager().registerEvents(LL, this);
		final LogoutListener OL = new LogoutListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(OL, this);
		final DeathListener DTHL = new DeathListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(DTHL, this);
		final PortalInteract PI = new PortalInteract();
		Bukkit.getServer().getPluginManager().registerEvents(PI, this);

		// HEARTBEAT
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run(){
				if(olyWar.gameIsActive){
					final Player[] players = Bukkit.getOnlinePlayers();
					if(players.length > 0){
						for(final Player p : players){
							if(olyWar.getClass(p) == ClassType.PALADIN){
								int health = p.getHealth();
								if(health != ClassType.PALADIN.getMaxHealth()) p.setHealth(health++);
							}else if(olyWar.getClass(p) == ClassType.ASSASSIN){
								if(invisible.contains(getName(p))){
									int mana = p.getFoodLevel();
									if(mana != 0) p.setFoodLevel(mana--);
									else CloakInteract.visible(p);
								}else{
									int mana = p.getFoodLevel();
									if(mana != 20) p.setFoodLevel(mana += 2);
								}
							}else{
								int mana = p.getFoodLevel();
								if(mana != 20) p.setFoodLevel(mana++);
							}
						}
					}
				}
			}
		}, 15L, 15L);
		//Autobalance Ticks
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				AutoBalance.run(true);
			}
		}, 3*60*20L, 3*60*20L);
	}

	@Override
	public boolean onCommand(final CommandSender s, final Command cc, final String cl, final String[] args){
		Manager.run(s, cl, args, this);
		return true;
	}

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

	public static ClassType getClass(final Player p){
		if(players.containsKey(getName(p))) return players.get(getName(p));
		else return null;
	}

	public static void setClass(final Player p, final ClassType ct){
		players.put(getName(p), ct);
	}

	public static void leaveClass(final Player p){
		players.remove(getName(p));
	}

	public static Team getTeam(final Player p){
		if(teams.containsKey(getName(p))) return teams.get(getName(p));
		else return null;
	}

	public static void setTeam(final Player p, final Team t){
		teams.put(getName(p), t);
		CraftPlayer cp = (CraftPlayer) p;
		cp.getHandle().name = t.getColor() + getName(p);
	}

	public static void leaveTeam(final Player p){
		teams.remove(getName(p));
		((CraftPlayer)p).getHandle().name = olyWar.getName(p);
	}

	public static TeamPref getPreference(final Player p){
		if(preference.containsKey(getName(p))) return preference.get(getName(p));
		else return null;
	}

	public static void setPreference(final Player p, final TeamPref tp){
		preference.put(getName(p), tp);
	}

	public static void removePreference(final Player p){
		preference.remove(getName(p));
	}

	public static int getScore(final Player p){
		if(score.containsKey(getName(p))) return score.get(getName(p));
		else return 0;
	}

	public static void addPoint(final Player p){
		int points = getScore(p);
		score.put(getName(p),points++);
	}

	public static int getLives(final Player p){
		if(lives.containsKey(getName(p))) return lives.get(getName(p));
		else return 0;
	}

	public static void die(final Player p, olyWar ow){
		kills.remove(getName(p));
		final ClassType ct = getClass(p);
		int ctkills = WarConfig.getClassScore(p, ct);
		int totalkills = WarConfig.getScore(p);
		totalkills++; ctkills++;
		WarConfig.setClassScore(p, ct, ctkills);
		WarConfig.setScore(p, totalkills);
		if(olyWar.gameType.equals("Team Deathmatch")){
			int numlives = getLives(p);
			numlives--;
			if(numlives > 0) lives.put(getName(p), numlives);
			if(numlives == 0){
				lives.remove(getName(p));
				playersAlive--;
				if(olyWar.getTeam(p) == Team.RED) redPlayersAlive--;
				else bluePlayersAlive--;
			}
		}
		
		if(redPlayersAlive == 0 || bluePlayersAlive == 0) Runtime.gameOverTDM(ow);
	}

	public static String getName(final Player p){
		String name = p.getName();
		return ChatColor.stripColor(name);
	}

}
