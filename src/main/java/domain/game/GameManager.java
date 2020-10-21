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
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_VALUE = -21;

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
        getGameResult();
        getReword();
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
        Output.showGameStart();
        participant.pickOneMoreCard(deck, pickUpIndex);
    }

    private int pickOneCardDealer(List<Card> cards, int index, Dealer dealer) {
        if (isBlackjack(dealer)) {
            return index;
        }

        if (isBelowSixteen(dealer.getCards())) {
            Output.showDealerGetCard();
            dealer.addCard(cards.get(index++));
            Output.showCardStateDealer(dealer);

            pickOneCardDealer(cards, index, dealer);
        }

        return index;
    }

    public boolean isBlackjack(Dealer dealer) {
        return getDealerCardValue(dealer.getCards()) == BLACKJACK_VALUE;
    }

    private boolean isBelowSixteen(List<Card> cards) {
        return getDealerCardValue(cards) <= DEALER_LIMIT_VALUE;
    }

    public int getDealerCardValue(List<Card> cards) {
        int cardValue = getCardValue(cards);

        if (cards.size() == BLACKJACK_CARD_COUNT && cardValue == BURST_VALUE) {
            return BLACKJACK_VALUE;
        }

        return cardValue;
    }

    private int getCardValue(List<Card> cards) {
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

    public void getGameResult() {
        Output.showGameResultDealer(dealer, getDealerCardValue(dealer.getCards()));
        Output.showGameResultPlayer(participant.getPlayers(), participant.getAllPlayerCardValue());
    }

    public void getReword() {
        double rewordInformation = participant.getRewordInformation(getDealerCardValue(dealer.getCards()));
        Output.showRewordDealer(rewordInformation);
    }
}
