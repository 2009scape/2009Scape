package org.crandor.game.content.skill.member.summoning.familiar;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.skill.SkillBonus;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.SummoningScroll;
import org.crandor.game.content.skill.member.summoning.pet.Pet;
import org.crandor.game.interaction.MovementPulse;
import org.crandor.game.node.entity.Entity;
import org.crandor.game.node.entity.combat.BattleState;
import org.crandor.game.node.entity.combat.CombatPulse;
import org.crandor.game.node.entity.combat.CombatStyle;
import org.crandor.game.node.entity.combat.CombatSwingHandler;
import org.crandor.game.node.entity.combat.equipment.WeaponInterface;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.map.zone.ZoneRestriction;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.Plugin;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a familiar.
 * @author Emperor
 */
public abstract class Familiar extends NPC implements Plugin<Object> {

	/**
	 * The summon graphics for a small familiar.
	 */
	protected static final Graphics SMALL_SUMMON_GRAPHIC = Graphics.create(1314);

	/**
	 * The spawn graphics for a large familiar.
	 */
	protected static final Graphics LARGE_SUMMON_GRAPHIC = Graphics.create(1315);

	/**
	 * The special animation.
	 */
	protected static final Animation SPECIAL_ANIMATION = Animation.create(7660);

	/**
	 * The special graphic.
	 */
	protected static final Graphics SPECIAL_GRAPHIC = Graphics.create(1316);

	/**
	 * The owner.
	 */
	protected Player owner;

	/**
	 * The amount of ticks left.
	 */
	protected int ticks;

	/**
	 * The initial amount of ticks.
	 */
	protected int maximumTicks;

	/**
	 * The amount of special points left.
	 */
	protected int specialPoints = 60;

	/**
	 * The pouch id.
	 */
	private final int pouchId;

	/**
	 * The special move cost.
	 */
	private final int specialCost;

	/**
	 * The combat reward.
	 */
	private CombatSwingHandler combatHandler;

	/**
	 * If the familiar is a combat familiar.
	 */
	protected boolean combatFamiliar;

	/**
	 * If the familiars special is charged.
	 */
	protected boolean charged;

	/**
	 * The invisible familiar boosts.
	 */
	protected List<SkillBonus> boosts = new ArrayList<>();

	/**
	 * The attack style.
	 */
	private final int attackStyle;

	/**
	 * Constructs a new {@code Familiar} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 * @param location The location.
	 * @param ticks The ticks left.
	 * @param pouchId The pouch.
	 * @param specialCost The special move cost.
	 * @param attackStyle the style.
	 */
	public Familiar(Player owner, int id, int ticks, int pouchId, int specialCost, final int attackStyle) {
		super(id, null);
		this.owner = owner;
		this.maximumTicks = ticks;
		this.ticks = ticks;
		this.pouchId = pouchId;
		this.specialCost = specialCost;
		this.combatFamiliar = NPCDefinition.forId(getOriginalId() + 1).getName().equals(getName());
		this.attackStyle = attackStyle;
	}

	/**
	 * Constructs a new {@code Familiar} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 * @param location The location.
	 * @param ticks The ticks left.
	 * @param pouchId The pouch.
	 * @param specialCost The special move cost.
	 */
	public Familiar(Player owner, int id, int ticks, int pouchId, int specialCost) {
		this(owner, id, ticks, pouchId, specialCost, WeaponInterface.STYLE_DEFENSIVE);
	}

	/**
	 * Creates the familiar.
	 * @param loc The location.
	 */
	public void init(Location loc, boolean call) {
		location = loc;
		if (location == null) {
			location = owner.getLocation();
			setInvisible(true);
		}
		super.init();
		startFollowing();
		sendConfiguration();
		if (call) {
			call();
		}
		owner.getInterfaceManager().openInfoBars();
		if (getZoneMonitor().isInZone("Wilderness")) {
			transform();
		}
	}
	
	@Override	
	public void init() {
		init(getSpawnLocation(), true);
	}

