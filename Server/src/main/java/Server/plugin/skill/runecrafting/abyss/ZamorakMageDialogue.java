package plugin.skill.runecrafting.abyss;

import plugin.dialogue.DialoguePlugin;
import plugin.quest.free.RuneMysteries;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;


/**
 * Handles the zamorak mages dialogue.
 * @author Vexia
 */
public final class ZamorakMageDialogue extends DialoguePlugin {

	/**
	 * The scrying orbs.
	 */
	private static final Item[] ORBS = new Item[] { new Item(5519), new Item(5518) };

	/**
	 * If its the varrock mage.
	 */
	private boolean varrockMage;

	/**
	 * Constructs a new {@code ZamorakMageDialogue} {@code Object}.
	 */
	public ZamorakMageDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ZamorakMageDialogue} {@code Object}.
	 * @param player the player.
	 */
	public ZamorakMageDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new ZamorakMageDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		varrockMage = npc.getId() == 2261 || npc.getId() == 2260;
		if (!player.getQuestRepository().isComplete(RuneMysteries.NAME)) {
			end();
			player.sendMessage("The mage doesn't seem interested in talking to you.");
			return true;
		}
		if (!varrockMage) {
			switch (getStage()) {
			case 0:
				npc("Meet me in Varrock's Chaos Temple.", "Here is not the place to talk.");
				break;
			case 1:
			case 2:
			case 3:
				npc("I already told you!", "meet me in the Varrock Chaos Temple!");
				break;
			case 4:
				npc("This is no place to talk!", "Meet me at the Varrock Chaos Temple!");
				break;
			}
		} else {
			switch (getStage()) {
			case 0:
				npc("I am in no mood to talk to you", "stranger!");
				break;
			case 1:
				npc("Ah, you again.", "What was it you wanted?", "The wilderness is hardly the appropriate place for a", "conversation now, is it?");
				break;
			case 2:
				npc("Well?", "Have you managed to use my scrying orb to obtain the", "information yet?");
				break;
			case 3:
				player("So... that's my end of the deal upheld.", "What do I get in return?");
				break;
			case 4:
				options("So what is this 'abyss' stuff?", "Is this abyss dangerous?", "Can you teleport me there now?");
				break;
			}
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (!varrockMage) {
			switch (getStage()) {
			case 0:
				setStage(1);
				end();
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				end();
				break;
			}
		} else {
			switch (getStage()) {
			case 0:
				end();
				break;
			case 1:
				switch (stage) {
				case 0:
					options("I'd like to buy some runes!", "Where do you get your runes from?", "All hail Zamorak!", "Nothing, thanks.");
					stage++;
					break;
				case 1:
					switch (buttonId) {
					case 1:
						player("I'd like to buy some runes!");
						stage = 10;
						break;
					case 2:
						player("Where do you get your runes from?", "No offence, but people around here don't exactly like", "'your type'.");
						stage = 20;
						break;
					case 3:
						player("All hail Zamorak!", "He's the man!", "If he can't do it, maybe some other guy can!");
						stage = 30;
						break;
					case 4:
						player("I didn't really want anything, thanks.", "I just like talking to random people I meet around the", "world.");
						stage = 40;
						break;
					}
					break;
				case 10:
					npc("I do not conduct business in this pathetic city.", "Speak to me in the wilderness if you desire to purchase", "runes from me.");
					stage++;
					break;
				case 11:
					end();
					break;
				case 20:
					npc("My 'type' Explain.");
					stage++;
					break;
				case 21:
					player("You know...", "Scary bearded men in dark clothing with unhealthy", "obsessions with destruction and stuff.");
					stage++;
					break;
				case 22:
					npc("Hmmm.", "Well, you may be right, the foolish Saradominists that", "own this pathetic city don't appreciate loyal Zamorakians,", "it is true.");
					stage++;
					break;
				case 23:
					player("So you can't be getting your runes anywhere around", "here...");
					stage++;
					break;
				case 24:
					npc("That is correct stranger.", "My mysteries of manufacturing Runes is a closely", "guarded secret of the Zamorakian brotherhood.");
					stage++;
					break;
				case 25:
					player("Oh, you mean the whole teleporting to the Rune", "essence mine, mining some essence, then using the", "talismans to locate the Rune Temples, then binding", "runes there?");
					stage++;
					break;
				case 26:
					player("I know all about it...");
					stage++;
					break;
				case 27:
					npc("WHAT?", "I... but... you...");
					stage++;
					break;
				case 28:
					npc("Tell me, this is important:", "You know of the ancient temples?", "You have been to a place where this 'rune essence'", "material is freely available?");
					stage++;
					break;
				case 29:
					npc("How did you get to such place?");
					stage = 200;
					break;
				case 200:
					player("Well, I helped deliver some research notes to Sedridor", "at the Wizards Tower, and he teleported me to a huge", "mine he said was hidden off to the North somewhere", "where I could mine essence.");
					stage++;
					break;
				case 201:
					npc("And there is an ubundant supply of this 'essence' there", "you say?");
					stage++;
					break;
				case 202:
					player("Yes, but I thought you said that you knew how to make", "runes?", "All this stuff is fairly basic knowledge I thought.");
					stage++;
					break;
				case 203:
					npc("No.", "No, not at all.");
					stage++;
					break;
				case 204:
					npc("We occasionally manage to plunder small samples of this", "'essence' and we have recently discovered these temples", "you speak of, but I have never ever heard of these talismans", "before, and I was certainly not aware that this 'essence'");
					stage++;
					break;
				case 205:
					npc("substance is a heavily stockpiled resource at the Wizards", "Tower.");
					stage++;
					break;
				case 206:
					npc("This changes everything.");
					stage++;
					break;
				case 207:
					player("How do you mean?");
					stage++;
					break;
				case 208:
					npc("For many years there has been a struggle for power", "on this world.", "You may dispute the morality of each side as you wish,", "but the stalemate that exists between my Lord Zamorak");
					stage++;
					break;
				case 209:
					npc("and that pathetic meddling fool Saradomin has meant", "that our struggle have become more secretive.", "We exist in a 'cold war' if you will, each side fearful of", "letting the other gain too much power, and each side");
					stage++;
					break;
				case 210:
					npc("equally fearful of entering into open warfare for fear of", "bringing our struggles to the attention of... other", "beings.");
					stage++;
					break;
				case 211:
					player("You mean Guthix?");
					stage++;
					break;
				case 212:
					npc("Indeed.", "Amongst others.", "But you now tell me that the Saradominist Wizards", "have the capability to mass produce runes, I can only");
					stage++;
					break;
				case 213:
					npc("conclude that they have been doing so secretly for some", "time now.");
					stage++;
					break;
				case 214:
					npc("I implore you adventurer, you may or may not agree", "with my aims, but you cannot allow such a one-sided", "shift in the balance of power to occur.");
					stage++;
					break;
				case 215:
					npc("Will you help me and my fellow Zamorakians to access", "this 'essence' mine?", "In return I will share with you the research we have", "gathered.");
					stage++;
					break;
				case 216:
					player("Okay, I'll help you.", "What can I do?");
					stage++;
					break;
				case 217:
					npc("All I need from you is the spell that will teleport me to", "this essence mine.", "That should be sufficient for the armies of Zamorak to", "once more begin stockpiling magic for war.");
					stage++;
					break;
				case 218:
					player("Oh.", "Erm...", "I don't actually know that spell.");
					stage++;
					break;
				case 219:
					npc("What?", "Then how do you access this location?");
					stage++;
					break;
				case 220:
					player("Oh, well, people who do know the spell teleport me there", "directly.", "Apparently they wouldn't teach it to me to try and keep", "the location secret.");
					stage++;
					break;
				case 221:
					npc("Hmmm.", "Yes, yes I see.", "Very well then, you may still assist us in finding this", "mysterious essence mine.");
					stage++;
					break;
				case 222:
					player("How would I do that?");
					stage++;
					break;
				case 223:
					setStage(2);
					player.getInventory().add(ORBS[0], player);
					npc("Here, take this scrying orb.", "I have cast a standard cypher spell upon it, so that it", "will absorb mystical energies that it is exposed to.");
					stage++;
					break;
				case 30:
					end();
					break;
				case 40:
					npc("...I see.", "Well, in the future, do not waste my time, or you will", "feel the wrath of Zamorak upon you.");
					stage++;
					break;
				case 41:
					end();
					break;
				}
				break;
			case 2:
				switch (stage) {
				case 0:
					if (!player.hasItem(ORBS[0]) && !player.getInventory().containsItem(ORBS[1])) {
						player("Uh...", "No...", "I kinda lost that orb thingy that you have me.");
						stage++;
						break;
					}
					if (!player.getInventory().containsItem(ORBS[1])) {
						player("No...", "Actually, I had something I wanted to ask you...");
						stage = 3;
					} else {
						player("Yes I have! I've got it right here!");
						stage = 50;
					}
					break;
				case 1:
					player.getInventory().add(ORBS[0], player);
					npc("What?", "Incompetent fool. Take this.", "And do not make me refret allying myself with you.");
					stage++;
					break;
				case 2:
					end();
					break;
				case 3:
					npc("I assume the task to be self-explainatory.", "What is it you wish to know?");
					stage++;
					break;
				case 4:
					player("Please excuse me, I have a very bad short term", "memory.", "What exactly am I supposed to be doing again?");
					stage++;
					break;
				case 5:
					npc("All I wish for you to do is to teleport to this 'rune", "essence' location from three different locations wile", "carrying the scrying orb I gave you.", "It will collect the data as you teleport.");
					stage++;
					break;
				case 6:
					end();
					break;
				case 224:
					npc("Bring it with you and teleport to the rune essence", "location, and it will absorb the mechanics of the spell and", "allow us to reverse-engineer the magic behind it.");
					stage++;
					break;
				case 225:
					npc("The important part is that you must teleport to the", "essence location from three entirely seperate locations.");
					stage++;
					break;
				case 226:
					npc("More than three may be helpful to us, but we need a", "minimum of three in order to triangulate the position of", "this essence mine.");
					stage++;
					break;
				case 227:
					npc("Is that all clear, stranger?");
					stage++;
					break;
				case 228:
					player("Yeah, I think so.");
					stage++;
					break;
				case 229:
					npc("Good.", "If you encounter any difficulties speak to me again.");
					stage++;
					break;
				case 230:
					end();
					break;
				case 50:
					npc("Excellent.", "Give it here, and I shall examine the findings.", "Speak to me in a small while.");
					stage++;
					break;
				case 51:
					setStage(3);
					player.getInventory().remove(ORBS[1]);
					end();
					break;
				}
				break;
			case 3:
				switch (stage) {
				case 0:
					npc("Indeed, a deal is always a deal.");
					stage++;
					break;
				case 1:
					npc("I offer you three things as a reward for your efforts on", "behalf of my Lord Zamorak;");
					stage++;
					break;
				case 2:
					npc("The first is knowledge.", "I offer you my collected research on the abyss.", "I also offer you 1000 points of experience in", "RuneCrafting for your trouble.");
					stage++;
					break;
				case 3:
					npc("Your second gift is convenience.", "Here you may take this pouch I discovered amidst my", "research.", "You will find it to have certain... interesting properties.");
					stage++;
					break;
				case 4:
					npc("Your final gift is that of movement", "I will from now on offer you a teleport to the abyss", "whenever you should require it.");
					stage++;
					break;
				case 5:
					setStage(4);
					player.getInventory().add(new Item(5520), player);
					player.getInventory().add(new Item(5509), player);
					player.getSkills().addExperience(Skills.RUNECRAFTING, 1000);
					player("Huh?", "Abyss?", "What are you talking about?", "You told me that you would help me with");
					stage++;
					break;
				}
				break;
			case 4:
				switch (stage) {
				case 0:
					switch (buttonId) {
					case 1:
						player("Uh...", "I really don't see how this talk about an 'abyss' relates", "to RuneCrafting in the slightest...");
						stage = 10;
						break;
					case 2:
						player("So...", "This 'abyss' place...", "Is it dangerous?");
						stage = 20;
						break;
					case 3:
						player("Well, I reckon I'm prepared to go there now.", "Beam me there, or whatever it is that you do!");
						stage = 30;
						break;
					}
					break;
				case 10:
					npc("My primary research responsibility was not towards the", "manufacture of runes, this is true.");
					stage = 8;
					break;
				case 20:
					npc("Well, the creates there ARE particularly offensive...");
					stage = 8;
					break;
				case 30:
					npc("No, not from here.", "The use of my Lord Zamorak magic in this land will", "draw too much attention to myself.");
					stage = 8;
					break;
				case 6:
					player("RuneCrafting!");
					stage++;
					break;
				case 7:
					npc("And so I have done.", "Read my research notes, they may enlighten you", "somewhat.");
					stage++;
					break;
				case 8:
					end();
					break;
				}
				break;
			}
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2257, 2258, 2259, 2260, 2261 };
	}

	/**
	 * Sets the stage.
	 * @param stage the stage.
	 */
	public void setStage(int stage) {
		player.getConfigManager().set(492, stage, true);
	}

	/**
	 * Gets the stage.
	 * @return the stage.
	 */
	public int getStage() {
		return player.getConfigManager().get(492);
	}
}
