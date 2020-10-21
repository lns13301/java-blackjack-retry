package domain.game;

import domain.card.Card;
import domain.user.Player;
import view.Output;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private static final int BURST_VALUE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int NULL_VALUE = 0;

    private List<Player> players;

    public Participant(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int pickCardFromDeck(List<Card> cards, int index) {
        for (Player player : players) {
            player.addCard(cards.get(index++));
        }

        return index;
    }

    public int pickOneMoreCard(List<Card> cards, int index) {
        for (Player player : players) {
            Output.showCardStatePlayer(player);
            index = pickOneCard(cards, index, player);
        }

        return index;
    }

    private int pickOneCard(List<Card> cards, int index, Player player) {
        int checkedValue = checkBlackjackAndBurst(player, index);

        if (checkedValue != NULL_VALUE) {
            return checkedValue;
        }

        if (Output.showGetOneMoreCard(player.getName())) {
            player.addCard(cards.get(index++));
            Output.showCardStatePlayer(player);

            pickOneCard(cards, index, player);
        }

        return index;
    }

    private boolean checkBurst(Player player) {
        return getCardValue(player) > BURST_VALUE;
    }

    private int getCardValue(Player player) {
        List<Card> cards = player.getCards();
        int lowAceValue = 0;
        int highAceValue = 0;

        for (Card card : cards) {
            lowAceValue += card.getNumber();
            highAceValue += card.getNumberAce();
        }

        if (highAceValue <= BURST_VALUE && lowAceValue < highAceValue) {
            return highAceValue;
        }

        return lowAceValue;
    }

    public List<Integer> getAllPlayerCardValue() {
        List<Integer> values = new ArrayList<>();

        for (Player player : players) {
            values.add(getCardValue(player));
        }

        return values;
    }

    public boolean isBlackjack(Player player) {
        return getCardValue(player) == BURST_VALUE && player.getCards().size() == BLACKJACK_CARD_COUNT;
    }

    private int checkBlackjackAndBurst(Player player, int index) {
        if (isBlackjack(player)) {
            Output.showBlackjack(player);

            return index;
        }

        if (checkBurst(player)) {
            Output.showBurst(player);

            return index;
        }

        return NULL_VALUE;
    }
}
