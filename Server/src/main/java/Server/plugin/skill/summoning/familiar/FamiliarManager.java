package plugin.skill.summoning.familiar;

import core.cache.def.impl.ItemDefinition;
import core.game.component.Component;
import core.game.container.Container;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import plugin.skill.Skills;
import plugin.skill.summoning.SummoningPouch;
import plugin.skill.summoning.pet.Pet;
import plugin.skill.summoning.pet.PetDetails;
import plugin.skill.summoning.pet.Pets;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.login.SavingModule;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.game.world.map.zone.ZoneRestriction;
import core.game.world.update.flag.context.Animation;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Handles a player's familiar.
 * @author Emperor
 */
public final class FamiliarManager implements SavingModule {

	/**
	 * The familiars mapping.
	 */
	private static final Map<Integer, Familiar> FAMILIARS = new HashMap<>();

	/**
	 * The pet details mapping, sorted by item id.
	 */
	private final Map<Integer, PetDetails> petDetails = new HashMap<Integer, PetDetails>();

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The familiar.
	 */
	private Familiar familiar;

	/**
	 * The combat level difference when using summoning.
	 */
	private int summoningCombatLevel;

	/**
	 * If the player has a summoning pouch.
	 */
	private boolean hasPouch;
	
	/**
	 * The list of insured pets.
	 */
	private List<Pets> insuredPets = new ArrayList<>();

