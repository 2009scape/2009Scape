package org.crandor.game.content.skill.member.slayer;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A non-garbage way of representing slayer masters
 * @author ceik
 */
public enum Master {
	TURAEL(8273, 0, 0, new int[]{15, 50}, new int[]{0, 0, 0},
			new Task(Tasks.BANSHEE, 8),
			new Task(Tasks.CRAWLING_HAND, 8),
			new Task(Tasks.SKELETONS, 7),
			new Task(Tasks.WOLVES, 7),
			new Task(Tasks.GOBLINS, 7),
			new Task(Tasks.DOG, 7),
			new Task(Tasks.CAVE_SLIMES, 8),
			new Task(Tasks.KALPHITES, 6),
			new Task(Tasks.ICE_FIENDS, 8),
			new Task(Tasks.MINOTAURS, 7),
			new Task(Tasks.CAVE_CRAWLERS, 8),
			new Task(Tasks.GHOSTS, 7),
			new Task(Tasks.BEARS, 7),
			new Task(Tasks.BATS, 7),
			new Task(Tasks.ZOMBIES, 7),
			new Task(Tasks.SCORPIONS, 7),
			new Task(Tasks.CAVE_BUG, 8),
			new Task(Tasks.DESERT_LIZARDS, 8),
			new Task(Tasks.DWARF, 7),
			new Task(Tasks.SPIDERS, 6),
			new Task(Tasks.BIRDS, 6),
			new Task(Tasks.COWS, 8),
			new Task(Tasks.MONKEYS, 6),
			new Task(Tasks.RATS, 7)),
	MAZCHNA(8274, 20, 0, new int[]{30, 70}, new int[]{2, 5, 15},
			new Task(Tasks.VAMPIRES, 6),
			new Task(Tasks.GHOULS, 7),
			new Task(Tasks.BANSHEE, 8),
			new Task(Tasks.CRAWLING_HAND, 8),
			new Task(Tasks.SKELETONS, 7),
			//MOGRES -> new Task(Tasks2.Mogres, 8),
			new Task(Tasks.PYREFIENDS, 8),
			//KILLERWATTS -> new Task(Tasks2.KILLERWATTS, 6),
			new Task(Tasks.SHADE,8),
			new Task(Tasks.COCKATRICES, 8),
			new Task(Tasks.HILL_GIANTS,7),
			new Task(Tasks.ROCK_SLUGS,8),
			new Task(Tasks.DESERT_LIZARDS, 8),
			new Task(Tasks.CAVE_SLIMES, 8),
			new Task(Tasks.HOBGOBLINS, 7),
			new Task(Tasks.WOLVES, 7),
			new Task(Tasks.DOG,7),
			new Task(Tasks.FLESH_CRAWLERS,7),
			new Task(Tasks.CATABLEPONS,8),
			//WALL BEASTS -> new Task(Tasks2.WALL_BEASTS,7),
			new Task(Tasks.KALPHITES,6),
			new Task(Tasks.CAVE_CRAWLERS, 8),
			new Task(Tasks.GHOSTS,7),
			new Task(Tasks.BEARS,6),
			new Task(Tasks.EARTH_WARRIORS,6),
			new Task(Tasks.ZOMBIES,7),
			new Task(Tasks.ICE_WARRIOR, 7),
			new Task(Tasks.CAVE_BUG,8),
			new Task(Tasks.BATS,7),
			new Task(Tasks.SCORPIONS,7)),
	VANNAKA(1597, 40, 0, new int[]{30, 80}, new int[]{4, 20, 60},
			new Task(Tasks.HARPIE_BUG_SWARMS,8),
			// SHADOW WARRIORS -> new Task(Tasks2.SHADOW_WARRIORS, 8),
			new Task(Tasks.BLOODVELDS,8),
			new Task(Tasks.DUST_DEVILS,8),
			new Task(Tasks.ABERRANT_SPECTRES, 8),
			new Task(Tasks.JUNGLE_HORRORS, 8),
			new Task(Tasks.TUROTHS, 8),
			new Task(Tasks.JELLIES,8),
			new Task(Tasks.INFERNAL_MAGES,8),
			new Task(Tasks.BASILISKS,8),
			new Task(Tasks.PYREFIENDS,8),
			new Task(Tasks.SHADE,8),
			new Task(Tasks.COCKATRICES,8),
			new Task(Tasks.OTHERWORDLY_BEING,8),
			new Task(Tasks.SPIRTUAL_MAGES,3),
			new Task(Tasks.SPIRTUAL_RANGERS, 3),
			new Task(Tasks.SPIRTUAL_WARRIORS,3),
			new Task(Tasks.DAGANNOTHS, 7),
			//ELVES -> new Task(Tasks2.ELVES, 7),
			new Task(Tasks.WEREWOLFS,7),
			new Task(Tasks.KURASKS,7),
			new Task(Tasks.KALPHITES,7),
			new Task(Tasks.FIRE_GIANTS,7),
			new Task(Tasks.OGRES,7),
			new Task(Tasks.MOSS_GIANTS,7),
			new Task(Tasks.TROLLS,7),
			new Task(Tasks.HELLHOUNDS,7),
			new Task(Tasks.BRONZE_DRAGONS,7),
			new Task(Tasks.BLUE_DRAGONS,7),
			new Task(Tasks.LESSER_DEMONS,7),
			new Task(Tasks.ANKOU,7),
			new Task(Tasks.ICE_GIANTS,7),
			new Task(Tasks.BRINE_RATS,7),
			//FEVER SPIDERS -> new Task(Tasks2.FEVER_SPIDERS,7),
			new Task(Tasks.VAMPIRES,7),
			//MOLANISKS -> new Task(Tasks2.MOLANISKS,7),
			//MOGRES -> new Task(Tasks2.MOGRES,7),
			new Task(Tasks.ICE_WARRIOR,7),
			new Task(Tasks.GHOULS,7),
			new Task(Tasks.DESERT_LIZARDS,7),
			new Task(Tasks.ROCK_SLUGS,7),
			new Task(Tasks.CAVE_SLIMES,7),
			new Task(Tasks.CAVE_CRAWLERS,7),
			new Task(Tasks.CAVE_BUG,7),
			new Task(Tasks.HILL_GIANTS,7),
			new Task(Tasks.HOBGOBLINS,7),
			//KILLERWATTS -> new Task(Tasks2.KILLERWATTS,6),
			new Task(Tasks.CROCODILES,6),
			//SEA SNAKES -> new Task(Tasks2.SEA_SNAKES,6),
			//TERROR DOGS -> new Task(Tasks2.TERROR_DOGS,6),
			new Task(Tasks.GREEN_DRAGON,6),
			//WALL BEAST -> new Task(Tasks2.WALL_BEAST,6),
			new Task(Tasks.EARTH_WARRIORS,6),
			new Task(Tasks.BANSHEE,6),
			new Task(Tasks.CRAWLING_HAND,6),
			new Task(Tasks.GARGOYLES, 5),
			new Task(Tasks.NECHRYAELS, 5),
			new Task(Tasks.ABYSSAL_DEMONS, 5)),

