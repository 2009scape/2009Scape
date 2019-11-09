package org.crandor.game.node.entity.player.link.spawn;

import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.info.login.SavingModule;
import org.crandor.game.node.entity.player.link.SavedData;
import org.crandor.game.node.entity.player.link.SpellBookManager;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.repository.Repository;
import org.crandor.net.packet.PacketRepository;
import org.crandor.net.packet.context.ChildPositionContext;
import org.crandor.net.packet.out.RepositionChild;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;

/**
 * Handles the spawn data for the spawn world.
 * @author Vexia
 *
 */
public class SpawnData implements SavingModule {

	/**
	 * The kill streak messages.
	 */
	private static final String[] KILLSTREAKS = new String[] {"rampage", "massacre", "frenzy", "annihilation", "decimation", "butchery", "extermination", "genocide", "carnage", "slaughter", "bloodshed", "assassination", "obliteration"};

	/**
	 * The current title.
	 */
	private PKTitle title = PKTitle.NOOB;

	/**
	 * The tutorial stage for the spawn server.
	 */
	private int tutorialStage;

	/**
	 * The pk points.
	 */
	private int pkPoints;

	/**
	 * The amount of kills.
	 */
	private int kills;

	/**
	 * The ammount of deaths.
	 */
	private int deaths;

	/**
	 * The skill streak.
	 */
	private int killStreak;

	private int purchased;

	/**
	 * Constructs a new {@Code SpawnData} {@Code Object}
	 */
	public SpawnData() {
		/**
		 * empty.
		 */
	}

	@Override
	public void save(ByteBuffer buffer) {
		SavedData.save(buffer, tutorialStage, 1);
		SavedData.save(buffer, pkPoints, 2);
		SavedData.save(buffer, kills, 3);
		SavedData.save(buffer, deaths, 4);
		SavedData.save(buffer, killStreak, 5);
		SavedData.save(buffer, title.ordinal(), 6);
		SavedData.save(buffer, purchased, 7);
		buffer.put((byte) 0);
	}

	@Override
	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch(opcode) {
				case 1:
					tutorialStage = buffer.getInt();
					break;
				case 2:
					pkPoints = buffer.getInt();
					break;
				case 3:
					kills = buffer.getInt();
					break;
				case 4:
					deaths = buffer.getInt();
					break;
				case 5:
					killStreak = buffer.getInt();
					break;
				case 6:
					title = PKTitle.values()[buffer.getInt()];
					break;
				case 7:
					for (int i = 0; i < PKPackage.values().length; i++) {
						purchased = PKPackage.values()[i].ordinal();
						System.out.println("SpawnData: " + PKPackage.values()[i].ordinal());
					}
					break;
			}
		}
	}

	/**
	 * Draws the stats tab. 
	 * @param player the player.
	 */
	public void drawStatsTab(Player player) {
		if (GameWorld.isEconomyWorld()) {
			return;
		}
		player.getPacketDispatch().sendInterfaceConfig(274, 3, true);
		player.getPacketDispatch().sendInterfaceConfig(274, 8, true);
		sendString(player, "Players online: " + Repository.getPlayers().size(), 5);
		sendString(player, "Starter Packages Below...", 23);
		sendString(player, "Information:", 12, null, -1);
		sendString(player, "PK Points: " + player.getSavedData().getSpawnData().getPkPoints(), 13);
		sendString(player, "Keldagrim Kills: " + getKills(), 14);
		sendString(player, "Keldagrim Deaths: " + getDeaths(), 15);
		sendString(player, "Keldagrim KDR: " + getKdr(), 16);
		sendString(player, "Title: " + "<col=" + getTitle().getTitleColor() + ">" + getTitle().getName(), 17);
		sendString(player, " ", 20);
		sendString(player, "Starter Packages:", 31);
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 60, 10, 298));/*
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 33, 10, 300));
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 34, 11, 314));*/
		//Sets & consumables

		sendString(player, PKPackage.values()[0].getName(), 156);
		sendString(player, PKPackage.values()[1].getName(), 32);
		sendString(player, PKPackage.values()[2].getName(), 137);
		sendString(player, PKPackage.values()[3].getName(), 146);
		sendString(player, PKPackage.values()[4].getName(), 142);
