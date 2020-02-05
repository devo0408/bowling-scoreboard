package bowling;

import com.devo.bowling.Frame;
import org.junit.Test;

import static  org.junit.Assert.*;

public class FrameTest {

    @Test
    public  void scoreTest(){

        // Frame frame1 = new Frame();
        //frame1.push(5);
        //frame1.push(4);
        //assertEquals(9, frame1.score());

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

}
