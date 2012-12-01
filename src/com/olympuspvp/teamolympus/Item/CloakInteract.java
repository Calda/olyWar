package com.olympuspvp.teamolympus.Item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class CloakInteract{

	public static void run(final Player p){

		if(olyWar.isInvisible(p)) visible(p);
		else invisible(p);

	}

	public static void invisible(final Player p){

		final Team t1 = olyWar.getTeam(p);
		final Team t2 = t1.getOpposite();

		olyWar.setInisible(p);
		p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are now cloaked.");

		for(final Player plr : Bukkit.getServer().getOnlinePlayers()){
			if(olyWar.getTeam(plr) == t2) plr.hidePlayer(p);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
			plr.playEffect(p.getLocation(), Effect.SMOKE, 0);
		}
	}

	public static void visible(final Player p){
		olyWar.setVisible(p);
		p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are no longer cloacked.");
		for(final Player plr : Bukkit.getServer().getOnlinePlayers()){
			plr.showPlayer(p);
		}
	}
}
