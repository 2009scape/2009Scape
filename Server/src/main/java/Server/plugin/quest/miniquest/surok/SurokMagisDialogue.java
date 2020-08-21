package plugin.quest.miniquest.surok;

import plugin.activity.ActivityPlugin;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.game.world.map.build.DynamicRegion;

/**
 * Handles the surok magis dialogue.
 * @author Vexia
 */
public class SurokMagisDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@Code SurokMagisDialogue} {@Code Object}
	 * @param player the player.
	 */
	public SurokMagisDialogue(Player player) {
		super(player);
	}

	/**
	 * Constructs a new {@Code SurokMagisDialogue} {@Code Object}
	 */
	public SurokMagisDialogue() {
		/**
		 * empty.
		 */
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SurokMagisDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player(player.getUsername() + "! The meddling adventurer.");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			player("Surok! What are you doing here?", "How did you-");
			stage++;
			break;
		case 1:
			npc("Escape from Varrock Palace Library? That curel", "imprisonment you left me in?");
			stage++;
			break;
		case 2:
			player("Well...er..yes.");
			stage++;
			break;
		case 3:
			npc("Bah! A mere trifile for a powerful mage such as myself.", "There were plenty of other foolish people to help with", "my plans, you would do well to stay out of my way.");
			stage++;
			break;
		case 4:
			player("Stop, Surok! As a member of the Varrock Palace Secret", "Guard, I arrest you! Again!");
			stage++;
			break;
		case 5:
			npc("Ha! I tire of this meaningless drivel. Catch me if you can.");
			stage++;
			break;
		case 6:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7002, 7136 };
	}

	/**
	 * Handles a surok cutscene.
	 * @author Vexia
	 */
	public static class SurokCutscene extends CutscenePlugin {

		/**
		 * The surok scene.
		 */
		private SurokScene scene;

		/**
		 * Constructs a new {@Code SurokCutscene} {@Code Object}
		 */
		public SurokCutscene() {
			super("Surok Cutscene");
		}

		/**
		 * Constructs a new {@Code SurokCutscene} {@Code Object}
		 * @param player the player.
		 */
		public SurokCutscene(Player player) {
			this();
			this.player = player;
		}

		@Override
		public boolean start(Player player, boolean login, Object... args) {
			scene = (SurokScene) args[0];
			region = DynamicRegion.create(scene.getRegionId());
			setRegionBase();
			registerRegion(region.getId());
			return super.start(player, login, args);
		}

		@Override
		public ActivityPlugin newInstance(Player p) throws Throwable {
			return new SurokCutscene(p);
		}

		@Override
		public Location getStartLocation() {
			return base.transform(scene.getStartData()[0], scene.getStartData()[1], 0);
		}

		@Override
		public Location getSpawnLocation() {
			return null;
		}

		@Override
		public void configure() {

		}

		/**
		 * Gets the scene.
		 * @return the scene
		 */
		public SurokScene getScene() {
			return scene;
		}

		/**
		 * Sets the bascene.
		 * @param scene the scene to set.
		 */
		public void setScene(SurokScene scene) {
			this.scene = scene;
		}

		/**
		 * The surok scene.
		 * @author Vexia
		 */
		public static enum SurokScene {
			ESCAPE(-1, new int[] {});

			/**
			 * The region id.
			 */
			private final int regionId;

			/**
			 * The start data.
			 */
			private final int[] startData;

			/**
			 * Constructs a new {@Code SurokScene} {@Code Object}
			 * @param regionId the region id.
			 * @param startData the start data.
			 */
			private SurokScene(int regionId, int[] startData) {
				this.regionId = regionId;
				this.startData = startData;
			}

			/**
			 * Gets the regionId.
			 * @return the regionId
			 */
			public int getRegionId() {
				return regionId;
			}

			/**
			 * Gets the startData.
			 * @return the startData
			 */
			public int[] getStartData() {
				return startData;
			}

		}

	}
}
