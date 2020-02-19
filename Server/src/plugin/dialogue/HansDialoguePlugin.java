package plugin.dialogue;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.content.dialogue.FacialExpression;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.Player;


/**
 * Represents the dialogue plugin used for the hans npc.
 */
@InitializablePlugin
public final class HansDialoguePlugin extends DialoguePlugin {

	private int[] timePlayed = new int[3];
	private int joinDateDays;

	/**
	 * Constructs a new {@code HansDialoguePlugin} {@code Object}.
	 */
	public HansDialoguePlugin() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HansDialoguePlugin} {@code Object}.
	 * @param player the player.
	 */
	public HansDialoguePlugin(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HansDialoguePlugin(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Hello. What are you doing here?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {

		switch (stage) {
			case 0:
				interpreter.sendOptions("Select an Option", "I'm looking for whoever is in charge of this place.", "I have come to kill everyone in this castle!", "I don't know. I'm lost. Where am I?", "Have you been here as long as me?", "I'd like to learn faster!");
				stage++;
				break;
			case 1:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Who, the Duke? He's in his study, on the first floor.");
						stage = 50;
						break;
					case 2:
						end();
						//TODO:
						// Face the player and walk away from them (like moon walking?).
						// After a moment, return to normal pathing associated with HansNPC.java
						npc.sendChat("Help! Help!");
						break;
					case 3:
						interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "You are in Lumbridge Castle.");
						stage = 50;
						break;
					case 4:
						interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "I've been patrolling this castle for years!");
						stage = 40;
						break;
					case 5:
						if (player.getSkills().experienceMutiplier == 15.0) {
							interpreter.sendDialogues(npc, FacialExpression.OSRS_NORMAL, "Sorry, but you're already at the fastest experience", "rate. It's only a one way operation, and", "you can't learn faster yet I'm afraid!");
							stage = 50;
						} else {
							interpreter.sendDialogues(npc, FacialExpression.HAPPY, "That's great! I can help you with that, but", "I must warn you it's only a one-way thing. There's no", "going back!");
							stage = 150;
						}
						break;
				}

				break;
			case 40:
				interpreter.sendDialogues(player, FacialExpression.THINKING, "You must be old then?");
				stage++;
				break;
			case 41:
				interpreter.sendDialogues(npc, FacialExpression.LAUGH, "Haha, you could say I'm quite the veteran of these lands.", "Yes, I've been here a fair while...");
				stage++;
				break;
			case 42: //mixing OSRS here
				interpreter.sendDialogues(player, FacialExpression.ASKING, "Can you tell me how long I've been here?");
				stage++;
				break;
			case 43:
				interpreter.sendDialogues(npc, FacialExpression.FRIENDLY, "Ahh, I see all the newcomers arriving in Lumbridge, fresh-faced ","and eager for adventure. I remember you...");
				stage = 50;
				break;
		/*case 44:
			getTimePlayed();

			//The text:
			//NOTE: it splits the text in different spots if the hours/minutes/days are 0 (because 0 days sounds weird, so it doesn't show it).

			//You've spent [amount] days, [amount] hours, [amount] minutes in the world (NEXT LINE) since you arrived [amount] days ago.
			//You've spent [amount] (days/hours), [amount] (hours/minutes) in the world since (NEXT LINE) you arrived [amount] days ago.
			//You've spent [amount] (days/hours/minutes) in the world since you arrived (NEXT LINE) [amount] days ago.
		*/

			//Closing Chat
			case 50:
				end();
				break;
			case 150:
				interpreter.sendOptions("Select an Option", "Set my exp rate to 10x", "Nevermind");
				stage++;
				break;
			case 151:
				switch (buttonId) {
					case 1:
						interpreter.sendDialogues(npc, FacialExpression.NEUTRAL, "Tada! Your experience rate is now 15x.", "Happy Scaping!");
						player.getSkills().experienceMutiplier = 10.0;
						stage = 50;
						break;
					case 2:
					    end();
						break;
				}

				break;
		}

		return true;
	}

	/**
	 * Obtains the player's join date and time played.
	 */
	private void getPlayerTime() {

		//TODO:
		// Find the Date Joined and Time Played variables for the player WITHOUT directly connecting to the SQL database here
		// Split the Time Played variable into Days, Hours and Minutes
		// Insert each calculation into the timePlayed array ( 0 for Days, 1 for Hours and 2 for Minutes)
		// Calculate the Days Since registering by subtracting the Date Joined from the Current Server Date (ServerDate - Join_Date)
		// Insert the date difference into joinDateDays variable
		// return;???
	}

	@Override
	public int[] getIds() {
		return new int[] { 0 };
	}
}
