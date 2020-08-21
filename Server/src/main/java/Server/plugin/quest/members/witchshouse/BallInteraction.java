package plugin.quest.members.witchshouse;

import core.game.interaction.MovementPulse;
import core.game.interaction.Option;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import plugin.SpellBlocks;
import plugin.activity.mta.TelekineticGrabSpell;
import plugin.quest.PluginInteraction;
import plugin.quest.PluginInteractionManager;
import plugin.skill.Skills;

@InitializablePlugin
public class BallInteraction extends PluginInteraction {
    private boolean handled;
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        SpellBlocks.register(TelekineticGrabSpell.SPELL_ID, (Node) new Item(2407));
        setIds(new int[]{2407});
        PluginInteractionManager.register(this, PluginInteractionManager.InteractionType.ITEM);
        return this;
    }

    @Override
    public boolean handle(Player player, Item item, Option option) {
        player.debug("Trying to handle ball...");
        if(option.getName().toLowerCase().equals("take")){

            player.debug("Take option used...");
            player.getPulseManager().run(new MovementPulse(player, item.getLocation().transform(0, -1, 0)) {
                @Override
                public boolean pulse() {
                    return true;
                }
            }, "movement");
            handleBall(player);
        }
        return handled;
    }

    public void handleBall(Player player){
        if(player.getAttribute("witchs_house:experiment_killed",false)) {
            if (player.getInventory().containsItem(new Item(2407))) {
                player.sendMessage("You already have the ball.");
                handled = true;
                player.debug("Handled in branch 1");
            } else {
                handled = false;
                player.debug("Unhandled.");
            }
        } else {
            player.debug("Handled in branch 2.");
            int[] skillsToDecrease = {Skills.DEFENCE, Skills.ATTACK, Skills.STRENGTH, Skills.RANGE, Skills.MAGIC};
            for (int i = 0; i < skillsToDecrease.length; i++) {
                player.getSkills().setLevel(i, player.getSkills().getLevel(i) > 5 ? player.getSkills().getLevel(i) - 5 : 1);
            }
            player.getPacketDispatch().sendMessage("<col=ff0000>The experiment glares at you, and you feel yourself weaken.</col>");
            new WitchsExperimentNPC(player.getAttribute("witchs_house:experiment_id",897), Location.create(2936, 3463, 0),player).init();
            handled = true;
        }
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }
}
