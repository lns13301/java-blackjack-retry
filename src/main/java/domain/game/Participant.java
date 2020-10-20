package domain.game;

import domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    List<Player> players;

    public Participant(List<Player> players) {
        this.players = players;
    }
}
