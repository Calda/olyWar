package com.olympuspvp.teamolympus.Item;

import com.olympuspvp.teamolympus.type.ClassType;

public enum Unlockable{
	// POWDER_MAGIC(ItemType.POWDER_MAGIC, ClassType.ASSASSIN),
	STAFF_FIRE_BIG(null, ClassType.SORCERER), // use?
	CROSSBOW_REPEATING(null, ClassType.RANGER),
	DAGGER_MAGIC(ItemType.DAGGER_MAGIC, ClassType.ASSASSIN),
	DAGGER_POISON(ItemType.DAGGER_POISON, ClassType.ASSASSIN),
	SWORD_FIRE(ItemType.SWORD_FIRE, ClassType.PALADIN),
	SWORD_MAGIC(ItemType.SWORD_MAGIC, ClassType.PALADIN);

	private ClassType unlocking;
	private ItemType it;

	Unlockable(final ItemType item, final ClassType unlcking){
		this.it = item;
		this.unlocking = unlcking;
	}

	public ClassType getUnlockingClass(){
		return this.unlocking;
	}

	public ItemType getItemType(){
		return this.it;
	}
}