	/**
	 * Constructs a new {@code FamiliarManager} {@code Object}.
	 * @param player The player.
	 */
	public FamiliarManager(Player player) {
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {
		for (Entry<Integer, PetDetails> entry : petDetails.entrySet()) {
			buffer.put((byte) 3);
			buffer.putInt(entry.getKey());
			entry.getValue().save(buffer);
		}
		if (hasPet()) {
			buffer.put((byte) 4);
			buffer.putInt(((Pet) familiar).getPet().getBabyItemId());
		} else if (hasFamiliar()) {
			buffer.put((byte) 1);
			buffer.putShort((short) familiar.getOriginalId());
			buffer.putShort((short) familiar.ticks);
			buffer.put((byte) familiar.specialPoints);
			if (familiar.isBurdenBeast() && !((BurdenBeast) familiar).getContainer().isEmpty()) {
				((BurdenBeast) familiar).getContainer().save(buffer.put((byte) 2));
			}
			buffer.put((byte) 5).putInt(familiar.getSkills().getLifepoints());
		}
		if (insuredPets.size() > 0) {
			buffer.put((byte) 6);
			buffer.put((byte) insuredPets.size());
			for (Pets pet : insuredPets) {
				buffer.putInt(pet.getBabyItemId());
			}
		}
		buffer.put((byte) 0);
	}

	public final void parse(JSONObject familiarData){
		JSONArray petDetails = (JSONArray) familiarData.get("petDetails");
		for(int i = 0 ; i < petDetails.size(); i++){
			JSONObject detail = (JSONObject) petDetails.get(i);
			PetDetails details = new PetDetails(0);
			details.updateHunger(Double.parseDouble(detail.get("hunger").toString()));
			details.updateGrowth(Double.parseDouble(detail.get("growth").toString()));
			details.setStage(Integer.parseInt(detail.get("stage").toString()));
			this.petDetails.put(Integer.parseInt(detail.get("petId").toString()),details);
		}

		if(familiarData.containsKey("currentPet")){
			int currentPet = Integer.parseInt( familiarData.get("currentPet").toString());
			PetDetails details = this.petDetails.get(currentPet);
			Pets pets = Pets.forId(currentPet);
			if (details == null) {
				details = new PetDetails(pets.getGrowthRate() == 0.0 ? 100.0 : 0.0);
				this.petDetails.put(currentPet, details);
			}
			familiar = new Pet(player, details, currentPet, pets.getNpcId(details.getStage()));
		} else if(familiarData.containsKey("familiar")){
			JSONObject currentFamiliar = (JSONObject) familiarData.get("familiar");
			int familiarId = Integer.parseInt( currentFamiliar.get("originalId").toString());
			familiar = FAMILIARS.get(familiarId).construct(player,familiarId);
			familiar.ticks = Integer.parseInt( currentFamiliar.get("ticks").toString());
			familiar.specialPoints = Integer.parseInt( currentFamiliar.get("specialPoints").toString());
			JSONArray famInv = (JSONArray) currentFamiliar.get("inventory");
			if(famInv != null){
				((BurdenBeast) familiar).container.parse(famInv);
			}
			familiar.setAttribute("hp",Integer.parseInt( currentFamiliar.get("lifepoints").toString()));
		}
	}

	@Override
	public final void parse(ByteBuffer buffer) {
		int opcode;
		PetDetails details;
		while ((opcode = buffer.get() & 0xFF) != 0) {
			switch (opcode) {
			case 1:
				int npcId = buffer.getShort() & 0xFFFF;
				familiar = FAMILIARS.get(npcId).construct(player, npcId);
				familiar.ticks = buffer.getShort() & 0xFFFF;
				familiar.specialPoints = buffer.get() & 0xFF;
				break;
			case 2:
				if (familiar == null || !familiar.isBurdenBeast()) {
					System.err.println("Error parsing BoB container!");
					new Container(30).parse(buffer);
					continue;
				}
				((BurdenBeast) familiar).getContainer().parse(buffer);
				break;
			case 3:
				int baseItem = buffer.getInt();
				details = new PetDetails(0);
				details.parse(buffer);
				petDetails.put(baseItem, details);
				break;
			case 4:
				int itemId = buffer.getInt();
				details = petDetails.get(itemId);
				Pets pets = Pets.forId(itemId);
				if (details == null) {
					details = new PetDetails(pets.getGrowthRate() == 0.0 ? 100.0 : 0.0);
					petDetails.put(itemId, details);
				}
				familiar = new Pet(player, details, itemId, pets.getNpcId(details.getStage()));
				break;
			case 5:
				int hp = buffer.getInt();
				if (familiar != null) {
					familiar.setAttribute("hp", hp);
				}
				break;
			case 6:
				int size = buffer.get();
				Pets pet;
				for (int i = 0; i < size; i++) {
					pet = Pets.forId(buffer.getInt());
					if (pet != null) {
						insuredPets.add(pet);
					}
				}
				break;
			}
		}
	}

	/**
	 * Called when the player logs in.
	 */
	public void login() {
		if (hasFamiliar()) {
			familiar.init();
		}
		player.getFamiliarManager().setConfig(243269632);
	}

	/**
	 * Summons a familiar.
	 * @param item The item.
	 * @param pet If the familiar is a pet.
	 * @param deleteItem we should delete the item.
	 */
	public void summon(Item item, boolean pet, boolean deleteItem) {
		if (hasFamiliar()) {
			player.getPacketDispatch().sendMessage("You already have a follower.");
			return;
		}
		if (player.getZoneMonitor().isRestricted(ZoneRestriction.FOLLOWERS) && !player.getLocks().isLocked("enable_summoning")) {
			player.getPacketDispatch().sendMessage("This is a Summoning-free area.");
			return;
		}
		if (pet) {
			summonPet(item, deleteItem);
			return;
		}
		final SummoningPouch pouch = SummoningPouch.get(item.getId());
		if (pouch == null) {
			return;
		}
		if (player.getSkills().getStaticLevel(Skills.SUMMONING) < pouch.getLevelRequired()) {
			player.getPacketDispatch().sendMessage("You need a Summoning level of " + pouch.getLevelRequired() + " to summon this familiar.");
			return;
		}
		if (player.getSkills().getLevel(Skills.SUMMONING) < pouch.getSummonCost()) {
			player.getPacketDispatch().sendMessage("You need at least " + pouch.getSummonCost() + " Summoning points to summon this familiar.");
			return;
		}
		final int npcId = pouch.getNpcId();
		Familiar fam = FAMILIARS.get(npcId);
		if (fam == null) {
			player.getPacketDispatch().sendMessage("Invalid familiar " + npcId + " - report on 2009scape github");
			return;
		}
		fam = fam.construct(player, npcId);
		if (fam.getSpawnLocation() == null) {
			player.getPacketDispatch().sendMessage("The spirit in this pouch is too big to summon here. You will need to move to a larger");
			player.getPacketDispatch().sendMessage("area.");
			return;
		}
		if (!player.getInventory().remove(item)) {
			return;
		}
		player.getSkills().updateLevel(Skills.SUMMONING, -pouch.getSummonCost(), 0);
		player.getSkills().addExperience(Skills.SUMMONING, pouch.getSummonExperience());
		familiar = fam;
		spawnFamiliar();
		if (player.getSkullManager().isWilderness()) {
			player.getAppearance().sync();
		}
	}

	/**
	 * Summons a familiar.
	 * @param item the item.
	 * @param pet the pet.
	 */
	public void summon(final Item item, boolean pet) {
		summon(item, pet, true);
	}

	/**
	 * Morphs a pet.
	 * @param item the item.
	 * @param deleteItem the item.
	 * @param location the location.
	 */
	public void morphPet(final Item item, boolean deleteItem, Location location) {
		if (hasFamiliar()) {
			familiar.dismiss();
		}
		summonPet(item, deleteItem, true, location);
	}
	
	/**
	 * Summons a pet.
	 * @param item the item.
	 * @param deleteItem the item.
	 */
	private boolean summonPet(final Item item, boolean deleteItem) {
		return summonPet(item, deleteItem, false, null);
	}
	
	/**
	 * Summons a pet.
	 * @param item the item.
	 * @param morph the pet.
	 */
	private boolean summonPet(final Item item, boolean deleteItem, boolean morph, Location location) {
		final int itemId = item.getId();
		if (itemId > 8850 && itemId < 8900) {
			return false;
		}
		Pets pets = Pets.forId(itemId);
		if (pets == null) {
			return false;
		}
		if (player.getSkills().getStaticLevel(Skills.SUMMONING) < pets.getSummoningLevel()) {
			player.getDialogueInterpreter().sendDialogue("You need a summoning level of " + pets.getSummoningLevel() + " to summon this.");
			return false;
		}
		int baseItemId = pets.getBabyItemId();
		PetDetails details = petDetails.get(baseItemId);
		if (details == null) {
			details = new PetDetails(pets.getGrowthRate() == 0.0 ? 100.0 : 0.0);
			petDetails.put(baseItemId, details);
		}
		int id = pets.getItemId(details.getStage());
		if (itemId != id) {
			player.getPacketDispatch().sendMessage("This is not the right pet, grow the pet correctly.");
			return true;
		}
		int npcId = pets.getNpcId(details.getStage());
		if (npcId > 0) {
			familiar = new Pet(player, details, itemId, npcId);
			if (deleteItem) {
				player.animate(new Animation(827));
				player.getInventory().remove(item);
			}
			if (morph) {
				morphFamiliar(location);
			} else {
				spawnFamiliar();
			}
			return true;
		}
		return true;
	}

	/**
	 * Morphs the current familiar.
	 * @param location the location.
	 */
	public void morphFamiliar(Location location) {
		familiar.init(location, false);
		player.getInterfaceManager().openTab(new Component(662));
		player.getInterfaceManager().setViewedTab(7);
	}
	
	/**
	 * Spawns the current familiar.
	 */
	public void spawnFamiliar() {
		familiar.init();
		player.getInterfaceManager().openTab(new Component(662));
		player.getInterfaceManager().setViewedTab(7);
	}

	/**
	 * Dumps the bob.
	 */
	public void dumpBob() {
		if (!hasFamiliar()) {
			player.getPacketDispatch().sendMessage("You don't have a familiar.");
			return;
		}
		Familiar familiar = getFamiliar();
		if (!familiar.isBurdenBeast()) {
			player.getPacketDispatch().sendMessage("Your familiar is not a beast of burden.");
			return;
		}
		BurdenBeast beast = ((BurdenBeast) familiar);
		if (!player.getBank().hasSpaceFor(beast.getContainer())) {
			player.getPacketDispatch().sendMessage("There is not enough space left in your bank.");
			return;
		}
		player.getBank().addAll(beast.getContainer());
		beast.getContainer().clear();
	}

	/**
	 * Makes the pet eat.
	 * @param foodId The food item id.
	 * @param npc The pet NPC.
	 */
	public void eat(int foodId, Pet npc) {
		if (npc != familiar) {
			player.getPacketDispatch().sendMessage("This isn't your pet!");
			return;
		}
		Pet pet = (Pet) familiar;
		Pets pets = Pets.forId(pet.getItemId());
		if (pets == null) {
			return;
		}
		for (int food : pets.getFood()) {
			if (food == foodId) {
				player.getInventory().remove(new Item(foodId));
				player.getPacketDispatch().sendMessage("Your pet happily eats the " + ItemDefinition.forId(food).getName() + ".");
				player.animate(new Animation(827));
				npc.getDetails().updateHunger(-15.0);
				return;
			}
		}
		player.getPacketDispatch().sendMessage("Nothing interesting happens.");
	}

	/**
	 * Picks up a pet.
	 */
	public void pickup() {
		if (player.getInventory().freeSlots() == 0) {
			player.getPacketDispatch().sendMessage("You don't have enough room in your inventory.");
			return;
		}
		Pet pet = ((Pet) familiar);
		PetDetails details = pet.getDetails();
		if (player.getInventory().add(new Item(pet.getPet().getItemId(details.getStage())))) {
			player.animate(Animation.create(827));
			player.getFamiliarManager().dismiss();
		}
	}

	/**
	 * Adjusts the battle state.
	 * @param state the state.
	 */
	public void adjustBattleState(final BattleState state) {
		if (!hasFamiliar()) {
			return;
		}
		familiar.adjustPlayerBattle(state);
	}

	/**
	 * Gets a boost from a familiar.
	 * @param skill the skill.
	 * @return the boosted level.
	 */
	public int getBoost(int skill) {
		if (!hasFamiliar()) {
			return 0;
		}
		return familiar.getBoost(skill);
	}

	/**
	 * Checks if the player has an active familiar.
	 * @return {@code True} if so.
	 */
	public boolean hasFamiliar() {
		return familiar != null;
	}

	/**
	 * Checks if the player has an active familiar and is a pet.
	 * @return {@code True} if so.
	 */
	public boolean hasPet() {
		return hasFamiliar() && familiar instanceof Pet;
	}

	/**
	 * Dismisses the familiar.
	 * @param saveDetails the details of a pet.
	 */
	public void dismiss(boolean saveDetails) {
		if (hasPet() && !saveDetails) {
			removeDetails(((Pet) familiar).getItemId());
		}
		if (hasFamiliar()) {
			familiar.dismiss();
		}
	}

	/**
	 * Dismisses the familiar.
	 */
	public void dismiss() {
		dismiss(true);
	}

	/**
	 * Removes the details for this pet.
	 * @param itemId The item id of the pet.
	 */
	public void removeDetails(int itemId) {
		Pets pets = Pets.forId(itemId);
		if (pets == null) {
			return;
		}
		petDetails.remove(pets.getBabyItemId());
	}

	/**
	 * Checks if it's the owner of a familiar.
	 * @param familiar the familiar
	 * @return {@code True} if so.
	 */
	public boolean isOwner(Familiar familiar) {
		if (!hasFamiliar()) {
			return false;
		}
		if (this.familiar != familiar) {
			player.getPacketDispatch().sendMessage("This is not your familiar.");
			return false;
		}
		return true;
	}

	/**
	 * Sets a config value.
	 * @param value the value.
	 */
	public void setConfig(int value) {
		int current = player.getConfigManager().get(1160);
		int newVal = current + value;
		player.getConfigManager().set(1160, newVal);
	}

	/**
	 * Gets the familiar.
	 * @return The familiar.
	 */
	public Familiar getFamiliar() {
		return familiar;
	}

	/**
	 * Sets the familiar.
	 * @param familiar The familiar to set.
	 */
	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	/**
	 * Gets the familiars.
	 * @return The familiars.
	 */
	public static Map<Integer, Familiar> getFamiliars() {
		return FAMILIARS;
	}

	/**
	 * Gets the usingSummoning.
	 * @return The usingSummoning.
	 */
	public boolean isUsingSummoning() {
		return hasPouch || hasFamiliar();
	}

	/**
	 * Gets the hasPouch.
	 * @return The hasPouch.
	 */
	public boolean isHasPouch() {
		return hasPouch;
	}

	/**
	 * Sets the hasPouch.
	 * @param hasPouch The hasPouch to set.
	 */
	public void setHasPouch(boolean hasPouch) {
		this.hasPouch = hasPouch;
	}

	/**
	 * Gets the summoningCombatLevel.
	 * @return The summoningCombatLevel.
	 */
	public int getSummoningCombatLevel() {
		return summoningCombatLevel;
	}

	/**
	 * Sets the summoningCombatLevel.
	 * @param summoningCombatLevel The summoningCombatLevel to set.
	 */
	public void setSummoningCombatLevel(int summoningCombatLevel) {
		this.summoningCombatLevel = summoningCombatLevel;
	}


	public Map<Integer, PetDetails> getPetDetails() {
		return petDetails;
	}
}