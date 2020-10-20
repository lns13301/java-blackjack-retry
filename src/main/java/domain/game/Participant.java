package domain.game;

import domain.card.Card;
import domain.user.Player;

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

    public int pickCardFromDeck(List<Card> cards, Player player, int index) {
        player.addCard(cards.get(index));
        return ++index;
    }

    // 처음 2장씩 분배
    public int pickCardFromDeck(List<Card> cards, int index) {
        for (Player player : players) {
            player.addCard(cards.get(index));
            index++;
            player.addCard(cards.get(index));
            index++;
        }

        return index;
    }
}
