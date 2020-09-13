package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.game.world.repository.Repository;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.quest.free.dragonslayer.DragonSlayer;

/**
 * Represents the option handler for port sarim.
 * @author 'Vexia
 * @date 27/11/2013
 */
@InitializablePlugin
public class PortsObjectPlugin extends OptionHandler {

	/**
	 * Represents the locations of crossing a gang plank at port sarim.
	 */
	private final Location PORT_SARIM[] = new Location[] {/*
														 * port sarim - entrana
														 * boat
														 */
	new Location(3048, 3231, 1), Location.create(3048, 3234, 0), new Location(3038, 3192, 0), new Location(3038, 3189, 1), new Location(3032, 3217, 1), new Location(3029, 3217, 0), new Location(3041, 3199, 1), new Location(3041, 3202, 0) };

	/**
	 * Represents the entrana locations.
	 */
	private final Location ENTRANA[] = new Location[] { /* entrana boat */
	new Location(2834, 3331, 1), new Location(2834, 3335, 0) };

	/**
	 * Represents the karamja locations.
	 */
	private final Location KARAMJA[] = new Location[] { new Location(2956, 3143, 1), new Location(2956, 3146, 0), new Location(2957, 3158, 1), new Location(2954, 3158, 0) };

	/**
	 * Represents the catherby locations.
	 */
	private final Location CATHERBY[] = new Location[] { new Location(2792, 3417, 1), new Location(2792, 3414, 0) };

	/**
	 * Represents the location teleport to in brimhaven.
	 */
	private final Location BRIMHAVEN[] = new Location[] { new Location(2763, 3238, 1), Location.create(2760, 3238, 0), new Location(2775, 3234, 1), new Location(2772, 3234, 0) };

	/**
	 * Represents the ardougne location.
	 */
	private final Location ARDOUGNE[] = new Location[] { new Location(2683, 3268, 1), new Location(2683, 3271, 0) };

	/**
	 * Represents the port khazard location.
	 */
	private final Location PORT_KHAZARD[] = new Location[] { new Location(2674, 3141, 1), new Location(2674, 3144, 0) };

	/**
	 * represents the port phastmatys locations.
	 */
	private final Location PORT_PHASMATYS[] = new Location[] { new Location(3705, 3503, 1), new Location(3702, 3503, 0) };

