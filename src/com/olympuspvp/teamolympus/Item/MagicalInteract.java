package com.olympuspvp.teamolympus.Item;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MagicalInteract {

	@Deprecated
	public static void addEffect(Entity p){

		PotionEffectType type = PotionEffectType.HUNGER;
		Random r = new Random();
		int random = r.nextInt(8);
		int level = r.nextInt(3);
		int length = 15;
		switch(random){
		case 0: type = PotionEffectType.BLINDNESS; length = 5; break;
		case 1: type = PotionEffectType.CONFUSION; length = 10; break;
		case 2: type = PotionEffectType.HUNGER; length = 15; break;
		case 3: type = PotionEffectType.SLOW; length = 20; break;
		case 4: type = PotionEffectType.SLOW_DIGGING; length = 20; break;
		case 5: type = PotionEffectType.WEAKNESS; length = 20; break;
		}PotionEffect effect = new PotionEffect(type, length*20, level);
		if(p instanceof LivingEntity){
			LivingEntity effecting = (LivingEntity)p;
			effecting.addPotionEffect(effect);
		}
	}
}
