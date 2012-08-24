package com.olympuspvp.teamolympus.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Runtime;
import com.olympuspvp.teamolympus.game.Team;
import org.bukkit.block.*;

public class KOTHbeat {

	public static int percentageRed = 0;
	public static Team owningTeam = Team.NONE;
	public static int redTime = 60;
	public static int blueTime = 60;
	public static Server s = Bukkit.getServer();
	private static olyWar ow = null;
	private static boolean started = false;

	public static void start(final olyWar olyw){
		ow = olyw;
		percentageRed = 0;
		redTime = 60;
		blueTime = 60;
		started = false;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType.equals("KOTH")){
					if(!started){
						started = true;
						setOwner(Team.NONE);
					}int red = 0;
					int blue = 0;
					for(final Entity e : olyWar.point1.getEntities()){
						if(e instanceof Player){
							final Player p = (Player) e;
							Material m = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
							if(m == Material.WOOL || m == Material.NETHERRACK || m == Material.LAPIS_BLOCK){
								final Team t = olyWar.getTeam(p);
								if(t == Team.RED) red++;
								else if(t == Team.BLUE) blue++;
							}
						}
					}if(red > blue){
						if(percentageRed < 64 && owningTeam != Team.RED) percentageRed++;
						else if(percentageRed == 64) setOwner(Team.RED);
					}else if(blue > red){
						if(percentageRed > -64 && owningTeam != Team.BLUE) percentageRed--;
						else if(percentageRed == -64) setOwner(Team.BLUE);
					}
					for(final Player p : Bukkit.getOnlinePlayers()){
							final Inventory i = p.getInventory();
							if(percentageRed == 0 && owningTeam == Team.NONE) i.setItem(8, new ItemStack(Material.WOOL));
							else{
								int percent = percentageRed;
								if(percent == 0){
									if(owningTeam == Team.RED) percent = -1;
									else percent = 1;
								}if(owningTeam == Team.RED) i.setItem(8, new ItemStack(Material.LAPIS_BLOCK, -percent));
								else if(owningTeam == Team.BLUE) i.setItem(8, new ItemStack(Material.NETHERRACK, percent));
								else{
									if(percentageRed > 0) i.setItem(8, new ItemStack(Material.NETHERRACK, percentageRed));
									else i.setItem(8, new ItemStack(Material.LAPIS_BLOCK, -percentageRed));
								}
							}//p.updateInventory();
					}
				}
			}
		}, 0L, 3L);

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType.equals("KOTH")){
					if(owningTeam == Team.RED) redTime--;
					else if(owningTeam == Team.BLUE) blueTime--;

					if(redTime == 0){
						if(percentageRed >= 0 && owningTeam == Team.RED) gameOver();
					}

					if(blueTime == 0){
						if(percentageRed <= 0 && owningTeam == Team.BLUE) gameOver();
					}

					for(final Player p : Bukkit.getOnlinePlayers()){
						if(olyWar.getTeam(p) != Team.NONE){
							final Inventory i = p.getInventory();
							i.setItem(7, new ItemStack(Material.NETHERRACK, redTime));
							i.setItem(6, new ItemStack(Material.LAPIS_BLOCK, blueTime));
							//p.updateInventory();
						}
					}
				}
			}
		}, 20*30L, 60L);

	}

	public static void gameOver(){
		started = false;
		s.broadcastMessage(olyWar.map + "And that's the game!");
		final Team opp = owningTeam.getOpposite();
		s.broadcastMessage(olyWar.map + owningTeam.getColor() + " Team " + owningTeam.getName() + ChatColor.GOLD + "defeated" + opp.getColor() + " Team " + opp.getName());
		olyWar.gameIsActive = false;

		percentageRed = 0;
		redTime = 64;
		blueTime = 64;
		setOwner(Team.NONE);
		Runtime.startGame(ow);	
	}

	private static void setOwner(final Team t){
		if(t == Team.RED) Bukkit.getServer().broadcastMessage(olyWar.map + ChatColor.RED + "Team Red " + ChatColor.GOLD + 
				"took the point and has " + ChatColor.YELLOW + redTime*3 + " seconds " + ChatColor.GOLD + "remaining.");
		if(t == Team.BLUE) Bukkit.getServer().broadcastMessage(olyWar.map + ChatColor.BLUE + "Team Blue " + ChatColor.GOLD + 
				"took the point and has " + ChatColor.YELLOW + blueTime*3 + " seconds " + ChatColor.GOLD + "remaining.");
		owningTeam = t;
		percentageRed = 0;
		for(int x = 0; x <= 16; x++){
			for(int y = 0; y <= 128; y++){
				for(int z = 0; z <= 16; z++){
					final Block b = olyWar.point1.getBlock(x,y,z);
					final Material m = b.getType();
					if(m == Material.WOOL || m == Material.NETHERRACK || m == Material.LAPIS_BLOCK){
						if(t == Team.RED) b.setType(Material.NETHERRACK);
						if(t == Team.BLUE) b.setType(Material.LAPIS_BLOCK);
						if(t == Team.NONE) b.setType(Material.WOOL);
					}
				}
			}
		}
	}
}