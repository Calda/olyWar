package com.olympuspvp.teamolympus.command;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;
public class Leave {

	public static void run(Player p, olyWar ow){
		
		Team t = olyWar.getTeam(p);
		if(t != null){
			p.sendMessage(olyWar.getLogo() + "You are no longer a member of Team " + t.getColor() + t.getName());
			p.sendMessage(olyWar.getLogo() + "You may rejoin a team at the start of the next round.");
			olyWar.leaveTeam(p);
			if(t == Team.BLUE) olyWar.bluePlayersAlive--;
			else olyWar.redPlayersAlive--;
			while(olyWar.getLives(p) != 0){
				olyWar.die(p, ow);
			}p.teleport(olyWar.spawn);
		}else{
			p.sendMessage(olyWar.getLogo() + "You are not a member of a team.");
			p.sendMessage(olyWar.getLogo() + "If you would like to change your preffered team, use the following commands:");
			p.sendMessage(olyWar.getLogo() + "/red     /blue     /random");
		}
		
	}
}
