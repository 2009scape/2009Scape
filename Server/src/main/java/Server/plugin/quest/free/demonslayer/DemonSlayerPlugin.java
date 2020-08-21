package plugin.quest.free.demonslayer;

import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.Plugin;

/**
 * Represents the demon slayer plugin used to handle relative node interactions.
 * @author 'Vexia
 * @date 3/1/14
 */
public final class DemonSlayerPlugin extends OptionHandler {

	/**
	 * Represents the object id of the drain.
	 */
	public static final int DRAIN_ID = 17424;

	/**
	 * Represents the location of the lumbridge sewers.
	 */
	private static final Location SEWER_LOCATION = new Location(3237, 9858, 0);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(881).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(882).getConfigurations().put("option:close", this);
		ObjectDefinition.forId(882).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(DRAIN_ID).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(17429).getConfigurations().put("option:take", this);
		NPCDefinition.forId(DemonSlayerCutscene.DELRITH).getConfigurations().put("option:attack", this);
		NPCDefinition.forId(DemonSlayerCutscene.WEAKEND_DELRITH).getConfigurations().put("option:banish", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Demon Slayer");
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : node instanceof Item ? ((Item) node).getId() : ((NPC) node).getId();
		switch (id) {
		case 880:
			player.getDialogueInterpreter().open(8427322);
			break;
		case 879:
			if (!player.getEquipment().containsItem(DemonSlayer.SILVERLIGHT)) {
				player.getPacketDispatch().sendMessage("I'd better wield Silverlight first.");
				return true;
			}
			player.face(((NPC) node));
			player.getPulseManager().clear("interaction:attack:" + node.hashCode());
			player.getProperties().getCombatPulse().attack(node);
			return true;
		case DRAIN_ID:
			if (quest.getStage(player) == 20) {
				player.getDialogueInterpreter().open(883, 883, "key");
				return true;
			} else {
				player.sendMessage("You search the castle drain and find nothing of value.");
			}
			return true;
		case 881:
			ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(882));
			break;
		case 882:
			switch (option) {
			case "climb-down":
				if (node.getLocation().equals(new Location(3237, 3458, 0))) {
					ClimbActionHandler.climb(player, new Animation(828), SEWER_LOCATION);
				} else {
					ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				}
				break;
			case "close":
				ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(881));
				break;
			}
			break;
		case 17429:
			if (quest.getStage(player) == 20 && player.getInventory().add(DemonSlayer.FIRST_KEY)) {
				player.getConfigManager().set(222, 4757762, true);
				player.removeAttribute("demon-slayer:poured");
				player.removeAttribute("demon-slayer:just-poured");
				player.getDialogueInterpreter().sendItemMessage(DemonSlayer.FIRST_KEY.getId(), "You pick up an old rusty key.");
			} else {
				if (player.getInventory().freeSlots() == 0) {
					player.getPacketDispatch().sendMessage("Not enough inventory space.");
					return true;
				}
			}
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(final Node node, final Node n) {
		if (n instanceof NPC) {
			return n.getLocation().transform(2, 0, 0);
		}
		if (n.getId() == DRAIN_ID) {
			return Location.create(3226, 3496, 0);
		}
		return null;
	}

}
