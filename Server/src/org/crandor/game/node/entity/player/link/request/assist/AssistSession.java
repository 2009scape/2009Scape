package org.crandor.game.node.entity.player.link.request.assist;

import org.crandor.game.component.CloseEvent;
import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.request.RequestModule;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents the class used to handle the assist requesting of a {@link Player}
 * .
 * @author 'Vexia
 * @date Oct 3, 2013
 */
public final class AssistSession extends Pulse implements RequestModule {

	/**
	 * Represents the player of this request assist class.
	 */
	private Player player;

	/**
	 * Represents the partener of this class.
	 */
	private Player partener;

	/**
	 * Represents the {@link Component} to display.
	 */
	private final Component component = new Component(301).setCloseEvent(getCloseEvent());

	/**
	 * Represents the {@link Animation} of assisting a player.
	 */
	private static final Animation ANIMATION = Animation.create(7299);

	/**
	 * Represents the {@link Graphics} of assisting a player.
	 */
	private static final Graphics GRAPHIC = Graphics.create(1247);

	/**
	 * The time out until the exp is reset.
	 */
	private static final long TIME_OUT = 86400000;// 60000; //86400000;

	/**
	 * Represents the config values of toggling a button.
	 */
	private static final byte[] CONFIG_VALUES = { 3, 4, 6, 8, 9, 11, 13, 14, 15 };

	/**
	 * Represents used child values of the experience to show.
	 */
	private static final int[] CHILD_VALUES = { 46, 48, 50, 52, 54, 56, 58, 60, 62 };

	/**
	 * Represents the current skills being used.
	 */
	private boolean[] skills = new boolean[9];

	/**
	 * Represents if the session is restricted from gaining experience.
	 */
	private boolean restricted = false;

	/**
	 * Represents the gained exp for the skill(index-experience).
	 */
	private double exp[] = new double[9];

	/**
	 * Represents a kill switch for the session.
	 */
	private boolean kill = false;

	/**
	 * Represents the start time.
	 */
	private long time;

	/**
	 * Constructs a new {@code RequestAssist} {@code Object}.
	 * @param player the player.
	 * @param partener the partener.
	 */
	public AssistSession(final Player player, final Player partener) {
		this.player = player;
		this.partener = partener;
	}

	/**
	 * Constructs a new {@code AssistSession} {@code Object}.
	 */
	public AssistSession() {
		/**
		 * empty.
		 */
	}

	/**
	 * Method used to add the extension of this class to the {@literal Player}.
	 * @param player the player.
	 * @param partener the partener.
	 */
	public static final void extend(final Player player, final Player partener) {
		player.addExtension(AssistSession.class, new AssistSession(player, partener));
	}

	/**
	 * Method used to get the extension of this class from the {@link Player}.
	 * @param player the player.
	 * @return the {@link AssistSession} (this).
	 */
	public final static AssistSession getExtension(final Player player) {
		return player.getExtension(AssistSession.class);
	}

	@Override
	public void open(Player player, Player target) {
		if (player.getIronmanManager().checkRestriction() || target.getIronmanManager().checkRestriction()) {
			return;
		}
		player.face(target);
		target.face(player);
		AssistSession.extend(player, target);
		AssistSession.getExtension(player).open();
	}

	/**
	 * Method used to be called only for the {@link #player} of this class which
	 * then extends this class to the partener.
	 */
	public final void open() {
		partener.addExtension(AssistSession.class, this);
		player.lock();
		player.getInterfaceManager().open(component);
		load();
		player.getPacketDispatch().sendMessage("Sending assistance response.");
		player.getPacketDispatch().sendMessage("You are assisting " + partener.getUsername() + ".");
		partener.getPacketDispatch().sendMessage("You are being assisted by " + player.getUsername() + ".");
		player.getPacketDispatch().sendString("Assist System XP Display - You are assisting " + partener.getUsername() + "", 301, 101);
		player.getPacketDispatch().sendString("", 301, 10);
		player.animate(ANIMATION);
		player.graphics(GRAPHIC);
		partener.animate(ANIMATION);
		partener.getAudioManager().send(4010);
		player.getAudioManager().send(4010);
		toggleIcon(player, false);
		toggleIcon(partener, false);
		GameWorld.Pulser.submit(this);
		refresh();
	}

