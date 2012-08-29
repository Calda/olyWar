package com.olympuspvp.teamolympus.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.game.Runtime;

public class ChooseMap {

	public static String[] maps;

	public static void loadMapNames(){
		maps = new String[WarConfig.getNumberOfMaps()];
		for(int i = 0; i < WarConfig.getNumberOfMaps(); i++){
			maps[i] = WarConfig.getMapName(i+1);
		}
	}

	public static void chooseMap(final Player p, final String[] args){
		if(!p.isOp()) p.sendMessage(ChatColor.GRAY + "You must be an OP to select a map.");
		else{
			if(args.length == 0) p.sendMessage(ChatColor.GRAY + "Incorrect Command Usage: /map [mapName]\n/maps for a list of maps");
			else{
				final StringBuilder sb = new StringBuilder();
				for(int i = 0; i < args.length; i++){
					System.out.println(i + " " + args[i]);
					if(i == args.length - 1) sb.append(args[i]);
					else sb.append(args[i] + " ");
				}final String mapName = sb.toString();
				int chosenMap = 0;
				boolean found = false;
				for(final String str : maps){
					chosenMap++;
					if(str.equalsIgnoreCase(mapName)){
						p.sendMessage(olyWar.map + "You have chosen "+ ChatColor.DARK_GREEN + str + ChatColor.GOLD + " as the next map!");
						p.sendMessage(olyWar.map + "It will began once the current game ends.");
						found = true;
						break;
					}
				}if(found){
					Runtime.adminChosen = true;
					Runtime.adminChosenMap = chosenMap;
				}else{
					p.sendMessage(ChatColor.GRAY + "That map does not exist. /maps for a list of maps");
				}
			}
		}
	}

	public static void listMaps(final Player p){
		final StringBuilder sb = new StringBuilder();
		boolean darkgray = false;
		int amount = 0;
		for(final String s : maps){
			darkgray = !darkgray;
			String add = " ";
			if(amount == 3){
				amount = 0;
				add = "\n";
			}if(darkgray) sb.append(ChatColor.DARK_GRAY + s + add);
			if(!darkgray) sb.append(ChatColor.GRAY + s + add);
			amount++;
		}p.sendMessage(ChatColor.GREEN + "Maps in Cycle:\n" + sb.toString());
	}

}
