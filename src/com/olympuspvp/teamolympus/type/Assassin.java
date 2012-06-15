package com.olympuspvp.teamolympus.type;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.olympuspvp.teamolympus.Item.*;
import com.olympuspvp.teamolympus.game.Team;

@Deprecated
public class Assassin implements Class{
	private final int maximumHealth = 12;
	private ClassType type = ClassType.ASSASSIN;
	private Player player;
	private Team team;
	
	Assassin(Player p, Team t){
		this.player = p;
		this.team = t;
	}
	
	@Override
	public List<PotionEffect> getRespawnEffects(){
		PotionEffect[] array = {};
		List<PotionEffect> respawnEffects = Arrays.asList(array);
		respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 4));
		respawnEffects.add(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 4));
		return respawnEffects;
	}

	@Override
	public List<ItemType> getRespawnInventory(){
		ItemType[] array = {};
		List<ItemType> respawnInventory = Arrays.asList(array);
		respawnInventory.add(ItemType.USER_CHOICE);
		respawnInventory.add(ItemType.POWDER_POISON);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.POTION_HEALTH);
		respawnInventory.add(ItemType.CLOAK);
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
