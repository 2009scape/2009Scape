package plugin.activity.duel;

import java.text.DecimalFormat;

import core.game.component.CloseEvent;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import core.game.container.Container;
import core.game.container.ContainerType;
import core.game.container.access.InterfaceContainer;
import core.game.container.impl.EquipmentContainer;
import core.game.content.global.action.EquipHandler;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.PlayerParser;
import core.game.node.entity.player.link.RunScript;
import core.game.node.entity.state.EntityState;
import core.game.node.item.Item;
import core.game.system.config.ItemConfigParser;
import core.game.system.monitor.PlayerMonitor;
import core.plugin.Plugin;
import core.tools.RandomFunction;

/**
 * Represents a duel session.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class DuelSession extends ComponentPlugin {

	/**
	 * The friendly rules interface.
	 */
	private static final Component FRIENDLY_INTER = new Component(637).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			decline(player);
			return true;
		}
	});

	/**
	 * The staked rules interface.
	 */
	private static final Component STAKED_INTER = new Component(631).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			decline(player);
			return true;
		}
	});

	/**
	 * The friendly rule interface.
	 */
	private static final Component FRIENDLY_RULE_INTER = new Component(639).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			decline(player);
			return true;
		}
	});

	/**
	 * The staked rule interface.
	 */
	private static final Component STAKED_RULE_INTER = new Component(626).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			decline(player);
			return true;
		}
	});

	/**
	 * The friendly victory interface.
	 */
	private static final Component FRIENDLY_VICTORY = new Component(633);

	/**
	 * The stake victory.
	 */
	private static final Component STAKE_VICTORY = new Component(634).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			reward(player);
			return true;
		}
	});

	/**
	 * The rules.
	 */
	private final DuelRule[] rules = new DuelRule[DuelRule.values().length];

	/**
	 * The players staked container.
	 */
	private StakeContainer playerContainer;

	/**
	 * The targets staked container.
	 */
	private StakeContainer targetContainer;

	/**
	 * The first player.
	 */
	private final Player player;

	/**
	 * The second player.
	 */
	private final Player other;

	/**
	 * If this is a staked duel.
	 */
	private final boolean staked;

	/**
	 * The accepting state of the session.
	 */
	private int acceptState;

	/**
	 * The fight state (0 not started, 1 started, 2 finished)
	 */
	private int fightState;

	/**
	 * Constructs a new {@code DuelSession} {@code Object}.
	 * @param player The first player.
	 * @param other The second player.
	 */
	public DuelSession(Player player, Player other, boolean staked) {
		this.player = player;
		this.other = other;
		this.staked = staked;
	}

	/**
	 * Declines the duel.
	 * @param player The player declining.
	 */
	public static void decline(Player player) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return;
		}
		if (session.getAcceptState() == 3 || session.getAcceptState() == 7) {
			return;
		}
		if (session.isStaked()) {
			session.getPlayerContainer().release();
			session.getTargetContainer().release();
			session.resetAccept();
		}
		session.player.removeExtension(DuelSession.class);
		session.other.removeExtension(DuelSession.class);
		session.end();
		if (player == session.other) {
			session.player.getPacketDispatch().sendMessage("Other player declined " + (session.staked ? "stake and " : "") + "duel options.");
		} else {
			session.other.getPacketDispatch().sendMessage("Other player declined " + (session.staked ? "stake and " : "") + "duel options.");
		}
	}

	/**
	 * Ends the session.
	 */
	public void end() {
		player.removeAttribute("duel:partner");
		other.removeAttribute("duel:partner");
		player.removeAttribute("duel:staked");
		other.removeAttribute("duel:staked");
		other.removeAttribute("duel:forfeit");
		player.removeAttribute("duel:forfeit");
		player.getInterfaceManager().close();
		player.getInterfaceManager().closeSingleTab();
		player.getInterfaceManager().restoreTabs();
		other.getInterfaceManager().close();
		other.getInterfaceManager().closeSingleTab();
		other.getInterfaceManager().restoreTabs();
		other.getLocks().unlockMovement();
		player.getLocks().unlockMovement();
		player.getHintIconManager().clear();
		other.getHintIconManager().clear();
		heal(player);
		heal(other);
		for (DuelRule rule : rules) {
			if (rule != null) {
				player.getLocks().unlock(rule.name().toLowerCase(), true, player);
				other.getLocks().unlock(rule.name().toLowerCase(), true, other);
			}
		}
	}

	/**
	 * Heals a player after a duel.
	 * @param p the player.
	 */
	public void heal(Player p) {
		p.fullRestore();
		if (p.getStateManager().hasState(EntityState.POISONED)) {
			p.getStateManager().remove(EntityState.POISONED);
		}
		p.getSkills().restore();
	}

	/**
	 * Opens the rules interface.
	 */
	public void openRules() {
		player.setAttribute("duel:partner", other);
		other.setAttribute("duel:partner", player);
		player.setAttribute("duel:staked", staked);
		other.setAttribute("duel:staked", staked);
		player.removeAttribute("duel:accepted");
		other.removeAttribute("duel:accepted");
		openRules(player, other);
		openRules(other, player);
	}

	/**
	 * Opens the rules for a player.
	 * @param player The player.
	 */
	private void openRules(Player player, Player opponent) {
		player.getConfigManager().set(286, 0);
		if (staked) {
			StakeContainer container = new StakeContainer(player, this);
			if (player == this.player) {
				playerContainer = container;
			} else {
				targetContainer = container;
			}
			player.getInterfaceManager().open(STAKED_INTER);
			player.getPacketDispatch().sendString(opponent.getUsername(), 631, 25);
			player.getPacketDispatch().sendString(Integer.toString(opponent.getProperties().getCurrentCombatLevel()), 631, 27);
			player.getPacketDispatch().sendString("", 631, 28);
			container.open();
		} else {
			player.getInterfaceManager().open(FRIENDLY_INTER);
			player.getPacketDispatch().sendString(opponent.getUsername(), 637, 16);
			player.getPacketDispatch().sendString(Integer.toString(opponent.getProperties().getCurrentCombatLevel()), 637, 18);
			player.getPacketDispatch().sendString("", 637, 45);
		}
		player.getConfigManager().set(286, 0);
	}

	/**
	 * Handles the leaving of a player from the duel area.
	 * @param p the player who is forced to leave.
	 * @param type = the type of leaving (0 forfeit, 1 = logout, 2 win)
	 */
	public void leave(Player p, int type) {
		if (fightState == 2) {
			return;
		}
		Player o = getOpposite(p);
		o.getImpactHandler().setDisabledTicks(6);
		o.teleport(RandomFunction.getRandomElement(DuelArea.RESPAWN_LOCATIONS));
		boolean victory = type == 0 || type == 2 || type == 1 && p.getImpactHandler().getImpactLog().containsKey(o);
		fightState = 2;
		p.removeExtension(DuelSession.class);
		end();
		if (victory) {
			if (type == 0) {
				o.sendMessage("Well done! " + p.getUsername() + " resigned!");
			} else if (type == 2) {
				o.sendMessage("Well done! You have defeated " + p.getUsername() + "!");
			}
			victory(o);
		} else if (type == 1) {
			getContainer(player).release();
			getContainer(o).release();
			o.removeExtension(DuelSession.class);
			o.getDialogueInterpreter().sendDialogue("Your opponent timed out, there was no winner!");
		}
		DuelArenaActivity.insertEntry(o, p);
	}

	/**
	 * Handles the victory for a player.
	 * @param player the player.
	 */
	public void victory(Player player) {
		Component component = staked ? STAKE_VICTORY : FRIENDLY_VICTORY;
		player.getInterfaceManager().open(component);
		player.getPacketDispatch().sendString(getOpposite(player).getUsername(), component.getId(), staked ? 23 : 22);
		player.getPacketDispatch().sendString(Integer.toString(getOpposite(player).getProperties().getCurrentCombatLevel()), component.getId(), staked ? 22 : 21);
		if (staked) {
			getContainer(player).release();
			StakeContainer targetContainer = getOppositeContainer(player);
			if (targetContainer.itemCount() > 0) {
				InterfaceContainer.generateItems(player, targetContainer.toArray(), new String[] { "Examine" }, 634, 33);
			}
			Container c = new Container(28, ContainerType.ALWAYS_STACK);
			c.addAll(targetContainer);
			String log = "defeated => " + getOpposite(player).getName() + " and receieved {";
			for (Item i : c.toArray()) {
				if (i == null) {
					continue;
				}
				log += i.getName() + " x " + i.getAmount() + ",";
			}
			if (log.charAt(log.length() - 1) == ',') {
				log = log.substring(0, log.length() - 1);
			}
			log += "}";
			player.getMonitor().log(log, PlayerMonitor.DUEL_LOG);
		} else {
			player.removeExtension(DuelSession.class);
		}
	}

	/**
	 * Rewards the player.
	 * @param player the player.
	 */
	public static void reward(Player player) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null || session.getFightState() == 4) {
			return;
		}
		StakeContainer targetContainer = session.getOppositeContainer(player);
		if (!player.getInventory().hasSpaceFor(targetContainer)) {
			player.getBank().addAll(targetContainer);
			player.sendMessage("An error occured & the stake transfered to your bank.");
		} else {
			session.setFightState(4);
			player.getInventory().addAll(targetContainer);
		}
		player.removeExtension(DuelSession.class);
		PlayerParser.save(player);
	}

	/**
	 * Handles the accepting of the dule rules.
	 */
	private void accept() {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return;
		}
		switch (session.getAcceptState()) {
		case 2:
			if (!session.checkRules(session.player) || !session.checkRules(session.other)) {
				resetAccept();
				return;
			}
			session.player.lock(1);
			session.other.lock(1);
			session.setAcceptState(3);
			session.player.removeAttribute("duel:accepted");
			session.other.removeAttribute("duel:accepted");
			session.other.getInterfaceManager().open(session.isStaked() ? STAKED_RULE_INTER : FRIENDLY_RULE_INTER);
			session.player.getInterfaceManager().open(session.isStaked() ? STAKED_RULE_INTER : FRIENDLY_RULE_INTER);
			session.setAcceptState(4);
			session.player.getInterfaceManager().closeSingleTab();
			session.other.getInterfaceManager().closeSingleTab();
			session.player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 11, 12);
			session.other.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 7, 11, 12);
			StringBuilder before = new StringBuilder();
			StringBuilder during = new StringBuilder();
			if (hasEquipmentRules()) {
				before.append("Some worn items will be taken off.<br>");
			}
			if (hasRule(DuelRule.NO_WEAPON) || hasRule(DuelRule.NO_SHIELD)) {
				during.append("You can't use 2H weapons such as bows.<br>");
			}
			if (hasRule(DuelRule.NO_DRINKS) || hasRule(DuelRule.NO_FOOD)) {
				before.append("Boosted stats will be restored.<br>");
			}
			if (hasRule(DuelRule.NO_PRAYER)) {
				before.append("Existing prayers will be stopped.<br>");
			}
			if (before.length() == 0) {
				before.append("Nothing will be changed.<br>");
			}
			if (during.length() == 0) {
				during.append("You will fight using normal combat.<br>");
			}
			for (DuelRule rule : rules) {
				if (rule != null && rule.getEquipmentSlot() == -1) {
					during.append(rule.getInfo() + "<br>");
				}
			}
			int interfaceId = session.isStaked() ? 626 : 639;
			int[] childs = staked ? new int[] { 28, 29, 30, 31, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45 } : new int[] { 16, 17, 18, 19, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
			clearChilds(session.player, interfaceId, childs);
			clearChilds(session.other, interfaceId, childs);
			String[] tokens = before.toString().split("<br>");
			for (int i = 0; i < tokens.length; i++) {
				session.player.getPacketDispatch().sendString(tokens[i], interfaceId, staked ? (28 + i) : (16 + i));
				session.other.getPacketDispatch().sendString(tokens[i], interfaceId, staked ? (28 + i) : (16 + i));
			}
			tokens = during.toString().split("<br>");
			for (int i = 0; i < tokens.length; i++) {
				session.player.getPacketDispatch().sendString(tokens[i], interfaceId, staked ? (34 + i) : (22 + i));
				session.other.getPacketDispatch().sendString(tokens[i], interfaceId, staked ? (34 + i) : (22 + i));
			}
			if (staked) {
				session.player.getPacketDispatch().sendString(session.getContainer(player).isEmpty() ? "Absolutely nothing!" : getDisplayMessage(session.getContainer(player).toArray()), 626, 25);
				session.player.getPacketDispatch().sendString(session.getOppositeContainer(player).isEmpty() ? "Absolutely nothing!" : getDisplayMessage(session.getOppositeContainer(player).toArray()), 626, 26);
				session.other.getPacketDispatch().sendString(session.getOppositeContainer(player).isEmpty() ? "Absolutely nothing!" : getDisplayMessage(session.getOppositeContainer(player).toArray()), 626, 25);
				session.other.getPacketDispatch().sendString(session.getOppositeContainer(other).isEmpty() ? "Absolutely nothing!" : getDisplayMessage(session.getOppositeContainer(other).toArray()), 626, 26);
			}
			session.updateToolTip(player, "");
			session.updateToolTip(getOpposite(player), "");
			break;
		case 6:
			session.player.lock(2);
			session.other.lock(2);
			session.player.sendMessage("Accepted stake and duel options.");
			session.other.sendMessage("Accepted stake and duel options.");
			session.setAcceptState(7);
			DuelArenaActivity.getDuelArea(hasRule(DuelRule.OBSTACLES)).duel(this);
			session.applyRules(session.player);
			session.applyRules(session.other);
			session.setAcceptState(8);
			break;
		}
	}

	/**
	 * Applies the rules to a player.
	 * @param player the player.
	 */
	public void applyRules(Player player) {
		for (DuelRule rule : rules) {
			if (rule != null) {
				if (rule.getEquipmentSlot() != -1 && player.getEquipment().get(rule.getEquipmentSlot()) != null) {
					EquipHandler.unequip(player, rule.getEquipmentSlot(), player.getEquipment().getId(rule.getEquipmentSlot()));
				}
				player.getLocks().lock(rule.name().toLowerCase(), 100000);
			}
		}
		if (hasRule(DuelRule.NO_SHIELD)) {
			Item i = player.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
			if (i != null && i.getDefinition().getConfiguration(ItemConfigParser.TWO_HANDED, false)) {
				EquipHandler.unequip(player, EquipmentContainer.SLOT_WEAPON, i.getId());
			}
		}
		if (hasRule(DuelRule.NO_DRINKS) || hasRule(DuelRule.NO_FOOD)) {
			player.getSkills().restore();
		}
		if (hasRule(DuelRule.NO_PRAYER)) {
			player.getPrayer().reset();
		}
	}

	/**
	 * Toggles a rule.
	 * @param p The player toggling the rule.
	 * @param index The rule index.
	 */
	private void toggleRule(Player p, int index) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return;
		}
		session.resetAccept();
		if (rules[index] != null) {
			rules[index] = null;
		} else {
			if (index < 3) {
				int count = 0;
				for (int i = 0; i < 3; i++) {
					if (rules[i] != null) {
						count++;
					}
				}
				if (count == 2) {
					session.updateToolTip(player, "You can't have No Ranged, No Melee AND No Magic, how would you fight?");
					p.getPacketDispatch().sendMessage("You can't have No Ranged, No Melee AND No Magic, how would you fight?");
					return;
				}
			} else if ((index == 8 && rules[9] != null) || (index == 9 && rules[8] != null)) {
				rules[8] = rules[9] = null;
				p.getPacketDispatch().sendMessage(index == 8 ? "You can't have obstacles if you want No Movement." : "You can't have No Movement in an area with obstacles.");
			}
			if (index == 15 || index == 16) {
				player.sendMessage("Beware: You won't be able to use two-handed weapons such as bows.");
			}
			rules[index] = DuelRule.values()[index];
		}
		int value = 0;
		for (int i = 0; i < rules.length; i++) {
			if (rules[i] != null) {
				value |= 1 << rules[i].getConfigIndex();
			}
		}
		player.getConfigManager().set(286, value);
		other.getConfigManager().set(286, value);
	}

	/**
	 * Checks the current rules for restrictions.
	 */
	private boolean checkRules(Player player) {
		Container c = new Container(60);
		for (DuelRule rule : rules) {
			if (rule != null && rule.getEquipmentSlot() != -1 && player.getEquipment().get(rule.getEquipmentSlot()) != null) {
				c.add(player.getEquipment().get(rule.getEquipmentSlot()));
			}
		}
		if (hasRule(DuelRule.FUN_WEAPONS) && !hasFunWeapon(player)) {
			updateToolTip(player, "Fun Weapons is selected but you don't have a 'fun weapon'.");
			updateToolTip(getOpposite(player), "Fun Weapons is selected but your opponent does not have a 'fun weapon'.");
			return false;
		}
		if (staked) {
			c.addAll(getContainer(player));
			c.addAll(getOppositeContainer(player));
		}
		if (!player.getInventory().hasSpaceFor(c)) {
			updateToolTip(player, "You do not have enough space for the items removed and/or the stake.");
			updateToolTip(getOpposite(player), "Your opponent does not have enough space for the items removed and/or the stake.");
			return false;
		}
		return true;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, final int slot, int itemId) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return false;
		}
		switch (component.getId()) {
		case 626:// stake rules
			if (button == 53) {
				requestAccept(player, component);
			}
			break;
		case 639:// friendly rules
			if (button == 35) {
				requestAccept(player, component);
				break;
			}
			break;
		case 631:
			if (button == 107) {// decline
				decline(player);
				break;
			} else if (button == 102) {
				requestAccept(player, component);
				break;
			}
			final StakeContainer c = session.getContainer(player);
			if (button == 103) {
				int amount = 0;
				switch (opcode) {
				case 155:
					amount = 1;
					break;
				case 196:
					amount = 5;
					break;
				case 124:
					amount = 10;
					break;
				case 199:
					amount = c.getAmount(c.get(slot));
					break;
				case 234:
					player.setAttribute("runscript", new RunScript() {
						@Override
						public boolean handle() {
							c.withdraw(slot, (int) getValue());
							return true;
						}
					});
					player.getDialogueInterpreter().sendInput(false, "Enter the amount:");
					break;
				}
				c.withdraw(slot, amount);
				break;
			} else if (button == 104) {
				break;
			}
			if (button == 107) {
				player.getInterfaceManager().close();
				break;
			}
			if (button >= 57 && button <= 67) {
				int index = 12 + ((57 - button) * -1);
				if (button == 60) {
					index = DuelRule.values().length - 1;
				} else if (button == 61) {
					index = 15;
				} else if (button == 62) {
					index = DuelRule.NO_BODY.ordinal();
				} else if (button == 63) {
					index = DuelRule.NO_SHIELD.ordinal();
				} else if (button == 64) {
					index = DuelRule.NO_LEGS.ordinal();
				} else if (button == 67) {
					index = DuelRule.NO_GLOVES.ordinal();
				} else if (button == 66) {
					index = DuelRule.NO_BOOTS.ordinal();
				}
				session.toggleRule(player, index);
				break;
			}
			if (button >= 29 && button <= 53) {
				if (button > 49) {
					button--;
				}
				int index = (button - 29) / 2;
				if (index == 11) {
					index = 10;
				} else if (index == 10) {
					index = 11;
				}
				session.toggleRule(player, index);
				break;
			}
			return false;
		case 637:
			if (button == 86) {
				player.getInterfaceManager().close();
				break;
			} else if (button == 83 && session.acceptState < 2) {
				requestAccept(player, component);
				break;
			}
			if (button >= 46 && button <= 56) {
				int index = 0;
				switch (button) {
				case 46:
					index = DuelRule.NO_HATS.ordinal();
					break;
				case 47:
					index = DuelRule.NO_CAPES.ordinal();
					break;
				case 48:
					index = DuelRule.NO_AMULET.ordinal();
					break;
				case 49:
					index = DuelRule.NO_ARROWS.ordinal();
					break;
				case 50:
					index = DuelRule.NO_WEAPON.ordinal();
					break;
				case 51:
					index = DuelRule.NO_BODY.ordinal();
					break;
				case 52:
					index = DuelRule.NO_SHIELD.ordinal();
					break;
				case 53:
					index = DuelRule.NO_LEGS.ordinal();
					break;
				case 54:
					index = DuelRule.NO_RINGS.ordinal();
					break;
				case 55:
					index = DuelRule.NO_BOOTS.ordinal();
					break;
				case 56:
					index = DuelRule.NO_GLOVES.ordinal();
				}
				session.toggleRule(player, index);
				break;
			}
			if (button >= 19 && button <= 42) {
				session.toggleRule(player, (button - 19) / 2);
				break;
			}
			return false;
		case 336:
			final StakeContainer c1 = player == session.getPlayer() ? session.getPlayerContainer() : session.getTargetContainer();
			switch (opcode) {
			case 155:
				c1.offer(slot, 1);
				break;
			case 196:
				c1.offer(slot, 5);
				break;
			case 124:
				c1.offer(slot, 10);
				break;
			case 199:
				c1.offer(slot, player.getInventory().getAmount(player.getInventory().get(slot).getId()));
				break;
			case 234:
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						c1.offer(slot, (int) getValue());
						return true;
					}
				});
				player.getDialogueInterpreter().sendInput(false, "Enter the amount:");
				break;
			case 9:
				player.getPacketDispatch().sendMessage(player.getInventory().get(slot).getDefinition().getExamine());
				break;
			}
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(631, this);
		ComponentDefinition.put(637, this);
		ComponentDefinition.put(626, this);
		ComponentDefinition.put(639, this);
		StakeContainer.OVERLAY.setPlugin(this);
		return this;
	}

	/**
	 * Requests an accepting of the duel.
	 * @param player the player.
	 * @param component the component.
	 */
	public void requestAccept(Player player, Component component) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return;
		}
		// 1 & 5 are waiing.
		// 2 & 6 are completed
		if (session.getAcceptState() == (session.getAcceptState() < 3 ? 1 : 5) && session.getOpposite(player).getAttribute("duel:accepted", false)) {
			session.setAcceptState(session.getAcceptState() == 1 ? 2 : 6);
			session.accept();
			return;
		}
		if (session.getAcceptState() == 0 || session.getAcceptState() == 4) {
			session.setAcceptState(session.getAcceptState() == 0 ? 1 : 5);
			player.setAttribute("duel:accepted", true);
			session.updateToolTip(player, "Waiting for other player...");
			session.updateToolTip(session.getOpposite(player), "Other player accepted.");
		}
	}

	/**
	 * Resets the accept state.
	 */
	public void resetAccept() {
		setAcceptState(getAcceptState() < 3 ? 0 : 4);
		player.removeAttribute("duel:accepted");
		other.removeAttribute("duel:accepted");
	}

	/**
	 * Clears the childs.
	 * @param childs the childs.
	 */
	public void clearChilds(Player player, int interfaceId, int... childs) {
		for (int i : childs) {
			player.getPacketDispatch().sendString("", interfaceId, i);
		}
	}

	/**
	 * Updates the tool tip message.
	 * @param player the player.
	 * @param message the message.
	 */
	private void updateToolTip(Player player, String message) {
		DuelSession session = player.getExtension(DuelSession.class);
		if (session == null) {
			return;
		}
		int interfaceId = 631;
		int child = 28;
		if (!staked) {
			interfaceId = acceptState < 4 ? FRIENDLY_INTER.getId() : FRIENDLY_RULE_INTER.getId();
			child = acceptState < 4 ? 45 : 33;
		} else if (acceptState > 3) {
			interfaceId = STAKED_RULE_INTER.getId();
			child = 45;
		}
		player.getPacketDispatch().sendString(message, interfaceId, child);
	}

	/**
	 * Gets the display message for the stake.
	 * @param items the items.
	 * @return the message.
	 */
	private String getDisplayMessage(Item[] items) {
		String message = "Absolutely nothing!";
		if (items.length > 0) {
			message = "";
			for (int i = 0; i < items.length; i++) {
				if (items[i] == null) {
					continue;
				}
				message = message + "<col=FF9040>" + items[i].getName();
				if (items[i].getAmount() > 1) {
					message = message + "<col=FFFFFF> x ";
					message = message + "<col=FFFF00>" + getFormattedNumber(items[i].getAmount()) + "<br>";
				} else {
					message = message + "<br>";
				}
			}
		}
		return message;
	}

	/**
	 * Checks if the player has a fun weapon.
	 * @param player the player.
	 * @return <code>True</code>
	 */
	public boolean hasFunWeapon(Player player) {
		Container c = new Container(60);
		c.addAll(player.getInventory());
		c.addAll(player.getEquipment());
		for (Item item : c.toArray()) {
			if (item == null) {
				continue;
			}
			if (item.getDefinition().getConfiguration("fun_weapon", false)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the item is restricted equipment.
	 * @param item the item.
	 * @return {@code True} if so.
	 */
	public boolean isRestrictedEquipment(Item item) {
		if (item == null) {
			return false;
		}
		int slot = item.getDefinition().getConfiguration(ItemConfigParser.EQUIP_SLOT, -1);
		if (slot == -1) {
			return false;
		}
		boolean twoHanded = item.getDefinition().getConfiguration(ItemConfigParser.TWO_HANDED, false);
		if (slot == EquipmentContainer.SLOT_WEAPON && twoHanded && hasRule(DuelRule.NO_SHIELD)) {
			return true;
		}
		for (DuelRule rule : rules) {
			if (rule == null) {
				continue;
			}
			if (rule.getEquipmentSlot() == slot) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if this session has equipment rules applied.
	 * @return {@code True} if so.
	 */
	public boolean hasEquipmentRules() {
		for (DuelRule rule : rules) {
			if (rule != null && rule.ordinal() >= DuelRule.NO_HATS.ordinal()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a rule is active.
	 * @param r the rule.
	 * @return {@code True} if so.
	 */
	public boolean hasRule(DuelRule r) {
		return rules[r.ordinal()] != null;
	}

	/**
	 * Gets the opposite container.
	 * @param player the player.
	 * @return the container.
	 */
	public StakeContainer getOppositeContainer(Player player) {
		return player == this.player ? targetContainer : playerContainer;
	}

	/**
	 * Gets the players container.
	 * @param player the player.
	 * @return the staked container.
	 */
	public StakeContainer getContainer(Player player) {
		return player == this.player ? playerContainer : targetContainer;
	}

	/**
	 * Gets the formatted number.
	 * @param amount the amount.
	 * @return the formatted number.
	 */
	private String getFormattedNumber(int amount) {
		return new DecimalFormat("#,###,##0").format(amount).toString();
	}

	/**
	 * Gets the rule size.
	 * @return the amount of active rules.
	 */
	public int getRuleSize() {
		int count = 0;
		for (DuelRule rule : rules) {
			if (rule != null) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Gets the opposite.
	 * @param player the player.
	 * @return the player.
	 */
	public Player getOpposite(Player player) {
		return this.player == player ? other : this.player;
	}

	/**
	 * Gets the other player.
	 * @param player the player.
	 * @return the player.
	 */
	public Player getOther(Player player) {
		return player == this.player ? other : player;
	}

	/**
	 * Gets the rules.
	 * @return The rules.
	 */
	public DuelRule[] getRules() {
		return rules;
	}

	/**
	 * Gets the playerContainer.
	 * @return the playerContainer.
	 */
	public StakeContainer getPlayerContainer() {
		return playerContainer;
	}

	/**
	 * Sets the playerContainer.
	 * @param playerContainer the playerContainer to set
	 */
	public void setPlayerContainer(StakeContainer playerContainer) {
		this.playerContainer = playerContainer;
	}

	/**
	 * Gets the targetContainer.
	 * @return the targetContainer.
	 */
	public StakeContainer getTargetContainer() {
		return targetContainer;
	}

	/**
	 * Sets the targetContainer.
	 * @param targetContainer the targetContainer to set
	 */
	public void setTargetContainer(StakeContainer targetContainer) {
		this.targetContainer = targetContainer;
	}

	/**
	 * Gets the player.
	 * @return the player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the other.
	 * @return the other.
	 */
	public Player getOther() {
		return other;
	}

	/**
	 * Gets the staked.
	 * @return the staked.
	 */
	public boolean isStaked() {
		return staked;
	}

	/**
	 * Gets the acceptState.
	 * @return the acceptState.
	 */
	public int getAcceptState() {
		return acceptState;
	}

	/**
	 * Sets the acceptState.
	 * @param acceptState the acceptState to set
	 */
	public void setAcceptState(int acceptState) {
		this.acceptState = acceptState;
	}

	/**
	 * Gets the fightState.
	 * @return the fightState.
	 */
	public int getFightState() {
		return fightState;
	}

	/**
	 * Sets the fightState.
	 * @param fightState the fightState to set
	 */
	public void setFightState(int fightState) {
		this.fightState = fightState;
	}
}