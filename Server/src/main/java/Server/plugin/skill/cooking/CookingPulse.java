/*
package plugin.skill.cooking;

import org.crandor.game.content.global.SkillcapePerks;
import plugin.tutorial.TutorialSession;
import plugin.tutorial.TutorialStage;
import plugin.skill.SkillPulse;
import plugin.skill.Skills;
import org.crandor.game.node.entity.impl.Animator;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.audio.Audio;
import org.crandor.game.node.entity.player.link.diary.DiaryType;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.update.flag.context.Animation;

import static org.crandor.tools.RandomFunction.RANDOM;

*/
/**
 * Pulse for cooking
 * @author ceik
 *//*

public final class CookingPulse extends Pulse {
	private static Player player;

	private static GameObject node;

	private static boolean intentionalBurn = false;

	//range animation
	private static final Animation RANGE_ANIMATION = new Animation(883, Animator.Priority.HIGH);

	//fire animation
	private static final Animation FIRE_ANIMATION = new Animation(897, Animator.Priority.HIGH);

	//Cooking sound
	public static final Audio SOUND = new Audio(2393, 1, 1);

	//food's ID
	private final int food;

	//amount to cook
	private int amount;

	//whether or not we are cooking food
	private boolean isFood = true;

	*/
/**
	 * Constructs a new {@code CookingPulse} {@code Object}.
	 * @param player the player.
	 * @param node the object node (fire/range)
	 * @param food the food to cook.
	 * @param amount the amount to cook
	 *//*

	public CookingPulse(Player player, GameObject node, final int food, final int amount) {
		this.player = player;
		this.node = node;
		this.food = food;
		this.amount = amount;
	}

	*/
/**
	 * Another constructor for the Cooking Pulse, this time flags it as an intentional burn
	 * @param player the player cooking
	 * @param node the object being used to cook (range/fire)
	 * @param food the item being cooked
	 * @param intentionalBurn whether or not it's an intentional burn
	 *//*

	public CookingPulse(Player player, GameObject node, final int food, final boolean intentionalBurn) {
		this.player = player;
		this.food = food;
		this.amount = 1;
		this.intentionalBurn = intentionalBurn;
	}

	*/
/**
	 * handler for cooking non-food items
	 * @param player the player cooking
	 * @param item the item being cooked
	 * @param amount the amount the player has
	 * @param isFood whether we are cooking food
	 *//*

	public CookingPulse(Player player, final int item, int amount, int product, final boolean isFood){
		this.player = player;
		this.food = item;
		this.amount = amount;
		this.isFood = isFood;
	}

	@Override
	public void start() {
		if (intentionalBurn || checkRequirements()) {
			super.start();
		}
	}

	@Override
	public boolean pulse() {
		if(intentionallyBurn(food) || !checkRequirements()){
			return true;
		}
		return reward();
	}

	public boolean checkRequirements() { //check if the player has the requirements
		if (!node.isActive()) {
			return true;
		}
		//handle cook's assistant range
		if(node.getId() == 114 && !player.getQuestRepository().isComplete("Cook's Assistant")){
			player.getPacketDispatch().sendMessage("You need to have completed the Cook's Assistant quest in order to use that range.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.COOKING) < CookableItems.forId(food).level) {
			player.getDialogueInterpreter().sendDialogue("You need a cooking level of " + CookableItems.forId(food).level + " to cook this.");
			return false;
		}
		int inventoryAmount = player.getInventory().getAmount(CookableItems.forId(food).raw);
		if (amount > inventoryAmount) {
			amount = inventoryAmount;
		}
		if (amount < 1) {
			return false;
		}
		return true;
	}

	public void animate() {
		player.animate(getAnimation(node));
	}

	public boolean reward() {
		if (getDelay() == 1) {
			setDelay(node.getName().toLowerCase().equals("range") ? 5 : 4);
			return false;
		}
		//handle tutorial stuff
		if(!TutorialSession.getExtension(player).finished()){
			updateTutorial(player);
			amount --;
			return true;
		}

		if (cook(player,node,isBurned(player,node,food),food)) {
			amount--;
		} else {
			return true;
		}
		return amount < 1;
	}

	public boolean cook(final Player player, final GameObject object, final boolean burned, final int food) {
		Item item = CookableItems.getRaw(food);
		if(intentionalBurn){ //stops the cook function from being ran if we burning on purpose
			return false;
		}
		//lumbridge diary
		if (CookableItems.forId(food) != null && player.getInventory().remove(item)) {
			if (!burned) {
				player.getInventory().add(CookableItems.getCooked(food));
			} else {
				player.getInventory().add(CookableItems.getBurnt(food));
			}
			player.getSkills().addExperience(Skills.COOKING, burned ? 0 : CookableItems.forId(food).experience, true);
			player.getPacketDispatch().sendMessage(getMessage(item, burned));
			player.getAudioManager().send(SOUND);
			return true;
		}
		return false;
	}

	public boolean intentionallyBurn(int food){
		if(intentionalBurn && player.getInventory().remove(new Item(food))){ //only runs if it's flagged as intentional burning
			player.getInventory().add(CookableItems.getIntentionalBurn(food));
			player.getPacketDispatch().sendMessage("You deliberately burn the perfectly good piece of meat.");
			return true;
		} else {
			return false;
		}
	}

	public String getMessage(Item food, boolean burned){
		if(!burned) {
			return "You manage to cook some " + food.getName().replace("Raw ", "");
		} else {
			return "You accidentally burn some " + food.getName().replace("Raw ","");
		}
	}

	public boolean isBurned(final Player player, final GameObject object, int food) {
		double burn_stop = (double)CookableItems.getBurnLevel(food);
		if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.COOKING)) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.COOKING) > burn_stop) {
			return false;
		}
		double burn_chance = 60.0 + (object.getName().equals("fire") ? 1.00 : 0) - (object.getId() == 114 ? 1.00 : 0);
		double cook_level = (double) player.getSkills().getLevel(Skills.COOKING);
		double lev_needed = (double) CookableItems.forId(food).level;
		double multi_a = (burn_stop - lev_needed);
		double burn_dec = (burn_chance / multi_a);
		double multi_b = (cook_level - lev_needed);
		burn_chance -= (multi_b * burn_dec);
		double randNum = RANDOM.nextDouble() * 100.0;
		return !(burn_chance <= randNum);
	}

	public boolean updateTutorial(Player player){
		if (TutorialSession.getExtension(player).getStage() == 14) {
			TutorialStage.load(player, 15, false);
			return cook(player, node,true, food);
		} else if (TutorialSession.getExtension(player).getStage() == 15) {
			TutorialStage.load(player, 16, false);
			return cook(player, node, false, food);
		}
		if (TutorialSession.getExtension(player).getStage() == 20) {
			TutorialStage.load(player, 21, false);
			return cook(player, node, false, food);
		}
		return cook(player, node, isBurned(player,node,food),food);
	}

	*/
/**
	 * Gets the animation to use based on the interacting object.
	 * @param object the object.
	 * @return the animation.
	 *//*

	private final Animation getAnimation(final GameObject object) {
		return !object.getName().toLowerCase().equals("fire") ? RANGE_ANIMATION : FIRE_ANIMATION;
	}
}
*/
