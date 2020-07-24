package plugin.skill.fletching;

import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;

import java.util.HashMap;
public class Fletching {
    public static HashMap<Integer, FletchingItems[]>logMap = new HashMap<>();
    public static HashMap<Integer, Bolts> boltMap = new HashMap<>();
    public static HashMap<Integer, Darts> dartMap = new HashMap<>();
    public static HashMap<Integer, ArrowHeads> arrowHeadMap = new HashMap<>();
    public static HashMap<Integer, GemBolts> gemMap = new HashMap<>();
    public static HashMap<Integer, GemBolts> tipMap = new HashMap<>();
    public static HashMap<Integer, String> stringMap = new HashMap<>();
    public static HashMap<Integer, Limb> limbMap = new HashMap<>();
    static{
        Items[] itemsArray = Items.values();
        int thisLength = itemsArray.length;
        for(int x = 0; x < thisLength; x++){
            Items item = itemsArray[x];
            logMap.putIfAbsent(item.id, item.items);
        }
        Bolts[] boltArray = Bolts.values();
        thisLength = boltArray.length;
        for(int x = 0; x < thisLength; x++){
            Bolts bolt = boltArray[x];
            boltMap.putIfAbsent(bolt.unfinished,bolt);
        }
        Darts[] dartArray = Darts.values();
        thisLength = dartArray.length;
        for(int x = 0; x < thisLength; x++){
            Darts dart = dartArray[x];
            dartMap.putIfAbsent(dart.unfinished,dart);
        }
        ArrowHeads[] ahArray = ArrowHeads.values();
        thisLength = ahArray.length;
        for(int x = 0; x < thisLength; x++){
            ArrowHeads arrowhead = ahArray[x];
            arrowHeadMap.putIfAbsent(arrowhead.unfinished,arrowhead);
        }
        GemBolts[] gbArray = GemBolts.values();
        thisLength = gbArray.length;
        for(int x = 0; x < thisLength; x++){
            GemBolts gem = gbArray[x];
            gemMap.putIfAbsent(gem.gem,gem);
            tipMap.putIfAbsent(gem.tip,gem);
        }
        String[] stringArray = String.values();
        thisLength = stringArray.length;
        for(int x = 0; x < thisLength; x++){
            String bow = stringArray[x];
            stringMap.putIfAbsent(bow.unfinished,bow);
        }
        Limb[] limbsArray = Limb.values();
        thisLength = limbsArray.length;
        for(int x = 0; x < thisLength; x++){
            Limb limb = limbsArray[x];
            limbMap.putIfAbsent(limb.stock, limb);
        }
    }
    public static FletchingItems[] getEntries(int id){
        return logMap.get(id);
    }
    public static boolean isLog(int id){
        return logMap.get(id) != null;
    }
    public static boolean isBolt(int id){
        return boltMap.get(id) != null;
    }
    public static boolean isDart(int id){
        return dartMap.get(id) != null;
    }
    public static boolean isArrowHead(int id){
        return arrowHeadMap.get(id) != null;
    }
    public static boolean isGemTip(int id){
        return tipMap.get(id) != null;
    }
    public static Item[] getItems(int id){
        FletchingItems[] entry = getEntries(id);
        Item items[] = {};
        switch(entry.length){
            case 1:
                items = new Item[] {new Item(entry[0].id)};
                break;
            case 2:
                items = new Item[] {new Item(entry[0].id), new Item(entry[1].id)};
                break;
            case 3:
                items = new Item[] {new Item(entry[0].id), new Item(entry[1].id), new Item(entry[2].id)};
                break;
            case 4:
                items = new Item[] {new Item(entry[0].id), new Item(entry[1].id), new Item(entry[2].id), new Item(entry[3].id)};
                break;
        }
        return items;
    }
    public enum Limb {
        WOODEN_STOCK(9440,9420,9454,9, 12, new Animation(4436)),
        OAK_STOCK(9442,9422,9176,24, 32, new Animation(4437)),
        WILLOW_STOCK(9444,9423,9457,39, 44, new Animation(4438)),
        TEAK_STOCK(9446,9425,9459,46, 54, new Animation(4439)),
        MAPLE_STOCK(9448,9427,9461,54, 64, new Animation(4440)),
        MAHOGANY_STOCK(9450,9429,9463,61, 82, new Animation(4441)),
        YEW_STOCK(9452,9431,9465,69, 100, new Animation(4442));


        public int stock, limb, product,level;
        public double experience;
        public Animation animation;

