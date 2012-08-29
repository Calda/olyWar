package com.olympuspvp.teamolympus.Item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class EnchantingTableInteract {

	public static void help(final Player p, final Material show){
		String usage = "This item is not modified on this server or texturepack.";

		if(show == ItemType.ARROW.getMaterial()){
			usage = "This is an arrow. It is consumed by the weapons of the archer line of classes: the Archer, Huntsman, and Ranger.";
		}

		else if(show == ItemType.BOW.getMaterial()){
			usage = "This is a bow. It is the primary weapon of the Archer and Huntsman. It can shoot arrows a long distance, which do 1.5 hearts of damage.";
		}

		else if(show == ItemType.CLOAK.getMaterial()){
			usage = "This is a cloak. It is used by the Assassin. It allows the user to become invisible to the other team when they click with it. Cloaking consumes mana over time, which recharges while you are not cloaked. " +
					"When the user interacts with anything, they will instantly be returned to a visible state. Use this to sneak up behind the enemy team.";
		}

		else if(show == ItemType.CROSSBOW.getMaterial()){
			usage = "This is a crossbow. It is the Ranger's primary weapon. When you right click with it once, you stand still and cock it into its ready state. " +
					"Clicking with it again shoots as arrow, which does more damage than a regular bow.";
		}

		else if(show == ItemType.CROSSBOW_BURNING.getMaterial()){
			usage = "This is a burning crossbow. It is an upgrade of the Ranger's crossbow. It functions the same, except it ignites enemies on arrow contact.";
		}

		else if(show == ItemType.CROSSBOW_MAGIC.getMaterial()){
			usage = "This is a magical crossbow. It is an upgrade of the Ranger's crossbow. It functions the same, except it gives the enemy a random, nagative, magical effect, such as blindness, nausea, slowness, and fatigue, on arrow contact.";
		}

		else if(show == ItemType.DAGGER.getMaterial()){
			usage = "This is a dagger. It is the primary weapon of the Assassin. It does minimal damage under regular conditions, but if you attack from behind, the attack will instantly kill any enemy.";
		}

		else if(show == ItemType.DAGGER_MAGIC.getMaterial()){
			usage = "This is a magical dagger. It is an upgrade of the Assassin's dagger.  It functions the same, except it gives the enemy a random, nagative, magical effect, such as blindness, nausea, slowness, and fatigue, on contact.";
		}

		else if(show == ItemType.DAGGER_POISON.getMaterial()){
			usage = "This is a poison dagger. It is an upgrade of the Assassin's dagger. It functions the same, except it poisons the enemy upon contact.This will leave the enemy vulnerable to attacks of your teammates.";
		}

		else if(show == ItemType.EMPTY_BOTTLE.getMaterial()){
			usage = "This is an empty bottle. At one point, it contained a regenerative agent for either health or mana.";
		}

		else if(show == ItemType.PORTAL.getMaterial()){
			usage = "This is a portal. It is the specialty of the Infiltrator. Once you place two of them, they link together, allowing your team to move quickly across the map. They will be destroyed if they are punched by an enemy.";
		}

		else if(show == ItemType.PORTAL_CALLBACK.getMaterial()){
			usage = "This is a portal callback staff. It allows you to take your portals off of the map and return them to your inventory so that you may place them again.";
		}

		else if(show == ItemType.POTION_HEALTH.getMaterial()){
			usage = "This is a potion of health. When you drink it, its regenerative agents act on your health, refilling it to your class's maximum amount.";
		}

		else if(show == ItemType.POTION_MANA.getMaterial()){
			usage = "This is a potion of mana. When you drink it, its regenerative agents act on your mana, refilling it completely.";
		}

		else if(show == ItemType.POWDER_POISON.getMaterial()){
			usage = "This is a pouch of poison powder, the secondary weapon of the assassin. When thrown, it will release a poisonous powder into the air, poisoning the people nearby." +
					" Be warned, for it can also poison your teammates.";
		}

		else if(show == ItemType.STAFF_BLINK.getMaterial()){
			usage = "This is a Blink Staff: a utility of the Infiltrator. On click, it launches a ball of energy into the air which teleports you to its location once it hits the ground. This allows the infiltrator to quickly get to locations for which to place their portals, as well a evade enemies.";
		}

		else if(show == ItemType.STAFF_FIRE.getMaterial()){
			usage = "This is a Fire Staff, the primary weapon of the mage and the sorcerer. It shoots small fireballs at your target on click, which ignite and damage enemies.";
		}

		else if(show == ItemType.STAFF_FIRE_BIG.getMaterial()){
			usage = "This is a upgrade of the Sorcerer's Fire Staff. It functions the same, except it shoots larger fireballs that do more damage.";
		}

		else if(show == ItemType.STAFF_HEALING.getMaterial()){
			usage = "This is a Staff of Healing, the primary tool of the preist. On click, it consumes mana to heal all players on your team within a radius, as well as yourself.";
		}

		else if(show == ItemType.STAFF_LIGHTNING.getMaterial()){
			usage = "This is a Lightning Staff, a weapon of the sorcerer. It calls down a bolt of lightning at your target on click, but it consumes 10 mana.";
		}

		else if(show == ItemType.SWORD_FIRE.getMaterial()){
			usage = "This is a Fire Sword, an upgrade of the Paladin's Long Sword. It functions the same, except it ignites upon contact.";
		}

		else if(show == ItemType.SWORD_LONG.getMaterial()){
			usage = "This is a Long Sword, the primary weapon of the Paladin. It does more damage than the Warrior's Short Sword.";
		}

		else if(show == ItemType.SWORD_MAGIC.getMaterial()){
			usage = "This is a Magical Sword, an upgrade of the Paladin's Long Sword. It functions the same, except it gives the enemy a random, nagative, magical effect, such as blindness, nausea, slowness, and fatigue, on contact.";
		}

		else if(show == ItemType.SWORD_SHORT.getMaterial()){
			usage = "This is a Short Sword, the primary weapon of the Warrior. It's use and purpose is extremely onvious.";
		}

		else if(show == ItemType.THROWING_KNIVES.getMaterial()){
			usage = "These are Throwing Knives, the specialty weapon of the Rouge. You throw them and, on contact, they will do .5 damage.";
		}

		else if(show == ItemType.WAR_AXE.getMaterial()){
			usage = "This is a War Axe, the primary weapon of the Berserker. When you hit an enemy with it, all enemies within a radius will also be damaged.";
		}

		else if(show == ItemType.WOLF_EGG.getMaterial()){
			usage = "This is a Wolf Egg, the specialty of the Huntsman. On click, a pet wolf will be spawned and will fight with you.";
		}

		p.sendMessage(ChatColor.GRAY + usage);


	}

}
