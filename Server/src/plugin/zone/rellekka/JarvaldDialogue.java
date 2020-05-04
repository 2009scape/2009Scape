package plugin.zone.rellekka;

import org.crandor.game.component.Component;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.LocationLogoutTask;
import org.crandor.game.system.task.LogoutTask;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;

/**
 * Handles the jarvald dialogue.
 * @author Vexia
 */
public final class JarvaldDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code JarvaldDialogue} {@code Object}.
	 */
	public JarvaldDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JarvaldDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JarvaldDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JarvaldDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (args.length > 1) {
			handleTravelStage();
			return true;
		}
		if (npc.getId() == 2438) {
			npc("Ah, you live yet, outerlander!");
			stage = 37;
			return true;
		}
		npc("What do you want from me outerlander?", "It is our policy not to associate with those not of our", "tribe.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			options("Where is your chieftain?", "What Jarvald is doing.", "Nothing");
			stage++;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Where is your chieftain?", "I find it highly discriminatory to refuse to talk to", "someone on the grounds that they are not part of your", "tribe.");
				stage = 10;
				break;
			case 2:
				player("So what are you doing here?");
				stage = 20;
				break;
			case 3:
				end();
				break;
			}
			break;
		case 10:
			npc("I don't rightly understand your speech outerlander, but", "my loyality is with Chieftain Brundt.");
			stage++;
			break;
		case 11:
			npc("He resides in our longhall; it is the large building over", "there, you should speak to him for he speaks for us all.");
			stage++;
			break;
		case 12:
			end();
			break;
		case 20:
			handleTravelStage();
			break;
		case 21:
			player("Hey, scary barbarian type guy, think I can join you on", "this expedition?");
			stage++;
			break;
		case 22:
			npc("An outerlander join us on a honoured hunt???");
			stage++;
			break;
		case 23:
			npc("Well.....", "I gues...", "I might be able to allow you to join us, although it is a", "breach of many of our customs...");
			stage++;
			break;
		case 24:
			player("Oh, pleeeeeeease?", "I really LOVE killing stuff!");
			stage++;
			break;
		case 25:
			npc("Well...", "I remain unconvinced that it would be wise to allow an", "outerlander to join us in such dangerous battle, but", "your ethusiasm seems genuine enough...");
			stage++;
			break;
		case 26:
			npc("I will allow you to escort us, but you must pay me a", "sum of money first.");
			stage++;
			break;
		case 27:
			player("What?", "That's outrageous, why charge me money?", "And, uh, how much does it cost me?");
			stage++;
			break;
		case 28:
			npc("Ah, the outerlander have stolen from my people for", "many years, in this way you can help my community", "with a small amount of money...");
			stage++;
			break;
		case 29:
			npc("Let us say...", "1000 coins.", "Payable in advance, of course.");
			stage++;
			break;
		case 30:
			npc("For this I will take you to Waterbirth Island on my", "boat, and will bring you back here when you have had", "your fill of the hunt.", "Assuming you are still alive to wish to leave, of course.");
			stage++;
			break;
		case 31:
			player.getConfigManager().set(520, 1 << 13, true);
			end();
			break;
		case 32:
			options("YES", "NO");
			stage++;
			break;
		case 33:
			switch (buttonId) {
			case 1:
				if (!player.getInventory().contains(995, 1000)) {
					player("Sorry, I don't have enough coins.");
					stage = 35;
					break;
				}
				if (player.getInventory().remove(new Item(995, 1000))) {
					sail(player, true);
				}
				end();
				break;
			case 2:
				player("No, actually I have some stuff to do here first.");
				stage = 34;
				break;
			}
			break;
		case 34:
			npc("As you wish.", "Come and see me when your bloodlust needs sating.");
			stage++;
			break;
		case 35:
			end();
			break;
		case 36:
			switch (buttonId) {
			case 1:
				npc("Then let us away;", "There will be death to bring here another day!");
				stage = 39;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 37:
			npc("Have you had your fill of the hunt and wish to return,", "or are you still feeling the joy of the cull?");
			stage++;
			break;
		case 38:
			interpreter.sendOptions("Leave island?", "YES", "NO");
			stage = 36;
			break;
		case 39:
			end();
			sail(player, false);
			break;
		}
		return true;
	}

	/**
	 * Sails the player to and from waterbirth.
	 * @param player the player.
	 */
	public void sail(final Player player, final boolean to) {
		player.lock();
		player.getInterfaceManager().open(new Component(224));
		player.addExtension(LogoutTask.class, new LocationLogoutTask(5, to ? Location.create(2544, 3759, 0) : Location.create(2620, 3685, 0)));
		GameWorld.Pulser.submit(new Pulse(1, player) {
			int count;

			@Override
			public boolean pulse() {
				switch (++count) {
				case 5:
					player.unlock();
					player.getInterfaceManager().close();
					player.getProperties().setTeleportLocation(to ? Location.create(2544, 3759, 0) : Location.create(2620, 3685, 0));
					player.getDialogueInterpreter().close();
					player.getDialogueInterpreter().sendDialogue("The ship arrives at " + (to ? "Waterbirth Island" : "Rellekka") + ".");
					return true;
				}
				return false;
			}

		});
	}

	/**
	 * Handles the travel stage.
	 */
	private void handleTravelStage() {
		if (npc.getId() == 2438) {
			interpreter.sendOptions("Leave island?", "YES", "NO");
			stage = 36;
			return;
		}
		if (player.getConfigManager().get(520) == 0) {
			npc("This should not concern you, outerlander.", "I am awaiting other Fremenniks to join me on an", "expedition to Waterbirth Island.");
			stage = 21;
		} else {
			npc("So do you have the 1000 coins for my service, and are", "you ready to leave?");
			stage = 32;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 2435, 2436, 2437, 2438 };
	}

}
