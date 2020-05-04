package plugin.npc.familiar;

import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.skill.member.summoning.familiar.Familiar;
import org.crandor.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.path.Path;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Handles the Spirit wolf familiar.
 * @author Emperor
 */
@InitializablePlugin
public final class SpiritWolfNPC extends Familiar {

	/**
	 * Represents the cutscene during wolf whistle.
	 */
	private CutscenePlugin cutscene;

	/**
	 * Constructs a new {@code SpiritWolfNPC} {@code Object}.
	 */
	public SpiritWolfNPC() {
		this(null, 6829);
	}

	/**
	 * Constructs a new {@code SpiritWolfNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 */
	public SpiritWolfNPC(Player owner, int id) {
		super(owner, id, 600, 12047, 3, WeaponInterface.STYLE_ACCURATE);
		if (owner != null) {
			cutscene = (CutscenePlugin) owner.getAttribute("in-cutscene", null);
		}
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new SpiritWolfNPC(owner, id);
	}

	@Override
	public void init() {
		super.init();
		if (cutscene != null) {
			getProperties().setTeleportLocation(cutscene.getBase().transform(44, 52, 1));
			faceLocation(cutscene.getBase().transform(42, 53, 1));
		}
	}

	@Override
	public void startFollowing() {
		if (cutscene != null) {
			return;
		}
		super.startFollowing();
	}

	@Override
	public boolean call() {
		if (cutscene != null) {
			return true;
		}
		return super.call();
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (!(special.getNode() instanceof NPC)) {
			owner.getPacketDispatch().sendMessage("You can only target monsters with this special move.");
			return false;
		}
		final NPC npc = (NPC) special.getNode();
		if (npc.getWalkRadius() > 20) { // Usually indicates special NPC.
			owner.getPacketDispatch().sendMessage("This monster won't get intimidated by your familiar.");
			return false;
		}
		if (cutscene != null) {
			if (npc.getId() != 6990) {
				owner.getPacketDispatch().sendMessage("You can't do this right now.");
				return false;
			}
			DialoguePlugin dial = owner.getAttribute("wolf-dial", null);
			if (dial != null) {
				dial.handle(0, 0);
			} else {
				cutscene.stop(true);
			}
			return false;
		}
		if (!canAttack(npc)) {
			return false;
		}
		visualizeSpecialMove();
		owner.getAudioManager().send(4265);
		faceTemporary(npc, owner, 2);
		super.visualize(Animation.create(8293), new Graphics(1334, 96));
		Projectile.magic(this, npc, 1333, 40, 36, 50, 5).send();
		GameWorld.Pulser.submit(new Pulse(2, this, npc) {
			@Override
			public boolean pulse() {
				npc.faceTemporary(SpiritWolfNPC.this, 2);
				Location destination = npc.getLocation().transform(Direction.getLogicalDirection(location, npc.getLocation()), 3);
				Path path = Pathfinder.find(npc, destination);
				path.walk(npc);
				return true;
			}
		});
		return true;
	}

	@Override
	public Location getSpawnLocation() {
		if (cutscene != null) {
			return cutscene.getBase().transform(44, 52, 1);
		}
		return super.getSpawnLocation();
	}

	@Override
	public int[] getIds() {
		return new int[] { 6829, 6830 };
	}

}
