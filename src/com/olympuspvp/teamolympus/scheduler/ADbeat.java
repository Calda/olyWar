package com.olympuspvp.teamolympus.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Runtime;
import com.olympuspvp.teamolympus.game.Team;

public class ADbeat {

	private static int percentageRed1 = 0;
	private static int percentageRed2 = 0;
	private static Team owningTeam1 = Team.RED;
	private static Team owningTeam2 = Team.RED;
	private static int redTime = 64;
	private static Server s = Bukkit.getServer();
	private static olyWar ow = null;
	private static boolean started = false;
	public static boolean setup = false;
	private static int setupTime = 30;

	public static void start(final olyWar olyw){
		ow = olyw;
		percentageRed1 = 0;
		percentageRed2 = 0;
		redTime = 60;
		owningTeam1 = Team.RED;
		owningTeam2 = Team.RED;
		started = false;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType.equals("Attack/Defense")){
					if(!started){
						s.broadcastMessage(olyWar.map + "Setup for " + ChatColor.RED + "Team Red " + ChatColor.GOLD + "has begun. 30 seconds remain.");
						started = true;
						setOwner(Team.RED, olyWar.point1, 1);
						setOwner(Team.RED, olyWar.point2, 2);
						percentageRed1 = 0;
						percentageRed2 = 0;
						redTime = 60;
						setupTime = 30;
						setup = true;
					}//point1
					if(owningTeam1 == Team.RED){
						int red = 0;
						int blue = 0;
						for(final Entity e : olyWar.point1.getEntities()){
							if(e instanceof Player){
								final Player p = (Player) e;
								final Material m = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
								if(m == Material.WOOL || m == Material.NETHERRACK || m == Material.LAPIS_BLOCK){
									final Team t = olyWar.getTeam(p);
									if(t == Team.RED) red++;
									else if(t == Team.BLUE) blue++;
								}
							}
						}if(red > blue){
							if(percentageRed1 > 0) percentageRed1--;
						}else if(red < blue){
							percentageRed1++;
							if(percentageRed1 >= 64){
								setOwner(Team.BLUE, olyWar.point1, 1);
								final int dif = 60 - redTime;
								redTime += dif;
								if(redTime > 60) redTime = 60;
							}
						}
					}//point2
					if(owningTeam2 == Team.RED && owningTeam1 == Team.BLUE){
						int red = 0;
						int blue = 0;
						for(final Entity e : olyWar.point2.getEntities()){
							if(e instanceof Player){
								final Player p = (Player) e;
								final Material m = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
								if(m == Material.WOOL || m == Material.NETHERRACK || m == Material.LAPIS_BLOCK){
									final Team t = olyWar.getTeam(p);
									if(t == Team.RED) red++;
									else if(t == Team.BLUE) blue++;
								}
							}
						}if(red > blue){
							if(percentageRed2 > 0) percentageRed2--;
						}else if(red < blue){
							percentageRed2++;
							if(percentageRed2 >= 64){
								setOwner(Team.BLUE, olyWar.point2, 2);
							}
						}
					}
					for(final Player p : Bukkit.getOnlinePlayers()){
						final Inventory i = p.getInventory();
						if(owningTeam1 == Team.RED) i.setItem(6, new ItemStack(Material.NETHERRACK, percentageRed1));
						else if(owningTeam1 == Team.BLUE) i.setItem(6, new ItemStack(Material.LAPIS_BLOCK, percentageRed1));
						if(owningTeam2 == Team.RED) i.setItem(7, new ItemStack(Material.NETHERRACK, percentageRed2));
						else if(owningTeam2 == Team.BLUE) i.setItem(7, new ItemStack(Material.LAPIS_BLOCK, percentageRed2));
						if(setup) i.setItem(8, new ItemStack(Material.LAPIS_BLOCK, setupTime));
						else i.setItem(8, new ItemStack(Material.NETHERRACK, redTime));
					}
				}
			}
		}, 0L, 3L);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType.equals("Attack/Defense")){
					if(redTime > 0){
						redTime--;
						if(redTime == 0) gameOver(Team.RED);
					}else gameOver(Team.RED);
				}
			}
		}, 20*30L, 60L);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType.equals("Attack/Defense") && setup){
					if(setupTime >= 0){
						setupTime--;
						if(setupTime <= 0){
							setup = false;
							setupTime = 30;
							s.broadcastMessage(olyWar.map + "Setup is now over! " + ChatColor.BLUE + "Team Blue " + ChatColor.GOLD + "may now leave their spawn.");
						}
					}
				}
			}
		}, 20L, 20L);
	}

	public static void gameOver(final Team win){
		started = false;
		s.broadcastMessage(olyWar.map + "And that's the game!");
		final Team opp = win.getOpposite();
		s.broadcastMessage(olyWar.map + win.getColor() + "Team " + win.getName() + ChatColor.GOLD + " defeated " + opp.getColor() + "Team " + opp.getName());
		olyWar.gameIsActive = false;

		percentageRed1 = 0;
		percentageRed2 = 0;
		redTime = 60;
		setOwner(Team.RED, olyWar.point1, 1);
		setOwner(Team.RED, olyWar.point2, 2);
		Runtime.startGame(ow);
	}

	private static void setOwner(final Team t, final Chunk c, final int chunkNumber){
		if(chunkNumber == 1) owningTeam1 = t;
		else owningTeam2 = t;
		if(t == Team.BLUE){
			final String time = ChatColor.YELLOW + "" + redTime*3/60 + "m " + redTime*3%60 + "s ";
			Bukkit.getServer().broadcastMessage(olyWar.map + ChatColor.BLUE + "Team Blue " + ChatColor.GOLD + "has taken " + ChatColor.YELLOW + "Point " + chunkNumber + " " + ChatColor.GOLD +
					"with " + time + ChatColor.GOLD + "remaining");
		}for(int x = 0; x <= 16; x++){
			for(int y = 0; y <= 128; y++){
				for(int z = 0; z <= 16; z++){
					final Block b = c.getBlock(x,y,z);
					final Material m = b.getType();
					if(m == Material.WOOL || m == Material.NETHERRACK || m == Material.LAPIS_BLOCK){
						if(b.getRelative(BlockFace.UP).getType() != Material.IRON_FENCE && b.getRelative(BlockFace.DOWN).getType() != Material.IRON_FENCE){
							if(t == Team.RED) b.setType(Material.NETHERRACK);
							if(t == Team.BLUE) b.setType(Material.LAPIS_BLOCK);
							if(t == Team.NONE) b.setType(Material.WOOL);
						}
					}
				}
			}
		}
	}
}