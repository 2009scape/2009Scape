package plugin.activity.wguild.dummy;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.chunk.AnimateObjectUpdateFlag;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.tools.RandomFunction;

/**
 * Handles the dummy room.
 * @author Emperor
 */
@InitializablePlugin
public final class DummyRoom extends OptionHandler {

	/**
	 * Represents a dummy.
	 * @author Emperor
	 */
	private static enum Dummy {
		STAB(new GameObject(15629, 2857, 3549, 0, 10, 2), -1, WeaponInterface.BONUS_STAB), SLASH(new GameObject(15625, 2858, 3554, 0), -1, WeaponInterface.BONUS_SLASH), CRUSH(new GameObject(15628, 2859, 3549, 0, 10, 2), -1, WeaponInterface.BONUS_CRUSH), CONTROLLED(new GameObject(15627, 2855, 3552, 0, 10, 3), WeaponInterface.STYLE_CONTROLLED, -1), DEFENCE(new GameObject(15630, 2855, 3550, 0, 10, 3), WeaponInterface.STYLE_DEFENSIVE, -1), AGGRESSIVE(new GameObject(15626, 2860, 3553, 0, 10, 1), WeaponInterface.STYLE_AGGRESSIVE, -1), ACCURATE(new GameObject(15624, 2856, 3554, 0), WeaponInterface.STYLE_ACCURATE, -1), ;

		/**
		 * The object.
		 */
		private final GameObject object;

		/**
		 * The attack style to be used on this dummy.
		 */
		private final int attackStyle;

		/**
		 * The bonus type to be used on this dummy.
		 */
		private final int bonusType;

		/**
		 * Constructs a new {@code Dummy} {@code Object}.
		 * @param object The object.
		 * @param attackStyle The attack style.
		 */
		private Dummy(GameObject object, int attackStyle, int bonusType) {
			this.object = object;
			this.attackStyle = attackStyle;
			this.bonusType = bonusType;
		}

		/**
		 * Gets the object.
		 * @return The object.
		 */
		public GameObject getObject() {
			return object;
		}

		/**
		 * Gets the attackStyle.
		 * @return The attackStyle.
		 */
		public int getAttackStyle() {
			return attackStyle;
		}

		/**
		 * Gets the bonusType.
		 * @return The bonusType.
		 */
		public int getBonusType() {
			return bonusType;
		}
	}

	/**
	 * The current dummy.
	 */
	private static Dummy dummy;

	/**
	 * The time stamp.
	 */
	private static int timeStamp;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(15656).getConfigurations().put("option:view", this);
		for (Dummy dummy : Dummy.values()) {
			ObjectDefinition.forId(dummy.getObject().getId()).getConfigurations().put("option:hit", this);
		}
		GameWorld.Pulser.submit(new Pulse(10) {
			boolean activeDummy;
			GameObject controlled;

			@Override
			public boolean pulse() {
				if (!activeDummy) {
					setDelay(10);
					timeStamp = GameWorld.getTicks();
					dummy = RandomFunction.getRandomElement(Dummy.values());
					ObjectBuilder.replace(RegionManager.getObject(dummy.getObject().getLocation()), dummy.getObject(), 11);
					activeDummy = true;
					if (dummy == Dummy.CONTROLLED && controlled == null) {
						Location l = Location.create(2860, 3551, 0);
						controlled = new GameObject(dummy.getObject().getId(), l, 10, 1);
						ObjectBuilder.replace(RegionManager.getObject(l), controlled, 11);
					}
					return false;
				}
				setDelay(4);
				Animation animation = Animation.create(4164);
				animation.setObject(dummy.getObject());
				RegionManager.getRegionChunk(dummy.getObject().getLocation()).flag(new AnimateObjectUpdateFlag(animation));
				activeDummy = false;
				if (controlled != null) {
					animation = Animation.create(4164);
					animation.setObject(controlled);
					RegionManager.getRegionChunk(controlled.getLocation()).flag(new AnimateObjectUpdateFlag(animation));
					controlled = null;
				}
				return false;
			}
		});
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		if (object.getId() == 15656) {
			player.getInterfaceManager().open(new Component(412));
			return true;
		}
		if (object.getId() == dummy.getObject().getId()) {
			if (timeStamp == player.getAttribute("dummy_ts", -1)) {
				player.getPacketDispatch().sendMessage("You have already hit a dummy this turn.");
				return true;
			}
			if (player.getProperties().getAttackStyle().getStyle() != dummy.getAttackStyle() && player.getProperties().getAttackStyle().getBonusType() != dummy.getBonusType()) {
				player.lock(5);
				player.visualize(player.getProperties().getAttackAnimation(), new Graphics(80, 96));
				player.getPacketDispatch().sendMessage("You whack the dummy with the wrong attack style.");
			} else {
				player.getSkills().addExperience(Skills.ATTACK, 15.0, true);
				player.animate(player.getProperties().getAttackAnimation());
				player.getPacketDispatch().sendMessage("You whack the dummy successfully!");
				player.setAttribute("dummy_ts", timeStamp);
				player.getSavedData().getActivityData().updateWarriorTokens(2);
			}
			return true;
		}
		return false;
	}

}
