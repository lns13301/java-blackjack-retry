package view;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private static final int BURST_VALUE = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int DRAW_PRIZE = 0;
    private static final int BLACKJACK_VALUE = -21;
    private static final double BLACKJACK_BONUS = 1.5;

    public static String[] showWhoJoinGame() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        return Input.inputJoinPlayerName();
    }

    public static List<Player> showHowMuchBetting(String[] names) {
        List<Player> players = new ArrayList<>();

        for (String name : names) {
            System.out.println(name + "의 배팅 금액은?");

            players.add(new Player(name, Input.inputBetMoney()));
        }

        return players;
    }
    
    public static void showGameProgressing(List<Player> players) {
        String names = String.join(", ", players.stream()
                .collect(Collectors.groupingBy(Player::getName))
                .keySet());

        System.out.println("딜러와 " + names + "에게 2장의 카드를 분배 했습니다.");
    }

    public static void showCardStateDealer(Dealer dealer) {
        System.out.println("딜러: " + dealer.getCards());
    }

    public static void showCardStatePlayers(List<Player> players) {
        players.forEach(player -> System.out.println(player.getName() + "카드: " + player.getCards()));
    }

    public static void showCardStatePlayer(Player player) {
        System.out.println(player.getName() + "카드: " + player.getCards());
    }

    public static boolean showGetOneMoreCard(String name) {
        System.out.println(name + "은 한 장의 카드를 더 받겠습니까? (y / n)");
        return Input.inputYesOrNo();
    }

    public static void showDealerGetCard() {
        System.out.println("\n딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void showBurst(Player player) {
        System.out.println(player.getName() + "의 카드합이 21을 초과하였습니다...\n");
    }

    public static void showGameStart() {
        System.out.println("\n ====================카드 분배 시작====================\n");
    }

    private static void showGameResult() {
        System.out.println("\n ====================게임 결과====================\n");
    }
    
    public static void showGameResultDealer(Dealer dealer, int cardValue) {
        showGameResult();
        System.out.print("딜러 카드: " + dealer.getCards() + " - 결과: " + cardValue);

        addBurstMessage(cardValue);
        addBlackjackMessage(dealer, cardValue);

        System.out.println();
    }

    public static void showGameResultPlayer(List<Player> players, List<Integer> cardValues) {
        for (int i = 0; i < players.size(); i++) {
            System.out.print(players.get(i).getName() + " 카드: "
                    + players.get(i).getCards() + " - 결과: " + cardValues.get(i));

            addBurstMessage(cardValues.get(i));
            addBlackjackMessage(players.get(i), cardValues.get(i));
            System.out.println();
        }
    }

    private static void addBurstMessage(int cardValue) {
        if (cardValue > BURST_VALUE) {
            System.out.print(" (버스트)");
        }
    }
    
    private static void addBlackjackMessage(Dealer player, int cardValue) {
        if (player.getCards().size() == BLACKJACK_CARD_COUNT && cardValue == 21) {
            System.out.print(" (블랙잭)");
        }
    }

    public static void showBlackjack(Player player) {
        System.out.println(player.getName() + "는 블랙잭 입니다!\n");
    }

    public static double showRewardPlayer(Player player, int cardValue, int dealerCardValue) {
        double reword = beatMessage(player, cardValue, dealerCardValue);

        if (reword != DRAW_PRIZE) {
            return reword;
        }

        reword = defeatMessage(player, cardValue, dealerCardValue);

        if (reword != DRAW_PRIZE) {
            return reword;
        }

        reword = drawMessage(player, cardValue, dealerCardValue);
        return reword;
    }

    private static double beatMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue == BLACKJACK_VALUE && dealerCardValue != BLACKJACK_VALUE) {
            System.out.println(player.getName() + ": " + player.getBettingMoney() * BLACKJACK_BONUS + " (블랙잭 승리)");
            return -player.getBettingMoney() * BLACKJACK_BONUS;
        }

        if ((cardValue > dealerCardValue && cardValue <= BURST_VALUE)
                || (cardValue <= BURST_VALUE && dealerCardValue > BURST_VALUE)) {
            System.out.println(player.getName() + ": " + player.getBettingMoney());
            return -player.getBettingMoney();
        }

        return DRAW_PRIZE;
    }

    private static double drawMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue == BLACKJACK_VALUE && dealerCardValue == BLACKJACK_VALUE) {
            System.out.println(player.getName() + ": " + DRAW_PRIZE);
            return DRAW_PRIZE;
        }

        if (cardValue == dealerCardValue && cardValue <= BURST_VALUE) {
            System.out.println(player.getName() + ": " + DRAW_PRIZE);
            return DRAW_PRIZE;
        }

        return DRAW_PRIZE;
    }

    private static double defeatMessage(Player player, int cardValue, int dealerCardValue) {
        if (cardValue != BLACKJACK_VALUE && dealerCardValue == BLACKJACK_VALUE) {
            System.out.println(player.getName() + ": " + -player.getBettingMoney() + " (블랙잭 패배)");
            return player.getBettingMoney();
        }

        if (cardValue > BURST_VALUE || (cardValue < dealerCardValue && dealerCardValue <= BURST_VALUE)) {
            System.out.println(player.getName() + ": " + -player.getBettingMoney());
            return player.getBettingMoney();
        }

        return DRAW_PRIZE;
    }

    public static void showRewordDealer(double dealerReword) {
        System.out.println("\n## 최종수익");
        System.out.println("딜러: " + dealerReword);
    }
}
