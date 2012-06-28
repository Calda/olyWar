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
				if(voted.contains(olyWar.getName(p))){
					if(args[0].contains("y")){
						yes++;
						voted.add(olyWar.getName(p));
						p.sendMessage(olyWar.getLogo() + "You have voted yes");
					}else if(args[0].contains("n")){
						no++;
						voted.add(olyWar.getName(p));
						p.sendMessage(olyWar.getLogo() + "You have votes no");
					}else if(!args[0].equalsIgnoreCase("results")) p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
				}else{
					p.sendMessage(ChatColor.GRAY + "You may not vote multiple times.");
				}
				if(args[0].equalsIgnoreCase("results")){
					p.sendMessage(olyWar.getLogo() + "Current Results: "+ yes + " Yes/ " + no + " No");
				}else{
					p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
				}
			}

		}else{
			p.sendMessage(olyWar.getLogo() + "There is no vote in progress.");
		}
	}

	public static void openVote(){
		voteInProgress = true;
		yes = 0;
		no = 0;
	}

	public static boolean getVerdict(){
		voteInProgress = false;
		Bukkit.getServer().broadcastMessage(Runtime.map + "Voting results are "+ yes + " Yes/ " + no + " No");
		if(yes >= no) return true;
		else return false;
	}

}
