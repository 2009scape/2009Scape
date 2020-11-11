package plugin.npc.familiar;

import core.game.container.impl.EquipmentContainer;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.GroundItem;
import core.game.node.item.GroundItemManager;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.RegionManager;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.player.FaceLocationFlag;
import core.plugin.Plugin;
import core.plugin.PluginManager;
import plugin.skill.Skills;
import plugin.skill.firemaking.FireMakingPulse;
import plugin.skill.firemaking.Log;
import plugin.skill.summoning.familiar.Familiar;
import plugin.skill.summoning.familiar.FamiliarSpecial;
import core.game.node.entity.combat.equipment.WeaponInterface;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the Forge Regent familiar.
 * @author Aero
 */
@InitializablePlugin
public class ForgeRegentNPC extends Familiar {

	/**
	 * The animation of the forge regent.
	 */
	private static final Animation FIREMAKE_ANIMATION = Animation.create(8085); // TODO FIX - this is from pyrelord

	/**
	 * Constructs a new {@code ForgeRegentNPC} {@code Object}.
	 */
	public ForgeRegentNPC() {
		this(null, 7335);
	}

	/**
	 * Constructs a new {@code ForgeRegentNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public ForgeRegentNPC(Player owner, int id) {
		super(owner, id, 4500, 12782, 6, WeaponInterface.STYLE_RANGE_ACCURATE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new ForgeRegentNPC(owner, id);
	}

	@Override
	public void configureFamiliar() {
		PluginManager.definePlugin(new ForgeRegentFiremake());
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (!(special.getTarget() instanceof Player)) {
			owner.sendMessage("You can't use this special on an npc.");
			return false;
		}
		Player target = special.getTarget().asPlayer();
		if (!canCombatSpecial(target)) {
			return false;
		}
		if (target.getInventory().freeSlots() < 1) {
			owner.sendMessage("The target doesn't have enough inventory space.");
			return false;
		}
		Item weapon = target.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
		Item shield = target.getEquipment().get(EquipmentContainer.SLOT_SHIELD);
		if (weapon == null && shield == null) {
			owner.sendMessage("The target doesn't have a weapon or shield.");
			return false;
		}
		Item remove = null;
		while (remove == null) {
			if (RandomFunction.random(2) == 1) {
				remove = weapon;
			} else {
				remove = shield;
			}
		}
		graphics(Graphics.create(1394));
		target.graphics(Graphics.create(1393));
		if (target.getEquipment().remove(remove)) {
			target.getInventory().add(remove);
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7335, 7336 };
	}

	/**
	 * Handles the use with event of a log on a forge regent.
	 */
	public final class ForgeRegentFiremake extends UseWithHandler {

		/**
		 * Constructs a new {@code ForgeRegentFiremake} {@code Object}.
		 */
		public ForgeRegentFiremake() {
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
				GameWorld.getPulser().submit(new Pulse(ticks, player, familiar) {
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
						if (player.getViewport().getRegion().getId() == 10806) {
							player.getAchievementDiaryManager().finishTask(player, DiaryType.SEERS_VILLAGE, 1, 9);
						}
						return true;
					}
				});
			}
			return true;
		}

	}

}
