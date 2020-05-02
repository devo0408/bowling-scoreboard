package com.devo.bowling;

import lombok.Builder;
import lombok.Getter;


import static com.devo.bowling.Utils.integerToInt;
import static com.devo.bowling.Utils.between;
import static java.lang.String.format;

@Getter
public class Frame {

  private Integer pinOne;
  private Integer pinTwo;
  private Frame nextFrame;

  private final Integer MIN_SCORE = 0;
  private final Integer MAX_SCORE = 10;


  @Builder
  public Frame(Integer pinOne, Integer pinTwo, Frame nextFrame) {
    validateInputPins(integerToInt(pinOne), integerToInt(pinTwo));
    this.pinOne = pinOne;
    this.pinTwo = pinTwo;
    this.nextFrame = nextFrame;
  }

  public void setPinOne(int pinOne){
    validateInputPins(pinOne, integerToInt(pinTwo));
    this.pinOne = pinOne;
  }

  public void setPinTwo(int pinTwo){
    validateInputPins(integerToInt(pinOne), pinTwo);
    this.pinTwo = pinTwo;
  }

  public void setNextFrame(Frame nextFrame){
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
    return isStrike() || (pinOne != null && pinTwo != null);
  }

  public boolean isSpare(){
    return !isStrike() && MAX_SCORE.equals(pinsSum());
  }

  public boolean isStrike(){
    return MAX_SCORE.equals(pinOne);
  }

  private void validateInputPins(int inputPinOne, int inputPinTwo){
    if (!isInputPinsCorrect(inputPinOne, inputPinTwo)) {
      throw new BowlingException(format("Pins mast be between %d and %d", MIN_SCORE, MAX_SCORE));
    }
  }

  private boolean isInputPinsCorrect(int inputPinOne, int inputPinTwo) {
    return between(inputPinOne, MIN_SCORE, MAX_SCORE)
           && between(inputPinTwo, MIN_SCORE, MAX_SCORE)
           && between(inputPinOne + inputPinTwo, MIN_SCORE, MAX_SCORE);
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
