package com.olympuspvp.teamolympus.Item;
import org.bukkit.Material;
public enum ItemType {
	
	POTION_MANA(Material.MUSHROOM_SOUP, 1, "Mana Potion", -10),
	EMPTY_BOTTLE(Material.BOWL, 1, "Empty Bottle", 0),
	POTION_HEALTH(null, 1, "Mana Potion", 0),
	//BLINK ROD
	BOW(Material.BOW, 1, "Bow", 0),
	ARROW(Material.ARROW, 64, "Arrow", 0),
	WOLF_EGG(null, 64, "Arrow", 14),//NEEDS TO BE CHANGED (bone)
	
	SWORD_SHORT(null, 1, "Short Sword", 0),//NEEDS TO BE CHANGED
	SWORD_LONG(Material.IRON_SWORD, 1, "Long Sword", 0),
	SWORD_FIRE(Material.STONE_SWORD, 1, "Fire Sword", 0),
	SWORD_MAGIC(Material.WOOD_SWORD, 1, "Magic Sword", 0),
	WAR_AXE(Material.IRON_AXE, 1, "War Axe", 0),
	DAGGER(Material.WOOD_SPADE, 1, "Dagger", 0),
	DAGGER_POISON(Material.STONE_SPADE, 1, "Poison Dagger", 0),
	DAGGER_MAGIC(Material.IRON_SPADE, 1, "Magic Dagger", 0),
	
	STAFF_FIRE(Material.BLAZE_ROD, 1, "Fire Staff", 8),
	STAFF_ICE(null, 1, "Ice Wand", 8),//NEEDS TO BE CHANGED
	STAFF_HEALING(Material.WOOD_PICKAXE, 1, "Staff of Healing", 8),
	STAFF_LIGHTNING(null, 1, "Lightning Staff", 20),//NEEDS TO BE CHANGED
	
	PORTAL(Material.PORTAL,1, "Teleporter", 0),
	PORTAL_CALLBACK(null, 1, "Teleporter Callback", 20), //NEEDS TO BE CHANGED
	CLOAK(null,1, "Cloak of Invisibility", 20), //NEEDS TO BE CHANGED
	POWDER_POISON(Material.EYE_OF_ENDER,5, "Poison Powder", 0),
	POWDER_MAGIC(null,128, "Magic Powder", 0),//NEEDS TO BE CHANGED
	THROWING_KNIVES(Material.SNOW_BALL,64, "Throwing Knives", 0);
	
	private Material mat;
	private int Amount;
	private String name;
	private int manaUsage;
	
	ItemType(Material m, int amnt, String friendlyName, int mu){
		this.mat = m;
		this.Amount = amnt;
		this.name = friendlyName;
		this.manaUsage = mu;
	}
	
	public Material getMaterial(){
		return this.mat;
	}
	
	public int getAmount(){
		return this.Amount;
	}
	public int getManaUsage(){
		return this.manaUsage;
	}
	
	public String toString(){
		return this.name;
	}
}