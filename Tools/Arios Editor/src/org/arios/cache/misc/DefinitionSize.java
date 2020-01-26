package org.arios.cache.misc;

import org.arios.cache.Cache;
import org.arios.cache.def.impl.AnimationDefinition;
import org.arios.cache.def.impl.GraphicDefinition;
import org.arios.cache.def.impl.NPCDefinition;
import org.arios.cache.def.impl.ObjectDefinition;

/**
 * Represents a class that stores the size of mutliple definition type sizes.
 * @author 'Vexia
 *
 */
public final class DefinitionSize {
	
	/**
	 * Method used to return the component size of the interface.
	 * @param interfaceId the interface.
	 * @return the value.
	 */
	public static final int getInterfaceDefinitionsComponentsSize(int interfaceId) {
		return Cache.getIndexes()[3].getFilesSize(interfaceId);
	}
	
	/**
	 * Method used to return the max size of the interface definitions.
	 * @return the size.
	 */
	public static final int getInterfaceDefinitionsSize() {
		return Cache.getIndexes()[3].getContainersSize();
	}
	
	/**
	 * Method used to return the item definition size.
	 * @return the size.
	 */
	public static final int getItemDefinitionsSize() {
		int lastContainerId = Cache.getIndexes()[19].getContainersSize() - 1;
		return lastContainerId* 256+ Cache.getIndexes()[19].getFilesSize(lastContainerId);
	}
	
	/**
	 * Method used to return the {@link NPCDefinition} size.
	 * @return the size.
	 */
	public static final int getNPCDefinitionsSize() {
		int lastContainerId = Cache.getIndexes()[18].getContainersSize() - 1;
		return lastContainerId* 128+ Cache.getIndexes()[18].getFilesSize(lastContainerId);
	}
	
	/**
	 * Method used to return the {@link ObjectDefinition} size.
	 * @return the size.
	 */
	public static final int getObjectDefinitionsSize() {
		int lastContainerId = Cache.getIndexes()[16].getContainersSize() - 1;
		return lastContainerId* 256+ Cache.getIndexes()[16]	.getFilesSize(lastContainerId);
	}
	
	/**
	 * Method used to return the {@link AnimationDefinition} size.
	 * @return the size.
	 */
	public static final int getAnimationDefinitionsSize() {
		int lastContainerId = Cache.getIndexes()[20].getContainersSize() - 1;
		return lastContainerId* 128+ Cache.getIndexes()[20].getFilesSize(lastContainerId);
	}
	
	/**
	 * Method used to return the {@link GraphicDefinition} size.
	 * @return the size.
	 */
	public static final int getGraphicDefinitionsSize() {
		int lastContainerId = Cache.getIndexes()[21].getContainersSize() - 1;
		return lastContainerId* 256+ Cache.getIndexes()[21].getFilesSize(lastContainerId);
	}
}