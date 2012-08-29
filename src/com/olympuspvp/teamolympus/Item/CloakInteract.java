package com.olympuspvp.teamolympus.Item;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class CloakInteract{

	public static void run(final Player p){

		if(checkVisible(p)) visible(p);
		else invisible(p);

	}

	public static void invisible(final Player p){

		final Team t1 = olyWar.getTeam(p);
		final Team t2 = t1.getOpposite();

		olyWar.invisible.add(olyWar.getName(p));
		p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are now cloaked.");

		for(final Player plr : Bukkit.getServer().getOnlinePlayers()){
			if(olyWar.getTeam(plr) == t2) plr.hidePlayer(p);
		}
	}

	public static void visible(final Player p){
		olyWar.invisible.remove(p);
		p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are no longer cloacked.");
		for(final Player plr : Bukkit.getServer().getOnlinePlayers()){
			plr.showPlayer(p);
		}
	}

	public static boolean checkVisible(final Player p){

		if(olyWar.invisible.contains(olyWar.getName(p))) return true;
		else return false;

	}
}