	CHAELDAR(1598, 70, 25, new int[]{110, 170}, new int[]{10, 50, 150},
			new Task(Tasks.ABYSSAL_DEMONS,12),
			new Task(Tasks.IRON_DRAGONS,12),
			new Task(Tasks.NECHRYAELS, 12),
			new Task(Tasks.KURASKS, 12),
			new Task(Tasks.FIRE_GIANTS, 12),
			new Task(Tasks.SPIRTUAL_WARRIORS,4),
			new Task(Tasks.SPIRTUAL_RANGERS,4),
			new Task(Tasks.SPIRTUAL_MAGES,4),
			new Task(Tasks.GARGOYLES,11),
			new Task(Tasks.KALPHITES,11),
			new Task(Tasks.DAGANNOTHS,11),
			new Task(Tasks.BRONZE_DRAGONS,11),
			new Task(Tasks.TROLLS,11),
			new Task(Tasks.JUNGLE_HORRORS,10),
			new Task(Tasks.BLACK_DEMONS,10),
			new Task(Tasks.TUROTHS, 10),
			new Task(Tasks.CAVE_HORRORS,10),
			new Task(Tasks.JELLIES, 10),
			new Task(Tasks.AVIANSIES,9),
			new Task(Tasks.STEEL_DRAGONS,9),
			new Task(Tasks.LESSER_DEMONS,9),
			new Task(Tasks.GREATER_DEMONS,9),
			new Task(Tasks.HELLHOUNDS,9),
			new Task(Tasks.DUST_DEVILS,9),
			new Task(Tasks.BLUE_DRAGONS,8),
			//ELVES -> new Task(Tasks2.ELVES,8),
			new Task(Tasks.ABERRANT_SPECTRES,8),
			//SHADOW WARRIORS -> new Task(Tasks2.SHADOW_WARRIORS,8),
			new Task(Tasks.BLOODVELDS,8),
			new Task(Tasks.SKELETAL_WYVERN,7),
			//MUTATED ZYGOMITE -> new Task(Tasks2.MUTATED_ZYGOMITES,7),
			new Task(Tasks.INFERNAL_MAGES,7),
			new Task(Tasks.BRINE_RATS,7),
			//FEVER SPIDERS -> new Task(Tasks2.FEVER_SPIDERS,7),
			new Task(Tasks.BASILISKS,7),
			//WALL BEASTS -> new Task(Tasks2.WALL_BEASTS,6),
			//MOLANISKS -> new Task(Tasks2.MOLANISKS,6),
			//MOGRES -> new Task(Tasks2.MOGRES,6),
			new Task(Tasks.PYREFIENDS,6),
			new Task(Tasks.HARPIE_BUG_SWARMS,6),
			new Task(Tasks.COCKATRICES,6),
			new Task(Tasks.CAVE_SLIMES,6),
			new Task(Tasks.DESERT_LIZARDS, 5),
			new Task(Tasks.ROCK_SLUGS, 5),
			new Task(Tasks.BANSHEE, 5),
			new Task(Tasks.CAVE_CRAWLERS, 5)),
	DURADEL(8275, 100, 50, new int[]{50, 199}, new int[]{15, 75, 225},
			new Task(Tasks.JAD, 1),
			new Task(Tasks.COMMANDER_ZILYANA,1),
			new Task(Tasks.CHAOS_ELEMENTAL, 1),
			new Task(Tasks.GENERAL_GRARDOOR,1),
			new Task(Tasks.GIANT_MOLE,1),
			new Task(Tasks.KING_BLACK_DRAGON,1),
			new Task(Tasks.KRIL_TSUTSAROTH,1),
			new Task(Tasks.KREE_ARRA,1),
			new Task(Tasks.ABYSSAL_DEMONS,12),
			new Task(Tasks.DARK_BEASTS,11),
			new Task(Tasks.TZHAAR, 10),
			new Task(Tasks.HELLHOUNDS, 10),
			new Task(Tasks.KALPHITES,9),
			new Task(Tasks.NECHRYAELS,9),
			new Task(Tasks.MITHRIL_DRAGON,9),
			new Task(Tasks.BLACK_DRAGONS,9),
			new Task(Tasks.GREATER_DEMONS,9),
			new Task(Tasks.DAGANNOTHS,9),
			new Task(Tasks.GARGOYLES,8),
			new Task(Tasks.BLACK_DEMONS,8),
			//RED DRAGONS new Task(Tasks2.RED_DRAGONS,8),
			new Task(Tasks.BLOODVELDS,8),
			new Task(Tasks.AVIANSIES,8),
			//SUQAHS new Task(Tasks2.SUQAHS,8),
			new Task(Tasks.ABERRANT_SPECTRES,7),
			new Task(Tasks.STEEL_DRAGONS,7),
			new Task(Tasks.SPIRTUAL_MAGES,2),
			new Task(Tasks.SPIRTUAL_RANGERS,2),
			new Task(Tasks.SPIRTUAL_WARRIORS,2),
			new Task(Tasks.SKELETAL_WYVERN,7),
			new Task(Tasks.FIRE_GIANTS,7),
			new Task(Tasks.TROLLS,6),
			new Task(Tasks.IRON_DRAGONS,5),
			new Task(Tasks.ANKOU,5),
			new Task(Tasks.DUST_DEVILS,5),
			new Task(Tasks.KURASKS,4),
			new Task(Tasks.CAVE_HORRORS,4),
			new Task(Tasks.BLUE_DRAGONS,4),
			//ELVES new Task(Tasks2.ELVES,4),
			//ZYGOMITES new Task(Tasks2.MUTATED_ZYGOMITES,2),
			new Task(Tasks.WATERFIENDS,2));

