package plugin.quest.free.demonslayer;

import core.game.component.Component;
import plugin.activity.ActivityManager;
import plugin.activity.CutscenePlugin;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Direction;
import core.game.world.update.flag.context.Animation;
import core.net.packet.PacketRepository;
import core.net.packet.context.CameraContext;
import core.net.packet.context.CameraContext.CameraType;
import core.net.packet.out.CameraViewPacket;

/**
 * Represents the dialogue which handles the transcript for the gypsy aris.
 * @author 'Vexia
 * @version 1.0
 */
public final class GypsyArisDialogue extends DialoguePlugin {

	/**
	 * Represents the coins to remove.
	 */
	private static final Item COINS = new Item(995, 1);

	/**
	 * Represents the animation used to look into a crystal ball.
	 */
	private static final Animation ANIMATION = new Animation(4615);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Represents the wally npc.
	 */
	private NPC wally;

	/**
	 * Represents the cutscene plugin instance.
	 */
	private CutscenePlugin cutscene;

	/**
	 * Constructs a new {@code GypsyArisDialogue} {@code Object}.
	 */
	public GypsyArisDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GypsyArisDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GypsyArisDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GypsyArisDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Demon Slayer");
		switch (quest.getStage(player)) {
		case 100:
			npc("Greetings young one.");
			stage = 0;
			break;
		case 30:
		case 10:
		case 20:
			if (args.length > 1) {
				npc("Delrith will come forth from the stone circle again.");
				stage = 200;
				return true;
			}
			npc("Greetings. How goes the quest?");
			if (quest.getStage(player) != 30) {
				stage = 1;
			} else {
				stage = 0;
			}
			break;
		case 0:
			if (args.length > 1) {
				cutscene = ((CutscenePlugin) args[1]);
				npc("Wally managed to arrive at the stone circle just as", "Delrith was summoned by a cult of chaos druids...");
				stage = 200;
				return true;
			}
			npc("Hello young one.");
			stage = 1;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 100:
			switch (stage) {
			case 0:
				npc("You're a hero now. That was a good bit of", "demonslaying.");
				stage = 1;
				break;
			case 1:
				player("Thanks.");
				stage = 2;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 30:
			switch (stage) {
			case 0:
				player("I have the sword now. I just need to kill the demon, I", "think.");
				stage = 1;
				break;
			case 1:
				npc("Yep, that's right.");
				stage = 2;
				break;
			case 2:
				options("What is the magical incantation?", "Well I'd better press on with it", "Where can I find the demon?");
				stage = 3;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("What is the magical incantation?");
					stage = 10;
					break;
				case 2:
					player("Well I'd better press on with it.");
					stage = 20;
					break;
				case 3:
					player("Where can I find the demon?");
					stage = 30;
					break;
				}
				break;
			case 10:
				npc(DemonSlayer.getIncantation(player) + ".");
				stage = 11;
				break;
			case 11:
				player("Well I'd better press on with it.");
				stage = 20;
				break;
			case 20:
				npc("See you anon.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("Just head south and you should find the stone circle", "just outside the city gate.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			}
			break;
		case 20:
			switch (stage) {
			case 0:
				player("I found Sir Prysin. Unfortunately I haven't got the", "sword yet. He's made it complicated for me.");
				stage = 1;
				break;
			case 1:
				npc("Ok, hurry, we haven't much time.");
				stage = 2;
				break;
			case 2:
				options("What is the magical incantation?", "Well I'd better press on with it.");
				stage = 3;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("What is the magical incantation?");
					stage = 10;
					break;
				case 2:
					player("Well I'd better press on with it.");
					stage = 20;
					break;
				}
				break;
			case 10:
				npc(DemonSlayer.getIncantation(player) + ".");
				stage = 11;
				break;
			case 11:
				player("Well I'd better press on with it.");
				stage = 20;
				break;
			case 20:
				player("See you anon.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			}
			break;
		case 10:
			switch (stage) {
			case 1:
				player("I'm still working on it.");
				stage = 2;
				break;
			case 2:
				npc("Well if you need any advice I'm always here, young", "one.");
				stage = 3;
				break;
			case 3:
				options("What is the magical incantation?", "Where can I find Silverlight?", "Stop calling me that!", "Well I'd better press on with it.");
				stage = 4;
				break;
			case 4:
				switch (buttonId) {
				case 1:
					player("What is the magical incantation?");
					stage = 10;
					break;
				case 2:
					player("Where can I find Silverlight?");
					stage = 20;
					break;
				case 3:
					player("Stop calling me that!");
					stage = 30;
					break;
				case 4:
					player("Well I'd better press on with it.");
					stage = 40;
					break;
				}
				break;
			case 10:
				npc("Oh yes, let me think a second...");
				stage = 11;
				break;
			case 11:
				npc("Alright, I think I've got it now, it goes...");
				stage = 12;
				break;
			case 12:
				npc(DemonSlayer.getIncantation(player) + ".", "Have you got that?");
				stage = 13;
				break;
			case 13:
				player("I think so, yes.");
				stage = 14;
				break;
			case 14:
				end();
				break;
			case 20:
				npc("Silverlight has been passed down through Wally's", "descendants. I believe it is currently in the care of one", "of the King's knights called Sir Prysin.");
				stage = 21;
				break;
			case 21:
				npc("He shouldn't be too hard to find. He lives in the royal", "palace in this city. Tell him Gypsy Aris sent you.");
				stage = 22;
				break;
			case 22:
				end();
				break;
			case 30:
				npc("In the scheme of things you are very young.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				npc("See you anon.");
				stage = 41;
				break;
			case 41:
				end();
				break;
			case 200:
				npc("I would imagine an evil sorcerer is already starting on", "the rituals to summon Delrith as we speak.");
				stage = 201;
				break;
			case 201:
				options("How am I meant to fight a demon who can destroy cities?", "Okay, where is he? I'll kill him for you!", "What is the magical incantation?", "Where can I find Silverlight?");
				stage = 202;
				break;
			case 202:
				switch (buttonId) {
				case 1:
					player("How am I meant to fight a demon who can destroy ", "cities?!");
					stage = 110;
					break;
				case 2:
					player("Okay, where is he? I'll kill him for you!");
					stage = 120;
					break;
				case 3:
					player("What is the magical incantation?");
					stage = 10;
					break;
				case 4:
					player("Where can I find Silverlight?");
					stage = 20;
					break;
				}
				break;
			case 120:
				npc("Ah, the overconfidence of the young!");
				stage = 121;
				break;
			case 121:
				npc("Delrith can't be harmed by ordinary weapons. You", "must face him using the same weapon that Wally used.");
				stage = 201;
				break;
			case 110:
				npc("If you face Delrith while he is still weak from being", "summoned, and use the correct weapon, you will not", "find the task to arduous.");
				stage = 111;
				break;
			case 111:
				npc("Do not fear. If you follow the path of the great hero", "Wally, then you are sure to defeat the demon.");
				stage = 201;
				break;
			}
			break;
		case 0:
			switch (stage) {
			case 1:
				npc("Cross my palm with silver and the future will be", "revealed to you.");
				stage = 2;
				break;
			case 2:
				options("Ok, here you go.", "Who are you calling young one?!", "No, I don't believe in that stuff.", "With silver?");
				stage = 3;
				break;
			case 3:
				switch (buttonId) {
				case 1:
					player("Ok, here you go.");
					stage = 10;
					break;
				case 2:
					player("Who are you calling young one?!");
					stage = 20;
					break;
				case 3:
					player("No, I don't believe in that stuff.");
					stage = 30;
					break;
				case 4:
					player("With silver?");
					stage = 40;
					break;
				}
				break;
			case 10:
				if (!player.getInventory().containsItem(COINS)) {
					end();
					return true;
				}
				if (player.getInventory().remove(COINS)) {
					npc("Come closer, and listen carefully to what the future", "holds for you, as I peer int the swirling mists of the", "crystal ball.");
					npc.animate(ANIMATION);
					stage = 12;
				} else {
					player("Sorry, I don't seem to have enough coins.");
					stage = 11;
				}
				break;
			case 11:
				end();
				break;
			case 12:
				npc("I can see images forming. I can see you.");
				stage = 13;
				break;
			case 13:
				npc("You are holding a very impressive looking sword. I'm", "sure I recognize that sword...");
				stage = 14;
				break;
			case 14:
				npc("There is a big dark shadow appearing now.");
				stage = 15;
				break;
			case 15:
				npc("Aargh!");
				stage = 16;
				break;
			case 16:
				player("Are you all right?");
				stage = 17;
				break;
			case 17:
				npc("It's Delrith! Delrith is coming!");
				stage = 18;
				break;
			case 18:
				player("Who's Delrith?");
				stage = 50;
				break;
			case 50:
				npc("Delrith...");
				stage = 51;
				break;
			case 51:
				npc("Delrith is a powerful demon.");
				stage = 52;
				break;
			case 52:
				npc("Oh! I really hope he didn't see me looking at him", "through my crystal ball!");
				stage = 53;
				break;
			case 53:
				npc("He tried to destroy this city 150 years ago. He was", "stopped just in time by the great hero Wally.");
				stage = 54;
				break;
			case 54:
				npc("Using his magic sword Silverlight, Wally managed to", "trap the demon in the stone circle just south", "of this city.");
				stage = 55;
				break;
			case 55:
				npc("Ye gods! Silverlight was the sword you were holding in", "my vision! You are the one destined to stop the demon", "this time.");
				stage = 56;
				break;
			case 56:
				options("How am I meant to fight a demon who can destroy cities?", "Okay, where is he? I'll kill him for you!", "Wally doesn't sound like a very heroic name.");
				stage = 57;
				break;
			case 57:
				switch (buttonId) {
				case 1:
					player("How am I meant to fight a demon who can destroy ", "cities?!");
					stage = 110;
					break;
				case 2:
					player("Okay, where is he? I'll kill him for you!");
					stage = 120;
					break;
				case 3:
					player("Wally doesn't sound like a very heroic name.");
					stage = 130;
					break;
				}
				break;
			case 110:
				npc("If you face Delrith while he is still weak from being", "summoned, and use the correct weapon, you will not", "find the task to arduous.");
				stage = 111;
				break;
			case 111:
				npc("Do not fear. If you follow the path of the great hero", "Wally, then you are sure to defeat the demon.");
				stage = 112;
				break;
			case 112:
				options("Okay, where is he? I'll kill him for you!", "Wally doesn't sound like a very heroic name.", "So how did Wally kill Delrith?");
				stage = 113;
				break;
			case 113:
				switch (buttonId) {
				case 1:
					player("Okay, where is he? I'll kill him for you!");
					stage = 120;
					break;
				case 2:
					player("Wally doesn't sound like a very heroic name.");
					stage = 130;
					break;
				case 3:
					player("So how did Wally kill Delrith?");
					stage = 180;
					break;
				}
				break;
			case 120:
				npc("Ah, the overconfidence of the young!");
				stage = 121;
				break;
			case 121:
				npc("Delrith can't be harmed by ordinary weapons. You", "must face him using the same weapon that Wally used.");
				stage = 122;
				break;
			case 122:
				options("How am I meant to fight a demon who can destroy cities?", "Wally doesn't sound like a very heroic name.", "So how did wally kill Delrith?");
				stage = 123;
				break;
			case 123:
				switch (buttonId) {
				case 1:
					player("How am I meant to fight a demon who can destroy ", "cities?!");
					stage = 110;
					break;
				case 2:
					player("Wally doesn't sound like a very heroic name.");
					stage = 130;
					break;
				case 3:
					player("So how did Wally kill Delrith?");
					stage = 180;
					break;
				}
				break;
			case 130:
				npc("Yes I know. Maybe this is why history doesn't", "remember him. However he was a very great hero.");
				stage = 131;
				break;
			case 131:
				npc("Who knows how much pain and suffering Delrith would", "have brought forth without Wally to stop him!");
				stage = 132;
				break;
			case 132:
				npc("It looks like you are going to need to perform similar", "heroics.");
				stage = 133;
				break;
			case 133:
				options("How am I meant to fight a demon who can destroy cities?", "Okay, where is he? I'll kill him for you!", "So how did Wally kill Delrith?");
				stage = 134;
				break;
			case 134:
				switch (buttonId) {
				case 1:
					player("How am I meant to fight a demon who can destroy ", "cities?!");
					stage = 110;
					break;
				case 2:
					player("Okay, where is he? I'll kill him for you!");
					stage = 120;
					break;
				case 3:
					player("So how did Wally kill Delrith?");
					stage = 180;
					break;
				}
				break;
			case 180:
				ActivityManager.start(player, "Wally cutscene", false);
				end();
				break;
			case 20:
				npc("You have been on this world a relatively short time. At", "least compared to me.");
				stage = 21;
				break;
			case 21:
				end();
				break;
			case 30:
				npc("Ok suit yourself.");
				stage = 31;
				break;
			case 31:
				end();
				break;
			case 40:
				npc("Oh, sorry, I forgot. With gold, I mean. They haven't", "used silver coins since before you were born! So, do", "you want your fortune told?");
				stage = 41;
				break;
			case 41:
				options("Ok, here you go.", "No, I don't believe in that stuff.");
				stage = 42;
				break;
			case 42:
				switch (buttonId) {
				case 1:
					player("Ok, here you go.");
					stage = 10;
					break;
				case 2:
					player("No, I don't believe in that stuff.");
					stage = 30;
					break;
				}
				break;
			case 200:// Wally cutscene dialogue.
				wally = NPC.create(4664, cutscene.getBase().transform(31, 40, 0));
				wally.setDirection(Direction.WEST);
				cutscene.getNPCS().add(wally);
				wally.init();
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX() + 2, player.getLocation().getY() + 2, 260, 1, 10));
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 190, player.getLocation().getY() + 14, 260, 1, 10));
				interpreter.sendDialogues(wally, FacialExpression.FURIOUS, "Die, foul demon!");
				GameWorld.Pulser.submit(new Pulse(5) {
					@Override
					public boolean pulse() {
						wally.animate(new Animation(4603));
						return true;
					}
				});
				stage = 201;
				break;
			case 201:
				interpreter.sendDialogues(wally, null, "Now, what was that incantation again?");
				stage = 202;
				break;
			case 202:
				interpreter.sendDialogues(wally, null, DemonSlayer.generateIncantation(player) + "!");
				stage = 203;
				break;
			case 203:
				close();
				player.getInterfaceManager().openOverlay(new Component(115));
				GameWorld.Pulser.submit(new Pulse(1) {
					int counter = 0;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 1:
							player.getProperties().setTeleportLocation(cutscene.getBase().transform(25, 36, 0));
							break;
						case 3:
							wally.setDirection(Direction.SOUTH_WEST);
							wally.getProperties().setTeleportLocation(cutscene.getBase().transform(28, 40, 0));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, player.getLocation().getX(), player.getLocation().getY(), 440, 1, 100));
							PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, player.getLocation().getX() + 1, player.getLocation().getY() + 1, 440, 1, 100));
						case 4:
							player.getInterfaceManager().closeOverlay();
							player.getInterfaceManager().close();
							wally.animate(new Animation(4604));
							interpreter.sendDialogues(wally, null, "I am the greatest demon slayer EVER!");
							stage = 204;
							break;
						case 5:
							wally.animate(new Animation(4604));
							return true;
						}
						return false;
					}
				});
				break;
			case 204:
				npc("By reciting the correct magical incantation, and", "thrusting Silverlight into Delrith while he was newly", "summoned, Wally was able to imprison Delrith in the", "stone block in the centre of the circle.");
				stage = 205;
				break;
			case 205:
				cutscene.stop(true);
				close();
				break;
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 882 };
	}
}