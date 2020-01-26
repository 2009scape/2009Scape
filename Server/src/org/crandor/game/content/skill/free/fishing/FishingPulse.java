package org.crandor.game.content.skill.free.fishing;

import org.crandor.game.content.global.SkillingPets;
import org.crandor.game.content.global.tutorial.TutorialSession;
import org.crandor.game.content.global.tutorial.TutorialStage;
import org.crandor.game.content.skill.SkillPulse;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.summoning.familiar.Forager;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.portal.Perks;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.entity.player.link.skillertasks.SkillTasks;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.tools.RandomFunction;

/**
 * Handles a fishing pulse.
 * @author Vexia
 */
public final class FishingPulse extends SkillPulse<NPC> {

	/**
	 * Represents the fishing option.
	 */
	private final FishingOption option;

	/**
	 * Represents the fish type.
	 */
	private Fish fish;

	/**
	 * Represents the base location the npc was at.
	 */
	private final Location location;

	/**
	 * Constructs a new {@code FishingPulse} {@code Object}.
	 * @param player the player.
	 * @param npc the fishing spot NPC.
	 * @param option The fishing option.
	 */
	public FishingPulse(final Player player, final NPC npc, final FishingOption option) {
		super(player, npc);
		this.option = option;
		this.location = npc.getLocation();
		if (option != null) {
			this.fish = option.getRandomFish(player);
		}
	}

	@Override
	public void start() {
		if (TutorialSession.getExtension(player).getStage() == 12) {
			TutorialStage.load(player, 13, false);
		}
		if (player.getFamiliarManager().hasFamiliar() && player.getFamiliarManager().getFamiliar() instanceof Forager) {
			final Forager forager = (Forager) player.getFamiliarManager().getFamiliar();
			Location dest = player.getLocation().transform(player.getDirection());
			Pathfinder.find(forager.getLocation(), dest).walk(forager);
		}
		super.start();
	}

	@Override
	public boolean checkRequirements() {
		if (option == null) {
			return false;
		}
		player.debug(String.valueOf(player.getInventory().containsItem(option.getTool())));
		if (!player.getInventory().containsItem(option.getTool()) && !hasBarbTail()) {
			//System.out.println(isBareHanded(player));
			player.getDialogueInterpreter().sendDialogue("You need a " + option.getTool().getName().toLowerCase() + " to catch these fish.");
			stop();
			return false;
		}
		if (option.getBait() != null && !player.getInventory().containsItem(option.getBait())) {
			player.getDialogueInterpreter().sendDialogue("You don't have any " + option.getBait().getName().toLowerCase() + "s left.");
			stop();
			return false;
		}
		if (player.getSkills().getLevel(Skills.FISHING) < fish.getLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a fishing level of " + fish.getLevel() + " to catch " + (fish == Fish.SHRIMP || fish == Fish.ANCHOVIE ? "" : "a") + " " + fish.getItem().getName().toLowerCase() + ".".trim());
			stop();
			return false;
		}
		if (player.getInventory().freeSlots() == 0) {
			player.getDialogueInterpreter().sendDialogue("You don't have enough space in your inventory.");
			stop();
			return false;
		}
		if (location != node.getLocation() || !node.isActive() || node.isInvisible()) {
			stop();
			return false;
		}
		return true;
	}

