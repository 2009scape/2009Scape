package plugin.interaction.npc.sorceress_app;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.impl.Projectile;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.update.flag.context.Graphics;

/**
 * Dialogue for Sorceress Apprentice
 * @author SonicForce41
 */
public class SorceressApprenticeDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code SorceressApprenticeDialogue.java} {@code Object}.
	 */
	public SorceressApprenticeDialogue() {

	}

	/**
	 * Constructs a new {@code SorceressApprenticeDialogue.java} {@code Object}.
	 * @param player the Player
	 */
	public SorceressApprenticeDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5532 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Okay, here goes - and remember, to", "return just drink from the fountain.");
			stage = 1;
			break;
		case 1:
			teleport(npc, player);
			end();
			break;
		case 10:
			npc("Cleaning, cleaning, always cleaning. This apprenticeship", "isn't all that it was cracked up to be.");
			stage = 11;
			break;
		case 11:
			player("Whose apprentice are you?");
			stage = 12;
			break;
		case 12:
			npc("Oh, Aadeela, the sorceress upstairs, said she'd teach me", "magic, she did. And here I am scrubbing floors without", "a spell to help me.");
			stage = 13;
			break;
		case 13:
			interpreter.sendOptions("Select an Option", "I could cast a Water Blast or a Wind Blast spell?", "Surely there must be upsides to the task?");
			stage = 14;
			break;
		case 14:
			switch (buttonId) {
			case 1:
				player("I could cast a Water Blast or a Wind Blast spell to", "hurry things along if you'd like?");
				stage = 110;
				break;
			case 2:
				player("Surely there must be upsides to the task?");
				stage = 120;
				break;
			}
			break;
		case 110:
			npc("No, no, she'd kill me or worse if she knew I was using", "Magic to do chores. Her last apprentice - well I'd", "rather not say.");
			stage = 111;
			break;
		case 111:
			player("Oh go on, what happend to them?");
			stage = 112;
			break;
		case 112:
			npc("They say she turned them into little spiders.");
			stage = 113;
			break;
		case 113:
			player("Oh, that's too bad. I had better leave you to it.");
			stage = 114;
			break;
		case 114:
			end();
			break;
		case 120:
			npc("Nope. Clean this, clean that. When I'm finished cleaning", "here I have to go help out in the garden.");
			stage = 121;
			break;
		case 121:
			player("What garden?");
			stage = 122;
			break;
		case 122:
			npc("Oh, I shouldn't have told you.");
			stage = 123;
			break;
		case 123:
			interpreter.sendOptions("Select an Option", "You're right, you shouldn't have.", "Oh, you can talk to me. I can see you're having a bad day.");
			stage = 124;
			break;
		case 124:
			switch (buttonId) {
			case 1:
				player("You're right, you shouldn't have.");
				stage = 125;
				break;
			case 2:
				player("Oh, you can talk to me. I can see you're having a bad", "day.");
				stage = 126;
				break;
			}
			break;
		case 125:
			end();
			break;
		case 126:
			npc("You know you're right. Nobody listens to me.");
			stage = 127;
			break;
		case 127:
			player("A sympathetic ear can do wonders.");
			stage = 128;
			break;
		case 128:
			npc("Yes, if I just let my frustrations out, I'd feel a lot", "better. Now what was I saying?");
			stage = 129;
			break;
		case 129:
			player("You mentioned something about the garden.");
			stage = 130;
			break;
		case 130:
			npc("Oh yeah, the dreadful garden of hers.");
			stage = 131;
			break;
		case 131:
			player("Where is it?");
			stage = 132;
			break;
		case 132:
			npc("Oh, it's nowhere.");
			stage = 133;
			break;
		case 133:
			player("What do you mean?");
			stage = 134;
			break;
		case 134:
			npc("Well it's here, but not really. You see the sorceress is", "trying out some new type of compression magic.");
			stage = 135;
			break;
		case 135:
			player("Oh, that sounds interesting - so how does it work?");
			stage = 136;
			break;
		case 136:
			npc("It would take too long to explain and, to be honest, I", "don't really understand how it works.");
			stage = 137;
			break;
		case 137:
			player("Fair enough, but tell me, how do you get to the", "garden?");
			stage = 138;
			break;
		case 138:
			npc("By magic! The sorceress did teach me one spell.");
			stage = 139;
			break;
		case 139:
			interpreter.sendOptions("Select an Option", "Wow, cast the spell on me. It will be good Magic training for you.", "Oh, that's nice. Well it's been great talking to you.");
			stage = 140;
			break;
		case 140:
			switch (buttonId) {
			case 1:
				player("Wow, cast the spell on me. It will be good Magic", "training for you.");
				stage = 142;
				break;
			case 2:
				player("Oh, that's nice. Well it's been great", "talking to you.");
				stage = 141;
				break;
			}
			break;
		case 141:
			end();
			break;
		case 142:
			npc("You wouldn't mind?");
			stage = 143;
			break;
		case 143:
			player("Of course not. I'd be glad to help.");
			stage = 144;
			break;
		case 144:
			npc("Okay, here goes! Remember, to return, just drink from", "the fountain.");
			stage = 145;
			break;
		case 145:
			player.getSavedData().getGlobalData().setApprentice(true);
			teleport(npc, player);
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SorceressApprenticeDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if (player.getSavedData().getGlobalData().hasSpokenToApprentice()) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hey apprentice, do you want to try out", "your teleport skills again?");
			stage = 0;
		} else {
			player("Hello. What are you doing?");
			stage = 10;
		}
		return true;
	}

	public static void teleport(final NPC npc, final Player player) {
		npc.faceTemporary(player, 4);
		npc.graphics(new Graphics(108));
		player.lock();
		Projectile.create(npc, player, 109).send();
		npc.sendChat("Senventior Disthinte Molesko!");
		GameWorld.submit(new Pulse(1) {
			int counter = 0;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 2:
					player.getProperties().setTeleportLocation(Location.create(2912, 5474, 0));
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

}
