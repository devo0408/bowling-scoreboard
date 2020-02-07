package bowling;

import com.devo.bowling.BowlingException;
import com.devo.bowling.Frame;
import org.junit.Test;

import static  org.junit.Assert.*;

public class FrameTest {

    @Test
    public  void scoreTest(){

        Frame frame1 = new Frame();
        frame1.push(5);
        frame1.push(4);
        assertEquals(9, frame1.score());

        Frame frame2 = new Frame();
        frame2.push(5);
        assertEquals(5, frame2.score());

        Frame frame3 = new Frame();
        frame3.push(0);
        frame3.push(10);
        assertEquals(10, frame3.score());
    }

    @Test
    public void isDoneTest(){

        Frame done = new Frame();
        done.push(5);
        done.push(4);
        assertTrue(done.isDone());

        Frame notDone = new Frame();
        notDone.push(5);
        assertFalse(notDone.isDone());

        Frame strike = new Frame();
        strike.push(10);
        assertTrue(strike.isDone());

        Frame spare = new Frame();
        spare.push(5);
        spare.push(5);
        assertTrue(spare.isDone());

    }

    @Test
    public void isSpareTest(){

        Frame spare = new Frame();
        spare.push(5);
        spare.push(5);
        assertTrue(spare.isSpare());

        Frame notSpare = new Frame();
        notSpare.push(5);
        notSpare.push(4);
        assertFalse(notSpare.isSpare());

        Frame strike = new Frame();
        strike.push(10);
        assertFalse(strike.isSpare());

    }

    @Test
    public void isStrikeTest(){
        Frame strike = new Frame();
        strike.push(10);
        assertTrue(strike.isStrike());

        Frame notStrike = new Frame();
        notStrike.push(0);
        notStrike.push(10);
        assertFalse(notStrike.isStrike());
    }

    @Test(expected = BowlingException.class)
    public void incorrectScoreTest(){
        Frame frame = new Frame();
        frame.push(11);
    }

    @Test(expected = BowlingException.class)
    public void pushToDoneTest(){
        Frame frame = new Frame();
        frame.push(5);
        frame.push(4);
        frame.push(1);
    }

}
