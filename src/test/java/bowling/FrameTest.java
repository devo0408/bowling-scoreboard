package bowling;

import com.devo.bowling.Frame;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class FrameTest {

  @Test
  public void tesIsStrike(){
    Frame strikeFrame = Frame.builder()
        .pinOne(10)
        .build();
    assertTrue(strikeFrame.isStrike());
    assertTrue(strikeFrame.isFinished());
    assertFalse(strikeFrame.isSpare());
  }

  @Test
  public void testStrikeScore() {
    Frame thirdStrikeFrame = Frame.builder()
        .pinOne(10)
        .build();

    Frame secondFrameStrike = Frame.builder()
        .pinOne(10)
        .nextFrame(thirdStrikeFrame)
        .build();

    Frame firstStrikeFrame = Frame.builder()
        .pinOne(10)
        .nextFrame(secondFrameStrike)
        .build();

    assertEquals(30, firstStrikeFrame.score());
    assertEquals(20, secondFrameStrike.score());
    assertEquals(10, thirdStrikeFrame.score());
  }

  @Test
  public void testIsSpare() {
    Frame spareFrame = Frame.builder()
        .pinOne(5)
        .pinTwo(5)
        .build();
    assertTrue(spareFrame.isSpare());
    assertTrue(spareFrame.isFinished());
    assertFalse(spareFrame.isStrike());
  }

  @Test
  public void testRegularFrame() {
    Frame regularFrame = Frame.builder()
        .pinOne(3)
        .pinTwo(4)
        .build();
    assertTrue(regularFrame.isFinished());
    assertFalse(regularFrame.isSpare());
    assertFalse(regularFrame.isStrike());
  }

}
