package plugin.command;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.SystemLogger;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.Region;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the commands related to teleporting.
 * @author 'Vexia
 * @author Emperor
 */
@InitializablePlugin
public final class TeleportCommand extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.ADMINISTRATOR);
		return this;
	}

	@Override
	public boolean parse(Player player, String name, String[] args) {
		final Player target;
		String n = "";
		switch (name) {
		case "tele":
			if (args.length == 2 && args[1].contains(",")) {
				args = args[1].split(",");
				int x = Integer.parseInt(args[1]) << 6 | Integer.parseInt(args[3]);
				int y = Integer.parseInt(args[2]) << 6 | Integer.parseInt(args[4]);
				int z = Integer.parseInt(args[0]);
				player.getProperties().setTeleportLocation(Location.create(x, y, z));
				return true;
			}
			if (args.length < 2) {
				player.debug("syntax error: x, y, (optional) z");
				return false;
			}
			player.getProperties().setTeleportLocation(Location.create(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args.length > 3 ? Integer.parseInt(args[3]) : 0));
			return true;
		case "telecs": // Client ctrl+shift teleport
			try {
				Location loc = player.getPlayerFlags().getLastSceneGraph();
				int x = ((loc.getRegionX() - 6) << 3) + toInteger(args[1]);
				int y = ((loc.getRegionY() - 6) << 3) + toInteger(args[2]);
				player.getPulseManager().clear();
				player.getProperties().setTeleportLocation(Location.create(x, y, player.getLocation().getZ()));
			} catch (Throwable t) {
				// region is changing
				t.printStackTrace();
			}
			return true;
		case "teler": // Teleports to the center of the region.
			int regionId = Integer.parseInt(args[1]);
			int x = 32;
			int y = 32;
			if (args.length > 3) {
				x = toInteger(args[2]);
				y = toInteger(args[3]);
			}
			player.getProperties().setTeleportLocation(Location.create(((regionId >> 8) << 6) + x, ((regionId & 0xFF) << 6) + y, 0));
			player.debug("Current location=" + player.getProperties().getTeleportLocation());
			return true;
		case "telers": // Teleports to the start of the region.
			regionId = Integer.parseInt(args[1]);
			player.getProperties().setTeleportLocation(Location.create(((regionId >> 8) << 6), ((regionId & 0xFF) << 6), 0));
			player.debug("Current location=" + player.getProperties().getTeleportLocation());
			return true;
		case "telere": // Teleports to the end of the region.
			regionId = Integer.parseInt(args[1]);
			player.getProperties().setTeleportLocation(Location.create(((regionId >> 8) << 6) + 63, ((regionId & 0xFF) << 6) + 63, 0));
			player.debug("Current location=" + player.getProperties().getTeleportLocation());
			return true;
		case "teleto":
			if (args.length < 1) {
				player.debug("syntax error: name");
				return true;
			}
			for (int i = 1; i < args.length; i++) {
				if (i == 1) {
					n += args[i];
					continue;
				}
				n += " " + args[i];
			}
			target = Repository.getPlayer(n);
			if (target == null) {
				player.debug("syntax error: name");
				return true;
			}
			if (target.getAttribute("fc_wave") != null) {
				player.sendMessage("You cannot teleport to a player who is in the Fight Caves.");
				return true;
			}
			player.getProperties().setTeleportLocation(target.getLocation());
			break;
		case "teletome":
			if (args.length < 1) {
				player.debug("syntax error: name");
				return true;
			}
			for (int i = 1; i < args.length; i++) {
				if (i == 1) {
					n += args[i];
					continue;
				}
				n += " " + args[i];
			}
			target = Repository.getPlayer(n);
			if (target == null) {
				player.debug("syntax error: name");
				return true;
			}
			target.getProperties().setTeleportLocation(player.getLocation());
			break;
		case "pos":
		case "position":
		case "loc":
			final Location l = player.getLocation();
			final Region r = player.getViewport().getRegion();
			player.getPacketDispatch().sendMessage("Absolute: " + l + ", regional: [" + l.getLocalX() + ", " + l.getLocalY() + "], chunk: [" + l.getChunkOffsetX() + ", " + l.getChunkOffsetY() + "], flag: [" + RegionManager.isTeleportPermitted(l) + ", " + RegionManager.getClippingFlag(l) + ", " + RegionManager.isLandscape(l) + "].");
			player.getPacketDispatch().sendMessage("Region: [id=" + l.getRegionId() + ", active=" + r.isActive() + ", instanced=" + ((r instanceof DynamicRegion)) + "], obj=" + RegionManager.getObject(l) + ".");
			player.getPacketDispatch().sendMessage("Object: " + RegionManager.getObject(l) + ".");
			SystemLogger.log("Viewport: " + l.getSceneX(player.getPlayerFlags().getLastSceneGraph()) + "," + l.getSceneY(player.getPlayerFlags().getLastSceneGraph()));
			String loc = "Location.create(" + l.getX() + ", " + l.getY() + ", " + l.getZ() + ")";
			SystemLogger.log(loc + "; "+ player.getPlayerFlags().getLastSceneGraph() + ", " + l.getLocalX() + ", " + l.getLocalY());
			StringSelection stringSelection = new StringSelection(loc);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			return true;
		}
		return false;
	}

}