	/**
	 * Method used to get the {@link CloseEvent} of the {@link Component}.
	 * @return the close event of the {@link #component}.
	 */
	public final CloseEvent getCloseEvent() {
		return new CloseEvent() {
			@Override
			public boolean close(Player player, Component c) {
				save();
				player.removeExtension(AssistSession.class);
				partener.removeExtension(AssistSession.class);
				player.unlock();
				toggleIcon(player, true);
				toggleIcon(partener, true);
				player.getInterfaceManager().restoreTabs();
				player.getPacketDispatch().sendMessage("You have stopped assisting " + partener.getUsername() + ".");
				partener.getPacketDispatch().sendMessage(getPlayer().getUsername() + " has stopped assisting you.");
				kill = true;
				return true;
			}
		};
	}

	/**
	 * Method used to toggle the assisting icons.
	 * @param player the player.
	 * @param hide the icon or not.
	 */
	public final void toggleIcon(final Player player, final boolean hide) {
		for (int i = 79; i < 81; i++) {
			player.getPacketDispatch().sendInterfaceConfig(548, i, hide);
		}
	}

	/**
	 * Method used to override the adding of experience when the player is the
	 * assister.
	 * @param skill the skill.
	 * @param experience the experience.
	 */
	public final void addExperience(final int skill, final double experience) {
		if (restricted || getTotalExperience() >= 30000) {
			restricted = true;
			return;
		}
		final int skillIndex = getSkillIndex(skill);
		if (exp[skillIndex] + experience >= 30000) {
			exp[skillIndex] = 30000;
			restricted = true;
			return;
		}
		exp[skillIndex] += experience;
		if (exp[skillIndex] >= 30000) {
			exp[skillIndex] = 30000;
		}
		refresh();
	}

	/**
	 * Method used to refresh the interface.
	 */
	public final void refresh() {
		int value = 0;
		double totalXp = 0;
		for (byte i = 0; i < 9; i++) {
			if (exp[i] >= 30000) {
				exp[i] = 30000;
			}
			player.getPacketDispatch().sendString("" + (int) exp[i], 301, CHILD_VALUES[i]);
			if (skills[i]) {
				value |= 1 << CONFIG_VALUES[i];
			}
			totalXp += exp[i];
		}
		if (getTotalExperience() >= 30000) {
			restricted = true;
		} else {
			restricted = false;
		}
		String message = "";
		if (isRestricted()) {
			message = "You've earned the maximum XP from the Assist System within a 24-hour period. You can assist again in " + getTimeLeft() + ".";
		}
		player.getPacketDispatch().sendString(message, 301, 10);
		player.getConfigManager().set(1087, value);
		player.getConfigManager().set(1088, (int) totalXp * 10);
	}

	/**
	 * Method used to toggle the skill button.
	 * @param id the id.
	 */
	public final void toggleButton(final byte id) {
		skills[id] = skills[id] ? false : true;
	}

	/**
	 * Method used to check if a specified skill is currently toggled.
	 * @param skill the skill.
	 * @return <code>True</code> if toggled.
	 */
	public final boolean isToggled(final int skill) {
		return skills[getSkillIndex(skill)];
	}

	/**
	 * Method used to get the skill index by the skill id.
	 * @param skill the skill id.
	 * @return the index.
	 */
	public final int getSkillIndex(int skill) {
		switch (skill) {
		case Skills.RUNECRAFTING:
			return 0;
		case Skills.CRAFTING:
			return 1;
		case Skills.FLETCHING:
			return 2;
		case Skills.CONSTRUCTION:
			return 3;
		case Skills.FARMING:
			return 4;
		case Skills.MAGIC:
			return 5;
		case Skills.SMITHING:
			return 6;
		case Skills.COOKING:
			return 7;
		case Skills.HERBLORE:
			return 8;
		}
		return -1;
	}

