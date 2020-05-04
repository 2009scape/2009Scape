package plugin.interaction.city;

import org.crandor.cache.def.impl.ObjectDefinition;
import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.global.action.ClimbActionHandler;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.equipment.Ammunition;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the node option handler for lumbridge.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class LumbridgeNodePlugin extends OptionHandler {

	/**
	 * If the flag is in use.
	 */
	private static boolean FLAG_IN_USE;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(36978).getConfigurations().put("option:play", this);
		ObjectDefinition.forId(37335).getConfigurations().put("option:raise", this);
		ObjectDefinition.forId(37095).getConfigurations().put("option:shoot-at", this);
		ObjectDefinition.forId(36976).getConfigurations().put("option:ring", this);
		ObjectDefinition.forId(22114).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(29355).getConfigurations().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		int id = ((GameObject) node).getId();
		switch (id) {
		case 29355:
			if (node.getLocation().getX() == 3209) {
				ClimbActionHandler.climb(player, ClimbActionHandler.CLIMB_UP, new Location(3210, 3216, 0));
				break;
			}
			ClimbActionHandler.climbLadder(player, node.asObject(), "climb-up");
			return true;
		case 37095:
			if (!player.getEquipment().contains(9706, 1) || !player.getEquipment().contains(9705, 1)) {
				player.getPacketDispatch().sendMessage("You need a training bow and arrow to practice here.");
				return true;
			}
			player.getPulseManager().run(new ArcheryTargetPulse(player, (GameObject) node));
			return true;
		case 36978:
			player.lock();
			ActivityManager.start(player, "organ cutscene", false);
			return true;
		case 37335:
			if (!FLAG_IN_USE) {
				FLAG_IN_USE = true;
				player.getPacketDispatch().sendObjectAnimation(((GameObject) node), new Animation(9979));
				player.sendMessage("You start cranking the lever.");
				GameWorld.Pulser.submit(new Pulse(1, player) {
					int counter;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 7:
							player.sendMessage("The flag reaches the top...");
							break;
						case 12:
							player.sendMessage("...and slowly descends.");
							break;
						}
						return counter >= 20;
					}

					@Override
					public void stop() {
						super.stop();
						FLAG_IN_USE = false;
					}
				});
			}
			break;
		case 36976:
			player.getPacketDispatch().sendMessage("The towns people wouldn't appreciate you ringing their bell.");
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			final GameObject object = (GameObject) n;
			if (object.getId() == 36976) {
				return Location.create(3243, 3205, 2);
			} else if (object.getId() == 37095) {
				return n.getLocation().transform(5, 0, 0);
			}
		}
		return null;
	}

	/**
	 * Represents the archery target pulse.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class ArcheryTargetPulse extends Pulse {

		/**
		 * Represents the player instance.
		 */
		private final Player player;

		/**
		 * Represents the game object.
		 */
		private final GameObject object;

		/**
		 * Constructs a new {@code ArcheryTargetPulse} {@code Object}.
		 * @param player the player.
		 * @param object the object.
		 */
		public ArcheryTargetPulse(final Player player, final GameObject object) {
			super(1, player, object);
			this.player = player;
			this.object = object;
		}

		@Override
		public boolean pulse() {
			if (getDelay() == 1) {
				setDelay(player.getProperties().getAttackSpeed());
			}
			if (player.getEquipment().remove(new Item(9706, 1))) {
				Projectile p = Ammunition.get(9706).getProjectile().transform(player, object.getLocation());
				p.setEndLocation(object.getLocation());
				p.setEndHeight(25);
				p.send();
				player.animate(new Animation(426));
				Entity entity = player;
				int level = entity.getSkills().getLevel(Skills.RANGE);
				int bonus = entity.getProperties().getBonuses()[14];
				double prayer = 1.0;
				if (entity instanceof Player) {
					prayer += ((Player) entity).getPrayer().getSkillBonus(Skills.RANGE);
				}
				double cumulativeStr = Math.floor(level * prayer);
				if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_RANGE_ACCURATE) {
					cumulativeStr += 3;
				} else if (entity.getProperties().getAttackStyle().getStyle() == WeaponInterface.STYLE_LONG_RANGE) {
					cumulativeStr += 1;
				}
				cumulativeStr *= 1.0;
				int hit = (int) ((14 + cumulativeStr + (bonus / 8) + ((cumulativeStr * bonus) * 0.016865)) * 1.0) / 10 + 1;
				player.getSkills().addExperience(Skills.RANGE, ((hit * 1.33) / 10));
				return !player.getEquipment().contains(9706, 1) || !player.getEquipment().contains(9705, 1);
			} else {
				return true;
			}
		}

	}
}
