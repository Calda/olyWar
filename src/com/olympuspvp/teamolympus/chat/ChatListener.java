package com.olympuspvp.teamolympus.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.configuration.WarConfig;

public class ChatListener implements Listener{
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e){
		
		Player p = e.getPlayer();
		String name = olyWar.getName(p);
		String sepColor = ChatColor.DARK_GRAY + "";
		String chatColor = ChatColor.WHITE + "";
		if(!name.contains("¤")){
			if(name.contains("CalDaBeast")){
				name = ChatColor.LIGHT_PURPLE + "Cal" + ChatColor.DARK_PURPLE + "Da" + ChatColor.LIGHT_PURPLE + "Beast";
				sepColor = ChatColor.DARK_PURPLE + "";
				chatColor = ChatColor.AQUA + "";
			}else if(name.contains("kevinbo")){
				name = ChatColor.DARK_RED + "kevinbo";
				sepColor = ChatColor.RED + "";
				chatColor = ChatColor.DARK_GREEN + "";
			}else if(name.contains("AeroEveryDay")){
				name = ChatColor.BLUE + "AeroEveryDay";
				sepColor = ChatColor.AQUA + "";
				chatColor = ChatColor.GOLD + "";
			}else if(name.contains("Spimpy")){
				name = ChatColor.DARK_GRAY + "Spimpy";
				sepColor = ChatColor.GRAY + "";
				chatColor = ChatColor.DARK_AQUA + "";
			}else if(name.contains("Medious")){
				name = ChatColor.DARK_AQUA + "Medious";
				sepColor = ChatColor.AQUA + "";
				chatColor = ChatColor.GREEN + "";
			}else if(name.contains("willno123")){
				name = ChatColor.DARK_GRAY + "willno" + ChatColor.WHITE + "1" + ChatColor.GRAY + "2" + ChatColor.DARK_GRAY + "3";
				sepColor = ChatColor.BLACK + "";
				chatColor = ChatColor.BLUE + "";
			}
		}
		String tag = WarConfig.getTagPreference(p);
		if(p.isOp()){
			if(name.contains("Cal") || name.contains("Aero")) tag = Tag.DEV.toTag();
			else tag = Tag.ADMIN.toTag();
		}else if(p.hasPermission("original")) tag = Tag.ORIGINAL.toTag();
		else if(p.hasPermission("donor")) tag = Tag.DONOR.toTag();
		
		String message = tag + name + sepColor + ": " + chatColor + e.getMessage();
		e.setFormat(message);
	}
	
}
