package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the apothecary npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ApothecaryDialogue extends DialoguePlugin {

	/**
	 * Represents the potion requirements.
	 */
	private static final Item[] POTION_ITEMS = new Item[] { new Item(223), new Item(225), new Item(995, 5) };

	/**
	 * Represents the potion item.
	 */
	private static final Item POTION = new Item(115);

	/**
	 * Represents the unkown potion.
	 */
	private static final Item UNKNOWN_POTION = new Item(195, 1);

	/**
	 * Represents the cadava berries item.
	 */
	private static final Item CADAVA_BERRIES = new Item(753);

	/**
	 * Represents the cadava potion item.
	 */
	private static final Item CADAVA_POTION = new Item(756);

	/**
	 * Constructs a new {@code ApothecaryDialogue} {@code Object}.
	 */
	public ApothecaryDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ApothecaryDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ApothecaryDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ApothecaryDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getQuestRepository().getQuest("Romeo & Juliet").getStage(player) == 40) {
			interpreter.sendDialogues(player, null, "Apothecary. Father Lawrence sent me.");
			stage = 500;
			return true;
		}
		if (player.getQuestRepository().getQuest("Romeo & Juliet").getStage(player) == 50) {
			if (!player.getInventory().contains(753, 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Keep searching for those Cadavaberries. They're needed", "for the potion.");
				stage = 507;
				return true;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well done. You have the berries.");
				stage = 637;
				return true;
			}
		}
		if (player.getQuestRepository().getQuest("Romeo & Juliet").getStage(player) == 60) {
			if (!player.getInventory().contains(756, 1) && !player.getBank().contains(756, 1)) {
				if (player.getInventory().contains(753, 1)) {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well done. You have the berries.");
					stage = 637;
					return true;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Keep searching for those Cadavaberries. They're needed", "for the potion.");
					stage = 507;
					return true;
				}
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the Apothecary. I brew potions.", "Do you need anything specific?");
			}
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I am the Apothecary. I brew potions.", "Do you need anything specific?");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 1:
			interpreter.sendOptions("Select an Option", "Can you make a strength potion?", "Do you know a potion to mame hair fall out?", "Have you got any good potions to give away?", "Can you make a potion that makes it seem like I'm dead?");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you make a strength potion?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you know a potion to make hair fall out?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Have you got any good potions to give away?");
				stage = 140;
				break;
			case 4:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can you make a potion that makes it seems like I'm dead?");
				stage = 40;
				break;
			}

			break;
		case 10:
			if (player.getInventory().containItems(223, 225)) {
				npc("Certainly, just hand over the ingredients and 5 coins.");
				stage = 50;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, but the ingredients are a little hard to find. If you", "ever get them I will make it for you, for a fee.");
			stage = 11;
			break;
		case 50:
			if (!player.getInventory().contains(995, 5)) {
				end();
				player.getPacketDispatch().sendMessage("You need 5 gold coins to do that.");
				return true;
			}
			interpreter.sendDialogue("You hand over the ingredients and money.");
			stage = 51;
			break;
		case 51:
			if (player.getInventory().remove(POTION_ITEMS)) {
				player.getInventory().add(POTION);
				end();
				if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(1, 0)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 1, 0, true);
				}
			}
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So what are the ingredients?");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You'll need to find the eggs of the deadly red spider and a", "limpwurt root.");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh and you'll have to pay me 5 coins.");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I'll look out for them.");
			stage = 15;
			break;
		case 15:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I do indeed. I gave it to my mother. That's why I now live", "alone.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ok then. Try this potion.");
			player.getInventory().add(UNKNOWN_POTION);
			stage = 31;
			break;
		case 31:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What a strange and morbid request! I can as it happens.", "The berry of the cadava bush, prepared properly, will", "induce a coma so deep that you will seem to be dead. It's", "very dangerous.");
			stage = 41;
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I have the other ingredients, but I'll need you to bring me", "one bunch of cadava berries.");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I'll bear that in mind.");
			stage = 43;
			break;
		case 43:
			end();
			break;
		case 140:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry, charity is not my strong point.");
			stage = 141;
			break;
		case 141:
			end();
			break;
		case 500:
			interpreter.sendDialogues(player, null, "I need a Cadava potion to help Romeo and Juliet.");
			stage = 501;
			break;
		case 501:
			interpreter.sendDialogues(npc, null, "Cadava potion. It's pretty nasty. And hard to make.");
			stage = 502;
			break;
		case 502:
			interpreter.sendDialogues(npc, null, "Wing of rat, tail of frog.", "Ear of snake and horn of dog.");
			stage = 503;
			break;
		case 503:
			interpreter.sendDialogues(npc, null, "I have all that, but i need some Cadava berries.");
			stage = 504;
			break;
		case 504:
			interpreter.sendDialogues(npc, null, "You will have to find them while I get the rest ready.");
			stage = 505;
			break;
		case 505:
			interpreter.sendDialogues(npc, null, "Bring them here when you have them. But be careful.", "They are nasty.");
			stage = 506;
			break;
		case 506:
			player.getQuestRepository().getQuest("Romeo & Juliet").setStage(player, 50);
			interpreter.sendDialogues(player, null, "Ok, thanks.");
			stage = 507;
			break;
		case 507:
			end();
			break;
		case 637:
			interpreter.sendItemMessage(753, "You hand over the berries.");
			stage = 638;
			break;
		case 638:
			if (player.getInventory().remove(CADAVA_BERRIES)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Phew! Here is what you need.");
			}
			stage = 639;
			break;
		case 639:
			if (!player.getInventory().add(CADAVA_POTION)) {
				GroundItemManager.create(new GroundItem(CADAVA_POTION, player.getLocation(), player));
			}
			interpreter.sendItemMessage(756, "The Apothecary gives you a Cavada potion.");
			stage = 640;
			break;
		case 640:
			player.getQuestRepository().getQuest("Romeo & Juliet").setStage(player, 60);
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 638 };
	}
}
