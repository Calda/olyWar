package com.olympuspvp.teamolympus.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.type.ClassType;

public class ChooseClass {

	public static void run(Player p, String[] args){

		if(args.length != 1){
			p.sendMessage(ChatColor.GRAY + "Incorrect Command Usage. /class [classType]");
			p.sendMessage(ChatColor.GRAY + "Type /classes for a list of avaliable classes!");
		}else{
			String type = null;
			if(args[0].equalsIgnoreCase("archer")){
				olyWar.setClass(p, ClassType.ARCHER); 
				type = "Archer";
			}else if(args[0].equalsIgnoreCase("assassin")){
				olyWar.setClass(p, ClassType.ASSASSIN);
				type = "Assassin";
			}else if(args[0].equalsIgnoreCase("berserker")){
				olyWar.setClass(p, ClassType.BERSERKER);
				type = "Berserker";
			}else if(args[0].equalsIgnoreCase("cleric")){
				olyWar.setClass(p, ClassType.CLERIC);
				type = "Cleric";
			}else if(args[0].equalsIgnoreCase("huntsman")){
				olyWar.setClass(p, ClassType.HUNTSMAN);
				type = "Huntsman";
			}else if(args[0].equalsIgnoreCase("ranger")){
				olyWar.setClass(p, ClassType.RANGER); 
				type = "Ranger";
			}else if(args[0].equalsIgnoreCase("mage")){
				olyWar.setClass(p, ClassType.MAGE); 
				type = "Mage";
			}else if(args[0].equalsIgnoreCase("sorcerer")){
				olyWar.setClass(p, ClassType.SORCERER); 
				type = "Sorcerer";
			}else if(args[0].equalsIgnoreCase("infiltrator")){
				olyWar.setClass(p, ClassType.INFILTRATOR); 
				type = "Infiltrator";
			}else if(args[0].equalsIgnoreCase("warrior")){
				olyWar.setClass(p, ClassType.WARRIOR); 
				type = "Warrior";
			}else if(args[0].equalsIgnoreCase("paladin")){
				olyWar.setClass(p, ClassType.PALADIN); 
				type = "Paladin";
			}else if(args[0].equalsIgnoreCase("rouge")){
				olyWar.setClass(p, ClassType.ROUGE); 
				type = "Rouge";
			}

			if(type != null){
				p.sendMessage(olyWar.getLogo() + "Your selected class is now " + ChatColor.YELLOW + type);
				if(olyWar.getTeam(p) != null){
					p.sendMessage(olyWar.getLogo() + "Your class will be changed next time you spawn.");
				}
			}else{
				p.sendMessage(ChatColor.GRAY + "Incorrect Command Usage. /class [classType]");
				p.sendMessage(ChatColor.GRAY + "/classes for a list of avaliable classes");
			}
		}

	}

}
