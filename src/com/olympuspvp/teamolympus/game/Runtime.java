package com.olympuspvp.teamolympus.game;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.command.Vote;
import com.olympuspvp.teamolympus.configuration.WarConfig;

public class Runtime{
	final static Server s = Bukkit.getServer();
<<<<<<< HEAD
	public final static String map = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "MAP" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD;

=======
	final static String map = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "MAP" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD;
	
>>>>>>> wat
	public static void startGame(final olyWar ow){
		final Random r = new Random();
		final int numberOfMaps = WarConfig.getNumberOfMaps();
		int chosenMap;
		Location redSpawn;
		Location blueSpawn;
		String mapName;
		String mapType;
<<<<<<< HEAD
		Chunk point1;
		Chunk point2;
=======

		while(true){
>>>>>>> wat

			boolean brokenMap = false;
			do{
				chosenMap = r.nextInt(numberOfMaps);
				chosenMap++;
				redSpawn = WarConfig.getRedSpawn(chosenMap);
				blueSpawn = WarConfig.getBlueSpawn(chosenMap);
				mapName = WarConfig.getMapName(chosenMap);
				mapType = WarConfig.getMapType(chosenMap);
				if(mapName == null || mapType == null || blueSpawn == null || redSpawn == null){
					brokenMap = true;
					System.out.println("Map number " + chosenMap + " is broken. Finding a different map.");
				}else brokenMap = false;
				
				if(mapType.equals("Attack/Defend") || mapType.equalsIgnoreCase("King of the Hill")){
					point1 = WarConfig.getChunk1(chosenMap);
					point2 = WarConfig.getChunk2(chosenMap);
					if(point1 == null) brokenMap = true;
					else if(point2 == null && mapType.equals("Attack/Defend")) brokenMap = true;
				}

			}while(brokenMap);

			if(!brokenMap){
				final String mt = mapType;
				final String mn = mapName;
				final Location red = redSpawn;
				final Location blue = blueSpawn;
				Vote.openVote();
				s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mapType + " " + ChatColor.DARK_GREEN + mapName + ChatColor.GOLD + " in 30 seconds.");
				s.broadcastMessage(map + "Use the command /vote [yes/no/results] to vote!");
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 20 seconds.");
					s.broadcastMessage(map + "Use the command /vote [yes/no/results] to vote!");}}, 10*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 10 seconds.");
					s.broadcastMessage(map + "Use the command /vote [yes/no/results] to vote!");}}, 20*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){@Override public void run(){s.broadcastMessage(map + "Next map is " + ChatColor.GREEN + mt + " on " + ChatColor.DARK_GREEN + mn + ChatColor.GOLD + " in 5 seconds.");
					s.broadcastMessage(map + "Use the command /vote [yes/no/results] to vote!");}}, 25*20L);
				s.getScheduler().scheduleSyncDelayedTask(ow, new Runnable(){
					@Override
					public void run(){
						if(Vote.getVerdict()){
							olyWar.gameIsActive = true; //GAME IS ACTIVE\\
							s.broadcastMessage(map + "Map is now " + ChatColor.GREEN + mt + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + mn);

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

							AutoBalance.run(false);

							for(Player p : s.getOnlinePlayers()){
								if(olyWar.getTeam(p) == Team.RED) p.teleport(red, TeleportCause.PLUGIN);
								if(olyWar.getTeam(p) == Team.BLUE) p.teleport(blue, TeleportCause.PLUGIN);
							}
						}else{
							startGame(ow);
						}
					}
				}, 30*20L);
<<<<<<< HEAD
		}
	}public static void gameOverTDM(olyWar ow){

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
		killAll(ow);
		olyWar.gameIsActive = false;
		Runtime.startGame(ow);
	}

	public static void gameOverAD(){

	}public static void gameOverKOTH(){

	}
	public static void killAll(olyWar ow){
		olyWar.hasLeftGame.clear();
		for(Player p : s.getOnlinePlayers()){
			if(olyWar.getLives(p) != 0){
				olyWar.die(p, ow);
				p.teleport(olyWar.spawn, TeleportCause.PLUGIN);
=======
>>>>>>> wat
			}
		}
	}public static void gameOverTDM(){
		s.broadcastMessage(map +"And that's the game!");
		s.broadcastMessage(map + "Final score is " + ChatColor.RED + olyWar.redPlayersAlive + ChatColor.GOLD + " - " + ChatColor.BLUE + olyWar.bluePlayersAlive);
		if(olyWar.bluePlayersAlive == 0) s.broadcastMessage(map +"Team " + ChatColor.RED + "Red " + ChatColor.GOLD + " defeated the " + ChatColor.BLUE + "Blue" + ChatColor.GOLD + " Team!");
		else s.broadcastMessage(map +"Team " + ChatColor.BLUE + "Blue " + ChatColor.GOLD + " defeated the " + ChatColor.RED + "Red" + ChatColor.GOLD + " Team!");
		
	}
}
