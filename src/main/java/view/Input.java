package view;

import java.util.InputMismatchException;
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
        catch (InputMismatchException e) {
            throw new InputMismatchException("숫자를 입력하세요.");
        }
    }

    public static boolean inputYesOrNo() {
        String choice = new Scanner(System.in).next();

        return choice.equals("y") || choice.equals("Y");
    }
}
