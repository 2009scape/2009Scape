package plugin.pkzone;

import core.cache.Cache;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.info.Rights;
import core.game.node.item.Item;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;
import core.plugin.PluginManifest;
import plugin.skill.Skills;

@InitializablePlugin
@PluginManifest(name="PKZone")
public class PKCommands extends CommandPlugin {

    @Override
    public boolean parse(Player player, String name, String[] args) {
        switch(name){
            case "max":
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
                        player.getPacketDispatch().sendMessage("You can't do that right now.");
                        return true;
                    }
                }
                for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
                    player.getSkills().setLevel(i, 99);
                    player.getSkills().setStaticLevel(i, 99);
                }
                player.getSkills().updateCombatLevel();
                player.getAppearance().sync();
                return true;
            case "item":
                if (args.length < 2) {
                    player.sendMessage("You must specify an item ID");
                    return false;
                }
                int id = toInteger(args[1]);
                int amount = args.length > 2 ? toInteger(args[2]) : 1;
                if (id > Cache.getItemDefinitionsSize()) {
                    player.sendMessage("Item ID '" + id + "' out of range.");
                    return true;
                }
                Item item = new Item(id, amount);
                int max = player.getInventory().getMaximumAdd(item);
                if (amount > max) {
                    amount = max;
                }
                item.setAmount(amount);
                player.getInventory().add(item);
                return true;
            case "pure":
                int[] SKILLS = new int[]{Skills.ATTACK,Skills.STRENGTH,Skills.HITPOINTS};
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
                        player.getPacketDispatch().sendMessage("You can't do that right now.");
                        return true;
                    }
                }
                for (int i = 0; i < SKILLS.length; i++) {
                    player.getSkills().setLevel(SKILLS[i], 99);
                    player.getSkills().setStaticLevel(SKILLS[i], 99);
                }
                player.getSkills().updateCombatLevel();
                player.getAppearance().sync();
                return true;
            case "empty":
                player.getInventory().clear();
                return true;
            case "reset":
                if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
                    if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
                        player.getPacketDispatch().sendMessage("You can't do that right now.");
                        return true;
                    }
                }
                for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
                    player.getSkills().setLevel(i, 1);
                    player.getSkills().setStaticLevel(i, 1);
                }
                player.getSkills().updateCombatLevel();
                player.getAppearance().sync();
                player.getInventory().clear();
                player.getEquipment().clear();
                return true;
        }
        return false;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        link(CommandSet.PLAYER);
        return this;
    }
}
