package com.olympuspvp.teamolympus.command;
import org.bukkit.entity.Player;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;
public class Leave {

	public static void run(final Player p, final olyWar ow){

		final Team t = olyWar.getTeam(p);
		if(t != null){
			p.sendMessage(olyWar.getLogo() + "You are no longer a member of " + t.getTeamName());
			olyWar.leaveTeam(p);
			if(olyWar.mapType.equals("TDM")){
				if(t == Team.BLUE) olyWar.bluePlayersAlive--;
				else olyWar.redPlayersAlive--;
			}p.teleport(olyWar.spawn);
		}else{
			p.sendMessage(olyWar.getLogo() + "You are not a member of a team.");
			p.sendMessage(olyWar.getLogo() + "If you would like to change your preffered team, use the following commands:");
			p.sendMessage(olyWar.getLogo() + "/red     /blue     /random");
		}

	}
}
