package com.olympuspvp.teamolympus.Item;

import net.minecraft.server.EntitySmallFireball;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.entity.CraftSmallFireball;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class StaffFireInteract{

	protected static double[] origincoords = new double[3];
	protected static double[] targetcoords = new double[3];
	protected static int[] currentcoords = new int[3];
	protected static int[] previouscoords = new int[3];
	protected static double[] slopevector = new double[3];
	private static Location player_loc;
	private static Block tb;

	public static void run(final Player p){
		player_loc = p.getLocation();
		tb = p.getTargetBlock(null, 100);

		origincoords[0] = player_loc.getX();
		origincoords[1] = player_loc.getY();
		origincoords[2] = player_loc.getZ();

		targetcoords[0] = tb.getX() + .5 * tb.getX() / Math.abs(tb.getX()); // I hate you sometimes, Notch. Really? Every quadrant is different?
		targetcoords[1] = tb.getY() + .5 - 2;
		targetcoords[2] = tb.getZ() + .5 * tb.getZ() / Math.abs(tb.getZ());

		dofireball(p);
	}

	public static void dofireball(final Player p){
		double linelength = 0;

		for(int i = 0; i < 3; i++){
			slopevector[i] = targetcoords[i] - origincoords[i];
		}

		linelength = Math.pow(Math.pow(slopevector[0], 2) + Math.pow(slopevector[1], 2) + Math.pow(slopevector[2], 2), .5);

		for(int i = 0; i < 3; i++){
			slopevector[i] = slopevector[i] / linelength;
		}

		final EntitySmallFireball fireball = new EntitySmallFireball(((CraftWorld) p.getWorld()).getHandle(), ((CraftPlayer) p).getHandle(), slopevector[0] * linelength, slopevector[1] * linelength, slopevector[2] * linelength);
		final CraftSmallFireball craftfireball = new CraftSmallFireball((CraftServer) p.getServer(), fireball);
		final Vector velocity = new Vector();
		velocity.setX(slopevector[0]);
		velocity.setY(slopevector[1]);
		velocity.setZ(slopevector[2]);
		craftfireball.setVelocity(velocity);
		craftfireball.setBounce(true);
		final Location fbl = craftfireball.getLocation();
		craftfireball.getHandle().setLocation(fbl.getX(), fbl.getY() + 1, fbl.getZ(), fbl.getPitch(), fbl.getYaw());
		((CraftWorld) p.getWorld()).getHandle().addEntity(fireball);
	}
}
