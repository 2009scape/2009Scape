package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.GameWorld;

/**
 * Represents the information clerk museum dialogue plugin
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class InformationclerkMuseumDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code InformationclerkMuseumDialogue} {@code Object}.
	 */
	public InformationclerkMuseumDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code InformationclerkMuseumDialogue} {@code Object}.
	 * @param player the player.
	 */
	public InformationclerkMuseumDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new InformationclerkMuseumDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to Varrock Museum. How can I help you today?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 150:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course. The Dig Site exhibit has several display cases", "of finds discovered on the Dig Site to the east of Varrock.");
			stage = 151;
			break;
		case 151:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "As you've passed your Earth Science exams at the Dig", "Site, you can go through into the cleaning area and clean", "off some finds. This will help our Dig Site display floor to", "give a more accurate view of life back in the 3rd and fth");
			stage = 152;
			break;
		case 152:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ages, as well as earning you Kudos. If you'd like to know", "more about cleaning finds, just ask the archaeologists.");
			stage = 153;
			break;
		case 153:
			interpreter.sendOptions("Select an Option", "Ask about something else.", "Bye");
			stage = 154;
			break;
		case 154:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What else can you inform me on?");
				stage = 0;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye!");
				stage = 200;
				break;
			}
			break;
		case 200:
			end();
			break;
		case 170:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, yes. The Timeline exhibit has lots of display cases", "showing things from the beginning of time right up to the", "present day.");
			stage = 171;
			break;
		case 171:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I know you've helped out a bit in the Timeline exhibit", "upstairs, but I'm sure you can help more. When you're out", "on your travels being a vrave adventurer, remember that", "you can come back to the Museum after some quests to");
			stage = 172;
			break;
		case 172:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "let us know important historical facts. This will help us to", "update the displays and make the Museum a more", "informative place! You'll earn yourself Kudos too.");
			stage = 173;
			break;
		case 173:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, thanks. One more question: why are the display", "numbers all out of sequence?");
			stage = 174;
			break;
		case 174:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ahh, that's due to the numbering being done as we were", "constructing the cases and putting the displays in them,", "then suffling them into the right places. We thought", "rather than renumbering them all - such a boring job,");
			stage = 175;
			break;
		case 175:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "writing labels - we'd leave it. They all have unique numbers", "and future displays would mess up the consecutive", "numbering anyway.");
			stage = 176;
			break;
		case 176:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ahhh, I see.");
			stage = 153;
			break;
		case 180:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Why, yes. The Natural History exhibit has displays of", "various creatures you can find around " + GameWorld.getName() + ".");
			stage = 181;
			break;
		case 181:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I see you have already demonstrated some of your", "knowledge of the natural world in the Natural History", "exhibit, so why not pop down and so some more quizzes", "with Orlando! You can earn Kudos at the same time.");
			stage = 182;
			break;
		case 182:
			interpreter.sendOptions("Select an Option", "But what's Natural History got to do with existing animals?", "Ask about something else.", "Bye");
			stage = 183;
			break;
		case 183:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "But what's natural history got to do with existing animals?");
				stage = 184;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What else can you inform me on?");
				stage = 0;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Bye!");
				stage = 200;
				break;
			}
			break;
		case 184:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The study of nautral history is simply the study of the", "history of the species. The species doesn't neccasrily", "need to be an extinct one.");
			stage = 153;
			break;
		case 230:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Kudos is a measure of how much you've assisted the", "Museum. The more information give us, Dig Site finds", "that you clean and quizzes you solve, the higher your", "Kudos.");
			stage = 231;
			break;
		case 231:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "But what's it for?");
			stage = 232;
			break;
		case 232:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, recently we found a rather interesting island to the", "north of Morytania. We believe that it may be of", "archaeological significance. I suspect we'll be looking for", "qualified archaeologists once we have constructed out");
			stage = 233;
			break;
		case 233:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "canal and barge. So, we're using Kudos as a measure of", "who is willing and able to help us here at the Museum, so", "they can then be invited on our dig son the new island.");
			stage = 234;
			break;
		case 234:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Would I qualify, then?");
			stage = 235;
			break;
		case 235:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Unfortunately, you haven't earned enough Kudos yet, so", "you aren't qualified to help us on the dig. If you're", "interested in helping us out and getting that Kudos, simply", "help out around the museum.");
			stage = 236;
			break;
		case 236:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, thanks.");
			stage = 237;
			break;
		case 237:
			end();
			break;
		case 0:
			interpreter.sendOptions("What would you like to do?", "Take a map of the Museum.", "Find out about the Dig Site exhibit.", "Find out about the Timeline exhibit.", "Find out abou the Natural History exhibit.", "Find out about Kudos.");
			stage = 1;
			break;
		case 1:

			switch (buttonId) {
			case 1:
				interpreter.sendDialogue("You reach and take a map of the Museum.");
				player.getInventory().add(new Item(11184, 1));
				stage = 100;

				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could you tell me about the Dig Site exhibit please?");
				stage = 150;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could you tell me about the Timeline exhibit please?");
				stage = 170;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Could you tell me about the Nautral History exhibit", "please?");
				stage = 180;
				break;
			case 5:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What is Kudos?");
				stage = 230;
				break;

			}

			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5938 };
	}
}
