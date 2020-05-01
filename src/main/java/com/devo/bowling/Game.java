package com.devo.bowling;

import java.util.List;
import java.util.function.Predicate;

public class Game {

  private Frame firstFrame;
  private Frame lastFrame;
  private int framesCount;

  private static final int MAX_FRAMES = 10;
  private static final int MAX_FRAMES_LAST_SPARE = 11;
  private static final int MAX_FRAMES_LAST_STRIKE = 12;


  public Game (){
    Frame initialFrame = Frame.builder().build();
    firstFrame = initialFrame;
    lastFrame = initialFrame;
    framesCount = 1;
  }

  public void push(int pins){

    if (isFinished()) {
      throw new BowlingException("Game is finished");
    }

    if (lastFrame.isFinished()) {
      Frame newLastFrame = Frame.builder()
          .pinOne(pins)
          .build();
      Frame frameBeforeLast = lastFrame;
      frameBeforeLast.withNextFrame(newLastFrame);
      framesCount++;
    } else {
      lastFrame.withPinTwo(pins);
    }
  }

  public boolean isFinished(){
    List<Predicate<Integer>> isFinishedPredicates = List.of(
        (count) -> count == MAX_FRAMES && !lastFrame.isSpare() && !lastFrame.isStrike(),
        (count) -> count == MAX_FRAMES_LAST_SPARE && !lastFrame.isStrike(),
        (count) -> count == MAX_FRAMES_LAST_STRIKE
    );
    return isFinishedPredicates.stream()
        .anyMatch(predicate -> predicate.test(framesCount));
  }

}
