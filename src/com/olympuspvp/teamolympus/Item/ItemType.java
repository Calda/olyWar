package com.olympuspvp.teamolympus.Item;
import org.bukkit.Material;
public enum ItemType {
	
	USER_CHOICE(null, 1, null, 0),
	
	POTION_MANA(Material.MUSHROOM_SOUP, 1, "Mana Potion", 0),
	EMPTY_BOTTLE(Material.BOWL, 1, "Empty Bottle", 0),
	POTION_HEALTH(Material.MILK_BUCKET, 1, "Mana Potion", 0),
	
	BOW(Material.BOW, 1, "Bow", 0),
	ARROW(Material.ARROW, 64, "Arrow", 0),
	WOLF_EGG(Material.BONE, 64, "Arrow", 14),//need to code
	
	SWORD_SHORT(Material.GOLD_SWORD, 1, "Short Sword", 0),
	SWORD_LONG(Material.IRON_SWORD, 1, "Long Sword", 0),
	SWORD_FIRE(Material.STONE_SWORD, 1, "Fire Sword", 0),//need to code
	SWORD_MAGIC(Material.WOOD_SWORD, 1, "Magic Sword", 0),//need to code
	WAR_AXE(Material.IRON_AXE, 1, "War Axe", 0),
	DAGGER(Material.WOOD_SPADE, 1, "Dagger", 0),//need to code
	DAGGER_POISON(Material.STONE_SPADE, 1, "Poison Dagger", 0),//need to code
	DAGGER_MAGIC(Material.IRON_SPADE, 1, "Magic Dagger", 0),//need to code
	
	STAFF_BLINK(Material.FEATHER, 1, "Blink Staff", 0),//need to code
	STAFF_FIRE(Material.BLAZE_ROD, 1, "Fire Staff", 6),
	STAFF_ICE(Material.STRING, 1, "Ice Wand", 8),//need to code
	STAFF_HEALING(Material.WOOD_PICKAXE, 1, "Staff of Healing", 8),
	STAFF_LIGHTNING(Material.STICK, 1, "Lightning Staff", 20),
	
	PORTAL(Material.PORTAL,1, "Portal", 0),//need to code
	PORTAL_CALLBACK(null, 1, "Portal Callback", 20), //NEEDS TO BE CHANGED//need to code
	CLOAK(null,1, "Cloak of Invisibility", 20), //NEEDS TO BE CHANGED//need to code
	POWDER_POISON(Material.SUGAR,5, "Poison Powder", 0),
	//POWDER_MAGIC(Material.SULPHUR,128, "Magic Powder", 0),
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