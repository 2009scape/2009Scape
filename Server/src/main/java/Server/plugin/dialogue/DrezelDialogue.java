package plugin.dialogue;

import core.cache.def.impl.NPCDefinition;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.quest.Quest;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the drezel npc.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DrezelDialogue extends DialoguePlugin {

	/**
	 * Represents the array of items related to drezel.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(2953), new Item(2954) };

	/**
	 * Constructs a new {@code DrezelDialogue} {@code Object}.
	 */
	public DrezelDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DrezelDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DrezelDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DrezelDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		npc.setName("Drezel");
		NPCDefinition.forId(getIds()[0]).setName("Drezel");
		Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		if (quest.getStage(player) == 13) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello.");
			stage = 0;
		} else if (quest.getStage(player) == 14) {
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How does it adventurer? Any luck in finding the key to", "the cell or a way of stopping the vampire yet?");
			stage = 600;
		} else if (quest.getStage(player) == 15) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "The key fitted the lock! You're free to leave now!");
			stage = 701;
		} else if (quest.getStage(player) == 16) {
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I poured the blessed water over the vampires coffin. I", "think that should trap him in there long enough for you", "to escape.");
			stage = 800;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Priest in Peril");
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh! You do not appear to be on of those Zamorkians", "who imprisoned me here! Who are you and why are", "you here?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "My name's " + player.getUsername() + ". King Roald sent me to find out", "what was going on at the temple. I take it you are", "Drezel?");
			stage = 2;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That is right! Oh, praise be to Saradomin! All is not yet", "lost! I feared that when those Zamorakians attacked this", "place and imprisoned");
			stage = 3;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "me up here, Misthalin would be doomed! If they should", "manage to desecrate the holy river Salve we would be", "defenceless against Morytania!");
			stage = 4;
			break;
		case 4:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How is a river a good defence then?");
			stage = 5;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, it is a long tale, and I am not sure we have time!");
			stage = 6;
			break;
		case 6:
			interpreter.sendOptions("Select an Option", "Tell me anyway.", "You're right, we don't.");
			stage = 7;
			break;
		case 7:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Tell me anyway.");
				stage = 8;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You're right, we don't.");
				stage = 500;
				break;
			}
			break;
		case 8:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "d, Saradomin has granted you wisdom I see. Well, the", "story of the river Salve and of how it protects Mithsalin", "is the story of this temple,");
			stage = 9;
			break;
		case 9:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "and of the seven warrior priests who died here long ago,", "from whom I am descended. Once long ago Misthalin", "did not have the borders that");
			stage = 10;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "it currently does. This entire area, as far West as", "Varrock itself was under the control of an evil god.", "There was frequent skirmishing");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "along the borders, as the brave heroes of Varrock", "fought to keep the evil creatures that now are trapped", "on the eastern side of the River Salve from over", "running");
			stage = 12;
			break;
		case 12:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "the human encampments, who worshipped Saradomin.", "Then one day, Saradomin himself appeared to one of", "our mighty heroes, whose name has been forgotten by", "history,");
			stage = 13;
			break;
		case 13:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "and told him that should we be able to take the pass that", "this temple now stands in, Saradomin would use his", "power to bless this river, and make it");
			stage = 14;
			break;
		case 14:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "impassable to all creatures with evil in their hearts. This", "unknown hero grouped together all of the mightiest", "fighters whose hearts were pure");
			stage = 15;
			break;
		case 15:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "that he could find, and the seven of them rode here to", "make a final stand. The enemies swarmed across the", "Salve but they did not yield");
			stage = 16;
			break;
		case 16:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "For then days and nights they fought, never sleeping,", "never eating, fuelled by their desire to make the world a", "better place for humans to live.");
			stage = 17;
			break;
		case 17:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "On the eleventh day they were to be joined by", "reinforcements from a neighbouring encampment, but", "then those reinforecements arrived all they found");
			stage = 18;
			break;
		case 18:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "were the bodies of these seven brave but unknown", "heroes, surrounded by the piles of the dead creatures of", "evil that had tried to defeat them.");
			stage = 19;
			break;
		case 19:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "The men were saddend at the loss of such pure and", "mighty warriors, yet their sacrifice had not been in", "vain; for the water of the Salve");
			stage = 20;
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "had indeed been filled with the power of Saradomin, and", "the evil creatures of Morytania were trapped beyond", "the river banks forever, by their own evil.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "In memory of this brave sacrifice my ancestors built", "this temple so that the land would always be free of the", "evil creatures");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "who wish to destroy it, and laid the bodies of those brave", "warriors in tombs of honour below this temple with", "golden gifts on the tombs as marks of respect.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "They also built a statue on the river mouth so that all", "who mighty try and cross into Misthalin from Morytania", "would know that these lands are protected");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "by the glory of Saradomin and that good will always", "defeat evil, no matter how the odds are stacked against", "them.");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Ok, I can see how the river protects the border, but I", "can't see how anything could affect that from this", "temple.");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, as much as it saddens me to say so adventurer,", "Lord Saradomin's presence has not been felt on the", "land for many years now, and even");
			stage = 27;
			break;
		case 27:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "though all true Saradominists know that he watches over", "us, his power upon the land is not as strong as it once", "was.");
			stage = 28;
			break;
		case 28:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I fear that should those Zamorakians somehow pollute", "the Salve and desecrate his blessing, his power might not", "be able to stop.");
			stage = 29;
			break;
		case 29:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "the army of evil that lurks to the east, longing for the", "opportunity to invade and destroy us all!");
			stage = 30;
			break;
		case 31:
			interpreter.sendOptions("Select an Option", "Yes.", "No.");
			stage = 503;
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So what do you say adventurer? Will you aid me and", "all of Misthalin in foiling this Zamorakian plot?");
			stage = 31;
			break;
		case 500:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, let's just say if we cannot undo whatever damage", "has been done here the entire land is in grave peril!");
			stage = 501;
			break;
		case 501:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "So what do you say adventurer? Will you aid me and", "all of Misthalin in foiling this Zamorakian plot?");
			stage = 502;
			break;
		case 502:
			interpreter.sendOptions("Select an Option", "Yes.", "No.");
			stage = 503;
			break;
		case 503:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes, ofcourse. Any threat to Misthalin must be", "neutralised immediately. So what can I do to help?");
				stage = 506;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "HA! NO! You can rot in there for all I care you", "stupid priest! All hail mighty Zamorak! Death to puny", "Misthalin!");
				stage = 504;
				break;
			}
			break;
		case 504:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oooooh... I knew it was too good to be true... then", "leave me to my fate villain, there's no need to taunt me", "as well as keeping me imprisoned.");
			stage = 505;
			break;
		case 505:
			end();
			break;
		case 506:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well, the immediate problem is that I am trapped in this", "cell. I know that they key to free me is nearby, for none", "of the Zamorakians");
			stage = 507;
			break;
		case 507:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "who imprisoned me here were ever gone for long", "periods of time, Should you find the key however, as", "you may have noticed,");
			stage = 508;
			break;
		case 508:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "there is a vampire in that coffin over there. I do not", "know how they managed to find it, but it is one of the", "ones that somehow");
			stage = 509;
			break;
		case 509:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "survived the battle here all those years ago, and is by", "now quite, quite, mad. It has been trapped on this side", "of the river for centuries,");
			stage = 510;
			break;
		case 510:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "and as those fiendish Zamorakians pointed out to me", "with delight, as a descendant of one of those who", "trapped it here, it will recognise");
			stage = 511;
			break;
		case 511:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "the smell of my blood should I come anywhere near it.", "It will of course then wake up and kill me, very", "probably slowly and painfully.");
			stage = 512;
			break;
		case 512:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Maybe I could kill it somehow then while it is asleep?");
			stage = 513;
			break;
		case 513:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "No adventurer, I do not think it would be wise for you", "to wake it at all. As I say, it is little more than a wild", "animal, and must");
			stage = 514;
			break;
		case 514:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "be extremely powerful to have survived until today. I", "suspect your best chance would be to incapacitate it", "somehow.");
			stage = 515;
			break;
		case 515:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, find the key to your cell, and do something about", "the vampire.");
			stage = 516;
			break;
		case 516:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "When you have done both of those I will be able to", "inspect the damage which those Zamorakians have done", "to the purity of the Salve");
			stage = 517;
			break;
		case 517:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Depending on the severity of the damage, I may", "require further assistance from you in restoring its", "purity.");
			stage = 518;
			break;
		case 518:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, well first thing's first; let's get you out of here.");
			stage = 519;
			break;
		case 519:
			quest.setStage(player, 14);
			end();
			break;
		case 600:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, not yet...");
			stage = 601;
			break;
		case 601:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well don't give up adventurer! That key MUST be", "around here somewhere! I know none of those", "Zamorakians ever got very far from this building!");
			stage = 602;
			break;
		case 602:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "How do you know that?");
			stage = 603;
			break;
		case 603:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I could hear them laughing about some gullible fool that", "they tricked into killing the guard dog at the", "monument.");
			stage = 604;
			break;
		case 604:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh.");
			stage = 605;
			break;
		case 605:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Honestly, what kind of idiot would go around killing", "things just because a stranger told them to? What kind", "of oafish, numb-skulled, dim-witted,");
			stage = 606;
			break;
		case 606:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Okay, OKAY, I get the picture!");
			stage = 607;
			break;
		case 607:
			end();
			break;
		case 701:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Well excellent work adventurer! Unfortunately, as you", "know, I cannot risk waking that vampire in the coffin.");
			stage = 702;
			break;
		case 702:
			if (player.getInventory().contains(2953, 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I have some water from the Salve. It seems to have", "been desecrated though. Do you think you could bless", "it for me?");
				stage = 703;
			} else {

				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Do you have any idea about dealing with vampire?");
				stage = 730;
			}
			break;
		case 703:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Yes, good thinking adventurer! Give it to me, I will bless", "it!");
			stage = 704;
			break;
		case 704:
			if (!player.getInventory().containsItem(ITEMS[0])) {
				end();
				return true;
			}
			if (player.getInventory().remove(ITEMS[0])) {
				player.getInventory().add(ITEMS[1]);
			}
			end();
			player.getPacketDispatch().sendMessage("The priest blesses the water for you.");
			break;
		case 730:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "well, the water of the salve should still have enough", "power to work against the vampie despite what those", "Zamorakians might have done to it...");
			stage = 731;
			break;
		case 731:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Maybe you should try and get hold of some from", "somewhere?");
			stage = 732;
			break;
		case 732:
			end();
			break;
		case 800:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Excellent work adventurer! I am free at last! Let me", "ensure that evil vampire is trapped for good. I will", "meet you down by the monument.");
			stage = 801;
			break;
		case 801:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Look for me down there, I need to assess what damage", "has been done to our holy barrier by those evil", "Zamorakians!");
			stage = 802;
			break;
		case 802:
			Quest quests = player.getQuestRepository().getQuest("Priest in Peril");
			quests.setStage(player, 17);
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 1047 };
	}
}
