package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the hunting experience dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HuntingExpert extends DialoguePlugin {

	/**
	 * Represents the items used for buying skillcapes.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(9948), new Item(9949), new Item(9950) };

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 99000);

	/**
	 * Constructs a new {@code HuntingExpert} {@code Object}.
	 */
	public HuntingExpert() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HuntingExper} {@code Object}.
	 * @param player the player.
	 */
	public HuntingExpert(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HuntingExpert(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Whoah there, stranger, careful where you're walking. I", "almost clobbered you there; thought you were a larupia", "or something.");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendOptions("Select an Option", "What are you doing here?", "What's a laurpia?", "What is that cool cape you're wearing?", "Ahh, leave me alone you crazy killing person.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What are you doing here?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's a laurpia?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What is that cool cape you're wearing?");
				stage = 30;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ahh, leave me alone you crazy killing person.");
				stage = 140;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Me? Oh, I'm just having some fun, capturing wild", "animals, living on the edge, stuff like that.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hey, want me to teach you how to catch the critters", "around here? Ain't come across anything yet that I", "couldn't capture.");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "Okay, where's a good place to start?", "What sort of things are there to catch around here?", "How can I improve my chances of making a capture?", "How can I get clothes and equipment like yours?", "I think I'll give it a miss right now.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, where's a good place to start?");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What sort of things are there to catch around here?");
				stage = 110;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How can I improve my chances of making a capture?");
				stage = 120;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Where can I get clothes and equipment like yours?");
				stage = 130;
				break;
			case 5:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I think I'll give it a miss right now, thanks.");
				stage = 150;
				break;
			}
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Your easiest bet around here is probably to go for some", "of the birds. Go for crimson swifts; they're the", "bright red ones that hang around near the coast.", "They're as obliging as can be, or maybe they're just");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "plain dumb, but either way they just seem to throw", "themselves into traps half the time.");
			stage = 102;
			break;
		case 102:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What sort of trap should I use?");
			stage = 103;
			break;
		case 103:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Just use a strandard bird snare and you'll be fine. You", "can get them in any decent shop that sells Hunter", "gear.");
			stage = 104;
			break;
		case 104:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do they work, then?");
			stage = 105;
			break;
		case 105:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, they're kind of sneaky, really. You've got a tall", "post with what appears to be a little perch sticking out", "the side. Now, that the bird will see this as somewhere to sit", "but the perch is actually rigged such that when the bird");
			stage = 106;
			break;
		case 106:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "lands on it, it'll drop away.");
			stage = 107;
			break;
		case 107:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Futher, as the perch drops, it releases a noose that", "tightens around the bird's feet and captures it. Neat", "huh?");
			stage = 108;
			break;
		case 110:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What're you interested in? You've got anything from", "birds for begginers up to laurpias for the pros, not to", "mention weasels, butterflies, barb-tails and chinchompas.");
			stage = 111;
			break;
		case 111:
			end();
			break;
		case 120:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "There are four key factors that will affect your", "chances: what bait you use, how close to the trap you", "are, your appearance and your smell.");
			stage = 121;
			break;
		case 121:
			end();
			break;
		case 130:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, the equipment isn't too hard to get hold of. You've", "got specialist shops like Aleck's in Yanille that'll see you", "right. He's a bit pricey, mind, but it's pretty decent kit.");
			stage = 131;
			break;
		case 131:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The clothing is a tad trickier to get hold of, the", "materials required being rather difficult to seperate from", "their relucant owners.");
			stage = 132;
			break;
		case 132:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "But, if you can get the furs, there are shops like the", "tailor's in east Varrock that can help you.");
			stage = 133;
			break;
		case 133:
			end();
			break;
		case 140:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Heh, well I guess it's not everyone's thing.");
			stage = 141;
			break;
		case 150:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Suit yourself!");
			stage = 151;
			break;
		case 151:
			end();
			break;
		case 108:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What's a laurpia? You don't know? You mean you", "really don't recognise the significance of these clothes", "I'm wearing?");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, they're certainly decorative...what about them?");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, these are the furs from a laurpia. Caught the", "creature myself I did, and these make me blend in", "better, see?");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "If you want to be successful as a hunter you've got to", "be as stealthy as a kyatt, as quiet as a mouse and blend", "in like a, well like a larupia, I guess!");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 30:
			if (player.getSkills().getLevel(Skills.HUNTER) < 99) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "This? It's a Hunter's Skillcape. It shows that I am a", "true master of the art of Hunting. If you ever manage", "to train your Hunter skill to it maximu level then I", "could sell you one.");
				stage = 31;
			} else {
				interpreter.sendDialogues(npc, null, "An adventurer whom possesses the same level", "of Hunter as me, would you be interested in buying", "a skillcape of Hunter for 99,000 gold coins ?");
				stage = 35;
			}
			break;
		case 31:
			end();
			break;
		case 35:
			interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.");
			stage = 36;
			break;
		case 36:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, null, "Yes, please.");
				stage = 38;
				break;
			case 2:
				interpreter.sendDialogues(player, null, "Yes, please.");
				stage = 99;
				break;
			}
			break;
		case 38:
			if (!player.getInventory().contains(995, 99000)) {
				interpreter.sendDialogues(player, null, "Sorry, I dont have that amount of money.");
				stage = 99;
				break;
			} else {
				if (player.getInventory().freeSlots() < 2) {
					interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough inventory space.");
					stage = 99;
					break;
				}
				if (!player.getInventory().containsItem(COINS)) {
					end();
					return true;
				}
				if (player.getInventory().remove(COINS)) {
					player.getInventory().add(player.getSkills().getMasteredSkills() > 1 ? ITEMS[1] : ITEMS[0], ITEMS[2]);
					interpreter.sendDialogues(npc, null, "There you go! Enjoy adventurer.");
					stage = 99;
				}
			}
			break;
		case 99:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 5112, 5113 };
	}
}
