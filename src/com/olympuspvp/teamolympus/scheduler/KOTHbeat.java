package com.olympuspvp.teamolympus.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class KOTHbeat {

	public static int percentageRed = 0;
	public static Team owningTeam = Team.NONE;
	public static int redTime = 64;
	public static int blueTime = 64;
	public static boolean overtime = false;
	
	
	public static void start(olyWar ow){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType == "KOTH"){
					int red = 0;
					int blue = 0;
					for(Entity e : olyWar.point1.getEntities()){
						if(e instanceof Player){
							Player p = (Player) e;
							Team t = olyWar.getTeam(p);
							if(t == Team.RED) red++;
							else if(t == Team.BLUE) blue++;
						}
					}if(red > blue){
						int max = 0;
						if(owningTeam == Team.RED) max = 64;
						if(percentageRed < max) percentageRed++;
						else if(max == -64) setOwner(Team.RED);
					}else if(blue > red){
						int max = 0;
						if(owningTeam == Team.BLUE) max = -64;
						if(percentageRed > max) percentageRed--;
						else if(max == -64) setOwner(Team.BLUE);
					}
				}
			}
		}, 20*30L, 5L);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
			@Override
			public void run(){
				if(olyWar.gameIsActive && olyWar.mapType == "KOTH"){
					if(owningTeam == Team.RED) redTime--;
					else if(owningTeam == Team.BLUE) blueTime--;
					
					if(redTime == 0){
						if
					}
					
				}
			}
		}, 60L, 60L);
	}
	
	private static void setOwner(Team t){
		owningTeam = t;
		percentageRed = 0;
		for(int x = 0; x > 16; x++){
			for(int y = 0; y >= 128; y++){
				for(int z = 0; z > 16; z++){
					Block b = olyWar.point1.getBlock(x,y,z);
					if(b.getType() == Material.WOOL){
						b.setData(t.getWoolData());
					}
				}
			}
		}
	}
}
