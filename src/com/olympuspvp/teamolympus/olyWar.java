package com.olympuspvp.teamolympus;

import java.io.File;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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

	public static FileConfiguration loadData(Player owner) {
		File customConfigFile = new File("plugins/olyWar/players/" + owner.getName() + ".yml");
		FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		String name = customConfig.getString("name");
		if(name == null){
			
			//set all default paths here
			
		}
		return customConfig;
	}

}
