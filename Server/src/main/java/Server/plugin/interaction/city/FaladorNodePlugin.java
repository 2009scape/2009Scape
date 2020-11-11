package plugin.interaction.city;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.NPCDefinition;
import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.content.global.action.DoorActionHandler;
import core.game.node.entity.player.link.diary.DiaryType;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.impl.ForceMovement;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle node interactions in falador.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FaladorNodePlugin extends OptionHandler {

	/**
	 * Represents the opening of a cupboard animation.
	 */
	private static final Animation OPEN_ANIMATION = new Animation(536);

	/**
	 * Represents the closing of a cupboard animation.
	 */
	private static final Animation CLOSE_ANIMATION = new Animation(535);

	/**
	 * Represents the portrait item.
	 */
	private static final Item PORTRAIT = new Item(666);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2271).getHandlers().put("option:open", this);// sir
		// vyvans
		// cupboard
		// (closed)
		ObjectDefinition.forId(2272).getHandlers().put("option:shut", this);// sir
		// vyvans
		// cupboard
		// (open)
		ObjectDefinition.forId(2272).getHandlers().put("option:search", this);// sir
		// vyvans
		// cupboard
		// (open)
		// dwarven mine
		ObjectDefinition.forId(30868).getHandlers().put("option:squeeze-through", this);
		ObjectDefinition.forId(5020).getHandlers().put("option:ride", this);
		ItemDefinition.forId(245).getHandlers().put("option:take", this);
		// fally park.
		NPCDefinition.forId(2290).getHandlers().put("option:talk-to", this);
		ObjectDefinition.forId(11708).getHandlers().put("option:close", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final int id = node.getId();
		switch (id) {
		case 11708:// estate door.
			DoorActionHandler.handleDoor(player, (GameObject) node);
			break;
		case 2290:
			player.getDialogueInterpreter().open(id, node);
			return true;
		case 5020:
			player.getDialogueInterpreter().sendDialogue("You must visit Keldagrim before you are allowed to ride mine carts.");
			break;
		case 30868:
			if (player.getSkills().getLevel(Skills.AGILITY) < 42) {
				player.getPacketDispatch().sendMessage("You need an agility level of 42 to do this.");
				return true;
			}
			Location dest = player.getLocation().equals(new Location(3035, 9806, 0)) ? new Location(3028, 9806, 0) : new Location(3035, 9806, 0);
			player.animate(new Animation(2240));
			ForceMovement movement = new ForceMovement(player, player.getLocation(), dest, new Animation(2240)) {
				@Override
				public void stop() {
					super.stop();
				}
			};
			movement.run(player, 8);
			GameWorld.getPulser().submit(new Pulse(7, player) {

				@Override
				public boolean pulse() {
					player.animate(new Animation(2240));
					return true;
				}
			});
			break;
		case 2271:
			ObjectBuilder.replace((GameObject) node, ((GameObject) node).transform(2272));
			player.animate(OPEN_ANIMATION);
			break;
		case 2272:
			switch (option) {
			case "shut":
				ObjectBuilder.replace((GameObject) node, ((GameObject) node).transform(2271));
				player.animate(CLOSE_ANIMATION);
				break;
			case "search":
				if (player.getInventory().containsItem(PORTRAIT)) {
					player.getDialogueInterpreter().sendDialogue("There is just a load of junk in here.");
					return true;
				} else {

					if (!player.getInventory().add(PORTRAIT)) {
						GroundItemManager.create(PORTRAIT, player);
					}
					player.getDialogueInterpreter().sendDialogue("You find a small portrait in here which you take.");
				}
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof NPC) {
			final NPC npc = (NPC) n;
			if (npc.getId() == 2290) {
				return Location.create(2997, 3374, 0);
			}
		} else if (n instanceof GameObject) {
			if (n.getId() == 11708 && node.getLocation().equals(new Location(2981, 3370, 0))) {
				return node.getLocation();
			}
		}
		return null;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, Node node) {
		return !(node instanceof Item);
	}
}
