package view;

import java.util.Scanner;

public class Input {

    public static String[] inputJoinPlayerName() {
        String players = new Scanner(System.in).next();

        return players.split(",");
    }
}
