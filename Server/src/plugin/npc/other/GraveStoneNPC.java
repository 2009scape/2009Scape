package plugin.npc.other;

import java.util.List;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.HintIconManager;
import org.crandor.game.node.entity.player.link.grave.GraveManager;
import org.crandor.game.node.entity.player.link.grave.GraveType;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles a gravestone npc.
 * @author Vexia
 */
@InitializablePlugin
public class GraveStoneNPC extends AbstractNPC {

	/**
	 * The owner of the gravestone.
	 */
	private String owner;

	/**
	 * The display name.
	 */
	private String display;

	/**
	 * The gravestone type.
	 */
	private GraveType type;

	/**
	 * The life decay time.
	 */
	private int life = -1;

	/**
	 * If the stone is blessed.
	 */
	private boolean blessed;

	/**
	 * The ground items list.
	 */
	private List<GroundItem> items;

	/**
	 * Constructs a new {@Code GraveStoneNPC} {@Code Object}
	 */
	public GraveStoneNPC() {
		super(-1, null);
	}

	/**
	 * Constructs a new {@Code GraveStoneNPC} {@Code Object}
	 * @param id the id.
	 * @param location the location.
	 */
	public GraveStoneNPC(int id, Location location) {
		super(id, location);
		super.setWalks(false);
		super.setNeverWalks(true);
	}

	@Override
	public void init() {
		super.init();
		Player player = Repository.getPlayer(owner);
		if (player != null) {
			HintIconManager.registerHintIcon(player, this);
		}
		lock();
		animate(Animation.create(7394));
	}

	@Override
	public void handleTickActions() {
		if (life == -1) {
			return;
		}
		if (life < GameWorld.getTicks()) {
			clear();
			message("Your gravestone has collapsed.");
			return;
		}
		int minutes = getMinutes();
		if (minutes <= 1) {
			int seconds = getSeconds();
			int transformId = -1;
			if (seconds == 100) {
				transformId = getOriginalId() + 1;
			} else if (seconds == 30) {
				transformId = getOriginalId() + 2;
			}
			if (transformId != -1) {
				transform(transformId);
			}
		}
	}

	@Override
	public void clear() {
		super.clear();
		Player player = Repository.getPlayer(owner);
		if (player != null) {
			player.getHintIconManager().clear();
		}
		if (owner != null) {
			GraveManager.getGraves().remove(owner);
		}
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		return false;
	}

	@Override
	public boolean canAttack(final Entity victim) {
		return false;
	}

	@Override
	public Object fireEvent(String identifier, Object... objects) {
		switch (identifier) {
		case "updateItems":
			for (GroundItem item : items) {
				if (item == null) {
					continue;
				}
				item.setDropper((Player) objects[0]);
			}
			break;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		GraveStoneNPC npc = new GraveStoneNPC(id, location);
		if (objects != null && objects.length > 1) {
			npc.setOwner((String) objects[0]);
			npc.setLife((int) objects[1]);
			npc.setItems((List<GroundItem>) objects[2]);
			npc.setType((GraveType) objects[3]);
			npc.setDisplay((String) objects[4]);
		}
		GraveManager.getGraves().put(npc.getOwner(), npc);
		return npc;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new GraveStonePlugin());
		return super.newInstance(arg);
	}

	@Override
	public int[] getIds() {
		return new int[] { 6565, 6568, 6571, 6574, 6577, 6580, 6583, 6586, 6589, 6592, 6595, 6598, 6601 };
	}

	/**
	 * Reads the grave message.
	 * @param player the player.
	 */
	public void read(Player player) {
		player.getConfigManager().set(1146, 1);
		player.getInterfaceManager().open(new Component(266));
		if (!isOwner(player.getName())) {
			player.getPacketDispatch().sendString(getMessage(), 266, 23);
		} else {
			final int minutes = getMinutes();
			String message;
			if (minutes < 1) {
				int seconds = getSeconds();
				message = "It looks like it'll survive another " + (seconds > 1 ? seconds + " seconds." : "second.");
			} else {
				message = "It looks like it'll survive another " + (minutes > 1 ? minutes + " minutes" : "minute") + ".";
			}
			player.getPacketDispatch().sendMessage(message);
			player.getPacketDispatch().sendString(getMessage(), 266, 23);
		}
	}

	/**
	 * Repairs the grave.
	 * @param player the player.
	 */
	public void repair(Player player) {
		if (player.getSkills().getStaticLevel(Skills.PRAYER) < 2) {
			player.getDialogueInterpreter().sendDialogue("You need a prayer level of 2 to repair a gravestone.");
			return;
		}
		if (getId() == getOriginalId()) {
			player.sendMessage("This grave does not need repairing.");
			return;
		}
		int seconds = type.getDecay() * 60;
		int ticks = (1000 * seconds) / 600;
		reTransform();
		updateItems(ticks);
		setLife(GameWorld.getTicks() + ticks);
	}

