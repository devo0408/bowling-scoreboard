package bowling;

import com.devo.bowling.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class GameTest {

  private static final Integer MAX_FRAME_SCORE = 10;
  private static final  int MAX_FRAMES_IN_GAME = 10;
  private static final int MAX_SCORE = 300;


  @Test
  public void testMaxScore(){
    Game game = new Game();
    while (!game.isFinished()) {
      game.push(MAX_FRAME_SCORE);
    }
    assertEquals(MAX_SCORE, game.score());
  }

  @Test
  public void testNotFinishedGameInTheEnd() {
    Game game = new Game();
    for (int i = 0; i <  MAX_FRAMES_IN_GAME - 1;  i++) {
      game.push(MAX_FRAME_SCORE);
    }
    assertFalse(game.isFinished());
    assertEquals(240, game.score());
  }

  @Test
  public void testNotFinishedGameInTheMiddle() {
    Game game = new Game();
    game.push(10);
    game.push(5);
    game.push(5);
    game.push(7);
    game.push(2);
    game.push(4);

    assertFalse(game.isFinished());
    assertEquals(50, game.score());
  }

}
