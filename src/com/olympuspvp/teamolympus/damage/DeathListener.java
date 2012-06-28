package com.olympuspvp.teamolympus.damage;

import java.util.Random;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.configuration.WarConfig;


public class DeathListener implements Listener{
	olyWar olyw;
	public DeathListener(olyWar ow){
		this.olyw = ow;
	}
	@EventHandler
	public void onEntityDeath(final EntityDeathEvent e){
		e.setDroppedExp(0);
	}
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent e){
		final String kill = ChatColor.WHITE + "[" + ChatColor.AQUA + "KILL" + ChatColor.WHITE + "] ";
		final String streak = ChatColor.GRAY + "[" + ChatColor.BLUE + "STREAK" + ChatColor.GRAY + "] ";
		int numberOfKills = 0;
		final Entity killer = e.getEntity().getKiller();
		final Player killed = e.getEntity();
		if(olyWar.getTeam(killed) != null){
			final ChatColor cc1 = olyWar.getTeam(killed).getColor();
			final String name1 = olyWar.getName(killed);

			if(killer instanceof Player){
				final Player killerp = (Player) killer;
				olyWar.addPoint(killerp);
				numberOfKills = olyWar.getScore(killerp);
				final ChatColor cc2 = olyWar.getTeam(killerp).getColor();
				final String name2 = olyWar.getName(killerp);
				killerp.awardAchievement(Achievement.KILL_ENEMY);

				final Random r = new Random();
				String reason = "killed";
				switch(r.nextInt(10)){

				case 0:
					reason = " destroyed ";
					break;
				case 1:
					reason = " obliterated ";
					break;
				case 2:
					reason = " murdered ";
					break;
				case 3:
					reason = " knocked out ";
					break;
				case 4:
					reason = " killed ";
					break;
				case 5:
					reason = " killed ";
					break;
				case 6:
					reason = " killed ";
					break;
				case 7:
					reason = " defeated ";
					break;
				case 8:
					reason = " annihilated ";
					break;
				case 9:
					reason = " murdered ";
					break;
				}e.setDeathMessage(kill + cc2 + name2 + ChatColor.YELLOW + reason + cc1 + name1);

			}else if(killer instanceof Projectile){
				final LivingEntity killerLE = ((Projectile)killer).getShooter();
				if(killerLE instanceof Player){
					final Player killerp = (Player) killerLE;
					olyWar.addPoint(killerp);
					numberOfKills = olyWar.getScore(killerp);
					final ChatColor cc2 = olyWar.getTeam(killerp).getColor();
					final String name2 = olyWar.getName(killerp);
					e.setDeathMessage(cc2 + name2 + ChatColor.YELLOW + " shot " + cc1 + name1);
				}
			}else if(killer instanceof Tameable){
				final AnimalTamer killerLE = ((Tameable)killer).getOwner();
				if(killerLE instanceof Player){
					final Player killerp = (Player) killerLE;
					olyWar.addPoint(killerp);
					numberOfKills = olyWar.getScore(killerp);
					final ChatColor cc2 = olyWar.getTeam(killerp).getColor();
					final String name2 = olyWar.getName(killerp);
					e.setDeathMessage(kill + cc2 + name2 +"'s wolf" + ChatColor.YELLOW + " bit " + cc1 + name1);
				}
			}

			Bukkit.getServer().broadcastMessage(e.getDeathMessage());
			e.setDeathMessage(null);
			String message = null;
			if(numberOfKills == 4) message = "Overkill!";
			if(numberOfKills >= 5) message = "Massive Killstreak!";
			if(numberOfKills >= 7) message = "We got a massacre on our hands!";
			if (message != null) Bukkit.getServer().broadcastMessage(streak + ChatColor.DARK_GRAY + message);

			final int pointsBefore = olyWar.getScore(killed);

			killed.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You got " + cc1 + pointsBefore + ChatColor.GOLD + " points during this life as a " + cc1 + olyWar.getClass(killed).getName());
			killed.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You now have " + cc1 + WarConfig.getScore(killed) + ChatColor.GOLD + " points with that class and " + cc1 + WarConfig.getScore(killed) + ChatColor.GOLD + " points total");
			olyWar.die(killed, olyw);
			Inventory i = killed.getInventory();
			i.setContents(null);
		}
	}
}