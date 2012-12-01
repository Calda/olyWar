package com.olympuspvp.teamolympus.command;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;

public class Vote {
	private static boolean voteInProgress = false;
	private static int yes = 0;
	private static int no = 0;
	private static HashMap<String, Boolean> votes = new HashMap<String, Boolean>();

	public static void run(final Player p, final String[] args){
		if(voteInProgress){
			if(args.length != 1){
				p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
			}else{
				if(args[0].contains("y")){
					if(!votes.containsKey(olyWar.getName(p))){
						yes++;
						votes.put(olyWar.getName(p), true);
						p.sendMessage(olyWar.map + "You have voted " + ChatColor.GREEN + "yes " + ChatColor.GOLD + "for the next map");
						p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
					}else{
						if(votes.get(olyWar.getName(p))){
							p.sendMessage(ChatColor.GRAY + "You may not vote multiple times.");
						}else{
							no--;
							yes++;
						}
					}
				}else if(args[0].contains("n")){
					if(!votes.containsKey(olyWar.getName(p))){
						no++;
						votes.put(olyWar.getName(p), false);
						p.sendMessage(olyWar.map + "You have voted " + ChatColor.RED + "no " + ChatColor.GOLD + "for the next map");
						p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
					}else{
						if(!votes.get(olyWar.getName(p))){
							p.sendMessage(ChatColor.GRAY + "You may not vote multiple times.");
						}else{
							no++;
							yes--;
						}
					}
				}else if(args[0].equalsIgnoreCase("results")){
					p.sendMessage(olyWar.map + "Current results are " + ChatColor.GREEN + yes + " yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " no");
				}else p.sendMessage(ChatColor.GRAY + "Incorrect Usage: /vote [yes/no/results]");
			}
		}else{
			p.sendMessage(ChatColor.GRAY + "There is no vote in progress.");
		}
	}

	public static void openVote(){
		votes.clear();
		voteInProgress = true;
		yes = 0;
		no = 0;
	}

	public static boolean getVerdict(){
		voteInProgress = false;
		votes.clear();
		Bukkit.getServer().broadcastMessage(olyWar.map + "Voting results are " + ChatColor.GREEN + yes + " Yes" + ChatColor.GOLD + "/" + ChatColor.RED + no + " No");
		if(yes >= no) return true;
		else return false;
	}

}
