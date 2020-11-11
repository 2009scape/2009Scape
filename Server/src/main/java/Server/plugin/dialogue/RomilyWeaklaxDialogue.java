package plugin.dialogue;

import core.game.content.ItemNames;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import core.tools.RandomFunction;

/**
 * Handles the RomilyWeaklaxDialogue dialogue.
 *
 * @author 'Vexia
 */
@InitializablePlugin
public class RomilyWeaklaxDialogue extends DialoguePlugin {
    private enum PieReward {
        APPLE(ItemNames.APPLE_PIE_2323, 84),
        REDBERRY(ItemNames.REDBERRY_PIE_2325, 90),
        MEAT(ItemNames.MEAT_PIE_2327, 96),
        GARDEN(ItemNames.GARDEN_PIE_7178, 112),
        FISH(ItemNames.FISH_PIE_7188, 125),
        ADMIRAL(ItemNames.ADMIRAL_PIE_7198, 387);

        int id, reward;

        PieReward(int id, int reward) {
            this.id = id;
            this.reward = reward;
        }

        protected static PieReward forId(int id) {
            for (PieReward pie : PieReward.values()) {
                if (pie.id == id) {
                    return pie;
                }
            }
            return null;
        }
    }

    private final static String keyAmt = "romily-weaklax:pie-amt";
    private final static String keyId = "romily-weaklax:pie-assigned";

    private int pieId = 0;
    private int pieAmt = 0;
    private PieReward pieReward;

    public RomilyWeaklaxDialogue() {

    }

    public RomilyWeaklaxDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new RomilyWeaklaxDialogue(player);
    }

    @Override
	public void init() {
    	super.init();
    	PluginManager.definePlugin(new RomilyWildPieHandler());
	}

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        pieId = player.getAttribute(keyId, 0);
        pieAmt = player.getAttribute(keyAmt, 0);
        pieReward = PieReward.forId(pieId);

        if (args.length > 1) {
        	Item usedWith = (Item) args[1];
        	if (usedWith.getId() == ItemNames.WILD_PIE_7208) {
        		npc("Is that a wild pie for me?"); // TODO not accurate dialogue, unfortunately offscreen in this video https://www.youtube.com/watch?v=FjlLZnDxofY
        		stage = 100;
        		return true;
			}
		}

        npc("Hello and welcome to my pie shop, how can I help you?");
        if (pieId != 0 && pieAmt != 0) {
            stage = 2;
        } else {
            stage = 0;
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 999:
                end();
                break;
            case 0:
                options("I'd like to buy some pies.", "Do you need any help?", "I'm good thanks.");
                stage = 1;
                break;
            case 1:

                switch (buttonId) {
                    case 1:
                        player("I'd like to buy some pies.");
                        stage = 10;
                        break;
                    case 2:
                        player("Do you need any help?");
                        stage = 20;
                        break;
                    case 3:
                        player("I'm good thanks.");
                        stage = 999;
                        break;
                }
                break;
            case 2:
                options("I'd like to buy some pies.", "I've got those pies you wanted.", "I'm good thanks.");
                stage = 1;
                break;
            case 3:

                switch (buttonId) {
                    case 1:
                        player("I'd like to buy some pies.");
                        stage = 10;
                        break;
                    case 2:
                        player("I've got those pies you wanted.");
                        stage = 50;
                        break;
                    case 3:
                        player("I'm good thanks.");
                        stage = 999;
                        break;
                }
                break;
            case 10:
                npc("Take a look.");
                stage++;
                break;
            case 11:
                end();
                npc.openShop(player);
                break;
            case 20:
                npc("Actually I could, you see I'm running out of stock and I", "don't have tme to bake any more pies. would you be", "willing to bake me some pies? I'll pay you well for them.");
                stage = 21;
                break;
            case 21:
                options("Sure, what do you need?", "Sorry, I can't help you.");
                stage = 22;
                break;
            case 22:
                switch (buttonId) {
                    case 1:
                        player("Sure, what do you need?");
                        stage = 60;
                        break;
                    case 2:
                        player("Sorry, I can't help you.");
                        stage++;
                        break;
                }
                break;
            case 23:
                npc("Come back if you ever want to bake pies.");
                stage = 999;
                break;
            case 50:
                final int piesInInventory = player.getInventory().getAmount(pieId);
                final int deficit = pieAmt - piesInInventory;
                if (piesInInventory == 0) {
                    npc("Doesn't look like you have any of the", pieAmt + " " + new Item(pieId).getName() + "s I requested.");
                    stage = 999;
                    break;
                } else if (deficit == 0) {
                    npc("Thank you very much!");
                    player.removeAttribute(keyAmt);
                    player.removeAttribute(keyId);
                } else {
                    npc("Thank you, if you could bring me the other " + deficit + " that'd", "be great!");
                    player.setAttribute("/save:" + keyAmt, deficit);
                }
                player.getInventory().remove(new Item(pieId, piesInInventory));
                player.getInventory().add(new Item(995, pieReward.reward * piesInInventory));
                stage = 999;
                break;
            case 60:
            	pieAmt = RandomFunction.random(1, 28);
            	pieId = PieReward.values()[RandomFunction.nextInt(PieReward.values().length)].id;
				player.setAttribute("/save:" + keyAmt, pieAmt);
				player.setAttribute("/save:" + keyId, pieId);
                npc("Great, can you bake me " + pieAmt + " " + new Item(pieId).getName() + "s please.");
                stage = 999;
                break;

			case 100:
				player("Yes, it is.");
				stage++;
				break;
			case 101:
				npc("Oh, how splendid! Let me take that from you then.");
				player.getInventory().remove(new Item(ItemNames.WILD_PIE_7208));
				player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 5);
				stage++;
				break;
			case 102:
				npc("Now, was there anything else you needed?");
				if (pieId != 0 && pieAmt != 0) {
					stage = 2;
				} else {
					stage = 0;
				}
				break;
        }

        return true;
    }


    @Override
    public int[] getIds() {
        return new int[]{3205};
    }

    public static final class RomilyWildPieHandler extends UseWithHandler {
    	public RomilyWildPieHandler() {
    		super(ItemNames.WILD_PIE_7208);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
    		addHandler(3205, NPC_TYPE, this);
    		return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
    		if (!event.getPlayer().getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(2,5)) {
				event.getPlayer().getDialogueInterpreter().open(3205, event.getUsedItem());
			}
			return true;
		}
	}

}
