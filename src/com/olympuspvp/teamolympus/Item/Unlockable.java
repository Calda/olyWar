package com.olympuspvp.teamolympus.Item;

import com.olympuspvp.teamolympus.type.ClassType;

public enum Unlockable{
	// POWDER_MAGIC(ItemType.POWDER_MAGIC, ClassType.ASSASSIN),
	@Deprecated
	STAFF_FIRE_BIG(ItemType.STAFF_FIRE_BIG, ClassType.SORCERER),
	STAFF_FIRE(ItemType.STAFF_FIRE, ClassType.SORCERER),
	@Deprecated
	CROSSBOW_BURNING(ItemType.CROSSBOW_BURNING, ClassType.RANGER),
	@Deprecated
	CROSSBOW_MAGIC(ItemType.CROSSBOW_MAGIC, ClassType.RANGER),
	@Deprecated
	CROSSBOW(ItemType.CROSSBOW, ClassType.RANGER),
	DAGGER_MAGIC(ItemType.DAGGER_MAGIC, ClassType.ASSASSIN),
	DAGGER(ItemType.DAGGER, ClassType.ASSASSIN),
	DAGGER_POISON(ItemType.DAGGER_POISON, ClassType.ASSASSIN),
	SWORD_FIRE(ItemType.SWORD_FIRE, ClassType.PALADIN),
	SWORD_LONG(ItemType.SWORD_FIRE, ClassType.PALADIN),
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
