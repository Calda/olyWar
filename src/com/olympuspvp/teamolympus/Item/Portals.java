package com.olympuspvp.teamolympus.Item;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Portals {

	private Location one = null;
	private int health1 = 20;
	private Location two = null;
	private int health2 = 20;

	Portals(){}

	public void set(final int portal, final Location block){
		if(portal == 1) this.one = block;
		else if(portal == 2) this.two = block;
		else throw new IllegalArgumentException("Portal number must be 1 or 2");
	}

	public Location get(final int portal){
		if(portal == 1) return this.one;
		else if(portal == 2) return this.two;
		else throw new IllegalArgumentException("Portal number must be 1 or 2");
	}

	public boolean hit(final int portal){
		if(portal == 1){
			this.health1--;
			for(final Player p : Bukkit.getOnlinePlayers()){
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				p.playEffect(one, Effect.SMOKE, 0);
				if(this.health1 > 0) p.playEffect(one, Effect.ZOMBIE_CHEW_WOODEN_DOOR, 0);
				else p.playEffect(one, Effect.ZOMBIE_DESTROY_DOOR, 0);
			}if(this.health1 <= 0){
				this.one.getBlock().setTypeId(0);
				this.one.getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
				this.one = null;
				return true;
			}
		}else if(portal == 2){
			this.health2--;
			for(final Player p : Bukkit.getOnlinePlayers()){
				p.playEffect(two, Effect.SMOKE, 0);
				p.playEffect(two, Effect.SMOKE, 0);
				if(this.health2 > 0) p.playEffect(two, Effect.ZOMBIE_CHEW_WOODEN_DOOR, 0);
				else p.playEffect(two, Effect.ZOMBIE_DESTROY_DOOR, 0);
			}if(this.health2 <= 0){
				this.two.getBlock().setTypeId(0);
				this.two.getBlock().getRelative(BlockFace.DOWN).setTypeId(0);
				this.two = null;
				return true;
			}
		}else throw new IllegalArgumentException("Portal number must be 1 or 2");
		return false;
	}


}