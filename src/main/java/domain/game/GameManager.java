package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import view.Output;

import java.util.List;

public class GameManager {
    private List<Card> deck = CardFactory.create();
    private int pickUpIndex = 0;

    public void startGame() {
        Output.showWhoJoinGame();
    }
}
