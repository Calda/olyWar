package com.olympuspvp.teamolympus.damage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.type.ClassType;

public class RespawnListener implements Listener{

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e){
		final Player p = e.getPlayer();
		if(olyWar.gameIsActive){
			int lives = olyWar.getLives(p);
			if(olyWar.mapType != "TDM") lives = 1;
			if(lives > 0){

				final Team t = olyWar.getTeam(p);
				final ClassType ct = olyWar.getClass(p);
				if(t == Team.RED){
					//olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.RED + olyWar.getName(p));
					e.setRespawnLocation(olyWar.redSpawn);
					p.teleport(olyWar.redSpawn);
					p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK));
				}if(t == Team.BLUE){
					//olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.BLUE + olyWar.getName(p));
					e.setRespawnLocation(olyWar.blueSpawn);
					p.teleport(olyWar.redSpawn);
					p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
				}if(t != Team.NONE){
					p.sendMessage(olyWar.map + "You have respawned in " + ChatColor.GREEN + olyWar.mapType + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + olyWar.mapName + ChatColor.GOLD + " as " + ct.getArticle() + " " + t.getColor() + ct.getName());
				}

				olyWar.applyClass(p);

			}else{
				e.setRespawnLocation(olyWar.spawn);
				p.teleport(olyWar.spawn);
				olyWar.setPlayerName(((CraftPlayer)p).getHandle(),olyWar.getName(p));
			}
		}else{
			e.setRespawnLocation(olyWar.spawn);
			p.teleport(olyWar.spawn);
			olyWar.setPlayerName(((CraftPlayer)p).getHandle(),olyWar.getName(p));
		}
	}

	@EventHandler
	public void onTeleport(final PlayerTeleportEvent event){
		final Player player = event.getPlayer();
		for (final Player p : Bukkit.getOnlinePlayers()){
			if (p.canSee(player)) p.showPlayer(player);
		}
	}

}
