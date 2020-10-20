package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import view.Output;

import java.util.List;

public class GameManager {
    private List<Card> deck = CardFactory.create();
    private int pickUpIndex = 0;
    private Participant participant;

    public void startGame() {
        String[] names = Output.showWhoJoinGame();
        participant = new Participant(Output.showHowMuchBetting(names));
    }
}
