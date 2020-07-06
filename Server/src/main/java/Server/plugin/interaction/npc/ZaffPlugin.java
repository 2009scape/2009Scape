package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for buying a battle staff from zeke.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ZaffPlugin extends OptionHandler {
	
	/**
	 * The bacon ring.
	 */
	public static final Item BEACON_RING = new Item(11014);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("buy-battlestaves", this);
		new ZaffDialogue().init();
		new ZaffStaveDialogue().init();
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(9679);
		return true;
	}

	/**
	 * Represents the dialogue plugin used for the zaff npc.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ZaffDialogue extends DialoguePlugin {

		/**
		 * Represents the staff item.
		 */
		@SuppressWarnings("unused")
		private static final Item STAFF = new Item(11014, 1);

		/**
		 * The quest.
		 */
		private Quest quest;

		/**
		 * Constructs a new {@code ZaffDialogue} {@code Object}.
		 */
		public ZaffDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ZaffDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ZaffDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ZaffDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			quest = player.getQuestRepository().getQuest("What Lies Below");
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Would you like to buy or sell some staves or is there", "something else you need?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				if (quest.getStage(player) == 60) {
					interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.", "Rat Burgiss sent me.");
					stage = 1;
					break;
				} else if (quest.getStage(player) == 80) {
					interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.", "We did it! We beat Surok!");
					stage = 1;
					break;
				} else if (quest.getStage(player) >= 70) {
					interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.", "Can I have another ring?");
					stage = 1;
					break;
				}
				interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you.");
					stage = 20;
					break;
				case 3:
					if (quest.getStage(player) == 60) {
						player("Rat Burgiss sent me!");
						stage = 70;
						break;
					} else if (quest.getStage(player) == 80) {
						player("We did it! We beat Surok!");
						stage = 200;
						break;
					}
					interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Can I have another ring?");
					stage = 50;
					break;
				}

				break;
			case 10:
				end();
				npc.openShop(player);
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, 'stick' your head in again if you change your mind.");
				stage = 21;
				break;
			case 21:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Huh, terrible pun. You just can't get the 'staff' these", "days!");
				stage = 22;
				break;
			case 22:
				end();
				break;
			case 50:
				if (player.getInventory().contains(11014, 1))
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Go and get the one that's in your inventory " + player.getUsername() + "!");
				else if (player.getBank().contains(11014, 1))
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Go and get the one that's in your bank" + player.getUsername() + "!");
				else if (player.getEquipment().contains(11014, 1))
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Go and get the one that's on your finger " + player.getUsername() + "!");
				else {
					interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Of course you can! Here you go " + player.getUsername() + "!");
					player.getInventory().add(BEACON_RING);
				}
				stage = 51;
				break;
			case 51:
				end();
				break;
			case 70:
				npc("Ah, yes; You must be " + player.getUsername() + "! Rat sent word that you", "would be coming. Everything is prepared. I have created", "a spell that will remove the mind control spell.");
				stage++;
				break;
			case 71:
				player("Okay, what's the plan?");
				stage++;
				break;
			case 72:
				npc("Listen carefully. For the spell to succeed, the king must", "be made very weak, if his mind is controlled, you will", "need to fight him until he is all but dead.");
				stage++;
				break;
			case 73:
				npc("Then and ONLY then, use your ring to summon me.", "I will teleport to you and cast the spell that will", "cure the king.");
				stage++;
				break;
			case 74:
				player("Why must I summon you? Can't you come with me?");
				stage++;
				break;
			case 75:
				npc("I cannot. I must look after my shop here and", "I have lots to do. Rest assured, I will come when you", "summon me.");
				stage++;
				break;
			case 76:
				player("Okay, so what do I do now?");
				stage++;
				break;
			case 77:
				npc("Take this beacon ring and some instructions.");
				stage++;
				break;
			case 78:
				npc("Once you have read the instructions. It will be time for", "you to arrest Surok.");
				stage++;
				break;
			case 79:
				player("Won't he be disinclined to acquiesce to that request?");
				stage++;
				break;
			case 80:
				npc("Won't he what?");
				stage++;
				break;
			case 81:
				player("Won't he refuse?");
				stage++;
				break;
			case 82:
				npc("I very much expect so. It may turn nasty, so be on your", "guard. I hope we can stop him before he can cast his", "spell!", "Make sure you have that ring I gave you.");
				stage++;
				break;
			case 83:
				player("Okay, thanks, Zaff!");
				stage++;
				break;
			case 84:
				player.getInventory().add(BEACON_RING);
				quest.setStage(player, 70);
				end();
				break;
			case 200:
				npc("Yes. You have done well, " + player.getUsername() + ". You are to be", "commended for you actions!");
				stage++;
				break;
			case 201:
				player("It was all in the call of duty!");
				stage++;
				break;
			case 202:
				player("What will happen with Surok now?");
				stage++;
				break;
			case 203:
				npc("Well, when I disrupted Surok's spell, he will have been", "sealed in the library, but we still need to keep an", "eye on him, just in case.");
				stage++;
				break;
			case 204:
				npc("When you are ready, report back to Rat and he will", "reward you.");
				stage++;
				break;
			case 205:
				player("Okay, I will.");
				stage++;
				break;
			case 206:
				quest.setStage(player, 90);
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 546 };
		}
	}

	/**
	 * Represents the dialogue used to buy staves from zaff.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final class ZaffStaveDialogue extends DialoguePlugin {

		/**
		 * The ammount of battlestaves.
		 */
		private int ammount;

		/**
		 * Constructs a new {@code ZaffBuyStavesDialogue} {@code Object}.
		 */
		public ZaffStaveDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ZaffBuyStavesDialogue} {@code Object}.
		 * @param player the player.
		 */
		public ZaffStaveDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ZaffStaveDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have any battlestaves?");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				ammount = player.getSavedData().getGlobalData().getZaffAmount();
				if (player.getSavedData().getGlobalData().getZafTime() < System.currentTimeMillis()) {
					ammount = getMaxStaffs();
				}
				if (player.getSavedData().getGlobalData().getZafTime() > System.currentTimeMillis() && ammount <= 0) {
					interpreter.sendDialogues(546, FacialExpression.HALF_GUILTY, "I'm very sorry! I seem to be out of battlestaves at the", "moment! I expect I'll get some more in by tomorrow,", "though.");
					stage = 2;
					break;
				}
				interpreter.sendDialogues(546, FacialExpression.HALF_GUILTY, "Battlestaves cost 8,000 gold pieces each. I have " + ammount + " left.", "How many would you like to buy?");
				stage = 1;
				break;
			case 1:
				end();
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						int amt = (int) value;
						if (amt > ammount) {
							amt = ammount;
						}
						if (amt > player.getInventory().freeSlots()) {
							amt = player.getInventory().freeSlots();
						}
						if (amt == 0) {
							return true;
						}
						if (7000 * amt > player.getInventory().getAmount(new Item(995))) {
							player.getPacketDispatch().sendMessage("You don't have enough money to buy that many.");
							return true;
						} else {
							Item remove = new Item(995, 7000 * amt);
							if (!player.getInventory().containsItem(remove)) {
								end();
								return true;
							}
							player.getInventory().remove(remove);
							if (player.getInventory().add(new Item(1391, amt))) {
								player.getSavedData().getGlobalData().setZafTime(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
								player.getSavedData().getGlobalData().setZaffAmount(ammount - amt);
							}
						}
						return true;
					}
				});
				interpreter.sendInput(false, "Zaff has " + ammount + " battlestaves...");
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, okay then. I'll try again another time.");
				stage = 3;
				break;
			case 3:
				end();
				break;
			}
			return true;
		}

		/**
		 * Gets the max staffs to buy.
		 * @return the max staffs to buy.
		 */
		public int getMaxStaffs() {
			int level = player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).getLevel();
			return level == -1 ? 8 : 15 * (level + 2);
		}

		@Override
		public int[] getIds() {
			return new int[] { 9679 };
		}
	}
}
