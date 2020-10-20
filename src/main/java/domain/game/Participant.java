package domain.game;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import view.Output;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private static final int BURST_VALUE = 21;

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
        if (Output.showGetOneMoreCard(player.getName())) {
            player.addCard(cards.get(index++));
            Output.showCardStatePlayer(player);

            pickOneCard(cards, index, player);
        }

        return index;
    }

    private boolean checkBurst(Player player) {
        List<Card> cards = player.getCards();
        int lowAceValue = 0;
        int highAceValue = 0;

        for (Card card : cards) {
            lowAceValue += card.getNumber();
            highAceValue += card.getNumberAce();
        }

        return lowAceValue > BURST_VALUE && highAceValue > BURST_VALUE;
    }
}
