import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class BowlingGameTest {

    BowlingGame game = new BowlingGame();

    @Test
    public void queDesGoutieresDonne0Points() {
        rollMany(20, 0);
        assertEquals(0, game.getScore());
    }

    @Test
    public void uneSeuleQuille(){
        game.roll(1);
        rollMany(19, 0);
        assertEquals(1, game.getScore());
    }

    @Test
    public void bonusDuSpare(){
        game.roll(8);
        game.roll(2);
        game.roll(1);
        rollMany(17, 0);
        assertEquals(12, game.getScore());
    }
    
    @Test
    public void strike(){
        game.roll(10);
        game.roll(7);
        rollMany(17, 0);
        assertEquals(24, game.getScore());
    }
    
    @Test
    public void strikeEtSpare(){
        game.roll(10);
        game.roll(7);
        game.roll(3);
        game.roll(9);
        rollMany(15, 0);
        System.out.println(game.getScore());
        assertEquals(48, game.getScore());
    }

    private void rollMany(int numberRolls, int numberPins) {
        for (int i = 0; i < numberRolls; i++) {
            game.roll(numberPins);
        }
    }
}
