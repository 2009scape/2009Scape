package plugin.blackjack;

public abstract class BlackJackSession {
    public abstract boolean tick();
    public boolean ongoing;
    public boolean updated;
}
