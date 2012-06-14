package com.olympuspvp.teamolympus;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import com.olympuspvp.teamolympus.Heartbeat;
import com.olympuspvp.teamolympus.type.*;

public class olyWar extends JavaPlugin{
	Heartbeat hb;
	
	@Override
	public void onEnable(){
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			public void run() {
				hb.addMana();
			}
		}, 30L);
	}
	public boolean onCommand(CommandSender s, Command cc, String cl, String[] args){
		return true;
	}

}
