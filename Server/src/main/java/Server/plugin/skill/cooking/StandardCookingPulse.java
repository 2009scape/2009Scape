package plugin.skill.cooking;

import core.game.content.ItemNames;
import core.game.content.global.SkillcapePerks;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import plugin.quest.tutorials.tutorialisland.TutorialStage;
import plugin.skill.Skills;
import core.game.node.entity.impl.Animator;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.audio.Audio;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.system.task.Pulse;
import core.game.world.update.flag.context.Animation;
import core.tools.RandomFunction;

import static core.tools.RandomFunction.RANDOM;

public class StandardCookingPulse extends Pulse {
    //range animation
    private static final Animation RANGE_ANIMATION = new Animation(883, Animator.Priority.HIGH);

    //fire animation
    private static final Animation FIRE_ANIMATION = new Animation(897, Animator.Priority.HIGH);

    //Cooking sound
    public static final Audio SOUND = new Audio(2577, 1, 1);

    private int initial,product,amount,level;
    private GameObject object;
    private Player player;
    private double experience;
    private boolean burned = false;

    public StandardCookingPulse(Player player, GameObject object, int initial, int product, int amount){
        this.player = player;
        this.object = object;
        this.initial = initial;
        this.product = product;
        this.amount = amount;
    }

    @Override
    public void start() {
        if(checkRequirements()) {
            super.start();
            cook(player,object, CookableItems.cookingMap.get(initial) != null && isBurned(player, object, initial),initial,product);
            amount--;
        }
    }

    @Override
    public boolean pulse() {
        if(!checkRequirements()){
            return true;
        }
        return reward();
    }

    public void animate() {player.animate(getAnimation(object));}

    public boolean checkRequirements(){
        CookableItems properties = CookableItems.forId(initial);
        this.level = 1;
        this.experience = 0;
        if(properties != null){
            //handle cook's assistant range
            if(object.getId() == 36973 && !player.getQuestRepository().isComplete("Cook's Assistant")){
                player.getPacketDispatch().sendMessage("You need to have completed the Cook's Assistant quest in order to use that range.");
                return false;
            }

            //check level
            if (player.getSkills().getLevel(Skills.COOKING) < properties.level) {
                player.getDialogueInterpreter().sendDialogue("You need a cooking level of " + properties.level + " to cook this.");
                return false;
            }

            this.level = properties.level;
            this.experience = properties.experience;
            this.burned = isBurned(player,object,initial);
        }
        if (amount < 1) {
            return false;
        }
        return true;
    }

    public boolean reward(){
        if (getDelay() == 1) {
            setDelay(object.getName().toLowerCase().equals("range") ? 5 : 4);
            return false;
        }
        //handle tutorial stuff
        if(!TutorialSession.getExtension(player).finished()){
            updateTutorial(player);
            amount --;
            return true;
        }

        if (cook(player,object,burned,initial,product)) {
            amount--;
        } else {
            return true;
        }
        return amount < 1;
    }

    public boolean isBurned(final Player player, final GameObject object, int food) {
        boolean hasGauntlets = player.getEquipment().containsItem(new Item(ItemNames.COOKING_GAUNTLETS_775));
        double burn_stop = (double)CookableItems.getBurnLevel(food);
        if(hasGauntlets && (food == ItemNames.RAW_SWORDFISH || food == ItemNames.RAW_LOBSTER || food == ItemNames.RAW_SHARK)){
            burn_stop -= 6;
        }
        if (SkillcapePerks.hasSkillcapePerk(player, SkillcapePerks.COOKING)) {
            return false;
        }
        if (player.getSkills().getLevel(Skills.COOKING) > burn_stop) {
            return false;
        }
        double burn_chance = 60.0 + (object.getName().equals("fire") ? 1.00 : 0) - (object.getId() == 114 ? 1.00 : 0);
        double cook_level = (double) player.getSkills().getLevel(Skills.COOKING);
        double lev_needed = (double) CookableItems.forId(food).level;
        double multi_a = (burn_stop - lev_needed);
        double burn_dec = (burn_chance / multi_a);
        double multi_b = (cook_level - lev_needed);
        burn_chance -= (multi_b * burn_dec);
        double randNum = RANDOM.nextDouble() * 100.0;
        return !(burn_chance <= randNum);
    }

    public boolean cook(final Player player, final GameObject object, final boolean burned, final int initial, final int product) {
        Item initialItem = new Item(initial);
        Item productItem = new Item(product);
        player.lock(getDelay());
        animate();

        //lumbridge diary
        if (object.getId() == 114 && player.getViewport().getRegion().getId() == 12850 && !player.getAchievementDiaryManager().getDiary(DiaryType.LUMBRIDGE).isComplete(0, 7)) {
            player.getAchievementDiaryManager().updateTask(player, DiaryType.LUMBRIDGE, 0, 7, true);
        }
        //handle special cooking results (spits, cake, etc) that don't justify separate plugin
        switch(initial){
            case 9986:
            case 2876:
            case 7566:
            case 9984:
            case 7724: // spits
                if (RandomFunction.random(15) == 5) {
                    player.getPacketDispatch().sendMessage("Your iron spit seems to have broken in the proccess.");
                } else {
                    if (!player.getInventory().add(new Item(7725))) {
                        GroundItemManager.create(new Item(7725), player.getLocation(), player);
                    }
                }
                break;
            case 1889: //cake
                if (!player.getInventory().add(new Item(1887))) {
                    GroundItemManager.create(new Item(1887), player);
                }
                break;
        }
        if (player.getInventory().remove(initialItem)) {
            if (!burned) {
                player.getInventory().add(productItem);
                player.getSkills().addExperience(Skills.COOKING, experience,true);
            } else {
                player.getInventory().add(CookableItems.getBurnt(initial));
            }
            player.getPacketDispatch().sendMessage(getMessage(initialItem, productItem, burned));
            player.getAudioManager().send(SOUND);
            return true;
        }
        return false;
    }

    public String getMessage(Item food, Item product, boolean burned){
        if(CookableItems.intentionalBurn(food.getId())){
            return "You deliberately burn the perfectly good piece of meat.";
        }
        switch(product.getId()) {
            case 9436:
                return "You dry the meat into sinew.";
            case 1781:
                return "You burn the seaweed into soda ash.";
        }
        if(!burned) {
            return "You manage to cook some " + food.getName().replace("Raw ", "");
        } else {
            return "You accidentally burn some " + food.getName().replace("Raw ","");
        }
    }

    public boolean updateTutorial(Player player){
        if (TutorialSession.getExtension(player).getStage() == 14) {
            TutorialStage.load(player, 15, false);
            return cook(player, object,true, initial, product);
        } else if (TutorialSession.getExtension(player).getStage() == 15) {
            TutorialStage.load(player, 16, false);
            return cook(player, object, false, initial, product);
        }
        if (TutorialSession.getExtension(player).getStage() == 20) {
            TutorialStage.load(player, 21, false);
            return cook(player, object, false, initial, product);
        }
        return cook(player, object, burned, initial, product);
    }

    private final Animation getAnimation(final GameObject object) {
        return !object.getName().toLowerCase().equals("fire") ? RANGE_ANIMATION : FIRE_ANIMATION;
    }
}
