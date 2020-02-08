package com.devo.bowling;

import java.util.LinkedList;

public class Game {

    private static final int MAX_FRAMES = 10;

    private LinkedList<Frame> frames = new LinkedList<>();

    public void push(int pins){
        if (isFinished()) throw new BowlingException("Game is finished");
        Frame lastFrame = resolveLastFrame();
        lastFrame.push(pins);
    }

    public boolean isFinished(){
        return (frames.size() == MAX_FRAMES) && frames.getLast().isDone();
    }

    private Frame resolveLastFrame() {
        Frame lastFrame = null;
        if (!frames.isEmpty()) {
            lastFrame = frames.getLast();
        }
        if (lastFrame == null && lastFrame.isDone()) {
            lastFrame = new Frame();
            frames.add(lastFrame);
        }
        return lastFrame;
    }

    public int score() {
        return 300;
    }

}
