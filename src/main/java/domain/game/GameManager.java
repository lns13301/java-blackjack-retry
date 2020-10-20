package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.Output;

import java.util.List;

public class GameManager {
    private List<Card> deck = CardFactory.create();
    private int pickUpIndex = 0;
    private Dealer dealer = new Dealer();
    private Participant participant;

    public void startGame() {
        String[] names = Output.showWhoJoinGame();
        participant = new Participant(Output.showHowMuchBetting(names));
        distributeCard();
        getOneMoreCard();
    }

    public void distributeCard() {
        Output.showGameProgressing(participant.getPlayers());

        dealer.addCard(deck.get(pickUpIndex));
        pickUpIndex++;

        pickUpIndex = participant.pickCardFromDeck(deck, pickUpIndex);
        pickUpIndex = participant.pickCardFromDeck(deck, pickUpIndex);

        Output.showCardStateDealer(dealer);
        Output.showCardStatePlayers(participant.getPlayers());
    }

    public void getOneMoreCard() {
        System.out.println("\n ====================카드 분배 시작====================\n");
        participant.pickOneMoreCard(deck, pickUpIndex);
    }
}
