package com.olympuspvp.teamolympus.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import com.olympuspvp.teamolympus.olyWar;

public class PortalInteract implements Listener{

	private static HashMap<String, List<Block>> portals = new HashMap<String, List<Block>>();

	@EventHandler
	public static void onPortalPlace(final BlockPlaceEvent e){
		final Player p = e.getPlayer();
		final Block b = e.getBlockPlaced();
		final Block up = b.getRelative(BlockFace.UP);
		final String name = olyWar.getName(p);
		if(olyWar.gameIsActive){
			if((b.getType() == Material.WOOL) && (up.getType() == Material.AIR)){// ////
				final Byte data = b.getData();                                   // need to change to portal blocks
				if((data == ((byte) 14)) || (data == ((byte) 11))){              // ////

					up.setType(Material.PORTAL);
					List<Block> owned = portals.get(name);
					if(owned == null){

						owned = new ArrayList<Block>();
						owned.add(b);
						portals.put(name, owned);
						p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You have created a new portal for you team.");
						p.setItemInHand(null);

					}
					if(owned.size() == 1){

						owned.add(b);
						portals.put(name, owned);
						p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You have created a new portal for you team.");
						p.setItemInHand(null);

					}else e.setCancelled(true);
				}else e.setCancelled(true);
			}else if(!p.isOp()) e.setCancelled(true);
		}
	}

	@EventHandler
	public static void onPlayerPortal(final PlayerPortalEvent e){
		final Block portal = e.getPlayer().getLocation().getBlock();

		if(portal.getType() == Material.PORTAL){

			final Iterator<Entry<String, List<Block>>> i = portals.entrySet().iterator();
			Block found = null;
			while(i.hasNext() && (found == null)){
				final Map.Entry<String, List<Block>> me = i.next();
				final List<Block> twoPortals = me.getValue();
				if(twoPortals.size() == 2){
					for(int iter = 1; iter > 2; iter++){
						if(twoPortals.get(iter).equals(portal)){
							found = twoPortals.get(iter);
							e.setTo(found.getLocation());
						}
					}
				}
			}

		}else e.setCancelled(true);

	}

	@Deprecated
	public static void deletePortals(final Player p){
		final String name = olyWar.getName(p);
		final List<Block> playerPortals = portals.get(name);
		if(playerPortals == null){
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You do not have any place portals!");
		}else{

		}
	}

	@Deprecated
	public static void punchPortal(final Player p){

	}

}
