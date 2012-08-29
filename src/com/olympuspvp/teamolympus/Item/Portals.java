package com.olympuspvp.teamolympus.Item;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Portals {

	private Block one = null;
	private int health1 = 20;
	private Block two = null;
	private int health2 = 20;

	Portals(){}

	public void set(final int portal, final Block block){
		if(portal == 1) this.one = block;
		else if(portal == 2) this.two = block;
		else throw new IllegalArgumentException("Portal number must be 1 or 2");
	}

	public Block get(final int portal){
		if(portal == 1) return this.one;
		else if(portal == 2) return this.two;
		else throw new IllegalArgumentException("Portal number must be 1 or 2");
	}

	public boolean hit(final int portal){
		if(portal == 1){
			this.health1--;
			for(final Player p : Bukkit.getOnlinePlayers()){
				p.playEffect(this.one.getLocation(), Effect.SMOKE, 0);
				p.playEffect(this.one.getLocation(), Effect.SMOKE, 0);
				if(this.health1 > 0) p.playEffect(this.one.getLocation(), Effect.ZOMBIE_CHEW_WOODEN_DOOR, 0);
				else p.playEffect(this.one.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 0);
			}if(this.health1 <= 0){
				this.one.setTypeId(0);
				this.one.getRelative(BlockFace.DOWN).setTypeId(0);
				this.one = null;
				return true;
			}
		}else if(portal == 2){
			this.health2--;
			for(final Player p : Bukkit.getOnlinePlayers()){
				p.playEffect(this.two.getLocation(), Effect.SMOKE, 0);
				p.playEffect(this.two.getLocation(), Effect.SMOKE, 0);
				if(this.health2 > 0) p.playEffect(this.two.getLocation(), Effect.ZOMBIE_CHEW_WOODEN_DOOR, 0);
				else p.playEffect(this.two.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 0);
			}if(this.health2 <= 0){
				this.two.setTypeId(0);
				this.two.getRelative(BlockFace.DOWN).setTypeId(0);
				this.two = null;
				return true;
			}
		}else throw new IllegalArgumentException("Portal number must be 1 or 2");
		return false;
	}


}