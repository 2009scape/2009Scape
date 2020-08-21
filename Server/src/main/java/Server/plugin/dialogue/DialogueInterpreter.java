package plugin.dialogue;

import core.cache.def.impl.ItemDefinition;
import core.cache.def.impl.NPCDefinition;
import core.game.component.Component;
import core.game.system.config.ItemConfigParser;
import plugin.quest.tutorials.tutorialisland.TutorialSession;
import core.game.node.entity.Entity;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.system.script.ScriptContext;
import core.game.system.script.ScriptManager;
import core.game.system.script.context.*;
import core.net.packet.PacketRepository;
import core.net.packet.context.ContainerContext;
import core.net.packet.out.ContainerPacket;
import core.plugin.PluginManifest;
import core.plugin.PluginType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the dialogues.
 * @author Emperor
 */
@PluginManifest(type = PluginType.DIALOGUE)
public final class DialogueInterpreter {

    /**
     * The dialogue plugins.
     */
    private static final Map<Integer, DialoguePlugin> PLUGINS = new HashMap<>();

    /**
     * The dialogue scripts.
     */
    private static final Map<Integer, ScriptContext> SCRIPTS = new HashMap<>();

    /**
     * a List of dialogue actions.
     */
    private final List<DialogueAction> actions = new ArrayList<>();

    /**
     * The currently opened dialogue.
     */
    private DialoguePlugin dialogue;

    /**
     * Scripted dialogue current stage.
     */
    private ScriptContext dialogueStage;

    /**
     * The current dialogue key.
     */
    private int key;

    /**
     * The player.
     */
    private final Player player;

    /**
     * Constructs a new {@code DialogueInterpreter} {@code Object}.
     * @param player The player.
     */
    public DialogueInterpreter(Player player) {
        this.player = player;
    }

