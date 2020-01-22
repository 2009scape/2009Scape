package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the plugin used to handle node interactions in varrock.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class VarrockNodePlugin extends OptionHandler {

	/**
	 * Represents the bronze axe item.
	 */
	private static final Item BRONZE_AXE = new Item(1351);

	/**
	 * Represents the spade item.
	 */
	private static final Item SPADE = new Item(952);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(24357).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(5581).getConfigurations().put("option:take-axe", this);
		ObjectDefinition.forId(36974).getConfigurations().put("option:take-axe", this);
		ObjectDefinition.forId(24428).getConfigurations().put("option:walk-down", this);
		ObjectDefinition.forId(1749).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(23636).getConfigurations().put("option:read", this);
		ObjectDefinition.forId(28094).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(24389).getConfigurations().put("option:knock-at", this);
		ObjectDefinition.forId(9662).getConfigurations().put("option:take", this);
		ObjectDefinition.forId(17974).getConfigurations().put("option:climb-up", this);
		ObjectDefinition.forId(29534).getConfigurations().put("option:enter", this);
		ObjectDefinition.forId(17985).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(24366).getConfigurations().put("option:climb-up", this);
		ObjectBuilder.add(new GameObject(17974, new Location(3204, 9911), 10, 0));
		new KnockatDoorDialogue().init();
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : ((Item) node).getId();
		switch (id) {
		case 24366:
			ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(3237, 3459));
			return true;
		case 29534:
			player.getDialogueInterpreter().open(543543);
			return true;
		case 17985:
			ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_DOWN, new Location(3204, 9910), "You enter the murky sewers.");
			return true;
		case 17974:
			ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(3179, 3472), "You resurface.");
			return true;
		case 24389:
			player.getDialogueInterpreter().open(KnockatDoorDialogue.ID, player.getLocation().getX() == 3182 ? 45 : 44);
			break;
		case 28094:
			player.getDialogueInterpreter().sendDialogues(player, FacialExpression.THINKING, "I don't think I should go inside.");
			break;
		case 23636:
			player.getInterfaceManager().open(new Component(531));
			break;
		case 1749:
			if (player.getLocation().getZ() == 2 && player.getLocation().getDistance(new Location(3096, 3433, 2)) < 4) {
				ClimbActionHandler.climb(player, new Animation(827), Location.create(3097, 3432, 1));
				return true;
			} else if (player.getLocation().getZ() == 1 && player.getLocation().getDistance(new Location(3095, 3433, 1)) < 4) {
				ClimbActionHandler.climb(player, new Animation(827), Location.create(3096, 3432, 0));
				return true;
			}
			ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			return true;
		case 5581:
		case 36974:
			if (!player.getInventory().add(BRONZE_AXE)) {
				player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
				return true;
			}
			ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(5582), 5000);
			break;
		case 24357:
			if (player.getLocation().getDistance(Location.create(3188, 3358, 0)) < 3) {
				ClimbActionHandler.climb(player, new Animation(828), Location.create(3188, 3354, 1));
				return true;
			}
			if (((GameObject) node).getLocation().equals(new Location(3156, 3435, 0))) {
				ClimbActionHandler.climb(player, new Animation(828), Location.create(3155, 3435, 1));
				return true;
			}
			ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			return true;
		case 24428:
			// TODO:
			return false;
		case 9662:
			if (!player.getInventory().hasSpaceFor(SPADE)) {
				player.getPacketDispatch().sendMessage("Not enough inventory space.");
				return true;
			}
			player.getInventory().add(SPADE);
			ObjectBuilder.replace((GameObject) node, ((GameObject) node).transform(0), 250);
			return true;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public boolean isWalk(final Player player, final Node node) {
		return !(node instanceof Item);
	}

	/**
	 * Represents the dialogue used for the knocking at a door in varrock bank.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class KnockatDoorDialogue extends DialoguePlugin {

		/**
		 * Represents the id of this dialogue.
		 */
		private static final int ID = 903042893;

		/**
		 * Represents the id to use.
		 */
		private int npcId;

		/**
		 * Constructs a new {@code KnockatDoorDialogue} {@code Object}.
		 */
		public KnockatDoorDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code KnockatDoorDialogue} {@code Object}.
		 * @param player the player.
		 */
		public KnockatDoorDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new KnockatDoorDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npcId = (int) args[0];
			player("I don't think I'm ever going to be allowed in there.");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				player.lock(3);
				interpreter.sendPlainMessage(true, "<col=08088A>Knock knock...");
				GameWorld.submit(new Pulse(3, player) {
					@Override
					public boolean pulse() {
						interpreter.sendDialogues(npcId, null, "Who's there?");
						stage = 1;
						return true;
					}
				});
				break;
			case 1:
				options("I'm " + player.getUsername() + ". Please let me in.", "Boo.", "Kanga.", "Thank.", "Doctor.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					player("I'm " + player.getUsername() + ". Please let me in.");
					stage = 10;
					break;
				case 2:
					player("Boo.");
					stage = 20;
					break;
				case 3:
					player("Kanga.");
					stage = 30;
					break;
				case 4:
					player("Thank.");
					stage = 40;
					break;
				case 5:
					player("Doctor.");
					stage = 50;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npcId, null, "No. Staff only beyond this point.", "You can't come in here.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npcId, null, "Boo who?");
				stage = 21;
				break;
			case 21:
				player("There's no need to cry!");
				stage = 22;
				break;
			case 22:
				interpreter.sendDialogues(npcId, FacialExpression.FURIOUS, "What? I'm not... oh, just go away!");
				stage = 23;
				break;
			case 23:
				end();
				break;
			case 30:
				interpreter.sendDialogues(npcId, null, "Kanga who?");
				stage = 31;
				break;
			case 31:
				player("No, 'kangaroo'.");
				stage = 32;
				break;
			case 32:
				interpreter.sendDialogues(npcId, FacialExpression.FURIOUS, "Stop messing about and go away!");
				stage = 33;
				break;
			case 33:
				end();
				break;
			case 40:
				interpreter.sendDialogues(npcId, null, "Thank who?");
				stage = 41;
				break;
			case 41:
				player("You're welcome!");
				stage = 42;
				break;
			case 42:
				interpreter.sendDialogues(npcId, FacialExpression.FURIOUS, "Stop it!");
				stage = 43;
				break;
			case 43:
				end();
				break;
			case 50:
				interpreter.sendDialogues(npcId, FacialExpression.FURIOUS, "Doctor. wh.. hang on, I'm not falling for that one again!", "Go away.");
				stage = 51;
				break;
			case 51:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 903042893 };
		}

	}
}