//		sendString(player, PKPackage.values()[5].getName(), 33);
		/*sendString(player, PKPackage.values()[6].getName(), 34);
		sendString(player, PKPackage.values()[7].getName(), 35);
		sendString(player, PKPackage.values()[8].getName(), 36);
		sendString(player, PKPackage.values()[9].getName(), 147);
		sendString(player, PKPackage.values()[10].getName(), 37);
		sendString(player, PKPackage.values()[11].getName(), 131);
		sendString(player, PKPackage.values()[12].getName(), 38);*/

	}

	/**
	 * Sends the string.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 * @param color the color.
	 */
	private void sendString(Player player, String string, int child, String color, int purchased) {
		player.getPacketDispatch().sendInterfaceConfig(274, child, false);
		player.getPacketDispatch().sendString((color != null && purchased == -1 ? color : "") + string, 274, child);
	}

	/**
	 * Sends the string.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 */ 
	private void sendString(Player player, String string, int child, int purchased) {
		sendString(player, string, child, "<col=FE9A2E>", purchased);
	}

	/**
	 * Sends the string.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 */
	private void sendString(Player player, String string, int child) {
		sendString(player, string, child, "<col=FE9A2E>", -1);
	}

	/**
	 * Shouts spawn info of the player.
	 * @param p the player.
	 * @param button the button.
	 */
	public void handleButton(Player p, int button) {
		if (GameWorld.isEconomyWorld()) {
			return;
		}
		drawStatsTab(p);
		switch(button) {
			case -1:
				break;
			case 16:
				p.sendChat("<col=FF0000>My KDR is: " + p.getSavedData().getSpawnData().getKdr() + "!");
				break;
			case 14:
				p.sendChat("<col=FF0000>I have killed " + p.getSavedData().getSpawnData().getKills() + " Keldagrim player" + (p.getSavedData().getSpawnData().getKills() != 1 ? "s" : "") + "!");
				break;
			case 15:
				p.sendChat("<col=FF0000>I have been killed " + p.getSavedData().getSpawnData().getDeaths() + " time" + (p.getSavedData().getSpawnData().getDeaths() != 1 ? "s" : "") + "!");
				break;
			case 13:
				p.sendChat("<col=FF0000>PK Points: " + p.getSavedData().getSpawnData().getPkPoints() + "!");
				break;
			case 12:
				p.sendChat("<col=FF0000>Keldagrim Credits: " + p.getDetails().getShop().getCredits() + "!");
				break;
			case 17:
				PKTitle[] titles = PKTitle.getTitles(p);
				PKTitle t;
				if (title.ordinal() >= titles.length-1) {
					t = titles[0];
				} else {
					t = titles[title.ordinal() + 1];
				}
				this.title = t;
				p.getAppearance().sync();
				drawStatsTab(p);
				break;
			/*sendString(player, PKPackage.values()[0].getName(), 156);
			sendString(player, PKPackage.values()[1].getName(), 32);
			sendString(player, PKPackage.values()[2].getName(), 137);
			sendString(player, PKPackage.values()[3].getName(), 146);
			sendString(player, PKPackage.values()[4].getName(), 142);
			sendString(player, PKPackage.values()[5].getName(), 33);
			sendString(player, PKPackage.values()[6].getName(), 34);
			sendString(player, PKPackage.values()[7].getName(), 35);
			sendString(player, PKPackage.values()[8].getName(), 36);
			sendString(player, PKPackage.values()[9].getName(), 147);
			sendString(player, PKPackage.values()[10].getName(), 37);
			sendString(player, PKPackage.values()[11].getName(), 131);
			sendString(player, PKPackage.values()[12].getName(), 38);*/
			case 156:
				Player player = p;
				if (!player.canSpawn()) {
					return;
				}
				p.getSkills().setLevel(Skills.ATTACK, 99);
				p.getSkills().setStaticLevel(Skills.ATTACK, 99);
				p.getSkills().setLevel(Skills.STRENGTH, 99);
				p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
				p.getSkills().setLevel(Skills.DEFENCE, 99);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 99);
				p.getSkills().setLevel(Skills.HITPOINTS, 99);
				p.getSkills().setStaticLevel(Skills.HITPOINTS, 99);
				p.getSkills().setLevel(Skills.RANGE, 99);
				p.getSkills().setStaticLevel(Skills.RANGE, 99);
				p.getSkills().setLevel(Skills.MAGIC, 99);
				p.getSkills().setStaticLevel(Skills.MAGIC, 99);
				p.getSkills().setLevel(Skills.PRAYER, 99);
				p.getSkills().setStaticLevel(Skills.PRAYER, 99);
				loadGear(p, 0);
				break;
			case 32:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				p.getSkills().setLevel(Skills.ATTACK, 50);
				p.getSkills().setStaticLevel(Skills.ATTACK, 50);
				p.getSkills().setLevel(Skills.STRENGTH, 99);
				p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
				p.getSkills().setLevel(Skills.DEFENCE, 45);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 45);
				p.getSkills().setLevel(Skills.HITPOINTS, 99);
				p.getSkills().setStaticLevel(Skills.HITPOINTS, 99);
				p.getSkills().setLevel(Skills.RANGE, 99);
				p.getSkills().setStaticLevel(Skills.RANGE, 99);
				p.getSkills().setLevel(Skills.MAGIC, 94);
				p.getSkills().setStaticLevel(Skills.MAGIC, 94);
				p.getSkills().setLevel(Skills.PRAYER, 52);
				p.getSkills().setStaticLevel(Skills.PRAYER, 52);
				loadGear(p, 1);
				break;
			case 137:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				p.getSkills().setLevel(Skills.ATTACK, 90);
				p.getSkills().setStaticLevel(Skills.ATTACK, 90);
				p.getSkills().setLevel(Skills.STRENGTH, 99);
				p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
				p.getSkills().setLevel(Skills.DEFENCE, 80);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 80);
				p.getSkills().setLevel(Skills.HITPOINTS, 99);
				p.getSkills().setStaticLevel(Skills.HITPOINTS, 99);
				p.getSkills().setLevel(Skills.RANGE, 99);
				p.getSkills().setStaticLevel(Skills.RANGE, 99);
				p.getSkills().setLevel(Skills.MAGIC, 99);
				p.getSkills().setStaticLevel(Skills.MAGIC, 99);
				p.getSkills().setLevel(Skills.PRAYER, 80);
				p.getSkills().setStaticLevel(Skills.PRAYER, 80);
				loadGear(p, 2);
				break;
			case 146:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				p.getSkills().setLevel(Skills.ATTACK, 80);
				p.getSkills().setStaticLevel(Skills.ATTACK, 80);
				p.getSkills().setLevel(Skills.STRENGTH, 99);
				p.getSkills().setStaticLevel(Skills.STRENGTH, 99);
				p.getSkills().setLevel(Skills.DEFENCE, 1);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 1);
				p.getSkills().setLevel(Skills.HITPOINTS, 99);
				p.getSkills().setStaticLevel(Skills.HITPOINTS, 99);
				p.getSkills().setLevel(Skills.RANGE, 99);
				p.getSkills().setStaticLevel(Skills.RANGE, 99);
				p.getSkills().setLevel(Skills.MAGIC, 99);
				p.getSkills().setStaticLevel(Skills.MAGIC, 99);
				p.getSkills().setLevel(Skills.PRAYER, 52);
				p.getSkills().setStaticLevel(Skills.PRAYER, 52);
				loadGear(p, 3);
				break;
			case 142:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				p.getSkills().setLevel(Skills.ATTACK, 60);
				p.getSkills().setStaticLevel(Skills.ATTACK, 60);
				p.getSkills().setLevel(Skills.STRENGTH, 92);
				p.getSkills().setStaticLevel(Skills.STRENGTH, 92);
				p.getSkills().setLevel(Skills.DEFENCE, 45);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 45);
				p.getSkills().setLevel(Skills.HITPOINTS, 91);
				p.getSkills().setStaticLevel(Skills.HITPOINTS, 91);
				p.getSkills().setLevel(Skills.RANGE, 99);
				p.getSkills().setStaticLevel(Skills.RANGE, 99);
				p.getSkills().setLevel(Skills.MAGIC, 99);
				p.getSkills().setStaticLevel(Skills.MAGIC, 99);
				p.getSkills().setLevel(Skills.PRAYER, 52);
				p.getSkills().setStaticLevel(Skills.PRAYER, 52);
				loadGear(p, 4);
				break;
			case 33:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 5);
				break;
			case 34:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 6);
				break;
			case 35:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 7);
				break;
			case 36:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 8);
				break;
			case 147:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 9);
				break;
			case 37:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 10);
				break;
			case 131:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 11);
				break;
			case 38:
				player = p;
				if (!player.canSpawn()) {
					return;
				}
				loadGear(p, 12);
				break;

		}
	}

	public void loadGear(Player p, int index) {
		PKPackage pkPackage = PKPackage.values()[index];
		if (pkPackage != null) {
			if (pkPackage.getType() == 0 && (!p.getBank().hasSpaceFor(p.getEquipment()) || !p.getBank().hasSpaceFor(p.getInventory()))) {
				p.sendMessage("You don't have enough bank space to do that.");
				return;
			}
			if (pkPackage == pkPackage.PURE) {
				p.getSkills().setLevel(Skills.DEFENCE, 1);
				p.getSkills().setStaticLevel(Skills.DEFENCE, 1);
				p.getSkills().setLevel(Skills.PRAYER, 52);
				p.getSkills().setStaticLevel(Skills.PRAYER, 52);
			} /*else if (pkPackage.getType() == 0) {
				for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
					p.getSkills().setLevel(i, 99);
					p.getSkills().setStaticLevel(i, 99);
				}
			}*/
			if (pkPackage.getType() == 0/* && purchased == -1*/) {
				p.getBank().addAll(p.getEquipment());
				p.getBank().addAll(p.getInventory());
				p.getEquipment().clear();
				p.getInventory().clear();
				/*for (Item item : pkPackage.getItems()) {
					if ((item.getDefinition().hasAction("wear") || item.getDefinition().hasAction("wield")) && p.getEquipment().get(item.getDefinition().getConfiguration(ItemConfigSQLHandler.EQUIP_SLOT, -1)) == null) {
						p.getEquipment().add(item, true, false);
					} else {
						p.getInventory().add(item);
					}
				}
			} else {*/
				p.getInventory().add(pkPackage.getItems());
				purchased = PKPackage.values()[index].ordinal();
			}
			if (pkPackage.getSpellBook() != -1 && purchased == -1) {
				p.getSpellBookManager().setSpellBook(SpellBookManager.SpellBook.values()[pkPackage.getSpellBook()]);
				p.getSpellBookManager().update(p);
			}
			if (pkPackage == pkPackage.VENGEANCE_RUNE || pkPackage == pkPackage.BARRAGE_RUNE || pkPackage == pkPackage.ENTANGE_RUNE) {
				p.getSpellBookManager().setSpellBook(SpellBookManager.SpellBook.LUNAR);
				p.getSpellBookManager().update(p);
			}
			p.getSkills().updateCombatLevel();
			p.getAppearance().sync();
			if (purchased != -1) {
				p.sendMessage("You have already purchased your one set, please use the shops");
				p.sendMessage("for any gear needed.");
			} else {
				p.sendMessage("You load the " + pkPackage.getName() + " package, your items have been banked!");
			}
			p.getInterfaceManager().openTab(3, new Component(149)); // inventory
		}
	}

	/**
	 * Called when the player killer has killed another player.
	 * @param killer
	 * @param killed
	 */
	public void onDeath(Player killer, Player killed) {
		if (GameWorld.isEconomyWorld()) {
			return;
		}
		if (killer.isArtificial() || killed.isArtificial() || killer.getDetails().getInfo().getIp().equals(killed.getDetails().getInfo().getIp()) || killed.getDetails().getInfo().getMac().equals(killer.getDetails().getInfo().getMac())) {
			killer.sendMessage("You can't kill someone from your own computer address.");
			return;
		}
		SpawnData killedInfo = killed.getSavedData().getSpawnData();
		int increment = getStreakPoints(killer);
		if (killedInfo.getKillStreak() > 4) {
			increment += getStreakPoints(killed);
			Repository.sendNews("<img=10><col=CC6600>News: " + killer.getUsername() + " has ended " + killed.getUsername() + "'s killstreak of " + killedInfo.getKillStreak() + "!");
		}
		incrementKills();
		incrementStreak();
		killedInfo.setKillStreak(0);
		killedInfo.incrementDeaths();
		incrementPkPoints(increment);
		PKTitle.checkTitle(killer);
		drawStatsTab(killer);
		killedInfo.drawStatsTab(killed);
		killer.sendMessage("<col=FF0000>You have killed " + killed.getUsername() + "! Your Keldagrim PK Points have increased by " + increment + ".");
		killer.sendMessage("<col=FF0000>You are now on a " + killStreak + " killstreak!");
		if (killStreak > 4) {
			Repository.sendNews("<img=10><col=CC6600>News: " + getStreakMessage(killer, killed));
		}
		PKScoreBoard.check(killer);
	}

	/**
	 * Gets the streak points.
	 * @param killed the killed player.
	 * @return the points.
	 */
	private int getStreakPoints(Player killed) {
		int streak = killed.getSavedData().getSpawnData().getKillStreak();
		int points = 1;
		if (streak > 3) {
			return streak;
		}
		return points;
	}

	/**
	 * Gets a kill streak message.
	 * @param killer the killer.
	 * @param killed the killed.
	 * @return the kill streak message.no1 readd him
	 */
	private String getStreakMessage(Player killer, Player killed) {
		int streak = getKillStreak();	
		String message = "killstreak";
		if (streak > 5) {
			int index = streak - 6;
			if (index > KILLSTREAKS.length-1) {
				return killer.getUsername() + " is unstoppable! " + (killer.getAppearance().isMale() ? "He" : "She") + " is on a killstreak of " + streak + "!";
			} else {
				message = "kill " + KILLSTREAKS[index];
			}
		}
		return killer.getUsername() + " is on a " + streak + " " + message + "! Kill " + (killer.getAppearance().isMale() ? "him" : "her") + " to gain " + streak + " PKP!";
	}

	/**
	 * Increments the kill streak.
	 */
	public void incrementStreak() {
		killStreak++;
	}

	/**
	 * Increments the deaths.
	 */
	public void incrementDeaths() {
		deaths++;
	}

	/**
	 * Increments the kills.
	 */
	public void incrementKills() {
		kills++;
	}

	/**
	 * Increments the pk points.
	 * @param increment the increment.
	 */
	public void incrementPkPoints(int increment) {
		setPkPoints(getPkPoints() + increment);
	}

	/**
	 * Checks if the tutorial is completed.
	 * @return {@code True} if so.
	 */
	public boolean hasCompletedTutorial() {
		return getTutorialStage() > 2;
	}

	/**
	 * Increments the tutorial stage.
	 */
	public void incrementTutorialStage() {
		setTutorialStage(getTutorialStage() + 1);
	}

	/**
	 * Gets the kdr.
	 * @return the kdr.
	 */
	public String getKdr() {
		return new DecimalFormat().format(deaths == 0 ? kills : (double) ((double) kills / (double) deaths));
	}

	/**
	 * Gets the tutorialStage.
	 * @return the tutorialStage
	 */
	public int getTutorialStage() {
		return tutorialStage;
	}

	/**
	 * Sets the batutorialStage.
	 * @param tutorialStage the tutorialStage to set.
	 */
	public void setTutorialStage(int tutorialStage) {
		this.tutorialStage = tutorialStage;
	}

	/**
	 * Gets the pkPoints.
	 * @return the pkPoints
	 */
	public int getPkPoints() {
		return pkPoints;
	}

	/**
	 * Sets the bapkPoints.
	 * @param pkPoints the pkPoints to set.
	 */
	public void setPkPoints(int pkPoints) {
		this.pkPoints = pkPoints;
	}

	/**
	 * Gets the title.
	 * @return the title
	 */
	public PKTitle getTitle() {
		return title;
	}

	/**
	 * Sets the batitle.
	 * @param title the title to set.
	 */
	public void setTitle(PKTitle title) {
		this.title = title;
	}

	/**
	 * Gets the kills.
	 * @return the kills
	 */
	public int getKills() {
		return kills;
	}

	/**
	 * Sets the bakills.
	 * @param kills the kills to set.
	 */
	public void setKills(int kills) {
		this.kills = kills;
	}

	/**
	 * Gets the deaths.
	 * @return the deaths
	 */
	public int getDeaths() {
		return deaths;
	}

	/**
	 * Decrements the points.
	 */
	public void decrementPoints(int decrement) {
		pkPoints-= decrement;
	}

	/**
	 * Sets the badeaths.
	 * @param deaths the deaths to set.
	 */
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	/**
	 * Gets the killStreak.
	 * @return the killStreak
	 */
	public int getKillStreak() {
		return killStreak;
	}

	/**
	 * Sets the bakillStreak.
	 * @param killStreak the killStreak to set.
	 */
	public void setKillStreak(int killStreak) {
		this.killStreak = killStreak;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}


}
