package view;

import domain.user.Dealer;
import domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Output {

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

    public static void showCardStatePlayer(List<Player> players) {
        players.forEach(player -> System.out.println(player.getName() + "카드: " + player.getCards()));
    }

    public static void showGetOneMoreCard(String name) {
        System.out.println(name + "은 한 장의 카드를 더 받겠습니까? (y / n");
    }

    public static void showHavingCard(Player player) {
        System.out.println(player.getName() + "카드 : " + player.getCards());
    }

    public static void showDealerGetCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void showResult(Dealer dealer, List<Player> players) {
        System.out.println("딜러 카드: " + dealer.getCards());
        players.forEach(player -> System.out.println(player.getName() + "카드: " + player.getCards()));
    }
}
