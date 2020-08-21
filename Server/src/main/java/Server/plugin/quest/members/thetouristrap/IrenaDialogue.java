package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;

/**
 * Represents the irena dialogue used during tourist trap.
 * @author 'Vexia
 * @version 1.0
 */
public final class IrenaDialogue extends DialoguePlugin {

	/**
	 * The array of skills to choose.
	 */
	private static final int[] SKILLS = new int[] { Skills.FLETCHING, Skills.AGILITY, Skills.SMITHING, Skills.THIEVING };

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code IrenaDialogue} {@code Object}.
	 */
	public IrenaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code IrenaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public IrenaDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new IrenaDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		if (quest.getStage(player) == 95 && player.getInventory().containsItem(TouristTrap.ANNA_BARREL)) {
			npc("Hey, great you've found Ana!");
			stage = 900;
			return true;
		}
		switch (quest.getStage(player)) {
		case 98:
			npc("Thank you very much for returning my daughter to", "me. I'm really very grateful... I would like to reward", "you for your bravery and daring.");
			stage++;
			break;
		case 0:
			interpreter.sendDialogue("Irena seems to be very upset and cries as you approach her.");
			break;
		case 100:
		case 95:
		case 99:
			player.getPacketDispatch().sendMessage("Irena seems happy now that her daughter has returned home.");
			npc("Thanks so much for returning my daughter to me.", "I expect that she will go on another trip soon though.", "She is the adventurous type... a bit like yourself really!", "Okay, see you around then!");
			break;
		default:
			npc("*Sob* Have you found my daughter yet?");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 900:
			interpreter.sendDialogues(823, null, "<col=08088A>-- You show Irena the barrel with Ana in it. --", "Hey great, there's my Mum!");
			stage++;
			return true;
		case 901:
			player.getInventory().remove(TouristTrap.ANNA_BARREL);
			end();
			quest.setStage(player, 98);
			player.getDialogueInterpreter().open(822);
			return true;
		}
		switch (quest.getStage(player)) {
		case 98:// reward.
			switch (stage) {
			case 1:
				npc("I can offer you increased knowledge in two of the", "following areas.");
				stage++;
				break;
			case 2:
				options("Fletching.", "Agility.", "Smithing.", "Thieving.");
				stage++;
				break;
			case 3:
				player.setAttribute("first-skill", buttonId);
				npc("Okay, now choose your second skills.");
				stage++;
				break;
			case 4:
				options("Fletching.", "Agility.", "Smithing.", "Thieving.");
				stage++;
				break;
			case 5:
				player.setAttribute("second-skill", buttonId);
				npc("Okay, that's all the skills I can teach you!");
				stage++;
				break;
			case 6:
				int skills[] = new int[] { SKILLS[player.getAttribute("first-skill", 0) - 1], SKILLS[player.getAttribute("second-skill", 0) - 1] };
				for (int i : skills) {
					player.getSkills().addExperience(i, 4650);
				}
				quest.finish(player);
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 0:
				if (quest.hasRequirements(player)) {
					npc("Boo hoo! Oh dear, my only daughter....");
					stage++;
				} else {
					npc("Boo hoo! Go away!");
					stage = 99;
				}
				break;
			case 1:
				player("What's the matter?");
				stage++;
				break;
			case 2:
				npc("Oh dear... my daughter, Ana, has gone missing in the", "desert. I fear that she is lost, or perhaps... *sob* even", "worse.");
				stage++;
				break;
			case 3:
				player("When did she go into the desert?");
				stage++;
				break;
			case 4:
				npc("*Sob* she went in there just a few days ago, *Sob*", "she said she would be back yesterday.");
				stage++;
				break;
			case 5:
				npc("*Sob* And she's not...");
				stage++;
				break;
			case 6:
				player("Is there a reward if I get her back?");
				stage++;
				break;
			case 7:
				npc("Well, yes, you'll have my gratitude young man.");
				stage++;
				break;
			case 8:
				npc("And I'm sure that Ana will also be very pleased! And I", "may see if I can get a small reward together... But I", "cannot promise anything. So does that mean that you'll", "look for her then?");
				stage++;
				break;
			case 9:
				player("Okay Irena, calm down. I'll get your daughter back for", "you.");
				stage++;
				break;
			case 10:
				npc("That would be great! That's really very nice of you!", "She was wearing a red silk scarf when she left.");
				stage++;
				break;
			case 11:
				npc("Are you 'really' sure you want to do this? I mean, go", "on this quest?");
				stage++;
				break;
			case 12:
				player("Yes, I'll go on this quest!");
				stage++;
				break;
			case 13:
				npc("Oh thank you! You've made me a very happy mother, I", "just hope it's not too late!");
				stage++;
				break;
			case 14:
				player("Do you have any other hints on where she may have", "gone?");
				stage++;
				break;
			case 15:
				npc("I did go looking for her myself and I came across some", "footprints just a little way south. I'm worried that they", "lead to the desert mining camp.");
				stage++;
				break;
			case 16:
				quest.start(player);
				end();
				break;
			case 99:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				player("No, not yet.");
				stage++;
				break;
			case 1:
				npc("Please! Hurry up.");
				stage++;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 100:
		case 95:
		case 99:
			player.getPacketDispatch().sendMessage("Irena goes back to work.");
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4986 };
	}

}
