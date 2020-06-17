package com.neo.problemtwo.utils;

import java.util.List;

public class CommonUtils {

    public static float findAverage(List<Integer> numbers){
        float average = 0;

        for(int num : numbers){
            average += num;
        }

        return average/numbers.size();
    }

}
