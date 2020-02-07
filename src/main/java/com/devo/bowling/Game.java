package com.devo.bowling;

import java.util.LinkedList;

public class Game {

    private static final int MAX_FRAMES = 10;

    private LinkedList<Frame> frames = new LinkedList<>();

    void push(int pins){

    }

    private boolean isFinished(){
        return (frames.size() == MAX_FRAMES) && frames.getLast().isDone();
    }

    private Frame resolveLastFrameFrame(){

        Frame lastFrame = frames.getLast();

        Frame lastFrame = frames.getLast();
        if (lastFrame.isDone()){
            lastFrame = new Frame();
            frames.add(lastFrame);
        }
        return lastFrame;
    }

}
