package plugin.interaction.npc;

import java.util.ArrayList;
import java.util.List;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.content.global.shop.Shop;
import org.crandor.game.content.skill.member.summoning.pet.Pets;
import org.crandor.game.interaction.NodeUsageEvent;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.interaction.UseWithHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Handles pet insurance via the NPC Probita.
 * @author Empathy
 *
 */
@InitializablePlugin
public class ProbitaNPC extends OptionHandler {

	/**
	 * The id of Probita.
	 */
	public static final int PROBITA = 8652;

	/**
	 * The item ids of the pets that can have insurance.
	 */
	public static final int[] PETS = new int[] { 14654, 14655, 14656, 14649, 1639, 14640, 14641, 14638, 14642, 14647, 14648, 14646, 14645, 14828, 14650, 14653, 14658, 14661, 14657, 14659, 14651, 14823, 14821, 14822, 14827 };

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(PROBITA).getConfigurations().put("option:check", this);
		PluginManager.definePlugin(new ProbitaDialogue());
		PluginManager.definePlugin(new ProbitaUseWithHandler());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		configureShop(player);
		return true;
	}

	/**
	 * Configures the pet insurance shop.
	 * @param player the player
	 * @return true if succesfully configured.
	 */
	public boolean configureShop(Player player) {
		if (player.getFamiliarManager().getInsuredPets().size() > 0) {
			List<Item> list = new ArrayList<>();
			Item item;
			for (int i = 0; i < player.getFamiliarManager().getInsuredPets().size(); i++) {
				item = new Item(player.getFamiliarManager().getInsuredPets().get(i).getBabyItemId());
				if (player.hasItem(item)) {
					continue;
				}
				if (player.getFamiliarManager().hasFamiliar()) {
					if (player.getFamiliarManager().getFamiliar().getId() == player.getFamiliarManager().getInsuredPets().get(i).getBabyNpcId()) {
						continue;
					}
				}
				list.add(item);				
			}
			if (list.size() > 0) {
				Shop petShop = new Shop("Pet insurance", list.toArray(new Item[list.size()]), false, 995, false, false, 1000000) {
					@Override
					public boolean canBuy(Player player, Item item, int price, Item currency) {
						if (player.hasItem(item)) {
							return true;
						}
						if (player.getFamiliarManager().getFamiliar() != null && player.getFamiliarManager().getFamiliar().getId() == Pets.forId(item.getId()).getBabyNpcId()) {
							return true;
						}
						return super.canBuy(player, item, price, currency);
					}
				};
				petShop.open(player);
				return true;
			} else {
				player.sendMessage("You are not missing any pets.");
				return false;
			}
		}
		player.sendMessage("You have not insured any pets.");
		return false;
	}

	/**
	 * Handles the reward of a pet used on the npc probita.
	 * @author Vexia
	 *
	 */
	public class ProbitaUseWithHandler extends UseWithHandler {

		/**
		 * Constructs a new @{Code ProbitaUseWithHandler} object.
		 */
		public ProbitaUseWithHandler() {
			super(PETS);
		}

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			addHandler(PROBITA, NPC_TYPE, this);
			return this;
		}

		@Override
		public boolean handle(NodeUsageEvent event) {
			final Player player = event.getPlayer();
			Pets pet = Pets.forId(event.getUsedItem().getId());
			if (pet == null) {
				return true;
			}
			player.getDialogueInterpreter().open(PROBITA, event.getUsedWith(), pet);
			return true;
		}

	}

	/**
	 * Handles the Probita dialogue.
	 * @author Empathy
	 *
	 */
	public class ProbitaDialogue extends DialoguePlugin {

		/**
		 * The pet to be insured.
		 */
		private Pets pet;

		/**
		 * Constructs a new {@code ProbitaDialogue} {@code Object}.
		 */
		public ProbitaDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code ProbitaDialogue} {@code Object}.
		 * 
		 * @param player the player.
		 */
		public ProbitaDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new ProbitaDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			npc = (NPC) args[0];
			if (args.length > 1) {
				pet = (Pets) args[1];
				if (pet == null) {
					return true;
				}
				npc("Would you like to insure your " + ItemDefinition.forId(pet.getBabyItemId()).getName() + " for", "500,000 gold coins?");
				stage = 60;
				return true;
			}
			interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Welcome to the pet insurance bureau.", "How can I help you?");
			stage = 1;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 1:
				options("Tell me about pet insurance.", "I've lost a pet. Have you got it?", "I have a pet that I'd like to insure.", "What pets have I insured?", "Maybe another time.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Tell me about pet insurance.");
					stage = 10;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I've lost a pet. Have you got it?");
					stage = 20;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I have a pet that I'd like to insure.");
					stage = 30;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What pets have I insured?");
					stage = 40;
					break;
				case 5:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Maybe another time.");
					stage = 50;
					break;
				}
				break;
			case 10:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_LAUGH3, "It's simple. You show me your pet, and pay an", "insurance fee of 500,000 coins. From that moment, I'll", "watch out for that pet");
				stage = 11;
				break;
			case 11:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Every time you lose it or dismiss it in future, I'll catch", "the pet and keep it safely here. You can collect it each", "time for a reclamation fee of 1,000,000 coins.");
				stage = 12;
				break;
			case 12:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "The insurance fee is a one-off charge; once you've", "insured a pet, it stays insured no matter how many", "times you lose it and reclaim it. You just have to pay", "the reclamation fee each time.");
				stage = 13;
				break;
			case 13:
				options("I've lost a pet. Have you got it?", "I have a pet that I'd like to insure.", "What pets have I insured?", "Maybe another time.");
				stage = 14;
				break;
			case 14:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I've lost a pet. Have you got it?");
					stage = 20;
					break;
				case 2:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "I have a pet that I'd like to insure.");
					stage = 30;
					break;
				case 3:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "What pets have I insured?");
					stage = 40;
					break;
				case 4:
					interpreter.sendDialogues(player, FacialExpression.NO_EXPRESSION, "Maybe another time.");
					stage = 50;
					break;
				}
				break;
			case 20:
				if (player.getFamiliarManager().getInsuredPets().size() > 0) {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Let me check for you.");
					stage = 21;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "I'm afraid you don't seem to have insured any pets, so", "I can't help you.");
					stage = 50;
				}
				break;
			case 21:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, configureShop(player) ? "You are missing some pets, you can claim them now." : "You are not missing any pets." );
				stage = 50;
				break;
			case 30:
				interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Great! The insurance fee is 500,000 coins. Just hand", "me the pet so I can register it.");
				stage = 50;
				break;
			case 40:
				if (player.getFamiliarManager().getInsuredPets().size() > 0) {
					end();
					List<String> pets = new ArrayList<>();
					for (int i = 0; i < player.getFamiliarManager().getInsuredPets().size(); i++) {
						pets.add(NPCDefinition.forId(player.getFamiliarManager().getInsuredPets().get(i).getBabyNpcId()).getName());
					}
					String[] petNames = pets.toArray(new String[pets.size()]);
					//Add all the pet names you've insured to 1 string, for later use in the dialogue.
					String name = "";
					//TODO: Figure a way to make it work with dialogue, currently it sends a game message.
					player.sendNotificationMessage("You have insured the following pets: ");
					for (String s : petNames) {
						//This adds all the pet names to 1 string. currently sends game messages.
						name = name == "" ? s : name + ", " + s;
						player.sendNotificationMessage(s);
					}
					break;
				} else {
					interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "I am afraid you don't seem to have insured any pets at", "all.");
					stage = 50;
					break;
				}
			case 50:
				end();
				break;
			case 60:
				options("Yes, please.", "No thank you.");
				stage++;
				break;
			case 61:
				switch (buttonId) {
				case 1:
					player("Yes, please");
					stage++;
					break;
				case 2:
					player("No thank you.");
					stage = 50;
					break;
				}
				break;
			case 62:
				if (pet == null) {
					end();
					break;
				}
				if (hasInsuredPet(pet, player)) {
					npc("You've already insured this pet.");
					stage = 50;
					break;
				}
				if (!player.getInventory().contains(995, 500000)) {
					npc("I'm sorry, but you don't have enough gold coins", "in order to insure this pet.");
					stage = 50;
					break;
				}
				if (player.getInventory().remove(new Item(995, 500000))) {
					player.getFamiliarManager().getInsuredPets().add(pet);
					interpreter.sendItemMessage(pet.getBabyItemId(), "Your pet is now insured", "You can reclaim it from Probita if you lose it.");
					stage = 50;
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { PROBITA };
		}

	}
	
	/**
	 * Checks if a pet is already insured.
	 * @param pet the pet to check.
	 * @param player the player.
	 * @return true if the player has insured that pet.
	 */
	private boolean hasInsuredPet(Pets pet, Player player) {
		for (Pets p : player.getFamiliarManager().getInsuredPets()) {
			if (p != null) {
				NPCDefinition npc = NPCDefinition.forId(p.getBabyNpcId());
				NPCDefinition npc1 = NPCDefinition.forId(pet.getBabyNpcId());
				if (npc.getName().equalsIgnoreCase(npc1.getName())) {
					return true;
				}
			}
		}
		return false;
	}

}
