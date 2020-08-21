package plugin.quest.members.thetouristrap;

import java.util.List;

import plugin.dialogue.DialoguePlugin;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.plugin.PluginManager;
import core.tools.RandomFunction;

/**
 * Represents the mining desert camp guard dialogue.
 * @author 'Vexia
 * @version 1.0
 */
public final class DesertGuardDialogue extends DialoguePlugin {

	/**
	 * Represents the quest.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code DesertGuardDialogue} {@code Object}.
	 */
	public DesertGuardDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DesertGuardDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DesertGuardDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DesertGuardDialogue(player);
	}

	@Override
	public void init() {
		super.init();
		PluginManager.definePlugin(new DesertGuardNPC());
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		switch (npc.getId()) {
		case 5001:
			switch (quest.getStage(player)) {
			default:
				npc("What are you looking at?");
				break;
			case 40:
				npc("Yeah, what do you want?");
				break;
			case 50:
			case 51:
			case 52:
			case 53:
			case 54:
				if (player.getInventory().containsItem(TouristTrap.TENTI_PINEAPPLE)) {
					player("Hey... I have something for you!");
					stage = 0;
				} else {
					npc("Do you have my tenti pineapple yet?");
					stage = 10;
				}
				break;
			case 60:
			case 70:
			case 80:
			case 90:
			case 100:
				if (stage == 60) {
					end();
					return true;
				}
				npc("That pineapple was just delicious, many thanks. I don't", "suppose you could get me another? <col=08088A>-- The guard looks at", "<col=08088A>you pleadingly.");
				break;
			}
			break;
		default:
			switch (quest.getStage(player)) {
			default:
				npc("What are you looking at?");
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (npc.getId()) {
		case 5001:
			switch (quest.getStage(player)) {
			case 51:
			case 52:
			case 53:
			case 54:
				switch (stage) {
				case 0:
					interpreter.sendItemMessage(TouristTrap.TENTI_PINEAPPLE, "You show the Tenti pineapple to the guard.");
					stage++;
					break;
				case 1:
					if (player.getInventory().remove(TouristTrap.TENTI_PINEAPPLE)) {
						quest.setStage(player, 60);
						npc("Great! Just what I've been looking for! Mmmmmmm...", "Delicious! This is so nice! Mmmmm, *SLURP*", "Yummmm... Oh yes, this is great.");
						stage = 10;
					}
					break;
				case -10:
					player("Sorry, I don't have it yet.");
					stage = -11;
					break;
				case -11:
					end();
					break;
				case 10:
					player("So, can I go through now please?");
					stage++;
					break;
				case 11:
					npc("Yes, yes, of course... a deal's a deal!");
					stage++;
					break;
				case 12:
					end();
					break;
				}
				break;
			case 50:
				switch (stage) {
				case 0:
					interpreter.sendItemMessage(TouristTrap.TENTI_PINEAPPLE, "You show the Tenti pineapple to the guard.");
					stage++;
					break;
				case 1:
					if (player.getInventory().remove(TouristTrap.TENTI_PINEAPPLE)) {
						quest.setStage(player, 60);
						npc("Great! Just what I've been looking for! Mmmmmmm...", "Delicious!! This is so nice! Mmmmm, *SLURP*", "Yummmm... Oh yes, this is great.");
						stage = 10;
					}
					break;
				case 10:
					player("Sorry, I don't have it yet.");
					stage++;
					break;
				case 11:
					end();
					break;
				}
				break;
			case 40:
				switch (stage) {
				case 0:
					player("I'd like to mine in a different area.");
					stage++;
					break;
				case 1:
					npc("Oh, you want to work in another area of the mine eh?", "<col=08088A>-- The guard seems pleased with his rhetorical question.--", "Well, I can understand that! A change is as good as a", "rest they say.");
					stage++;
					break;
				case 2:
					player("Yes sir, you're quite right sir.");
					stage++;
					break;
				case 3:
					npc("Of course I'm right. And what goes around comes", "around as they say. And it's been absolutely ages since", "I've had anything different to eat.");
					stage++;
					break;
				case 4:
					npc("What I wouldn't give for some whole, fresh, ripe and", "juicy pineapple for a change. And those Tenti's have the", "best pineapple in this entire area.");
					stage++;
					break;
				case 5:
					interpreter.sendDialogue("The guard winks at you.");
					stage++;
					break;
				case 6:
					npc("I'm sure you get my meaning...");
					stage++;
					break;
				case 7:
					player("Yes sir, we understand each other perfectly.");
					stage++;
					break;
				case 8:
					npc("Okay, good the. And remember, I prefer my", "pineapples whole, not chopped up with all the juice gone.");
					stage++;
					break;
				case 9:
					interpreter.sendDialogue("The guard moves back to his post and winks at you knowingly.");
					stage++;
					break;
				case 10:
					quest.setStage(player, 50);
					end();
					break;
				}
				break;
			case 100:
			case 60:
				switch (stage) {
				case 0:
					player("You must be joking! The last one I got you cost me", "double shifts working copper ore! You should be", "grateful you got one at all.");
					stage++;
					break;
				case 1:
					end();
					break;
				case 10:
					player("So, can I go through now please?");
					stage++;
					break;
				case 11:
					npc("Yes, yes, of course... a deal's a deal!");
					stage++;
					break;
				case 12:
					end();
					break;
				}
				break;
			default:
				end();
				break;
			}
			break;
		default:
			switch (quest.getStage(player)) {
			default:
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4993, 4994, 4995, 4996, 4997, 4998, 4999, 5000, 5001 };
	}

	/**
	 * The desert guard npc.
	 * @author 'Vexia
	 */
	public static final class DesertGuardNPC extends AbstractNPC {

		/**
		 * Represents the last check.
		 */
		private int lastCheck;

		/**
		 * Constructs a new {@code DesertGuardNPC} {@code Object}.
		 */
		public DesertGuardNPC() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code DesertGuardNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public DesertGuardNPC(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new DesertGuardNPC(id, location);
		}

		@Override
		public void tick() {
			if (lastCheck < GameWorld.getTicks()) {
				lastCheck = GameWorld.getTicks() + RandomFunction.random(50, 150);
				if (!inCombat()) {
					warn();
				}
			}
			super.tick();
		}

		/**
		 * Method used to warn players whom are breaking rules.
		 */
		private void warn() {
			final List<Player> players = RegionManager.getLocalPlayers(this);
			for (final Player player : players) {
				if (player.getAttribute("guard-warning", 0) > GameWorld.getTicks() || !player.getZoneMonitor().isInZone("mining camp") || player.inCombat() || !player.getLocation().withinDistance(this.getLocation(), 8)) {
					continue;
				}
				if (TouristTrap.inJail(player)) {
					continue;
				}
				if (!TouristTrap.hasSlaveClothes(player)) {
					player.setAttribute("guard-warning", GameWorld.getTicks() + 300);
					player.lock();
					GameWorld.Pulser.submit(new Pulse(1, this, player) {
						int count;

						@Override
						public boolean pulse() {
							switch (++count) {
							case 1:
								face(player);
								if (!TouristTrap.hasSlaveClothes(player)) {
									sendChat("Hey, they're interesting clothes!");
									player.getPacketDispatch().sendMessage("Guard: You're no slave.");
								} else {
									sendChat("Hey - you with the armour!");
									player.getPacketDispatch().sendMessage("Guard: Hey - You with the armour!");
								}
								break;
							case 5:
								if (!TouristTrap.hasSlaveClothes(player)) {
									sendChat("You're no slave.");
									player.getPacketDispatch().sendMessage("Guard: What are you doing in here?");
								} else {
									sendChat("You're not allowed in here!");
									player.getPacketDispatch().sendMessage("Guard: You're not allowed in here!");
								}
								break;
							case 8:
								sendChat("Intel the cell you go! I hope this teaches you a lesson.");
								getProperties().getCombatPulse().attack(player);
								break;
							case 10:
								if (!TouristTrap.inJail(player)) {
									TouristTrap.jail(player);
								}
								break;
							}
							return false;
						}
					});
					break;
				}
			}
		}

		@Override
		public int[] getIds() {
			return new int[] { 4993, 4994, 4995, 4996, 4997, 4998, 4999, 5000, 5001, 5002 };
		}

	}
}
