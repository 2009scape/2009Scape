package alex.cache.loaders;

import java.util.HashMap;

import alex.util.Methods;

public class EquipIds {

	private static HashMap<Integer, Integer> equipIds = new HashMap<Integer, Integer>();
	
	public static int getEquipId(int itemId) {
		return getEquipIds(itemId, true, true);
	}
	public static int getEquipIds(int itemId, boolean putEquipIdsOnMemory, boolean putItemsOnMemory) {
		if(!equipIds.isEmpty()) {
			if(!equipIds.containsKey(itemId))
				return -1;
			return equipIds.get(itemId);
		}
		int equipId = 0;
		for(int itemIds = 0; itemIds < Methods.getAmountOfItems(); itemIds++) {
			ItemDefinition itemDef = putItemsOnMemory ? ItemDefinition.getItemDefinition(itemIds) : new ItemDefinition(itemIds);
			if(itemDef.getMaleWornModelId1()>= 0 || itemDef.getMaleWornModelId2()>= 0) {
				if(putEquipIdsOnMemory) 
					equipIds.put(itemId, equipId);
				else {
					if(itemIds == itemId)
						return equipId;
				}
				equipId++;
					
			}
		}
		if(!equipIds.containsKey(itemId))
			return -1;
		return equipIds.get(itemId);
	}
}
