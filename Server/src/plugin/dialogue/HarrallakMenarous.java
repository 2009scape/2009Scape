package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the harrallak menarous npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HarrallakMenarous extends DialoguePlugin {

	/**
	 * Constructs a new {@code HarrallakMenarous} {@code Object}.
	 */
	public HarrallakMenarous() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HarrallakMenarous} {@code Object}.
	 * @param player the player.
	 */
	public HarrallakMenarous(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HarrallakMenarous(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to my humble guild, " + player.getUsername() + ".");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Quite a place you've got here.", "You any good with a sword?", "Bye!");
			stage = 1;
			break;
		case 1:
			if (buttonId == 1) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Quite a place you've got here. Tell me more about it.");
				stage = 53;
			} else if (buttonId == 2) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You any good with a sword?");
				stage = 5;
			} else if (buttonId == 3) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye!");
				stage = 2;
			}
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Farewell, brave warrior, I do hope you enjoy my guild.");
			stage = 120;
			break;
		case 3:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You any good with a sword?");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Am I any good with a sword'? Have you any clue who I", "am?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Not really, no.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, I could best any person alive in a rapier duel!");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Try me, then!");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "My dear man, I couldn't possibly duel you, I might hurt", "you and then what would happen to my reputation!", "Besides, I have this wonderful guild to run. Why don't", " you take a look at the various activities we have.");
			stage = -8;
			break;
		case -8:
			npc("You might even collect enough tokens to be allowed in", "to kill the strange beasts from the east!");
			stage = 10;
			break;
		case 10:
			interpreter.sendOptions("Select an Option", "Tell me about the Strength training Area.", "Tell me about the Attack training area.", "Tell me about the Defence training area.", "Tell me about the Combat training area.", "Tell me about tokens.");
			stage = 11;
			break;
		case 11:
			if (buttonId == 1) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the Strength training area.");
				stage = 12;
			} else if (buttonId == 2) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the Attack training area.");
				stage = 29;
			} else if (buttonId == 3) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the Defence training area");
				stage = 16;
			} else if (buttonId == 4) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about the Combat training area");
				stage = 35;
			} else if (buttonId == 5) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me about tokens.");
				stage = 42;
			}
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh, the mighty warrior, Sloane, guards the Strength", "training area. This intriguing little area consits of two", "shotput lanes for different weights of shot. It's fairly", "simple, the referee or Sloane can explain more. There's");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "also the store room next door where Jimmy might share", "his talents with you, but don't tell him that I know", "he's not on guard duty!");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh? Why?");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, he's doing no harm and let's face it, with all these", "warriors around, the guild is hardly unguarded. You can", "find the strength area just up the stairs behind the bank.");
			stage = 10;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "To polish your defensive skills to the very highest level,", "we've employed a most intentive dwarf and a catapult.");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You're going to throw dwarves at me?");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh my, no! I think Gamfred would object to that most", "strongly.");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "He's an inventor, you see, and has built a marvellous", "contraptiont hat can throw all sorts of things at you.", "Things such as magic missiles...");
			stage = 20;
			break;
		case 20:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Mmmm?");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...spiked iron balls...");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Er...");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...spinning, slashing bladed...");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ummm...");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...and anvils.");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "...and anvils.");
			stage = 27;
			break;
		case 27:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "ANVILS?");
			stage = 28;
			break;
		case 28:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No need to be afraid, it's all under very controlled", "conditions! You can find it just up the stairs and", "behind the bank.");
			stage = 10;
			break;
		case 29:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahhh, dummies.");
			stage = 30;
			break;
		case 30:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'm no dummy, I just want to know what is there!");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh no, my dear man, I did not mean you at all! The", "training area has mechanical dummies that pop up out of", "holes in the floor. The noble dward, Gamfred, invented the", "mechanism and Ajjat can explain more about what to do");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "there.");
			stage = 33;
			break;
		case 33:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, okay, I'll have to try it out.");
			stage = 34;
			break;
		case 34:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can find it just down the corridor and on", "your right.");
			stage = 10;
			break;
		case 35:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh, yes, our redient magician from foreign lands", "created a most amazing gadget that can turn your own", "armour against you! It's really quite intriguing.");
			stage = 36;
			break;
		case 36:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "That sounds dangerous. What if I'm wearing it at the", "time?");
			stage = 37;
			break;
		case 37:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So far, that's not happend. You need to speak to", "Shanomi about the specifics of the process, but as I", "understand it, putting a suit of armour in one of these", "devices will make it come to life somehow. The better the");
			stage = 38;
			break;
		case 38:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "armour, the harder it is to defeat.");
			stage = 39;
			break;
		case 39:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Fighting my own armour sounds weird. I could be", "killed by it...");
			stage = 40;
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Indeed, we have had a few fatalities from warriors", "overstretching themselves and not knowing their limits.", "Start small and work up, is my motto! That and go see", "Lidio for some food if you need it.");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, thanks for the warning.");
			stage = 10;
			break;
		case 42:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh, yes! The tokens allow you to spend an amount of", "time with my 'discovery', located on the top floor of the", "guild. Now, the amount of tokens you collect from the", "five activities around the guild will dictate how");
			stage = -43;
			break;
		case -43:
			npc("long Kamfreena will allow in the enclosure on the very", "top floor. More tokens equals more time. There are", "also some bonuses available should you take part in all of", "the activites around the guild.");
			stage = 44;
			break;
		case 43:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "will allow in the enclosure on the very top floor. More", "tokens equals more time. There are also some bonuses", "available should you take part in all of the activites", "around the guild.");
			stage = 44;
			break;
		case 44:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, okay. So, how do i earn these tokens?");
			stage = 45;
			break;
		case 45:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You can earn them by simply using the traning exercises", "around the guild. The staff will enter your token", "earning into a ledger as you play.");
			stage = 46;
			break;
		case 46:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sounds easy enough.");
			stage = 120;
			break;
		case 47:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Should you part in all five activites around the guild", "you can choose to pay for your time on the top floor with", "tokens of all types. Should you do this then you'll find you", "spend less tokens overall and have a better chance of");
			stage = 48;
			break;
		case 48:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "getting the dragon defender, amongst other things.");
			stage = 49;
			break;
		case 49:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Excellent, sounds good. So, what's up on the top floor?");
			stage = 50;
			break;
		case 50:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, wit giving too much away, they're big and mean,", "and you get to fight them for defenders. If you're really", "lucky, they'll summon a cyclossus...although that be", "unlucky. Still, if you manage to defeat him, you could win");
			stage = 51;
			break;
		case 51:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "his hat.");
			stage = 52;
			break;
		case 52:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Interesting...I will have to explore the top floor then!");
			stage = 10;
			break;
		case 53:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes indeed. What would you like to know?");
			stage = 10;
			break;
		case 120:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4286 };
	}
}
