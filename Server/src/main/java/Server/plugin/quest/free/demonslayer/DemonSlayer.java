package plugin.quest.free.demonslayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Represents the demon slayer quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class DemonSlayer extends Quest {

	/**
	 * Represents the silverlight item.
	 */
	public static final Item SILVERLIGHT = new Item(2402);

	/**
	 * Represents the incantations we can use to generate a total incantation.
	 */
	private static final String[] INCANTATIONS = new String[] { "Carlem", "Aber", "Purchai", "Camerinthum", "Gabindo" };

	/**
	 * Represents the first key item.
	 */
	public static final Item FIRST_KEY = new Item(2401);

	/**
	 * Represents the second key item.
	 */
	public static final Item SECOND_KEY = new Item(2400);

	/**
	 * Represents the third key item.
	 */
	public static final Item THIRD_KEY = new Item(2399);

	/**
	 * Constructs a new {@Code DemonSlayer} {@Code Object}
	 */
	public DemonSlayer() {
		super("Demon Slayer", 16, 15, 3, 222, 0, 1, 3);
	}
	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugins(new DemonSlayerPlugin(), new DSlayerDrainPlugin(), new DemonSlayerCutscene(), new WallyCutscenePlugin(), new GypsyArisDialogue(), new SirPyrsinDialogue(), new TraibornDialogue(), new CaptainRovinDialogue());
		return this;
	} 

	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		switch (getStage(player)) {
		case 0:
			player.getPacketDispatch().sendString(BLUE + "I can start this quest by speaking to the " + RED + "Gypsy " + BLUE + "in the " + RED + "tent", 275, 4 + 8);
			player.getPacketDispatch().sendString(BLUE + "in " + RED + "Varrock's main square", 275, 5+ 8);
			player.getPacketDispatch().sendString(BLUE + "I must be able to defeat a level 27 " + RED + "apocalyptic demon" + BLUE + "!", 275, 7+ 8);
			break;
		case 10:
			line(player, "<str>I spoke to the Gypsy in Varrock Square who saw my future.", 4+ 8);
			line(player, "<str>Unfortunately it involved killing a demon who nearly", 5+ 8);
			line(player, "<str>destroyed Varrock over 150 years ago.", 6+ 7);
			line(player, BLUE + "To defeat the " + RED + "demon " + BLUE + "I need the magical sword " + RED + "Silverlight.", 8+ 8);
			line(player, BLUE + "I should ask " + RED + "Sir Prysin " + BLUE + "in " + RED + "Varrock Palace " + BLUE + "where it is.", 9+ 8);
			break;
		case 20:
			line(player, "<str>I spoke to the Gypsy in Varrock Square who saw my future.", 4+ 8);
			line(player, "<str>Unfortunately it involved killing a demon who nearly", 5+ 8);
			line(player, "<str>destroyed Varrock over 150 years ago.", 6+ 8);
			line(player, BLUE + "To defeat the " + RED + "demon " + BLUE + "I need the magical sword " + RED + "Silverlight.", 8+ 8);
			line(player, RED + "Sir Prysin " + BLUE + "needs " + RED + "3 keys " + BLUE + "before he can give me " + RED + "Silverlight.", 9+ 8);
			if (player.getInventory().containsItem(FIRST_KEY) && player.getInventory().containsItem(SECOND_KEY) && player.getInventory().containsItem(THIRD_KEY)) {
				line(player, BLUE + "Now I have " + RED + "all 3 keys " + BLUE + "I should go and speak to " + RED + "Sir Prysin", 9+ 8);
				line(player, BLUE + "and collect the magical sword " + RED + "Silverlight " + BLUE + "from him.", 10+ 8);
			} else {
				line(player, player.hasItem(FIRST_KEY) ? "<str>I have the 1st Key with me." : BLUE + "The " + RED + "1st Key " + BLUE + "was dropped down the palace kitchen drains.", 11+ 8);
				line(player, player.hasItem(SECOND_KEY) ? "<str>I have the 2nd Key with me." : BLUE + "The " + RED + "2nd Key " + BLUE + "is with Captain Rovin in Varrock Palace.", 12+ 8);
				line(player, player.hasItem(THIRD_KEY) ? "<str>I Have the 3rd key with me." : BLUE + "The " + RED + "3rd Key " + BLUE + "is with the Wizard Traiborn at the Wizards' Tower.", 13+ 8);
				if (player.getAttribute("demon-slayer:traiborn", false)) {
					line(player, BLUE + "The " + RED + "3rd Key " + BLUE + "is with Wizard Traiborn at the Wizards' Tower.", 13);
					line(player, RED + "Traiborn " + BLUE + "needs " + RED + "25  " + BLUE + "more " + RED + "bones.", 14);
				}
			}
			break;
		case 30:
			line(player, "<str>I spoke to the Gypsy in Varrock Square who saw my future.", 4+ 8);
			line(player, "<str>Unfortunately it involved killing a demon who nearly", 5+ 8);
			line(player, "<str>destroyed Varrock over 150 years ago.", 6+ 8);
			line(player, "<str>I reclaimed the magical sword Silverlight from Sir Prysin.", 8+ 8);
			line(player, BLUE + "Now I should go to the stone circle south of the city and", 9+ 8);
			line(player, BLUE + "destroy " + RED + "Delrith " + BLUE + "using " + RED + "Silverlight" + BLUE + "!", 10+ 8);
			break;
		case 100:
			line(player, "<str>I spoke to the Gypsy in Varrock Square who saw my future.", 4+ 8);
			line(player, "<str>Unfortunately it involved killing a demon who nearly", 5+ 8);
			line(player, "<str>destroyed Varrock over 150 years ago.", 6+ 7);
			line(player, "<str>I reclaimed the magical sword Silverlight from Sir Prysin.", 8+ 8);
			line(player, "<str>Using its power I managed to destroy the demon Delrith", 9+ 8);
			line(player, "<str>like the great hero Wally did many years before.", 10+ 8);
			line(player, "<col=FF0000>QUEST COMPLETE!", 12+ 7);
			break;
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("3 Quests Points", 277, 8+ 2);
		player.getPacketDispatch().sendString("Silverlight", 277, 9+ 2);
		player.getPacketDispatch().sendItemZoomOnInterface(SILVERLIGHT.getId(), 230, 277, 5);
		player.removeAttribute("demon-slayer:traiborn");
		player.removeAttribute("demon-slayer:incantation");
		player.removeAttribute("demon-slayer:poured");
		player.removeAttribute("demon-slayer:received");
		player.getQuestRepository().syncronizeTab(player);
	}

	/**
	 * Gets the incantation the player was given.
	 * @param player the player.
	 * @return the incantation given.
	 */
	public static String getIncantation(final Player player) {
		return player.getAttribute("demon-slayer:incantation", generateIncantation(player));
	}

	/**
	 * Generates an incantation.
	 * @param player the player.
	 * @return the incantation.
	 */
	public static String generateIncantation(final Player player) {
		final String incantation = player.getAttribute("demon-slayer:incantation", generateIncantation());
		player.setAttribute("/save:demon-slayer:incantation", incantation);
		return incantation;
	}

	/**
	 * Gets the generated incantation.
	 * @return the incantation.
	 */
	private final static String generateIncantation() {
		List<String> incantations = new ArrayList<>();
		for (String s : INCANTATIONS) {
			incantations.add(s);
		}
		Collections.shuffle(incantations);
		return incantations.get(0) + "... " + incantations.get(1) + "... " + incantations.get(2) + "... " + incantations.get(3) + "... " + incantations.get(4);
	}
	/**
	 * Represents the dialogue plugin used for the captain rovin npc.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class CaptainRovinDialogue extends DialoguePlugin {

		/**
		 * Represents the quest instance.
		 */
		private Quest quest;

		/**
		 * Constructs a new {@code CaptainRovin} {@code Object}.
		 */
		public CaptainRovinDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code CaptainRovin} {@code Object}.
		 * @param player the player.
		 */
		public CaptainRovinDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new CaptainRovinDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			quest = player.getQuestRepository().getQuest("Demon Slayer");
			switch (quest.getStage(player)) {
			default:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What are you doing up here? Only the palace guards", "are allowed up here.");
				stage = 0;
				break;
			}
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (quest.getStage(player)) {
			default:
				defaultDialogue(buttonId);
				break;
			}
			return true;
		}

		/**
		 * Method used to handle the default dialogue.
		 * @param buttonId the button id.
		 */
		private final void defaultDialogue(int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "I am one of the palace guards.", "What about the King?", "Yes I know, but this is important.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I am one of the palace guards.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What about the King? Surely you'd let him up here.");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, I know but this is important.");
					stage = 30;
					break;
				}
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok, I'm listening. Tell me what's so important.");
				stage = 31;
				break;
			case 31:
				if (quest.getStage(player) == 20) {
					player("There's a demon who wants to invade the city.");
					stage = 600;
					return;
				}
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Erm... I forgot.");
				stage = 32;
				break;
			case 32:
				end();
				break;
			case 21:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, yes I suppose we'd let him up, He doesn't", "generally want to come up here, but if he did want to,", "he could.");
				stage = 21;
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, you're not! I know all the palace guards.");
				stage = 11;
				break;
			case 11:
				end();
				break;
			case 600:
				if (player.getInventory().containsItem(DemonSlayer.SECOND_KEY) || player.getBank().containsItem(DemonSlayer.SECOND_KEY)) {
					npc("Yes, you said before, haven't you killed it yet?");
					stage = 620;
					return;
				}
				npc("Is it a powerful demon?");
				stage = 601;
				break;
			case 601:
				player("Yes, very.");
				stage = 602;
				break;
			case 602:
				npc("As good as the palace guards are, I don't know if", "they're up to taking on a very powerful demon.");
				stage = 603;
				break;
			case 603:
				player("It's not them who are going to fight the demon, it's me.");
				;
				stage = 604;
				break;
			case 604:
				npc("What, all by yourself? How are you going to do that?");
				stage = 605;
				break;
			case 605:
				player("I'm going to use the powerful sword Silverlight, which I", "believe you have one of the keys for?");
				stage = 606;
				break;
			case 606:
				npc("Yes, I do. But why should I give it to you?");
				stage = 607;
				break;
			case 607:
				player("Sir Prysin said you would give me the key.");
				stage = 608;
				break;
			case 608:
				npc("Oh, he did, did he? Well I don't report to Sir Prysin, I", "report directly to the king!");
				stage = 609;
				break;
			case 609:
				npc("I didn't work my way up through the ranks of the", "palace guards so I could take orders from an ill-bred", "moron who only has his job because his great-", "grandfather was a hero with a silly name!");
				stage = 610;
				break;
			case 610:
				player("Why did he give you one of the keys then?");
				stage = 611;
				break;
			case 611:
				npc("Only because the king ordered him to! The king", "couldn't get Sir Prysin to part with his precious", "ancestral sword, but he made him lock it up so he", "couldn't lose it.");
				stage = 612;
				break;
			case 612:
				npc("I got one key and I think some wizard got another.", "Now what happened to the third one?");
				stage = 613;
				break;
			case 613:
				player("Sir Prysin dropped it down a drain!");
				stage = 614;
				break;
			case 614:
				npc("Ha ha ha! The idiot!");
				stage = 615;
				break;
			case 615:
				npc("Okay, I'll give you the key, just so that it's you that", "kills the demon and not Sir Prysin!");
				stage = 616;
				break;
			case 616:
				if (player.getInventory().freeSlots() == 0) {
					npc("Talk to me againw hen you have free inventory space.");
					stage = 617;
					return;
				}
				if (player.getInventory().add(DemonSlayer.SECOND_KEY)) {
					interpreter.sendItemMessage(DemonSlayer.SECOND_KEY.getId(), "Captain Rovin hands you a key.");
					stage = 618;
				}
				break;
			case 617:
				end();
				break;
			case 618:
				end();
				break;
			case 620:
				player("Well I'm going to use the powerful sword Silverlight", "which I believe you have one of the keys for?");
				stage = 621;
				break;
			case 621:
				npc("I already gave you my key. Check your pockets.");
				stage = 622;
				break;
			case 622:
				end();
				break;
			}
		}

		@Override
		public int[] getIds() {
			return new int[] { 884 };
		}

	}
}