    /**
     * @param dialogue the dialogue to set.
     */
    public void setDialogue(DialoguePlugin dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * Opens the dialogue for the given dialogue type.
     * @param dialogueType The dialogue type.
     * @param args the args.
     * @return {@code True} if successful.
     */
    public boolean open(String dialogueType, Object... args) {
        return open(getDialogueKey(dialogueType), args);
    }

    /**
     * Opens the dialogue for the given NPC id.
     * @param dialogueKey The dialogue key (usually NPC id).
     * @param args The arguments.
     * @return {@code True} if successful.
     */
    public boolean open(int dialogueKey, Object... args) {
        key = dialogueKey;
        if (args.length > 0 && args[0] instanceof NPC) {
            NPC npc = (NPC) args[0];
            npc.setDialoguePlayer(player);
            try { //this prevents the dialogue interpreter from dying when you try opening an NPC's dialogue without passing the NPC itself as an argument
                npc.getWalkingQueue().reset();
                npc.getPulseManager().clear();
            } catch(Exception e){
            }
        } else if (args.length < 1) {
            args = new Object[] { dialogueKey };
        }
        ScriptContext script = SCRIPTS.get(dialogueKey);
        if (script != null) {
            Object[] arguments = new Object[args.length + 1];
            for (int i = 0; i < args.length; i++) {
                arguments[i + 1] = args[i];
            }
            arguments[0] = player;
            startScript(script, arguments);
            return true;
        }
        DialoguePlugin plugin = PLUGINS.get(dialogueKey);
        if (plugin == null) {
            return false;
        }
        if (player.isDebug()) {
            player.sendMessage("Dialogue opening - " + plugin.getClass().getSimpleName() + ", key=" + dialogueKey + "");
        }
        this.dialogue = plugin.newInstance(player);
        if (dialogue == null || !dialogue.open(args)) {
            dialogue = null;
            return false;
        }
        return true;
    }

    /**
     * Starts a dialogue script.
     * @param script The script.
     * @param args The arguments.
     */
    public void startScript(ScriptContext script, Object... args) {
        startScript(key, script, args);
    }

    /**
     * Starts a dialogue script.
     * @param dialogueKey The dialogue key.
     * @param script The script.
     * @param args The arguments.
     */
    public void startScript(int dialogueKey, ScriptContext script, Object... args) {
        key = dialogueKey;
        (dialogueStage = script).execute(args);
        if (script != null && script.isInstant()) {
            dialogueStage = script = ScriptManager.run(script, args);
        }
    }

    /**
     * Handles an dialogue input.
     * @param componentId The id of the chatbox component.
     * @param buttonId The button id.
     */
    public void handle(int componentId, int buttonId) {
        if (dialogueStage != null) {
            dialogueStage = ScriptManager.run(dialogueStage, player, key, buttonId);
            if (!(dialogueStage instanceof PDialInstruction || dialogueStage instanceof NPCDialInstruction || dialogueStage instanceof OptionDialInstruction || dialogueStage instanceof PlainMessageInstruction || dialogueStage instanceof ItemMessageInstruction)) {
                player.getInterfaceManager().closeChatbox();
            }
            return;
        }
        player.getDialogueInterpreter().getDialogue().handle(componentId, buttonId - 1);//here
    }

    /**
     * Closes the current dialogue.
     * @return {@code True} if successful.
     */
    public boolean close() {
        if (dialogue != null || dialogueStage != null) {
            actions.clear();
            if (player.getInterfaceManager().getChatbox() != null && player.getInterfaceManager().getChatbox().getCloseEvent() != null) {
                return true;
            }
            if (dialogueStage != null) {
                dialogueStage = null;
                player.getInterfaceManager().closeChatbox();
            }
            if (dialogue != null && dialogue.close()) {
                dialogue = null;
            }
        }
        return dialogue == null && dialogueStage == null;
    }

    /**
     * Puts a dialogue plugin on the mapping.
     * @param id The NPC id (or {@code 1 << 16 | dialogueId} when the dialogue
     * isn't for an NPC).
     * @param plugin The plugin.
     */
    public static void add(int id, DialoguePlugin plugin) {
        if (PLUGINS.containsKey(id)) {
            throw new IllegalArgumentException("Dialogue " + (id & 0xFFFF) + " is already in use - [old=" + PLUGINS.get(id).getClass().getSimpleName() + ", new=" + plugin.getClass().getSimpleName() + "]!");
        }
        PLUGINS.put(id, plugin);
    }

    /**
     * Adds a dialogue script for the given key.
     * @param dialogueKey The dialogue key.
     * @param context The dialogue script.
     */
    public static void add(int dialogueKey, ScriptContext context) {
        if (SCRIPTS.containsKey(dialogueKey)) {
            // throw new IllegalArgumentException("Dialogue " + dialogueKey +
            // " is already in use - [old=" +
            // SCRIPTS.get(dialogueKey).getClass().getSimpleName() + ", new=" +
            // context.getClass().getSimpleName() + "]!");
        }
        SCRIPTS.put(dialogueKey, context);
    }

    /**
     * Gets the script context for the given dialogue key.
     * @param key The dialogue key.
     * @return The script context.
     */
    public static ScriptContext getScript(int key) {
        return SCRIPTS.get(key);
    }

    /**
     * Send plane messages based on the amount of specified messages.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogue(String... messages) {
        if (messages.length < 1 || messages.length > 4) {
            return null;
        }
        int interfaceId = 209 + messages.length;
        for (int i = 0; i < messages.length; i++) {
            player.getPacketDispatch().sendString(messages[i], interfaceId, i + 1);
        }
        player.getInterfaceManager().openChatbox(interfaceId);
        if (player.getAttribute("tut-island", false)) {
           
        }
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), 1, false);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Sends a plane message and hides the continue button.
     * @param hideContinue if we should hide it or not.
     * @param messages the messages.
     * @return the component.
     */
    public Component sendPlainMessage(final boolean hideContinue, String... messages) {
        sendDialogue(messages);
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), (messages.length + 1), hideContinue);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Opens the destroy item chatbox interface.
     * @param id The item id.
     * @param message The message to display.
     * @return The component.
     */
    public Component sendDestroyItem(int id, String message) {
        player.getInterfaceManager().openChatbox(94);
        PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 94, 93, 93, new Item[] { new Item(id) }, 1, false));
        String text = ItemDefinition.forId(id).getConfiguration(ItemConfigParser.DESTROY_MESSAGE, "Are you sure you want to destroy this object?");
        if (text.length() > 200) {
            String[] words = text.split(" ");
            StringBuilder sb = new StringBuilder(words[0]);
            for (int i = 1; i < words.length; i++) {
                if (i == (words.length / 2)) {
                    sb.append("<br>");
                } else {
                    sb.append(" ");
                }
                sb.append(words[i]);
            }
            text = sb.toString();
        }
        player.getPacketDispatch().sendString("Are you sure you want to destroy this object?", 94, 2);
        player.getPacketDispatch().sendString("Yes.", 94, 3);
        player.getPacketDispatch().sendString("No.", 94, 4);
        player.getPacketDispatch().sendString(text, 94, 7);
        player.getPacketDispatch().sendString(ItemDefinition.forId(id).getName(), 94, 8);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send plane messages with a blue title.
     * @param title The title.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendPlaneMessageWithBlueTitle(String title, String... messages) {
        player.getPacketDispatch().sendString(title, 372, 0);
        for (int i = 0; i < messages.length; i++) {
            player.getPacketDispatch().sendString(messages[i], 372, i + 1);
        }
        player.getInterfaceManager().openChatbox(372);
        if (player.getAttributes().containsKey("tut-island") || TutorialSession.getExtension(player).getStage() < TutorialSession.MAX_STAGE) {
        }
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send plane messages with scroll and a blue title.
     * @param title The title.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendScrollMessageWithBlueTitle(String title, String... messages) {
        for (int i = 0; i < 11; i++) {
            player.getPacketDispatch().sendString(" ", 421, i + 2);
        }
        player.getPacketDispatch().sendString(title, 421, 1);
        for (int i = 0; i < messages.length; i++) {
            player.getPacketDispatch().sendString(messages[i], 421, i + 2);
        }
        player.getInterfaceManager().openChatbox(421);
        if (player.getAttributes().containsKey("tut-island")) {
        }
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send a message with an item next to it.
     * @param itemId The item id.
     */
    public Component sendItemMessage(int itemId, String... messages) {
        player.getInterfaceManager().openChatbox(131);
        String message = messages[0];
        for (int i = 1; i < messages.length; i++) {
            message += "<br>" + messages[i];
        }
        player.getPacketDispatch().sendString(message, 131, 1);
        player.getPacketDispatch().sendItemOnInterface(itemId, 1, 131, 2);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send a message with an item next to it.
     */
    public Component sendItemMessage(final Item item, String... messages) {
        player.getInterfaceManager().openChatbox(131);
        String message = messages[0];
        for (int i = 1; i < messages.length; i++) {
            message += "<br>" + messages[i];
        }
        player.getPacketDispatch().sendString(message, 131, 1);
        player.getPacketDispatch().sendItemOnInterface(item.getId(), item.getAmount(), 131, 2);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send a message with an item next to it.
     * @param message The message.
     */
    public Component sendDoubleItemMessage(int first, int second, String message) {
        player.getInterfaceManager().openChatbox(131);
        player.getPacketDispatch().sendString(message, 131, 1);
        player.getPacketDispatch().sendItemOnInterface(first, 1, 131, 0);
        player.getPacketDispatch().sendItemOnInterface(second, 1, 131, 2);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send a message with an item next to it.
     * @param message The message.
     */
    public Component sendDoubleItemMessage(Item first, Item second, String message) {
        player.getInterfaceManager().openChatbox(131);
        player.getPacketDispatch().sendString(message, 131, 1);
        player.getPacketDispatch().sendItemOnInterface(first.getId(), first.getAmount(), 131, 0);
        player.getPacketDispatch().sendItemOnInterface(second.getId(), second.getAmount(), 131, 2);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param entity The entity.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogues(Entity entity, FacialExpression expression, String... messages) {
        return sendDialogues(entity, expression == null ? -1 : expression.getAnimationId(), messages);
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param entity The entity.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogues(Entity entity, int expression, String... messages) {
        return sendDialogues(entity instanceof Player ? -1 : ((NPC) entity).getShownNPC(player).getId(), expression, messages);
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param npcId The npc id.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @param hide the continue.
     * @return The chatbox component.
     */
    public Component sendDialogues(int npcId, FacialExpression expression, boolean hide, String... messages) {
        sendDialogues(npcId, expression == null ? -1 : expression.getAnimationId(), messages);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param npcId The npc id.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @param hide the continue.
     * @return The chatbox component.
     */
    public Component sendDialogues(Entity entity, FacialExpression expression, boolean hide, String... messages) {
        sendDialogues(entity, expression, messages);
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), 3, hide);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param npcId The npc id.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @param hide the continue.
     * @return The chatbox component.
     */
    public Component sendDialogues(Entity entity, int expression, boolean hide, String... messages) {
        sendDialogues(entity, expression, messages);
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), 3, hide);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param entity The entity.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogues(int npcId, int expression, boolean hide, String... messages) {
        sendDialogues(npcId, expression, messages);
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), 3, hide);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param npcId The npc id.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogues(int npcId, FacialExpression expression, String... messages) {
        return sendDialogues(npcId, expression == null ? -1 : expression.getAnimationId(), messages);
    }

    /**
     * Send dialogues based on the amount of specified messages.
     * @param npcId The npc id.
     * @param expression The entity's facial expression.
     * @param messages The messages.
     * @return The chatbox component.
     */
    public Component sendDialogues(int npcId, int expression, String... messages) {
        if (messages.length < 1 || messages.length > 4) {
            return null;
        }
        boolean npc = npcId > -1;
        int interfaceId = (npc ? 240 : 63) + messages.length;
        if (expression == -1) {
            expression = FacialExpression.HALF_GUILTY.getAnimationId();
        }
        player.getPacketDispatch().sendAnimationInterface(expression, interfaceId, 2);
        if (npc) {
            player.getPacketDispatch().sendNpcOnInterface(npcId, interfaceId, 2);
            player.getPacketDispatch().sendString(NPCDefinition.forId(npcId).getName(), interfaceId, 3);
        } else {
            player.getPacketDispatch().sendPlayerOnInterface(interfaceId, 2);
            player.getPacketDispatch().sendString(player.getUsername(), interfaceId, 3);
        }
        for (int i = 0; i < messages.length; i++) {
            player.getPacketDispatch().sendString(messages[i].toString().replace("@name", player.getUsername()), interfaceId, (i + 4));
        }
        player.getInterfaceManager().openChatbox(interfaceId);
        if (player.getAttributes().containsKey("tut-island") || TutorialSession.getExtension(player).getStage() < TutorialSession.MAX_STAGE) {
        }
        player.getPacketDispatch().sendInterfaceConfig(player.getInterfaceManager().getChatbox().getId(), 3, false);
        return player.getInterfaceManager().getChatbox();
    }

    /**
     * Send options based on the amount of specified options.
     * @param title The title.
     * @param options The options.
     */
    public void sendOptions(Object title, String... options) {
        int interfaceId = 224 + (2 * options.length);
        if (options.length < 2 || options.length > 5) {
            return;
        }
        if (title != null) {
            player.getPacketDispatch().sendString(title.toString(), interfaceId, 1);
        }
        for (int i = 0; i < options.length; i++) {
            player.getPacketDispatch().sendString(options[i].toString(), interfaceId, i + 2);
        }
        if (player.getAttributes().containsKey("tut-island")) {
        }
        player.getInterfaceManager().openChatbox(interfaceId);
    }

    /**
     * Send a input run script.
     * @param string The strings.
     * @param objects The arguments.
     */
    public void sendInput(boolean string, Object... objects) {
        player.getPacketDispatch().sendRunScript(string ? 109 : 108, "s", objects);
    }

    /**
     * Sends a long input.
     * @param objects the objects.
     */
    public void sendLongInput(Object... objects) {
        player.getPacketDispatch().sendRunScript(110, "s", objects);
    }

    /**
     * Sends the private message input.
     * @param reciever The receiver.
     */
    public void sendMessageInput(String reciever) {
        player.getPacketDispatch().sendRunScript(107, "s", reciever);
    }

    /**
     * Checks if the dialogue for the given id is added.
     * @param id The NPC id/dialogue id.
     * @return {@code True} if so.
     */
    public static boolean contains(int id) {
        return PLUGINS.containsKey(id);
    }

    /**
     * Gets the currently opened dialogue.
     * @return The dialogue plugin.
     */
    public DialoguePlugin getDialogue() {
        return dialogue;
    }

    /**
     * Reserves a key for the name.
     * @param name The name.
     * @return The key.
     */
    public static int getDialogueKey(String name) {
        return 1 << 16 | name.hashCode();
    }

    /**
     * Gets the dialogueStage.
     * @return The dialogueStage.
     */
    public ScriptContext getDialogueStage() {
        return dialogueStage;
    }

    /**
     * Sets the dialogueStage.
     * @param dialogueStage The dialogueStage to set.
     */
    public void setDialogueStage(ScriptContext dialogueStage) {
        this.dialogueStage = dialogueStage;
    }

    /**
     * Adds a dialogue reward.
     * @param action the reward.
     */
    public void addAction(DialogueAction action) {
        actions.clear();
        actions.add(action);
    }

    /**
     * Gets the actions.
     * @return The actions.
     */
    public List<DialogueAction> getActions() {
        return actions;
    }
}