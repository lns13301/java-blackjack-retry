package domain.user;

import domain.card.Card;
import view.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 게임 참여자를 의미하는 객체
 */
public class Player extends Dealer{
    private static final int BLACKJACK_VALUE = -21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BURST_VALUE = 21;
    private static final int NULL_VALUE = 0;

    private final String name;
    private final double bettingMoney;

    public Player(String name, double bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    // TODO 추가 기능 구현

    public String getName() {
        return name;
    }

    public double getBettingMoney() {
        return bettingMoney;
    }

    public boolean isBlackjack() {
        return getCardValuePlayer() == BLACKJACK_VALUE;
    }

    public int getCardValuePlayer() {
        List<Card> cards = getCards();

        int cardValue = getCardValue(cards);

        if (cards.size() == BLACKJACK_CARD_COUNT && cardValue == BURST_VALUE) {
            return BLACKJACK_VALUE;
        }

        return cardValue;
    }

    private boolean isBurst() {
        return getCardValuePlayer() > BURST_VALUE;
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

    private int checkBlackjackAndBurst(int index) {
        if (isBlackjack()) {
            Output.showBlackjack(this);

            return index;
        }

        if (isBurst()) {
            Output.showBurst(this);

            return index;
        }

        return NULL_VALUE;
    }

    public int pickOneCard(List<Card> cards, int index) {
        int checkedValue = checkBlackjackAndBurst(index);

        if (checkedValue != NULL_VALUE) {
            return checkedValue;
        }

        if (Output.showGetOneMoreCard(getName())) {
            addCard(cards.get(index++));
            Output.showCardStatePlayer(this);

            index = pickOneCard(cards, index);
        }

        return index;
    }
}
