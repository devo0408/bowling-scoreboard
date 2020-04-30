package com.devo.bowling;

import lombok.Builder;
import lombok.Getter;

import static java.lang.Integer.sum;

@Getter
public class Frame {

  private final Integer pinOne;
  private final Integer pinTwo;
  private final Frame nextFrame;

  private Integer MAX_SCORE = 10;


  @Builder(toBuilder = true)
  public Frame(Integer pinOne, Integer pinTwo, Frame nextFrame) {
    this.pinOne = pinOne;
    this.pinTwo = pinTwo;
    this.nextFrame = nextFrame;
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
    return isStrike() && (pinOne != null && pinTwo != null);
  }

  public boolean isSpare(){
    return !isStrike() && MAX_SCORE.equals(sum(pinOne, pinTwo));
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

  private int pinsSum(){
    return sum(pinOne, pinTwo);
  }

  private int spareExtraScore(){
    return (hasNextFrame() ? nextFrame.pinOne : 0);
  }

  private int strikeExtraScore(){
    int extraScore = 0;
    if (hasNextFrame()) {
      extraScore += nextFrame.pinsSum();
      if (nextFrame.isStrike() && nextFrame.hasNextFrame()){
        extraScore += nextFrame.getNextFrame().pinOne;
      }
    }
    return extraScore;
  }

}
