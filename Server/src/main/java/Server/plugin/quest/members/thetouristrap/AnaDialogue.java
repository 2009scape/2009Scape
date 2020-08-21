package plugin.quest.members.thetouristrap;

import plugin.dialogue.DialoguePlugin;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.map.RegionManager;
import core.plugin.Plugin;
import core.plugin.PluginManager;

/**
 * The dialogue plugin used to handle the ana npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class AnaDialogue extends DialoguePlugin {

	/**
	 * The quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code AnaDialogue} {@code Object}.
	 */
	public AnaDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AnaDialogue} {@code Object}.
	 * @param player the player.
	 */
	public AnaDialogue(final Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AnaDialogue(player);
	}

	@Override
	public void init() {
		super.init();
		PluginManager.definePlugin(new AnaNPC());
		PluginManager.definePlugin(new AnaBarrelHandler());
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
		if ((quest.getStage(player) == 71 || quest.getStage(player) == 72) && args.length > 1) {
			player.getDialogueInterpreter().sendDialogue("You see a barrel coming to the surface. Before too long you haul it", "onto the side. The barrel seems quite heavy and you hear a muffled", "sound coming from inside.");
			stage = 400;
			return true;
		}
		switch (quest.getStage(player)) {
		case 98:
			switch (stage) {
			case 0:
				npc("Great! Thanks for getting me out of that mine! And", "that barrel wasn't too bad anyway! Pop by again", "sometime, I'm sure we'll have a barrel of laughs!");
				stage++;
				break;
			}
			break;
		case 60:
			player("Hello!");
			break;
		case 61:
			if (args.length > 1) {
				npc("Hey, what do you think you're doing?");
				stage = 20;
				return true;
			}
			player("Hello again!");
			break;
		default:
			player.getPacketDispatch().sendMessage("This slave does not appear interested in talking to you.");
			end();
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 98:
			switch (stage) {
			case 1:
				npc("Oh! I nearly forgot! Here's a key I found in the", "tunnels. It might be some use to you, not sure what", "it opens. Sorry, but I have to go now.");
				stage++;
				break;
			case 2:
				interpreter.sendItemMessage(TouristTrap.WROUGHT_IRON_KEY, "Ana gives you a wrought iron key...");
				stage++;
				break;
			case 3:
				player.getInventory().add(TouristTrap.WROUGHT_IRON_KEY, player);
				player.getPacketDispatch().sendMessage("Ana spots Irena and waves...");
				NPC irena = RegionManager.getNpc(player, 4986);
				irena.sendChat("Hi Ana!");
				quest.setStage(player, 98);
				end();
				player.getDialogueInterpreter().open(4986, irena);
				break;
			}
			break;
		case 71:
			switch (stage) {
			case 400:
				interpreter.sendDialogues(823, null, "Get me OUT OF HERE!");
				stage++;
				break;
			case 401:
				quest.setStage(player, 72);
				end();
				break;
			}
			break;
		case 61:
			switch (stage) {
			case 0:
				npc("Hello there, how's it going? Do you have a plan to get", "out of here yet?");
				stage++;
				break;
			case 1:
				player("Well, I'm working on it, have you got any suggestions?");
				stage++;
				break;
			case 2:
				npc("Hmmm.");
				stage++;
				break;
			case 3:
				npc("No, sorry...");
				stage++;
				break;
			case 4:
				npc("The only thing that gets out of here is the rock that we", "mine.");
				stage++;
				break;
			case 5:
				npc("Not even the dead get a decent funeral. Bodies are", "just thrown down disued mine holes. It's very", "disrespectful...");
				stage++;
				break;
			case 6:
				player("How does the rock get out?");
				stage++;
				break;
			case 7:
				npc("Well, we mine it in this section, then someone scoops it", "into a barrel. The barrels are loaded onto a mine cart.", "Then they're deposited near the surface lift.");
				stage++;
				break;
			case 8:
				npc("I have no idea where they go from there. But that's", "not going to help us, is it?");
				stage++;
				break;
			case 9:
				player("Where would I get one of those barrels from?");
				stage++;
				break;
			case 10:
				npc("Well, you would get one from around by the life area.", "But why would you want one of those?");
				stage++;
				break;
			case 11:
				player("I could try to sneak you out if you were in a barrel!");
				stage++;
				break;
			case 12:
				npc("There is no way you are getting me into a barrel. No", "WAY! Do you understand?");
				stage++;
				break;
			case 13:
				player("Well, we'll see, it might be the only way.");
				stage++;
				break;
			case 14:
				end();
				break;
			case 20:// barrel dialogue.
				if (player.getInventory().remove(TouristTrap.BARREL)) {
					player.sendChat("Shush... It's for your own good!");
					player.getInventory().add(TouristTrap.ANNA_BARREL);
					close();
					player.lock(3);
					GameWorld.Pulser.submit(new Pulse(3, player) {
						@Override
						public boolean pulse() {
							interpreter.sendDialogues(823, null, "<col=08088A>-- You manage to squeeze Ana into the barrel, --", "<col=08088A>-- despite her many complaints. --", "I djont fit in dis bawwel... Wet me out!!");
							stage++;
							return true;
						}
					});
				}
				break;
			case 21:
				end();
				break;
			}
			break;
		case 60:
			switch (stage) {
			case 0:
				npc("Hello there, I don't think I've seen you before.");
				stage++;
				break;
			case 1:
				player("What's your name?");
				stage++;
				break;
			case 2:
				npc("My name? Oh, how sweet, my name is Ana. I come from", "Al-Kharid though we've only recently moved there. I was", "born, and did most of my growing up, in Varrock. I", "thought the desert might be interesting. What a surprise");
				stage++;
				break;
			case 3:
				npc("I got!");
				stage++;
				break;
			case 4:
				player("Do you want to go back to Al-Kharid?");
				stage++;
				break;
			case 5:
				npc("Sure, I miss my Mum, her name is Irena and she is", "probably waiting for me. How do you propose we get out", "of here though?");
				stage++;
				break;
			case 6:
				npc("I'm sure you've noticed the many square jawed guards", "around here. You look like you can handle yourself", "but I have my doughts that you can take them all on!");
				stage++;
				break;
			case 7:
				quest.setStage(player, 61);
				end();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 822 };
	}

	/**
	 * The use with handler for the barrel on ana.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class AnaBarrelHandler extends UseWithHandler {

		/**
		 * Constructs a new {@code AnaBarrelHandler} {@code Object}.
		 */
		public AnaBarrelHandler() {
			super(TouristTrap.BARREL.getId());
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(822, NPC_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			final Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
			switch (quest.getStage(player)) {
			case 61:
				player.getDialogueInterpreter().open(822, event.getUsedWith(), true);
				break;
			default:
				return false;
			}
			return true;
		}

	}

	/**
	 * The ana npc.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public static final class AnaNPC extends AbstractNPC {

		/**
		 * Constructs a new {@code AnaNPC} {@code Object}.
		 */
		public AnaNPC() {
			super(0, null);
		}

		/**
		 * Constructs a new {@code AnaNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public AnaNPC(int id, Location location) {
			super(id, location);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new AnaNPC(id, location);
		}

		@Override
		public boolean isHidden(final Player player) {
			Quest quest = player.getQuestRepository().getQuest(TouristTrap.NAME);
			if (quest.getStage(player) > 61) {
				return true;
			}
			return player.getInventory().containsItem(TouristTrap.ANNA_BARREL) || player.getBank().containsItem(TouristTrap.ANNA_BARREL);
		}

		@Override
		public int[] getIds() {
			return new int[] { 822 };
		}

	}
}
