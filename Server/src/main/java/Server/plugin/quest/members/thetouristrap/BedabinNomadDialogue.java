package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;

/**
 * The dialogue plugin used for the bedabin nomad npcs.
 * @author 'Vexia
 * @version 1.0
 */
public final class BedabinNomadDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code BedabinNomadDialogue} {@code Object}.
	 */
	public BedabinNomadDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BedabinNomadDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BedabinNomadDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BedabinNomadDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (npc.getId()) {
		case 834:// guard
			switch (quest.getStage(player)) {
			default:
				npc("Sorry, but you can't use the tent without permission.");
				break;
			case 54:
				if (player.getInventory().containsItem(TouristTrap.TECHNICAL_PLANS)) {
					player("Al Shabim said I could enter, here are the plans!");
					stage++;
				} else {
					npc("Sorry, but you can't use the tent without permission.");
				}
				break;
			case 100:
				npc("Sorry, but you can't use the tent without permission. But", "thanks for all your help with the Bedabin people.");
				break;
			}
			break;
		case 833:// nomad
		case 1239:// fighter
			npc("Hello Effendi! How can I help you?");
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (npc.getId()) {
		case 834:// guard
			switch (quest.getStage(player)) {
			default:
				end();
				break;
			case 54:
				switch (stage) {
				case 0:
					end();
					break;
				case 1:
					npc("Okay, go ahead.");
					stage = 2;
					break;
				case 2:
					end();
					final GameObject door = RegionManager.getObject(new Location(3169, 3046, 0));
					ObjectBuilder.replace(door, door.transform(2701), 2);
					player.getWalkingQueue().reset();
					player.getWalkingQueue().addPath(3169, 3046);
					player.getPacketDispatch().sendMessage("You walk into the tent.");
					break;
				}
				break;
			case 100:
				end();
				break;
			}
			break;
		case 833:// nomad
			switch (stage) {
			case 0:
				options("What is this place?", "Where is Shantay Pass?", "What do you have to sell?", "Okay, thanks.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("What is this place?");
					stage = 10;
					break;
				case 2:
					player("Where is Shantay pass?");
					stage = 20;
					break;
				case 3:
					npc.openShop(player);
					end();
					break;
				case 4:
					player("Okay, thanks.");
					stage = 40;
					break;
				}
			case 10:
				npc("This is the camp of Bedabin. Talk to our leader, Al", "Shabim, he'll be happy to chat. We can sell you very", "reasonably priced water...");
				stage++;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("It is North East of here effendi, across the trackless", "desert. It will be a thirsty trip.");
				stage = 41;
				break;
			case 40:
			case 41:
				end();
				break;
			}
			break;
		case 1239:// fighter
			switch (stage) {
			case 0:
				options("What is this place?", "Where is Shantay Pass?", "Okay, thanks.");
				stage++;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					player("What is this place?");
					stage = 10;
					break;
				case 2:
					player("Where is Shantay pass?");
					stage = 20;
					break;
				case 3:
					player("Okay, thanks.");
					stage = 40;
					break;
				}
				break;
			case 10:
				npc("This is the camp of Bedabin. Talk to our leader, Al", "Shabim, he'll be happy to chat. We can sell you very", "reasonably priced water...");
				stage++;
				break;
			case 11:
				end();
				break;
			case 20:
				npc("It is North East of here effendi, across the trackless", "desert. It will be a thirsty trip.");
				stage = 41;
				break;
			case 40:
			case 41:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { /** bedamin nomad guard */
		834, /** bedamin nomad */
		833, /** bedamin nomad fighter */
		1239 };
	}

}
