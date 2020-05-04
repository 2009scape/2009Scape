package org.crandor.game.content.global.travel;

import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.tools.RandomFunction;

/**
 * Represents a utilitity class for rune essence teleporting.
 * @author 'Vexia
 */
public final class EssenceTeleport {

	/**
	 * Array of all the possible {@code Location} locations.
	 */
	public static final Location LOCATIONS[] = { Location.create(2911, 4832, 0), Location.create(2913, 4837, 0), Location.create(2930, 4850, 0), Location.create(2894, 4811, 0), Location.create(2896, 4845, 0), Location.create(2922, 4820, 0), Location.create(2931, 4813, 0) };

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(437);

	/**
	 * Method used to teleport a player.
	 * @param npc the npc.
	 * @param player the player.
	 */
	public static void teleport(final NPC npc, final Player player) {
		npc.animate(ANIMATION);
		npc.faceTemporary(player, 1);
		npc.graphics(new Graphics(108));
		player.lock();
		player.getAudioManager().send(195);
		Projectile.create(npc, player, 109).send();
		npc.sendChat("Senventior Disthinte Molesko!");
		GameWorld.Pulser.submit(new Pulse(1) {
			int counter = 0;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 2:
					if (getStage(player) == 2 && player.getInventory().contains(5519, 1)) {
						Item item = player.getInventory().get(player.getInventory().getSlot(new Item(5519)));
						if (item != null) {
							if (item.getCharge() == 1000) {
								player.getSavedData().getGlobalData().resetAbyss();
							}
							Wizard wizard = Wizard.forNPC(npc.getId());
							if (!player.getSavedData().getGlobalData().hasAbyssCharge(wizard.ordinal())) {
								player.getSavedData().getGlobalData().setAbyssCharge(wizard.ordinal());
								item.setCharge(item.getCharge() + 1);
								if (item.getCharge() == 1003) {
									player.sendMessage("Your scrying orb has absorbed enough teleport information.");
									player.getInventory().remove(new Item(5519));
									player.getInventory().add(new Item(5518));
								}
							}
						}
					}
					player.getSavedData().getGlobalData().setEssenceTeleporter(npc.getId());
					player.getProperties().setTeleportLocation(getLocation());
					if (npc.getId() == 553) {
						if (!player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).isComplete(0, 1)) {
							player.getAchievementDiaryManager().getDiary(DiaryType.VARROCK).updateTask(player, 0, 1, true);
						}
					} else if (npc.getId() == 300) {
						if (!player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 1)) {
							player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).updateTask(player, 0, 1, true);
						}
					}
					break;
				case 3:
					player.graphics(new Graphics(110));
					player.unlock();
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * Method used to teleport back to the home.
	 * @param player the prayer.
	 */
	public static void home(final Player player) {
		final Wizard wizard = EssenceTeleport.Wizard.forNPC(player.getSavedData().getGlobalData().getEssenceTeleporter());
		Projectile.create(player, player, 345).send();
		player.getProperties().setTeleportLocation(wizard.getLocation());
	}

	/**
	 * Gets the stage.
	 * @return the stage.
	 */
	public static int getStage(Player player) {
		return player.getConfigManager().get(492);
	}

	/**
	 * Method used to get a random location.
	 * @return the location.
	 */
	public static Location getLocation() {
		int count = RandomFunction.random(LOCATIONS.length);
		return LOCATIONS[count];
	}

	/**
	 * Represents the wizard npc who can teleport.
	 * @author 'Vexia
	 */
	public static enum Wizard {
		AUBURY(553, 0x2, new Location(3253, 3401, 0)), SEDRIDOR(300, 0x4, new Location(3107, 9573, 0)), DISTENTOR(462, 0x8, new Location(2591, 3085, 0)), CROMPERTY(2328, 0x12, Location.create(2682, 3323, 0));

		/**
		 * Constructs a new {@code WizardTowerPlugin} {@code Object}.
		 * @param npc the npc.
		 * @param location the location.
		 */
		Wizard(final int npc, int mask, final Location location) {
			this.npc = npc;
			this.mask = mask;
			this.location = location;
		}

		/**
		 * Represents the npc of this wizard.
		 */
		private final int npc;

		/**
		 * The mask.
		 */
		private final int mask;

		/**
		 * Represents the returining location.
		 */
		public final Location location;

		/**
		 * Method used to get a wizard by the npc.
		 * @param npc the npc.
		 * @return the wizard.
		 */
		public static Wizard forNPC(final int npc) {
			for (Wizard wizard : Wizard.values()) {
				if (npc == 844) {
					return Wizard.CROMPERTY;
				}
				if (wizard.getNpc() == npc) {
					return wizard;
				}
			}
			return Wizard.AUBURY;
		}

		/**
		 * Gets the npc.
		 * @return The npc.
		 */
		public int getNpc() {
			return npc;
		}

		/**
		 * Gets the location.
		 * @return The location.
		 */
		public Location getLocation() {
			return location;
		}

		/**
		 * Gets the mask.
		 * @return The mask.
		 */
		public int getMask() {
			return mask;
		}
	}

}