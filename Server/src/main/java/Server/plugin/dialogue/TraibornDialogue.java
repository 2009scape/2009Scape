package plugin.dialogue;

import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.game.world.update.flag.context.Animation;

import plugin.quest.free.demonslayer.DemonSlayer;

/**
 * Represents the dialogue used to handle the Traiborn NPC.
 * @author 'Vexia
 * @date 3/1/14
 */
public class TraibornDialogue extends DialoguePlugin {

	/**
	 * Represents the bones item.
	 */
	private static final Item BONES = new Item(526, 25);

	/**
	 * Represents the object animation.
	 */
	@SuppressWarnings("unused")
	private static final Animation OBJECT_ANIM = new Animation(4599);

	/**
	 * Represents the animation of giving the key.
	 */
	private static final Animation ANIMATION = new Animation(536);

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Constructs a new {@code TraibornDialogue} {@code Object}.
	 */
	public TraibornDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code TraibornDialogue} {@code Object}.
	 * @param player the player.
	 */
	public TraibornDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new TraibornDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		quest = player.getQuestRepository().getQuest("Demon Slayer");
		switch (quest.getStage(player)) {
		case 20:
			if (player.getAttribute("demon-slayer:traiborn", false)) {
				npc("How are you doing finding bones?");
				stage = 380;
			} else {
				interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ello young thingummywut.");
				stage = 0;
			}
			break;
		default:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Ello young thingummywut.");
			stage = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (quest.getStage(player)) {
		case 20:
			switch (stage) {
			case 0:
				if (player.getInventory().containsItem(DemonSlayer.THIRD_KEY) && player.getBank().containsItem(DemonSlayer.THIRD_KEY)) {
					options("What's a thingummywut?", "Teach me to be a mighty powerful wizard.");
					stage = 1;
				} else {
					options("What's a thingummywut?", "Teach me to be a mighty powerful wizard.", "I need to get a key given to you by Sir Prysin.");
					stage = 1;
				}
				break;
			case 1:
				switch (buttonId) {
				case 1:
				case 2:
					handleDefault(buttonId);
					break;
				case 3:
					player("I need to get a key given to you by Sir Prysin.");
					stage = 300;
					break;
				}
				break;
			case 300:
				npc("Sir Prysin? Who's that? What would I want his key", "for?");
				stage = 301;
				break;
			case 301:
				player("Well, have you got any keys knocking around?");
				stage = 302;
				break;
			case 302:
				npc("Now you come to mention it, yes I do have a key. It's", "in my special closet of valuable stuff. Now how do I get", "into that?");
				stage = 303;
				break;
			case 303:
				npc("I sealed it using one of my magic rituals. So it would", "makes sense that another ritual would open it again.");
				stage = 304;
				break;
			case 304:
				player("So do you know what ritual to use?");
				stage = 305;
				break;
			case 305:
				npc("Let me think a second.");
				stage = 306;
				break;
			case 306:
				npc("Yes a simple drazier style ritual should suffice. Hmm,", "main problem with that is I'll need 25 sets of bones.", "Now where am I going to get hold of something like", "that?");
				stage = 307;
				break;
			case 307:
				options("Hmm, that's too bad. I really need that key.", "I'll get the bones for you.");
				stage = 308;
				break;
			case 308:
				switch (buttonId) {
				case 1:
					player("Hmm, that's too bad. I really need that key.");
					stage = 330;
					break;
				case 2:
					player("I'll get the bones for you.");
					stage = 340;
					break;
				}
				break;
			case 330:
				npc("Ah well, sorry I couldn't be any more help.");
				stage = 331;
				break;
			case 331:
				end();
				break;
			case 340:
				npc("Ooh that would be very good of you.");
				stage = 341;
				break;
			case 341:
				player("Ok I'll speak to you when I've got some bones.");
				stage = 342;
				break;
			case 342:
				player.setAttribute("/save:demon-slayer:traiborn", true);
				end();
				break;
			case 380:
				if (player.getInventory().containsItem(BONES)) {
					player("I have some bones.");
					stage = 382;
				} else {
					player("I don't have all the bones yet.");
					stage = 381;
				}
				break;
			case 382:
				npc("Give 'em here then.");
				stage = 383;
				break;
			case 383:
				interpreter.sendDialogue("You give Traiborn 25 sets of bones.");
				stage = 384;
				break;
			case 384:
				npc("Hurrah! That's all 25 sets of bones.");
				stage = 385;
				break;
			case 385:
				npc.animate(new Animation(4602));
				npc("Wings of dark and colour too,", "Spreading in the morning dew;", "Locked away I have a key;", "Return it now, please, unto me.");
				stage = 386;
				break;
			case 386:
				final GameObject object = new GameObject(17434, Location.create(3113, 3161, 1), 11, 1);
				ObjectBuilder.add(object);
				npc.faceLocation(object.getLocation());
				npc.animate(ANIMATION);
				if (!player.getInventory().containsItem(BONES)) {
					end();
					return true;
				}
				if (player.getInventory().remove(BONES)) {
					player.removeAttribute("demon-slayer:traiborn");
					player.getInventory().add(DemonSlayer.THIRD_KEY);
					interpreter.sendItemMessage(DemonSlayer.THIRD_KEY.getId(), "Traiborn hands you a key.");
					stage = 387;
				}
				GameWorld.Pulser.submit(new Pulse(1) {
					int counter = 0;

					@Override
					public boolean pulse() {
						switch (counter++) {
						case 5:
							npc.face(player);
							break;
						case 7:
							ObjectBuilder.remove(object);
							return true;
						}
						return false;
					}
				});
				break;
			case 387:
				player("Thank you very much.");
				stage = 388;
				break;
			case 388:
				npc("Not a problem for a friend of Sir What's-his-face.");
				stage = 389;
				break;
			case 389:
				end();
				break;
			case 381:
				end();
				break;
			}
			break;
		case 0:
			switch (stage) {
			default:
				handleDefault(buttonId);
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Method used to handle the default button id.
	 * @param buttonId the buttton id.
	 */
	private final void handleDefault(int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "What's a thingummywut?", "Teach me to be a mighty and powerful wizard.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "What's a thingumywut?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Teach  me to be a mighty and powerful wizard.");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "A thingummywut? Where? Where?");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Those pesky thingummywuts. They get everywhere.", "They leave a terrible mess too.");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "Err you just called me a thingymummywut.", "Tell me what they look like and I'll mask 'em.");
			stage = 13;
			break;
		case 13:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Err you just called me thingummywut.");
				stage = 100;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me what they look like and I'll mash 'em.");
				stage = 120;
				break;
			}
			break;
		case 120:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Don't be ridiculous. No-one has ever seen one.");
			stage = 121;
			break;
		case 121:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "They're invisible, or a myth, or a figment of my", "imagination. Can't remember which right now.");
			stage = 122;
			break;
		case 122:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "You're a thingummywut? I've never seen one up close", "before. They said I was mad!");
			stage = 101;
			break;
		case 101:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Now you are my proof! There ARE thingummywuts in", "this tower. Now where can I find a cage big enough to", "keep you?");
			stage = 102;
			break;
		case 102:
			interpreter.sendOptions("Select an Option", "Err I'd better be off really.", "They're right, you are mad.");
			stage = 103;
			break;
		case 103:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Err I'd better be off really.");
				stage = 110;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "They're right, you are mad.");
				stage = 130;
				break;
			}
			break;
		case 130:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That's a pity. I thought maybe they were winding me", "up.");
			stage = 131;
			break;
		case 131:
			end();
			break;
		case 110:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh ok, have a good time, and watch out for sheep!", "They're more cunning than they look.");
			stage = 111;
			break;
		case 111:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Wizard eh? You don't want any truck with that sort.", "They're not to be trusted. That's what I've heard", "anyways.");
			stage = 21;
			break;
		case 21:
			interpreter.sendOptions("Select an Option", "So aren't you a wizard?", "Oh I'd better stop talking to you then.");
			stage = 22;
			break;
		case 22:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "So aren't you a wizard?");
				stage = 40;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh I'd better stop talking to you then.");
				stage = 60;
				break;
			}
			break;
		case 60:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Cheerio then. It was nice chatting to you.");
			stage = 61;
			break;
		case 61:
			end();
			break;
		case 40:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How dare you? Of course I'm a wizard. Now don't be", "so cheeky or I'll turn you into a frog.");
			stage = 41;
			break;
		case 41:
			end();
			break;
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 881 };
	}
}