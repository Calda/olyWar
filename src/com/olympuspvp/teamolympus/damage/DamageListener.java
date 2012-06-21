package com.olympuspvp.teamolympus.damage;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.DaggerInteract;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.MagicalInteract;
import com.olympuspvp.teamolympus.Item.WarAxeInteract;
import com.olympuspvp.teamolympus.game.Team;

public class DamageListener implements Listener{

	@EventHandler
	public void onDamage(final EntityDamageByEntityEvent e){
		final Entity damagedE = e.getEntity();
		final Entity attackerE = e.getDamager();

		if((damagedE instanceof LivingEntity) && !(damagedE instanceof Player)){
			applyDamage(attackerE, ((LivingEntity)damagedE) , e);
		}else if((damagedE instanceof Player) && (attackerE instanceof Player)){
			final Team t1 = olyWar.getTeam((Player)attackerE);
			final Team t2 = olyWar.getTeam((Player)damagedE);
			if(t1 != t2) applyDamage(attackerE, ((Player)damagedE) , e);
			else e.setCancelled(true);
		}
	}

	public static void applyDamage(final Entity attacker, final LivingEntity damaged, final EntityDamageByEntityEvent e){
		if(attacker instanceof Snowball){
			e.setCancelled(true);
			damaged.damage(1, ((Snowball) e.getDamager()).getShooter());
		}

		else if(attacker instanceof SmallFireball){
			e.setDamage(2);
			e.getEntity().setFireTicks(100);
		}

		else if(attacker instanceof Arrow){
			e.setDamage(3);
		}

		else if(attacker instanceof Wolf){
			e.setDamage(3);
		}

		else if(attacker instanceof Player){
			final Player plr = (Player) attacker;
			final Material hand = ((Player) e.getDamager()).getItemInHand().getType();

			if(hand == ItemType.SWORD_SHORT.getMaterial()){
				e.setDamage(3);
			}

			else if(hand == ItemType.WAR_AXE.getMaterial()){
				WarAxeInteract.run(((LivingEntity) e.getEntity()), ((Player) e.getDamager()));
			}

			else if(hand == ItemType.SWORD_LONG.getMaterial()){
				e.setDamage(4);
			}

			else if(hand == ItemType.SWORD_FIRE.getMaterial()){
				e.setDamage(2);
				damaged.setFireTicks(100);
			}

			else if(hand == ItemType.SWORD_MAGIC.getMaterial()){
				e.setDamage(3);
				MagicalInteract.addPotion(damaged);
			}

			else if(hand == ItemType.DAGGER.getMaterial()){
				e.setDamage(0);
				DaggerInteract.run(plr, damaged);
			}

			else if(hand == ItemType.DAGGER_POISON.getMaterial()){
				e.setDamage(0);
				DaggerInteract.run(plr, damaged);
				final PotionEffect effect = new PotionEffect(PotionEffectType.POISON, 200, 1);
				damaged.addPotionEffect(effect);
			}

			else if(hand == ItemType.DAGGER_MAGIC.getMaterial()){
				e.setDamage(0);
				DaggerInteract.run(plr, damaged);
				MagicalInteract.addPotion(damaged);
			}

		}
	}
}
