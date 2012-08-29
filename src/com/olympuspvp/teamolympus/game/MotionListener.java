package com.olympuspvp.teamolympus.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.scheduler.ADbeat;

public class MotionListener implements Listener{

	private String cachedMapNameRed = "";
	private Block tpToRed = null;
	private String cachedMapNameBlueSetup = "";
	private Block tpToBlueSetup = null;
	private String cachedMapNameBlue = "";
	private Block tpToBlue = null;

	@EventHandler
	public void onMove(final PlayerMoveEvent e){
		final Player player = e.getPlayer();
		for (final Player p : Bukkit.getOnlinePlayers()){
			p.showPlayer(player);
		}if(olyWar.gameIsActive){
			final Player p = e.getPlayer();
			final Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			if(b.getType() == Material.IRON_FENCE){
				final Team t = olyWar.getTeam(p);
				if(t != null){
					final Material m = b.getRelative(BlockFace.DOWN).getType();
					if(m == Material.NETHERRACK && t == Team.BLUE){
						Block tpTo = null;
						if(this.cachedMapNameRed == olyWar.mapName){
							tpTo = this.tpToRed;
						}else{
							if(b.getRelative(BlockFace.EAST).getType() != Material.NETHERRACK){
								tpTo = b.getRelative(BlockFace.EAST);
							}else if(b.getRelative(BlockFace.NORTH).getType() != Material.NETHERRACK){
								tpTo = b.getRelative(BlockFace.NORTH);
							}else if(b.getRelative(BlockFace.SOUTH).getType() != Material.NETHERRACK){
								tpTo = b.getRelative(BlockFace.SOUTH);
							}else if(b.getRelative(BlockFace.WEST).getType() != Material.NETHERRACK){
								tpTo = b.getRelative(BlockFace.WEST);
							}this.tpToRed = tpTo;
							this.cachedMapNameRed = olyWar.mapName;
						}if(tpTo != null){
							p.teleport(tpTo.getRelative(BlockFace.UP).getLocation());
							p.sendMessage(olyWar.map + "You cannot enter " + ChatColor.RED + "Red's spawn");
						}

					}else if(m == Material.LAPIS_BLOCK && t == Team.RED){
						Block tpTo = null;
						if(this.cachedMapNameBlue == olyWar.mapName){
							tpTo = this.tpToBlue;
						}else{
							if(b.getRelative(BlockFace.EAST).getType() != Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.EAST);
							}else if(b.getRelative(BlockFace.NORTH).getType() != Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.NORTH);
							}else if(b.getRelative(BlockFace.SOUTH).getType() != Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.SOUTH);
							}else if(b.getRelative(BlockFace.WEST).getType() != Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.WEST);
							}this.tpToBlue = tpTo;
							this.cachedMapNameBlue = olyWar.mapName;
						}if(tpTo != null){
							p.teleport(tpTo.getRelative(BlockFace.UP).getLocation());
							p.sendMessage(olyWar.map + "You cannot enter " + ChatColor.BLUE+ "Blue's spawn");
						}
					}else if(ADbeat.setup && m == Material.LAPIS_BLOCK && t== Team.BLUE){
						Block tpTo = null;
						if(this.cachedMapNameBlueSetup == olyWar.mapName){
							tpTo = this.tpToBlueSetup;
						}else{
							if(b.getRelative(BlockFace.EAST).getType() == Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.EAST);
							}else if(b.getRelative(BlockFace.NORTH).getType() == Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.NORTH);
							}else if(b.getRelative(BlockFace.SOUTH).getType() == Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.SOUTH);
							}else if(b.getRelative(BlockFace.WEST).getType() == Material.LAPIS_BLOCK){
								tpTo = b.getRelative(BlockFace.WEST);
							}this.tpToBlueSetup = tpTo;
							this.cachedMapNameBlueSetup = olyWar.mapName;
						}if(tpTo != null){
							p.teleport(tpTo.getRelative(BlockFace.UP).getLocation());
							p.sendMessage(olyWar.map + "You cannot enter " + ChatColor.BLUE+ "Blue's spawn");
						}
					}
				}
			}
		}
	}
}