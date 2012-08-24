package com.olympuspvp.teamolympus.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;

import com.olympuspvp.teamolympus.olyWar;

public class LoginListener implements Listener{

	@EventHandler
	public void onPlayerLogin(final PlayerJoinEvent e){
		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		for(final PotionEffect pe : p.getActivePotionEffects()){
			p.removePotionEffect(pe.getType());
		}p.getInventory().clear();
		p.teleport(olyWar.spawn, TeleportCause.PLUGIN);
		if(p.hasPlayedBefore()){
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "Welcome back to Realms PVP");
			try{p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "So far, you have earned " + ChatColor.YELLOW + WarConfig.getScore(p) + ChatColor.GOLD + " points!");
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "How many will you make today?");}
			catch(final Exception e1){
				p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "Unable to find your number of kills... :(");
			}
		}else{
			Bukkit.getServer().broadcastMessage(olyWar.getLogo() + ChatColor.BLUE + p.getName() + ChatColor.DARK_AQUA + " has joined for the first time!");
		}
	}

}
