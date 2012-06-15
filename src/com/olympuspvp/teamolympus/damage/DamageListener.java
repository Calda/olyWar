package com.olympuspvp.teamolympus.damage;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.MagicalInteract;

public class DamageListener implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Snowball){
			e.setDamage(3);
		}
		
		else if(e.getDamager() instanceof Player){
			Material hand = ((Player)e.getDamager()).getItemInHand().getType();
			
			if(hand == ItemType.SWORD_SHORT.getMaterial()){
				e.setDamage(4);
			}
			
			else if(hand == ItemType.SWORD_LONG.getMaterial()){
				e.setDamage(5);
			}
			
			else if(hand == ItemType.SWORD_FIRE.getMaterial()){
				e.setDamage(4);
				e.getEntity().setFireTicks(100);
			}
			
			else if(hand == ItemType.SWORD_MAGIC.getMaterial()){
				e.setDamage(5);
				MagicalInteract.addEffect(e.getEntity());
			}
		}
	}
}
