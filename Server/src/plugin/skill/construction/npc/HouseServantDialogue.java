package plugin.skill.construction.npc;


import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.construction.HouseManager;
import org.crandor.game.content.skill.member.construction.Servant;
import org.crandor.game.content.skill.member.construction.ServantType;
import org.crandor.game.interaction.MovementPulse;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.IronmanMode;
import org.crandor.game.node.item.Item;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.path.Pathfinder;
import plugin.interaction.inter.SawmillPlankInterface.Plank;

/**
 * Handles the Servant's dialogues.
 * @author Splinter
 * @date 9/23/2015
 * @version 0.98 (TODO: Missing a few dialogues)
 */
public class HouseServantDialogue extends DialoguePlugin {

	/**
	 * If using the sawmill.
	 */
	private boolean sawmill;

	/**
	 * The logs
	 */
	private Item logs;

	/**
	 * Constructs a new {@code ServantDialogue} {@code Object}.
	 */
	public HouseServantDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code ServantDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HouseServantDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HouseServantDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		HouseManager manager = player.getHouseManager();
		boolean inHouse = manager.isInHouse(player);
		if (args.length > 1) { //Parse options from our "use-with" handler
			sawmill = (boolean) args[1];
			logs = (Item) args[2];
		}
		if (player.getIronmanManager().checkRestriction(IronmanMode.ULTIMATE)) {
			player.sendMessage("Ultimate Ironmen cannot hire butlers.");
			return true;
		}
		if (!manager.hasHouse() && inHouse) {
			interpreter.sendDialogues(npc, npc.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You don't have a house that I can work in.", "I'll be waiting here if you decide to buy a house.");
			stage = 100;
			return true;
		}
		if (!manager.hasServant()) {
			ServantType type = ServantType.forId(npc.getId());
			if(player.getSkills().getLevel(Skills.CONSTRUCTION) >= type.getLevel()){
				interpreter.sendDialogues(npc, npc.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You're not aristocracy, but I suppose you'd do. Do you", "want a good cook for " + type.getCost() + " coins?");
				stage = 0;
				return true;
			} 
			interpreter.sendDialogues(npc, npc.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You need a Construction level of " + type.getLevel() + " and you must not", "currently have another person working for you", "in order to hire me.");
			stage = 100;
			return true;
		} else {
			Servant servant = manager.getServant();
			if(servant.getItem() == null){
				servant.setItem(new Item(0, 0));
			}
			if (inHouse) {
				follow(player, servant);
				if (sawmill) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Very well, I will take these logs to the mill and", "have them converted into planks.");
					stage = 110;
					return true;
				} 
				if (servant.getItem().getAmount() > 0) {
					if(player.getInventory().freeSlots() < 1){
						interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I have returned with what you asked me to", "retrieve. As I see your inventory is full, I shall wait", "with these " + servant.getItem().getAmount()+" items until you are ready.");
						stage = 100;
						return true;
					}
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I have returned with what you asked me to", "retrieve.");
					stage = 150;
					return true;
				}
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Yes, " + (player.getAppearance().isMale() ? "sir" : "ma'am") + "?", "You have " + (8 - servant.getUses()) + " uses of my services remaining.");
				stage = 50;
			} else if(npc.getId() != servant.getId()){
				interpreter.sendDialogues(npc, npc.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You already have someone working for you.", "Fire them first before hiring me.");
				stage = 100;
			}
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		HouseManager manager = player.getHouseManager();
		Servant servant = manager.getServant();
		ServantType type = ServantType.forId(npc.getId());
		switch(stage) {
		case 0:
			options("What can you do?", "Tell me about your previous jobs.", "You're hired!");
			stage++;
			break;
		case 1:
			switch(buttonId) {
			case 1: //TODO:
				break;
			case 2: //TODO:
				break;
			case 3:
				player("You're hired!");
				stage = 2;
				break;
			}
			break;
		case 2:
			interpreter.sendDialogues(npc, npc.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Alright, " + (player.getAppearance().isMale() ? "sir" : "ma'am") + ". I can start work immediately.");
			stage++;
			break;
		case 3:
			if (type != null && player.getInventory().getAmount(995) >= type.getCost() && player.getInventory().remove(new Item(995, type.getCost()))) {
				manager.setServant(new Servant(type));
				interpreter.sendDialogue("The servant heads to your house.");
				stage = 100;
				break;
			}
			interpreter.sendDialogue("You don't have enough money to pay the servant's hiring fee.");
			stage = 100;
			break;
		case 50:
			options("Go to the bank/sawmill...", "Misc...", "Stop following me.", "You're fired!");
			stage++;
			break;
		case 51:
			type = servant.getType();
			switch(buttonId) {
			case 1:
				options(!servant.getAttributes().containsKey("con:lastfetch") ? "Repeat last fetch task" : "Fetch another " + (type.getCapacity() + " x " + ((Item) servant.getAttribute("con:lastfetch")).getName().toLowerCase()) + " (" + (servant.getAttribute("con:lastfetchtype")) + ")", "Go to the bank", "Go to the sawmill", "Pay wages (" + servant.getUses()+"/8 uses)");
				stage++;
				break;
			case 2:
				options("Greet guests", "Cook me something");
				stage = 56;
				break;
			case 3:
				player("Stop following me.");
				if (servant.getPulseManager().isMovingPulse()) {
					servant.getPulseManager().clear("movement");
				}
				stage = 100;
				break;
			case 4:
				player("You're fired!");
				stage = 75;
				break;
			}
			break;
		case 52:
			switch(buttonId){
			case 1:
				if (!servant.getAttributes().containsKey("con:lastfetch")) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I haven't recently fetched anything from the bank or", "sawmill for you.");
					stage = 50;
					break;
				} else {
					if (servant.getAttribute("con:lastfetchtype") == "bank") {
						bankFetch(player, (Item) servant.getAttribute("con:lastfetch"));
						return true;
					}
					end();
					sawmillRun(player, (Item) servant.getAttribute("con:lastfetch"));
				}
				break;
			case 2:
				options("Planks", "Oak planks", "Teak planks", "Mahogany planks", "More options");
				stage = 60;
				break;
			case 3:
				if (type == ServantType.MAID || type == ServantType.RICK) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I am unable to travel to the sawmill for you.");
					stage = 100;
					break;
				}
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Hand the logs to me and I will take them to the", "sawmill for you.");
				stage = 100;
				break;
			case 4:
				stage = 100;
				if (servant.getUses() < 1) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You have no need to pay me yet, I haven't performed", "any of my services for you.");
					break;
				}
				if (!player.getInventory().containsItem(new Item(995, type.getCost()))) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Thanks for the kind gesture, but you don't have enough", "money to pay me. I require " + type.getCost() + " coins every eight uses", "of my services.");
					break;
				}
				if (player.getInventory().remove(new Item(995, type.getCost()))) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Thank you very much.");
					servant.setUses(0);
				}
				break;
			}
			break;
		case 56:
			switch(buttonId) {
			case 1:
				servant.setGreet(!servant.isGreet());
				player("Please " + (servant.isGreet() ? "greet" : "do not greet") + " all new guests upon arrival.");
				stage++;
				break;
			case 2:
				if (type.getFood() == null) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I don't know any recipes.");
					stage = 100;
					break;
				}
				if (type.getFood().length > 1) {
					options(type.getFood()[0].getName(), type.getFood()[1].getName(), "Nevermind.");
					stage = 58;
				} else {
					options(type.getFood()[0].getName(), "Nevermind.");
					stage = 59;
				}
				break;
			}
			break;
		case 57:
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Whatever you command.");
			stage = 50;
			break;
		case 58:
			if (player.getInventory().freeSlots() < 1) {
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I would love to share my fine cooking with you,", "but your hands are currently full.");
				stage = 100;
				break;
			}
			if (!prereqs(player, null, false)) {
				end();
				return true;
			}
			switch(buttonId) {
			case 1:
				player.getInventory().add(type.getFood()[0]);
				stage = 50;
				break;
			case 2:
				player.getInventory().add(type.getFood()[1]);
				stage = 50;
				break;
			case 3:
				player("Nevermind.");
				stage = 100;
				return true;
			}
			servant.setUses(servant.getUses() + 1);
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Luckily for you, I already have some made. Here you", "go.");
			stage = 50;
			break;
		case 59:
			if (player.getInventory().freeSlots() < 1) {
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I would love to share my fine cooking with", "you, but you have no space to take anything.");
				stage = 50;
				break;
			}
			if (!prereqs(player, null, false)) {
				end();
				return true;
			}
			switch(buttonId) {
			case 1:
				player.getInventory().add(type.getFood()[0]);
				stage = 100;
				break;
			case 2:
				player("Nevermind.");
				stage = 100;
				return true;
			}
			servant.setUses(servant.getUses() + 1);
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Luckily for you, I already have some made. Here you", "go.");
			stage = 100;
			break;
		case 60:
			switch(buttonId) {
			case 1: //planks
				bankFetch(player, new Item(960));
				stage = 100;
				break;
			case 2: //oak
				bankFetch(player, new Item(8778));
				stage = 100;
				break;
			case 3: //teak
				bankFetch(player, new Item(8780));
				break;
			case 4: //mahog
				bankFetch(player, new Item(8782));
				break;
			case 5:
				options("Soft clay", "Limestone bricks", "Steel bars", "Cloth", "More options");
				stage++;
				break;
			}
			break;
		case 61:
			switch(buttonId) {
			case 1: //clay
				bankFetch(player, new Item(1761));
				break;
			case 2: //lime
				bankFetch(player, new Item(3420));
				break;
			case 3: //steel bars
				bankFetch(player, new Item(2353));
				break;
			case 4: //cloth
				bankFetch(player, new Item(8790));
				break;
			case 5: 
				options("Gold leaves", "Marble blocks", "Magic stones");
				stage++;
				break;
			}
			break;
		case 62:
			switch(buttonId) {
			case 1: //leaves
				bankFetch(player, new Item(4692));
				break;
			case 2: //marble
				bankFetch(player, new Item(8786));
				break;
			case 3: //magic stones
				bankFetch(player, new Item(4703));
				break;
			}
			break;
		case 75:
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "Very well. I will return to the Guild of the Servants", "in Ardougne if you wish to re-hire me.");
			stage++;
			break;
		case 76:
			end();
			servant.getItem().setAmount(0);
			servant.setUses(0);
			servant.clear();
			servant.setLocation(new Location(0, 0));
			manager.setServant(null);
			break;
		case 100:
			end();
			break;
		case 110:
			end();
			sawmillRun(player, logs);
			break;
		case 150:
			if (servant.getItem()== null) {
				end();
				return true;
			}
			int amtLeft = servant.getItem().getAmount();
			boolean flag = false;
			if (amtLeft < 1 || servant.getItem() == null) {
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I don't have any items left.");
				stage = 100;
				break;
			}
			if (amtLeft > player.getInventory().freeSlots()) {
				amtLeft = player.getInventory().freeSlots();
				flag = true;
			}
			servant.getItem().setAmount(servant.getItem().getAmount() - amtLeft);
			player.getInventory().add(new Item(servant.getItem().getId(), amtLeft));
			if (flag) {
				interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I still have " + servant.getItem().getAmount() + " left for you to take from me.");
				stage = 100;
			} else{
				end();
			}
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4235, 4237, 4239, 4241, 4243 };
	}

	/**
	 * Checks the pre-reqs for a sawmill run or a bank run.
	 * @param player
	 * @param item
	 * @return true or false if they have the requirements to use the servant.
	 */
	private boolean prereqs(final Player player, final Item item, boolean sawmill) {
		HouseManager manager = player.getHouseManager();
		Servant servant = manager.getServant();
		if (!sawmill && player.getInventory().freeSlots() < 1) {
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You don't have any space in your inventory.");
			stage = 100;
			return false;
		}
		if (servant.getUses() >= 8) {
			player.sendMessage("<col=CC0000>The servant has left your service due to a lack of payment.</col>");
			servant.getItem().setAmount(0);
			servant.setUses(0);
			servant.clear();
			servant.setLocation(new Location(0, 0));
			manager.setServant(null);
			end();
			return false;
		}
		if (servant.getItem().getAmount() > 0) {
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You can't send me off again, I'm still holding some of", "your previous items.");
			return false;
		}
		return true;
	}

	/**
	 * Goes to the sawmill.
	 * @param player
	 * @param item
	 */
	private void sawmillRun(final Player player, final Item item) {
		HouseManager manager = player.getHouseManager();
		final Servant servant = manager.getServant();
		final ServantType type = manager.getServant().getType();
		if (servant == null || item == null || !prereqs(player, item, true)) {
			return;
		}
		if (type == ServantType.MAID || type == ServantType.RICK) {
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "I am unable to take planks to the sawmill.");
			return;
		}
		int amt = player.getInventory().getAmount(item);
		if (amt < 1) {
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You don't have any more of that type of log.");
			return;
		}
		for (Plank plank : Plank.values()) {
			if (plank.getLog().getId() == item.getId()) {
				if (amt > type.getCapacity()) {
					amt = type.getCapacity();
				}
				if (!player.getInventory().contains(995, plank.getPrice() * amt)) {
					interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You don't have enough coins for me to do that.", "I can hold " + type.getCapacity() + " logs and each of this type of log", "costs " + plank.getPrice() + " coins each to convert into plank form.");
					return;
				}
				end();
				if (player.getInventory().remove(new Item(item.getId(), amt)) && player.getInventory().remove(new Item(995, amt * plank.getPrice()))) {
					manager.getServant().setItem(new Item(plank.getPlank().getId(), amt));
					servant.setInvisible(true);
					servant.getLocks().lockMovement(100);
					GameWorld.submit(new Pulse((int) (type.getTimer() / 0.6)){

						@Override
						public boolean pulse() {
							servant.setInvisible(false);
							servant.getLocks().unlockMovement();
							servant.setAttribute("con:lastfetch", new Item(item.getId(), 1));
							servant.setAttribute("con:lastfetchtype", "sawmill");
							interpreter.open(servant.getId(), servant);
							return true;
						}
					});
				}
				break;
			}
		}
	}

	/**
	 * Gets items from the player's bank.
	 * @param player
	 * @param item
	 */
	private void bankFetch(final Player player, final Item item) {
		final HouseManager manager = player.getHouseManager();
		final Servant servant = manager.getServant();
		final ServantType type = manager.getServant().getType();
		if (servant == null || item == null || !prereqs(player, item, false)) {
			return;
		}
		if (!player.getBank().containsItem(item)) {
			interpreter.sendDialogues(servant, servant.getId() == 4243 ? FacialExpression.NO_EXPRESSION : null, "You don't seem to have any of those in the bank.");
			stage = 100;
			return;
		}
		end();
		servant.setInvisible(true);
		servant.getLocks().lockMovement(100);
		GameWorld.submit(new Pulse((int) (type.getTimer() / 0.6)) {

			@Override
			public boolean pulse() {
				if (player == null || player.getHouseManager().getRegion() != player.getViewport().getRegion()) { //TODO: Check if in dungeon?
					return true;
				}
				int amt = player.getBank().getAmount(item.getId());
				if (amt < 1) {
					return true;
				}
				if (amt > type.getCapacity()) {
					amt = type.getCapacity();
				}
				servant.setInvisible(false);
				servant.getLocks().unlockMovement();
				Item fetch = new Item(item.getId(), amt);
				if (player.getBank().remove(fetch)) {
					manager.getServant().setItem(fetch);
					interpreter.open(servant.getId(), servant);
				}
				servant.setAttribute("con:lastfetch", new Item(fetch.getId(), 1));
				servant.setAttribute("con:lastfetchtype", "bank");
				manager.getServant().setUses(manager.getServant().getUses() + 1);
				follow(player, servant);
				return true;
			}

		});
	}

	/**
	 * Follows the person who is talking to them.
	 * @param player
	 * @param npc
	 */
	private void follow(Player player, NPC npc) {
		npc.getPulseManager().run(new MovementPulse(npc, player, Pathfinder.SMART) {
			
			@Override
			public boolean pulse() {
				return false;
			}
		}, "movement");
	}
}