package com.olympuspvp.teamolympus.Item;

import net.minecraft.server.Material;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class StaffBlinkInteract {

	public static void run(Player p){
		
		Block targetBlock = p.getTargetBlock(null, 30);
		if(!targetBlock.getType().equals(Material.AIR)){
			if(ConsumeMana.consumeMana(p,ItemType.STAFF_BLINK.getManaUsage())){
				Location loc = targetBlock.getLocation();
				loc.setY(loc.getY() + 1);
				p.teleport(loc, TeleportCause.PLUGIN);
			}
		}
	}
}
