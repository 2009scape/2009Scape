package plugin.ai.skillingbot;

import core.game.world.map.Location;

import java.util.ArrayList;

import plugin.skill.Skills;
import core.game.interaction.Option;
import core.game.node.Node;
import plugin.ai.AIPlayer;
import core.game.node.item.Item;
import core.net.packet.in.InteractionPacket;

public class SkillingBot extends AIPlayer {

	private int tick = 5;
	private ArrayList<Integer> interactNodeIds;
	private int fromWhereDoIdrop;
	private int skill;
	private int interactionRange;
	
	public SkillingBot(Location l)
	{
		super(l);
		this.fromWhereDoIdrop = 0;
		this.interactionRange = 15;
	}

	public SkillingBot(Location l, int skill, ArrayList<Integer> entrys)
	{
		super(l);
		this.skill = skill;
		this.fromWhereDoIdrop = 0;
		this.interactNodeIds = entrys;
		this.interactionRange = 15;

		switch (this.skill)
		{
			case Skills.MINING:
			default:
				break;
		}
	}
	
	@Override
	public void tick()
	{
		super.tick();
		
		//Despawn
		if (this.getSkills().getLifepoints() == 0)
			AIPlayer.deregister(this.getUid());
		
		//Interact with object
		if (this.tick <= 0) 
		{
			this.tick = 5;
			if (this.skill == Skills.FISHING)
				this.tick = 20;

			// Node test = getClosestNodeWithEntry(15, 15503);
			Node node;
			if (this.skill != Skills.FISHING)
			{
				node = getClosestNodeWithEntry(interactionRange, interactNodeIds);
			}
			else
			{
				node = getClosesCreature(interactionRange, interactNodeIds);
//				if (test != null)
//					System.out.println("interact with " + test.getName());
			}
			
			if (node == null) {
				System.out.println("SkillingBot.java: Object not found " + this.skill);
				return;
			}
			
			//System.out.println("free slots " + this.getInventory().freeSlots());
			
			if (this.getInventory().isFull())
			{
//				System.out.println(this.getName() + " starts droping from " + fromWhereDoIdrop);
				for (int i = fromWhereDoIdrop; i < 28; i++)
				{
					Item drop = this.getInventory().get(i);
					final Option option = drop.getInteraction().get(4);
					drop.getInteraction().handleItemOption(this, option, this.getInventory());
//					System.out.println("drop item " + i);
				}
			}

			int x = node.getLocation().getX();
			int y = node.getLocation().getY();
			if (this.skill != Skills.FISHING)
			{
				InteractionPacket.handleObjectInteraction(this, 0, x, y, node.getId());
			}
			else
			{
				InteractionPacket.handleNPCInteraction(this, 0, node.getIndex());
			}
		}
		else 
			this.tick--;
	}
	
	public int getSkill()
	{
		return this.skill;
	}
	
	public int getFromWhereToDrop()
	{
		return this.fromWhereDoIdrop;
	}
	
	public void setFromWhereDoIdrop(int id)
	{
		this.fromWhereDoIdrop = id;
	}
	
	public void setInteractionRange(int range)
	{
		this.interactionRange = range;
	}

}
