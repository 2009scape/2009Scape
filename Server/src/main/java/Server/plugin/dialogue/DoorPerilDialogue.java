package plugin.dialogue;

import core.game.content.global.action.DoorActionHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.object.GameObject;

/**
 * Represents the door peril dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DoorPerilDialogue extends DialoguePlugin {

	/**
	 * Represents the game object door.
	 */
	private GameObject door;

	/**
	 * Constructs a new {@code DoorPerilDialogue} {@code Object}.
	 */
	public DoorPerilDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DoorPerilDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DoorPerilDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DoorPerilDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		door = (GameObject) args[0];
		Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		if (quest.getStage(player) == 10) {
			interpreter.sendDialogue("You knock at the door...You hear a voice from inside.", "Who are you, and what do you want?");
			stage = 0;
		}
		if (quest.getStage(player) == 12) {// just killed dog.
			interpreter.sendDialogue("You knock at the door...You hear a voice from inside.", "You again?", "What do you want now?");
			stage = 11;
		}
		if (quest.getStage(player) >= 13) {
			interpreter.sendDialogue("You knock at the door...The door swings open as you knock.");
			stage = 20;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ummmm.....");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Roald sent me to check on Drezel.");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogue("(Psst... Hey... Who's Roald? Who's Drezel?)(Uh... isn't Drezel that", "dude upstairs? Oh, wait, Roald's the King of Varrock right?)(He is???", "Aw man... Hey, you deal with this okay?) He's just coming! Wait a", "second!Hello, my name is Drevil. (Drezel!) I mean Drezel.");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, as I say, the King sent me to make sure", "everything's okay with you.");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogue("And, uh, what would you do if everything wasn't okay with me?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm not sure. Ask you what help you need I suppose.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogue("Ah, good, well, I don't think... (Psst... hey... the dog!) OH! Yes, of", "course! Will you do me a favour adventurer?");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sure. I'm a helpful person!");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogue("HAHAHAHA! Really? Thanks buddy! You see that mausoleum out", "there? There's a horrible big dog underneath it that I'd like you to", "kill for me! It's been really bugging me! Barking all the time and", "stuff! Please kill it for me buddy!");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okey-dokey, one dead dog coming up.");
			stage = 10;
			break;
		case 10:
			Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
			quest.setStage(player, 11);
			end();
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I killed that dog for you.");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogue("HAHAHAHAHA! Really? Hey, that's great! Yeah thanks a lot buddy!", "HAHAHAHAHAHA");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's so funny?");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogue("HAHAHAHA nothing buddy! We're just so grateful to you!", "HAHAHA Yeah, maybe you should go tell the King what a great job", "you did buddy! HAHAHA");
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 20:
			end();
			DoorActionHandler.handleDoor(player, door);
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 54584 };
	}
}
