package com.devo.bowling;

import java.util.List;
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

    if (isFinished()) {
      throw new BowlingException("Game is finished");
    }

    if (lastFrame.isFinished()) {
      Frame newLastFrame = Frame.builder()
          .pinOne(pins)
          .build();
      Frame frameBeforeLast = lastFrame;
      frameBeforeLast.withNextFrame(newLastFrame);
    } else {
      lastFrame.withPinTwo(pins);
    }
  }

  public boolean isFinished(){
    int framesCount = calculateFramesCount();
    List<Predicate<Integer>> isFinishedPredicates = List.of(
        (count) -> framesCount == MAX_FRAMES && !lastFrame.isSpare() && !lastFrame.isStrike(),
        (count) -> framesCount == MAX_FRAMES_LAST_SPARE && !lastFrame.isStrike(),
        (count) -> framesCount == MAX_FRAMES_LAST_STRIKE
    );
    return isFinishedPredicates.stream()
        .anyMatch(predicate -> predicate.test(framesCount));
  }

  private int calculateFramesCount(){
    int framesCount = 0;
    Frame frame = this.firstFrame;
    while (frame.hasNextFrame()){
      framesCount++;
      frame = frame.getNextFrame();
    }
    return framesCount;
  }

}
