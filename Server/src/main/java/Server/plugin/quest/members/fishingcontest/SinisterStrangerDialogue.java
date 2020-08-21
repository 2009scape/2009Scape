package plugin.quest.members.fishingcontest;

import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import plugin.dialogue.DialoguePlugin;

/**
 * Handles the SinisterStrangerDialogue dialogue.
 * @author Woah
 */

@InitializablePlugin
public class SinisterStrangerDialogue extends DialoguePlugin {

	public SinisterStrangerDialogue() {

	}

	public SinisterStrangerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) { return new SinisterStrangerDialogue(player);	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc("...");
		return true;
	}


	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
			case 0:
				interpreter.sendOptions("Choose an option:", "...?", "Who are you?", "So... you like fishing?");
				stage++;
				break;

			case 1:
				switch (buttonId) {
					case 1:
						player("...?");
						stage = 10;
						break;
					case 2:
						player("Who are you?");
						stage = 20;
						break;
					case 3:
						player("So... you like fishing?");
						stage = 30;
						break;
				} break;

			case 2:
				interpreter.sendOptions("Choose an option:", "You're a vampire aren't you?", "Is it nice there?", "So you like fishing?");
				stage++;
				break;


			case 3:
				switch (buttonId) {
					case 1:
						player("You're a vampire aren't you?");
						stage = 21;
						break;
					case 2:
						player("Is it nice there?");
						stage = 50;
						break;
					case 3:
						player("So you like fishing?");
						stage = 30;
						break;
				} break;


			case 4:
				interpreter.sendOptions("Choose an option:", "You're a vampire aren't you?", "So you like fishing?", "Well, good luck with the fishing.");
				stage++;
				break;

			case 5:
				switch (buttonId) {
					case 1:
						player("You're a vampire aren't you?");
						stage = 21;
						break;
					case 2:
						player("So you like fishing?");
						stage = 30;
						break;
					case 3:
						player("Well, good luck with the fishing.");
						stage = 70;
						break;
				} break;

			case 6:
				interpreter.sendOptions("Choose an option:", "You're a vampire aren't you?", "If you get thirsty you should drink something.", "Well, good luck with the fishing.");
				stage++;
				break;

			case 7:
				switch (buttonId) {
					case 1:
						player("You're a vampire aren't you?");
						stage = 21;
						break;
					case 2:
						player("If you get thirsty you should drink something.");
						stage = 40;
						break;
					case 3:
						player("Well, good luck with the fishing.");
						stage = 70;
						break;
				} break;


			case 9:
				end();
				break;

			case 10:
				npc("...");
				stage++;
				break;
			case 11:
				player("......?");
				stage++;
				break;
			case 12:
				npc("......");
				stage = 9;
				break;

			case 20:
				npc("My name is Vlad.", "I come from far avay,", "vere the sun iz not so bright.");
				stage = 2;
				break;

			case 21:
				npc("Just because I can't stand ze smell ov garlic", "and I don't like bright sunlight doesn't", "necessarily mean I'm ein vampire!");
				stage = 9;
				break;


			case 30:
				npc("My doctor told me to take up ein velaxing hobby.", "Vhen I am stressed I tend to get ein little");
				stage++;
				break;
			case 31:
				npc("... thirsty.");
				stage = 6;
				break;

			case 40:
				npc("I tsink I may do zat soon...");
				stage = 9;
				break;



			case 50:
				npc("It is vonderful!", "Zev omen are beautiful und ze nights are long!");
				stage = 4;
				break;

			case 70:
				npc("Luck haz notsing to do vith it.", "It is all in ze technique.");
				stage = 9;
				break;





		}
		return true;
	}




	@Override
	public int[] getIds() {
		return new int[] { 3677 };
	}
}
