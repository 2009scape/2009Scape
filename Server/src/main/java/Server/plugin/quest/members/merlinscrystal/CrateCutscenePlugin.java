package plugin.quest.members.merlinscrystal;

import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;

/**
 * Handles the crate cutscene plugin.
 * @author Vexia
 */
public class CrateCutscenePlugin extends CutscenePlugin {

	/**
	 * The dialogue plugin.
	 */
	private DialoguePlugin dialogue;

	/**
	 * Constructs a new {@code CrateCutscenePlugin} {@code Object}
	 */
	public CrateCutscenePlugin() {
		super("Merlin Crate Cutscene", false);
	}

	/**
	 * Constructs a new {@code CrateCutscenePlugin} {@code Object}
	 * @param player the player.
	 */
	public CrateCutscenePlugin(Player player) {
		this();
		this.player = player;
	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		setDialogue((DialoguePlugin) args[0]);
		return super.start(player, login, args);
	}

	@Override
	public void open() {
		super.open();
		player.setAttribute("cutscene", this);
		player.getDialogueInterpreter().sendDialogue("You wait.");
	}

	@Override
	public void stop(boolean fade) {
		end();
	}

	@Override
	public boolean leave(Entity entity, boolean logout) {
		if (logout && entity.isPlayer()) {
			entity.setLocation(Location.create(2778, 3401, 0));
		}
		return super.leave(entity, logout);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new CrateCutscenePlugin(p);
	}

	@Override
	public Location getStartLocation() {
		return base.transform(18, 25, 0);
	}

	@Override
	public Location getSpawnLocation() {
		return Location.create(2778, 3401, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(12609);
		setRegionBase();
		registerRegion(region.getId());
		ObjectBuilder.add(new GameObject(65, base.transform(18, 25, 0), 0, 0));
		ObjectBuilder.add(new GameObject(65, base.transform(19, 25, 0), 0, 4));
		ObjectBuilder.add(new GameObject(65, base.transform(18, 24, 0), 0, 1));
		ObjectBuilder.add(new GameObject(65, base.transform(18, 26, 0), 0, 3));
	}

	/**
	 * Gets the dialogue.
	 * @return the dialogue
	 */
	public DialoguePlugin getDialogue() {
		return dialogue;
	}

	/**
	 * Sets the dialogue.
	 * @param dialogue the dialogue to set.
	 */
	public void setDialogue(DialoguePlugin dialogue) {
		this.dialogue = dialogue;
	}

}
