package plugin.activity.pyramidplunder;

import plugin.activity.ActivityManager;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;

/**
 * Handles Guardian mummy dialogue.
 * @author Originally Emperor, completely redone by Ceikry.
 */
public final class GuardMummyDialogue extends DialoguePlugin {

	/**
	 * If the player successfully caught the evil twin.
	 * Will remain unused until the quest is implemented. (if ever)
	 */
	//private int type;


	/**
	 * Constructs a new {@code GuardMummyDialogue} {@code Object}.
	 */
	public GuardMummyDialogue() {super();}
	public GuardMummyDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GuardMummyDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(args.length > 1){
			switch((int) args[1]){
				case 0:
					npc("You! How did you get into this place?");
					stage = 50;
					break;
				case 1:
					player("I know what I'm doing - let's get on with it.");
					stage = 10;
					break;
			}
		} else {
			npc("Errr");
		}
		/*type = args.length == 1 ? 0 : (Integer) args[1];
		if (type == 1) {
			player("I know what I'm doing - let's get on with it.");
		} else {
			npc("You! How did you get in?");
		}*/
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch(stage){
			case 10:
				npc("Fine, I'll take you to the first room now...");
				stage = 60;
				break;
			case 50:
				player("I could ask you the same thing.");
				stage = 51;
				break;
			case 51:
				npc("Nevermind that. I suppose since you're here I can tell", "you how to play.");
				stage = 52;
				break;
			case 52:
				npc("In each room there are urns, a chest, and a sarcophagus.");
				stage = 53;
				break;
			case 53:
				npc("The urns contain artifacts based on the room that you are","currently in. The chest contains valuable artifacts but is","sometimes trapped. The sarcophagus has one of my","brothers in it. And lots of artifacts.");
				stage = 54;
				break;
			case 54:
				player("Anything else I should know?");
				stage = 55;
				break;
			case 55:
				npc("Yes, each room requires more thieving knowledge than the"," last. And be careful not to die.");
				stage = 56;
				break;
			case 56:
				player("Oh, yes, dying. Not good. Well I suppose that covers it?");
				stage = 57;
				break;
			case 57:
				npc("Yep.");
				stage = 58;
				break;
			case 58:
				end();
				break;
			case 60:
				end();
				ActivityManager.start(player, "Pyramid plunder", false);
				break;

		}
		/*switch (stage++) {
		case 0:
			if (type == 1) {
				npc("Fine, I'll take you to the first room now...");
			} else {
				System.out.println("TODO:2");
			}
			return true;
		case 1:
			if (type == 1) {
				end();
				ActivityManager.start(player, "Pyramid plunder", false);
			} else {
				System.out.println("TODO:3");
			}
			return true;
		}*/
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4476, 4477 };
	}

}