	@Override
	public void handleTickActions() {
		if (ticks-- % 50 == 0) {
			updateSpecialPoints(-15);
			owner.getSkills().updateLevel(Skills.SUMMONING, -1, 0);
			if (!getText().isEmpty()) {
				super.sendChat(getText());
			}
		}
		sendTimeRemaining();
		switch (ticks) {
		case 100:
			owner.getPacketDispatch().sendMessage("<col=ff0000>You have 1 minute before your familiar vanishes.");
			break;
		case 50:
			owner.getPacketDispatch().sendMessage("<col=ff0000>You have 30 seconds before your familiar vanishes.");
			break;
		case 0:
			if (isBurdenBeast() && !((BurdenBeast) this).getContainer().isEmpty()) {
				owner.getPacketDispatch().sendMessage("<col=ff0000>Your familiar has dropped all the items it was holding.");
			} else {
				owner.getPacketDispatch().sendMessage("<col=ff0000>Your familiar has vanished.");
			}
			dismiss();
			return;
		}
		CombatPulse combat = owner.getProperties().getCombatPulse();
		if (!isInvisible() && !getProperties().getCombatPulse().isAttacking() && (combat.isAttacking() || owner.inCombat())) {
			Entity victim = combat.getVictim();
			if (victim == null) {
				victim = owner.getAttribute("combat-attacker");
			}
			if (combat.getVictim() != this && victim != null && !victim.isInvisible() && getProperties().isMultiZone() && owner.getProperties().isMultiZone() && isCombatFamiliar() && !isBurdenBeast()) {
				getProperties().getCombatPulse().attack(victim);
			}
		}
		if ((!isInvisible() && owner.getLocation().getDistance(getLocation()) > 12) || (isInvisible() && ticks % 25 == 0)) {
			if (!call()) {
				setInvisible(true);
			}
		} else if (!getPulseManager().hasPulseRunning()) {
			startFollowing();
		}
		handleFamiliarTick();
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (entity == owner) {
			owner.getPacketDispatch().sendMessage("You can't just betray your own familiar like that!");
			return false;
		}
		if (entity instanceof Player) {
			if (!owner.isAttackable(entity, style)) {
				return false;
			}
		}
		if (!getProperties().isMultiZone()) {
			if (entity instanceof Player && !((Player) entity).getProperties().isMultiZone()) {
				((Player) entity).getPacketDispatch().sendMessage("You have to be in multicombat to attack a player's familiar.");
				return false;
			}
			if (entity instanceof Player) {
				((Player) entity).getPacketDispatch().sendMessage("This familiar is not in the a multicombat zone.");
			}
			return false;
		}
		if (entity instanceof Player) {
			if (!((Player) entity).getSkullManager().isWilderness()) {
				((Player) entity).getPacketDispatch().sendMessage("You have to be in the wilderness to attack a player's familiar.");
				return false;
			}
			if (!owner.getSkullManager().isWilderness()) {
				((Player) entity).getPacketDispatch().sendMessage("This familiar's owner is not in the wilderness.");
				return false;
			}
		}
		return super.isAttackable(entity, style);
	}

	@Override
	public void onRegionInactivity() {
		call();
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		if (combatHandler != null) {
			return combatHandler;
		}
		return super.getSwingHandler(swing);
	}

	/**
	 * Constructs a new {@code Familiar} {@code Object}.
	 * @param owner The owner.
	 * @param id The NPC id.
	 * @return The familiar.
	 */
	public abstract Familiar construct(Player owner, int id);

	/**
	 * Executes the special move.
	 * @param special The familiar special object.
	 * @return {@code True} if the move was executed.
	 */
	protected abstract boolean specialMove(FamiliarSpecial special);

	/**
	 * Handles the familiar special tick.
	 */
	protected void handleFamiliarTick() {
	}

	/**
	 * Configures use with events, and other plugin related content..
	 */
	protected void configureFamiliar() {

	}

	/**
	 * Gets the forced chat text for this familiar.
	 * @return The forced chat text.
	 */
	protected String getText() {
		return "";
	}

	/**
	 * Transforms the familiar into the Wilderness combat form.
	 */
	public void transform() {
		if (isCombatFamiliar()) {
			transform(getOriginalId() + 1);
		}
	}

	/**
	 * Sends the time remaining.
	 */
	private void sendTimeRemaining() {
		int minutes = (int) Math.ceil(ticks * 0.01);
		int hash = minutes << 7 | ((ticks - (minutes * 100)) > 49 ? 1 : 0) << 6;
		owner.getConfigManager().set(1176, hash);
	}

	/**
	 * Checks if the familiar can execute its special move and does so if able.
	 * @param special The familiar special object.
	 */
	public void executeSpecialMove(FamiliarSpecial special) {
		if (special.getNode() == this) {
			return;
		}
		if (specialCost > specialPoints) {
			owner.getPacketDispatch().sendMessage("Your familiar does not have enough special move points left.");
			return;
		}
		SummoningScroll scroll = SummoningScroll.forPouch(pouchId);
		if (scroll == null) {
			owner.getPacketDispatch().sendMessage("Invalid scroll for pouch " + pouchId + " - report!");
			return;
		}
		if (!owner.getInventory().contains(scroll.getItemId(), 1)) {
			owner.getPacketDispatch().sendMessage("You do not have enough scrolls left to do this special move.");
			return;
		}
		if (owner.getLocation().getDistance(getLocation()) > 15) {
			owner.getPacketDispatch().sendMessage("Your familiar is too far away to use that scroll, or it cannot see you.");
			return;
		}
		if (specialMove(special)) {
			setAttribute("special-delay", GameWorld.getTicks() + 3);
			owner.getInventory().remove(new Item(scroll.getItemId()));
			visualizeSpecialMove();
			updateSpecialPoints(specialCost);
			owner.getSkills().addExperience(Skills.SUMMONING, scroll.getExperience(), true);
		}
	}

