package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.PulseRunner;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.path.Path;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the dialogue plugin used for kitten interactions.
 * @author Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class KittenInteractDialogue extends DialoguePlugin {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(827);

	/**
	 * Constructs a new {@code KittenInterfactDialogue} {@code Object}.
	 */
	public KittenInteractDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code KittenInterfactDialogue} {@code Object}.
	 * @param player the player.
	 */
	public KittenInteractDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new KittenInteractDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		interpreter.sendOptions("Interact with Kitten", "Stroke", "Chase-Vermin", "Shoo-away.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:// stroke
				player.animate(ANIMATION);
				player.getFamiliarManager().getFamiliar().sendChat("Purr...purr...");
				interpreter.sendDialogues(player, null, "That cat sure loves to be stroked.");
				stage = 99;
				break;
			case 2:// chase-vermine
				end();
				player.sendChat("Go on puss...kill that rat!");
				boolean cant = true;
				NPC rat = null;
				for (NPC n : RegionManager.getLocalNpcs(player.getLocation(), 10)) {
					if (!n.getName().contains("rat")) {
						cant = false;
						continue;
					}
					if (n.getLocation().getDistance(player.getFamiliarManager().getFamiliar().getLocation()) < 8) {
						cant = true;
						rat = n;
						break;
					} else {
						cant = false;
					}
				}
				if (!cant) {
					player.getPacketDispatch().sendMessage("Your cat cannot get to its prey.");
				} else {
					player.getFamiliarManager().getFamiliar().sendChat("Meeeoooooowwww!");
					final Path path = Pathfinder.find(player.getFamiliarManager().getFamiliar(), rat);
					path.walk(player.getFamiliarManager().getFamiliar());
					rat.sendChat("Eeek!");
					PulseRunner.submit(new Pulse(5) {

						@Override
						public boolean pulse() {
							player.getFamiliarManager().getFamiliar().call();
							player.getPacketDispatch().sendMessage("The rat manages to get away!");
							return true;
						}

					});
				}
				break;
			case 3:// shoo-away
				interpreter.sendOptions("Are you sure?", "Yes I am.", "No I'm not.");
				stage = 560;
				break;
			}
			break;
		case 560:
			switch (buttonId) {
			case 1:// yes
				player.sendChat("Shoo cat!");
				player.getFamiliarManager().getFamiliar().sendChat("Miaow!");
				// player.getFamiliarManager().getFamiliar().dismiss();//TODO:
				// Pet
				player.getPacketDispatch().sendMessage("The cat has run away.");
				end();
				break;
			case 2:// no
				end();
				break;
			}
			break;
		case 99:
			if (player.getFamiliarManager().hasFamiliar()) {
				player.getFamiliarManager().getFamiliar().sendChat("Miaow!");
			}
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 343823 };
	}
}
