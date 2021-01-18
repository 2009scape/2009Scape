package core.game.content.global;

import core.game.node.entity.player.Player;

/**
 * The BossKillcounter keeps track of the amount of bosses the player has slain.
 * addtoKillcount(player, npcId) should be added in the finalizeDeath() method of the combat handler for the boss.
 * @author Splinter
 */
public enum BossKillCounter {
	
	
	/* ORDINAL BOUND */
	KING_BLACK_DRAGON(new int[] { 50 }, "King Black Dragon", 14649), 
	BORK(new int[] { 7133, 7134 }, "Bork", -1),
	DAGANNOTH_SUPREME(new int[] { 2881 }, "Dagannoth Supreme", 14639),
	DAGANNOTH_PRIME(new int[] { 2882 }, "Dagannoth Prime", 14640), 
	DAGANNOTH_REX(new int[] { 2883 }, "Dagannoth Rex", 14641), 
	CHAOS_ELEMENTAL(new int[] { 3200 }, "Chaos Elemental", 14638), 
	GIANT_MOLE(new int[] { 3340 }, "Giant Mole", 14642), 
	SARADOMIN(new int[] { 6247 }, "Commander Zilyana", 14647), 
	ZAMORAK(new int[] { 6203 }, "K'ril Tsutsaroth", 14648), 
	BANDOS(new int[] { 6260 }, "General Graardor", 14646),
	ARMADYL(new int[] { 6222 }, "Kree'arra", 14645), 
	JAD(new int[] { 2745 }, "Tz-Tok Jad", 14828), 
	KALPHITE_QUEEN(new int[] { 1160 }, "Kalphite Queen", 14650),
	CORPOREAL_BEAST(new int[] { 8133 }, "Corporeal Beast", 14653),
	CALLISTO(new int[] { 8610 }, "Callisto", 14658),
	SCORPIA(new int[] { 8611 }, "Scorpia", 14661),
	VENENATIS(new int[] { 8612 }, "Venenatis", 14657),
	VETION(new int[] { 8613 }, "Vet'ion", 14659),
	KRAKEN(new int[] { 8614 }, "Cave Kraken", 14651),
	

	;

	/**
	 * The npcs that can increase the killcounter
	 */
	private final int[] npc;

	/**
	 * The name of the NPC, to be displayed as a sendMessage
	 */
	private final String name;
	
	/**
	 * The item ID of the pet relating to the boss.
	 */
	private final int petId;

	/**
	 * Constructs a new {@code BossKillCounter} {@code Object}.
	 * @param npc the npc.
	 * @param name the npc's string name
	 */
	BossKillCounter(final int[] npc, final String name, final int petId) {
		this.npc = npc;
		this.name = name;
		this.petId = petId;
	}

	/**
	 * Gets the npc.
	 * @return The npc.
	 */
	public int[] getNpc() {
		return npc;
	}

	/**
	 * Gets the NPC's name
	 * @return their name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the petId
	 * @return The petId
	 */
	public int getPetId() {
		return petId;
	}

	/**
	 * Gets the type for the npc.
	 * @param npc the npc.
	 * @return the BossKillcounter
	 */
	public static BossKillCounter forNPC(final int npc) {
		for (BossKillCounter kc : BossKillCounter.values()) {
			for (int i : kc.getNpc()) {
				if (npc == i) {
					return kc;
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds to the player's killcount for that particular boss.
	 * @param killer The player who killed the npc
	 * @param npcid the ID of the npc that just died
	 */
	public static void addtoKillcount(Player killer, int npcid) {
		if (killer == null) {
			return;
		}
		BossKillCounter boss = BossKillCounter.forNPC(npcid);
		if (boss == null) {
			return;
		}
		killer.getSavedData().getGlobalData().getBossCounters()[boss.ordinal()]++;
		killer.getPacketDispatch().sendMessage("Your " + boss.getName() + " killcount is now: <col=ff0000>" + killer.getSavedData().getGlobalData().getBossCounters()[boss.ordinal()] + "</col>.");
//		addBossPet(killer, npcid, boss);
	}
	
//	/**
//	 * Gives the player the pet if they killed a certain boss.
//	 * The chance by default is 1/5000. This rate lowers to 1/2200 if the <GlobalEvents> for Boss Pets is active.
//	 * Note: Not all bosses have pet versions of themselves.
//	 */
//	private static void addBossPet(Player killer, int npcid, BossKillCounter boss){
//		if(boss.getPetId() == -1){ //The boss does not have a pet version.
//			return;
//		}
//		int number = 5000;
//		if (npcid == 2745) {
//			number = 200;
//			if (killer.getSlayer().getTask() == Tasks.JAD) {
//				number = 100;
//			}
//		} else if (npcid == 3200) {
//			number = 300;
//		}
//		int rand = number;
//		if(rand == 10){
//			for (int i = 0; i < killer.getFamiliarManager().getInsuredPets().size(); i++) {
//				if (killer.getFamiliarManager().getInsuredPets().get(i).getBabyItemId() == boss.getPetId()) {
//					return;
//				}
//			}
//			if(killer.getFamiliarManager().hasFamiliar() && killer.getInventory().freeSlots() < 1){
//				return;
//			}
//			if(!killer.getFamiliarManager().hasFamiliar()){
//				killer.getFamiliarManager().summon(new Item(boss.getPetId()),  true);
//				killer.sendNotificationMessage("You have a funny feeling like you're being followed.");
//			} else if (killer.getInventory().freeSlots() > 0){
//				killer.getInventory().add(new Item(boss.getPetId(), 1));
//				killer.sendNotificationMessage("You feel something weird sneaking into your backpack.");
//			}
//			Repository.sendNews(killer.getUsername()+" now commands a miniature "+(boss.equals(CORPOREAL_BEAST) ? "Dark core" : boss.getName())+"!");
//		}
//	}

	/**
	 * Increments the player's Barrows chest counter.
	 * @param player the player
	 */
	public static void addtoBarrowsCount(Player player) {
		if (player == null) {
			return;
		}
		player.getSavedData().getGlobalData().setBarrowsLoots(player.getSavedData().getGlobalData().getBarrowsLoots() + 1);
		player.getPacketDispatch().sendMessage("Your Barrows chest count is: <col=ff0000>" + player.getSavedData().getGlobalData().getBarrowsLoots() + "</col>.");
	}

}