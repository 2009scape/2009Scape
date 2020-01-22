package plugin.cutscene;

import org.crandor.game.content.activity.ActivityManager;
import org.crandor.game.content.activity.ActivityPlugin;
import org.crandor.game.content.activity.CutscenePlugin;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Direction;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.build.DynamicRegion;
import org.crandor.game.world.map.path.Path;
import org.crandor.game.world.map.path.Pathfinder;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.CameraContext;
import org.crandor.net.packet.context.CameraContext.CameraType;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.net.packet.out.CameraViewPacket;

/**
 * Represents the romeo and juliet cutscene plugin.
 * 
 * @author 'Vexia
 * @date 31/12/2013
 */
@InitializablePlugin
public final class RJCutscenePlugin extends CutscenePlugin {

	/**
	 * Represents the location the player will return to.
	 */
	private static final Location SPAWN_LOCATION = Location.create(3211, 3424, 0);

	/**
	 * Constructs a new {@code RJCutscenePlugin} {@code Object}.
	 */
	public RJCutscenePlugin() {
		this(null);
	}

	/**
	 * Constructs a new {@code RJCutscenePlugin} {@code Object}.
	 */
	public RJCutscenePlugin(final Player player) {
		super("Romeo & Juliet Cutscene");
		this.player = player;
	}

	@Override
	public boolean start(final Player player, final boolean login, Object... args) {
		final NPC romeo = NPC.create(639, base.transform(30, 37, 0));
		romeo.setDirection(Direction.EAST);
		romeo.faceLocation(getBase().transform(30, 38, 0));
		romeo.getLocks().lockMovement(10000);
		romeo.setWalks(false);
		npcs.add(NPC.create(639, base.transform(30, 37, 0)));
		for (NPC npc : npcs) {
			npc.init();
		}
		return super.start(player, login, args);
	}

	@Override
	public void open() {
		npcs.get(0).lock();
		player.getDialogueInterpreter().open(npcs.get(0).getId(), npcs.get(0), this);
		int x = player.getLocation().getX();
		int y = player.getLocation().getY();
		CameraContext rot = null;
		CameraContext pos = null;
		int height = 450;
		int speed = 100;
		int other = 1;
		pos = new CameraContext(player, CameraType.POSITION, x - 5, y - 4, height, other, speed);
		rot = new CameraContext(player, CameraType.ROTATION, x + 2, y, height, other, speed);
		PacketRepository.send(CameraViewPacket.class, pos);
		PacketRepository.send(CameraViewPacket.class, rot);
		player.lock();
		player.getLocks().lockMovement(1000000);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(9288);
		setRegionBase();
		registerRegion(region.getId());
	}

	@Override
	public void fade() {
		player.getQuestRepository().getQuest("Romeo & Juliet").finish(player);
	}

	@Override
	public void register() {
		new RomeoDialogue().init();
	}

	@Override
	public Location getStartLocation() {
		return getBase().transform(30, 38, 0);
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new RJCutscenePlugin(p);
	}

	@Override
	public Location getSpawnLocation() {
		return SPAWN_LOCATION;
	}

	/**
	 * Gets the phillipia npc.
	 * 
	 * @return the npc.
	 */
	public NPC getPhillipia() {
		return npcs.get(1);
	}

	/**
	 * Represents the plugin used to handle the romeo dialogue.
	 * 
	 * @author 'Vexia
	 * @date 31/12/2013
	 */
	public class RomeoDialogue extends DialoguePlugin {

		/**
		 * Represents the cutscene instance.
		 */
		private RJCutscenePlugin cutscene;

		/**
		 * Represents the phil npc.
		 */
		private NPC phill;

		/**
		 * Constructs a new {@code RomeoDialogue} {@code Object}.
		 */
		public RomeoDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code RomeoDialogue} {@code Object}.
		 * 
		 * @param player
		 *            the player.
		 */
		public RomeoDialogue(Player player) {
			super(player);
		}

