package com.olympuspvp.teamolympus.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Runtime;

public class Vote {
	private static boolean voteInProgress = false;
	private static int yes = 0;
	private static int no = 0;
	private static List<String> voted = new ArrayList<String>();

	public static void run(Player p, String[] args){
		if(voteInProgress){
			if(args.length != 1){
				p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
			}else{
				if(args[0].contains("y")){
					if(!voted.contains(olyWar.getName(p))){
						yes++;
						voted.add(olyWar.getName(p));
						p.sendMessage(olyWar.map + "You have voted " + ChatColor.GREEN + "yes " + ChatColor.GOLD + "for the next map");
						p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
					}else p.sendMessage(ChatColor.GRAY + "You may not vote multiple times.");
				}else if(args[0].contains("n")){
					if(!voted.contains(olyWar.getName(p))){
						no++;
						voted.add(olyWar.getName(p));
						p.sendMessage(olyWar.map + "You have voted " + ChatColor.RED + "no " + ChatColor.GOLD + "for the next map");
						p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
					}else p.sendMessage(ChatColor.GRAY + "You may not vote multiple times.");
				}else if(args[0].equalsIgnoreCase("results")){
					p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
				}else p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
			}
		}else{
			p.sendMessage(ChatColor.GRAY + "There is no vote in progress.");
		}
	}

	public static void openVote(){
		voteInProgress = true;
		yes = 0;
		no = 0;
	}

	public static boolean getVerdict(){
		voteInProgress = false;
		Bukkit.getServer().broadcastMessage(Runtime.map + "Voting results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
		if(yes >= no) return true;
		else return false;
	}

}
