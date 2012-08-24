package com.olympuspvp.teamolympus.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class PortalInteract implements Listener{

	private static HashMap<String, List<Block>> portals = new HashMap<String, List<Block>>();

	@EventHandler
	public static void onPortalPlace(final BlockPlaceEvent e){
		final Player p = e.getPlayer();
		final Block b = e.getBlockPlaced();
		final Block up = b.getRelative(BlockFace.UP);
		final String name = olyWar.getName(p);
		if(olyWar.gameIsActive){
			if((b.getType() == Material.PORTAL) && (up.getType() == Material.AIR)){
				final Byte data;
				if(olyWar.getTeam(p) == Team.RED) data = (byte) 14;
				else data = (byte) 11;
				b.setType(Material.WOOL);
				b.setData(data);
				up.setType(Material.PORTAL);
				List<Block> owned = portals.get(name);
				if(owned == null){

					owned = new ArrayList<Block>();
					owned.add(b);
					portals.put(name, owned);
					p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You have created a new portal for you team.");
					p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "Your portals are now connected!");
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

	@EventHandler
	public static void onPlayerPortal(final PlayerPortalEvent e){
		final Block portal = e.getPlayer().getLocation().getBlock();
		final Block wool = portal.getRelative(BlockFace.DOWN);
		boolean rightTeam = false;
		if(wool.getData() == ((byte)14) && olyWar.getTeam(e.getPlayer()) == Team.RED) rightTeam = true;
		else if(wool.getData() == ((byte)11) && olyWar.getTeam(e.getPlayer()) == Team.BLUE) rightTeam = true;
		if(rightTeam){

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
				}else e.getPlayer().sendMessage(olyWar.getLogo() + "There is no portal on the other end..."); e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
			e.getPlayer().sendMessage(olyWar.getLogo() + "You can't use another team's portal!");
		}
	}

	public static void deletePortals(final Player p){
		final String name = olyWar.getName(p);
		if(portals.containsKey(name)){
			p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You do not have any place portals!");
		}else{
			final List<Block> playerPortals = portals.get(name);
			portals.remove(name);
			p.getInventory().setItem(2, new ItemStack(Material.PORTAL, 1));
			p.getInventory().setItem(3, new ItemStack(Material.PORTAL, 1));
			for(Block portal : playerPortals){
				Block wool = portal.getRelative(BlockFace.DOWN);
				portal.setTypeId(0);
				wool.setTypeId(0);
			}
		}
	}

	public static void punchPortal(final Player p, final Block portal){
		final Iterator<Entry<String, List<Block>>> i = portals.entrySet().iterator();
		Block found = null;
		while(i.hasNext() && (found == null)){
			final Map.Entry<String, List<Block>> me = i.next();
			final List<Block> twoPortals = me.getValue();
			if(twoPortals.size() == 2){
				for(int iter = 1; iter > 2; iter++){
					if(twoPortals.get(iter).equals(portal)){
						Player owner = Bukkit.getPlayer(me.getKey());
						Team t1 = olyWar.getTeam(p);
						Team t2 = olyWar.getTeam(owner);
						if(t1 != t2){
							owner.sendMessage(olyWar.getLogo() + "Your portal has been destroyed");
							portal.getRelative(BlockFace.DOWN).setType(Material.AIR);
							portal.setType(Material.AIR);
							p.sendMessage(olyWar.getLogo() + "You destroyed " + owner.getName() + "'s" + ChatColor.GOLD + " portal!");
						}
					}
				}
			}
		}
	}

}
