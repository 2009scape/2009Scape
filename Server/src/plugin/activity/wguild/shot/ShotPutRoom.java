package plugin.activity.wguild.shot;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the shot put room.
 * @author Emperor
 */
@InitializablePlugin
public final class ShotPutRoom extends DialoguePlugin {

	static {
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public boolean handle(final Player player, Node node, String option) {
				final boolean lowWeight = node.getId() == 15664;
				if (node instanceof GroundItem) {
					player.getDialogueInterpreter().sendDialogues(4300, FacialExpression.FURIOUS, "Hey! You can't take that, it's guild property. Take one", "from the pile.");
					return true;
				}
				if (player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) != null || player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) != null || player.getEquipment().get(EquipmentContainer.SLOT_HANDS) != null) {
					player.getDialogueInterpreter().sendDialogue("To throw the shot you need your hands free!");
					return true;
				}
				if (player.getSettings().getRunEnergy() < 10) {
					player.getDialogueInterpreter().sendDialogue("You're too exhausted to throw the shot at this time. Take a break.");
					return true;
				}
				player.lock(4);
				player.animate(Animation.create(827));
				GameWorld.submit(new Pulse(2) {
					@Override
					public boolean pulse() {
						player.faceLocation(player.getLocation().transform(3, 0, 0));
						player.getDialogueInterpreter().open("shot_put", lowWeight);
						return true;
					}
				});
				return true;
			}

			@Override
			public Location getDestination(Node n, Node node) {
				if (node instanceof GameObject) {
					return node.getLocation().transform(0, -1, 0);
				}
				return null;
			}

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(15664).getConfigurations().put("option:throw", this);
				ObjectDefinition.forId(15665).getConfigurations().put("option:throw", this);
				ItemDefinition.forId(8858).getConfigurations().put("option:take", this);
				ItemDefinition.forId(8859).getConfigurations().put("option:take", this);
				return this;
			}

		});
	}

	/**
	 * If the low weight shot put is used.
	 */
	private boolean lowWeight;

	/**
	 * Constructs a new {@code ShotPutRoom} {@code Object}.
	 */
	public ShotPutRoom() {
		super();
	}

	/**
	 * Constructs a new {@code ShotPutRoom} {@code Object}.
	 * @param player The player.
	 */
	public ShotPutRoom(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ShotPutRoom(player);
	}

	@Override
	public boolean open(Object... args) {
		lowWeight = (Boolean) args[0];
		interpreter.sendOptions("Choose your style", "Standing throw", "Step and throw", "Spin and throw");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (buttonId) {
		case 1:
			player.animate(Animation.create(4181));
			throwShotPut(player, 5, lowWeight, "You throw the shot as hard as you can.");
			break;
		case 2:
			player.animate(Animation.create(4182));
			throwShotPut(player, 2, lowWeight, "You take a step and throw the shot as hard as you can.");
			break;
		case 3:
			player.animate(Animation.create(4183));
			throwShotPut(player, 5, lowWeight, "You spin around and release the shot.");
			break;
		default:
			return false;
		}
		end();
		return true;
	}

	/**
	 * Throws the shot put.
	 * @param player The player.
	 * @param delay The delay.
	 * @param lowWeight If the low weight shot put was thrown.
	 */
	private static void throwShotPut(final Player player, int delay, final boolean lowWeight, final String message) {
		player.lock();
		int cost = lowWeight ? 6 : 12;
		if (player.getAttribute("hand_dust", false)) {
			player.removeAttribute("hand_dust");
			cost = 0;
		}
		int distance = 1;
		if (RandomFunction.randomize(25 - cost) != 0) {
			int mod = (int) (RandomFunction.randomize(cost) * (1 - (player.getSettings().getRunEnergy() / (100 + cost))));
			distance += RandomFunction.randomize(12 - mod);
		}
		final boolean failed = distance < 2;
		final int tiles = distance;
		player.getPacketDispatch().sendMessage("You take a deep breath and prepare yourself.");
		GameWorld.submit(new Pulse(delay, player) {
			Location loc = player.getLocation();
			boolean thrown;

			@Override
			public boolean pulse() {
				if (!thrown) {
					player.getSettings().updateRunEnergy(10);
					player.getPacketDispatch().sendMessage(message);
					if (failed) {
						player.getPacketDispatch().sendMessage("You fumble and drop the shot on your toe. Ow!");
						player.getImpactHandler().manualHit(player, 1, HitsplatType.NORMAL, 2);
					}
					int speed = (int) (30 + (tiles * 10));
					Projectile projectile = Projectile.create(loc, loc = loc.transform(tiles, 0, 0), 690, 40, 0, getDelay() == 5 ? 5 : 12, speed, 20, 11);
					projectile.send();
					setDelay(1 + (int) Math.ceil(tiles * 0.3));
					thrown = true;
					return false;
				}
				player.unlock();
				if (!failed) {
					player.getSavedData().getActivityData().updateWarriorTokens(tiles + (!lowWeight ? 2 : 0));
					player.getSkills().addExperience(Skills.STRENGTH, tiles);
					player.getDialogueInterpreter().sendDialogues(lowWeight ? 4299 : 4300, FacialExpression.NO_EXPRESSION, "Well done. You threw the shot " + (tiles - 1) + " yard" + (tiles > 2 ? "s!" : "!"));
				}
				GroundItemManager.create(new GroundItem(new Item(lowWeight ? 8858 : 8859), loc, 20, player));
				return true;
			}
		});
	}

	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("shot_put") };
	}

}
