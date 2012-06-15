package com.olympuspvp.teamolympus.type;

public enum ClassType {
	ROUGE("Rouge"), 
	INFILTRATOR("Infiltrator"), 
	ASSASSIN("Assassin"), 
	WARRIOR("Warrior"), 
	BERSERKER("Berserker"), 
	PALADIN("Paladin"), 
	ARCHER("Archer"), 
	HUNTSMAN("Huntsman"), 
	RANGER("Ranger"), 
	MAGE("Mage"), 
	CLERIC("Cleric"), 
	SORCERER("Sorcerer");
	
	private String name;
	ClassType(String friendlyName){
		this.name = friendlyName;
	}
	
	public String getName(){
		return this.name;
	}
}
