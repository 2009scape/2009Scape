package plugin.interaction.item.withnpc;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.path.Path;
import core.game.world.map.path.Pathfinder;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle the use with interactions.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class GertrudeCatUsePlugin extends UseWithHandler {

	/**
	 * Represents the animation of bending down.
	 */
	private static final Animation BEND_DOWN = Animation.create(827);

	/**
	 * Represents the empty bucket.
	 */
	private static final Item EMPTY_BUCKET = new Item(1925);

	/**
	 * Constructs a new {@code GertrudeCatUsePlugin} {@code Object}.
	 */
	public GertrudeCatUsePlugin() {
		super(1927, 1552, 327, 13236);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(2997, NPC_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final NPC npc = ((NPC) event.getUsedWith());
		final Quest quest = player.getQuestRepository().getQuest("Gertrude's Cat");
		if (event.getUsedItem().getId() == 1927 && quest.getStage(player) == 20) {
			if (player.getInventory().remove(event.getUsedItem())) {
				player.getInventory().add(EMPTY_BUCKET);
				player.animate(BEND_DOWN);
				npc.sendChat("Mew!");
				quest.setStage(player, 30);
			}
		} else if (event.getUsedItem().getId() == 1552 && quest.getStage(player) == 30) {
			if (player.getInventory().remove(event.getUsedItem())) {
				player.animate(BEND_DOWN);
				npc.sendChat("Mew!");
				quest.setStage(player, 40);
			}
		} else if (event.getUsedItem().getId() == 327 && quest.getStage(player) == 50) {
			player.getDialogueInterpreter().sendDialogue("The cat doesn't seem interested in that.");
		} else if (event.getUsedItem().getId() == 13236) {
			if (player.getInventory().remove(event.getUsedItem())) {
				quest.setStage(player, 60);
				player.lock(5);
				GameWorld.Pulser.submit(new Pulse(1) {
					int count = 0;
					final NPC kitten = NPC.create(761, player.getLocation());

					@Override
					public boolean pulse() {
						switch (count) {
						case 0:
							kitten.init();
							kitten.face(npc);
							npc.face(kitten);
							npc.sendChat("Pur...");
							kitten.sendChat("Pur...");
							final Path path = Pathfinder.find(npc, new Location(3310, 3510, 1));
							path.walk(npc);
							final Path pathh = Pathfinder.find(kitten, new Location(3310, 3510, 1));
							pathh.walk(kitten);
							break;
						case 5:
							kitten.clear();
							player.setAttribute("hidefluff", System.currentTimeMillis() + 60000);
							break;
						}
						count++;
						return count == 6;
					}

				});
			}
		}
		return true;
	}

}
