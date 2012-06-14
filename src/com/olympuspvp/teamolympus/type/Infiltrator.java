package com.olympuspvp.teamolympus.type;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.olympuspvp.teamolympus.Item.*;
import com.olympuspvp.teamolympus.game.Team;

public class Infiltrator implements Class{
	private final int maximumHealth = 8;
	private ClassType type = ClassType.INFILTRATOR;
	private Player player;
	private Team team;

	Infiltrator(Player p, Team t){
		this.player = p;
		this.team = t;
	}

	@Override
	public List<PotionEffect> getRespawnEffects(){
		PotionEffect[] array = {};
		List<PotionEffect> respawnEffects = Arrays.asList(array);
		respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
		respawnEffects.add(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 3));
		return respawnEffects;
	}

	@Override
	public List<ItemType> getRespawnInventory(){
		ItemType[] array = {};
		List<ItemType> respawnInventory = Arrays.asList(array);
		respawnInventory.add(ItemType.DAGGER);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.PORTAL);
		respawnInventory.add(ItemType.PORTAL);
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
