package domain.card;

import domain.user.Player;
import view.Output;
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
        String[] names = new String[3];

        names[0] = "첫 번째";
        names[1] = "두 번째";
        names[2] = "세 번째";

        List<Player> players = Output.showHowMuchBetting(names);
        assertThat(players.size()).isEqualTo(3);
        assertThat(players.get(1).getName()).isEqualTo(("두 번째"));
    }

    @Test
    void gameProgressingTest() {
        List<Player> players = new ArrayList<>();

        players.add(new Player("첫 번째", 1000));
        players.add(new Player("두 번째", 4000));
        players.add(new Player("세 번째", 7000));

        Output.showGameProgressing(players);
    }

    @Test
    void showHandedCardViewTest() {
        List<Player> players = new ArrayList<>();

        players.add(new Player("첫 번째", 1000));
        players.add(new Player("두 번째", 4000));
        players.add(new Player("세 번째", 7000));

        players.get(0).addCard(new Card(Symbol.EIGHT, Type.CLUB));
        players.get(0).addCard(new Card(Symbol.EIGHT, Type.HEART));
        players.get(1).addCard(new Card(Symbol.NINE, Type.DIAMOND));
        players.get(2).addCard(new Card(Symbol.JACK, Type.SPADE));

        Output.showCardStatePlayer(players);
    }
}
