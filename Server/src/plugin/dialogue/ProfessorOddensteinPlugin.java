package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Represents the plugin dialogue to handle the interaction with professor
 * oddenstein.
 * @author 'Vexia
 */
@InitializablePlugin
public class ProfessorOddensteinPlugin extends DialoguePlugin {

	/**
	 * Represents the oil can item.
	 */
	private static final Item OIL_CAN = new Item(277);

	/**
	 * Represents the pressure guage item.
	 */
	private static final Item PRESSURE_GAUGE = new Item(271);

	/**
	 * Represents the rubber tube item.
	 */
	private static final Item RUBBER_TUBE = new Item(276);

	/**
	 * Represents the animation for professor to use.
	 */
	private static final Animation ANIMATION = new Animation(832);

	/**
	 * Represents the face location.
	 */
	private static final Location FACE_LOCATION = Location.create(3112, 3367, 2);

	/**
	 * The start graphic for spell.
	 */
	private static final Graphics WEAKEN_START = new Graphics(105, 96);

	/**
	 * The end graphic for the spell.
	 */
	private static final Graphics WEAKEN_END = new Graphics(107, 96);

	/**
	 * Constructs a new {@code ProfessorOddensteinPlugin} {@code Object}.
	 */
	public ProfessorOddensteinPlugin() {

	}

	/**
	 * Constructs a new {@code ProfessorOddensteinPlugin} {@code Object}.
	 * @param player the player.
	 */
	public ProfessorOddensteinPlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ProfessorOddensteinPlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Be careful in here, there's lots of dangerous equipment.");
			stage = 0;
			break;
		case 20:
			interpreter.sendDialogues(npc, null, "Have you found everything yet?");
			stage = 0;
			break;
		case 100:
			interpreter.sendOptions("Select an Option", "What does this machine do?", "Is this your house?");
			stage = 1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Ernest the Chicken");
		switch (quest.getStage(player)) {
		case 0:
		case 100:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "What does this machine do?", "Is this your house?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What does this machine do?");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Is this your house?");
					stage = 20;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What does this machine do?");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Nothing at the moment... It's broken. It's meant to be", "a transmutation machine.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "It as also spent time as a time travel machine, and a", "dramatic light generator, and a thing for generating", "monsters.");
				stage = 13;
				break;
			case 13:
				end();
				break;
			case 20:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "No, I'm just one of the tenants. It belongs to the count", "who lives in the basement.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "I'm looking for a guy called Ernest.", "What does this machine do?", "Is this your house?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "I'm looking for a guy called Ernest.");
					stage = 2;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What does this machine do?");
					stage = 10;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Is this your house?");
					stage = 20;
					break;
				}
				break;
			case 2:
				interpreter.sendDialogues(npc, null, "Ah Ernest, top notch bloke. He's helping me with my", "experiments.");
				stage = 3;
				break;
			case 3:
				interpreter.sendDialogues(player, null, "So you know where he is then?");
				stage = 4;
				break;
			case 4:
				interpreter.sendDialogues(npc, null, "He's that chicken over there.");
				stage = 5;
				break;
			case 5:
				interpreter.sendDialogues(player, null, "Ernest is a chicken..? Are you sure..?");
				stage = 6;
				break;
			case 6:
				interpreter.sendDialogues(npc, null, "Oh, he isn't normally a chicken, or at least he wasn't", "until he helped me test my pouletmorph machine.");
				stage = 7;
				break;
			case 7:
				interpreter.sendDialogues(npc, null, "It was originally going to be called a transmutation", "machine. But after testing pouletmorph seems more", "appropriate.");
				stage = 8;
				break;
			case 8:
				interpreter.sendDialogues(player, null, "I'm glad Veronica didn't actually get engaged to a", "chicken.");
				stage = 9;
				break;
			case 9:
				interpreter.sendDialogues(npc, null, "Who's Veronica?");
				stage = 10;
				break;
			case 10:
				interpreter.sendDialogues(player, null, "Ernest's fiancee. She probably doesn't want to marry a", "chicken.");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(npc, null, "Ooh I dunno. She could have free eggs for breakfast.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(player, null, "I think you'd better change him back.");
				stage = 13;
				break;
			case 13:
				interpreter.sendDialogues(npc, null, "Umm... It's not so easy...");
				stage = 14;
				break;
			case 14:
				interpreter.sendDialogues(npc, null, "My machine is broken, and the house germlins have", "run off with some vital bits.");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogues(player, null, "Well I can look for them.");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(npc, null, "That would be a help. They'll be somewhere in the", "manor house or its grounds, the gremlins never get", "further than the entrance gate.");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogues(npc, null, "I'm missing the pressure guage and a rubber tube.", "They've also taken my oil can, which I'm going to need", "to get this thing started again.");
				stage = 18;
				break;
			case 18:
				quest.setStage(player, 20);
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(OIL_CAN) && player.getInventory().containsItem(PRESSURE_GAUGE) && player.getInventory().containsItem(RUBBER_TUBE)) {
					interpreter.sendDialogues(player, null, "I have everything!");
					stage = 3;
				} else {
					interpreter.sendDialogues(player, null, "I'm afraid I'm still looking for them!");
					stage = 1;
				}
				break;
			case 1:
				interpreter.sendDialogues(npc, null, "I need a rubber tube, a pressure gauge and a can of", "oil. Then your friend can stop being a chicken.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			case 3:
				interpreter.sendDialogues(npc, null, "Give 'em here then.");
				stage = 4;
				break;
			case 4:
				end();
				player.lock();
				player.getPacketDispatch().sendMessage("You give a rubber tube, a pressure gauge,");
				player.getPacketDispatch().sendMessage("and a can of oil to the professor.");
				player.getPacketDispatch().sendMessage("Oddenstein starts up the machine.");
				final NPC chicken = Repository.findNPC(288);
				GameWorld.submit(new Pulse(1, player) {
					int counter = 0;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 1:
							npc.animate(ANIMATION);
							npc.faceLocation(FACE_LOCATION);
							break;
						case 2:
							player.getPacketDispatch().sendMessage("The machine hums and shakes.");
							npc.graphics(WEAKEN_START);
							Projectile.create(npc, chicken, 106, 40, 36, 52, 75, 15, 11);
							break;
						case 4:
							NPC ernest = NPC.create(287, chicken.getLocation());
							ernest.setAttribute("target", player);
							ernest.init();
							player.setAttribute("ernest-hide", true);
							if (player.getInventory().remove(OIL_CAN, PRESSURE_GAUGE, RUBBER_TUBE)) {
								player.getDialogueInterpreter().open(287, ernest);
							}
							break;
						case 5:
							npc.graphics(WEAKEN_END);
							return true;
						}
						return false;
					}
				});
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 286 };
	}
}
