package com.olympuspvp.teamolympus.type;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.olympuspvp.teamolympus.Item.*;
import com.olympuspvp.teamolympus.game.Team;

public class Paladin implements Class{
	private final int maximumHealth = 12;
	private ClassType type = ClassType.PALADIN;
	private Player player;
	private Team team;
	
	Paladin(Player p, Team t){
		this.player = p;
		this.team = t;
	}
	Paladin(Player p){
		this.player = p;
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
		respawnInventory.add(ItemType.SWORD_LONG);
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