        Limb(int stock, int limb, int product, int level, double experience, Animation animation) {
            this.stock = stock;
            this.limb = limb;
            this.product = product;
            this.level = level;
            this.experience = experience;
            this.animation = animation;
        }
    }
    public enum String{
            //Bows
            SHORT_BOW((byte) 1,50,841,5, 5, new Animation(6678)),
            LONG_BOW((byte) 1,48,839,10, 10, new Animation(6684)),
            OAK_SHORTBOW((byte) 1,54,843,20, 16.5, new Animation(6679)),
            OAK_LONGBOW((byte) 1,56,845,25, 25, new Animation(6685)),
            WILLOW_SHORTBOW((byte) 1,60,849,35, 33.3, new Animation(6680)),
            WILLOW_LONGBOW((byte) 1,58,847,40, 41.5, new Animation(6686)),
            MAPLE_SHORTBOW((byte) 1,64,853,50, 50, new Animation(6681)),
            MAPLE_LONGBOW((byte) 1,62,851,55, 58.3, new Animation(6687)),
            YEW_SHORTBOW((byte) 1,68,857,65, 66, new Animation(6682)),
            YEW_LONGBOW((byte) 1,66,855,70, 75, new Animation(6688)),
            MAGIC_SHORTBOW((byte) 1,72,861,80, 83.3, new Animation(6683)),
            MAGIC_LONGBOW((byte) 1,70,859,85, 91.5, new Animation(6689)),

            //crossbows
            BRONZE_CBOW((byte) 2,9454,9174,9, 6, new Animation(6671)),
            BLURITE_CBOW((byte) 2,9456,9176,24, 16, new Animation(6672)),
            IRON_CBOW((byte) 2,9457,9177,39, 22, new Animation(6673)),
            STEEL_CBOW((byte) 2,9459,9179,46, 27, new Animation(6674)),
            MITHIRIL_CBOW((byte) 2,9461,9181,54, 32, new Animation(6675)),
            ADAMANT_CBOW((byte) 2,9463,9183,61, 41, new Animation(6676)),
            RUNITE_CBOW((byte) 2,9465,9185,69, 50, new Animation(6677));


            public int unfinished,product,string,level;
            public double experience;
            public Animation animation;
            String(byte indicator, final int unfinished, final int product, final int level, final double experience, final Animation animation) {
                this.unfinished = unfinished;
                this.product = product;
                this.level = level;
                this.experience = experience;
                this.animation = animation;
                switch(indicator & 0xFF){
                    case 1:
                        this.string = 1777;
                        break;
                    case 2:
                        this.string = 9438;
                        break;
                }
            }
        }
    public enum GemBolts {
        OPAL(877,1609,45, 879, 11, 1.5),
        PEARL(9140, 411,46, 880, 41, 3.2),
        JADE(9139,1611, 9187, 9335, 26, 2.4),
        RED_TOPAZ(9141,1613, 9188, 9336, 48, 3.9),
        SAPPHIRE(9142,1607, 9189, 9337, 56, 4),
        EMERALD(9142,1605, 9190, 9338, 58, 5.5),
        RUBY(9143, 1603,9191, 9339, 63, 6.3),
        DIAMOND(9143, 1601,9192, 9340, 65, 7),
        DRAGONSTONE(9144,1615, 9193, 9341, 71, 8.2),
        ONYX(9144,6573, 9194, 9342, 73, 9.4);

        public int gem,tip,base,product,level;
        public double experience;
        GemBolts(int base, int gem, int tip, int product, int level, double experience){
            this.gem = gem;
            this.tip = tip;
            this.base = base;
            this.product = product;
            this.level = level;
            this.experience = experience;
        }

    }
    public enum ArrowHeads {
        BRONZE_ARROW(39, 882, 1, 2.6),
        IRON_ARROW(40, 884, 15, 3.8),
        STEEL_ARROW(41, 886, 30, 6.3),
        MITHRIL_ARROW(42, 888, 45, 8.8),
        ADAMANT_ARROW(43, 890, 60, 10),
        RUNE_ARROW(44, 892, 75, 13.8),
        DRAGON_ARROW(11237, 11212, 90, 16.3),
        BROAD_ARROW(13278, 4160, 52, 10);

        public int unfinished,finished,level;
        public double experience;
        ArrowHeads(int unfinished, int finished, int level, double experience){
            this.unfinished = unfinished;
            this.finished = finished;
            this.level = level;
            this.experience = experience;
        }
        public Item getFinished(){
            return new Item(finished);
        }
        public Item getUnfinished(){
            return new Item(unfinished);
        }
    }
    public enum Darts{
        BRONZE_DART(819, 806, 1, 1.8),
        IRON_DART(820, 807, 22, 3.8),
        STEEL_DART(821, 808, 37, 7.5),
        MITHRIL_DART(822, 809, 52, 11.2),
        ADAMANT_DART(823, 810, 67, 15),
        RUNE_DART(824, 811, 81, 18.8),
        DRAGON_DART(11232, 11230, 95, 25);

