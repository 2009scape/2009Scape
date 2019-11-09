package org.crandor.game.world.callback;

import org.crandor.game.content.eco.ge.GEOfferDispatch;
import org.crandor.game.content.holiday.HolidayEvent;
import org.crandor.game.content.skill.member.farming.FarmingPulse;
import org.crandor.game.content.skill.member.hunter.ImpetuousImpulses;
import org.crandor.game.system.SystemLogger;
import org.crandor.game.world.map.zone.ZoneBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a crappy class by vexia.
 * @author 'Vexia
 */
public final class CallbackHub {

	/**
	 * Represents the list of {@link CallBack} values.
	 */
	private static List<CallBack> calls = new ArrayList<>();

	/**
	 * Constructs a new {@code CallbackHub} {@code Object}.
	 */
	public CallbackHub() {
		/***
		 * empty.
		 */
	}

	/**
	 * Method used to initializes the call back hub.
	 * @return the value {@code True} if {@link CallBack#call()} is
	 * <code>True</code>.
	 */
	public static boolean call() {
		calls.add(new ZoneBuilder());
		calls.add(new GEOfferDispatch());
		calls.add(new FarmingPulse());
		calls.add(new ImpetuousImpulses());
		for (CallBack call : calls) {
			if (!call.call()) {
				SystemLogger.error("A callback was stopped, callback=" + call.getClass().getSimpleName() + ".");
				return false;
			}
		}
		HolidayEvent.init();
		calls.clear();
		calls = null;
		return true;
	}
}
