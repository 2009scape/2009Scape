package org.crandor.net.packet;

import org.crandor.net.packet.in.*;
import org.crandor.net.packet.out.*;
import org.crandor.net.packet.out.GrandExchangePacket;

import java.util.HashMap;
import java.util.Map;

/**
 * The packet repository.
 * @author Emperor
 */
public final class PacketRepository {

	/**
	 * The outgoing packets mapping.
	 */
	private final static Map<Class<?>, OutgoingPacket<? extends Context>> OUTGOING_PACKETS = new HashMap<>();

	/**
	 * The incoming packets mapping.
	 */
	private final static Map<Integer, IncomingPacket> INCOMING_PACKETS = new HashMap<>();

	/**
	 * Populate the mappings.
	 */
	static {
		OUTGOING_PACKETS.put(LoginPacket.class, new LoginPacket());
		OUTGOING_PACKETS.put(UpdateSceneGraph.class, new UpdateSceneGraph());
		OUTGOING_PACKETS.put(WindowsPane.class, new WindowsPane());
		OUTGOING_PACKETS.put(Interface.class, new Interface());
		OUTGOING_PACKETS.put(SkillLevel.class, new SkillLevel());
		OUTGOING_PACKETS.put(Config.class, new Config());
		OUTGOING_PACKETS.put(AccessMask.class, new AccessMask());
		OUTGOING_PACKETS.put(GameMessage.class, new GameMessage());
		OUTGOING_PACKETS.put(RunScriptPacket.class, new RunScriptPacket());
		OUTGOING_PACKETS.put(RunEnergy.class, new RunEnergy());
		OUTGOING_PACKETS.put(ContainerPacket.class, new ContainerPacket());
		OUTGOING_PACKETS.put(StringPacket.class, new StringPacket());
		OUTGOING_PACKETS.put(Logout.class, new Logout());
		OUTGOING_PACKETS.put(CloseInterface.class, new CloseInterface());
		OUTGOING_PACKETS.put(AnimateInterface.class, new AnimateInterface());
		OUTGOING_PACKETS.put(DisplayModel.class, new DisplayModel());
		OUTGOING_PACKETS.put(InterfaceConfig.class, new InterfaceConfig());
		OUTGOING_PACKETS.put(PingPacket.class, new PingPacket());
		OUTGOING_PACKETS.put(UpdateAreaPosition.class, new UpdateAreaPosition());
		OUTGOING_PACKETS.put(ConstructObject.class, new ConstructObject());
		OUTGOING_PACKETS.put(ClearObject.class, new ClearObject());
		OUTGOING_PACKETS.put(HintIcon.class, new HintIcon());
		OUTGOING_PACKETS.put(ClearMinimapFlag.class, new ClearMinimapFlag());
		OUTGOING_PACKETS.put(InteractionOption.class, new InteractionOption());
		OUTGOING_PACKETS.put(SetWalkOption.class, new SetWalkOption());
		OUTGOING_PACKETS.put(MinimapState.class, new MinimapState());
		OUTGOING_PACKETS.put(ConstructGroundItem.class, new ConstructGroundItem());
		OUTGOING_PACKETS.put(ClearGroundItem.class, new ClearGroundItem());
		OUTGOING_PACKETS.put(RepositionChild.class, new RepositionChild());
		OUTGOING_PACKETS.put(PositionedGraphic.class, new PositionedGraphic());
		OUTGOING_PACKETS.put(SystemUpdatePacket.class, new SystemUpdatePacket());
		OUTGOING_PACKETS.put(CameraViewPacket.class, new CameraViewPacket());
		OUTGOING_PACKETS.put(MusicPacket.class, new MusicPacket());
		OUTGOING_PACKETS.put(AudioPacket.class, new AudioPacket());
		OUTGOING_PACKETS.put(GrandExchangePacket.class, new GrandExchangePacket());
		OUTGOING_PACKETS.put(BuildDynamicScene.class, new BuildDynamicScene());
		OUTGOING_PACKETS.put(AnimateObjectPacket.class, new AnimateObjectPacket());
		OUTGOING_PACKETS.put(ClearRegionChunk.class, new ClearRegionChunk());
		OUTGOING_PACKETS.put(ContactPackets.class, new ContactPackets());
		OUTGOING_PACKETS.put(CommunicationMessage.class, new CommunicationMessage());
		OUTGOING_PACKETS.put(UpdateClanChat.class, new UpdateClanChat());
		OUTGOING_PACKETS.put(UpdateGroundItemAmount.class, new UpdateGroundItemAmount());
		OUTGOING_PACKETS.put(WeightUpdate.class, new WeightUpdate());
		OUTGOING_PACKETS.put(UpdateRandomFile.class, new UpdateRandomFile());
		OUTGOING_PACKETS.put(InstancedLocationUpdate.class, new InstancedLocationUpdate());
		OUTGOING_PACKETS.put(CSConfigPacket.class, new CSConfigPacket());
		INCOMING_PACKETS.put(22, new ClientFocusPacket());
		INCOMING_PACKETS.put(93, new PingPacketHandler());
		INCOMING_PACKETS.put(44, new CommandPacket());
		INCOMING_PACKETS.put(237, new ChatPacket());
		INCOMING_PACKETS.put(21, new CameraMovementPacket());
		INCOMING_PACKETS.put(75, new MouseClickPacket());
		INCOMING_PACKETS.put(243, new DisplayUpdatePacket());
		INCOMING_PACKETS.put(177, new UpdateInterfaceCounter());
		INCOMING_PACKETS.put(4, new MusicPacketHandler());
		INCOMING_PACKETS.put(245, new IdlePacketHandler());
		INCOMING_PACKETS.put(111, new org.crandor.net.packet.in.GrandExchangePacket());
		IncomingPacket packet = new WalkPacket();
		INCOMING_PACKETS.put(39, packet);
		INCOMING_PACKETS.put(77, packet);
		INCOMING_PACKETS.put(215, packet);
		packet = new ItemActionPacket();
		INCOMING_PACKETS.put(134, packet);//item on object
		INCOMING_PACKETS.put(115, packet);//on npc
		INCOMING_PACKETS.put(27, packet);//item on item
		INCOMING_PACKETS.put(248, packet);//on player	
		INCOMING_PACKETS.put(3, packet = new InteractionPacket());
		INCOMING_PACKETS.put(180, packet);//Player interact options v
		INCOMING_PACKETS.put(68, packet);
		INCOMING_PACKETS.put(71, packet);
		INCOMING_PACKETS.put(114, packet);
		INCOMING_PACKETS.put(175, packet);//Player interact options ^
		INCOMING_PACKETS.put(30, packet);
		INCOMING_PACKETS.put(78, packet);
		INCOMING_PACKETS.put(148, packet);
		INCOMING_PACKETS.put(218, packet);
		INCOMING_PACKETS.put(84, packet);
		INCOMING_PACKETS.put(170, packet);
		INCOMING_PACKETS.put(254, packet);
		INCOMING_PACKETS.put(194, packet);
		INCOMING_PACKETS.put(66, packet);
		INCOMING_PACKETS.put(33, packet);
		INCOMING_PACKETS.put(247, packet);
		INCOMING_PACKETS.put(156, packet = new ActionButtonPacket()); //Item V
		INCOMING_PACKETS.put(55, packet);
		INCOMING_PACKETS.put(153, packet);
		INCOMING_PACKETS.put(161, packet);
		INCOMING_PACKETS.put(135, packet); //^
		INCOMING_PACKETS.put(81, packet);
		INCOMING_PACKETS.put(184, packet);//close interface
		INCOMING_PACKETS.put(155, packet); //Interface V
		INCOMING_PACKETS.put(196, packet);
		INCOMING_PACKETS.put(124, packet);
		INCOMING_PACKETS.put(199, packet);
		INCOMING_PACKETS.put(234, packet);
		INCOMING_PACKETS.put(168, packet);
		INCOMING_PACKETS.put(166, packet);
		INCOMING_PACKETS.put(64, packet);
		INCOMING_PACKETS.put(53, packet);
		INCOMING_PACKETS.put(9, packet); //^
		INCOMING_PACKETS.put(132, packet); //Dialogue
		INCOMING_PACKETS.put(10, packet); //logout
		INCOMING_PACKETS.put(206, packet);//operate
		INCOMING_PACKETS.put(72, packet = new ExaminePacket());
		INCOMING_PACKETS.put(92, packet);
		INCOMING_PACKETS.put(94, packet);
		INCOMING_PACKETS.put(57, packet);
		INCOMING_PACKETS.put(34, packet);
		INCOMING_PACKETS.put(213, packet);
		//INCOMING_PACKETS.put(69, packet);
		//packet 98 - 530 settings interface
		INCOMING_PACKETS.put(104, packet = new ClanPacketHandler());
		INCOMING_PACKETS.put(188, packet);
		INCOMING_PACKETS.put(162, packet);
		INCOMING_PACKETS.put(157, new ChatSettingsPacket());
		INCOMING_PACKETS.put(244, packet = new RunScriptPacketHandler());
		INCOMING_PACKETS.put(23, packet);
		INCOMING_PACKETS.put(65, packet);
		INCOMING_PACKETS.put(110, new RegionChangePacket());
		INCOMING_PACKETS.put(195, packet = new InterfaceUseOnPacket());
		INCOMING_PACKETS.put(239, packet);
		INCOMING_PACKETS.put(73, packet);
		INCOMING_PACKETS.put(253, packet);
	    INCOMING_PACKETS.put(233, packet);
		INCOMING_PACKETS.put(231, packet = new SlotSwitchPacket());
		INCOMING_PACKETS.put(79, packet);
		INCOMING_PACKETS.put(167, new QuickChatPacket());	
		INCOMING_PACKETS.put(201, packet = new CommunicationPacket());
		INCOMING_PACKETS.put(120, packet);
		INCOMING_PACKETS.put(57, packet);
		INCOMING_PACKETS.put(34, packet);
		INCOMING_PACKETS.put(213, packet);
		INCOMING_PACKETS.put(99, packet = new ReportAbusePacket());
		// INCOMING_PACKETS.put(77, packet);
		// INCOMING_PACKETS.put(191, packet);
		// INCOMING_PACKETS.put(139, packet);
		// INCOMING_PACKETS.put(251, packet);
		// INCOMING_PACKETS.put(55, packet);
	}

	/**
	 * Sends a new packet.
	 * @param clazz The class of the outgoing packet to send.
	 * @param context The context.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void send(Class<? extends OutgoingPacket> clazz, Context context) {
		OutgoingPacket p = OUTGOING_PACKETS.get(clazz);
		if (p == null) {
			System.err.println("Invalid outgoing packet [handler=" + clazz + ", context=" + context + "].");
			return;
		}
		p.send(context);
	}

	/**
	 * Gets an incoming packet.
	 * @param opcode The opcode.
	 * @return The incoming packet.
	 */
	public static IncomingPacket getIncoming(int opcode) {
		return INCOMING_PACKETS.get(opcode);
	}
}