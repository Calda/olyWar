package com.olympuspvp.teamolympus.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.olympuspvp.teamolympus.olyWar;

public class LoginListener implements Listener{
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		p.teleport(olyWar.spawn, TeleportCause.PLUGIN);
		if(p.hasPlayedBefore()){
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "Welcome back to Realms PVP");
			try{p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "So far, you have made " + ChatColor.YELLOW + WarConfig.getTotalKills(p) + ChatColor.GOLD + " kills!");
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "How many will you get today?");}
			catch(Exception e1){
				p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "Unable to fine your number of kills.");
			}
		}else{
			Bukkit.getServer().broadcastMessage(olyWar.getLogo() + ChatColor.BLUE + p.getName() + ChatColor.DARK_AQUA + " has joined for the first time!");
		}
	}

}
