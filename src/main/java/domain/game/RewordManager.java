package domain.game;

import domain.user.Player;
import view.Output;

public class RewordManager {
    private static final int BLACKJACK_VALUE = -21;
    private static final int BURST_VALUE = 21;
    private static final int DRAW_PRIZE = 0;
    private static final double BLACKJACK_BONUS = 1.5;

    public static double showRewardPlayer(Player player, int cardValue, int dealerCardValue) {
        double reward = beatMessage(player, cardValue, dealerCardValue);

        if (reward != DRAW_PRIZE) {
            return reward;
        }

        reward = defeatMessage(player, cardValue, dealerCardValue);

        if (reward != DRAW_PRIZE) {
            return reward;
        }

        reward = drawMessage(player, cardValue, dealerCardValue);
        return reward;
    }

    private static double beatMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue == BLACKJACK_VALUE && dealerCardValue != BLACKJACK_VALUE) {
            Output.showPlayerBlackjackBeatMessage(player, BLACKJACK_BONUS);
            return -player.getBettingMoney() * BLACKJACK_BONUS;
        }

        if (dealerCardValue != BLACKJACK_VALUE
                && ((cardValue > dealerCardValue && cardValue <= BURST_VALUE)
                || (cardValue <= BURST_VALUE && dealerCardValue > BURST_VALUE))) {
            Output.showPlayerBeatMessage(player);
            return -player.getBettingMoney();
        }

        return DRAW_PRIZE;
    }

    private static double drawMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue == BLACKJACK_VALUE && dealerCardValue == BLACKJACK_VALUE) {
            Output.showPlayerDrawMessage(player, DRAW_PRIZE);
            return DRAW_PRIZE;
        }

        if (cardValue == dealerCardValue && cardValue <= BURST_VALUE) {
            Output.showPlayerDrawMessage(player, DRAW_PRIZE);
            return DRAW_PRIZE;
        }

        return DRAW_PRIZE;
    }

    private static double defeatMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue != BLACKJACK_VALUE && dealerCardValue == BLACKJACK_VALUE) {
            Output.showPlayerBlackjackDefeatMessage(player);
            return player.getBettingMoney();
        }

        if (cardValue > BURST_VALUE || (cardValue < dealerCardValue && dealerCardValue <= BURST_VALUE)) {
            Output.showPlayerDefeatMessage(player);
            return player.getBettingMoney();
        }

        return DRAW_PRIZE;
    }
}
