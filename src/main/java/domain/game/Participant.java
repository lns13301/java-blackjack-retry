package domain.game;

import domain.card.Card;
import domain.user.Player;
import view.Output;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private List<Player> players;

    public Participant(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int pickCardFromDeck(List<Card> cards, int index) {
        int cardIndex = index;

        for (Player player : players) {
            player.addCard(cards.get(cardIndex++));
        }

        return cardIndex;
    }

    public int pickOneMoreCard(List<Card> cards, int index) {
        for (Player player : players) {
            Output.showCardStatePlayer(player);
            index = player.pickOneCard(cards, index);
        }

        return index;
    }

    public List<Integer> getAllPlayerCardValue() {
        List<Integer> values = new ArrayList<>();

        for (Player player : players) {
            values.add(player.getCardValuePlayer());
        }

        return values;
    }

    public double getRewardInformation(int dealerCardValue) {
        double dealerReward = 0;

        for (Player player: players) {
            dealerReward += RewordManager.showRewardPlayer(player, player.getCardValuePlayer(), dealerCardValue);
        }

        return dealerReward;
    }
}