	private static HashMap<Integer,Master> idMap = new HashMap<>();

	static{
		Arrays.stream(Master.values()).forEach(m -> idMap.putIfAbsent(m.npc_id, m));
	}

	int npc_id,required_combat,required_slayer;
	int[] assignment_range;
	int[] streakPoints;
	List<Task> tasks;
	Master(int npc_id, int required_combat, int required_slayer, int[] assignment_range, int[] streakPoints, Task... tasks) {
		this.npc_id = npc_id;
		this.assignment_range = assignment_range;
		this.streakPoints = streakPoints;
		this.tasks = new ArrayList<>(Arrays.asList(tasks));
	}

	public static Master forId(int id){
		return idMap.get(id);
	}

	public int getNpc(){
		return this.npc_id;
	}

	public int[] getTaskPoints(){
		return streakPoints;
	}

	public boolean hasRequirements(Player player){
		return player.getProperties().getCurrentCombatLevel() >= this.required_combat && player.getSkills().getLevel(Skills.SLAYER) >= this.required_slayer;
	}

	public static boolean hasSameTask(Master master, Player player){
		return master.tasks.stream().filter(task -> task.task == player.getSlayer().getTask()).count() != 0;
	}

	public static class Task{
		Tasks task;
		Integer weight;
		Task(Tasks task, Integer weight){
			this.task = task;
			this.weight = weight;
		}
	}
}