package plugin.tutorial;

import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.HintIconManager;
import org.crandor.game.node.entity.player.link.IronmanMode;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.repository.Repository;
import org.crandor.net.amsc.MSPacketRepository;
import org.crandor.net.amsc.WorldCommunicator;

/**
 * Handles the tutorial completition dialogue (skippy, magic instructor)
 * @author Vexia
 * @author Splinter
 * 
 */
@InitializablePlugin
public class TutorialCompletionDialogue extends DialoguePlugin {

	/**
	 * The starter pack of items.
	 */
	private static final Item[] STARTER_PACK = new Item[] { new Item(1351, 1), new Item(590, 1), new Item(303, 1), new Item(315, 1), new Item(1925, 1), new Item(1931, 1), new Item(2309, 1), new Item(1265, 1), new Item(1205, 1), new Item(1277, 1), new Item(1171, 1), new Item(841, 1), new Item(882, 25), new Item(556, 25), new Item(558, 15), new Item(555, 6), new Item(557, 4), new Item(559, 2) };


	/**
	 * Represents the rune items.
	 */
	private static final Item[] RUNES = new Item[] { new Item(556, 5), new Item(558, 5) };

	/**
	 * If ironman is enabled.
	 */
	private static final boolean IRONMAN = true;

	/**
	 * Constructs a new {@code TutorialCompletionDialogue} {@code Object}
	 */
	public TutorialCompletionDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code TutorialCompletionDialogue} {@code Object}
	 * @param player the player.
	 */
	public TutorialCompletionDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new TutorialCompletionDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (npc == null) {
			return true;
		}
		if (npc.getId() == 946) {
			switch (TutorialSession.getExtension(player).getStage()) {
			case 67:
				interpreter.sendDialogues(player, null, "Hello.");
				stage = 0;
				return true;
			case 69:
				interpreter.sendDialogues(946, null, "Good. This is a list of your spells. Currently you can", "only cast one offensive spell called Wind Strike. Let's", "try it out on one of those chickens.");
				stage = 0;
				return true;
			case 70:
				if (!player.getInventory().contains(556, 1) && !player.getInventory().contains(558, 1)) {
					if (player.getInventory().hasSpaceFor(RUNES[0]) && player.getInventory().hasSpaceFor(RUNES[1])) {
						interpreter.sendDoubleItemMessage(RUNES[0], RUNES[1], "Terrova gives you five <col=08088A>air runes</col> and five <col=08088A>mind runes</col>!");
						player.getInventory().add(RUNES);
						stage = 3;
					} else {
						GroundItemManager.create(RUNES, player.getLocation(), player);
						stage = 3;
					}
				} else {
					end();
					TutorialStage.load(player, 70, false);
				}
				return true;
			case 71:
				interpreter.sendDialogues(946, null, "Well you're all finished here now. I'll give you a", "reasonable number of runes when you leave.");
				stage = -2;
				return true;
			}
		} else {
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "*psst.* Hey, do you want to skip the tutorial?", "I can send you straight to the mainland, easy.");
			stage = 1;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (npc.getId() == 2796 || TutorialSession.getExtension(player).getStage() >= 71) {
			switch (stage) {
			case -2:
				interpreter.sendOptions("Leave Tutorial Island?", "Yes.", "No.");
				stage = -1;
				break;
			case -1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(946, null, "When you get to the mainland you will find yourself in", "the town of Lumbridge. If you want some ideas on", "where to go next talk to my friend the Lumbridge", "Guide. You can't miss him; he's holding a big staff.");
					stage = 1;
					break;
				case 2:
					end();
					TutorialStage.load(player, 71, false);
					break;
				}
				break;
			case 1:
				interpreter.sendOptions("What would you like to say?", "<col=CC0000>Leave Tutorial Island.", "Can I decide later?", "I'll stay here for the Tutorial.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					npc("One more thing: Would you like to", "be an Ironman account?");
					stage = 501;
					if (!IRONMAN) {
						stage = 1200;
					}
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NORMAL, "I'll stay here for the Tutorial.");
					stage = 40;
					break;
				}
				break;
			case 40:
				interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Very well. Have fun, adventurer.");
				stage = 99;
				break;
			case 22:
				interpreter.sendOptions("What would you like to say?", "Send me to the mainland now.", "Who are you?", "Can I decide later?", "I'll stay here for the Tutorial.");
				stage = 2;
				break;
			case 501:
				player.removeAttribute("ironMode");
				player.removeAttribute("ironPermanent");
				options("Yes, please.", "What is an Ironman account?", "No, thanks.");
				stage++;
				break;
			case 502:
				switch (buttonId) {
				case 1:
					player("Yes, please.");
					stage = 506;
					break;
				case 2:
					player("What is an Ironman account?");
					stage = 530;
					break;
				case 3:
					player("No, thanks.");
					stage = 1200;
					break;
				}
				break;
			case 506:
				interpreter.sendOptions("Select a Mode", "Standard", "<col=CC0000>Ultimate</col>", "Go back.");
				stage++;
				break;
			case 507:
				switch (buttonId) {
				case 1:
				case 2:
					npc("You have chosen the " + (buttonId == 1 ? "Standard" : "<col=CC0000>Ultimate</col>") + " mode.");
					player.setAttribute("ironMode", IronmanMode.values()[buttonId]);
					stage = 516;
					break;
				case 3:
					player.removeAttribute("ironMode");
					player.removeAttribute("ironPermanent");
					options("Yes, please.", "What is an Ironman account?", "No, thanks.");
					stage = 502;
					break;
				}
				break;
			case 516:
				player.getIronmanManager().setMode(player.getAttribute("ironMode", IronmanMode.NONE));
				MSPacketRepository.sendInfoUpdate(player);
				npc("Congratulations, you have setup your Ironman account.", "You will travel into the mainland now.");
				stage = 1200;
				break;
			case 530:
				npc("An Ironman account is a style of playing where players", "are completely self-sufficient.");
				stage++;
				break;
			case 531:
				npc("A standard Ironman does not receieve items or", "assistance from other players. They cannot trade, stake,", "receieve PK loot, scavenge dropped items, nor player", "certain minigames.");
				stage++;
				break;
			case 532:
				npc("In addition to the standard Ironman rules. An", "Ultimate Ironman cannot use banks, nor retain any", "items on death in dangerous areas.");
				stage = 501;
				break;
			case 1200:
				npc("Keep in mind: our server has more content than any other", "server ever released. There's hundreds of hours of", "exciting and flawless gameplay awaiting you, "+player.getUsername()+".", "Enjoy your time playing "+GameWorld.getName()+"!");
				stage = 520;
				break;
			case 520:
				player.removeAttribute("tut-island");
				player.getConfigManager().set(1021, 0);
				player.getProperties().setTeleportLocation(new Location(3164, 3471));
				TutorialSession.getExtension(player).setStage(72);
				player.getInterfaceManager().closeOverlay();
				player.getInventory().clear();
				player.getEquipment().clear();
				player.getBank().clear();
				player.getInterfaceManager().restoreTabs();
				player.getInventory().add(STARTER_PACK);
				interpreter.sendDialogue("Welcome to " + GameWorld.getName() + "!", "If you require any assistance, please don't hesitate to contact our", "friendly staff members and players for advice.");
				player.getPacketDispatch().sendMessage("Welcome to " + GameWorld.getName() + ".");
				player.getPacketDispatch().sendMessages("<img=16><col=6600CC>As a new player, you are receiving boosted combat skill experience.</col>", "<col=6600CC>We highly recommend playing in high-detail mode if your machine can support it.</col>", "<col=6600CC>An arrow icon has been placed over the WildScape Pk Guide. You may speak to him for", "more in-game information.");
				player.sendMessage("<col=6600CC>Use the teleporter for easy access to training,cities and minigames.");
				player.getBank().add(new Item(121, 15), new Item(115, 15), new Item(133, 15), new Item(373, 50), new Item(2289, 25), new Item(7056, 25));
				player.unlock();
				TutorialSession.getExtension(player).setStage(TutorialSession.MAX_STAGE + 1);
				stage = 7;
				if (player.getIronmanManager().isIronman() && player.getSettings().isAcceptAid()) {
					player.getSettings().toggleAcceptAid();
				}
				if (WorldCommunicator.isEnabled()) {
					MSPacketRepository.sendInfoUpdate(player);
				}
				int slot = player.getAttribute("tut-island:hi_slot", -1);
				if (slot < 0 || slot >= HintIconManager.MAXIMUM_SIZE) {
					break;
				}
				player.getInterfaceManager().setViewedTab(3);
				player.removeAttribute("tut-island:hi_slot");
				HintIconManager.removeHintIcon(player, slot);
				HintIconManager.registerHintIcon(player, Repository.findNPC(Location.create(3168, 3481, 0)));
				break;
			case 7:
				end();
				break;
			case 99:
				end();
				TutorialStage.load(player, TutorialSession.getExtension(player).getStage(), false);
				break;
			}
			return true;
		}
		switch (TutorialSession.getExtension(player).getStage()) {
		case 67:
			switch (stage) {
			case 0:
				interpreter.sendDialogues(946, null, "Good day, newcomer. My name is Terrova. I'm here", "to tell you about Magic. Let's start by opening your", "spell list.");
				stage = 1;
				break;
			case 1:
				end();
				TutorialStage.load(player, 68, false);
				break;
			}
			break;
		case 69:
			switch (stage) {
			case 0:
				if (!player.getInventory().contains(556, 1) && !player.getInventory().contains(558, 1)) {
					if (player.getInventory().freeSlots() > 2) {
						interpreter.sendDoubleItemMessage(RUNES[0], RUNES[1], "Terrova gives you five <col=08088A>air runes</col> and five <col=08088A>mind runes</col>!");
						player.getInventory().add(RUNES[0], RUNES[1]);
						stage = 3;
					}
				} else {
					end();
					TutorialStage.load(player, 70, false);
				}
				break;
			case 3:
				end();
				TutorialStage.load(player, 70, false);
				break;
			}
			break;
		case 70:
			switch (stage) {
			case 0:
				break;
			case 3:
				end();
				TutorialStage.load(player, 70, false);
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] {/* skippy */2796, /* magic instructor */946 };
	}

}
