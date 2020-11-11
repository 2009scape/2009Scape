package plugin.dialogue;

import core.game.container.impl.EquipmentContainer;
import core.game.content.ItemNames;
import core.game.content.global.travel.ship.Ships;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialogue used to handle the sailing from and to karamja.
 *
 * @author Vexia
 */
@InitializablePlugin
public class SeamanDialoguePlugin extends DialoguePlugin {

    /**
     * Constructs a new {@code SeamanDialoguePlugin} {@code Object}.
     */
    public SeamanDialoguePlugin() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code SeamanDialoguePlugin} {@code Object}.
     *
     * @param player the player.
     */
    public SeamanDialoguePlugin(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new SeamanDialoguePlugin(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        if (args.length > 1 && player.getQuestRepository().isComplete("Pirate's Treasure")) {
			if (player.getEquipment().get(EquipmentContainer.SLOT_RING) != null && player.getEquipment().get(EquipmentContainer.SLOT_RING).getId() == ItemNames.RING_OF_CHAROSA_6465) {
				travel();
			} else if (player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(0)) {
				pay(15);
			} else {
				pay(30);
			}
            return true;
        } else {
            player.getPacketDispatch().sendMessage("You may only use the Pay-fare option after completing Pirate's Treasure.");
        }
        interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Do you want to go on a trip to Karamja?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The trip will cost you 30 coins.");
                stage = 1;
                break;
            case 1:
                boolean charos = false;
                if (player.getEquipment().get(EquipmentContainer.SLOT_RING) != null) {
                    charos = player.getEquipment().get(EquipmentContainer.SLOT_RING).getId() == ItemNames.RING_OF_CHAROSA_6465;
                }
                if (charos) {
                    interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.", "(Charm) Or I could pay you nothing at all...");
                } else {
                    interpreter.sendOptions("Select an Option", "Yes, please.", "No, thank you.");
                }
                stage = 2;
                break;
            case 2:
                switch (buttonId) {
                    case 1:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, please.");
                        if (player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(0)) {
                        	stage = 9;
						} else {
							stage = 11;
						}
                        break;
                    case 2:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, thank you.");
                        stage = 20;
                        break;
                    case 3:
                        interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Or I could pay you nothing at all...");
                        stage = 5;
                        break;
                }
                break;
            case 5:
                interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Mmmm ... Nothing at all you say ...");
                stage = 6;
                break;
			case 6:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, why not - jump aboard then.");
				if (!player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(1,10)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 1,10, true);
				}
				stage = 30;
				break;
			case 9:
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wait a minute... Aren't those Karamja gloves?", "Thought I'd seen you helping around the island.", "You can go on half price - 15 coins.");
				stage = 10;
				break;
			case 10:
				pay(15);
				break;
            case 11:
                pay(30);
                break;
            case 20:
                end();
                break;
            case 30:
                travel();

        }
        return true;
    }

    /**
     * Method used to pay the fare.
     */
    public void pay(int price) {
        if (!player.getInventory().contains(995, price)) {
            interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, I don't have enough coins for that.");
            stage = 20;
        } else {
            player.getInventory().remove(new Item(995, price));
            player.getPacketDispatch().sendMessage("You pay " + price + " coins and board the ship.");
            travel();
        }
    }

    public void travel() {
        end();
        Ships.PORT_SARIM_TO_KARAMAJA.sail(player);
    }

    @Override
    public int[] getIds() {
        return new int[]{377, 378, 376};
    }
}
