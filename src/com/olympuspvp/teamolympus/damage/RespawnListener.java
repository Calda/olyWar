package com.olympuspvp.teamolympus.damage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
			if(olyWar.mapType != "Team Deathmatch") lives = 1;
			if(lives > 0){

				e.setRespawnLocation(RespawnListener.respawnPlayer(p));

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

	public static Location respawnPlayer(Player p){
		Location loc = null;
		final ClassType ct = olyWar.getClass(p);
		if(olyWar.mapType.equals("Free For All")){
			Location randomLoc = olyWar.getFreeForAllSpawn();
			loc = randomLoc;
		}else{
			Team t = olyWar.getTeam(p);
			int red = 0;
			int blue = 0;
			for(Player plr : Bukkit.getOnlinePlayers()){
				if(olyWar.getTeam(plr) == Team.RED) red++;
				else if(olyWar.getTeam(plr) == Team.BLUE) blue++;
			}int difference = 0;
			Team higher = Team.NONE;
			if(red > blue){
				difference = red - blue;
				higher = Team.RED;
			}else if(blue > red){
				difference = blue - red; //FUUUUUUUUUUCK YOU NIGGERS
				higher = Team.BLUE;//I HATE JACK SO MUCH
			}if(difference > 1){//WHAT A FAGGOT
				if(t == higher){//EH EH EH ERRRR
					t = higher.getOpposite();//FUQ YOU GUIZ
					olyWar.setTeam(p, t);
					p.sendMessage(olyWar.map + "You have been autobalanced to " + t.getTeamName());
				}
			}

			if(t == Team.RED){
				olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.RED + olyWar.getName(p));
				loc = olyWar.redSpawn;
				p.getInventory().setHelmet(new ItemStack(Material.NETHERRACK));
			}if(t == Team.BLUE){
				olyWar.setPlayerName(((CraftPlayer)p).getHandle(), ChatColor.BLUE + olyWar.getName(p));
				loc = olyWar.blueSpawn;
				p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK));
			}if(t != Team.NONE){
				p.sendMessage(olyWar.map + "You have respawned in " + ChatColor.GREEN + olyWar.mapType + ChatColor.GOLD + " on " + ChatColor.DARK_GREEN + olyWar.mapName + ChatColor.GOLD + " as " + ct.getArticle() + " " + t.getColor() + ct.getName());
			}olyWar.applyClass(p);
		}p.teleport(loc);
		return loc;
	}

}
