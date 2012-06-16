package com.olympuspvp.teamolympus;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olympuspvp.teamolympus.Item.InteractionListener;
import com.olympuspvp.teamolympus.configuration.LoginListener;
import com.olympuspvp.teamolympus.configuration.WarConfig;
//import com.olympuspvp.teamolympus.configuration.WarConfig;
import com.olympuspvp.teamolympus.damage.DamageListener;

public class olyWar extends JavaPlugin{
	public static Location spawn;
	
	@Override
	public void onDisable(){}
	@Override
	public void onEnable(){
		WarConfig.loadConfig();
		spawn = WarConfig.getSpawn();
		InteractionListener IL = new InteractionListener();
		Bukkit.getServer().getPluginManager().registerEvents(IL, this);
		DamageListener DL = new DamageListener();
		Bukkit.getServer().getPluginManager().registerEvents(DL, this);
		LoginListener LL = new LoginListener();
		Bukkit.getServer().getPluginManager().registerEvents(LL, this);
		
		//HEARTBEAT
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Player[] players = Bukkit.getOnlinePlayers();
				if(players != null){
					if(players.length > 0){
						for(Player p : players){
							int mana = p.getFoodLevel();
							if(mana == 20){
							}else{
								mana++;
								p.setFoodLevel(mana);
							}
						}
					}
				}else{

				}
			}
		}, 15L, 15L);
	}
	
	public boolean onCommand(CommandSender s, Command cc, String cl, String[] args){
		return true;
	}

	public static String getLogo(){
		return ChatColor.BLUE + "[" + ChatColor.YELLOW + "oly" + ChatColor.GOLD + "War" + ChatColor.BLUE + "] ";
	}

	public static FileConfiguration loadData(Player owner) {
		File customConfigFile = new File("plugins/olyWar/players/" + owner.getName() + ".yml");
		FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
		String name = customConfig.getString("name");
		if(name == null){

		}
		return customConfig;
	}
}
