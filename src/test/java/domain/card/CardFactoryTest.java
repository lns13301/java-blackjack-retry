package domain.card;

import domain.user.Player;
import domain.view.Output;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardFactoryTest {
    @Test
    void create() {
        List<Card> cards = CardFactory.create();
        assertThat(cards).hasSize(52);
        System.out.println(cards);
    }

    @Test
    void getNameTest() {
        List<Player> players = new ArrayList<>();

        players.add(new Player("첫 번째", 1000));
        players.add(new Player("두 번째", 4000));
        players.add(new Player("세 번째", 7000));

        Output.showHowMuchBetting(players);
    }
}
