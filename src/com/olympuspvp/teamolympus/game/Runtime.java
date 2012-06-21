package com.olympuspvp.teamolympus.game;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.configuration.WarConfig;

public class Runtime{

	public static void startGame(final olyWar ow){
		final Random r = new Random();
		final String map = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "MAP" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD;
		final int numberOfMaps = WarConfig.getNumberOfMaps();
		int chosenMap;
		Location redSpawn;
		Location blueSpawn;
		String mapName;
		String mapType;
		final Server s = Bukkit.getServer();

		while(true){

			boolean brokenMap = false;
			do{
				chosenMap = r.nextInt(numberOfMaps + 1);
				redSpawn = WarConfig.getRedSpawn(chosenMap);
				blueSpawn = WarConfig.getBlueSpawn(chosenMap);
				mapName = WarConfig.getMapName(chosenMap);
				mapType = WarConfig.getMapType(chosenMap);
				if(mapName == null){
					brokenMap = true;
					System.out.println("Map number " + chosenMap + " is broken. Finding a different map.");
				}else brokenMap = false;

			}while(brokenMap);

			if(!brokenMap){
				final String mt = mapType;
				final String mn = mapName;
				final Location red = redSpawn;
				final Location blue = blueSpawn;
				s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mapType + " " + ChatColor.DARK_GREEN + mapName + ChatColor.GOLD + " in 30 seconds.");
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 20 seconds.");}}, 10*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 10 seconds.");}}, 20*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 5 seconds.");}}, 25*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){
					@Override
					public void run(){
						olyWar.gameIsActive = true; //GAME IS ACTIVE\\
						s.broadcastMessage(map + "Map is now " + ChatColor.GREEN + mt + " " + ChatColor.DARK_GREEN + mn);

						for(final Player plr : s.getOnlinePlayers()){
							final TeamPref playerPref = olyWar.getPreference(plr);
							if(playerPref == TeamPref.RED) olyWar.setTeam(plr, Team.RED);
							else if(playerPref == TeamPref.BLUE) olyWar.setTeam(plr, Team.BLUE);
							else if(playerPref == TeamPref.RANDOM){
								final boolean joinRed = r.nextBoolean();
								if(joinRed) olyWar.setTeam(plr, Team.RED);
								else olyWar.setTeam(plr, Team.BLUE);
							}else{} // they do not join a team : in lobby
						}
						
						AutoBalance.run();
						
						for(Player p : s.getOnlinePlayers()){
							if(olyWar.getTeam(p) == Team.RED) p.teleport(red, TeleportCause.PLUGIN);
							if(olyWar.getTeam(p) == Team.BLUE) p.teleport(red, TeleportCause.PLUGIN);
						}
					}
				}, 30*20L);

			}
		}
	}
}
