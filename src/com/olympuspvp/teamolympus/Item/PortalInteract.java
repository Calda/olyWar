package com.olympuspvp.teamolympus.Item;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.type.ClassType;

public class PortalInteract implements Listener{
	
	private static HashMap<String, Portals> allPortals = new HashMap<String, Portals>();
	private static String portalTag = ChatColor.DARK_PURPLE + "[" + ChatColor.LIGHT_PURPLE + "PORTAL" + ChatColor.DARK_PURPLE + ChatColor.DARK_PURPLE + "] " + ChatColor.LIGHT_PURPLE;

	@EventHandler
	public static void onPortalPlace(final BlockPlaceEvent e){
		final Player p = e.getPlayer();
		final Block b = e.getBlockPlaced();
		final Block up = b.getRelative(BlockFace.UP);
		final String name = olyWar.getName(p);
		if(olyWar.gameIsActive){
			if(b.getType() == Material.PORTAL && up.getType() == Material.AIR && up.getRelative(BlockFace.UP).getType() == Material.AIR){
				if(olyWar.getTeam(p) == Team.RED) b.setType(Material.NETHERRACK);
				else if(olyWar.getTeam(p) == Team.BLUE) b.setType(Material.LAPIS_BLOCK);
				up.setTypeId(1,false);
				up.setTypeId(90,false);
				if(!allPortals.containsKey(name)){
					final Portals portals = new Portals();
					portals.set(1, up.getLocation());
					allPortals.put(name, portals);
					p.sendMessage(portalTag + "You have created a new portal for you team.");
					p.sendMessage(portalTag + "Build another for them to connect!");
					p.setItemInHand(new ItemStack(ItemType.PORTAL_CALLBACK.getMaterial()));
				}else{
					final Portals portals = allPortals.get(name);
					/*if(portals.get(1) == null){
						portals.set(1, up);
						allPortals.put(name, portals);
						p.sendMessage(portalTag + "You have created a new portal for you team.");
						p.sendMessage(portalTag + "Build another for them to connect!");
						p.setItemInHand(new ItemStack(ItemType.PORTAL_CALLBACK.getMaterial()));
					}else{*/
					if(portals.get(2) == null){
						System.out.println("Assing portal 2");
						portals.set(2, up.getLocation());
						allPortals.put(name, portals);
						p.sendMessage(portalTag + "You have created another portal for you team.");
						p.sendMessage(portalTag + "You can now teleport between the two portals!");
						p.setItemInHand(new ItemStack(ItemType.PORTAL_CALLBACK.getMaterial()));
					}else{
						p.sendMessage(portalTag + "You already have two portals placed. Wat?");
					}
					//}
				}
			}else{ 
				e.setCancelled(true);
				e.getPlayer().sendMessage(portalTag + "You cannot place a portal here. There must be two blocks of space above the portal.");
			}
		}else if(!p.isOp()) e.setCancelled(true);
	}