		@Override
		public int[] getIds() {
			return new int[] { 639 };
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			Quest quest = player.getQuestRepository().getQuest("Romeo & Juliet");
			switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "No sorry. I haven't seen her.", "Perhaps I could help to find her for you?");
				stage = 1;
				break;
			case 1:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "No sorry. I haven't seen her.");
					stage = 200;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Perhaps I can help find her for you? What does she", "look like?");
					stage = 10;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh would you? That would be great! She has this sort", "of hair...");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hair...check..");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "...and she these...great lips...");
				stage = 13;
				break;
			case 13:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Lips...right.");
				stage = 14;
				break;
			case 14:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh and she has these lovley shoulders as well..");
				stage = 15;
				break;
			case 15:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Shoulders...right so she has hair, lips and shoulders...that", "should cut it down a bit.");
				stage = 16;
				break;
			case 16:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh yes, Juliet Is very different...please tell her that she", "is the love of my long and that I life to be with her?");
				stage = 17;
				break;
			case 17:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What?", "Surely you mean that 'she is the love of your life and", "that you long to be with her?");
				stage = 18;
				break;
			case 18:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh yeah...what you said...tell her that, it sounds much", "bettter!", "Oh you're so good at this!");
				stage = 19;
				break;
			case 19:
				interpreter.sendOptions("Select an Option", "Yes okay, I'll let her know.", "Sorry Romeo, I've got better things to do right now but", "maybe later?");
				stage = 20;
				break;
			case 20:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, ok, I'll let her know.");
					stage = 100;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Sorry Romeo, I've got better things to do right now but maybe latter?");
					stage = 50;
					break;
				case 3:
					end();
					break;
				}
				break;
			case 100:
				quest.setStage(player, 10);
				player.getQuestRepository().syncronizeTab(player);
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh great! And tell her that I want to kiss her a give.");
				stage = 101;
				break;
			case 101:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "You mean you want to give her a kiss!");
				stage = 102;
				break;
			case 102:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh you're good...you are good!");
				stage = 103;
				break;
			case 103:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "I see I've picked a true professional...!");
				stage = 104;
				break;
			case 104:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ok, thanks.");
				stage = 106;
				break;
			case 105:
				switch (buttonId) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ok, thanks.");
					stage = 106;
					break;
				}
				break;
			case 106:
				end();
				break;
			case 200:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "If you do see her please tell me!");
				stage = 201;
				break;
			case 201:
				end();
				break;
			case 230:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Please, oh please! Tell me where you have seen my juliet.");
				stage = 231;
				break;
			case 231:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I found her alone on a balcony!");
				stage = 255;
				break;
			case 255:
				end();
				break;
			case 300:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Oh great! That is great news! Well done...well done...", "what a total success!");
				stage = 301;
				break;
			case 301:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes, and she gave me a message to give you...");
				stage = 302;
				break;
			case 302:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Ohh great! A message...wow!");
				stage = 303;
				break;
			case 303:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yes!");
				stage = 304;
				break;
			case 304:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "A message...oh, I can't wait to read what my dear Juliet", "has to say....");
				stage = 305;
				break;
			case 305:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I know...it's exiting isn't it...?");
				stage = 306;
				break;
			case 306:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Yes...yes...");
				stage = 307;
				break;
			case 307:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "...");
				stage = 308;
				break;
			case 308:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_SNEAKY, "You've lost the message haven't you?");
				stage = 309;
				break;
			case 309:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Yep, haven't got a clue where it is.");
				stage = 310;
				break;
			case 310:
				end();
				break;
			case 400:
				interpreter.sendItemMessage(755, "You hand over Juliet's message to Romeo.");
				stage = 401;
				break;
			case 401:
				interpreter.sendDialogues(npc, null, "Oh, a message! A message! I've never had a message", "before...");
				stage = 402;
				break;
			case 402:
				interpreter.sendDialogues(player, null, "Really?");
				stage = 403;
				break;
			case 403:
				interpreter.sendDialogues(npc, null, "No, no, not one!");
				stage = 404;
				break;
			case 404:
				interpreter.sendDialogues(npc, null, "Oh, well, except for the occasional court summons.");
				stage = 405;
				break;
			case 405:
				interpreter.sendDialogues(npc, null, "But they're not really 'nice' messages. Not like this one!", "I'm sure that this message will be lovely.");
				stage = 406;
				break;
			case 406:
				interpreter.sendDialogues(player, null, "Well are you going to open it or not?");
				stage = 407;
				break;
			case 407:
				interpreter.sendDialogues(npc, null, "Oh yes, yes, of course!", "'Dearest Romeo, I am very pleased that you sent", player.getUsername() + " to look for me and to tell me that you still", "hold affliction...', Affliction! She thinks I'm diseased?");
				stage = 408;
				break;
			case 408:
				interpreter.sendDialogues(player, null, "'Affection?'");
				stage = 409;
				break;
			case 409:
				interpreter.sendDialogues(npc, null, "Ahh yes...'still hold affection for me. I still feel great", "affection for you, but unfortunately my Father opposes", "our marriage.'");
				stage = 410;
				break;
			case 410:
				interpreter.sendDialogues(player, null, "Oh dear...that doesn't sound too good.");
				stage = 411;
				break;
			case 411:
				interpreter.sendDialogues(npc, null, "What? '...great affection for you. Father opposes our..");
				stage = 412;
				break;
			case 412:
				interpreter.sendDialogues(npc, null, "'...marriage and will...");
				stage = 413;
				break;
			case 413:
				interpreter.sendDialogues(npc, null, "...will kill you if he sees you again!'");
				stage = 414;
				break;
			case 414:
				interpreter.sendDialogues(player, null, "I have not be honest, it's not getting any better...");
				stage = 415;
				break;
			case 415:
				interpreter.sendDialogues(npc, null, "'Our only hope is that Father Lawrence, our long time", "confidant, can help us in some way.'");
				stage = 416;
				break;
			case 416:
				interpreter.sendItemMessage(755, "Romeo folds the message away.");
				stage = 417;
				break;
			case 417:
				interpreter.sendDialogues(npc, null, "Well, that's it then...we haven't got a chance.");
				stage = 418;
				break;
			case 418:
				interpreter.sendDialogues(player, null, "What about Father Lawrence?");
				stage = 419;
				break;
			case 419:
				interpreter.sendDialogues(npc, null, "...our love is over...the great romance, the life of my", "love...");
				stage = 420;
				break;
			case 420:
				interpreter.sendDialogues(player, null, "...or you could speak to Father Lawrence!");
				stage = 421;
				break;
			case 421:
				interpreter.sendDialogues(npc, null, "Oh, my aching, breaking, heart...how useless the situation", "is now...we have no one to turn to...");
				stage = 422;
				break;
			case 422:
				quest.setStage(player, 30);
				player.getQuestRepository().syncronizeTab(player);
				player.getInventory().remove(new Item(755));
				interpreter.sendDialogues(player, null, "FATHER LAWRENCE!");
				stage = 423;
				break;
			case 423:
				interpreter.sendDialogues(npc, null, "Father Lawrence?");
				stage = 424;
				break;
			case 424:
				interpreter.sendDialogues(npc, null, "Oh yes, Father Lawrence...he's our long time confidant,", "he might have a solution! yes, yes, you have to go and", "talk to Lather Fawrence for us and ask him if he's got", "any suggestions for our predicatment?");
				stage = 425;
				break;
			case 425:
				interpreter.sendDialogues(player, null, "Where can I find Father Lawrence?");
				stage = 426;
				break;
			case 426:
				interpreter.sendDialogues(npc, null, "Lawther Fawrence! Oh he's....");
				stage = 427;
				break;
			case 427:
				interpreter.sendDialogues(npc, null, "You know he's not my 'real' Father don't you?");
				stage = 428;
				break;
			case 428:
				interpreter.sendDialogues(player, null, "I think I suspected that he wasn't.");
				stage = 429;
				break;
			case 429:
				interpreter.sendDialogues(npc, null, "Well anyway...he tells these song, loring bermons...and", "keep these here Carrockian vitizens snoring in his", "church to the East North.");
				stage = 430;
				break;
			case 430:
				interpreter.sendOptions("Select an Option", "How are you?", "Where can I find Father Lawrence?", "Have you heard anything from Juliet?", "Ok, thanks.");
				stage = 431;
				break;
			case 431:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "How are you?");
					stage = 500;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "Where can I find Father Lawrence?");
					stage = 530;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "Have you heard anything from Juliet?");
					stage = 560;
					break;
				case 4:
					interpreter.sendDialogues(player, null, "Ok, thanks.");
					stage = 580;
					break;
				}
				break;
			case 580:
				end();
				break;
			case 560:
				interpreter.sendDialogues(npc, null, "Sadly not my friend! And what's worse, her Father has", "threatend to kill me if he sees me. I mean, that seems", "a bit harsh!");
				stage = 561;
				break;
			case 561:
				interpreter.sendDialogues(player, null, "Well, I shouldn't worry too much...you can always run", "away if you see him...");
				stage = 562;
				break;
			case 562:
				interpreter.sendDialogues(npc, null, "I just wish I could remember what he looks like! I live", "in fear of every man I see!");
				stage = 563;
				break;
			case 563:
				interpreter.sendOptions("Select an Option", "How are you?", "Where can I find Father Lawrence?", "Have you heard anything from Juliet?", "Ok, thanks.");
				stage = 431;
				break;
			case 530:
				interpreter.sendDialogues(npc, null, "Lather Fawrence! Oh he's...");
				stage = 531;
				break;
			case 531:
				interpreter.sendDialogues(npc, null, "You know he's not my 'real' Father don't you?");
				stage = 532;
				break;
			case 532:
				interpreter.sendDialogues(player, null, "I think I suspected that he wasn't.");
				stage = 533;
				break;
			case 533:
				interpreter.sendDialogues(npc, null, "Well anyway...he tells these song, loring bermons...and", "keep these here Carrockian vitizens snoring in his", "church to the East North.");
				stage = 534;
				break;
			case 534:
				interpreter.sendOptions("Select an Option", "How are you?", "Where can I find Father Lawrence?", "Have you heard anything from Juliet?", "Ok, thanks.");
				stage = 431;
				break;
			case 500:
				interpreter.sendDialogues(npc, null, "Not so good my friend...I miss Judi..., Junie..., Jooopie...");
				stage = 501;
				break;
			case 501:
				interpreter.sendDialogues(player, null, "Juliet?");
				stage = 502;
				break;
			case 502:
				interpreter.sendDialogues(npc, null, "Juliet! I miss Juliet, terribly?");
				stage = 503;
				break;
			case 503:
				interpreter.sendDialogues(player, null, "Hmmm, so I see!");
				stage = 504;
				break;
			case 504:
				interpreter.sendOptions("Select an Option", "How are you?", "Where can I find Father Lawrence?", "Have you heard anything from Juliet?", "Ok, thanks.");
				stage = 431;
				break;
			case 800:
				interpreter.sendOptions("Select an Option", "How are you?", "Where can I find Father Lawrence?", "Have you heard anything from Juliet?", "Ok, thanks.");
				stage = 431;
				break;
			case 236:
				interpreter.sendDialogues(player, null, "Did not!");
				stage = 237;
				break;
			case 237:
				interpreter.sendDialogues(npc, null, "Oh, come on, come on, what did he say?");
				stage = 238;
				break;
			case 238:
				interpreter.sendDialogues(player, null, "He wants me to go to the Apothercary!");
				stage = 239;
				break;
			case 239:
				interpreter.sendDialogues(npc, null, "The Apothecary?");
				stage = 240;
				break;
			case 240:
				interpreter.sendDialogues(npc, null, "Oh...is he the one who mixes up all them magical potion-", "ey things?");
				stage = 241;
				break;
			case 241:
				interpreter.sendDialogues(player, null, "Yeah, I think so...but the word potion-ey doesn't exist.");
				stage = 242;
				break;
			case 242:
				interpreter.sendDialogues(npc, null, "Well, you just used it...so I guess it does exist!");
				stage = 243;
				break;
			case 243:
				interpreter.sendDialogues(player, null, "It doesn't matter...do you know where the Apothecary", "is?");
				stage = 244;
				break;
			case 244:
				interpreter.sendDialogues(npc, null, "It is Wouth Sest of here and near a sword shop.");
				stage = 245;
				break;
			case 245:
				end();
				break;
			case 326:
				interpreter.sendDialogues(npc, null, "Oh hello, have you seen Lather Fawrence?");
				stage = 327;
				break;
			case 327:
				interpreter.sendDialogues(player, null, "Yes, he's given me details of a potion which should help", "resolve this siution. The Apothecary is helping me", "prepare it.");
				stage = 328;
				break;
			case 328:
				interpreter.sendDialogues(npc, null, "Oooh, it all sounds horribly complicated?");
				stage = 329;
				break;
			case 329:
				interpreter.sendDialogues(npc, null, "All I can say is I'll be glad when Juliet's finally in that", "crypt!");
				stage = 330;
				break;
			case 330:
				interpreter.sendDialogues(player, null, "Spoken like a true lover!");
				stage = 331;
				break;
			case 331:
				end();
				break;
			case 350:
				interpreter.sendDialogues(npc, null, "Ooohh, that's the potion is it?", "Rather you than me!");
				stage = 351;
				break;
			case 351:
				interpreter.sendDialogues(player, null, "I'm not drinking it! It's for Juliet!");
				stage = 352;
				break;
			case 352:
				end();
				break;
			case 71:
				interpreter.sendDialogues(npc, null, "Ah right, the potion! Great...");
				stage = 72;
				break;
			case 72:
				interpreter.sendDialogues(npc, null, "What potion would that be then?");
				stage = 73;
				break;
			case 73:
				interpreter.sendDialogues(player, null, "The Cadava potion...you know, the one which will make", "her appear dead! She's in the crypt, pop along and claim", "your true love.");
				stage = 74;
				break;
			case 74:
				interpreter.sendDialogues(npc, null, "But I'm scared...will you come with me?");
				stage = 75;
				break;
			case 75:
				interpreter.sendDialogues(player, null, "Oh, ok...come on! I think I saw the entrance when I", "visited there last...");
				stage = 76;
				break;
			case 76:
				end();
				ActivityManager.start(player, "Romeo & Juliet Cutscene", false);
				player.lock();
				break;
			case 456:
				interpreter.sendDialogues(npc, null, "Her cousin and I are getting on well though. Thanks", "for your help.");
				stage = 457;
				break;
			case 457:
				end();
				break;
			case 740:
				interpreter.sendDialogues(player, null, "Oh, be quiet...");
				stage = 741;
				break;
			case 741:
				interpreter.sendDialogues(player, null, "We're here. Look, Juliet is over there!");
				stage = 742;
				break;
			case 742:
				close();
				Location ll = cutscene.getBase().transform(21, 36, 0);
				int x = ll.getX();
				int y = ll.getY();
				CameraContext rot = null;
				CameraContext pos = null;
				int height = 450;
				int speed = 55;
				int other = 1;
				pos = new CameraContext(player, CameraType.POSITION, x + 3, y + 4, height, other, speed);
				rot = new CameraContext(player, CameraType.ROTATION, x - 1, y - 2, height, other, speed);
				PacketRepository.send(CameraViewPacket.class, pos);
				PacketRepository.send(CameraViewPacket.class, rot);
				GameWorld.submit(new Pulse(5) {
					@Override
					public boolean pulse() {
						int x = player.getLocation().getX();
						int y = player.getLocation().getY();
						CameraContext rot = null;
						CameraContext pos = null;
						int height = 450;
						int speed = 100;
						int other = 1;
						pos = new CameraContext(player, CameraType.POSITION, x - 5, y - 4, height, other, speed);
						rot = new CameraContext(player, CameraType.ROTATION, x + 2, y, height, other, speed);
						PacketRepository.send(CameraViewPacket.class, pos);
						PacketRepository.send(CameraViewPacket.class, rot);
						interpreter.sendDialogues(player, null, "You go over to her...and I'll go and wait over here...");
						stage = 743;
						return true;
					}
				});
				break;
			case 743:
				interpreter.sendDialogues(npc, null, "Ohhh, ok then...");
				stage = 744;
				break;
			case 744:
				Location l = cutscene.base.transform(18, 32, 0);
				x = l.getX();
				y = l.getY();
				height = 330;
				speed = 100;
				other = 1;
				pos = new CameraContext(player, CameraType.POSITION, x, y - 1, height, other, speed);
				rot = new CameraContext(player, CameraType.ROTATION, x + 20, y + 50, height, other, speed);
				PacketRepository.send(CameraViewPacket.class, pos);
				PacketRepository.send(CameraViewPacket.class, rot);
				close();
				npc.getWalkingQueue().reset();
				npc.getWalkingQueue().addPath(cutscene.getBase().transform(19, 35, 0).getX(), cutscene.getBase().transform(19, 35, 0).getY());
				GameWorld.submit(new Pulse(12) {
					@Override
					public boolean pulse() {
						interpreter.sendDialogues(npc, null, "Hey...Juliet...");
						stage = 745;
						phill = NPC.create(3325, cutscene.getBase().transform(30, 37, 0));
						phill.init();
						cutscene.getNPCS().add(phill);
						Path path = Pathfinder.find(phill, cutscene.getBase().transform(20, 35, 0));
						path.walk(phill);
						return true;
					}
				});
				break;
			case 745:
				interpreter.sendDialogues(npc, null, "Juliet...");
				stage = 746;
				break;
			case 746:
				interpreter.sendDialogues(npc, null, "Oh dear...you seem to be dead.");
				stage = 747;
				break;
			case 747:
				interpreter.sendDialogues(phill, null, "Hi Romeo...I'm Phillipa!");
				stage = 748;
				break;
			case 748:
				npc.face(phill);
				interpreter.sendDialogues(npc, null, "Wow! You're a fox!");
				stage = 749;
				break;
			case 749:
				interpreter.sendDialogues(phill, null, "It's a shame about Juliet...but perhaps we can meet up", "later?");
				stage = 750;
				break;
			case 750:
				interpreter.sendDialogues(npc, null, "Who's Juliet?");
				stage = 751;
				break;
			case 751:
				end();
				cutscene.stop(true);
				break;
			}
			return true;
		}

		@Override
		public DialoguePlugin newInstance(Player player) {

			return new RomeoDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			Quest quest = player.getQuestRepository().getQuest("Romeo & Juliet");
			npc = (NPC) args[0];
			if (args.length > 1) {
				cutscene = (RJCutscenePlugin) args[1];
				interpreter.sendDialogues(npc, null, "This is pretty scary...");
				stage = 740;
				return true;
			}
			switch (quest.getStage(player)) {
			case 0:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Juliet. Juliet, where art thou Juliet?", "Have you seen my Juliet?");
				stage = 0;
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.NO_EXPRESSION, "Go and speak with Juliet!");
				stage = 457;
				break;
			case 20:
				if (!player.getInventory().contains(755, 1)) {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Romeo...great news...I've been in touch with Juliet!");
					stage = 300;
				} else {
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Romeo...great news...I've been in touch with Juliet!", "She's written a message for you...");
					stage = 400;
				}
				break;
			case 30:
				interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Hey again Romeo!");
				stage = 800;
				break;
			case 40:
				interpreter.sendDialogues(npc, null, "Ooh did you manage to survive one of Lather", "Fawrences sermons? I bet not, you were ages! I bet", "you snoozed on the welcome mat just as soon as you", "heard his voice!");
				stage = 236;
				break;
			case 50:
				interpreter.sendDialogues(player, null, "Hi Romeo!");
				stage = 326;
				break;
			case 60:
				if (!player.getInventory().contains(756, 1)) {
					interpreter.sendDialogues(player, null, "Hi Romeo!");
					stage = 326;
				} else {
					interpreter.sendItemMessage(756, "Romeo spots the Cadava potion.");
					stage = 350;
				}
				break;
			case 70:
				interpreter.sendDialogues(player, null, "Romeo, it's all set. Juliet has drunk the potion and has", "been taken down into the Crypt...now you just need to", "pop along and collect her.");
				stage = 71;
				break;
			case 100:
				interpreter.sendDialogues(npc, null, "I heard Juliet had died. Terrible business.");
				stage = 456;
				break;
			}
			return true;
		}
	}
}