	/**
	 * Sends the special move visualization for the owner.
	 */
	public void visualizeSpecialMove() {
		owner.visualize(Animation.create(7660), Graphics.create(1316));
	}

	/**
	 * Sends a familiar hit.
	 * @param target the target.
	 * @param maxHit the max hit.
	 * @param graphics the graphics.
	 */
	public void sendFamiliarHit(final Entity target, final int maxHit, final Graphics graphics) {
		final int ticks = 2 + (int) Math.floor(getLocation().getDistance(target.getLocation()) * 0.5);
		getProperties().getCombatPulse().setNextAttack(4);
		GameWorld.Pulser.submit(new Pulse(ticks, this, target) {
			@Override
			public boolean pulse() {
				BattleState state = new BattleState(Familiar.this, target);
				int hit = 0;
				if (getCombatStyle().getSwingHandler().isAccurateImpact(Familiar.this, target)) {
					hit = RandomFunction.randomize(maxHit);
				}
				state.setEstimatedHit(hit);
				target.getImpactHandler().handleImpact(owner, hit, CombatStyle.MELEE, state);
				if (graphics != null) {
					target.graphics(graphics);
				}
				return true;
			}
		});
	}

	/**
	 * Sends a projectile to the target.
	 * @param target the target.
	 * @param projectileId the projectile id.
	 */
	public void projectile(final Entity target, final int projectileId) {
		Projectile.magic(this, target, projectileId, 40, 36, 51, 10).send();
	}

	/**
	 * Sends a familiar hit.
	 * @param maxHit the max hit.
	 */
	public void sendFamiliarHit(final Entity target, final int maxHit) {
		sendFamiliarHit(target, maxHit, null);
	}

	/**
	 * Checks if this familiar can attack the target (used mainly for special
	 * moves).
	 */
	public boolean canAttack(Entity target, boolean message) {
		if (!target.isAttackable(owner, owner.getProperties().getCombatPulse().getStyle())) {
			return false;
		}
		if (target.getLocation().getDistance(getLocation()) > 8) {
			if (message) {
				owner.getPacketDispatch().sendMessage("That target is too far.");
			}
			return false;
		}
		if (target.getLocks().isInteractionLocked() || !target.isAttackable(this, CombatStyle.MAGIC)) {
			return false;
		}
		return isCombatFamiliar();
	}

	@Override
	public boolean canAttack(Entity target) {
		return canAttack(target, true);
	}

