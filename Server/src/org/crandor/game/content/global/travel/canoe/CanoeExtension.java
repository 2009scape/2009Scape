package org.crandor.game.content.global.travel.canoe;

import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.free.gather.SkillingTool;
import org.crandor.game.node.entity.impl.Animator.Priority;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.MinimapStateContext;
import org.crandor.net.packet.out.MinimapState;
import org.crandor.tools.RandomFunction;

/**
 * Represents a class that i used to hold relative information of the states of a canoe for a player.
 * @author Vexia
 * 
 */
public final class CanoeExtension {
	
	/**
	 * Represents the canoe shaping component.
	 */
	private static final Component shape = new Component(52);

	/**
	 * Represents the destination component.
	 */
	private static final Component destination = new Component(53);

	/**
	 * Represents the animation of pushing a canoe.
	 */
	private static final Animation PUSH = new Animation(3301);

	/**
	 * Represents the animation of rowing a canoe.
	 */
	@SuppressWarnings("unused")
	private static final Animation ROW = new Animation(3302);

	/**
	 * Represents the animation of falling over.
	 */
	private static final Animation FALL = new Animation(3303);

	/**
	 * Represents the animation of a floating canoe.
	 */
	private static final Animation FLOAT = new Animation(3304);

	/**
	 * Represents the animation of a sinking canoe.
	 */
	@SuppressWarnings("unused")
	private static final Animation SINK = new Animation(3305);

	/**
	 * Represents multiple config base id's of different states of canoes.
	 */
	private static final int[] CONFIGS = new int[] { 9, 10, 1, 5, 11 };

	/**
	 * Represents the shaping configs.
	 */
	private static final int[][] shapeConfigs = new int[][] {{ 9, 3 }, {10, 2 }, { 8, 5 }};

	/**
	 * Represents the boat childs (indexes)
	 */
	private static final int boatChilds[] = new int[] { 11, 13, 15, 38, 31 };

	/**
	 * Represents the location childs (indexes)
	 */
	private static final int locationChilds[] = new int[] { 50, 47, 44, 36 };

	/**
	 * Represents the distances to travel(indexes)
	 */
	private static final int distances[] = new int[] { 1, 2, 3, 4 };

	/**
	 * Represents the player instance.
	 */
	private final Player player;

	/**
	 * Represents the stage the player is at with crafting a canoe.(1=fallen,
	 * 2=crafted,3=floating)
	 */
	private int stage;

	/**
	 * Represents the current canoe station the {@link #player} is at.
	 */
	private CanoeStation station;

	/**
	 * Represents the current canoe.
	 */
	private Canoe canoe;

	/**
	 * Constructs a new {@code CanoeExtension} {@code Object}.
	 * @param player the player.
	 */
	public CanoeExtension(final Player player) {
		this.player = player;
	}

	/**
	 * Method used to get the extension of this class, if its already there we
	 * return it if not we create a new instance which is added to the player.
	 * @param player the player.
	 * @return the <code>CanoeExtension</code>
	 */
	public static CanoeExtension extension(final Player player) {
		CanoeExtension extension = player.getExtension(CanoeExtension.class);
		if (extension == null) {
			extension = new CanoeExtension(player);
			player.addExtension(CanoeExtension.class, extension);
		}
		return extension;
	}

	/**
	 * Method used to chop down a tree.
	 * @param object the object.
	 */
	public final void chop(final GameObject object) {
		final CanoeStation station = CanoeStation.forObject(object);
		final SkillingTool axe = getTool();
		if (axe == null) {
			player.getPacketDispatch().sendMessage("You do not have an axe which you have the woodcutting level to use.");
			return;
		}
		if (player.getSkills().getLevel(Skills.WOODCUTTING) < 12) {
			player.getPacketDispatch().sendMessage("You need a woodcutting level of at least 12 to chop down this tree.");
			return;
		}
		player.lock(3);
		player.animate(axe.getAnimation());
		player.getConfigManager().set(675, (station.ordinal() + 1) << 17);
		GameWorld.Pulser.submit(new Pulse(4, player) {
			@Override
			public boolean pulse() {
				player.animate(new Animation(-1, Priority.HIGH));
				player.getConfigManager().set(674, CONFIGS[0] << (station.ordinal() * 8));
				player.getConfigManager().set(674, CONFIGS[1] << (station.ordinal() * 8));
				player.getPacketDispatch().sendObjectAnimation(object, FALL, false);
				setStation(station);
				setStage(1);
				return true;
			}
		});
	}

