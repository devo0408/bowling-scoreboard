package bowling;

import com.devo.bowling.BowlingException;
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
  public void testSpareScore(){
    Frame thirdSpareFrame = Frame.builder()
        .pinOne(4)
        .pinTwo(6)
        .build();

    Frame secondSpareStrike = Frame.builder()
        .pinOne(5)
        .pinTwo(5)
        .nextFrame(thirdSpareFrame)
        .build();

    Frame firstSpareFrame = Frame.builder()
        .pinOne(7)
        .pinTwo(3)
        .nextFrame(secondSpareStrike)
        .build();

    assertEquals(15, firstSpareFrame.score());
    assertEquals(14, secondSpareStrike.score());
    assertEquals(10, thirdSpareFrame.score());
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

  @Test
  public void testRegularScore(){
    Frame thirdRegularFrame = Frame.builder()
        .pinOne(2)
        .pinTwo(3)
        .build();

    Frame secondRegularStrike = Frame.builder()
        .pinOne(5)
        .pinTwo(1)
        .nextFrame(thirdRegularFrame)
        .build();

    Frame firstRegularFrame = Frame.builder()
        .pinOne(4)
        .pinTwo(5)
        .nextFrame(secondRegularStrike)
        .build();

    assertEquals(9, firstRegularFrame.score());
    assertEquals(6, secondRegularStrike.score());
    assertEquals(5, thirdRegularFrame.score());
  }

  @Test(expected = BowlingException.class)
  public void negativePinsTest(){
    Frame negativeFrame = Frame.builder()
        .pinOne(-1)
        .pinTwo(9)
        .build();
  }

  @Test(expected = BowlingException.class)
  public void pinsOverflowTest(){
    Frame overflowFrame = Frame.builder()
        .pinOne(5)
        .build();
    overflowFrame.setPinTwo(6);
  }

}
