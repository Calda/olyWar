package com.olympuspvp.teamolympus.configuration;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class RespawnListener implements Listener{

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e){
		final Player p = e.getPlayer();
		if(olyWar.gameIsActive){
			final int lives = olyWar.getLives(p);
			if(lives > 0){
				final Team t = olyWar.getTeam(p);
				if(t == Team.RED) e.setRespawnLocation(olyWar.redSpawn);
				if(t == Team.BLUE) e.setRespawnLocation(olyWar.blueSpawn);
			}else e.setRespawnLocation(olyWar.spawn);
		}else e.setRespawnLocation(olyWar.spawn);
	}
}
