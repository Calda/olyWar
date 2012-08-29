package com.olympuspvp.teamolympus.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;


public class AutoBalance{
	static Server s = Bukkit.getServer();
	static Random r = new Random();

	public static void run(final boolean announce){
		if(Bukkit.getOnlinePlayers().length != 1){
			if(olyWar.gameIsActive){
				final List<String> redNames = new ArrayList<String>();
				final List<String> blueNames = new ArrayList<String>();
				for(final Player plr : s.getOnlinePlayers()){
					final Team team = olyWar.getTeam(plr);
					if(team == Team.RED) redNames.add(olyWar.getName(plr));
					else if(team == Team.BLUE) blueNames.add(olyWar.getName(plr));
				}

				int difference;
				Team higher = null;

				if(redNames.size() > blueNames.size()){
					difference = redNames.size() - blueNames.size();
					higher = Team.RED;
				}else if(redNames.size() < blueNames.size()){
					difference = blueNames.size() - redNames.size();
					higher = Team.BLUE;
				}else difference = 0;

				if(difference != 0){
					Collections.shuffle(redNames);
					Collections.shuffle(blueNames);
					final int changeOver = difference/2;
					int moveIndex;
					if(higher == Team.RED){
						moveIndex = r.nextInt(redNames.size()-difference-1);
						for(int i = 0; i <= changeOver; i++){
							String toMove = null;
							try{
								toMove = redNames.get(moveIndex);
								final Player p = s.getPlayer(toMove);
								if(p == null){
									throw new NullPointerException();
								}else{
									olyWar.setTeam(p,Team.BLUE);
									p.sendMessage(olyWar.getLogo() + "You are new a member of Team " + ChatColor.BLUE + "Blue");
									p.teleport(olyWar.blueSpawn);
									olyWar.redPlayersAlive--;
									olyWar.bluePlayersAlive++;
								}
							}catch(final ArrayIndexOutOfBoundsException e){
								e.printStackTrace();
								System.out.println("Math Error???");
							}catch(final NullPointerException e){
								e.printStackTrace();
								System.out.println("That player is no longer on the server... :(");
							}
						}
					}else if(higher == Team.BLUE){
						moveIndex = r.nextInt(blueNames.size()-difference-1);
						for(int i = 0; i <= changeOver; i++){
							String toMove = null;
							try{
								toMove = blueNames.get(moveIndex);
								final Player p = s.getPlayer(toMove);
								if(p == null){
									throw new NullPointerException();
								}else{
									olyWar.setTeam(p,Team.RED);
									p.sendMessage(olyWar.getLogo() + "You are new a member of Team " + ChatColor.RED + "Red");
									p.teleport(olyWar.redSpawn);
									olyWar.redPlayersAlive++;
									olyWar.bluePlayersAlive--;
								}
							}catch(final ArrayIndexOutOfBoundsException e){
								e.printStackTrace();
								System.out.println("Math Error???");
							}catch(final NullPointerException e){
								e.printStackTrace();
								System.out.println("That player is no longer on the server... :(");
							}
						}
					}
					Bukkit.getServer().broadcastMessage(olyWar.getLogo() + "Teams have been autobalanced.");
				}
			}
		}
	}
}
