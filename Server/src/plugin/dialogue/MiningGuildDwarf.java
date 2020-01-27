package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;

/**
 * Represents the mining guild dwarf.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class MiningGuildDwarf extends DialoguePlugin {

	/**
	 * Constructs a new {@code MiningGuildDwarf} {@code Object}.
	 */
	public MiningGuildDwarf() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MiningGuildDwarf} {@code Object}.
	 * @param player the player.
	 */
	public MiningGuildDwarf(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MiningGuildDwarf(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length == 2) {
			if (player.getSkills().getStaticLevel(Skills.MINING) < 60) {
				interpreter.sendDialogues(npc, null, "Sorry, but you need level 60 Mining to go in there.");
				stage = 69;
				return true;
			}
		}
		interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Welcome to the Mining Guild.", "Can I help you with anything?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 69:
			end();
			break;
		case 0:
			interpreter.sendOptions("What would you like to say?", "What have you got in the Guild?", "What do you dwarves do wiyth the ore you mine?", "No thanks, I'm fine.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What have you got in the Guild?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What do you dwarves do with the ore you mine?");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No thanks, I'm fine.");
				stage = 30;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ooh, it's WONDEFRFUL! There are lots of coal rocks,", "and even a few mithril rocks in the guild,", "all exclusively for people with at least level 60 mining", "There's no better mining site anywhere near here.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So you won't let me go in there?");
			stage = 12;
			break;
		case 12:
			if (player.getSkills().getStaticLevel(Skills.MINING) < 60) {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Sorry, but the rules are rules. Do some more training", "first.");
				stage = 13;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, you can enter if you wish.");
				stage = 14;
			}
			break;
		case 13:
			end();
			break;
		case 14:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "What do you think? We smelt it into bars, smith the", "metal to make armour and weapons, then we exchange", "them for goods and services.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I don't see many dwarves here.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No, this is only a mining outpost. We dwarves don't", "much like to settle in human cities. Most of the ore is", "carted off to Keldagrim, the great dwarven city-", "They've got a special blast furnace up there - it makes");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "smelting the ore so much easier. There are plenty of", "dwarven traders working in Keldagrim.");
			stage = 24;
			break;
		case 24:
			end();
			break;
		case 30:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 382 };
	}
}
