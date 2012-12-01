package com.olympuspvp.teamolympus.game;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.command.Vote;
import com.olympuspvp.teamolympus.configuration.WarConfig;

public class Runtime{
	final static Server s = Bukkit.getServer();
	public static boolean adminChosen = false;
	public static int adminChosenMap = -1;
	private static String map = olyWar.map;

	public static void startGame(final olyWar ow){
		final Random r = new Random();
		final int numberOfMaps = WarConfig.getNumberOfMaps();
		int chosenMap;
		String mapName;
		String mapType;

		boolean brokenMap = false;
		int loops = 0;
		do{
			if(!adminChosen){
				chosenMap = r.nextInt(numberOfMaps);
				chosenMap++;
			}else{
				adminChosen = false;
				chosenMap = adminChosenMap;
			}final Location redSpawn = WarConfig.getRedSpawn(chosenMap);
			final Location blueSpawn = WarConfig.getBlueSpawn(chosenMap);
			mapName = WarConfig.getMapName(chosenMap);
			mapType = WarConfig.getMapType(chosenMap);
			if(mapName == null || mapType == null || blueSpawn == null || redSpawn == null){
				brokenMap = true;
				System.out.println("Map number " + chosenMap + " is broken. Finding a different map.");
				if(loops > 20){
					final String tag = ChatColor.BLACK + "[" + ChatColor.DARK_RED + "!" + ChatColor.BLACK + "] ";
					Bukkit.getServer().broadcastMessage(tag + ChatColor.DARK_RED + "The map selecting system is currently broken.");
					Bukkit.getServer().broadcastMessage(tag + ChatColor.DARK_RED + "The server requires developer action.");
				}
			}else{
				olyWar.redSpawn = redSpawn;
				olyWar.blueSpawn = blueSpawn;
				olyWar.mapName = mapName;
				olyWar.mapType = mapType;
			}
			if(mapType == null) mapType = "";
			if(mapType.equals("Attack/Defense") || mapType.equalsIgnoreCase("KOTH")){
				olyWar.point1 = WarConfig.getChunk1(chosenMap);
				olyWar.point2 = WarConfig.getChunk2(chosenMap);
				if(olyWar.point1 == null) brokenMap = true;
				else if(olyWar.point2 == null && mapType.equals("Attack/Defense")) brokenMap = true;
			}else if(mapType.equalsIgnoreCase("Free For All")){
				olyWar.freeForAllSpawns = WarConfig.getSpawn(chosenMap);
			}

			loops++;
		}while(brokenMap);

		if(!brokenMap){
			final String mt = mapType;
			final String mn = mapName;
			Vote.openVote();
			s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mapType + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mapName + ChatColor.GOLD + " in 30 seconds.");
			s.broadcastMessage(map + "Use the command" + ChatColor.YELLOW + " /vote [yes/no/results] " + ChatColor.GOLD + "to vote!");
			s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 20 seconds.");
			s.broadcastMessage(map + "Use the command" + ChatColor.YELLOW + " /vote [yes/no/results] " + ChatColor.GOLD + "to vote!");}}, 10*20L);
			s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 10 seconds.");
			s.broadcastMessage(map + "Use the command" + ChatColor.YELLOW + " /vote [yes/no/results] " + ChatColor.GOLD + "to vote!");}}, 20*20L);
			s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 5 seconds.");
			s.broadcastMessage(map + "Use the command" + ChatColor.YELLOW + " /vote [yes/no/results] " + ChatColor.GOLD + "to vote!");}}, 25*20L);
			s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){
				@Override
				public void run(){
					if(Vote.getVerdict()){
						s.broadcastMessage(map + "Map is now " + ChatColor.GREEN + mt + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mn);

						for(final Player p : s.getOnlinePlayers()){
							olyWar.loadTeamFromPreference(p);
						}

						AutoBalance.run(false);

						for(final Player p : s.getOnlinePlayers()){
							olyWar.spawnPlayer(p);
							olyWar.applyClass(p);
						}

						olyWar.gameIsActive = true;

					}else{
						startGame(ow);
					}
				}
			}, 30*20L);
		}
	}

	public static void gameOverTDM(final olyWar ow){

		olyWar.gameIsActive = false;

		final int redAlive = olyWar.redPlayersAlive;
		final int blueAlive = olyWar.bluePlayersAlive;

		s.broadcastMessage(map + "And that's the game!");
		s.broadcastMessage(map + "Final score is " + ChatColor.RED + redAlive + ChatColor.GOLD + ":"+ ChatColor.BLUE + blueAlive);
		String winner;
		String loser;
		if(blueAlive > redAlive){
			winner = ChatColor.BLUE + "Blue";
			loser = ChatColor.RED + "Red";
		}else{
			loser = ChatColor.BLUE + "Blue";
			winner = ChatColor.RED + "Red";
		}s.broadcastMessage(map + "Team " + winner + ChatColor.GOLD + " defeated Team " + loser);
		olyWar.gameIsActive = false;
		startGame(ow);
	}
}
