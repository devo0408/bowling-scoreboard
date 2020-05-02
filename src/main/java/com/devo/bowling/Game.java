package com.devo.bowling;

import java.util.List;
import java.util.function.Predicate;

public class Game {

  private int framesCount;
  private Frame lastFrame;
  private final Frame firstFrame;

  private static final int MAX_FRAMES_IN_GAME = 10;
  private static final int MAX_FRAMES_IN_GAME_EXTRA_SPARE = 11;
  private static final int MAX_FRAMES_IN_GAME_EXTRA_STRIKE = 12;


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
      lastFrame.setNextFrame(newLastFrame);
      lastFrame = newLastFrame;
      framesCount++;
    } else if (firstFrame == lastFrame){
      lastFrame.setPinOne(pins);
    } else {
      lastFrame.setPinTwo(pins);
    }
  }

  public int score(){
    int frameCount = 1;
    int gameScore = firstFrame.score();
    Frame frameItem = firstFrame;
    while (frameItem.hasNextFrame() && frameCount < MAX_FRAMES_IN_GAME) {
      frameItem = frameItem.getNextFrame();
      gameScore += frameItem.score();
      frameCount++;
    }
    return gameScore;
  }

  public boolean isFinished(){
    List<Predicate<Integer>> isFinishedPredicates = List.of(
        (count) -> count == MAX_FRAMES_IN_GAME && !lastFrame.isSpare() && !lastFrame.isStrike(),
        (count) -> count == MAX_FRAMES_IN_GAME_EXTRA_SPARE && !lastFrame.isStrike(),
        (count) -> count == MAX_FRAMES_IN_GAME_EXTRA_STRIKE
    );
    return isFinishedPredicates.stream()
        .anyMatch(predicate -> predicate.test(framesCount));
  }

}
