package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.LivingEntity;
import com.olympuspvp.teamolympus.olyMath;


public class DaggerInteract{

	public static void run(final LivingEntity attacker, final LivingEntity target){

		final boolean validBackstab = olyMath.validateBackstab(attacker, target);

		if(validBackstab){

			if(target instanceof LivingEntity){
				target.damage(20, attacker);
			}

		}else{

			if(target instanceof LivingEntity){
				target.damage(2, attacker);
			}

		}

	}

}
