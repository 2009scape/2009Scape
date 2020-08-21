package plugin.quest.members.lostcity;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Handles the warriors dialogue in the lost city quest.
 * @author Vexia
 * @version 1.0
 */
public final class WarriorDialogue extends DialoguePlugin {

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code WarriorDialogue} {@code Object}.
	 */
	public WarriorDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code WarriorDialogue} {@code Object}.
	 * @param player the player.
	 */
	public WarriorDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new WarriorDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Lost City");
		switch (quest.getStage(player)) {
		case 10:
			player("So let me get this straight: I need to search the trees", "around here for a leprechaun; and then when I find", "him, he will tell me where this 'Zanaris' is?");
			break;
		case 20:
		case 21:
			player("Have you found anything yet?");
			break;
		case 100:
			player("Hey, thanks for all the information. It REALLY helped", "me out in finding the lost city of Zanaris and all.");
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there traveller.");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 0:
			handleFirstStage(buttonId);
			break;
		case 10:
			switch (stage) {
			case 0:
				npc("What? How did you know that? Uh... I mean, no, no", "you're very wrong. Very wrong, and not right at all,", "and I definitely didn't tell you about that at all.");
				stage++;
				break;
			case 1:
				end();
				break;
			}
			break;
		case 20:
		case 21:
			switch (stage) {
			case 0:
				npc("We're still searching for Zanaris...GAH! I mean we're", "not doing anything here at all.");
				stage++;
				break;
			case 1:
				player("I haven't found it yet either.");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 100:
			switch (stage) {
			case 0:
				npc("Oh please don't say that anymore! If the rest of my", "party knew I'd helped you they'd probably throw me", "out and make me walk home by myself!");
				stage++;
				break;
			case 1:
				end();
				break;
			}
			break;

		}
		return true;
	}

	/**
	 * Handles the first dialogue quest stage.
	 * @param buttonId the buttonId.
	 */
	private void handleFirstStage(int buttonId) {
		switch (stage) {
		case 0:
			options("Why are you camped out here for?", "Do you know any good adventures I can go on?");
			stage++;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("What are you camped here for?");
				stage++;
				break;
			case 2:
				player("Do you know any good adventurers I can go on?");
				stage = 50;
				break;
			}
			break;
		case 2:
			npc("We're looking for Zanaris...GAH! I mean we're not", "here for any particular reason at all.");
			stage++;
			break;
		case 3:
			options("Who's Zanaris?", "What's Zanaris?", "What makes you think it's out here?");
			stage++;
			break;
		case 4:
			switch (buttonId) {
			case 1:
				player("Who's Zanaris?");
				stage = 10;
				break;
			case 2:
				player("What's Zanaris?");
				stage = 20;
				break;
			case 3:
				player("What makes you think it's out here?");
				stage = 30;
				break;
			}
			break;
		case 10:
			npc("Ahahahaha! Zanaris isn't a person! It's a magical hidden", "city filled with treasure and rich... uh, nothing. It's", "nothing.");
			stage = 31;
			break;
		case 20:
			npc("I don't think we want other people competing with us to", "find it. Forget I said anything.");
			stage = 34;
			break;
		case 30:
			npc("Don't you know of the legends that tell of the magical", "city, hidden in the swamp... Uh, no, you're right, we're", "wasting our time here.");
			stage++;
			break;
		case 31:
			options("If it's hidden how are you planning to find it?", "There's no such thing!");
			stage++;
			break;
		case 32:
			switch (buttonId) {
			case 1:
				player("If it's hidden how are you planning to find it?");
				stage = 33;
				break;
			case 2:
				player("There's no such thing!");
				stage = 33;
				break;
			}
			break;
		case 33:
			npc("Well, we don't want to tell anyone else about that,", "because we don't want anyone else sharing in all that", "glory and treasure.");
			stage++;
			break;
		case 34:
			options("Please tell me.", "Looks like you don't know either.");
			stage++;
			break;
		case 35:
			switch (buttonId) {
			case 1:
				player("Please tell me?");
				stage = 37;
				break;
			case 2:
				player("Well, it looks to me like YOU don't know EITHER", "seeing as you're all just sat around here.");
				stage = 60;
				break;
			}
			break;
		case 37:
			npc("No.");
			stage++;
			break;
		case 38:
			player("Please?");
			stage++;
			break;
		case 39:
			npc("No!");
			stage++;
			break;
		case 40:
			player("PLEEEEEEEEEEEEEEEEEEEEEEEEESE???");
			stage = 42;
			break;
		case 42:
			npc("NO!");
			stage++;
			break;
		case 43:
			end();
			break;
		case 41:
			npc("When we've found Zanaris you'll... GAH! I mean, we're", "not here for any particular reason at all.");
			stage = 3;
			break;
		case 50:
			npc("Well we're on an adventure right now. Mind you, this", "is OUR adventure and we don't want to share it - find", "your own!");
			stage++;
			break;
		case 51:
			end();
			break;
		case 60:
			if (quest.hasRequirements(player)) {
				npc("Of course we know! We just haven't found which tree", "the stupid leprechaun's hiding in yet!");
				stage++;
			} else {
				npc("When we've found Zanaris you'll... GAH! I mean, we're", "not here for any particular reason at all.");
				stage = 3;
			}
			break;
		case 61:
			npc("GAH! I didn't mean to tell you that! Look, just forget I", "said anything okay?");
			stage++;
			break;
		case 62:
			player("So a leprechaun knows where Zanaris is eh?");
			stage++;
			break;
		case 63:
			npc("Ye.. uh, no. No, not at all. And even if he did - which", "he doesn't he DEFINITELY ISN'T hiding in some", "tree around here. Nope, definitely not. Honestly.");
			stage++;
			break;
		case 64:
			player("Thanks for the help!");
			stage++;
			break;
		case 65:
			npc("Help? What help? I didn't help! Please don't say I did,", "I'll get in trouble!");
			stage++;
			break;
		case 66:
			end();
			quest.start(player);
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 650 };
	}
}