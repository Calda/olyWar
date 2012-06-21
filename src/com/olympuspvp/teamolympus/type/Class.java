package com.olympuspvp.teamolympus.type;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import com.olympuspvp.teamolympus.Item.ItemType;
import com.olympuspvp.teamolympus.game.Team;
public interface Class{
	public abstract List<PotionEffect> getRespawnEffects();
	public abstract List<ItemType> getRespawnInventory();
	public abstract int getMaximumHealth();
	public abstract Player getPlayer();
	public abstract ClassType getClassType();
	public abstract Team getTeam();

}
