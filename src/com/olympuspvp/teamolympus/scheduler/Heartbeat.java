package com.olympuspvp.teamolympus.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.olympuspvp.teamolympus.olyWar;
import com.olympuspvp.teamolympus.Item.CloakInteract;
import com.olympuspvp.teamolympus.game.AutoBalance;
import com.olympuspvp.teamolympus.game.NoSprint;
import com.olympuspvp.teamolympus.type.ClassType;

public class Heartbeat {

	public static void start(olyWar ow){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){

			@Override
			public void run(){
				if(olyWar.gameIsActive){
				final Player[] players = Bukkit.getOnlinePlayers();
				if(players.length > 0){
					for(final Player p : players){
						if(olyWar.getClass(p) == ClassType.PALADIN){
							int health = p.getHealth();
							if(health != ClassType.PALADIN.getMaxHealth()) p.setHealth(health+1);
						}if(olyWar.getClass(p) == ClassType.ASSASSIN){
							if(olyWar.invisible.contains(olyWar.getName(p))){
								int mana = p.getFoodLevel();
								if(mana != 0) p.setFoodLevel(mana - 1);
								else CloakInteract.visible(p);
							}else{
								int mana = p.getFoodLevel();
								if(mana != 20) p.setFoodLevel(mana + 2);
							}
						}else{
							int mana = p.getFoodLevel();
							System.out.println(mana);
							if(mana != 20) p.setFoodLevel(mana + 1);
						}
					}
				}
				}
			}
		}, 15L, 15L);
		
				Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
					@Override
					public void run(){
						AutoBalance.run(true);
					}
				}, 3*60*20L, 3*60*20L);
				
				Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ow, new Runnable(){
					@Override
					public void run(){
						NoSprint.check();
					}
				}, 3L,3L);
		
	}
	
}