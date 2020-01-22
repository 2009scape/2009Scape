package plugin.dialogue;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the dialogue used for martin thwait.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MartinThwaitDialogue extends DialoguePlugin {

	/**
	 * Represents the skillcape items.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(9777), new Item(9778), new Item(9779) };

	/**
	 * Represents the coins to use.
	 */
	private static final Item COINS = new Item(995, 99000);

	/**
	 * Constructs a new {@code MartinThwait} {@code Object}.
	 */
	public MartinThwaitDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MartinThwait} {@code Object}.
	 * @param player the player.
	 */
	public MartinThwaitDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MartinThwaitDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "You know it's sometimes funny how things work out, I", "lose some gold but find an item, or I lose an item and", "find some gold... no-one ever knows what's gone where", "ya know.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			if (player.getSkills().getLevel(Skills.THIEVING) == 99) {
				interpreter.sendOptions("Select an Option", "Yeah I know what you mean, found anything recently?", "Okay... I'll be going now.", "Can you tell me about your skillcape?");
				stage = 1;
			} else {
				interpreter.sendOptions("Select an Option", "Yeah I know what you mean, found anything recently?", "Okay... I'll be going now.");
				stage = 1;
			}
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yeah I know what you mean, found anything recently?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Okay... I'll be going now.");
				stage = 20;
				break;
			case 3:
				if (player.getSkills().getLevel(Skills.THIEVING) == 99) {
					interpreter.sendDialogues(player, null, "Can you tell me about your skillcape?");
					stage = 30;
					break;
				}
				break;
			}
			break;
		case 10:
			if (player.getSkills().getLevel(Skills.AGILITY) >= 50 && player.getSkills().getLevel(Skills.THIEVING) >= 50 || player.getSkills().getLevel(Skills.THIEVING) == 99) {
				end();
				npc.openShop(player);
			} else {
				if (player.getSkills().getLevel(Skills.THIEVING) < 50) {
					interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Sorry, mate. Train up your Thieving skill to at least", "50 and I might be able to help you out.");
				}
				if (player.getSkills().getLevel(Skills.AGILITY) < 50) {
					interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Sorry, mate. Train up your Agility skill to at least", "50 and I might be able to help you out.");
				}
			}
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Sure, this is a Skillcape of Thieving. It shows my stature as", "a master thief! It has all sorts of uses , if you", "have a level of 99 Thieving I'll sell you one.");
			stage = 31;
			break;
		case 31:
			if (player.getSkills().getStaticLevel(Skills.THIEVING) == 99) {
				options("Yes, please.", "No, thanks.");
				stage = 32;
			} else {
				end();
			}
			break;
		case 32:
			switch (buttonId) {
			case 1:
				player("Yes, please.");
				stage = 33;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 33:
			if (player.getInventory().freeSlots() < 2) {
				player("Sorry, I don't seem to have enough inventory space.");
				stage = 34;
				return true;
			}
			if (!player.getInventory().containsItem(COINS)) {
				end();
				return true;
			}
			if (!player.getInventory().remove(COINS)) {
				player("Sorry, I don't seem to have enoug coins", "with me at this time.");
				stage = 34;
				return true;
			}
			player.getInventory().add(ITEMS[player.getSkills().getMasteredSkills() > 1 ? 1 : 0], ITEMS[2]);
			npc("There you go! Enjoy.");
			stage = 34;
			break;
		case 34:
			end();
			break;
		}
		return true;
	}

	@Override
	public void init() {
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				NPCDefinition.forId(2270).getConfigurations().put("option:trade", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				if (player.getSkills().getLevel(Skills.AGILITY) > 50 && player.getSkills().getLevel(Skills.THIEVING) > 50 || player.getSkills().getLevel(Skills.THIEVING) == 99) {
					if (npc == null) {
						return true;
					}
					npc.openShop(player);
				} else {
					if (player.getSkills().getLevel(Skills.THIEVING) < 50) {
						player.getDialogueInterpreter().sendDialogues(2270, FacialExpression.NO_EXPRESSION, "Sorry, mate. Train up your Thieving skill to at least", "50 and I might be able to help you out.");
					}
					if (player.getSkills().getLevel(Skills.AGILITY) < 50) {
						player.getDialogueInterpreter().sendDialogues(2270, FacialExpression.NO_EXPRESSION, "Sorry, mate. Train up your Agility skill to at least", "50 and I might be able to help you out.");
					}
				}
				return true;
			}

		});
		super.init();
	}

	@Override
	public int[] getIds() {
		return new int[] { 2270 };
	}
}
