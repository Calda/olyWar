package com.olympuspvp.teamolympus.Item;

import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.game.Team;

public class MagicalInteract{

	public static void addEffect(final Entity attacker, final Entity damaged){

		if(attacker instanceof Player){
			if(damaged instanceof Player){
				final Team t1 = olyWar.getTeam(((Player)attacker));
				final Team t2 = olyWar.getTeam(((Player)damaged));
				if(t1 != t2){
					addPotion(damaged);
				}
			}else{
				addPotion(damaged);
			}
		}

	}

	public static void addPotion(final Entity p){

		PotionEffectType type = PotionEffectType.HUNGER;
		final Random r = new Random();
		final int random = r.nextInt(8);
		final int level = r.nextInt(3);
		int length = 15;
		switch(random){
			case 0:
				type = PotionEffectType.BLINDNESS;
				length = 5;
				break;
			case 1:
				type = PotionEffectType.CONFUSION;
				length = 10;
				break;
			case 2:
				type = PotionEffectType.HUNGER;
				length = 15;
				break;
			case 3:
				type = PotionEffectType.SLOW;
				length = 20;
				break;
			case 4:
				type = PotionEffectType.SLOW_DIGGING;
				length = 20;
				break;
			case 5:
				type = PotionEffectType.WEAKNESS;
				length = 20;
				break;
		}
		final PotionEffect effect = new PotionEffect(type, length * 20, level);
		if(p instanceof LivingEntity){
			final LivingEntity effecting = (LivingEntity) p;
			effecting.addPotionEffect(effect);
		}
	}
}
