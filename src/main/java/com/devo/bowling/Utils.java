package com.devo.bowling;

public class Utils {


  public static int integerToInt(Integer value){
    return value == null ? 0 : value;
  }

  public static boolean between(int target, int start, int end) {
    return start <= target && target <= end;
  }

}
