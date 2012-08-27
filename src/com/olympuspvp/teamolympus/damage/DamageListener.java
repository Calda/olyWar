package com.olympuspvp.teamolympus.damage;

import java.util.Random;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.DaggerInteract;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.MagicalInteract;
import com.olympuspvp.teamolympus.Item.WarAxeInteract;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.type.ClassType;

public class DamageListener implements Listener{

	@EventHandler
	public void onDrop(final org.bukkit.event.player.PlayerDropItemEvent e){
		e.setCancelled(true);
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent e){
		if(e.getCause() == DamageCause.FALL && e.getEntity() instanceof Player){
			final Player p = (Player) e.getEntity();
			final ClassType ct = olyWar.getClass(p);
			if(ct == ClassType.ROUGE || ct == ClassType.INFILTRATOR || ct == ClassType.ASSASSIN){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDamageByEntity(final EntityDamageByEntityEvent e){
		final LivingEntity damaged = (LivingEntity) e.getEntity();
		final Entity attacker = e.getDamager();

		if(!onSameTeam(damaged, attacker)){
			if(attacker instanceof Snowball){
				e.setCancelled(true);
				damaged.damage(1, ((Snowball) e.getDamager()).getShooter());
			}

			else if(attacker instanceof SmallFireball){
				e.setDamage(2);
				e.getEntity().setFireTicks(100);
			}

			else if(attacker instanceof Arrow){
				e.setDamage(1);
				final LivingEntity le =((Arrow)attacker).getShooter();
				if(le instanceof Player){
					final Player p = (Player) le;
					if(olyWar.getClass(p) == ClassType.RANGER){
						addRandomEffect(damaged);
					}
				}
			}

			else if(attacker instanceof Wolf){
				e.setDamage(1);
			}

			else if(attacker instanceof Player){
				final Player plr = (Player) attacker;
				final Material hand = ((Player) e.getDamager()).getItemInHand().getType();

				if(hand == ItemType.SWORD_SHORT.getMaterial()){
					e.setDamage(2);
				}

				else if(hand == ItemType.WAR_AXE.getMaterial()){
					WarAxeInteract.run((LivingEntity) e.getEntity(), (Player) e.getDamager());
				}

				else if(hand == ItemType.SWORD_LONG.getMaterial()){
					e.setDamage(2);
				}

				else if(hand == ItemType.SWORD_FIRE.getMaterial()){
					e.setDamage(1);
					damaged.setFireTicks(100);
				}

				else if(hand == ItemType.SWORD_MAGIC.getMaterial()){
					e.setDamage(2);
					MagicalInteract.addPotion(damaged);
				}

				else if(hand == ItemType.DAGGER.getMaterial()){
					e.setDamage(0);
					DaggerInteract.run(plr, damaged);
				}

				else if(hand == ItemType.DAGGER_POISON.getMaterial()){
					e.setDamage(0);
					final PotionEffect effect = new PotionEffect(PotionEffectType.POISON, 200, 1);
					damaged.addPotionEffect(effect);
					DaggerInteract.run(plr, damaged);
				}

				else if(hand == ItemType.DAGGER_MAGIC.getMaterial()){
					e.setDamage(0);
					MagicalInteract.addPotion(damaged);
					DaggerInteract.run(plr, damaged);
				}

			}
		}
	}
	public static boolean onSameTeam(final Entity e1, final Entity e2){
		Team t1 = null, t2 = null;
		Player p1 = null, p2 = null;

		if(e1 instanceof Player) p1 = (Player) e1;
		else if(e1 instanceof Wolf) p1 = (Player) ((Wolf)e1).getOwner();
		else if(e1 instanceof Arrow){
			final LivingEntity le = ((Arrow)e1).getShooter();
			if(le instanceof Player) p1 = (Player) le;
		}else if(e1 instanceof Snowball){
			final LivingEntity le = ((Snowball)e1).getShooter();
			if(le instanceof Player) p1 = (Player) le;
		}else if(e1 instanceof SmallFireball){
			final LivingEntity le = ((SmallFireball)e1).getShooter();
			if(le instanceof Player) p1 = (Player) le;
		}

		if(e2 instanceof Player) p2 = (Player) e2;
		else if(e2 instanceof Wolf) p2 = (Player) ((Wolf)e2).getOwner();
		else if(e2 instanceof Arrow){
			final LivingEntity le = ((Arrow)e2).getShooter();
			if(le instanceof Player) p2 = (Player) le;
		}else if(e2 instanceof Snowball){
			final LivingEntity le = ((Snowball)e2).getShooter();
			if(le instanceof Player) p2 = (Player) le;
		}else if(e2 instanceof SmallFireball){
			final LivingEntity le = ((SmallFireball)e2).getShooter();
			if(le instanceof Player) p2 = (Player) le;
		}

		if(p1 != null && p2 != null){
			t1 = olyWar.getTeam(p1);
			t2 = olyWar.getTeam(p2);
			if(t1 == Team.RED && t2 == Team.RED || t2 == Team.BLUE && t1 == Team.BLUE) return true;
		}

		return false;
	}

	public void addRandomEffect(final Entity p){
		PotionEffectType type = PotionEffectType.HUNGER;
		final Random r = new Random();
		final int random = r.nextInt(8);
		final boolean doit = r.nextBoolean();
		int level = r.nextInt(2);
		int length = 15;
		if(doit){
			switch(random){
				case 0: type = PotionEffectType.BLINDNESS; length = 5; break;
				case 1: type = PotionEffectType.CONFUSION; length = 15; break;
				case 2: type = PotionEffectType.POISON; length = 5; level = 1;break;
				case 3: type = PotionEffectType.HUNGER; length = 15; break;
				case 4: type = PotionEffectType.SLOW; length = 20; break;
				case 5: type = PotionEffectType.SLOW_DIGGING; length = 15; break;
				case 6: type = PotionEffectType.WEAKNESS; length = 20; break;
				case 7: type = PotionEffectType.JUMP; length = 20; break;
			}final PotionEffect effect = new PotionEffect(type, length*20, level);
			final LivingEntity effecting = (LivingEntity)p;
			effecting.addPotionEffect(effect);
		}
	}

}
