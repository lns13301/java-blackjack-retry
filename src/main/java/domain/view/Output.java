package domain.view;

import domain.user.Player;

import java.util.List;

public class Output {

    public static void showWhoJoinGame() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리");
    }

    public static void showHowMuchBetting(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + "의 배팅 금액은?");
        }
    }
}