	/**
	 * Method used to get the skill array.
	 * @return the array of skill's turned on.
	 */
	public boolean[] getSkills() {
		return skills;
	}

	/**
	 * Method used to get the amount of time left.
	 * @return the formated time.
	 */
	public String getTimeLeft() {
		Date date2 = new Date(time);
		Date date1 = new Date(System.currentTimeMillis());
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		long diffSeconds = diff / 1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		if (diffHours > 1) {
			return "" + diffHours + " hours";
		}
		if (diffMinutes > 1) {
			return "" + diffMinutes + " minutes";
		}
		return "" + diffSeconds + " seconds";
	}

	/**
	 * Method used to return the total experience gained.
	 * @return the experience gained.
	 */
	public double getTotalExperience() {
		double xp = 0;
		for (int i = 0; i < 9; i++) {
			xp += exp[i];
		}
		return xp;
	}

	/**
	 * Method used to return the {@link Player}.
	 * @return the <code>Player</code>.
	 */
	public final Player getPlayer() {
		return player;
	}

	/**
	 * Method used to load the information.
	 */
	public final void load() {
		time = player.getSavedData().getGlobalData().getAssistTime();
		if (time == 0) {
			player.getSavedData().getGlobalData().setAssistTime(System.currentTimeMillis() + TIME_OUT);
		}
		for (int i = 0; i < 9; i++) {
			exp[i] = player.getSavedData().getGlobalData().getAssistExperience()[i];
		}
	}

	/**
	 * Method used to save the assist data.
	 */
	public final void save() {
		player.getSkills().refresh();
		player.getSavedData().getGlobalData().setAssistTime(time);
		player.getSavedData().getGlobalData().setAssistExperience(exp);
	}

	/**
	 * Method used to reset the assist data.
	 */
	public final void reset() {
		player.getSavedData().getGlobalData().setAssistTime(0L);
		player.getSavedData().getGlobalData().setAssistExperience(new double[9]);
		load();
	}

	@Override
	public boolean pulse() {
		if (!player.getLocation().withinDistance(partener.getLocation(), 20) || !partener.isActive() || !player.isActive()) {
			player.getInterfaceManager().close();
			return true;
		}
		if (time < System.currentTimeMillis()) {
			time = System.currentTimeMillis() + TIME_OUT;
			for (int i = 0; i < 9; i++) {
				exp[i] = 0;
			}
		}
		refresh();
		return kill;
	}

	/**
	 * Translates experience to another player.
	 * @param p the p.
	 * @param slot the slot.
	 * @param experience the exp.
	 * @param mod the mod.
	 * @return {@code True} if translated.
	 */
	public boolean translateExperience(Player p, int slot, double experience, double mod) {
		if (getPlayer() != p) {
			int index = getSkillIndex(slot);
			if (index != -1) {
				if (index != -1 && !isRestricted()) {
					int level = p.getSkills().getLevel(slot);
					int pLevel = player.getSkills().getLevel(slot);
					if (pLevel < level) {
						return false;
					}
					if (getSkills()[getSkillIndex(slot)]) {
						if (getExp()[index] + experience >= 30000) {
							experience = experience - (getExp()[index] + experience - 30000);
						}
						getPlayer().getSkills().addExperience(slot, experience);
						addExperience(slot, experience * mod);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Method used to return if the assist session is restricted.
	 * @return <code>True</code> if so.
	 */
	public boolean isRestricted() {
		return restricted;
	}

	/**
	 * Gets the exp.
	 * @return the exp.
	 */
	public double[] getExp() {
		return exp;
	}

	/**
	 * Sets th exp.
	 * @param exp the exp.
	 */
	public void setExp(double[] exp) {
		this.exp = exp;
	}

	/**
	 * Gets the partener.
	 * @return the partener
	 */
	public Player getPartener() {
		return partener;
	}

	/**
	 * Sets the bapartener.
	 * @param partener the partener to set.
	 */
	public void setPartener(Player partener) {
		this.partener = partener;
	}

}