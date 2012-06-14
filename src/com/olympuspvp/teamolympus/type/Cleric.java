package com.olympuspvp.teamolympus.type;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.olympuspvp.teamolympus.Item.*;
import com.olympuspvp.teamolympus.game.Team;

public class Cleric implements Class{
	private final int maximumHealth = 18;
	private ClassType type = ClassType.CLERIC;
	private Player player;
	private Team team;
	
	Cleric(Player p, Team t){
		this.player = p;
		this.team = t;
	}
	
	@Override
	public List<PotionEffect> getRespawnEffects(){
		PotionEffect[] array = {};
		List<PotionEffect> respawnEffects = Arrays.asList(array);
		return respawnEffects;
	}

	@Override
	public List<ItemType> getRespawnInventory(){
		ItemType[] array = {};
		List<ItemType> respawnInventory = Arrays.asList(array);
		respawnInventory.add(ItemType.STAFF_HEALING);
		return respawnInventory;
	}
	
	@Override
	public int getMaximumHealth(){
		return this.maximumHealth;
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public ClassType getClassType() {
		return this.type;
	}
	
	@Override
	public Team getTeam(){
		return this.team;
	}

}
