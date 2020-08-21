package plugin.quest.members.merlinscrystal;

import core.cache.def.impl.NPCDefinition;
import core.game.system.config.ShopParser;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * Represents the dialogue plugin used to handle the candle maker npc.
 * @author 'Vexia
 * @author Splinter
 * @version 1.0
 */
public final class CandleMakerDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CandleMakerDialogue} {@code Object}.
	 */
	public CandleMakerDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code CandleMakerDialogue} {@code Object}.
	 * @param player the player.
	 */
	public CandleMakerDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CandleMakerDialogue(player);
	}

	@Override
	public void init() {
		super.init();
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				NPCDefinition.forId(getIds()[0]).getConfigurations().put("option:trade", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				NPC npc = node.asNpc();
				Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
				if (quest.getStage(player) > 60) {
					ShopParser.Companion.openUid(player, 198);
				} else {
					npc.openShop(player);
				}
				return true;
			}

		});
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hi! Would you be interested in some of my fine", "candles?");
		stage = 2;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Merlin's Crystal");
		switch (stage) {
		case 2:
			if (quest.getStage(player) == 50 || quest.getStage(player) == 60) {// the
				// player
				// has
				// defeated
				// mordred
				// and
				// learned
				// about
				// the
				// black
				// candles
				interpreter.sendOptions("Select an Option", "Have any black candles?", "Yes, let me see your stock.", "No thanks.");
				stage = 3;
			} else if (quest.getStage(player) > 60) {// they're farther along in
				// the quest
				interpreter.sendOptions("Select an Option", "Have any black candles?", "Yes, let me see your stock.", "No thanks.");
				stage = 25;
			} else {// they haven't started the quest or are too low
				interpreter.sendOptions("Select an Option", "Yes, let me see your stock.", "No thanks.");
				stage = 10;
			}
			break;
		case 3:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Have you got any black candles?");
				stage = 4;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, let me see your stock.");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thank you.");
				stage = 40;
				break;
			}
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "BLACK candles???");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hmmm. In the candle making trade, we have a tradition", "that it's very bad luck to make black candles.");
			stage = 6;
			break;
		case 6:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "VERY bad luck.");
			stage = 7;
			break;
		case 7:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I will pay good money for one.");
			stage = 8;
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I still dunno...");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Tell you what. I'll supply you with a black candle...");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "IF you can bring me a bucket FULL of wax.");
			stage = 40;
			break;
		case 10:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, let me see your stock.");
				stage = 30;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thank you.");
				stage = 40;
				break;
			}
			break;
		case 25:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ah, you again. You're quite a trend setter. Can't believe", "the number of black candle requests I've had since you", "came. I couldn't pass up a business opportunity like that,", "bad luck or no. So I'm selling them now. Would you be");
				stage = 26;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, let me see your stock.");
				stage = 30;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thank you.");
				stage = 40;
				break;
			}
			break;
		case 26:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "interested in purchasing another?");
			stage = 27;
			break;
		case 27:
			interpreter.sendOptions("Select an Option", "Yes, let me see your stock.", "No thanks.");
			stage = 10;
			break;
		case 30:
			end();
			if (quest.getStage(player) > 60) {
				ShopParser.Companion.openUid(player, 198);
			} else {
				npc.openShop(player);
			}
			break;
		case 40:
			if (quest.getStage(player) == 50 && player.getInventory().contains(MerlinCrystalPlugin.BUCKET_OF_WAX.getId(), 1)) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wha- what's that? You've already got a bucket of wax!");
				stage = 41;
			} else {
				end();
			}
			break;
		case 41:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Give it 'ere and I'll trade you for a black candle.");
			stage = 42;
			break;
		case 42:
			interpreter.sendDialogue("You hand over a bucket of wax in exchange for a black candle.");
			player.getInventory().remove(new Item(MerlinCrystalPlugin.BUCKET_OF_WAX.getId(), 1));
			player.getInventory().add(new Item(MerlinCrystalPlugin.BLACK_CANDLE.getId(), 1));
			stage = 43;
			break;
		case 43:
			end();
			break;

		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 562 };
	}
}