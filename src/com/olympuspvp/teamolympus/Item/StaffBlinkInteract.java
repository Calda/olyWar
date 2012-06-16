package com.olympuspvp.teamolympus.Item;

//import net.minecraft.server.Material;

//import org.bukkit.Location;
//import org.bukkit.block.Block;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.olympuspvp.teamolympus.olyWar;

public class StaffBlinkInteract {

	public static void run(final Player p, olyWar ow){
		
		EnderPearl ep = p.launchProjectile(EnderPearl.class);
		ep.setPassenger(p);
		
		Runnable run = new Runnable(){
			
			public void run(){
				EnderPearl ep = p.launchProjectile(EnderPearl.class);
				ep.setPassenger(p);
			}
			
		};
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(ow, run, 10L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(ow, run, 10L);
		
		/*Block targetBlock = p.getTargetBlock(null, 30);
		if(!targetBlock.getType().equals(Material.AIR)){
			if(ConsumeMana.consumeMana(p,ItemType.STAFF_BLINK.getManaUsage())){
				Location loc = targetBlock.getLocation();
				loc.setY(loc.getY() + 1);
				p.teleport(loc, TeleportCause.PLUGIN);
			}
		}*/
	}
}
