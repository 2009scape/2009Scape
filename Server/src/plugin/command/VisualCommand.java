package plugin.command;

import org.crandor.cache.Cache;
import org.crandor.game.content.global.tutorial.CharacterDesign;
import org.crandor.game.content.skill.member.farming.wrapper.PatchWrapper;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.IronmanMode;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.SystemLogger;
import org.crandor.game.system.command.CommandPlugin;
import org.crandor.game.system.command.CommandSet;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.InterfaceContext;
import org.crandor.net.packet.out.Interface;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Represents the the command plugin used for visual commands.
 * @author Vexia
 * @author Emperor
 * 
 */
@InitializablePlugin
public final class VisualCommand extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.ADMINISTRATOR);
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean parse(final Player player, String name, String[] args) {
		Location location = null;
		GameObject object = null;
		Player o = null;

		switch (name) {
		case "invisible":
		case "invis":
		case "seti":
			player.setInvisible(!player.isInvisible());
			player.sendMessage("You are now "+(player.isInvisible() ? "invisible" : "rendering")+" for other players.");
			break;
		case "maxkc":
			for (int i = 0; i < 6; i++) {
				player.getSavedData().getActivityData().getBarrowBrothers()[i] = true;
			}
			String[] names = new String[] { "Ahrim", "Dharok", "Guthan", "Karil", "Torag", "Verac" };
			player.getSavedData().getActivityData().setBarrowKills(50);
			player.getPacketDispatch().sendMessage("Flagged all barrow brothers killed and 50 catacomb kills, current entrance: " + names[player.getSavedData().getActivityData().getBarrowTunnelIndex()] + ".");
			return true;
		case "1hko":
			player.setAttribute("1hko", !player.getAttribute("1hko", false));
			player.getPacketDispatch().sendMessage("1-hit KO mode " + (player.getAttribute("1hko", false) ? "on." : "off."));
			return true;
		case "anim":
		case "emote":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) delay");
				return true;
			}
			final Animation animation = new Animation(Integer.parseInt(args[1]), args.length > 2 ? Integer.parseInt(args[2]) : 0);
			player.animate(animation);
			return true;
		case "gfx":
		case "graphic":
		case "graphics":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) height delay");
				return true;
			}
			player.graphics(new Graphics(Integer.parseInt(args[1]), args.length > 2 ? Integer.parseInt(args[2]) : 0, args.length > 3 ? Integer.parseInt(args[3]) : 0));
			return true;
		case "sync":
		case "visual":
			if (args.length < 3) {
				player.debug("syntax error: anim_id gfx_id (optional) height");
				return true;
			}
			int animId = toInteger(args[1]);
			int gfxId = toInteger(args[2]);
			int height = args.length > 3 ? toInteger(args[3]) : 0;
			player.visualize(Animation.create(animId), new Graphics(gfxId, height));
			return true;
		case "pos_graphic":
		case "position_gfx":
		case "pos_gfx":
		case "lgfx":
			if (args.length < 2) {
				player.debug("syntax error: id x y (optional) height delay");
				return true;
			}
			location = Location.create(Integer.parseInt(args[2]), Integer.parseInt(args[3]), args.length > 4 ? Integer.parseInt(args[4]) : 0);
			player.getPacketDispatch().sendPositionedGraphic(Integer.parseInt(args[1]), args.length > 5 ? Integer.parseInt(args[5]) : 0, args.length > 6 ? Integer.parseInt(args[6]) : 0, location);
			break;
		case "npc":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) direction");
				return true;
			}
			NPC npc = NPC.create(toInteger(args[1]), player.getLocation());
			npc.setAttribute("spawned:npc", true);
			npc.setRespawn(false);
			npc.setDirection(player.getDirection());
			npc.init();
			npc.setWalks(args.length > 2 ? true : false);
			String npcString = "{" + npc.getLocation().getX() + "," + npc.getLocation().getY() + "," + npc.getLocation().getZ() + "," + (npc.isWalks() ? "1" : "0") + "," + npc.getDirection().ordinal() + "}";
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(new StringSelection(npcString), null);
			System.out.println(npcString);
			return true;
		case "npcsquad":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) sizeX sizeY");
				return true;
			}
			int sizeX = 3;
			int sizeY = 3;
			if (args.length > 2) {
				sizeX = toInteger(args[2]);
				if (args.length > 3) {
					sizeY = toInteger(args[3]);
				} else {
					sizeY = sizeX;
				}
			}
			boolean aggressive = args.length > 4;
			for (int x = 0; x < sizeX; x++) {
				for (int y = 0; y < sizeY; y++) {
					npc = NPC.create(toInteger(args[1]), player.getLocation().transform(1 + x, 1 + y, 0));
					npc.setAttribute("spawned:npc", true);
					npc.setAggressive(aggressive);
					npc.init();
					npc.setRespawn(false);
					npc.setWalks(aggressive);
				}
			}
			return true;
		case "oib":
			player.getInterfaceManager().openInfoBars();
			break;
		case "char":
			CharacterDesign.open(player);
			break;
		case "savenpc":
			
			return true;
		case "object":
		case "obj":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) type rotation or rotation");
				return true;
			}
			object = args.length > 3 ? new GameObject(toInteger(args[1]), player.getLocation(), toInteger(args[2]), toInteger(args[3])) : args.length == 3 ? new GameObject(toInteger(args[1]), player.getLocation(), toInteger(args[2])) : new GameObject(toInteger(args[1]), player.getLocation());
			ObjectBuilder.add(object);
			SystemLogger.log("object = " + object);
			return true;
		case "objwithanim":
			GameObject go = new GameObject(toInteger(args[1]), player.getLocation(), 0);
			ObjectBuilder.add(go);
			player.getPacketDispatch().sendObjectAnimation(go, Animation.create(toInteger(args[2])));
			return true;
		case "oa":
		case "object_anim":
		case "obj_anim":
		case "objectanim":
		case "objanim":
			if (args.length < 2) {
				player.debug("syntax error: x y id");
				return true;
			}
			location = args.length > 2 ? Location.create(Integer.parseInt(args[1]), Integer.parseInt(args[2]), 3) : player.getLocation();
			object = RegionManager.getObject(location);
			if (object == null) {
				player.debug("error: object not found in region cache.");
				return true;
			}
			player.getPacketDispatch().sendObjectAnimation(object, new Animation(toInteger(args[args.length - 1])));
			return true;
		case "inter":
		case "component":
		case "interface":
			if (args.length < 2) {
				player.debug("syntax error: interface-id");
				return true;
			}
			int componentId = toInteger(args[1]);
			if (componentId < 0 || componentId > Cache.getInterfaceDefinitionsSize()) {
				player.debug("Invalid component id [id=" + componentId + ", max=" + Cache.getInterfaceDefinitionsSize() + "].");
				return true;
			}
			player.getInterfaceManager().openComponent(componentId);
			return true;
		case "ti":
			player.getPacketDispatch().sendInterfaceConfig(90, 87, false);
			break;
		case "iconfig":
		case "inter_config":
			if (args.length < 2) {
				player.debug("syntax error: interface-id child hidden");
				return true;
			}
			boolean hidden = args.length > 3 ? Boolean.parseBoolean(args[3]) : true;
			player.getPacketDispatch().sendInterfaceConfig(toInteger(args[1]), toInteger(args[2]), hidden);
			player.getPacketDispatch().sendMessage("Interface child (id=" + args[1] + ", child=" + args[2] + ") is " + (hidden ? "hidden." : "visible."));
			return true;
		case "saveconfig":
		case "config":
			if (args.length < 2) {
				player.debug("syntax error: config-id (optional) value");
				return true;
			}
			if (name.equals("saveconfig")) {
				player.getConfigManager().set(toInteger(args[1]), args.length > 2 ? toInteger(args[2]) : 0, true);
			} else {
				player.getConfigManager().send(toInteger(args[1]), args.length > 2 ? toInteger(args[2]) : 0);
			}
			return true;
		case "loop_inter":
			final int st = toInteger(args[1]);
			final int en = args.length > 2 ? toInteger(args[2]) : 740;
			GameWorld.submit(new Pulse(3, player) {
				int id = st;

				@Override
				public boolean pulse() {
//					PacketRepository.send(Interface.class, new InterfaceContext(player, 548, 77, id, false));
					player.getInterfaceManager().openComponent(id);
					player.debug("Interface id: " + id);
					return ++id >= en;
				}
			});
			return true;
		case "loop_config":
		case "config_loop":
			if (args.length < 4) {
				player.debug("syntax error: config-id start end value");
				return true;
			}
			int value = toInteger(args[3]);
			for (int i = toInteger(args[1]); i < toInteger(args[2]); i++) {
				player.getConfigManager().set(i, value);
			}
			return true;
		case "string":
			if (args.length < 3) {
				player.debug("syntax error: interface child text");
				return true;
			}
			player.getPacketDispatch().sendString(args[3], toInteger(args[1]), toInteger(args[2]));
			return true;
		case "loop_string":
		case "string_loop":
			if (args.length < 3) {
				player.debug("syntax error: interface min max");
				return true;
			}
			int interfaceId = toInteger(args[1]);
			for (int i = toInteger(args[2]); i < toInteger(args[3]); i++) {
				player.getPacketDispatch().sendString("child=" + i, interfaceId, i);
			}
			return true;
		case "loop_oa":
			final int startId = toInteger(args[1]);
			final int endId = args.length > 2 ? toInteger(args[2]) : 11000;
			GameWorld.submit(new Pulse(3, player) {
				int id = startId;

				@Override
				public boolean pulse() {
					GameObject object = RegionManager.getObject(player.getLocation());
					if (object == null) {
						player.debug("error: object not found in region cache.");
						return true;
					}
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(id));
					player.debug("Animation id: " + id);
					return ++id >= endId;
				}
			});
			return true;
		case "loop_anim":
			final int start = toInteger(args[1]);
			final int end = args.length > 2 ? toInteger(args[2]) : 11000;
			GameWorld.submit(new Pulse(3, player) {
				int id = start;

				@Override
				public boolean pulse() {
					player.animate(Animation.create(id));
					player.debug("Animation id: " + id);
					return ++id >= end;
				}
			});
			return true;
		case "loop_gfx":
			final int s = toInteger(args[1]);
			final int e = args.length > 2 ? toInteger(args[2]) : 11000;
			GameWorld.submit(new Pulse(3, player) {
				int id = s;

				@Override
				public boolean pulse() {
					Projectile.create(player.getLocation(), player.getLocation().transform(0, 3, 0), id, 42, 36, 46, 75, 5, 11).send();
					player.graphics(new Graphics(id, 96));
					player.debug("Graphic id: " + id);
					return ++id >= e;
				}
			});
			return true;
		case "removenpc":
			player.setAttribute("removenpc", !player.getAttribute("removenpc", false));
			player.debug("You have set remove npc value to " + player.getAttribute("removenpc", false) + ".");
			return true;
		case "pnpc":
			if (args.length < 2) {
				player.debug("syntax error: id");
				return true;
			}
			player.getAppearance().transformNPC(toInteger(args[1]));
			return true;
		case "itemoni":
			int inter = toInteger(args[1]);
			int child = toInteger(args[2]);
			int item = args.length > 3 ? toInteger(args[3]) : 1038;
			player.getPacketDispatch().sendItemZoomOnInterface(item, 270, inter, child);
			return true;
		case "hit":
			player.getImpactHandler().manualHit(player, toInteger(args[1]), HitsplatType.NORMAL);
			return true;
		case "sound":
			player.getAudioManager().send(new Audio(Integer.parseInt(args[1]), 10, 1));
			return true;
		case "noclip":
			player.setAttribute("no_clip", !player.getAttribute("no_clip", false));
			return true;
		case "grow":
			for (PatchWrapper wrapper : player.getFarmingManager().getPatches()) {
				if (wrapper == null || wrapper.getPatch() == null || wrapper.getCycle() == null || wrapper.getCycle().getGrowthHandler() == null) {
					continue;
				}
				wrapper.getCycle().getGrowthHandler().handle();
			}
			return true;
		case "disabledisease":
			player.setAttribute("stop-disease", !player.getAttribute("stop-disease", false));
			player.sendMessage("Disable disease=" + player.getAttribute("stop-disease", false));
			return true;
		case "rake":
			for (PatchWrapper wrapper : player.getFarmingManager().getPatches()) {
				wrapper.getCycle().clear(player);
			}
			return true;
		case "full":
			for (PatchWrapper wrapper : player.getFarmingManager().getPatches()) {
				for (int i = 0; i < 20; i++) {
					if (wrapper == null || wrapper.getPatch() == null || wrapper.getCycle() == null || wrapper.getCycle().getGrowthHandler() == null) {
						continue;
					}
					wrapper.getCycle().getGrowthHandler().handle();
				}
			}
			return true;
		case "toreg"://these fucking kids are so goddam annoying
			o = Repository.getPlayer(args[1]);
			o.getIronmanManager().setMode(IronmanMode.NONE);
			player.sendMessage("done...");
			o.sendMessage("<col=FF0000>You are no longer an ironman. Log out to see the ironman icon disappear.</col>");
			break;
		case "clearpatches":
			if (args.length > 1) {
				o = Repository.getPlayer(args[1]);
			}
			if (o != null) {
				for (PatchWrapper wrapper : o.getFarmingManager().getPatches()) {
					wrapper.getCycle().clear(o);
				}
				o.sendMessage("Your patches have been cleared.");
				player.sendMessage("You cleared " + o.getUsername() + "'s patches.");
				return true;
			}
			for (PatchWrapper wrapper : player.getFarmingManager().getPatches()) {
				wrapper.getCycle().clear(player);
			}
			return true;
		}
		return false;
	}

}
