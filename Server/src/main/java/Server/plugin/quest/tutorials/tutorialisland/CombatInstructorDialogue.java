package plugin.quest.tutorials.tutorialisland;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;

/**
 * Represents the dialogue plugin related to the combat instructor npc.
 * @author Vexia
 */
@InitializablePlugin
public final class CombatInstructorDialogue extends DialoguePlugin {

	/**
	 * Represents the items related to the combat instructor.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(882, 50), new Item(841, 1), new Item(1171, 1), new Item(1277, 1) };

	/**
	 * Constructs a new {@code CombatInstructorDialogue} {@code Object}.
	 */
	public CombatInstructorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CombatInstructorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CombatInstructorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CombatInstructorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		switch (TutorialSession.getExtension(player).getStage()) {
		case 44:
			interpreter.sendDialogues(player, null, "Hi! My name's " + player.getUsername() + ".");
			stage = 0;
			break;
		case 47:
			interpreter.sendDialogues(npc, null, "Very good, but that little butter knife isn't going to ", "protect you much. Here, take these.");
			stage = 0;
			break;
		case 53:
			interpreter.sendDialogues(player, null, "I did it! I killed a giant rat! ");
			stage = 0;
			break;
		case 54:
			if (!player.getInventory().containItems(841, 882)) {
				if (player.getInventory().freeSlots() > 2) {
					interpreter.sendDoubleItemMessage(ITEMS[0], ITEMS[1], "The Combat Guide gives you some <col=08088A>bronze arrows</col> and a <col=08088A>shortbow</col>!");
					player.getInventory().add(ITEMS[0], ITEMS[1]);
					stage = 3;
				} else {
					end();
					TutorialStage.load(player, 54, false);
				}
			} else {
				end();
				TutorialStage.load(player, 54, false);
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (TutorialSession.getExtension(player).getStage()) {
		case 44:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "Do I look like I care? To me you're just another", "newcomer who thinks they're ready to fight.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "I am Vannaka, the greatest swordsman alive.");
				stage = 2;
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Let's get started by teaching you to wield a weapon.");
				stage = 3;
				break;
			case 3:
				end();
				TutorialStage.load(player, 45, false);
				break;
			}
			break;
		case 54:
			end();
			TutorialStage.load(player, 54, false);
			break;
		case 47:
			switch (stage) {
			case 0:
				if (!player.getInventory().containItems(1171, 1277)) {
					if (player.getInventory().freeSlots() > 2) {
						interpreter.sendDoubleItemMessage(1171, 1277, "The Combat Guide gives you a <col=08088A>bronze sword</col> and a <col=08088A>wooden shield</col>!");
						player.getInventory().add(ITEMS[2]);
						player.getInventory().add(ITEMS[3]);
						stage = 1;
					}
				} else {
					end();
					TutorialStage.load(player, 48, false);
					stage = 1;
				}
				break;
			case 1:
				end();
				TutorialStage.load(player, 48, false);
				break;
			}
			break;
		case 53:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(npc, null, "I saw, " + player.getUsername() + ". You seem better at this than I", "thought. Now that you have grasped basic swordplay", "let's move on.");
				stage = 1;
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "Let's try some ranged attacking, with this you can kill", "foes from a distance. Also, foes unable to reach you are", "as good as dead. You'll be able to attack the rats, ", "without entering the pit.");
				stage = 2;
				break;
			case 2:
				if (!player.getInventory().containItems(841, 882)) {
					if (player.getInventory().freeSlots() > 2) {
						interpreter.sendDoubleItemMessage(ITEMS[0], ITEMS[1], "The Combat Guide gives you some <col=08088A>bronze arrows</col> and a <col=08088A>shortbow</col>!");
						player.getInventory().add(ITEMS[0], ITEMS[1]);
						stage = 3;
					} else {
						end();
						TutorialStage.load(player, 53, false);
					}
				} else {
					end();
					TutorialStage.load(player, 54, false);
				}
				break;
			case 3:
				end();
				TutorialStage.load(player, 54, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 944 };
	}
}