package com.olympuspvp.teamolympus.damage;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.Item.MagicalInteract;
import com.olympuspvp.teamolympus.Item.WarAxeInteract;

public class DamageListener implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		Entity ent = e.getEntity();
		if(e.getDamager() instanceof Snowball){
			e.setCancelled(true);
			if(ent instanceof LivingEntity){
				((LivingEntity)ent).damage(1, ((Snowball)e.getDamager()).getShooter());
			}
		}
		
		else if(e.getDamager() instanceof Player){
			Material hand = ((Player)e.getDamager()).getItemInHand().getType();
			
			if(hand == ItemType.SWORD_SHORT.getMaterial()){
				e.setDamage(3);
			}
			
			else if(hand == ItemType.WAR_AXE.getMaterial()){
				if(ent instanceof LivingEntity){
					WarAxeInteract.run(((LivingEntity)e.getEntity()), ((Player)e.getDamager()));
				}
			}
			
			else if(hand == ItemType.SWORD_LONG.getMaterial()){
				e.setDamage(4);
			}
			
			else if(hand == ItemType.SWORD_FIRE.getMaterial()){
				e.setDamage(2);
				e.getEntity().setFireTicks(100);
			}
			
			else if(hand == ItemType.SWORD_MAGIC.getMaterial()){
				e.setDamage(3);
				MagicalInteract.addEffect(ent);
			}
			
			
		}
	}
}
