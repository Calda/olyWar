package com.olympuspvp.teamolympus.Item;
import net.minecraft.server.EntityPotion;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.entity.CraftThrownPotion;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PoisonPowderInteract {

	protected static double[] origincoords = new double[3];
	protected static double[] targetcoords = new double[3];
	protected static int[] currentcoords = new int[3];
	protected static int[] previouscoords = new int[3];
	protected static double[] slopevector = new double[3];
	private static Location player_loc;
	private static Block tb;

	@Deprecated
	public static void run(final Player p){
		p.setItemInHand(null);
		p.updateInventory();
		player_loc=p.getLocation();
		tb = p.getTargetBlock(null,3);

		origincoords[0] = player_loc.getX();
		origincoords[1] = player_loc.getY();
		origincoords[2] = player_loc.getZ();

		targetcoords[0] = tb.getX() + ((.5 * tb.getX()) / Math.abs(tb.getX())); //I hate you sometimes, Notch. Really? Every quadrant is different?
		targetcoords[1] = (tb.getY() + .5);
		targetcoords[2] = tb.getZ() + ((.5 * tb.getZ()) / Math.abs(tb.getZ()));

		//didn't work. I guess I don't understand where the origin of the fireball is determined in this code. shrug. -Gav
		// origincoords[0] = origincoords[0] + (int)(targetcoords[0] - origincoords[0])*0.1; //attempting to make fireballs not blow up in your face anymore. -Gav
		// origincoords[1] = origincoords[1] + (int)(targetcoords[1] - origincoords[1])*0.1;
		// origincoords[2] = origincoords[2] + (int)(targetcoords[2] - origincoords[2])*0.1;
		dofireball(p);
	}


	public static void dofireball(final Player p) {
		double linelength = 0;

		//Calculate slope vector
		for (int i = 0; i < 3; i++) {
			slopevector[i] = targetcoords[i] - origincoords[i];
		}
		//Calculate line length
		linelength = Math.pow((Math.pow(slopevector[0], 2) + Math.pow(slopevector[1], 2) + Math.pow(slopevector[2], 2)), .5);

		//Unitize slope vector
		for (int i = 0; i < 3; i++) {
			slopevector[i] = slopevector[i] / linelength;

		}

		//Hadoken!

		final EntityPotion fireball = new EntityPotion(((CraftWorld) p.getWorld()).getHandle(), ((CraftPlayer) p).getHandle(),16388);
		final CraftThrownPotion craftfireball = new CraftThrownPotion((CraftServer) p.getServer(),fireball);
		final Vector velocity = new Vector();
		velocity.setX(slopevector[0]);
		velocity.setY(slopevector[1]);
		velocity.setZ(slopevector[2]);
		craftfireball.setVelocity(velocity);
		((CraftWorld) p.getWorld()).getHandle().addEntity(fireball);

	}
}
