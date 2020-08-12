package plugin.ai.pvp;

import java.util.List;

import core.game.container.impl.EquipmentContainer;
import plugin.consumable.Consumable;
import plugin.consumable.Consumables;
import plugin.consumable.Food;
import plugin.consumable.effects.HealingEffect;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import plugin.ai.AIPlayer;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.path.Pathfinder;
import core.game.world.update.flag.context.ChatMessage;
import core.game.world.update.flag.player.ChatFlag;
import core.tools.RandomFunction;

public class PVPAIPActions {
	
	public static List<AIPlayer> pvp_players = null;
	
	private static final String[] trashMessages = {
			"I'll do your bloody nan in m8",
			"Your sister smells like a tuna sandwich",
			"You suck at bridding bro lmao",
			"Gf your bank :)",
			"Just delete your cache ur bad",
			"FFS mate get wrekt",
			"Dylan is a better pkr than u, kid..l0l"


	};
	
	private static final String[] safeMessages = {
			"Safe up n00b",
			"No safe",
			"Get rekt m8",
			"Stop fkin eating scrub",
			"Dw, you're gunna die anyhow.. :)",
			"Either ur eating shrimps or I'm hitting high asf",
			"Once i kill u wanna rematch?",
			
	};
	
	public static void syncBotThread(final Player player) {
		if (pvp_players == null || pvp_players.size() == 0) {
			return;
		}
		for (int aip_index = 0; aip_index < pvp_players.size(); aip_index++) {
			final AIPlayer bot = pvp_players.get(aip_index);
			final AIPlayer[] target = {
					pvp_players.get(RandomFunction.getRandom(pvp_players.size() - 1))
			};
			bot.getProperties().setRetaliating(true);
			bot.setAttribute("dead", false);
			GameWorld.Pulser.submit(new Pulse(1, bot) {
				int ticks;
				@Override
				public boolean pulse() {
					if (bot.getAttribute("dead", true)) {
						AIPlayer.deregister(bot.getUid());
						pvp_players.remove(bot);
						return true;
					}
					if (!bot.getInventory().contains(385, 1)) {
						flee(bot);
						return true;
					}
					//Inactivity logout timer.
					if (ticks++ == 5000 && !bot.getProperties().getCombatPulse().isInCombat() && !bot.getProperties().getCombatPulse().isAttacking() && bot.getWalkingQueue().getQueue().isEmpty()) {
						AIPlayer.deregister(bot.getUid());
						pvp_players.remove(bot);
						return true;
					}
					if (!pvp_players.contains(target) || !Pathfinder.find(bot, target[0]).isSuccessful() || !canAttack(bot, target[0])) {
						target[0] = pvp_players.get(RandomFunction.getRandom(pvp_players.size() - 1));
					}
					attackTarget(player, bot, target[0]);
					//prayer(bot, target[0]);
					eat(bot, target[0]);
					return false;
				}
			});
		}
	}

	
	private static void attackTarget(Player player, AIPlayer bot, AIPlayer target) {
		//If bots target is not himself we continue.
		if (bot != target) {
			//If they are in combat or attacking:
			if (bot.getProperties().getCombatPulse().isInCombat() || bot.getProperties().getCombatPulse().isAttacking()) {
				//TODO: Pray
				Item weapon = bot.getEquipment().get(EquipmentContainer.SLOT_WEAPON);
				if (RandomFunction.getRandom(25) == 1) {
					if(bot.getSettings().getSpecialEnergy() == 100) { //TODO: Change 100 to their weielded weapons spec max amount
						bot.getSettings().toggleSpecialBar();
					}
				}
			} else {
				if (RandomFunction.getRandom(25) == 1) {
					bot.attack(player);
				} else {
					//If a 1/50 chance is true, the attacker screams his opponents name hoping his team will dogpile him.
					int chatRandom = RandomFunction.getRandom(50);
					switch(chatRandom) {
					case 1:
						String target_name = target.getName().substring(0, 1).toUpperCase() + target.getName().substring(1);
						bot.getUpdateMasks().register(new ChatFlag(new ChatMessage(bot, target_name, 0, 0)));
						break;
					case 2:
						bot.getUpdateMasks().register(new ChatFlag(new ChatMessage(bot, trashMessages[RandomFunction.getRandom(trashMessages.length - 1)], 0, 0)));
						break;
					}
					bot.attack(target);
				}
			}
		}
	}
	
	/**
	 * A basic flee method to run them to another part of the wilderness.
	 * @param bot
	 * TODO: Possibly make them run away and hop the ditch (I did not do this because we have no other actions than pking at the moment).
	 */
	private static void flee(final AIPlayer bot) {
		bot.sendChat("Outta food m8! Off!");
		bot.sendChat("Ahh!");
		bot.sendChat("GTFO M8");
		bot.sendChat("Someone spec him!");
		GameWorld.Pulser.submit(new Pulse(1, bot) {
			int ticks;
			@Override
			public boolean pulse() {
				switch(ticks++) {
				case 1:
					bot.getProperties().getCombatPulse().stop();
					bot.getProperties().getCombatPulse().setNextAttack(RandomFunction.getRandom(10));
					Pathfinder.find(bot, bot.getLocation().transform(RandomFunction.random(-100, 100), RandomFunction.random(-100, 100), 0), false, Pathfinder.SMART).walk(bot);
					break;
				case 500:
					AIPlayer.deregister(bot.getUid());
					pvp_players.remove(bot);
					return true;
				}
				if (bot.getAttribute("dead", true)) {
					AIPlayer.deregister(bot.getUid());
					pvp_players.remove(bot);
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * Handles the eating of the AI player bots.
	 * @param bot
	 * TODO: Possibly add more food choices.
	 */
	private static void eat(Player bot, AIPlayer target) {
		Item shark = new Item(385);
		//385 is shark, you can add more from there easier.
		if((bot.getSkills().getStaticLevel(Skills.HITPOINTS) > bot.getSkills().getLifepoints() * 3) && bot.getInventory().containsItem(shark)) {
			bot.lock(3);
			Item food = bot.getInventory().getItem(shark);
			Consumable consumable = Consumables.getConsumableById(food.getId());
			if (consumable == null) {
				consumable = new Food(new int[] {food.getId()}, new HealingEffect(1));
			}
			consumable.consume(food, bot);
			bot.getProperties().getCombatPulse().delayNextAttack(3);
			int chatRandom = RandomFunction.getRandom(50);
			if (chatRandom == 1) {
				bot.getUpdateMasks().register(new ChatFlag(new ChatMessage(bot, safeMessages[RandomFunction.getRandom(safeMessages.length - 1)], 0, 0)));
			}
		}
	}

	private static boolean canAttack(Player p, Player t) {
		int level = p.getSkullManager().getLevel();
		if (t.getSkullManager().getLevel() < level) {
			level = t.getSkullManager().getLevel();
		}
		int combat = p.getProperties().getCombatLevel();
		int targetCombat = t.getProperties().getCombatLevel();
		if (combat - level > targetCombat || combat + level < targetCombat) {
			return false;
		}
		return true;
	}
	
}
