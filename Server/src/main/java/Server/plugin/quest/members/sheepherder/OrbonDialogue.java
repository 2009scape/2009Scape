package plugin.quest.members.sheepherder;

import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import plugin.dialogue.DialoguePlugin;

@InitializablePlugin
public class OrbonDialogue extends DialoguePlugin {
    public OrbonDialogue(){
        //empty
    }
    public OrbonDialogue(Player player){super(player);}


    @Override
    public DialoguePlugin newInstance(Player player) {
        return new OrbonDialogue(player);
    }

    @Override
    public boolean open(Object... args) {
        if(player.getQuestRepository().getStage("Sheep Herder") == 10){
            player("Hello doctor. I need to acquire some protective clothing","so that I can dispose of some escaped sheep infected","with the plague.");
            stage = 100;
            return true;
        } else {
            player.sendMessage("He doesn't seem interested in taling with you right now.");
        }
        return true;
    }

    @Override
    public boolean handle(int interfaceId, int buttonId) {
        switch(stage){
            case 100:
                npc("Protective clothing? I'm afraid I only have the one suit","which I made myself to prevent infection from the","contaminated patients I treat.");
                stage++;
                break;
            case 101:
                npc("I suppose I could sell you this one and make myself","another, but it would cost you at least 100 gold so that","I could afford a replacement.");
                stage++;
                break;
            case 102:
                options("Sorry doc, that's too much.","Ok, I'll take it.");
                stage++;
                break;
            case 103:
                switch(buttonId){
                    case 1:
                        player("Sorry doc, that's too much.");
                        stage = 120;
                        break;
                    case 2:
                        player("Ok, I'll take it.");
                        stage++;
                        break;
                }
                break;
            case 104:
                if(player.getInventory().containsItem(new Item(995,100))){
                    player.getInventory().remove(new Item(995,100));
                    if(!player.getInventory().add(SheepHerder.PLAGUE_BOTTOM, SheepHerder.PLAGUE_TOP)){
                        GroundItemManager.create(SheepHerder.PLAGUE_TOP,player.getLocation(),player);
                        GroundItemManager.create(SheepHerder.PLAGUE_BOTTOM,player.getLocation(),player);
                    }
                    player.getDialogueInterpreter().sendDialogue("You give Doctor Orbon 100 coins. Doctor Orbon hands over a","protective suit.");
                    stage++;
                    break;
                } else {
                    player("I would love to, but I don't have enough..");
                    stage = 120;
                    break;
                }
            case 105:
                npc("These should protect you from infection.");
                stage++;
                break;
            case 106:
                end();
                break;
            case 120:
                npc("That's unfortunate.");
                stage++;
                break;
            case 121:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {
        return new int[] {290};
    }
}
