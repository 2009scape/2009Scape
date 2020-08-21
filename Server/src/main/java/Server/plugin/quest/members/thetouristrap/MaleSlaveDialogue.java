package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.tools.RandomFunction;

/**
 * The dialogue for the male slave at the desert camp.
 * @author 'Vexia
 * @version 1.0
 */
public final class MaleSlaveDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code MaleSlaveDialogue} {@code Object}.
	 */
	public MaleSlaveDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MaleSlaveDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MaleSlaveDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MaleSlaveDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (npc.getShownNPC(player).getId()) {
		case 4985:
		case 825:
			switch (quest.getStage(player)) {
			case 40:
				npc("Do you want to trade clothes now?");
				break;
			case 30:
				npc("Hello again, do you want to try and unlock my chains?", "I'd really appreciate it!");
				break;
			case 20:
				npc("You look like a new 'recruit'. How long have you been", "here?");
				break;
			case 100:
				npc("Oh bother, I was caught by the guards again... Listen, if", "you can get me some Desert Clothes, I'll trade you for my", "slave clothes again... Do you want to trade?");
				break;
			default:
				npc("Please, just leave me alone my life is", "terrible as it is.");
				break;
			}
			break;
		case 826:
			switch (quest.getStage(player)) {
			default:
				npc("Yay! I'm finally free.");
				stage = 0;
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (npc.getShownNPC(player).getId()) {
		case 4985:
		case 825:
			switch (quest.getStage(player)) {
			case 30:
				switch (stage) {
				case 0:
					player("Yeah, okay, let's give it a go.");
					stage++;
					break;
				case 1:
					quest.setStage(player, 40);
					npc("Great! You did it! Do you want to trade clothes now?");
					stage = 0;
					break;
				}
				break;
			case 40:
				switch (stage) {
				case 0:
					player("Yes, I'll trade.");
					stage = 11;
					break;
				case 11:
					if (!TouristTrap.hasDesertClothes(player)) {
						npc("I need desert boots, a desert robe and a desert", "shirt if you want these clothes off me.");
						stage++;
					} else {
						npc("Great! You have the Desert Clothes! <col=08088A>-- the slave starts", "<col=08088A>undressing right in front of you --</col> Okay, here's the", "clothes, I won't need them anymore.");
						stage += 2;
					}
					break;
				case 12:
					end();
					break;
				case 13:
					interpreter.sendItemMessage(1845, "The slave gives you his dirty, flea infested robe.");
					stage++;
					break;
				case 14:
					interpreter.sendItemMessage(1844, "The slave gives you his muddy, sweat soaked shirt.");
					stage++;
					break;
				case 15:
					interpreter.sendItemMessage(1846, "The slave gives you a smelly pair of decomposing boots.");
					stage++;
					break;
				case 16:
					if (player.getEquipment().remove(TouristTrap.DESERT_CLOTHES)) {
						stage++;
						npc("Right, I'm off! Good luck!");
						TouristTrap.addConfig(player, (1 << 4));
						for (Item item : TouristTrap.SLAVE_CLOTHES) {
							player.getEquipment().add(item, true, false);
						}
						player.getPacketDispatch().sendMessages("The slave takes your desert robe.", "The slave takes your desert shirt.", "The slave takes your desert boots.");
					}
					break;
				case 17:
					player("Yeah, good luck to you too!");
					break;
				}
				break;
			case 20:
				switch (stage) {
				case 0:
					player("I've just arrived.");
					stage++;
					break;
				case 1:
					npc("Yeah, it looks like it as well. It's a shame that I won't be", "around long enough to get to know you. I'm making a", "break for it today. I have a plan to get out of here! It's", "amazing in its sophistication.");
					stage++;
					break;
				case 2:
					player("Oh yes, that sounds interesting.");
					stage++;
					break;
				case 3:
					npc("Yes, it is actually. I have all the details figured out", "except for one.");
					stage++;
					break;
				case 4:
					player("What's that then?");
					stage++;
					break;
				case 5:
					npc("<col=08088A>-- The slave rattles the chains on his arms loudly. --", "</col>These bracelets, I can't seem to get them off. If I", "could get them off, I'd be able to climb my way out of", "here.");
					stage++;
					break;
				case 6:
					player("I can try to undo them for you.");
					stage++;
					break;
				case 7:
					npc("Really, that would be great... <col=08088A>--The slave looks at you", "<col=08088A>strangely. --</col> Hang on a minute... I suppose you want", "something for doing this?");
					stage++;
					break;
				case 8:
					npc("The last time I did a trade in this place, I nearly lost", "the shirt from my back!");
					stage++;
					break;
				case 9:
					player("It's funny you should say that actually.");
					stage++;
					break;
				case 10:
					npc("<col=08088A>-- the slave looks at you blankly. --");
					stage++;
					break;
				case 11:
					npc("Yeah, go on!");
					stage++;
					break;
				case 12:
					player("If I can get the chains off, you have to give me", "something, okay?");
					stage++;
					break;
				case 13:
					npc("Sure, what do you want?");
					stage++;
					break;
				case 14:
					player("I want your clothes!");
					stage++;
					break;
				case 15:
					npc("Blimey!");
					stage++;
					break;
				case 16:
					player("I can dress like a slave and gain access to the mine", "area to scout it out.");
					stage++;
					break;
				case 17:
					npc("You're either incredibly brave or incredibly stupid. But", "what would I wear if you take my clothes? Get me", "some nice desert clothes and I'll think about it?");
					stage++;
					break;
				case 18:
					npc("Do you still want to try and undo the locks for me?");
					stage++;
					break;
				case 19:
					player("Yeah, okay, let's give it a go.");
					stage++;
					break;
				case 20:
					npc("Great!");
					stage++;
					break;
				case 21:
					interpreter.sendDialogue("You use some nearby bits of wood and wire to try and pick the lock.");
					stage++;
					break;
				case 22:
					final int random = RandomFunction.random(3);
					if (random == 1) {
						interpreter.sendDialogue("You didn't manage to pick the lock this time would you like another", "go?");
						stage++;
					} else if (random == 2) {
						interpreter.sendDialogue("A nearby guard spots you!");
						stage = 24;
					} else {
						quest.setStage(player, 40);
						npc("Great! You did it! Do you want to trade clothes now?");
						stage = 0;
					}
					break;
				case 23:
					player("Yeah, I'll give it another go.");
					stage = 21;
					break;
				case 24:
					player.lock();
					interpreter.sendDialogues(getIds()[0], null, true, "Oh oh!");
					GameWorld.Pulser.submit(new Pulse(4, player) {
						int counter;

						@Override
						public boolean pulse() {
							switch (++counter) {
							case 1:
								interpreter.sendDialogues(4993, null, true, "Oi, what are you two doing?");
								break;
							case 2:
								end();
								player.getPacketDispatch().sendMessage("Slave: Oh oh!");
								player.getPacketDispatch().sendMessage("Guard: Oi, what are you two doing?");
								;
								player.getPacketDispatch().sendMessage("The guards search you!");
								break;
							case 3:
								player.getPacketDispatch().sendMessage("You are roughed up by the guards and manhandled into a cell.");
								break;
							case 4:
								player.unlock();
								quest.setStage(player, 30);
								player.getProperties().setTeleportLocation(Location.create(3285, 3034, 0));
								return true;
							}
							return false;
						}
					});
					stage++;
					break;
				case 25:
					interpreter.sendDialogues(4993, null, true, "Oi, what are you two doing?");
					break;
				}
				break;
			case 100:
				switch (stage) {
				case 0:
					player("Yes, I'll trade.");
					stage++;
					break;
				case 1:
					if (!TouristTrap.hasDesertClothes(player)) {
						npc("I need desert boots, a desert robe and a desert", "shirt if you want these clothes off me.");
						stage++;
						break;
					}
					npc("Great! You have the Desert Clothes! <col=08088A>-- the slave starts", "<col=08088A>undressing right in front of you --</col> Okay, here's the", "clothes, I won't need them anymore.");
					stage = 13;
					break;
				case 2:
					end();
					break;
				case 13:
					interpreter.sendItemMessage(1845, "The slave gives you his dirty, flea infested robe.");
					stage++;
					break;
				case 14:
					interpreter.sendItemMessage(1844, "The slave gives you his muddy, sweat soaked shirt.");
					stage++;
					break;
				case 15:
					interpreter.sendItemMessage(1846, "The slave gives you a smelly pair of decomposing boots.");
					stage++;
					break;
				case 16:
					if (player.getEquipment().remove(TouristTrap.DESERT_CLOTHES)) {
						stage++;
						npc("Right, I'm off! Good luck!");
						TouristTrap.addConfig(player, (1 << 4));
						player.getEquipment().add(TouristTrap.SLAVE_CLOTHES);
						player.getPacketDispatch().sendMessages("The slave takes your desert robe.", "The slave takes your desert shirt.", "The slave takes your desert boots.");
					}
					break;
				case 17:
					player("Yeah, good luck to you too!");
					break;
				}
				break;
			default:
				switch (stage) {
				case 0:
					end();
					break;
				}
				break;
			}
			break;
		case 826:
			switch (quest.getStage(player)) {
			default:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 825, 826, 4985 };
	}

}
