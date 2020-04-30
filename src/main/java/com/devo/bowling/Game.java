package com.devo.bowling;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Game {

  private Frame firstFrame;
  private Frame lastFrame;

  private static int MAX_FRAMES = 10;
  private static int MAX_FRAMES_LAST_SPARE = 11;
  private static int MAX_FRAMES_LAST_STRIKE = 12;


  public Game (){
    Frame initialFrame = Frame.builder().build();
    this.firstFrame = initialFrame;
    this.lastFrame = initialFrame;
  }

  public void push(int pins){
    if (lastFrame.isFinished()) {
      Frame frameBeforeLast = lastFrame;
      Frame newLastFrame = Frame.builder()
          .pinOne(pins)
          .build();
      frameBeforeLast.withNextFrame(newLastFrame);
    } else {
      lastFrame.withPinTwo(pins);
    }
  }

  private int framesCount(){
    int framesCount = 0;
    Frame frame = this.firstFrame;
    while (frame.hasNextFrame()){
      framesCount++;
      frame = frame.getNextFrame();
    }
    return framesCount;
  }

  private boolean isFinished(){
    // 1. size < 10 last not spare, not strike
    // 2. size == 11, not strike
    // 3. size = 12 last

    List<Predicate<Integer>> isFinishedPredicates = List.of(
        (framesCount)
            -> framesCount == MAX_FRAMES && !lastFrame.isSpare() && !lastFrame.isStrike(),
        (framesCount)
            -> framesCount == MAX_FRAMES_LAST_SPARE && !lastFrame.isStrike(),
        (framesCount)
            -> framesCount == MAX_FRAMES_LAST_STRIKE
    );

    int framesCount = framesCount();

    return isFinishedPredicates.stream()
        .anyMatch(predicate -> predicate.test(framesCount));

  }

}
