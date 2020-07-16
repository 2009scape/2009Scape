package plugin.ai.system.predicate.impl;


import plugin.ai.system.predicate.Predicate;
import plugin.ai.system.predicate.PredicateType;

/**
 * Generates random appearance for the droid.
 * 
 * @author Viper
 * @version 1.0 - 13/07/2016
 */
public class AppearancePredicate extends Predicate {
	
	/**
	 * If the bot is a male.
	 */
	private boolean male;
	
	/**
	 * Constructs a new appearance predicate.
	 */
	public AppearancePredicate(boolean male) {
		super(-1);
		this.male = male;
	}
	
	@Override
	public PredicateType getType() {
		return PredicateType.PARSING;
	}

	@Override
	public void run() {
		if (isMale()) {
			/*bot.getAppearence().getBodyStyle()[0] = Utility.random(0, 8);
			bot.getAppearence().getBodyStyle()[1] = Utility.random(10, 17);
			bot.getAppearence().getBodyStyle()[2] = Utility.random(18, 25);
			bot.getAppearence().getBodyStyle()[3] = Utility.random(26, 32);
			bot.getAppearence().getBodyStyle()[4] = Utility.random(33, 34);
			bot.getAppearence().getBodyStyle()[5] = Utility.random(36, 41);
			bot.getAppearence().getBodyStyle()[6] = Utility.random(42, 44);

			bot.getAppearence().getBodyColors()[2] = (byte) Utility.random(0, 127);
			bot.getAppearence().getBodyColors()[1] = (byte) Utility.random(0, 127);
			bot.getAppearence().getBodyColors()[0] = (byte) Utility.random(0, 24);*/
		} else {
			
		}
//		bot.getAppearence().generateAppearenceData();
	}

	/**
	 * @return the male
	 */
	public boolean isMale() {
		return male;
	}

}