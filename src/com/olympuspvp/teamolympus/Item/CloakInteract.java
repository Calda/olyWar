package com.olympuspvp.teamolympus.Item;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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

		final String name = olyWar.getName(p);
		olyWar.invisible.add(name);
		if(p.isOnline()) p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are now cloaked.");

		final Iterator<Entry<String, Team>> i = olyWar.teams.entrySet().iterator();

		while(i.hasNext()){
			final Map.Entry<String, Team> me = i.next();
			if(me.getValue() == t2){
				final String nme = me.getKey();
				final Player plr = Bukkit.getPlayer(nme);
				if(plr != null) plr.hidePlayer(p);
			}
		}
	}

	public static void visible(final Player p){

		final Team t1 = olyWar.getTeam(p);
		final Team t2 = t1.getOpposite();

		final String name = olyWar.getName(p);
		olyWar.invisible.add(name);
		p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You are no longer cloacked.");

		final Iterator<Entry<String, Team>> i = olyWar.teams.entrySet().iterator();

		while(i.hasNext()){
			final Map.Entry<String, Team> me = i.next();
			if(me.getValue() == t2){
				final String nme = me.getKey();
				final Player plr = Bukkit.getPlayer(nme);
				if(plr != null) plr.showPlayer(p);
			}
		}
	}

	public static boolean checkVisible(final Player p){

		if(olyWar.invisible.contains(olyWar.getName(p))) return true;
		else return false;

	}
}
