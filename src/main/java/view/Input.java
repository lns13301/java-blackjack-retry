package view;

import java.util.Scanner;

public class Input {

    public static String[] inputJoinPlayerName() {
        String players = new Scanner(System.in).next();

        return players.split(",");
    }

    public static int inputBetMoney() {
        try {
            return new Scanner(System.in).nextInt();
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바른 값이 입력되지 않았습니다.");
        }
    }

    public static boolean inputYesOrNo() {
        String choice = new Scanner(System.in).next();

        return choice.equals("y") || choice.equals("Y");
    }
}
