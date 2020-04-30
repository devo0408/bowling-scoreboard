package com.devo.bowling;

public class Game {

  private Frame firstFrame;
  private Frame lastFrame;


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

}