	@EventHandler
	public static void onPlayerPortal(final PlayerPortalEvent e){
		final Player p = e.getPlayer();
		final Block portal = p.getLocation().getBlock();
		final Block wool = portal.getRelative(BlockFace.DOWN);
		boolean rightTeam = false;
		e.setCancelled(false);
		if(wool.getType() == Material.NETHERRACK && olyWar.getTeam(p) == Team.RED) rightTeam = true;
		else if(wool.getType() == Material.LAPIS_BLOCK && olyWar.getTeam(p) == Team.BLUE) rightTeam = true;
		if(rightTeam){
			final String owner = getOwnerOfPortal(portal);
			if(owner != null){
				final Portals portals = allPortals.get(owner);
				if(portals != null){
					final Location portalLoc = portal.getLocation();
					Location otherPortal = null;
					if(portalLoc.equals(portals.get(1))) otherPortal = portals.get(2);
					else if(portalLoc.equals(portals.get(2))) otherPortal = portals.get(1);
					else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not properly connected to its owner. Please report this as ERROR P-1");
					if(otherPortal != null){
						e.setTo(otherPortal);
						e.setCancelled(false);
					}else{
						p.sendMessage(portalTag + "A portal has not been connected to that one.");
						e.setCancelled(true);
						e.setTo(p.getLocation());
					}
					//}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not properly connected to its owner. Please report this as ERROR P-2");
				}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not owned by a player. Plase report this as ERROR P-3");
			}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not owned by a player. Plase report this as ERROR P-4");
		}else{
			if(wool.getType() == Material.NETHERRACK || wool.getType() == Material.LAPIS_BLOCK){
				e.getPlayer().sendMessage(portalTag + "You can't use another team's portal!");
			}e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	public static void deletePortals(final Player p){
		final String name = olyWar.getName(p);
		if(!allPortals.containsKey(name) && olyWar.getClass(p) == ClassType.INFILTRATOR) p.sendMessage(olyWar.getLogo() + ChatColor.GOLD + "You do not have any placed portals!");
		else{
			final Portals portals = allPortals.get(name);
			allPortals.remove(name);
			p.getInventory().setItem(1, new ItemStack(Material.PORTAL, 1));
			p.getInventory().setItem(2, new ItemStack(Material.PORTAL, 1));
			p.updateInventory();
			if(portals.get(1) != null){
				portals.get(1).getBlock().setTypeId(0);
				portals.get(1).getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
			}if(portals.get(2) != null){
				portals.get(2).getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
				portals.get(2).getBlock().setTypeId(0);
			}
		}
	}

	public static void deleteAllPortals(){
		for(final Player p : Bukkit.getOnlinePlayers()){
			final String name = olyWar.getName(p);
			if(allPortals.containsKey(name)){
				final Portals portals = allPortals.get(name);
				allPortals.remove(name);
				if(portals.get(1) != null){
					portals.get(1).getBlock().setTypeId(0);
					portals.get(1).getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
				}if(portals.get(2) != null){
					portals.get(2).getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
					portals.get(2).getBlock().setTypeId(0);
				}
			}
		}
	}

	public static void punchPortal(final Player p, final Block portal){
		final String owner = getOwnerOfPortal(portal);
		final Player ownerp = Bukkit.getPlayer(owner);
		final Team t = olyWar.getTeam(ownerp);
		if(owner != null && t != null){
			if(t != olyWar.getTeam(p)){
				final Portals portals = allPortals.get(owner);
				if(portals != null){
					if(portal.getLocation().equals(portals.get(1))){
						if(portals.hit(1)){
							ownerp.sendMessage(portalTag + "Your Portal " + ChatColor.DARK_PURPLE + 1 + ChatColor.LIGHT_PURPLE + " was broken by " + olyWar.getTeam(p).getColor() + olyWar.getName(p));
							p.sendMessage(portalTag + "You broke " + olyWar.getTeam(ownerp).getColor() + olyWar.getName(p) + "'s" + ChatColor.LIGHT_PURPLE + " portal!");
						}
					}else if(portal.getLocation().equals(portals.get(2))){
						if(portals.hit(1)){
							ownerp.sendMessage(portalTag + "Your Portal " + ChatColor.DARK_PURPLE + 2 + ChatColor.LIGHT_PURPLE + " was broken by " + olyWar.getTeam(p).getColor() + olyWar.getName(p));
							p.sendMessage(portalTag + "You broke " + olyWar.getTeam(ownerp).getColor() + olyWar.getName(p) + "'s" + ChatColor.LIGHT_PURPLE + " portal!");
						}
					}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not properly connected to its owner. Please report this as ERROR P-5");
				}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not properly connected to its owner. Please report this as ERROR P-6");
			}else p.sendMessage(portalTag + ChatColor.DARK_RED + "That portal is not owned by a player. Plase report this as ERROR P-7");
		}
	}

	public static String getOwnerOfPortal(final Block b){
		String name = null;
		for(final Player p : Bukkit.getOnlinePlayers()){
			if(allPortals.containsKey(olyWar.getName(p))){
				final Portals portals = allPortals.get(olyWar.getName(p));
				if(portals.get(1) != null){
					if(portals.get(1).equals(b.getLocation())) name = olyWar.getName(p);
				}if(portals.get(2) != null){
					if(portals.get(2).equals(b.getLocation())) name = olyWar.getName(p);
				}
			}
		}return name;
	}

	@EventHandler
	public void onBlockPhysics(final BlockPhysicsEvent e){
		if(e.getBlock().getTypeId() == 90) e.setCancelled(true);
	}

}
