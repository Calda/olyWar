package com.olympuspvp.teamolympus.damage;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.entity.ThrownPotion;

import com.olympuspvp.teamolympus.game.Team;

public class PVPListener implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		e.setCancelled(true);
		Entity damaged = e.getEntity();
		Entity damager = e.getDamager();
		if(damaged instanceof Player){
			
			Player damagedP = (Player) e;
			Team damagedTeam = Team.RED; //ADD METHOD FOR GET
					
			if(damager instanceof Player){ //player
			}

			else if(damager instanceof Wolf){ //wolf

			}

			else if(damager instanceof ThrownPotion){ //poison powder

			}

			else if(damager instanceof Fireball){

			}

			else if(damager instanceof Snowball){

			}
		}else if(damaged instanceof Wolf){
			
			
			
			
		}
	}

}