	/**
	 * Blesses the grave.
	 * @param player the player.
	 */
	public void bless(Player player) {
		if (isOwner(player.getName())) {
			player.getPacketDispatch().sendMessage("The gods don't seem to approve of people attempting to bless their own");
			player.getPacketDispatch().sendMessage("gravestones.");
			return;
		}
		if (player.getSkills().getStaticLevel(Skills.PRAYER) < 70) {
			player.getDialogueInterpreter().sendDialogue("You need a prayer level of 70 to bless a gravestone.");
			return;
		}
		if (player.getSkills().getPrayerPoints() == 0) {
			player.getDialogueInterpreter().sendDialogue("You don't have enough prayer points to do that.");
			return;
		}
		if (isBlessed()) {
			player.getPacketDispatch().sendMessage("This gravestone has already been blessed.");
			return;
		}
		reTransform();
		setBlessed(true);
		updateItems(6100);
		player.animate(Animation.create(645));
		graphics(Graphics.create(1274));
		setLife(GameWorld.getTicks() + 6000);
		message(player.getUsername() + " has blessed your grave, it will remain for another 60 minutes.");
	}

	/**
	 * Demolishes the grave.
	 * @param player the player.
	 */
	public void demolish(Player player) {
		if (!isOwner(player.getName())) {
			player.getPacketDispatch().sendMessage("This is not your grave!");
			return;
		}
		clear();
		player.sendMessage("You destroyed your grave!");
	}

	/**
	 * Updates the items with a new decay.
	 * @param ticks the ticks.
	 */
	private void updateItems(int ticks) {
		if (getItems() != null) {
			for (GroundItem item : getItems()) {
				if (item == null) {
					continue;
				}
				if (item.isActive()) {
					item.setDecayTime(ticks);
				}
			}
		}
	}

	/**
	 * Messages the owner.
	 * @param message the message.
	 */
	private void message(String message) {
		Player o = Repository.getPlayer(owner);
		if (o != null && o.isActive()) {
			o.sendMessage(message);
		}
	}

	/**
	 * Gets the minutes left.
	 * @return the minutes.
	 */
	public int getMinutes() {
		return (life - GameWorld.getTicks()) / 100;
	}

	/**
	 * Gets the seconds left.
	 * @return the seconds.
	 */
	public int getSeconds() {
		return (life - GameWorld.getTicks()) * 600 / 1000;
	}

	/**
	 * Gets the message.
	 * @return the message.
	 */
	public String getMessage() {
		int mins = getMinutes();
		return type.getMessage().replace("@name", display).replace("@mins", (mins > 1 ? mins + " minutes" : mins + " minute"));
	}

	/**
	 * Checks if this name is the owner.
	 * @param name the name.
	 * @return {@code True} if so.
	 */
	public boolean isOwner(String name) {
		return owner.equals(name);
	}

	/**
	 * Gets the owner.
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Gets the display.
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * Sets the badisplay.
	 * @param display the display to set.
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * Sets the baowner.
	 * @param owner the owner to set.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets the life.
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Sets the balife.
	 * @param life the life to set.
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public GraveType getType() {
		return type;
	}

	/**
	 * Sets the batype.
	 * @param type the type to set.
	 */
	public void setType(GraveType type) {
		this.type = type;
	}

	/**
	 * Gets the blessed.
	 * @return the blessed
	 */
	public boolean isBlessed() {
		return blessed;
	}

	/**
	 * Sets the bablessed.
	 * @param blessed the blessed to set.
	 */
	public void setBlessed(boolean blessed) {
		this.blessed = blessed;
	}

	/**
	 * Gets the items.
	 * @return the items
	 */
	public List<GroundItem> getItems() {
		return items;
	}

	/**
	 * Sets the baitems.
	 * @param items the items to set.
	 */
	public void setItems(List<GroundItem> items) {
		this.items = items;
	}

	/**
	 * Handles the interactions of a grave stone.
	 * @author Vexia
	 */
	public class GraveStonePlugin extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (GraveType g : GraveType.values()) {
				for (int start = g.getNpcId(); start < g.getNpcId() + 3; start++) {
					NPCDefinition.forId(start).getConfigurations().put("option:read", this);
					NPCDefinition.forId(start).getConfigurations().put("option:bless", this);
					NPCDefinition.forId(start).getConfigurations().put("option:demolish", this);
					NPCDefinition.forId(start).getConfigurations().put("option:repair", this);
				}
			}
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			if (!(node instanceof GraveStoneNPC)) {
				player.sendMessage("Error! NPC is not instanceof a GraveStoneNPC.");
				return true;
			}
			GraveStoneNPC grave = (GraveStoneNPC) node;
			switch (option) {
			case "read":
				grave.read(player);
				break;
			case "bless":
				grave.bless(player);
				break;
			case "demolish":
				grave.demolish(player);
				break;
			case "repair":
				grave.repair(player);
				break;
			}
			return true;
		}

	}
}
