package com.olympuspvp.teamolympus.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.TeamPref;
import com.olympuspvp.teamolympus.type.ClassType;

public class Manager {

	public static void run(final CommandSender s, final String cl, final String[] args, olyWar ow){

		if(s instanceof Player){
			Player p = (Player) s;
			if(cl.equalsIgnoreCase("leave")){
				Leave.run(p, ow);
			}
			if(cl.equalsIgnoreCase("red")){
				olyWar.setPreference(p, TeamPref.RED);
				p.sendMessage(olyWar.getLogo() + "You preferred team is now" + ChatColor.RED + " Team Red");
			}
			if(cl.equalsIgnoreCase("blue")){
				olyWar.setPreference(p, TeamPref.BLUE);
				p.sendMessage(olyWar.getLogo() + "You preferred team is now" + ChatColor.BLUE + " Team Blue");
			}
			if(cl.equalsIgnoreCase("random")){
				olyWar.setPreference(p, TeamPref.RANDOM);
				p.sendMessage(olyWar.getLogo() + "You team will now be randomly assigned at the start of each game");
			}
			if(cl.equalsIgnoreCase("classes")){
				StringBuilder sb = new StringBuilder();
				boolean darkgray = false;
				int amount = 0;
				for(ClassType ct : ClassType.values()){
					darkgray = !darkgray;
					String add = "";
					if(amount == 3){
						amount = 0;
						add = "\n";
					}if(darkgray) sb.append(ChatColor.DARK_GRAY + ct.getName() + add);
					if(!darkgray) sb.append(ChatColor.GRAY + ct.getName() + add);
					amount++;
				}s.sendMessage(ChatColor.GREEN + "Classes:\n" + sb.toString());
			}
			if(cl.equalsIgnoreCase("vote")){
				Vote.run(p, args);
			}
			if(cl.equalsIgnoreCase("buy")){
			}
			if(cl.equalsIgnoreCase("equip")){
			}
			if(cl.equalsIgnoreCase("title")){
			}
			if(cl.equalsIgnoreCase("class")){
				ChooseClass.run(p, args);
			}
			
		}else{
			s.sendMessage(ChatColor.GRAY + "You must be in-game to perform commands related to this plugin.");
		}
	}
}
