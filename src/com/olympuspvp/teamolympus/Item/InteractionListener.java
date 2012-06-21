package com.olympuspvp.teamolympus.Item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.type.ClassType;

public class InteractionListener implements Listener{

	private final olyWar plugin;

	public InteractionListener(final olyWar ow){
		this.plugin = ow;
	}

	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e){
		final Player p = e.getPlayer();
		final Material hand = p.getItemInHand().getType();
		final Action a = e.getAction();

		if(olyWar.getClass(p) == ClassType.ASSASSIN){
			if(CloakInteract.checkVisible(p)) CloakInteract.visible(p);
		}

		if(e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){

			//usage for items go here!

		}else{

			if((a == Action.LEFT_CLICK_AIR) || (a == Action.LEFT_CLICK_BLOCK)){

				if(hand == ItemType.STAFF_HEALING.getMaterial()){
					if(ConsumeMana.consumeMana(p, ItemType.STAFF_HEALING.getManaUsage())){
						StaffHealingInteract.run(p);
						e.setCancelled(true);
					}
				}

				else if(hand == ItemType.STAFF_FIRE.getMaterial()){
					if(ConsumeMana.consumeMana(p, ItemType.STAFF_FIRE.getManaUsage())){
						StaffFireInteract.run(p);
						e.setCancelled(true);
					}
				}

				else if(hand == ItemType.STAFF_LIGHTNING.getMaterial()){
					if(ConsumeMana.consumeMana(p, ItemType.STAFF_LIGHTNING.getManaUsage())){
						StaffLightningInteract.run(p);
						e.setCancelled(true);
					}
				}

				else if(hand == ItemType.POWDER_POISON.getMaterial()){
					PoisonPowderInteract.run(p);
					e.setCancelled(true);
				}

			}else if((a == Action.RIGHT_CLICK_AIR) || (a == Action.RIGHT_CLICK_BLOCK)){

				if(hand == ItemType.STAFF_HEALING.getMaterial()){
					if(ConsumeMana.consumeMana(p, ItemType.STAFF_HEALING.getManaUsage())){
						StaffHealingInteract.run(p);
						e.setCancelled(true);
					}
				}

				else if(hand == ItemType.POTION_MANA.getMaterial()){
					PotionManaInteract.run(p);
					e.setCancelled(true);
				}

				if(hand == ItemType.POTION_HEALTH.getMaterial()){
					PotionHealthInteract.run(p);
					e.setCancelled(true);
				}

				else if(hand == ItemType.STAFF_LIGHTNING.getMaterial()){
					if(ConsumeMana.consumeMana(p, ItemType.STAFF_LIGHTNING.getManaUsage())){
						StaffLightningInteract.run(p);
						e.setCancelled(true);
					}
				}

				else if(hand == ItemType.STAFF_BLINK.getMaterial()){
					StaffBlinkInteract.run(p, this.plugin);
					e.setCancelled(true);
				}

				else if(hand == ItemType.POWDER_POISON.getMaterial()){
					PoisonPowderInteract.run(p);
					e.setCancelled(true);
				}

				else if(hand == ItemType.WOLF_EGG.getMaterial()){
					WolfEggInteract.run(p);
					e.setCancelled(true);
				}

				else if(hand == ItemType.CLOAK.getMaterial()){
					CloakInteract.run(p);
					e.setCancelled(true);
				}
			}
		}
	}
}
