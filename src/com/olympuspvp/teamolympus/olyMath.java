package com.olympuspvp.teamolympus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Utilities;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class olyMath{

	/**
	 * Gets entities inside a cone.
	 * 
	 * @see Utilities#getPlayersInCone(List, Location, int, int, int)
	 * 
	 * @param entities - {@code List<Entity>}, list of nearby entities
	 * @param startpoint - {@code Location}, center point
	 * @param radius - {@code int}, radius of the circle
	 * @param degrees - {@code int}, angle of the cone
	 * @param direction - {@code int}, direction of the cone
	 * @return {@code List<Entity>} - entities in the cone
	 */
	public static List<Entity> getEntitiesInCone(final List<Entity> entities, final Location startpoint, final int radius, final int degrees, final int direction)
	{
		final List<Entity> newEntities = new ArrayList<Entity>();

		final int[] startPos = new int[] { (int) startpoint.getX(), (int) startpoint.getZ() };

		final int[] endA = new int[] { (int) (radius * Math.cos(direction - (degrees / 2))), (int) (radius * Math.sin(direction - (degrees / 2))) };

		for(final Entity e : entities)
		{
			final Location l = e.getLocation();
			final int[] entityVector = getVectorForPoints(startPos[0], startPos[1], l.getBlockX(), l.getBlockY());

			final double angle = getAngleBetweenVectors(endA, entityVector);
			if((Math.toDegrees(angle) < degrees) && (Math.toDegrees(angle) > 0))
				newEntities.add(e);
		}
		return newEntities;
	}

	/**
	 * Created an integer vector in 2d between two points
	 * 
	 * @param x1 - {@code int}, X pos 1
	 * @param y1 - {@code int}, Y pos 1
	 * @param x2 - {@code int}, X pos 2
	 * @param y2 - {@code int}, Y pos 2
	 * @return {@code int[]} - vector
	 */
	public static int[] getVectorForPoints(final int x1, final int y1, final int x2, final int y2)
	{
		return new int[] { x2 - x1, y2 - y1 };
	}

	/**
	 * Get the angle between two vectors.
	 * 
	 * @param vector1 - {@code int[]}, vector 1
	 * @param vector2 - {@code int[]}, vector 2
	 * @return {@code double} - angle
	 */
	public static double getAngleBetweenVectors(final int[] vector1, final int[] vector2)
	{
		return Math.atan2(vector2[1], vector2[0]) - Math.atan2(vector1[1], vector1[0]);
	}

	public static boolean validateBackstab(final LivingEntity stabber, final LivingEntity target){
		boolean valid = true;

		final Location loc = target.getLocation();
		final int yaw = Math.round(loc.getYaw());
		final List<Entity> infrontOfTarget = getEntitiesInCone(target.getNearbyEntities(5, 5, 5), loc, 5, 220, yaw);
		for (final Entity e : infrontOfTarget){
			if(e instanceof Player){
				final Player p = (Player) e;
				if(p.equals(stabber)){
					valid = false;
				}
			}
		}return valid;
	}

}
