package plugin.interaction.item.withobject;

import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.smithing.smelting.Bar;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * @author 'Vexia
 */
@InitializablePlugin
public class TutorialItemHandler extends UseWithHandler {
	public TutorialItemHandler() {
		super(438, 436);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(3044, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getInventory().containItems(438, 436)) {
			player.animate(new Animation(833));
			GameWorld.Pulser.submit(new Pulse(2) {
				@Override
				public boolean pulse() {
					player.getInventory().remove(new Item(438, 1));
					player.getInventory().remove(new Item(436, 1));
					player.getInventory().add(Bar.BRONZE.getProduct());
					player.getSkills().addExperience(Skills.SMITHING, Bar.BRONZE.getExperience());
					if (TutorialSession.getExtension(player).getStage() == 39) {
						TutorialStage.load(player, 40, false);
					}
					return true;
				}

			});

		}
		return true;
	}

}
