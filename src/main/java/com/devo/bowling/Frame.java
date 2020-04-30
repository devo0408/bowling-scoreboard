package com.devo.bowling;

import lombok.Builder;
import lombok.Getter;

import static java.lang.Integer.sum;

@Getter
public class Frame {

  private final Integer pinOne;
  private final Integer pinTwo;
  private final Frame prevFrame;

  private Integer MAX_SCORE = 10;


  @Builder(toBuilder = true)
  public Frame(Integer pinOne, Integer pinTwo, Frame prevFrame) {
    this.pinOne = pinOne;
    this.pinTwo = pinTwo;
    this.prevFrame = prevFrame;
  }

  public int score(){
    return sum(pinOne, pinTwo);
  }

  public boolean hasPrevFrame(){
    return prevFrame != null;
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

}