	/**
	 * Represents the pest control location.
	 */
	private final Location PEST_CONTROL[] = new Location[] { new Location(2662, 2676, 1), new Location(2659, 2676, 0) };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2412).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// entrana
		// boat.
		ObjectDefinition.forId(2413).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// entrana
		// boat.
		ObjectDefinition.forId(17404).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// charter
		// boat.
		ObjectDefinition.forId(17405).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// charter
		// boat.
		ObjectDefinition.forId(2083).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// regular
		// boat.
		ObjectDefinition.forId(2084).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// regular
		// boat.
		ObjectDefinition.forId(14304).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// regular
		// boat.
		ObjectDefinition.forId(14305).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// regular
		// boat.
		ObjectDefinition.forId(2593).getHandlers().put("option:cross", this);// port
		// sarim
		// -
		// lady
		// lumbridge.
		ObjectDefinition.forId(2415).getHandlers().put("option:cross", this);// entrana
		// off
		// boat.
		ObjectDefinition.forId(2414).getHandlers().put("option:cross", this);// entrana
		// on
		// boat.
		ObjectDefinition.forId(2081).getHandlers().put("option:cross", this);// karamaja
		// boat
		// (on)
		ObjectDefinition.forId(2082).getHandlers().put("option:cross", this);// karamaja
		// boat
		// (off)
		ObjectDefinition.forId(17398).getHandlers().put("option:cross", this);// karamaja
		// boat
		// (on)(second)
		ObjectDefinition.forId(17399).getHandlers().put("option:cross", this);// karamaja
		// boat
		// (off)(second)
		ObjectDefinition.forId(17394).getHandlers().put("option:cross", this);// catherby
		// boat
		// (on)
		ObjectDefinition.forId(17395).getHandlers().put("option:cross", this);// catherby
		// boat
		// (off)
		ObjectDefinition.forId(69).getHandlers().put("option:cross", this);// catherby
		// boat
		// (on)(second)
		ObjectDefinition.forId(17401).getHandlers().put("option:cross", this);// brimhaven
		// (off)
		ObjectDefinition.forId(17400).getHandlers().put("option:cross", this);// brimhaven
		// (on)
		ObjectDefinition.forId(2087).getHandlers().put("option:cross", this);// brimhaven
		// (on)(second)
		ObjectDefinition.forId(2088).getHandlers().put("option:cross", this);// brimhaven
		// (off)(second)
		ObjectDefinition.forId(2086).getHandlers().put("option:cross", this);// ardougne
		// (off)
		ObjectDefinition.forId(2085).getHandlers().put("option:cross", this);// ardougne
		// (on)
		ObjectDefinition.forId(17402).getHandlers().put("option:cross", this);// port
		// khazard
		// (on)
		ObjectDefinition.forId(17403).getHandlers().put("option:cross", this);// port
		// khazard
		// (off)
		ObjectDefinition.forId(17392).getHandlers().put("option:cross", this);// port
		// phasmatys(on)
		ObjectDefinition.forId(17393).getHandlers().put("option:cross", this);// port
		// phasmatys(off)
		ObjectDefinition.forId(11209).getHandlers().put("option:cross", this);// port
		// phasmatys(other
		// boat)
		ObjectDefinition.forId(14307).getHandlers().put("option:cross", this);// pest
		// control
		// ship.
		ObjectDefinition.forId(14306).getHandlers().put("option:cross", this);// pest
		// control
		// ship.
		// lady lumby
		ObjectDefinition.forId(2590).getHandlers().put("option:climb-down", this);
		ObjectDefinition.forId(2592).getHandlers().put("option:climb-up", this);
		ObjectDefinition.forId(2594).getHandlers().put("option:cross", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = ((GameObject) node).getId();
		switch (option) {
		case "climb-up":
			ClimbActionHandler.climb(player, new Animation(828), new Location(3048, 3208, 1));
			break;
		case "climb-down":
			ClimbActionHandler.climb(player, new Animation(828), new Location(3048, 9640, 1));
			break;
		case "cross":
			switch (id) {
			case 2594:
				cross(player, Location.create(3047, 3204, 0));
				break;
			case 14306:
				cross(player, PEST_CONTROL[0]);
				player.getPacketDispatch().sendMessage("You board the ship.");
				break;
			case 14307:
				cross(player, PEST_CONTROL[1]);
				player.getPacketDispatch().sendMessage("You disembark the ship.");
				break;
			case 2593:
				if (player.getQuestRepository().getQuest(DragonSlayer.NAME).getStage(player) == 100) {
					player.getDialogueInterpreter().open(744, Repository.findNPC(744), true);// lady
					// lumbridge.
					return true;
				}
				if (!player.getSavedData().getQuestData().getDragonSlayerAttribute("ship")) {
					player.getDialogueInterpreter().open(744, Repository.findNPC(744), true);// lady
					// lumbridge.
				} else {
					player.getPacketDispatch().sendMessage("You board the ship.");
					cross(player, new Location(3047, 3207, 1));
				}
				break;
			case 14304:
				cross(player, PORT_SARIM[6]);
				player.getPacketDispatch().sendMessage("You board the ship.");
				break;
			case 14305:
				cross(player, PORT_SARIM[7]);
				player.getPacketDispatch().sendMessage("You disembark the ship.");
				break;
			case 2083:
				cross(player, PORT_SARIM[4]);// port - sarim random
				// boat.
				break;
			case 2084:
				cross(player, PORT_SARIM[5]);// port - sarim random
				// boat.
				break;
			case 17404:
				cross(player, PORT_SARIM[3]);// port sarim charter boat.
				break;
			case 17405:
				cross(player, PORT_SARIM[2]);// port sarim charter boat.
				break;
			case 11209:
				player.getDialogueInterpreter().sendDialogues(player, null, "I don't think that whoever owns this ship will be happy", "with me wandering all over it.");
				break;
			case 17392:
				cross(player, PORT_PHASMATYS[0]);// port phasmatys on.
				break;
			case 17393:
				cross(player, PORT_PHASMATYS[1]);// port phasmatys off.
				break;
			case 17402:
				cross(player, PORT_KHAZARD[0]);// port khazard on.
				break;
			case 17403:
				cross(player, PORT_KHAZARD[1]);// port khazard off.
				break;
			case 2086:
				cross(player, ARDOUGNE[1]);// ardougne off
				break;
			case 2085:
				cross(player, ARDOUGNE[0]);// ardougne on.
				player.getPacketDispatch().sendMessage("You must speak to Captain Barnaby before it will set sail.");
				break;
			case 2087:
				cross(player, BRIMHAVEN[2]);// brimhaven second ship(pay
				// fare)
				player.getPacketDispatch().sendMessage("You must speak to the Customs Officer before it will set sail.");
				break;
			case 2088:
				cross(player, BRIMHAVEN[3]);// brimhaven second ship(pay
				// fare)
				break;
			case 17401:
				cross(player, BRIMHAVEN[1]);// entrana on boat.
				break;
			case 17400:
				cross(player, BRIMHAVEN[0]);// entrana off boat.
				break;
			case 69:// arhein ship.
				player.getDialogueInterpreter().open(563, Repository.findNPC(563), true);
				break;
			case 17394:
				cross(player, CATHERBY[0]);// catherby on
				break;
			case 17395:
				cross(player, CATHERBY[1]);// catherby off
				break;
			case 2412:// port-sarim to entrana(on boat)
				cross(player, PORT_SARIM[0]);
				break;
			case 2413:// port-sarim to entrana(off boat)
				cross(player, PORT_SARIM[1]);
				break;
			case 2414:// entrana on boat.
				cross(player, ENTRANA[0]);
				break;
			case 2415:// entrana off boat.
				cross(player, ENTRANA[1]);
				break;
			case 2081:
				cross(player, KARAMJA[0]);
				break;
			case 2082:
				cross(player, KARAMJA[1]);
				break;
			case 17398:
				cross(player, KARAMJA[2]);
				player.getPacketDispatch().sendMessage("You must speak to the Customs Officer before it will set sail.");
				break;
			case 17399:
				cross(player, KARAMJA[3]);
				player.getPacketDispatch().sendMessage("You must speak to the Customs Officer before it will set sail.");
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to cross a gang plank.
	 * @param player the player.
	 * @param location
	 */
	public final void cross(final Player player, Location location) {
		player.getProperties().setTeleportLocation(location);
	}
}
