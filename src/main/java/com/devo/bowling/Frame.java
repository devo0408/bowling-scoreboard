package com.devo.bowling;

import lombok.Builder;
import lombok.Getter;


import static com.devo.bowling.Utils.integerToInt;
import static com.devo.bowling.Utils.between;
import static java.lang.String.format;

@Getter
public class Frame {

  private final Integer pinOne;
  private final Integer pinTwo;
  private final Frame nextFrame;

  private Integer MIN_SCORE = 0;
  private Integer MAX_SCORE = 10;


  @Builder(toBuilder = true)
  public Frame(Integer pinOne, Integer pinTwo, Frame nextFrame) {
    this.pinOne = pinOne;
    this.pinTwo = pinTwo;
    this.nextFrame = nextFrame;
    validatePinsSum();
  }

  public int score(){
    int score = pinsSum();
    if (isSpare()) {
      score += spareExtraScore();
    }
    if (isStrike()) {
      score += strikeExtraScore();
    }
    return score;
  }

  public boolean hasNextFrame(){
    return nextFrame != null;
  }

  public boolean isFinished(){
    return isStrike() || (pinOne != null && pinTwo != null);
  }

  public boolean isSpare(){
    return !isStrike() && MAX_SCORE.equals(pinsSum());
  }

  public boolean isStrike(){
    return MAX_SCORE.equals(pinOne);
  }

  public Frame withPinTwo(int pinTwo) {
    return toBuilder()
        .pinTwo(pinTwo)
        .build();
  }

  public Frame withNextFrame(Frame nextFrame) {
    return toBuilder()
        .nextFrame(nextFrame)
        .build();
  }

  private void validatePinsSum(){
    if (!pinsCorrect()) {
      throw new BowlingException(format("Pins mast be between %d and %d", MIN_SCORE, MAX_SCORE));
    }
  }

  private boolean pinsCorrect(){
    return between(integerToInt(pinOne), MIN_SCORE, MAX_SCORE)
           && between(integerToInt(pinTwo), MIN_SCORE, MAX_SCORE)
           && between(pinsSum(), MIN_SCORE, MAX_SCORE);
  }

  private int pinsSum(){
    return integerToInt(pinOne) + integerToInt(pinTwo);
  }

  private int spareExtraScore(){
    return (hasNextFrame() ? integerToInt(nextFrame.pinOne) : 0);
  }

  private int strikeExtraScore(){
    int extraScore = 0;
    if (hasNextFrame()) {
      extraScore += nextFrame.pinsSum();
      if (nextFrame.isStrike() && nextFrame.hasNextFrame()){
        extraScore += integerToInt(nextFrame.getNextFrame().pinOne);
      }
    }
    return extraScore;
  }

}
