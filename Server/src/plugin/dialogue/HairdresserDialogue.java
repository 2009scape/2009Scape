package plugin.dialogue;

import org.crandor.game.component.Component;
import org.crandor.game.container.impl.EquipmentContainer;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;

/**
 * Represents the dialogue plugin used for the hairdresser.
 */
@InitializablePlugin
public final class HairdresserDialogue extends DialoguePlugin {


	/**
	 * Constructs a new {@code HairdresserDialogue} {@code Object}.
	 */
	public HairdresserDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HairdresserDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HairdresserDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HairdresserDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		final String gender = player.isMale() ? "sir" : "madam";

		//If the player right clicks the hairdresser and presses Hair-cut
		if (args.length == 2) {
			if (player.getInventory().contains(995, 2000) && player.getEquipment().get(EquipmentContainer.SLOT_HAT) == null &&
					player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) == null && player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) == null) {
				if (player.isMale()) {
					player.getInterfaceManager().open(new Component(596));
				} else {
					player.getInterfaceManager().open(new Component(592));
				}
			}
			else if(player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null || player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) != null || player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) != null ) {
				/** NOT ACCURATE DIALOGUE **/
				interpreter.sendDialogues(npc, FacialExpression.SCARED, player.isMale() ? "Sir, " : "Madam, " + "I can't cut your hair with those things pointing","at me. Please take them off and speak to me again.");
				stage = 13;
			}else{ //Not enough money
				interpreter.sendDialogues(player, FacialExpression.SAD, "I don't have 2000 gold coins on me...");
				stage = 12;
			}
			return true;
		}

		//If the player talks with the hairdresser
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Good afternoon " + gender + ". In need of a haircut are we?", player.isMale() ? "Perhaps a shave too?" : "");
		stage = 1;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {

			case 1:
				if (player.isMale()) {
					interpreter.sendOptions("Select an Option", "I'd like a haircut please.", "I'd like a shave please.", "No thank you.");
					stage = 2;
				} else {
					interpreter.sendOptions("Select an Option", "I'd like a haircut please.", "No thank you.");
					stage = 5;
				}
				break;

			//Male Option responses
			case 2:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'd like a haircut please.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'd like a shave please.");
						stage = 10;
						break;
					case 3:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No thank you.");
						stage = 20;
						break;
				}
				break;

			//Female Option responses
			case 5:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(player, FacialExpression.HAPPY, "I'd like a haircut please.");
						stage = 10;
						break;
					case 2:
						interpreter.sendDialogues(player, FacialExpression.NEUTRAL, "No thank you.");
						stage = 20;
						break;
				}
				break;

			//HairDresser Dialogues
			case 10:
				String gender = player.isMale() ? "sir" : "madam";
				String gender2 = player.isMale() ? "men's" : "women's";
				interpreter.sendDialogues(npc, FacialExpression.HAPPY, "Certainly " + gender + ". I cut " + gender2 + " hair at the bargain rate of", "only 2000 gold coins. I'll even throw in a free recolour!");
				stage++;
				break;
			case 11:
				if (player.getInventory().contains(995, 2000) && player.getEquipment().get(EquipmentContainer.SLOT_HAT) == null &&
						player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) == null && player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) == null) {
					interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Please select the hairstyle and colour you would like", "from this brochure.");
					stage = 14;
				}
				//Has helmet or sword/shield
				else if(player.getEquipment().get(EquipmentContainer.SLOT_HAT) != null || player.getEquipment().get(EquipmentContainer.SLOT_WEAPON) != null || player.getEquipment().get(EquipmentContainer.SLOT_SHIELD) != null ) {
					/** NOT ACCURATE DIALOGUE **/
					interpreter.sendDialogues(npc, FacialExpression.SCARED, player.isMale() ? "Sir, " : "Madam, " + "I can't cut your hair with those things pointing","at me. Please take them off and speak to me again.");
					stage = 13;
					break;
				} else {
					interpreter.sendDialogues(player, FacialExpression.SAD, "I don't have 2000 gold coins on me...");
					stage = 12;
				}
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.ANNOYED, "Well, come back when you do. I'm not running a", "charity here!");
				stage++;
				break;
			case 13:
				end();
				break;

			//Opens the Hairstyle menu and ends the conversation
			case 14:
				end();
				if (player.isMale()) {
					end();
					player.getInterfaceManager().open(new Component(596));
				} else {
					end();
					player.getInterfaceManager().open(new Component(592));
				}
				break;

			case 20:
				interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Very well. Come back if you change your mind.");
				stage = 13;
				break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 598 };
	}
}
