package plugin.blackjack;

import core.tools.RandomFunction;

public class BlackJackCardFactory {
    public enum BlackJackCard{
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        KING,
        QUEEN,
        JACK
    }

    public static BlackJackCard getNewCard(){
        int length = BlackJackCard.values().length;
        return BlackJackCard.values()[RandomFunction.random(0,length)];
    }
}
