package com.devo.bowling;


import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int MAX_SCORE = 10;
    private static final int MAX_TRY = 2;
    private static final int STRIKE_TRY = 1;

    private List<Integer> pins = new ArrayList<>(MAX_TRY);

    public void push(Integer pin){
        if (pin > MAX_SCORE) throw new BowlingException("Score more then: " + MAX_SCORE);
        if (isDone()) throw new BowlingException("Frame is done");
        pins.add(pin);
    }

    public boolean isDone(){
        return isStrike() || (pins.size() == MAX_TRY);
    }

    public int score(){
        return pins.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isSpare(){
        return (score() == MAX_SCORE) && (pins.size() == MAX_TRY);
    }

    public boolean isStrike(){
        return (score() == MAX_SCORE) && (pins.size() == STRIKE_TRY);
    }

}
