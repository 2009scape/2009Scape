package plugin.npc.familiar;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.crafting.jewellery.JewelleryCrafting;
import org.crandor.game.content.skill.free.firemaking.FireMakingPulse;
import org.crandor.game.content.skill.free.firemaking.Log;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.game.world.update.flag.player.FaceLocationFlag;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the Pyrelord familiar.
 * @author Aero
 * @author Vexia
 */
@InitializablePlugin
public class PyreLordNPC extends Familiar {

	/**
	 * The animation of the pyre lord.
	 */
	private static final Animation FIREMAKE_ANIMATION = Animation.create(8085);

	/**
	 * Constructs a new {@code PyreLordNPC} {@code Object}.
	 */
	public PyreLordNPC() {
		this(null, 7377);
	}

	/**
	 * Constructs a new {@code PyreLordNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public PyreLordNPC(Player owner, int id) {
		super(owner, id, 3200, 12816, 6, WeaponInterface.STYLE_AGGRESSIVE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new PyreLordNPC(owner, id);
	}

	@Override
	public void configureFamiliar() {
		PluginManager.definePlugin(new PyreLordFiremake());
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		final Item item = (Item) special.getNode();
		if (item.getId() != 2357) {
			owner.getPacketDispatch().sendMessage("You can only use this special on gold bars.");
			return false;
		}
		owner.lock(1);
		animate(Animation.create(8081));
		owner.graphics(Graphics.create(1463));
		JewelleryCrafting.open(owner);
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7377, 7378 };
	}

	/**
	 * Handles the use with event of a log on a pyrelord.
	 * @author Vexia
	 */
	public final class PyreLordFiremake extends UseWithHandler {

		/**
		 * Constructs a new {@code PyreLordFiremake} {@code Object}.
		 */
		public PyreLordFiremake() {
			super(1511, 2862, 1521, 1519, 6333, 10810, 1517, 6332, 12581, 1515, 1513, 13567, 10329, 10328, 7406, 7405, 7404);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (int id : getIds()) {
				addHandler(id, NPC_TYPE, this);
			}
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final Log log = Log.forId(event.getUsedItem().getId());
			final Familiar familiar = (Familiar) event.getUsedWith();
			final int ticks = FIREMAKE_ANIMATION.getDefinition().getDurationTicks();
			if (!player.getFamiliarManager().isOwner(familiar)) {
				return true;
			}
			if (RegionManager.getObject(familiar.getLocation()) != null || familiar.getZoneMonitor().isInZone("bank")) {
				player.getPacketDispatch().sendMessage("You can't light a fire here.");
				return false;
			}
			familiar.lock(ticks);
			familiar.animate(FIREMAKE_ANIMATION);
			if (player.getInventory().remove(event.getUsedItem())) {
				final GroundItem ground = GroundItemManager.create(event.getUsedItem(), familiar.getLocation(), player);
				GameWorld.Pulser.submit(new Pulse(ticks, player, familiar) {
					@Override
					public boolean pulse() {
						if (!ground.isActive()) {
							return true;
						}
						final GameObject object = new GameObject(log.getFireId(), familiar.getLocation());
						familiar.moveStep();
						GroundItemManager.destroy(ground);
						player.getSkills().addExperience(Skills.FIREMAKING, log.getXp() + 10);
						familiar.faceLocation(FaceLocationFlag.getFaceLocation(familiar, object));
						ObjectBuilder.add(object, log.getLife(), FireMakingPulse.getAsh(player, log, object));
						return true;
					}
				});
			}
			return true;
		}

	}
}