	@Override
	public void animate() {
		if (isBareHanded(player)) {
			player.animate(new Animation(6709));
			player.animate(new Animation(6709));
			GameWorld.submit(new Pulse(1) {
				int counter = 0;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 5:
						getCatchAnimationAndLoot(player);
						break;
					}
					return false;
				}
			});
		} else {
			player.animate(option.getAnimation());
		}
	}

	@Override
	public boolean reward() {
		if (getDelay() == 1) {
			super.setDelay(5);
			return false;
		}
		if (node.getId() == 333 && player.getZoneMonitor().isInZone("karamja") && player.getLocation().withinDistance(new Location(2924, 3178, 0), 10) && !player.getAchievementDiaryManager().hasCompletedTask(DiaryType.KARAMJA, 0, 6)) {
			player.getAchievementDiaryManager().updateTask(player, DiaryType.KARAMJA, 0, 6, true);
		}
		if (player.getFamiliarManager().hasFamiliar() && player.getFamiliarManager().getFamiliar() instanceof Forager) {
			final Forager forager = (Forager) player.getFamiliarManager().getFamiliar();
			forager.handlePassiveAction();
		}
		if (success()) {
			if (player.getInventory().hasSpaceFor(fish.getItem()) && option.getBait() != null ? player.getInventory().remove(option.getBait()) : true) {
				
				if (fish == Fish.TROUT && player.getLocation().withinDistance(new Location(3105, 3429, 0)) && !player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 11)) {
					player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 11, true);
				}
				if (fish == Fish.ANCHOVIE && player.getViewport().getRegion().getId() == 13105 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 6)) {
					player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 0, 6, true);
				}
				if (fish == Fish.SALMON && player.getViewport().getRegion().getId() == 12850 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(1, 3)) {
					player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 1, 3, true);
				}
				
				if (fish == Fish.ANCHOVIE) { 
					player.getSkillTasks().decreaseTask(player, SkillTasks.FANCHOVIES1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FANCHOVIES2);
				} else if (fish == Fish.HERRING) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FHERRING1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FHERRING2);
				} else if (fish == Fish.LOBSTER) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FLOBSTER1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FLOBSTER2);
				} else if (fish == Fish.SALMON) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSALMON1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSALMON2);
				} else if (fish == Fish.SHARK) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSHARK1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSHARK2);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSHARK3);
				} else if (fish == Fish.SHRIMP) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSHRIMP1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSHRIMP2);
				} else if (fish == Fish.SWORDFISH) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSWORD1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FSWORD2);
				} else if (fish == Fish.TROUT) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FTROUT1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FTROUT2);
				} else if (fish == Fish.TUNA) {
					player.getSkillTasks().decreaseTask(player, SkillTasks.FTUNA1);
					player.getSkillTasks().decreaseTask(player, SkillTasks.FTUNA2);
				}

				SkillingPets.checkPetDrop(player, SkillingPets.HERON);
				final Item item = fish.getItem();
			    player.getInventory().add(item);
				Perks.addDouble(player, item);
				player.getSkills().addExperience(Skills.FISHING, fish.getExperience(), true);
				message(2);
				if (TutorialSession.getExtension(player).getStage() == 13) {
					TutorialStage.load(player, 14, false);
					stop();
					return true;
				}
				fish = option.getRandomFish(player);
			}
		}
		return player.getInventory().freeSlots() == 0;
	}
	
	private boolean isBareHanded(Player p) {
		if (option == FishingOption.HARPOON) {
			if (checkFish(p) > 0 && !player.getInventory().containsItem(option.getTool()) || !player.getEquipment().containsItem(option.getTool())) {
				return true;
			}
			if (checkFish(p) > 2 && !player.getInventory().containsItem(option.getTool()) || !player.getEquipment().containsItem(option.getTool())) {
				return true;
			}
		}
		return false;
	}
	
	private int getCatchAnimationAndLoot(Player p) {
		int fishingFor = checkFish(p);
		switch(node.getId()) {
		case 324:
			switch(fishingFor) {
			case 1:
				p.animate(new Animation(6710));
				p.getSkills().addExperience(Skills.FISHING, 80);
				p.getSkills().addExperience(Skills.STRENGTH, 8);
				p.getInventory().add(new Item(359));
				break;
			case 2:
			case 3:
				if (RandomFunction.random(1) == 1) {
					p.animate(new Animation(6710));
					p.getSkills().addExperience(Skills.FISHING, 80);
					p.getSkills().addExperience(Skills.STRENGTH, 8);
					p.getInventory().add(new Item(359));
				} else {
					p.animate(new Animation(6707));
					p.getSkills().addExperience(Skills.FISHING, 100);
					p.getSkills().addExperience(Skills.STRENGTH, 10);
					p.getInventory().add(new Item(371));
				}
			}
			break;
		case 313:
			p.animate(new Animation(6705));
			p.getSkills().addExperience(Skills.FISHING, 110);
			p.getSkills().addExperience(Skills.STRENGTH, 11);
			p.getInventory().add(new Item(383));
			break;
		}
		return 0;
	}
	
	public static int checkFish(Player p) {
		if (p.getSkills().getLevel(Skills.FISHING) >= 55 && p.getSkills().getLevel(Skills.STRENGTH) >= 35) {
			if (p.getSkills().getLevel(Skills.FISHING) >= 70 && p.getSkills().getLevel(Skills.STRENGTH) >= 50) {
				if (p.getSkills().getLevel(Skills.FISHING) >= 96 && p.getSkills().getLevel(Skills.STRENGTH) >= 76) {
					return 3;
				}
				return 2;
			} 
			return 1;
		}
		return 0;
	}

	/**
	 * Checks if they have the barb tail harpoon.
	 * @return {@code True} if so.
	 */
	private boolean hasBarbTail() {
		if (option == FishingOption.HARPOON) {
			if (player.getInventory().containsItem(FishingOption.BARB_HARPOON.getTool()) || player.getEquipment().containsItem(FishingOption.BARB_HARPOON.getTool())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void message(int type) {
		switch (type) {
		case 0:
			player.getPacketDispatch().sendMessage(option.getStartMessage());
			break;
		case 2:
			player.getPacketDispatch().sendMessage(fish == Fish.ANCHOVIE || fish == Fish.SHRIMP ? "You catch some " + fish.getItem().getName().toLowerCase().replace("raw", "").trim() + "." : "You catch a " + fish.getItem().getName().toLowerCase().replace("raw", "").trim() + ".");
			if (player.getInventory().freeSlots() == 0) {
				player.getDialogueInterpreter().sendDialogue("You don't have enough space in your inventory.");
				stop();
			}
			break;
		}
	}

	/**
	 * Method used to check if the catch was a success.
	 * @return <code>True</code> if so.
	 */
	private boolean success() {
		if (getDelay() == 1) {
			return false;
		}
		int level = 1 + player.getSkills().getLevel(Skills.FISHING) + player.getFamiliarManager().getBoost(Skills.FISHING);
		double hostRatio = Math.random() * fish.getLevel();
		double clientRatio = Math.random() * ((level * 1.25 - fish.getLevel()));
		return hostRatio < clientRatio;
	}
}