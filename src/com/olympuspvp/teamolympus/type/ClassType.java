package com.olympuspvp.teamolympus.type;

public enum ClassType {
	
	ROUGE("Rouge", 10), 
	INFILTRATOR("Infiltrator", 8), 
	ASSASSIN("Assassin", 12), 
	WARRIOR("Warrior", 18), 
	BERSERKER("Berserker", 10), 
	PALADIN("Paladin", 12), 
	ARCHER("Archer", 12), 
	HUNTSMAN("Huntsman", 14), 
	RANGER("Ranger", 14), 
	MAGE("Mage", 14), 
	CLERIC("Cleric", 18), 
	SORCERER("Sorcerer", 12);
	
	private String name;
	private int maxHealth;
	
	ClassType(String friendlyName, int maximumHealth){
		this.name = friendlyName;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getMaxHealth(){
		return this.maxHealth;
	}
	
}
