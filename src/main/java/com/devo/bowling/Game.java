package com.devo.bowling;

public class Game {

  private Frame lastFrame;


  public Game (){
    this.lastFrame = Frame.builder().build();
  }

  public void push(int pins){
    if (lastFrame.isFinished()) {
      Frame prevLastFrame = lastFrame;
      lastFrame = Frame.builder()
          .pinOne(pins)
          .prevFrame(prevLastFrame)
          .build();
    } else {
      lastFrame.withPinTwo(pins);
    }
  }

}