	/**
	 * Method used to shape a canoe.
	 * @param object the object.
	 */
	public final void shape(final GameObject object) {
		player.getInterfaceManager().open(shape);
		for (int i = 0; i < shapeConfigs.length; i++) {
			if (player.getSkills().getLevel(Skills.WOODCUTTING) < Canoe.values()[i].getLevel()) {
				continue;
			}
			player.getPacketDispatch().sendInterfaceConfig(52, shapeConfigs[i][0], true);
			player.getPacketDispatch().sendInterfaceConfig(52, shapeConfigs[i][1], false);
		}
	}

	/**
	 * Method used to craft a canoe.
	 * @param canoe the canoe.
	 */
	public final void craft(final Canoe canoe) {
		if (player.getSkills().getLevel(Skills.WOODCUTTING) < canoe.getLevel()) {
			player.getPacketDispatch().sendMessage("You need a woodcutting level of at least " + canoe.getLevel() + " to make this canoe.");
			return;
		}
		player.getInterfaceManager().close();
		final SkillingTool axe = getTool();
		if (axe == null) {
			player.getPacketDispatch().sendMessage("You do not have an axe which you have the woodcutting level to use.");
			return;
		}
		player.lock(20);
		player.animate(getAnimation(axe));
		player.getPulseManager().run(new Pulse(3) {
			@Override
			public boolean pulse() {
				if (RandomFunction.random(canoe == Canoe.WAKA ? 8 : 6) == 1) {
					if (station == CanoeStation.EDGEVILLE && canoe == Canoe.WAKA && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(2, 0)) {
						player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 2, 0, true);
					}
					player.getConfigManager().set(674, station == CanoeStation.BARBARIAN_VILLAGE ? station.getCraftConfig(canoe, false) : (CONFIGS[2] << (station.ordinal() * 8)) + station.getCraftConfig(canoe, false));
					player.getConfigManager().set(675, (station.ordinal() + 1) << 17);
					player.getSkills().addExperience(Skills.WOODCUTTING, canoe.getExperience());
					setCanoe(canoe);
					setStage(2);
					player.unlock();
					return true;
				}
				player.animate(getAnimation(axe));
				return false;
			}

			@Override
			public void stop() {
				super.stop();
				player.animate(new Animation(-1, Priority.HIGH));
				player.unlock();
			}
		});
	}

	/**
	 * Method used to float the canoe.
	 * @param object the object.
	 */
	public final void floatCanoe(final GameObject object) {
		player.animate(PUSH);
		player.getConfigManager().set(674, (CONFIGS[3] << (station.ordinal() * 8)) + station.getCraftConfig(canoe, true));
		GameWorld.Pulser.submit(new Pulse(1) {
			int counter = 0;

			@Override
			public boolean pulse() {
				if (counter == 1) {
					player.getPacketDispatch().sendObjectAnimation(object, FLOAT, false);
					player.getConfigManager().set(674, (CONFIGS[4] << (station.ordinal() * 8)) + station.getFloatConfig(canoe));
					setStage(3);
				}
				counter += 1;
				return false;
			}
		});
	}

	/**
	 * Method used to travel to a canoe station.
	 * @param station the station to travel to.
	 */
	public final void travel(final CanoeStation station) {
		player.getInterfaceManager().close();
		if (player.getFamiliarManager().hasFamiliar()) {
			player.getPacketDispatch().sendMessage("You can't take a follower on a canoe.");
			return;
		}
		player.lock(18);
		GameWorld.Pulser.submit(new Pulse(1) {
			int count = 0;

			@Override
			public boolean pulse() {
				switch (count++) {
				case 1:
					player.getInterfaceManager().openOverlay(new Component(115));
					break;
				case 3:
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
					player.getInterfaceManager().hideTabs(0, 1, 2, 3, 4, 5, 6, 11, 12);
					break;
				case 16:
					player.getProperties().setTeleportLocation(station.getDestination());
					break;
				case 17:
					if (getStation() == CanoeStation.LUMBRIDGE && station == CanoeStation.EDGEVILLE && canoe == Canoe.WAKA && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(2, 2)) {
						player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 2, 2, true);
					}
					player.getInterfaceManager().close();
					player.getInterfaceManager().restoreTabs();
					PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
					player.getPacketDispatch().sendMessage("You arrive " + (station != CanoeStation.WILDERNESS ? "at " + station.getName() + "." : "in the wilderness. There are no trees suitable to make a canoe."));
					player.getPacketDispatch().sendMessage(station != CanoeStation.WILDERNESS ? "Your canoe sinks into the water after the hard journey." : "Your canoe sinks into the water after the hard journey. Looks like you're");
					if (station == CanoeStation.WILDERNESS) {
						player.getPacketDispatch().sendMessage("walking back.");
					}
					setCanoe(null);
					setStation(null);
					setStage(0);
					player.unlock();
					player.getConfigManager().set(674, 0);
					player.getInterfaceManager().closeOverlay();
					return true;
				}
				return false;
			}

		});
	}

	/**
	 * Method used to paddle the canoe.
	 * @param object the object.
	 */
	public final void paddle(final GameObject object) {
		player.getInterfaceManager().open(destination);
		int index = station.ordinal();
		player.getPacketDispatch().sendInterfaceConfig(53, boatChilds[index], true);
		player.getPacketDispatch().sendInterfaceConfig(53, locationChilds[index], false);
		int distance = distances[canoe.ordinal()];
		for (int i = 0; i < 5; i++) {
			if (Math.abs(station.ordinal() - i) > distance) {
				player.getPacketDispatch().sendInterfaceConfig(53, boatChilds[i], true);
			}
		}
		if (canoe != Canoe.WAKA) {
			player.getPacketDispatch().sendInterfaceConfig(53, 31, true);
		}
	}

	/**
	 * Method used to get the axe to use.
	 */
	private SkillingTool getTool() {
		SkillingTool tool = null;
		if (checkTool(SkillingTool.DRAGON_AXE)) {
			tool = SkillingTool.DRAGON_AXE;
		} else if (checkTool(SkillingTool.RUNE_AXE)) {
			tool = SkillingTool.RUNE_AXE;
		} else if (checkTool(SkillingTool.ADAMANT_AXE)) {
			tool = SkillingTool.ADAMANT_AXE;
		} else if (checkTool(SkillingTool.MITHRIL_AXE)) {
			tool = SkillingTool.MITHRIL_AXE;
		} else if (checkTool(SkillingTool.BLACK_AXE)) {
			tool = SkillingTool.BLACK_AXE;
		} else if (checkTool(SkillingTool.STEEL_AXE)) {
			tool = SkillingTool.STEEL_AXE;
		} else if (checkTool(SkillingTool.IRON_AXE)) {
			tool = SkillingTool.IRON_AXE;
		} else if (checkTool(SkillingTool.BRONZE_AXE)) {
			tool = SkillingTool.BRONZE_AXE;
		}
		return tool;
	}

	/**
	 * Checks if the player has a tool and if he can use it.
	 * @param tool The tool.
	 * @return {@code True} if the tool is usable.
	 */
	private boolean checkTool(SkillingTool tool) {
		if (player.getSkills().getStaticLevel(Skills.WOODCUTTING) < tool.getLevel()) {
			return false;
		}
		if (player.getEquipment().getNew(3).getId() == tool.getId()) {
			return true;
		}
		return player.getInventory().contains(tool.getId(), 1);
	}

	/**
	 * Method used to get the shaping animation of a tool.
	 * @param tool the tool.
	 * @return the animation.
	 */
	private final Animation getAnimation(final SkillingTool tool) {
		Animation animation = null;
		switch (tool) {
		case BRONZE_AXE:
			animation = Animation.create(3291);
			break;
		case IRON_AXE:
			animation = Animation.create(3290);
			break;
		case STEEL_AXE:
			animation = Animation.create(3289);
			break;
		case BLACK_AXE:
			animation = Animation.create(3288);
			break;
		case MITHRIL_AXE:
			animation = Animation.create(3287);
			break;
		case ADAMANT_AXE:
			animation = Animation.create(3286);
			break;
		case RUNE_AXE:
			animation = Animation.create(3285);
			break;
		case DRAGON_AXE:
			animation = Animation.create(3292);
			break;
		default:
			break;
		}
		return animation;
	}

	/**
	 * Gets the station.
	 * @return The station.
	 */
	public CanoeStation getStation() {
		return station;
	}

	/**
	 * Sets the station.
	 * @param station The station to set.
	 */
	public void setStation(CanoeStation station) {
		this.station = station;
	}

	/**
	 * Gets the canoe.
	 * @return The canoe.
	 */
	public Canoe getCanoe() {
		return canoe;
	}

	/**
	 * Sets the canoe.
	 * @param canoe The canoe to set.
	 */
	public void setCanoe(Canoe canoe) {
		this.canoe = canoe;
	}

	/**
	 * Gets the player.
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the stage.
	 * @return The stage.
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 * @param stage The stage to set.
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}

}
