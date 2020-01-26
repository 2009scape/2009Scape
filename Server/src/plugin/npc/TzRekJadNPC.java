package plugin.npc;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialogueInterpreter;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.repository.Repository;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles everything in regards to jad pet.
 * @author Empathy
 *
 */
@InitializablePlugin
public class TzRekJadNPC extends OptionHandler {

	/**
	 * The tzhaar mej id.
	 */
	private static final int TZHAAR_MEJ_ID = 2617;
	
	/**
	 * The firecape item.
	 */
	private static final Item FIRECAPE = new Item(6570);
	
	/**
	 * The tokkul item.
	 */
	private static final Item TOKKUL = new Item(6529);
	
	/**
	 * The jad item.
	 */
	private static final Item JAD_PET = new Item(14828);
	
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(TZHAAR_MEJ_ID).getConfigurations().put("option:exchange fire cape", this);
		PluginManager.definePlugin(new TzhaarMejJalDialogue(), new TzRekJadDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "exchange fire cape":
			player.getDialogueInterpreter().open(DialogueInterpreter.getDialogueKey(player.getInventory().containsItem(FIRECAPE) ? "firecape-exchange" : "tzhaar-mej"), node.asNpc());
			break;
		}
		return true;
	}
	
	/**
	 * Handles the TzhaarMejJal Dialogue to gamble for jad pet.
	 * @author Empathy
	 *
	 */
	public final class TzhaarMejJalDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code TzhaarMejJalDialogue} {@code Object}.
		 */
		public TzhaarMejJalDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code TzhaarMejJalDialogue} {@code Object}.
		 * 
		 * @param player the player.
		 */
		public TzhaarMejJalDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new TzhaarMejJalDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I have a fire cape here.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendOptions("Sell your fire cape?", "Yes, sell it for 8,000 TokKul.", "No, keep it.", "Bargain for TzRek-Jad.");
				stage = 1;
				break;
			case 1:
				switch(buttonId) {
				case 1:
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, player.getInventory().containsItems(FIRECAPE) ? "Hand your cape here, young JalYte." : "You not have firecape, JalYt.");
					stage = 10;
					break;
				case 2:
					end();
					break;
				case 3:
					interpreter.sendOptions("Sacrifice your firecape?", "Yes, I know I won't get my cape back.", "No, I like my cape!");
					stage = 20;
					break;
				}
				break;
			case 10:
				if (player.getInventory().containsItem(FIRECAPE)) {
					if (player.getInventory().remove(FIRECAPE)) {
						TOKKUL.setAmount(8000);
						player.getInventory().add(TOKKUL);
						TOKKUL.setAmount(1);
					}
				}
				end();
				break;
			case 20:
				switch (buttonId) {
				case 1:
					if (player.hasItem(JAD_PET)) {
						interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Best you train one TzRek-Jad only.");
						stage = 21;
						break;
					}
					if (player.getFamiliarManager().hasFamiliar()) {
						if (player.getFamiliarManager().getFamiliar().getId() == 8650) {
							interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Best you train one TzRek-Jad only.");
							stage = 21;
							break;
						}
					}
					if (player.getInventory().remove(FIRECAPE)) {
						int r = RandomFunction.getRandom(200);
						if (r == 1) {
							interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You lucky. Better train him good else TzTok-Jad find", "you, JalYt.");
							if (!player.getFamiliarManager().hasFamiliar()) {
								player.getFamiliarManager().summon(JAD_PET, true);
								player.sendNotificationMessage("You have a funny feeling like you're being followed.");
							} else if (player.getInventory().freeSlots() > 0) {
								player.getInventory().add(JAD_PET);
								player.sendNotificationMessage("You feel something weird sneaking into your backpack.");
							}
							Repository.sendNews(player.getUsername() + " now commands a miniature TzTok-Jad!");
						} else {
							interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "You not lucky. Maybe next time, JalYt.");
						}
					}
					stage = 21;
					break;
				case 2:
					end();
					break;
				}
				break;
			case 21:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("firecape-exchange") };
		}
	}
	
	/**
	 * Handles the TzRekJad dialogue.
	 * @author Empathy
	 *
	 */
	public final class TzRekJadDialogue extends DialoguePlugin {

			/**
			 * Constructs a new {@code TzRekJadDialogue} {@code Object}.
			 */
			public TzRekJadDialogue() {
				/**
				 * empty.
				 */
			}

			/**
			 * Constructs a new {@code TzRekJadDialogue} {@code Object}.
			 * 
			 * @param player the player.
			 */
			public TzRekJadDialogue(Player player) {
				super(player);
			}

			@Override
			public DialoguePlugin newInstance(Player player) {
				return new TzRekJadDialogue(player);
			}

			@Override
			public boolean open(Object... args) {
				npc = (NPC) args[0];
				int i = RandomFunction.getRandom(1);
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, i == 1 ? "Do you miss your people?" : "Are you hungry?");
				stage = (i == 1 ? 0 : 5);
				return true;
			}

			@Override
			public boolean handle(int interfaceId, int buttonId) {
				switch (stage) {
				case 0:
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Mej-TzTok-Jad Kot-Kl!");
					stage = 1;
					break;
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No.. I don't think so.");
					stage = 2;
					break;
				case 2:
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Jal-Zek Kl?");
					stage = 3;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No, no, I wouldn't hurt you.");
					stage = 4;
					break;
				case 4:
					end();
					break;
				case 5:
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Kl-Kra!");
					stage = 6;
					break;
				case 6:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Ooookay...");
					stage = 4;
					break;
				}
				return true;
			}

			@Override
			public int[] getIds() {
				return new int[] { 8650 };
			}
		}
	
}
