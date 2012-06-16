package com.olympuspvp.teamolympus.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Material;
import com.olympuspvp.teamolympus.Item.ItemType;

public class InteractionListener implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		Material hand = p.getItemInHand().getType();
		Action a = e.getAction();

		if(a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK){

			if(hand == ItemType.STAFF_HEALING.getMaterial()){
				if(ConsumeMana.consumeMana(p,ItemType.STAFF_HEALING.getManaUsage())){
					StaffHealingInteract.run(p);
					e.setCancelled(true);
				}
			}

			else if(hand == ItemType.STAFF_FIRE.getMaterial()){
				if(ConsumeMana.consumeMana(p,ItemType.STAFF_FIRE.getManaUsage())){
					StaffFireInteract.run(p);
					e.setCancelled(true);
				}
			}

			else if(hand == ItemType.STAFF_LIGHTNING.getMaterial()){
				if(ConsumeMana.consumeMana(p,ItemType.STAFF_LIGHTNING.getManaUsage())){
					StaffLightningInteract.run(p);
					e.setCancelled(true);
				}
			}
			
			else if(hand == ItemType.STAFF_BLINK.getMaterial()){
				StaffBlinkInteract.run(p);
				e.setCancelled(true);
			}

		}else if(a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK){

			if(hand == ItemType.POTION_MANA.getMaterial()){
				PotionManaInteract.run(p);
				e.setCancelled(true);
			}

			if(hand == ItemType.POTION_HEALTH.getMaterial()){
				PotionHealthInteract.run(p);
				e.setCancelled(true);
			}

			else if(hand == ItemType.STAFF_LIGHTNING.getMaterial()){
				if(ConsumeMana.consumeMana(p,ItemType.STAFF_LIGHTNING.getManaUsage())){
					StaffLightningInteract.run(p);
					e.setCancelled(true);
				}
			}

			else if(hand == ItemType.STAFF_BLINK.getMaterial()){
				StaffBlinkInteract.run(p);
				e.setCancelled(true);
			}

			else if(hand == ItemType.POWDER_POISON.getMaterial()){
				PoisonPowderInteract.run(p);
				e.setCancelled(true);
			}


		}
	}
}
