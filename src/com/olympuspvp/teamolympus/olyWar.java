package com.olympuspvp.teamolympus;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EntityTracker;
import net.minecraft.server.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.olympuspvp.teamolympus.Item.CloakInteract;
import com.olympuspvp.teamolympus.Item.InteractionListener;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.PortalInteract;
import com.olympuspvp.teamolympus.command.Manager;
import com.olympuspvp.teamolympus.configuration.LoginListener;
import com.olympuspvp.teamolympus.configuration.LogoutListener;
import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.damage.DamageListener;
import com.olympuspvp.teamolympus.damage.DeathListener;
import com.olympuspvp.teamolympus.damage.EntityHealthRegain;
import com.olympuspvp.teamolympus.damage.RespawnListener;
import com.olympuspvp.teamolympus.game.AutoBalance;
import com.olympuspvp.teamolympus.game.NoSprint;
import com.olympuspvp.teamolympus.game.Runtime;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.game.TeamPref;
import com.olympuspvp.teamolympus.type.ClassType;

public class olyWar extends JavaPlugin{

	public static Location spawn;
	public static Location redSpawn;
	public static Location blueSpawn;
	private static String[] array = {};
	public static boolean gameIsActive = false;
	public static int playersAlive = 0;
	public static int redPlayersAlive = 0;
	public static int bluePlayersAlive = 0;
	public static String mapType;
	public static String mapName;
	final public static String map = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "MAP" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD;

	public static HashMap<String, Team> teams = new HashMap<String, Team>();
	public static HashMap<String, Integer> lives = new HashMap<String, Integer>();
	private static HashMap<String, TeamPref> preference = new HashMap<String, TeamPref>();
	private static HashMap<String, ClassType> players = new HashMap<String, ClassType>();
	private static HashMap<String, Integer> kills = new HashMap<String, Integer>();
	private static HashMap<String, Integer> score = new HashMap<String, Integer>();

	public static List<String> invisible = Arrays.asList(array);
	public static List<String> hasLeftGame = Arrays.asList(array);
	public static int currentMapNumber = 0;
	public static Location currentRedSpawn = null;
	public static Location currentBlueSpawn = null;

	@Override
	public void onDisable(){
	}

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

		// HEARTBEAT
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run(){
				//if(olyWar.gameIsActive){
				final Player[] players = Bukkit.getOnlinePlayers();
				if(players.length > 0){
					for(final Player p : players){
						if(olyWar.getClass(p) == ClassType.PALADIN){
							int health = p.getHealth();
							if(health != ClassType.PALADIN.getMaxHealth()) p.setHealth(health+1);
						}if(olyWar.getClass(p) == ClassType.ASSASSIN){
							if(invisible.contains(getName(p))){
								int mana = p.getFoodLevel();
								if(mana != 0) p.setFoodLevel(mana - 1);
								else CloakInteract.visible(p);
							}else{
								int mana = p.getFoodLevel();
								if(mana != 20) p.setFoodLevel(mana + 2);
							}
						}else{
							int mana = p.getFoodLevel();
							System.out.println(mana);
							if(mana != 20) p.setFoodLevel(mana + 1);
						}
					}
				}
				//}
			}
		}, 15L, 15L);
		//Autobalance Ticks
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				AutoBalance.run(true);
			}
		}, 3*60*20L, 3*60*20L);
		//NoSprint
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run(){
				NoSprint.check();
			}
		}, 3L,3L);
	}
	@Override
	public boolean onCommand(final CommandSender s, final Command cc, final String cl, final String[] args){
		Manager.run(s, cl, args, this);
		return true;
	}

	public static String getName(final Player p){
		final String name = p.getName();
		return ChatColor.stripColor(name);
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
		else return Team.NONE;
	}

	public static void setTeam(final Player p, final Team t){
		teams.put(getName(p), t);
		final CraftPlayer cp = (CraftPlayer) p;
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
		if(score.containsKey(getName(p))) return score.get(getName(p));
		else return 0;
	}

	public static void addPoint(final Player p){
		int points = getScore(p);
		score.put(getName(p),points++);
	}

	public static void setLives(final Player p, final int life){
		lives.put(getName(p),new Integer(life));
	}

	public static int getLives(final Player p){
		if(lives.containsKey(getName(p))) return lives.get(getName(p));
		else return 0;
	}

	public static void die(final Player p, final olyWar ow){
		System.out.println("death");
		kills.remove(getName(p));
		for(final PotionEffect pe : p.getActivePotionEffects()){
			p.removePotionEffect(pe.getType());
		} final ClassType ct = getClass(p);
		int ctkills = WarConfig.getClassScore(p, ct);
		int totalkills = WarConfig.getScore(p);
		totalkills++; ctkills++;
		WarConfig.setClassScore(p, ct, ctkills);
		WarConfig.setScore(p, totalkills);
		int numlives = getLives(p);
		numlives--;
		if(numlives > 0) lives.put(getName(p), numlives);
		if(numlives == 0){
			lives.remove(getName(p));
			playersAlive--;
			if(olyWar.getTeam(p) == Team.RED) redPlayersAlive--;
			else bluePlayersAlive--;
		}

		if(redPlayersAlive == 0 || bluePlayersAlive == 0) Runtime.gameOverTDM(ow);
	}

	public static void applyClass(final Player p){
		final ClassType ct = olyWar.getClass(p);
		p.setHealth(ct.getMaxHealth());
		final Inventory i = p.getInventory();
		i.clear();
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
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 4));
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 4));
		}else if(ct == ClassType.BERSERKER){
			i.setItem(0,new ItemStack(ItemType.WAR_AXE.getMaterial()));
			i.setItem(1,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			i.setItem(2,new ItemStack(ItemType.POTION_HEALTH.getMaterial()));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
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
		}
	}

	public static void spawnPlayer(final Player p){
		final Team t = olyWar.getTeam(p);
		final ClassType ct = olyWar.getClass(p);
		if(t == Team.RED){
			setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.RED + getName(p));
			p.teleport(redSpawn, TeleportCause.PLUGIN);
			p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK));
		}if(t == Team.BLUE){
			setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.BLUE + getName(p));
			p.teleport(blueSpawn, TeleportCause.PLUGIN);
			p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
		}if(t != Team.NONE){
			p.sendMessage(map + "You have spawned in " + ChatColor.GREEN + mapType + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mapName + ChatColor.GOLD + " as " + ct.getArticle() + " " + t.getColor() + ct.getName());
			olyWar.setLives(p, 3);
		}
	}

	public static void setPlayerName(final EntityPlayer player, final String newname){
		final WorldServer world = (WorldServer) player.world;
		final EntityTracker tracker = world.tracker;
		tracker.untrackEntity(player);
		player.name = newname;
		tracker.track(player);
	}
}