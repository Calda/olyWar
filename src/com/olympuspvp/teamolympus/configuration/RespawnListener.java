package com.olympuspvp.teamolympus.configuration;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.olympuspvp.teamolympus.olyWar;

public class RespawnListener implements Listener{
	
	@EventHandler
	public void onPlayerLogin(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		p.teleport(olyWar.spawn, TeleportCause.PLUGIN);
	}

}
