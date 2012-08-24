package com.olympuspvp.teamolympus.configuration;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.olympuspvp.teamolympus.olyWar;

public class LogoutListener implements Listener{
	olyWar ow;
	public LogoutListener(olyWar olyw){
		this.ow = olyw;
	}
	
	@EventHandler
	public void onPlayerLogin(final PlayerQuitEvent e){
		e.setQuitMessage(null);
		olyWar.leaveClass(e.getPlayer());
		olyWar.leaveTeam(e.getPlayer());
	}

}
