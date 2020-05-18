package plugin.activity.wguild;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.global.action.DoorActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles the warrior guild options.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class WarriorsGuild extends OptionHandler {

	/**
	 * The token item id.
	 */
	public static final int TOKEN = 8851;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(15653).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(1530).getConfigurations().put("option:open", this);
		NPCDefinition.forId(4287).getConfigurations().put("option:claim-shield", this);
		NPCDefinition.setOptionHandler("claim-tokens", this);
		PluginManager.definePlugin(new ClaimTokenDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 15653:
		case 1530:
			if (node.getId() == 1530 && !node.getLocation().equals(new Location(2837, 3549, 0))) {
				DoorActionHandler.handleDoor(player, (GameObject) node);
				return true;
			}
			if (canEnter(player)) {
				player.getMusicPlayer().unlock(634);
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			} else {
				player.getDialogueInterpreter().sendDialogues(4285, null, "You not pass. You too weedy.");
			}
			break;

		default:
			switch (option) {
			case "claim-shield":
				player.getDialogueInterpreter().open(4287, node, true);
				break;
			case "claim-tokens":
				player.getDialogueInterpreter().open("wg:claim-tokens", node.getId());
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Checks if a player can enter the guild.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private boolean canEnter(final Player player) {
		return player.getSkills().getStaticLevel(Skills.ATTACK) + player.getSkills().getStaticLevel(Skills.STRENGTH) >= 130 || player.getSkills().getStaticLevel(Skills.ATTACK) == 99 || player.getSkills().getStaticLevel(Skills.STRENGTH) == 99;
	}

	/**
	 * The dialogue used to handle the claiming of tokens.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ClaimTokenDialogue extends DialoguePlugin {

		/**
		 * The npc id being used.
		 */
		private int npcId;

		/**
		 * Constructs a new {@code ClaimTokenDialogue} {@code Object}.
		 */
		public ClaimTokenDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ClaimTokenDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ClaimTokenDialogue(final Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ClaimTokenDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npcId = (Integer) args[0];
			player("May I claim my tokens please?");
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			int tokens = player.getSavedData().getActivityData().getWarriorGuildTokens();
			switch (stage) {
			case 0:
				if (tokens < 1) {
					interpreter.sendDialogues(npcId, null, "I'm afraid you have not earned any tokens yet. Try", "some of the activities around the guild to earn some.");
					stage = 3;
				} else {
					interpreter.sendDialogues(npcId, null, "Of course! Here you go, you've earned " + tokens + " tokens!");
					stage++;
				}
				break;
			case 1:
				final Item item = new Item(TOKEN, tokens);
				if (!player.getInventory().hasSpaceFor(item)) {
					player("Sorry, I don't seem to have enough inventory space.");
					stage++;
					break;
				}
				player.getSavedData().getActivityData().setWarriorGuildTokens(0);
				player.getInventory().add(item);
				player("Thanks!");
				stage++;
				break;
			case 2:
				end();
				break;
			case 3:
				player("Ok, I'll go see what I can find.");
				stage--;
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("wg:claim-tokens") };
		}

	}
}
