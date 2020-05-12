/*
package plugin.skill.slayer.dungeon;

import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.content.skill.member.slayer.Master;
import org.crandor.game.interaction.Option;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.impl.ForceMovement;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.zone.MapZone;
import org.crandor.game.world.map.zone.ZoneBorders;
import org.crandor.game.world.map.zone.ZoneBuilder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

*/
/**
 * Handles the stronghold slayer cave.
 * @author Vexia
 * @author Empathy
 *
 *//*

@InitializablePlugin
public class StrongholdSlayerCave extends MapZone implements Plugin<Object> {
	
	*/
/**
	 * The step over animation.
	 *//*

	private final Animation stepOver = new Animation(1603);
	
	*/
/**
	 * The climb animation.
	 *//*

	private final Animation climb = new Animation(3063);
	
	*/
/**
	 * Constructs a new @{Code StrongholdSlayerCave} object.
	 *//*

	public StrongholdSlayerCave() {
		super("Slayer stronghold zone", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean interact(Entity e, final Node node, Option option) {
		int id = node.getId();
		final Player player = e.asPlayer();
		switch (option.getName().toLowerCase()) {
		case "attack":
			if (player.isAdmin()) {
				player.attack(node);
				return true;
			}
			attack: {
				if (player.getSlayer().getMaster() == Master.NIEVE) {
					for (int i : player.getSlayer().getTask().getNpcs()) {
						if (i != id) {
							continue;
						}
						player.attack(node);
						break attack;
					}
				}
				player.face(node.asNpc());
				player.getDialogueInterpreter().sendDialogues(Master.NIEVE.getNpc(), FacialExpression.OSRS_NORMAL, "That's not your assigned Slayer target. In my cave,", "I expect people to focus on their Slayer training.");
			}
			return true;
		case "enter":
			switch (id){
			case 44131://stronghold entrance
				player.teleport(Location.create(2444, 9825, 0));
				break;
			case 42284://kraken entrance
				player.teleport(Location.create(3696, 5798, 0));
				break;
			}
			return true;
		case "use":
			switch (id) {
			case 42287:
				player.teleport(Location.create(2430, 3424, 0));
				break;
			case 42340:
				player.teleport(Location.create(2486, 9797, 0));
				break;
			}
			return true;
		case "chop":
			final GameObject object = node.asObject();
			SkillingTool axe = SkillingTool.getHatchet(player);
			if (axe == null) {
				player.sendMessage("You need an axe to chop through this.");
				return true;
			}
			player.animate(axe.getAnimation());
			player.getPulseManager().run(new Pulse(3, player) {

				@Override
				public boolean pulse() {
					if (ObjectBuilder.replace(object, object.transform(0), 2)) {
						Location destination = getRootLocation(player, object);
						player.lock(3);
						player.getWalkingQueue().reset();
						player.getWalkingQueue().addPath(destination.getX(), destination.getY(), true);
						return true;
					}
					return true;
				}
				
			});
			return true;
		case "climb": 
			final GameObject mud = node.asObject();
			final Location rightPile = Location.create(2427, 9763, 0);
			final Location leftPile = Location.create(2427, 9766, 0);
			if (id == 42311) {
				if (player.getSkills().getLevel(Skills.AGILITY) < 72) {
					player.sendMessage("You need an Agility level of at least 72 to negotiate this obstacle.");
					return true;
				}
				final boolean northPile = mud.getLocation().equals(leftPile);
				ForceMovement.run(player, player.getLocation(), northPile ? leftPile : rightPile, climb);
				player.getPulseManager().run(new Pulse(1, player) {

					int count;
					
					@Override
					public boolean pulse() {
						count++;
						if (count == 6) {
							player.teleport(Location.create(2427, northPile ? 9762 : 9767, 0));
							return true;
						}
						if (count == 3) {
							player.teleport(northPile ? rightPile : leftPile);
							player.faceLocation(northPile ? leftPile : rightPile);
							return false;
						}
						return false;
					}
					
				});
				
			}
			return true;
		case "step-over":
			final GameObject root = node.asObject();
			final Location destination = getRootLocation(player, root);
			player.getPulseManager().run(new Pulse(1, player) {

				@Override
				public boolean pulse() {
					ForceMovement.run(player, player.getLocation(), destination, stepOver);
					return true;
				}
				
			});
			return true;
		}
		return super.interact(e, node, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2244, 9730, 2506, 9845));
		registerRegion(14682);
		registerRegion(9525);
		registerRegion(14939);
	}


	@Override
	public boolean continueAttack(Entity e, Node target, CombatStyle style, boolean message) {
		if (!e.isPlayer() || !(target instanceof NPC)) {
			return super.continueAttack(e, target, style, message);
		}
		Player player = e.asPlayer();
		if (player.isAdmin()) {
			return super.continueAttack(player, target, style, message);
		}
		int id = target.getId();
		if (player.getSlayer().getMaster() == Master.NIEVE) {
			for (int i : player.getSlayer().getTask().getNpcs()) {
				if (i != id) {
					continue;
				}
				return super.continueAttack(player, target, style, message);
			}
		}
		player.face(target.asNpc());
		player.getDialogueInterpreter().sendDialogues(Master.NIEVE.getNpc(), FacialExpression.OSRS_NORMAL, "That's not your assigned Slayer target. In my cave,", "I expect people to focus on their Slayer training.");
		return false;
	}
	
	*/
/**
	 * Gets the location for the player to move to.
	 * @param player The player.
	 * @param object The object.
	 *//*

	private Location getRootLocation(Player player, GameObject object) {
		Location obj = object.getLocation();
		Location play = player.getLocation();
		if (obj.getY() == play.getY()) {
			int newX = (play.getX() - obj.getX() > 0 ? obj.getX() - 1 : obj.getX() + 1);
			return Location.create(newX, play.getY(), play.getZ());
		}
		int newY = (play.getY() - obj.getY() > 0 ? obj.getY() - 1 : obj.getY() + 1);
		return Location.create(play.getX(), newY, play.getZ());
	}

}
*/
