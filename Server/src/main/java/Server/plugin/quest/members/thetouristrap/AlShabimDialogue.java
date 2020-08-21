package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;

/**
 * The dialogue used for the al shabim npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class AlShabimDialogue extends DialoguePlugin {

	/**
	 * The bronze darts item.
	 */
	private static final Item BRONZE_DARTS = new Item(806, 6);

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code AlShabimDialogue} {@code Object}.
	 */
	public AlShabimDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AlShabimDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AlShabimDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AlShabimDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (quest.getStage(player)) {
		default:
			npc("Hello Effendi!");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 54:// build tool.
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(TouristTrap.PROTOTYPE_DART)) {
					npc("Wonderful, I see you have made the new weapon!");
					stage = 20;
				} else {
					if (!player.hasItem(TouristTrap.BEDABIN_KEY)) {
						player("I seemed to have lost the key...");
						stage++;
					} else {
						npc("Have you figured out the plans yet?");
						stage = 3;
					}
				}
				break;
			case 1:
				npc("Ahh Effendi, it's a good thing I have a spare!");
				stage++;
				break;
			case 2:
				player.getInventory().add(TouristTrap.BEDABIN_KEY, player);
				interpreter.sendItemMessage(TouristTrap.BEDABIN_KEY, "Al Shabim gives you a key.");
				stage = 11;
				break;
			case 3:
				player("No, not yet.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				interpreter.sendItemMessage(TouristTrap.PROTOTYPE_DART, "You show Al Shabrim the prototype dart.");
				;
				stage++;
				break;
			case 21:
				npc("This is truly fantastic Effendi!");
				stage++;
				break;
			case 22:
				if (player.getInventory().containsItem(TouristTrap.TECHNICAL_PLANS)) {
					npc("We will take the technical plans for the weapon as well.");
					stage++;
				} else {
					player.getInventory().remove(TouristTrap.TECHNICAL_PLANS);
					npc("We are forever grateful for this gift. My advisor have", "discovered some secrets which we will share with you.");
					stage = 25;
				}
				break;
			case 23:
				interpreter.sendItemMessage(TouristTrap.TECHNICAL_PLANS, "You hand over the technical plans for the weapon.");
				stage++;
				break;
			case 24:
				player.getInventory().remove(TouristTrap.TECHNICAL_PLANS);
				npc("We are forever grateful for this gift. My advisor have", "discovered some secrets which we will share with you.");
				stage++;
				break;
			case 25:
				interpreter.sendDialogue("Al Shabim's advisors show you some advanced techniques for making", "the new weapon.");
				stage++;
				break;
			case 26:
				npc("Please accept this selection of six bronze throwing darts", "as a token of our appreciation.");
				stage++;
				break;
			case 27:
				player.getInventory().add(BRONZE_DARTS, player);
				interpreter.sendItemMessage(806, "You receive six bronze throwing darts from Al Shabim.");
				stage = player.getInventory().containsItem(TouristTrap.BEDABIN_KEY) ? 28 : 29;
				break;
			case 28:
				player.getInventory().remove(TouristTrap.BEDABIN_KEY);
				npc("I'll take that key off your hands as well effendi! Many", "thanks.");
				stage++;
				break;
			case 29:
				quest.setStage(player, 60);
				player.getInventory().remove(TouristTrap.PROTOTYPE_DART);
				player.getInventory().add(TouristTrap.TENTI_PINEAPPLE);
				interpreter.sendItemMessage(806, "<col=08088A>*** Dart Construction ***", "Congratulations! You can now construct darts.");
				stage++;
				break;
			}
			break;
		case 50:
			switch (stage) {
			case 0:
				npc("I am Al Shabim, greetings on behalf of the Bedabin", "nomads. Now... what can I do for you?");
				stage++;
				break;
			case 1:
				player("I am looking for a pineapple.");
				stage++;
				break;
			case 2:
				npc("Oh yes, well that is interesting. Our sweet pineapples", "are renowned throughout the whole of Kharid! and I'll", "give you one if you do me a favour?");
				stage++;
				break;
			case 3:
				player("Oh yes?");
				stage++;
				break;
			case 4:
				npc("Captain Siad at the mining camp is holding some secret", "information. It is very important to us and we would", "like you to get it for us. It gives details of an", "interesting, yet ancient weapon.");
				stage++;
				break;
			case 5:
				npc("We would gladly share this information with you. All", "you have to do is gain access to his private room", "upstairs. We have a key for the chest that contains this", "information. Are you interested in our deal?");
				stage++;
				break;
			case 6:
				player("Yes, I'm interested.");
				stage++;
				break;
			case 7:
				npc("That's great Effendi!");
				stage++;
				break;
			case 8:
				npc("Here is a copy of the key that should give you access", "to the chest.");
				stage++;
				break;
			case 9:
				quest.setStage(player, 51);
				player.getInventory().add(TouristTrap.BEDABIN_KEY, player);
				interpreter.sendItemMessage(TouristTrap.BEDABIN_KEY, "Al Shabim gives you a key.");
				stage++;
				break;
			}
			break;
		case 51:
		case 52:
		case 53:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(TouristTrap.TECHNICAL_PLANS)) {
					npc("Aha! I see you have the plans. This is great! However,", "these plans do indeed look very technical. My people", "have further need of your skills.");
					stage = 20;
				} else {
					if (!player.hasItem(TouristTrap.BEDABIN_KEY)) {
						player("I seemed to have lost the key...");
						stage++;
					} else {
						npc("Have you found the plans yet?");
						stage = 3;
					}
				}
				break;
			case 1:
				npc("Ahh Effendi, it's a good thing I have a spare!");
				stage++;
				break;
			case 2:
				player.getInventory().add(TouristTrap.BEDABIN_KEY, player);
				interpreter.sendItemMessage(TouristTrap.BEDABIN_KEY, "Al Shabim gives you a key.");
				stage = 11;
				break;
			case 3:
				player("No, sorry. I'm still looking for them.");
				stage++;
				break;
			case 4:
				npc("Okay, Effendi!");
				stage = 11;
				break;
			case 10:
				npc("Bring us back the plans inside the chest, they should be", "sealed. All haste to you Effendi!");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("If you can help us to manufacture this item, we will", "share its secret with you. Does this deal interest you", "effendi?");
				stage++;
				break;
			case 21:
				player("Yes, I'm very interested.");
				stage++;
				break;
			case 22:
				npc("Okay Effendi, you need to follow the plans. You will", "need some special tools for this...  There is an anvil in", "the other tent. You have my permission to use it, but", "show the plans to the guard.");
				stage++;
				break;
			case 23:
				quest.setStage(player, 54);
				end();
				break;
			}
			break;
		case 100:
		case 60:
		case 70:
		case 80:
		case 90:
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 60 && !player.hasItem(TouristTrap.TENTI_PINEAPPLE)) {
					player.getInventory().add(TouristTrap.TENTI_PINEAPPLE, player);
					interpreter.sendItemMessage(TouristTrap.TENTI_PINEAPPLE, "You receive a tasty looking pineapple from Al Shabim.");
					stage++;
					stage = 32;
					break;
				}
				npc("Many thanks with helping previously Effendi!");
				stage++;
				break;
			case 30:
				npc("Oh, and here is your pineapple!");
				stage++;
				break;
			case 31:
				interpreter.sendItemMessage(TouristTrap.TENTI_PINEAPPLE, "You receive a tasty looking pineapple from Al Shabim.");
				stage++;
				break;
			case 32:
				end();
				break;
			case 1:
				end();
				break;
			}
			break;
		default:
			switch (stage) {
			case 0:
				player("Hi");
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

	@Override
	public int[] getIds() {
		return new int[] { 832 };
	}

}
