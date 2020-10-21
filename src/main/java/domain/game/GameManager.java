package domain.game;

import domain.card.Card;
import domain.card.CardFactory;
import domain.user.Dealer;
import domain.user.Player;
import view.Output;

import java.util.List;

public class GameManager {
    private static final int DEALER_LIMIT_VALUE = 16;
    private static final int BURST_VALUE = 21;

    private List<Card> deck = CardFactory.create();
    private int pickUpIndex = 0;
    private Dealer dealer = new Dealer();
    private Participant participant;

    public void startGame() {
        String[] names = Output.showWhoJoinGame();
        participant = new Participant(Output.showHowMuchBetting(names));
        distributeCard();
        getOneMoreCard();
        pickOneCardDealer(deck, pickUpIndex, dealer);
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

    public void getCardDealer() {

    }

    private int pickOneCardDealer(List<Card> cards, int index, Dealer dealer) {
        if (isBelowSixteen(dealer.getCards())) {
            Output.showDealerGetCard();
            dealer.addCard(cards.get(index++));
            Output.showCardStateDealer(dealer);

            pickOneCardDealer(cards, index, dealer);
        }

        return index;
    }

    private boolean isBelowSixteen(List<Card> cards) {
        int lowAceValue = 0;
        int highAceValue = 0;

        for (Card card : cards) {
            lowAceValue += card.getNumber();
            highAceValue += card.getNumberAce();
        }

        return lowAceValue <= DEALER_LIMIT_VALUE && highAceValue <= DEALER_LIMIT_VALUE;
    }
}