        public int unfinished, finished, level;
        public double experience;
        Darts(int unfinished, int finished, int level, double experience){
            this.unfinished = unfinished;
            this.finished = finished;
            this.level = level;
            this.experience = experience;
        }
        public Item getFinished(){
            return new Item(finished);
        }
        public Item getUnfinished(){
            return new Item(unfinished);
        }
    }
    public enum Bolts{
        BRONZE_BOLT(9375, 877, 9, 0.5),
        BLURITE_BOLT(9376, 9139, 24, 1),
        IRON_BOLT(9377, 9140, 39, 1.5),
        SILVER_BOLT(9382, 9145, 43, 2.5),
        STEEL_BOLT(9378, 9141, 46, 3.5),
        MITHRIL_BOLT(9379, 9142, 54, 5),
        ADAMANTITE_BOLT(9380, 9143, 61, 7),
        RUNITE_BOLT(9381, 9144, 69, 10),
        BROAD_BOLT(13279, 13280, 55, 3);

        public int unfinished, finished, level;
        public double experience;
        Bolts(int unfinished, int finished, int level, double experience){
            this.unfinished = unfinished;
            this.finished = finished;
            this.level = level;
            this.experience = experience;
        }
        public Item getFinished(){
            return new Item(finished);
        }
        public Item getUnfinished(){
            return new Item(unfinished);
        }
    }
    public enum Items{
        STANDARD(1511,FletchingItems.ARROW_SHAFT, FletchingItems.SHORT_BOW, FletchingItems.LONG_BOW, FletchingItems.WOODEN_STOCK),
        OAK(1521, FletchingItems.OAK_SHORTBOW, FletchingItems.OAK_LONGBOW, FletchingItems.OAK_STOCK),
        WILLOW(1519, FletchingItems.WILLOW_SHORTBOW, FletchingItems.WILLOW_LONGBOW, FletchingItems.WILLOW_STOCK),
        MAPLE(1517, FletchingItems.MAPLE_SHORTOW, FletchingItems.MAPLE_LONGBOW, FletchingItems.MAPLE_STOCK),
        YEW(1515, FletchingItems.YEW_SHORTBOW, FletchingItems.YEW_LONGBOW, FletchingItems.YEW_STOCK),
        MAGIC(1513, FletchingItems.MAGIC_SHORTBOW, FletchingItems.MAGIC_LONGBOW),
        TEAK(6333, FletchingItems.TEAK_STOCK);


        FletchingItems[] items;
        int id;
        Items(int id, FletchingItems item_1, FletchingItems item_2, FletchingItems item_3, FletchingItems item_4){
            items = new FletchingItems[] {item_1, item_2, item_3, item_4};
            this.id = id;
        }
        Items(int id, FletchingItems item_1, FletchingItems item_2, FletchingItems item_3){
            items = new FletchingItems[] {item_1, item_2, item_3};
            this.id = id;
        }
        Items(int id, FletchingItems item_1, FletchingItems item_2){
            items = new FletchingItems[] {item_1, item_2};
            this.id = id;
        }
        Items(int id, FletchingItems item_1){
            items = new FletchingItems[] {item_1};
            this.id = id;
        }
    }
    public enum FletchingItems {
        //Standard logs
        ARROW_SHAFT(52, 15, 1, 15),
        SHORT_BOW(50, 5, 5, 1),
        LONG_BOW(48, 10, 10, 1),
        WOODEN_STOCK(9440, 6, 9, 1),

        //Oak logs
        OAK_SHORTBOW(54, 16.5, 20, 1),
        OAK_LONGBOW(56,25,25,1),
        OAK_STOCK(9442, 16, 24, 1),

        //Willow logs
        WILLOW_SHORTBOW(60, 33.3, 35, 1),
        WILLOW_LONGBOW(58, 41.5, 40, 1),
        WILLOW_STOCK(9444, 22, 39, 1),

        //Maple logs
        MAPLE_SHORTOW(64, 50, 50, 1),
        MAPLE_LONGBOW(62, 58.3, 55, 1),
        MAPLE_STOCK(9448, 32, 54, 1),

        //Yew logs
        YEW_SHORTBOW(68, 55, 65, 1),
        YEW_LONGBOW(66, 75, 70, 1),
        YEW_STOCK(9452, 50, 69, 1),

        //Magic logs
        MAGIC_SHORTBOW(72, 83.3, 80,1),
        MAGIC_LONGBOW(70, 91.5, 85, 1),

        //Teak
        TEAK_STOCK(9446, 27, 46,1);


        int id,level,amount,logId;
        double experience;
        FletchingItems(int id, double experience, int level, int amount){
            this.id = id;
            this.level = level;
            this.amount = amount;
            this.experience = experience;
        }

        public Item getItem(){
            return new Item(id);
        }
    }

}
