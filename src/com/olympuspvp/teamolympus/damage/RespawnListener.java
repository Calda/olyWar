package com.olympuspvp.teamolympus.damage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.type.ClassType;

public class RespawnListener implements Listener{

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e){
		final Player p = e.getPlayer();
		if(olyWar.gameIsActive){
			final int lives = olyWar.getLives(p);
			if(lives > 0){
				
				final Team t = olyWar.getTeam(p);
				final ClassType ct = olyWar.getClass(p);
				if(t == Team.RED){
					//olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.RED + olyWar.getName(p));
					e.setRespawnLocation(olyWar.redSpawn);
					p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK));
				}if(t == Team.BLUE){
					//olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.BLUE + olyWar.getName(p));
					e.setRespawnLocation(olyWar.blueSpawn);
					p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
				}if(t != Team.NONE){
					p.sendMessage(olyWar.map + "You have respawned in " + ChatColor.GREEN + olyWar.mapType + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + olyWar.mapName + ChatColor.GOLD + " as " + ct.getArticle() + " " + t.getColor() + ct.getName());
				}
				
				olyWar.applyClass(p);
				
			}else{
				e.setRespawnLocation(olyWar.spawn);
				olyWar.setPlayerName(((CraftPlayer)p).getHandle(),olyWar.getName(p));
			}
		}else{
			e.setRespawnLocation(olyWar.spawn);
			olyWar.setPlayerName(((CraftPlayer)p).getHandle(),olyWar.getName(p));
		}
	}
}
