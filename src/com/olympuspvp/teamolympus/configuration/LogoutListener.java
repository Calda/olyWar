package com.olympuspvp.teamolympus.configuration;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.olympuspvp.teamolympus.olyWar;

public class LogoutListener implements Listener{

	@EventHandler
	public void onPlayerLogin(final PlayerQuitEvent e){
		e.setQuitMessage(null);
		final Player p = e.getPlayer();
		olyWar.leaveClass(p);
		olyWar.leaveTeam(p);
		olyWar.die(p);
		p.getInventory().setContents(null);
	}

}