	/**
	 * Checks if a familiar can perform a combat special attack.
	 * @param target the target.
	 * @param message show message.
	 * @return {@code True} if so.
	 */
	public boolean canCombatSpecial(Entity target, boolean message) {
		if (!canAttack(target, message)) {
			return false;
		}
		if (!isOwnerAttackable()) {
			return false;
		}
		if (getAttribute("special-delay", 0) > GameWorld.getTicks()) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if a faimiliar can perform a combat special attack.
	 * @param target the target.
	 * @return {@code True} if so.
	 */
	public boolean canCombatSpecial(Entity target) {
		return canCombatSpecial(target, true);
	}

	/**
	 * Checks if the owner is attackable.
	 * @return {@code True} if so.
	 */
	public boolean isOwnerAttackable() {
		if (!owner.getProperties().getCombatPulse().isAttacking() && !owner.inCombat() && !getProperties().getCombatPulse().isAttacking()) {
			owner.getPacketDispatch().sendMessage("Your familiar cannot fight whilst you are not in combat.");
			return false;
		}
		return true;
	}

	/**
	 * Gets the combat style.
	 * @return the style.
	 */
	public CombatStyle getCombatStyle() {
		return CombatStyle.MAGIC;
	}

	/**
	 * Adjusts a players battle state.
	 * @param state the state.
	 */
	public void adjustPlayerBattle(final BattleState state) {

	}

	/**
	 * Starts following the owner.
	 */
	public void startFollowing() {
		getPulseManager().run(new MovementPulse(this, owner, Pathfinder.DUMB) {
			@Override
			public boolean pulse() {
				return false;
			}
		}, "movement");
		face(owner);
	}

	@Override
	public void finalizeDeath(Entity killer) {
		dismiss();
	}

	/**
	 * Checks if the familiar is a combat familiar.
	 * @return {@code True} if so.
	 */
	public boolean isCombatFamiliar() {
		return combatFamiliar;
	}

	/**
	 * Sends the familiar packets.
	 */
	public void sendConfiguration() {
		owner.getConfigManager().set(448, getPouchId());
		owner.getConfigManager().set(1174, getOriginalId());
		owner.getConfigManager().set(1175, specialCost << 23);
		sendTimeRemaining();
		updateSpecialPoints(0);
	}

	/**
	 * Calls the familiar.
	 */
	//int spamTimer = 0;
	public boolean call() {
		Location destination = getSpawnLocation();
		if (destination == null) {
			//owner.getPacketDispatch().sendMessage("Your familiar is too big to fit here. Try calling it again when you are standing");
			//owner.getPacketDispatch().sendMessage("somewhere with more space.");
			//spamTimer = 50;
			return false;
		}
		setInvisible(getZoneMonitor().isRestricted(ZoneRestriction.FOLLOWERS) && !owner.getLocks().isLocked("enable_summoning"));
		getProperties().setTeleportLocation(destination);
		if (!(this instanceof Pet)) {
			if (size() > 1) {
				graphics(LARGE_SUMMON_GRAPHIC);
			} else {
				graphics(SMALL_SUMMON_GRAPHIC);
			}
		}
		if (getProperties().getCombatPulse().isAttacking()) {
			startFollowing();
		} else {
			face(owner);
		}
		if (!isRenderable() && owner.isActive()) {
			// System.err.println("Familiar in inactive region!");
			getWalkingQueue().update();
			getUpdateMasks().prepare(this);
		}
		return true;
	}

	/**
	 * Gets the spawning location of the familiar.
	 * @return The spawn location.
	 */
	public Location getSpawnLocation() {
		return RegionManager.getSpawnLocation(owner, this);
	}

	/**
	 * Dismisses the familiar.
	 */
	public void dismiss() {
		clear();
		getPulseManager().clear();
		owner.getInterfaceManager().hideTabs(7);
		owner.getFamiliarManager().setFamiliar(null);
		owner.getConfigManager().set(448, -1);
		owner.getConfigManager().set(1176, 0);
		owner.getConfigManager().set(1175, 182986);
		owner.getConfigManager().set(1174, -1);
		if (owner.getSkullManager().isWilderness()) {
			owner.getAppearance().sync();
		}
		owner.getInterfaceManager().setViewedTab(3);
	}

	/**
	 * Updates the special move points.
	 * @param diff The difference to decrease with.
	 */
	public void updateSpecialPoints(int diff) {
		specialPoints -= diff;
		if (specialPoints > 60) {
			specialPoints = 60;
		}
		owner.getConfigManager().set(1177, specialPoints);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : getIds()) {
			if (FamiliarManager.getFamiliars().containsKey(id)) {
				System.err.println("Familiar " + id + " was already registered!");
				return null;
			}
			FamiliarManager.getFamiliars().put(id, this);
			configureFamiliar();
		}
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	/**
	 * Gets the charged.
	 * @return The charged.
	 */
	public boolean isCharged() {
		if (charged) {
			owner.getPacketDispatch().sendMessage("Your familiar is already charging its attack!");
			return true;
		}
		return false;
	}

	/**
	 * Gets a familiar boost.
	 * @param skill the skill.
	 * @return the boost.
	 */
	public int getBoost(int skill) {
		SkillBonus bonus = null;
		for (SkillBonus b : boosts) {
			if (b.getSkillId() == skill) {
				bonus = b;
				break;
			}
		}
		if (bonus == null) {
			return 0;
		}
		return (int) bonus.getBonus();
	}

	/**
	 * Charges a familiar.
	 */
	public void charge() {
		setCharged(true);
	}

	/**
	 * Sets the charged.
	 * @param charged The charged to set.
	 */
	public void setCharged(boolean charged) {
		this.charged = charged;
	}

	/**
	 * Checks if the familiar is a beast of burden.
	 * @return {@code True} if so.
	 */
	public boolean isBurdenBeast() {
		return false;
	}

	/**
	 * Gets the NPC ids.
	 * @return The npc ids.
	 */
	public abstract int[] getIds();

	/**
	 * Gets the pouch id.
	 * @return The pouch id.
	 */
	public int getPouchId() {
		return pouchId;
	}

	/**
	 * Gets the owner.
	 * @return The owner.
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Gets the combatHandler.
	 * @return The combatHandler.
	 */
	public CombatSwingHandler getCombatHandler() {
		return combatHandler;
	}

	/**
	 * Sets the combatHandler.
	 * @param combatHandler The combatHandler to set.
	 */
	public void setCombatHandler(CombatSwingHandler combatHandler) {
		this.combatHandler = combatHandler;
	}

	/**
	 * Gets the view animation for remote viewing.
	 * @return the animation.
	 */
	public Animation getViewAnimation() {
		return null;
	}

	/**
	 * Gets the exp style.
	 * @return the style.
	 */
	public int getAttackStyle() {
		return attackStyle;
	}

}