package plugin.cutscene;

import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItem;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.path.Path;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.game.world.repository.Repository;
import org.crandor.game.world.update.flag.context.Animation;

/**
 * Represents the dialogue of the juliet NPC.
 * @author 'Vexia
 * @date 31/12/2013
 */
public final class JulietDialogue extends DialoguePlugin {

	/**
	 * Represents the quest instance.
	 */
	private Quest quest;

	/**
	 * Represents the juliet cutscene plugin.
	 */
	private JulietCutscenePlugin cutscene;

	/**
	 * Represents the cadava potion(next stage = 70)
	 */
	private static final Item POTION = new Item(756);

	/**
	 * Represents the drinking animation.
	 */
	private static final Animation DRINK_ANIM = new Animation(1327);

	/**
	 * Constructs a new {@code JulietDialogue} {@code Object}.
	 */
	public JulietDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JulietDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JulietDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JulietDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		quest = player.getQuestRepository().getQuest("Romeo & Juliet");
		npc = (NPC) args[0];
		if (args.length > 1) {
			cutscene = (JulietCutscenePlugin) args[1];
			interpreter.sendDialogues(npc, null, "Oh, here's Phillipa, my cousin...she's in on the plot too!");
			stage = 2000;
			return true;
		}
		switch (quest.getStage(player)) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Romeo, Romeo, wherefore art thou Romeo? Bold", "adventurer, have you seen Romeo on your travels?", "Skinny guy, a bit wishy washy, head full of poetry.");
			stage = 0;
			break;
		case 10:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Juliet, I come from Romeo.", "He begs me to tell you that he cares still.");
			stage = 700;
			break;
		case 20:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hello Juliet!");
			stage = 100;
			break;
		case 30:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hi Juliet, I have passed your message on to", "Romeo...he's scared half out of his wits at the news that", "your father wants to kill him.");
			stage = 900;
			break;
		case 40:
			interpreter.sendDialogues(player, null, "Hi Juliet, I've found Father Lawrence and he has a", "cunning plan. But for it to work, I need to seek the", "Apothecary!");
			stage = 236;
			break;
		case 50:
			interpreter.sendDialogues(player, null, "Hi Juliet!");
			stage = 656;
			break;
		case 60:
			if (!player.getInventory().contains(756, 1)) {
				interpreter.sendDialogues(player, null, "Hi Juliet! I have an interesting propostion for", "you...suggested by Father Lawrence. It may be the", "only way you'll be able to escape from this house and", "be with Romeo.");
				stage = 950;
			} else {
				interpreter.sendDialogues(player, null, "Hi Juliet! I have an interesting propostion for", "you...suggested by Father Lawrence. It may be the", "only way you'll be able to escape from this house and", "be with Romeo.");
				stage = 950;
			}
			break;
		case 70:
			interpreter.sendDialogues(npc, null, "Quickly! Go and tell Romeo the plan!");
			stage = 1002;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		final Quest quest = player.getQuestRepository().getQuest("Romeo & Juliet");
		final NPC phil = cutscene != null ? cutscene.getPhillipia() : (NPC) Repository.findNPC(3325);
		final NPC dad = cutscene != null ? cutscene.getNPCS().get(2) : (NPC) Repository.findNPC(3324);
		switch (stage) {
		case 2000:
			interpreter.sendDialogues(npc, null, "She's going to make it seem even more convincing!");
			stage = 2001;
			break;
		case 2001:
			interpreter.sendDialogues(phil, null, "Yes, I'm quite the actress! Good luck dear cousin!");
			stage = 2002;
			break;
		case 2002:
			interpreter.sendDialogues(npc, null, "Right...bottoms up!");
			stage = 2003;
			break;
		case 2003:
			close();
			GameWorld.submit(new Pulse(1) {
				int counter = 0;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 0:
						npc.animate(DRINK_ANIM);
						break;
					case 2:
						interpreter.sendDialogues(npc, FacialExpression.THINKING, "Urk!");
						stage = 2004;
						return true;
					}
					return false;
				}

			});
			break;
		case 2004:
			close();
			npc.animate(new Animation(836));
			GameWorld.submit(new Pulse(1) {
				int counter = 0;

				@Override
				public boolean pulse() {
					switch (counter++) {
					case 2:
						interpreter.sendDialogues(phil, FacialExpression.THINKING, "Oh no...Juliet has...died!");
						stage = 2005;
						return true;
					}
					return false;
				}

			});
			break;
		case 2005:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "You might be more believable if you're not smiling when", "you say it...");
			stage = 2006;
			break;
		case 2006:
			interpreter.sendDialogues(phil, FacialExpression.HALF_GUILTY, "Oh yeah...you might be right...ok, let's try again.");
			stage = 2007;
			break;
		case 2007:
			interpreter.sendDialogues(phil, FacialExpression.HALF_GUILTY, "Oh no...Juliet has...died?");
			stage = 2008;
			break;
		case 2008:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Perhaps a bit louder, like you're upset...that your cousin", "has died!");
			stage = 2009;
			break;
		case 2009:
			interpreter.sendDialogues(phil, FacialExpression.HALF_GUILTY, "Right...yes...Ok, ok, I think I'm getting my motivation", "now. Ok, let's try this again!");
			stage = 2010;
			break;
		case 2010:
			interpreter.sendDialogues(phil, FacialExpression.FURIOUS, "OH NO...JULIET HAS...DIED?....", "Ooooooohhhhhhh.....(sob).Juliet...my poor dead cousin!");
			stage = 2011;
			break;
		case 2011:
			interpreter.sendDialogues(dad, null, "What's all that screaming?");
			stage = 2012;
			break;
		case 2012:
			final Path path = Pathfinder.find(dad, player.getLocation().transform(0, 1, 0));
			path.walk(dad);
			interpreter.sendDialogues(dad, null, "Oh no! My poor daughter...what has become of you?");
			stage = 2013;
			break;
		case 2013:
			interpreter.sendDialogues(phil, FacialExpression.FURIOUS, "Poor Juliet...make preparations for her body to be", "placed in the Crypt...");
			stage = 2014;
			break;
		case 2014:
			if (player.getInventory().remove(POTION)) {
				quest.setStage(player, 70);
				cutscene.stop(true);
				end();
			}
			break;
		case 0:
			interpreter.sendOptions("Select an Option", "Yes I've met him.", "No, I think I'd have remembered if I'd met him.", "I guess I could look for him for you.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Yes I've met him.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "No, I think I'd have remembered if I'd met him.");
				stage = 20;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I guess I could look for him for you.");
				stage = 30;
				break;
			}
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, well that's a shame, I was hopping that you might", "deliver a message to him for me.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Sorry, but I don't even know what he looks like.");
			stage = 22;
			break;
		case 22:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, that would be so wonderful of you!", "I'd be most grateful if you could please deliver a", "message to him?");
			stage = 31;
			break;
		case 31:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Certainly, I'll do it straight away.");
			stage = 32;
			break;
		case 32:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Many thanks! Oh, i'm so very grateful. You may be", "our only hope.");
			stage = 33;
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "I'd be most grateful if you could please deliver a", "message to him?");
			stage = 31;
			break;
		case 33:
			if (!player.getInventory().add(new Item(755))) {
				GroundItemManager.create(new GroundItem(new Item(755), npc.getLocation(), player));
			}
			quest.setStage(player, 20);
			player.getQuestRepository().syncronizeTab(player);
			interpreter.sendItemMessage(755, "Juliet gives you a message.");
			stage = 34;
			break;
		case 34:
			end();
			break;
		case 100:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Hello there...have you delivered the message to Romeo", "yet? What news do you have from my loved one?");
			stage = 101;
			break;
		case 101:
			if (!player.getInventory().contains(755, 1) && !player.getBank().contains(755, 1)) {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Hmmm, that's the thing about messages....they're so easy", "to misplace...");
				stage = 105;
			} else {
				interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Oh, sorry, I've not had a chance to deliver it yet!");
				stage = 102;
			}
			break;
		case 102:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh, that's a shame. I've been waiting so patiently to", "hear some word from him.");
			stage = 103;
			break;
		case 103:
			end();
			break;
		case 105:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "How could you lose that message?", "It was incredibly important...and it took me an age to", "write! I used joined up writing and everything!");
			stage = 106;
			break;
		case 106:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Please, take this new message to him,", "and please don't loose it.");
			stage = 107;
			break;
		case 107:
			if (!player.getInventory().add(new Item(755))) {
				GroundItemManager.create(new GroundItem(new Item(755), npc.getLocation(), player));
			}
			quest.setStage(player, 20);
			player.getQuestRepository().syncronizeTab(player);
			interpreter.sendItemMessage(755, "Juliet gives you another message.");
			stage = 108;
			break;
		case 108:
			end();
			break;
		case 900:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "yes, unfortunately my father is quite the hunter, you", "may have seen some the animal head trophies on the", "wall. And it would be so awful to see Romeo's head up", "there with them!");
			stage = 901;
			break;
		case 901:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I know what you mean...");
			stage = 902;
			break;
		case 902:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "...this hair colour will clash terribly with the rest of the", "decoration.");
			stage = 903;
			break;
		case 903:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "That's not what I was suggesting at all...");
			stage = 904;
			break;
		case 904:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "I know, I know...I was just kidding.");
			stage = 905;
			break;
		case 905:
			interpreter.sendDialogues(player, null, "Anyway, don't worry because I'm on the case. I'm", "going to get some help from Father Lawrence.");
			stage = 906;
			break;
		case 906:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh yes, I'm sure that Father Lawrence will come up", "with a solution. I hope you find him soon.");
			stage = 907;
			break;
		case 907:
			end();
			break;
		case 700:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Oh how my heart soars to hear this news! Please take", "this message to him with great haste.");
			stage = 701;
			break;
		case 701:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Well, I hope it's good news...he was quite upset when I", "left him.");
			stage = 702;
			break;
		case 702:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "He's quite often upset...the poor sensitive soul. But I", "don't think he's going to take this news very well,", "however, all is not lost.");
			stage = 703;
			break;
		case 703:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Everything is explained in the letter, would you be so", "kind and deliver it to him please?");
			stage = 704;
			break;
		case 704:
			interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, "Certainly, I'll do so straight away.");
			stage = 705;
			break;
		case 705:
			interpreter.sendDialogues(npc, FacialExpression.HALF_GUILTY, "Many thanks! Oh, I'm so very grateful. You may be", "our only hope.");
			stage = 706;
			break;
		case 706:
			if (!player.getInventory().add(new Item(755))) {
				GroundItemManager.create(new GroundItem(new Item(755), npc.getLocation(), player));
			}
			quest.setStage(player, 20);
			player.getQuestRepository().syncronizeTab(player);
			interpreter.sendItemMessage(755, "Juliet gives you a message.");
			stage = 707;
			break;
		case 707:
			end();
			break;
		case 236:
			interpreter.sendDialogues(npc, null, "Oh good! I knew Father Lawrence would come up with", "something. However, I don't know where the apothecary", "is...I hope you find him soon. My father's temper gets", "no better.");
			stage = 237;
			break;
		case 237:
			end();
			break;
		case 656:
			interpreter.sendDialogues(npc, null, "Hi " + player.getUsername() + ", how close am I to being with my true", "love Romeo?");
			stage = 657;
			break;
		case 657:
			interpreter.sendDialogues(player, null, "Sorry, I still have to get a speical potion for you.");
			stage = 658;
			break;
		case 658:
			interpreter.sendDialogues(npc, null, "Oh, I hope it isn't a love potion because you would be", "wasting your time. My love for Romeo grows stronger", "every minute...");
			stage = 659;
			break;
		case 659:
			interpreter.sendDialogues(player, null, "That must be because you're not with him...");
			stage = 660;
			break;
		case 660:
			interpreter.sendDialogues(npc, null, "Oh no! I long to be close to my true love Romeo!");
			stage = 661;
			break;
		case 661:
			interpreter.sendDialogues(player, null, "Well, ok then...I'll set about getting this potion as quickly", "as I can!");
			stage = 662;
			break;
		case 662:
			interpreter.sendDialogues(npc, null, "Fair luck to you, the end is close.");
			stage = 663;
			break;
		case 663:
			end();
			break;
		case 950:
			interpreter.sendDialogues(npc, null, "Go on....");
			stage = 951;
			break;
		case 951:
			if (!player.getInventory().containsItem(POTION)) {
				interpreter.sendDialogues(player, null, "Let me go get the potion..");
				stage = 663;
			} else {
				interpreter.sendDialogues(player, null, "I have a Cadava potion here, suggested by Father", "Lawrence. If you drink it, it will make you appear dead!");
				stage = 952;
			}
			break;
		case 952:
			interpreter.sendDialogues(npc, null, "Yes...");
			stage = 953;
			break;
		case 953:
			interpreter.sendDialogues(player, null, "And when you appear dead...your still and lifeless", "corpse will be removed to the crypt!");
			stage = 954;
			break;
		case 954:
			interpreter.sendDialogues(npc, null, "Oooooh, a cold dark creepy crypt...");
			stage = 955;
			break;
		case 955:
			interpreter.sendDialogues(npc, null, "...sounds just peachy!");
			stage = 956;
			break;
		case 956:
			interpreter.sendDialogues(player, null, "Then...Romeo can steal into the crypt and rescure you", "just as you wake up!");
			stage = 957;
			break;
		case 957:
			interpreter.sendDialogues(npc, null, "...and this is the great idea for getting me out of here?");
			stage = 958;
			break;
		case 958:
			interpreter.sendDialogues(player, null, "To be fair, I can't take all the credit...in fact...it was all", "Father Lawrence's suggestion...");
			stage = 959;
			break;
		case 959:
			interpreter.sendDialogues(npc, null, "Ok...if this is the best we can do...hand over the potion!");
			stage = 960;
			break;
		case 960:
			interpreter.sendItemMessage(756, "You pass the suspicious potion to Juliet.");
			stage = 961;
			break;
		case 961:
			interpreter.sendDialogues(npc, null, "Wonderful! I just hope Romeo can remember to get", "me from the crypt.");
			stage = 962;
			break;
		case 962:
			interpreter.sendDialogues(npc, null, "Please go to Romeo and make sure he understands.", "Although I love his gormless, lovelorn soppy ways, he", "can be a bit dense sometimes and I don't want to wake", "up in that crypt on my own.");
			stage = 963;
			break;
		case 963:
			interpreter.sendDialogues(npc, null, "Before I swig this potion down, let me stand on the", "balcony so that I might see the sun one last time before", "I am commited to the crypt.");
			stage = 964;
			break;
		case 964:
			end();
			ActivityManager.start(player, "Juliet Cutscene", false);
			break;
		case 965:
			interpreter.sendDialogues(npc, null, "She's going to make it seem even more convincing!");
			stage = 966;
			break;
		case 966:
			interpreter.sendDialogues(phil, null, "Yes, I'm quite the actress! Good luck dear cousin!");
			stage = 967;
			break;
		case 967:
			interpreter.sendDialogues(npc, null, "Right...buttoms up!");
			stage = 968;
			break;
		case 968:
			npc.animate(npc.getProperties().getDeathAnimation());
			interpreter.sendDialogues(npc, null, "Urk!");
			stage = 969;
			break;
		case 969:
			interpreter.sendDialogues(phil, null, "Oh no...Juliet has...died!");
			stage = 970;
			break;
		case 970:
			interpreter.sendDialogues(player, null, "You might be more believable if you're not smiling when", "you say it...");
			stage = 971;
			break;
		case 971:
			interpreter.sendDialogues(phil, null, "Oh yeah...you might be right...ok, let's try again.");
			stage = 972;
			break;
		case 972:
			interpreter.sendDialogues(phil, null, "Oh no...Juliet has...died?");
			stage = 973;
			break;
		case 973:
			interpreter.sendDialogues(player, null, "Perhaps a bit louder, like you're upset...that your cousin", "has died!");
			stage = 974;
			break;
		case 974:
			interpreter.sendDialogues(phil, null, "Right...yes...Ok, ok, I think I'm getting my motivation", "now. Ok, let's try this again!");
			stage = 975;
			break;
		case 975:
			interpreter.sendDialogues(phil, null, "OH NO...JULIET HAS...DIED?...", "Ooooooohhhhhh...(sob), (sob).Juliet...my poor dead cousin!");
			stage = 976;
			break;
		case 976:
			interpreter.sendDialogues(dad, null, "What's all that screaming?");
			stage = 977;
			break;
		case 977:
			interpreter.sendDialogues(dad, null, "Oh no! My poor daughter...what has become of you?");
			stage = 978;
			break;
		case 978:
			interpreter.sendDialogues(phil, null, "Poor Juliet...make preparations for her body to be", "placed in the Crypt...");
			stage = 979;
			break;
		case 979:
			end();
			break;
		case 1002:
			interpreter.sendDialogues(player, null, "I'm on my way!");
			stage = 979;
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 637 };
	}

}