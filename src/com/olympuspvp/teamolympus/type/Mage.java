package com.olympuspvp.teamolympus.type;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.olympuspvp.teamolympus.Item.*;
import com.olympuspvp.teamolympus.game.Team;

@Deprecated
public class Mage implements Class{
	private final int maximumHealth = 14;
	private ClassType type = ClassType.MAGE;
	private Player player;
	private Team team;
	
	Mage(Player p, Team t){
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
		respawnInventory.add(ItemType.STAFF_FIRE);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.POTION_HEALTH);
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
