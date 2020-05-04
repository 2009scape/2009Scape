package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the gertrude cat dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GertrudesCatDialogue extends DialoguePlugin {

	/**
	 * Represents the animation of bending down.
	 */
	private final Animation BEND_DOWN = Animation.create(827);

	/**
	 * Constructs a new {@code GertrudesCatDialogue} {@code Object}.
	 */
	public GertrudesCatDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GertrudesCatDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GertrudesCatDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GertrudesCatDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "Talk-with", "Pick-up", "Stroke");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Gertrude's Cat");
		switch (stage) {
		case 545:
			end();
			break;
		case 0:
			switch (buttonId) {
			case 1:
				if (quest.getStage(player) < 60) {
					end();
					npc.sendChat("Miaoww");
				} else {
					interpreter.sendDialogues(player, null, "Oh, it looks like Fluffs has returned.", "I'd better leave her alone.");
					stage = 99;
				}
				break;
			case 2:
				close();
				player.getPulseManager().run(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count++) {
						case 0:
							player.animate(BEND_DOWN);
							break;
						case 2:
							npc.sendChat("Hisss!");
							break;
						case 3:
							player.sendChat("Ouch!");
							break;
						case 4:
							if (quest.getStage(player) == 40) {
								interpreter.sendDialogue("The cat seems afraid to leave.", "In the distance you can hear kittens mewing...");
								stage = 545;
								return true;
							}
							if (quest.getStage(player) == 30) {
								interpreter.sendDialogue("Maybe the cat is hungry?");
								stage = 545;
								return true;
							}
							if (quest.getStage(player) == 50) {
								end();
								return true;
							}
							end();
							interpreter.sendDialogue("Maybe the cat is thirsty?");
							stage = 545;
							break;
						}
						return count == 5;
					}
				});
				break;
			case 545:
				end();
				break;
			case 3:
				close();
				player.getPulseManager().run(new Pulse(1, player) {
					int count = 0;

					@Override
					public boolean pulse() {
						switch (count++) {
						case 0:
							player.animate(BEND_DOWN);
							break;
						case 2:
							npc.sendChat("Hisss!");
							break;
						case 3:
							player.sendChat("Ouch!");
							break;
						case 4:
							if (quest.getStage(player) == 40) {
								return true;
							}
							interpreter.sendDialogue("Perhaps the cat want's something?");
							stage = 545;
							break;
						}
						return count == 5;
					}

				});
				if (quest.getStage(player) == 40) {
					return true;
				}
				GameWorld.Pulser.submit(new Pulse(7, player) {
					@Override
					public boolean pulse() {
						end();
						return true;
					}

				});
				break;
			}
			break;
		case 99:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2997 };
	}
}
