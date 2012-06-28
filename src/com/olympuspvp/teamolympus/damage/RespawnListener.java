package com.olympuspvp.teamolympus.damage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.Unlockable;
import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.game.Team;
import com.olympuspvp.teamolympus.type.ClassType;

public class RespawnListener implements Listener{

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e){
		final Player p = e.getPlayer();
		if(olyWar.gameIsActive){
			final int lives = olyWar.getLives(p);
			if(lives > 0){
				final Team t = olyWar.getTeam(p);
				if(t == Team.RED) e.setRespawnLocation(olyWar.redSpawn);
				if(t == Team.BLUE) e.setRespawnLocation(olyWar.blueSpawn);

				//INVENTORY && POTION EFFECTS\\
				//============================\\
				ClassType ct = olyWar.getClass(p);
				List<ItemType> respawnInventory = new ArrayList<ItemType>();
				List<PotionEffect> respawnEffects = new ArrayList<PotionEffect>();
				if(ct == ClassType.ARCHER){
					respawnInventory.add(ItemType.BOW);
					respawnInventory.add(ItemType.ARROW);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 1));
				}else if(ct == ClassType.ASSASSIN){
					Unlockable u =  WarConfig.getWeaponPreference(p, ct);
					respawnInventory.add(u.getItemType());
					respawnInventory.add(ItemType.POWDER_POISON);
					respawnInventory.add(ItemType.CLOAK);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
					respawnEffects.add(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 3));
				}else if(ct == ClassType.BERSERKER){
					respawnInventory.add(ItemType.WAR_AXE);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
					respawnEffects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10*60*20, 2));
				}else if(ct == ClassType.CLERIC){
					respawnInventory.add(ItemType.STAFF_HEALING);

				}else if(ct == ClassType.HUNTSMAN){
					respawnInventory.add(ItemType.BOW);
					respawnInventory.add(ItemType.ARROW);
					respawnInventory.add(ItemType.WOLF_EGG);
					respawnInventory.add(ItemType.WOLF_EGG);
					respawnInventory.add(ItemType.WOLF_EGG);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
				}else if(ct == ClassType.INFILTRATOR){
					respawnInventory.add(ItemType.STAFF_BLINK);
					respawnInventory.add(ItemType.PORTAL);
					respawnInventory.add(ItemType.PORTAL);
					respawnInventory.add(ItemType.PORTAL_CALLBACK);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 3));
					respawnEffects.add(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 3));
				}else if(ct == ClassType.MAGE){
					respawnInventory.add(ItemType.STAFF_FIRE);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnInventory.add(ItemType.POTION_HEALTH);
				}else if(ct == ClassType.PALADIN){
					Unlockable u =  WarConfig.getWeaponPreference(p, ct);
					respawnInventory.add(u.getItemType());
					respawnEffects.add(new PotionEffect(PotionEffectType.SLOW, 10*60*20, 1));
				}else if(ct == ClassType.RANGER){
					Unlockable u =  WarConfig.getWeaponPreference(p, ct);
					respawnInventory.add(u.getItemType());
					respawnInventory.add(ItemType.ARROW);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
				}else if(ct == ClassType.ROUGE){
					respawnInventory.add(ItemType.THROWING_KNIVES);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 2));
					respawnEffects.add(new PotionEffect(PotionEffectType.JUMP, 10*60*20, 2));
				}else if(ct == ClassType.SORCERER){
					Unlockable u =  WarConfig.getWeaponPreference(p, ct);
					respawnInventory.add(u.getItemType());
					respawnInventory.add(ItemType.STAFF_LIGHTNING);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnEffects.add(new PotionEffect(PotionEffectType.SLOW, 10*60*20, 1));
				}else if(ct == ClassType.WARRIOR){
					respawnInventory.add(ItemType.SWORD_SHORT);
					respawnInventory.add(ItemType.POTION_HEALTH);
					respawnInventory.add(ItemType.POTION_HEALTH);
				}

				if(respawnInventory.size() != 0){
					for(ItemType it : respawnInventory){
						if(it != ItemType.POWDER_POISON){
							p.getInventory().addItem(new ItemStack(it.getMaterial(), it.getAmount()));
						}else{
							p.getInventory().addItem(new ItemStack(Material.POTION, 5, (byte) 16388));
						}
					}
				}

				if(respawnEffects.size() != 0){
					for(PotionEffect pe : respawnEffects){
						p.addPotionEffect(pe);
					}
				}

			}else e.setRespawnLocation(olyWar.spawn);
		}else e.setRespawnLocation(olyWar.spawn);
	}
}
