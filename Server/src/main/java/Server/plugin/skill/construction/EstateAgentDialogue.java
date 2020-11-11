package plugin.skill.construction;

import core.game.node.entity.player.link.diary.DiaryType;
import plugin.dialogue.DialoguePlugin;
import plugin.dialogue.FacialExpression;
import core.game.content.global.Skillcape;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.GameWorld;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;

/**
 * Represents the estate agent dialogue.
 *
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EstateAgentDialogue extends DialoguePlugin {

    /**
     * Represents the book item.
     */
    private static final Item BOOK = new Item(8463, 1);
    private static HouseLocation moveLoc;

    /**
     * Constructs a new {@code EstateAgentDialogue} {@code Object}.
     */
    public EstateAgentDialogue() {
        /**
         * empty.
         */
    }

    /**
     * Constructs a new {@code EstateAgentDialogue} {@code Object}.
     *
     * @param player the player.
     */
    public EstateAgentDialogue(Player player) {
        super(player);
    }

    @Override
    public DialoguePlugin newInstance(Player player) {
        return new EstateAgentDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        npc = (NPC) args[0];
        npc("Hello. Welcome to the " + GameWorld.getSettings().getName() + " Housing Agency! What", "can I do for you?");
        stage = 0;
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch (stage) {
            case 0:
                if (player.getHouseManager().hasHouse()) {
                    options("Can you move my house please?", "Can you redecorate my house please?", "Could I have a Construction guidebook?", "Tell me about houses.", "Tell me about that skillcape you're wearing.");
                    stage = 1;
                } else {
                    options("How can I get a house?", "Tell me about houses.");
                    stage = 2;
                }
                break;

            //Options for when a player has a house
            case 1:
                switch (buttonId) {
                    case 1:
                        player("Can you move my house please?");
                        stage = 10;
                        break;
                    case 2:
                        player("Can you redecorate my house please?");
                        stage = 30;
                        break;
                    case 3:
                        player("Could I have a Construction guidebook?");
                        stage = 50;
                        break;
                    case 4:
                        player("Tell me about houses!");
                        stage = 90;
                        break;
                    case 5:
                        player("Tell me about that skillcape you're wearing!");
                        stage = Skillcape.isMaster(player, Skills.CONSTRUCTION) ? 102 : 100;
                        break;
                }
                break;

            //Option for when a player does not have a house
            case 2:
                switch (buttonId) {
                    case 1:
                        player("How can I get a house?");
                        stage = 3;
                        break;
                    case 2:
                        player("Tell me about houses.");
                        stage = 90;
                        break;
                }
                break;

            //Real Estate Agent offering a house
            case 3:
                npc("I can sell you a starting house in Rimmington for", "1000 coins. As you increase your construction skill you", "will be able to have your house moved to other areas", "and redecorated in other styles.");
                stage++;
                break;
            case 4:
                npc("Do you want to buy a starter house?");
                stage++;
                break;
            case 5:
                options("Yes please!", "No thanks.");
                stage++;
                break;
            case 6:
                switch (buttonId) {
                    case 1:
                        player("Yes please!");
                        stage = 7;
                        break;
                    case 2:
                        player("No thanks.");
                        stage = 150;
                        break;
                }
                break;

            //Estate Agent Dialogue after house purchase
            case 7:
                if (player.getInventory().contains(995, 1000)) {
                    player.getInventory().remove(new Item(995, 1000));
                    player.getHouseManager().create(HouseLocation.RIMMINGTON);
                    npc("Thank you. Go through the Rimmington house portal", "and you will find your house ready for you to start", "building in it.");
                    stage = 150;
                } else {
                    npc("You don't have enough money to buy a house,", "come back when you can afford one.");
                    stage = 150;
                }
                break;

            //Move House Dialogue
            case 10:
                npc("Certainly. Where would you like it moved to?");
                stage++;
                break;
            case 11:
                options("Rimmington (5,000)", "Taverley (5,000)", "Pollnivneach (7,500)", "Rellekka (10,000)", "More...");
                stage++;
                break;
            case 12:
                switch (buttonId) {
                    case 1:
                        player("To Rimmington please!");
                        stage = 15;
                        break;
                    case 2:
                        player("To Taverly please!");
                        stage = 16;
                        break;
                    case 3:
                        player("To Pollnivneach please!");
                        stage = 17;
                        break;
                    case 4:
                        player("To Rellekka please!");
                        stage = 18;
                        break;
                    case 5:
                        options("Brimhaven (15,000)", "Yanille (25,000)", "...Previous");
                        stage = 13;
                        break;
                }
                break;
            case 13:
                switch (buttonId) {
                    case 1:
                        player("To Brimhaven please!");
                        stage = 19;
                        break;
                    case 2:
                        player("To Yanille please!");
                        stage = 20;
                        break;
                    case 3:
                        options("Rimmington (5,000)", "Taverley (5,000)", "Pollnivneach (7,500)", "Rellekka (10,000)", "More...");
                        stage = 12;
                        break;
                }
                break;
            case 15:
                configureMove(HouseLocation.RIMMINGTON);
                break;
            case 16:
                configureMove(HouseLocation.TAVERLY);
                break;
            case 17:
                configureMove(HouseLocation.POLLNIVNEACH);
                break;
            case 18:
                configureMove(HouseLocation.RELLEKKA);
                break;
            case 19:
                configureMove(HouseLocation.BRIMHAVEN);
                break;
            case 20:
                configureMove(HouseLocation.YANILLE);
                break;


            //Redecorate House
            case 30:
                npc("Certainly. My magic can rebuild the house in a", "completely new style! What style would you like?");
                stage++;
                break;
            case 31:
                options("Basic wood (5,000)", "Basic stone (5,000)", "Whitewashed stone (7,500)", "Fremennik-style wood (10,000)", "More...");
                stage++;
                break;
            case 32:
                switch (buttonId) {
                    case 1:
                        player("Basic wood please!");
                        stage = 35;
                        break;
                    case 2:
                        player("Basic stone please!");
                        stage = 36;
                        break;
                    case 3:
                        player("Whitewashed stone please!");
                        stage = 37;
                        break;
                    case 4:
                        player("Fremennik-style wood please!");
                        stage = 38;
                        break;
                    case 5:
                        options("Tropical wood (15,000)", "Fancy stone (25,000)", "Previous...");
                        stage = 33;
                        break;
                }
                break;
            case 33:
                switch (buttonId) {
                    case 1:
                        player("Tropical wood please!");
                        stage = 39;
                        break;
                    case 2:
                        player("Fancy stone please!");
                        stage = 40;
                        break;
                    case 3:
                        options("Basic wood (5,000)", "Basic stone (5,000)", "Whitewashed stone (7,500)", "Fremennik-style wood (10,000)", "More...");
                        stage = 32;
                        break;
                }
                break;
            case 35:
                redecorate(HousingStyle.BASIC_WOOD);
                break;
            case 36:
                redecorate(HousingStyle.BASIC_STONE);
                break;
            case 37:
                redecorate(HousingStyle.WHITEWASHED_STONE);
                break;
            case 38:
                redecorate(HousingStyle.FREMENNIK_STYLE_WOOD);
                break;
            case 39:
                redecorate(HousingStyle.TROPICAL_WOOD);
                // Give your player-owned house a fancy stone or tropical wood<br><br>finish at the Varrock estate agent's
                if (player.getLocation().withinDistance(Location.create(3239, 3474, 0))) {
                    player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 7);
                }
                break;
            case 40:
                redecorate(HousingStyle.FANCY_STONE);
                // Give your player-owned house a fancy stone or tropical wood<br><br>finish at the Varrock estate agent's
                if (player.getLocation().withinDistance(Location.create(3239, 3474, 0))) {
                    player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 2, 7);
                }
                break;

            //Asking for a Construction Book
            case 50:
                if (!player.hasItem(BOOK)) {
                    npc("Certainly.");
                    player.getInventory().add(BOOK);
                    stage = 150;
                    break;
                } else {
                    npc("You've already got one!");
                    stage = 150;
                    break;
                }

                //More Information on houses
            case 60:
                npc("It all came out of the wizards' experiments. They found", "a way to fold space, so that they could pack many", "acres of land into an area only a foot across.");
                stage++;
                break;
            case 61:
                npc("They created several folded-space regions across", "" + GameWorld.getSettings().getName() + ". Each one contains hundreds of small plots", "where people can build houses.");
                stage++;
                break;
            case 62:
                player("Ah, so that's how everyone can have a house without", "them cluttering up the world!");
                stage++;
                break;
            case 63:
                npc("Quite. The wizards didn't want to get bogged down", "in the business side of things so they ", "hired me to sell the houses.");
                stage++;
                break;
            case 64:
                npc("There are various other people across " + GameWorld.getSettings().getName() + " who can", "help you furnish your house. You should start buying", "planks from the sawmill operator in Varrock.");
                stage = 150;
                break;

            //Skillcape Dialogue for players without Level 99 Construction
            case 100:
                npc("As you may know, skillcapes are only available to masters", "in a skill. I have spent my entire life building houses and", "now I spend my time selling them! As a sign of my abilites", "I wear this Skillcape of Construction. If you ever have");
                stage = 101;
                break;
            case 101:
                npc("enough skill to build a demonic throne, come and talk to", "me and I'll sell you a skillcape like mine.");
                stage = 150;
                break;

            //Skillcape Dialogue for players with Level 99 Construction
            case 102:
                interpreter.sendDialogues(npc, FacialExpression.JOLLY, "I see you have recently achieved 99 construction.", "Would you like to buy a cape for 99,0000 gp?");
                stage++;
                break;
            case 103:
                options("Yes, I'll pay the 99k", "No thanks, maybe later.");
                stage++;
                break;
            case 104:
                switch (buttonId) {
                    case 1:
                        if (Skillcape.purchase(player, Skills.CONSTRUCTION)) {
                            npc("Here you go lad, enjoy!");
                        }
                        stage = 150;
                        break;
                    case 2:
                        player("No thanks, maybe later.");
                        stage = 150;
                        break;
                }
                break;

            //End
            case 150:
                end();
                break;
        }
        return true;
    }

    /**
     * Configures the move.
     *
     * @param location The house location.
     */
    private void configureMove(HouseLocation location) {
        if (location == player.getHouseManager().getLocation()) {
            npc("Your house is already there!");
            stage = 11;
            return;
        }
        if (location.getLevelRequirement() > player.getSkills().getStaticLevel(Skills.CONSTRUCTION)) {
            npc("I'm afraid you don't have a high enough construction", "level to move there. You need to have level " + location.getLevelRequirement() + ".");
            stage = 11;
            return;
        }
        if (!player.getInventory().contains(995, location.getCost())) {
            npc("Hmph. Come back when you have " + location.getCost() + " coins.");
            stage = 150;
            return;
        }
        player.getInventory().remove(new Item(995, location.getCost()));
        player.getHouseManager().setLocation(location);
        npc("Your house has been moved to " + location.getName() + ".");
        stage = 150;
        if (player.getLocation().withinDistance(Location.create(3239, 3474, 0))) {
            player.getAchievementDiaryManager().finishTask(player, DiaryType.VARROCK, 0, 11);
        }
    }

    /**
     * Redecorates the player's house.
     *
     * @param style The house style.
     */
    private void redecorate(HousingStyle style) {
        boolean diary = false;//player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).isComplete(0);
        if (style == player.getHouseManager().getStyle()) {
            npc("Your house is already in that style!");
            stage = 31;
            return;
        }
        if (style.getLevel() > player.getSkills().getStaticLevel(Skills.CONSTRUCTION)) {
            npc("You need a Construction level of " + style.getLevel() + " to buy this style.");
            stage = 31;
            return;
        }
        if (!player.getInventory().contains(995, style.getCost()) && !diary) {
            npc("Hmph. Come back when you have " + style.getCost() + " coins.");
            stage = 150;
            return;
        }
        if (!diary) {
            player.getInventory().remove(new Item(995, style.getCost()));
        }
        player.getHouseManager().redecorate(style);
        npc("Your house has been redecorated.");
        if (player.getLocation().withinDistance(new Location(2982, 3370))) {
            //player.getAchievementDiaryManager().getDiary(DiaryType.FALADOR).updateTask(player, 1, 5, true);
        }
        stage = 150;
    }

    @Override
    public int[] getIds() {
        return new int[]{4247};
    }
}