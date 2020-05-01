package bowling;

import com.devo.bowling.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {


  @Test
  public void testGameFinished(){
    Game game = new Game();
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);
    game.push(10);

    assertTrue(game.isFinished());

  }

}
