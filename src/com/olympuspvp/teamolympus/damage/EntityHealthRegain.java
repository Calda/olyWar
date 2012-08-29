package com.olympuspvp.teamolympus.damage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;


public class EntityHealthRegain implements Listener{

	@EventHandler
	public void onEntityHealthRegain(final EntityRegainHealthEvent e){
		if(e.getRegainReason() == RegainReason.SATIATED) e.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamage(final EntityDamageEvent e){
		if(e.getCause() == DamageCause.STARVATION) e.setCancelled(true);
	}

}
