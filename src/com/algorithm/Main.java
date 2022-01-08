package com.algorithm;

import com.algorithm.programmers.LvOne;

public class Main {

    public static void main(String[] args) {
        LvOne lvOne = new LvOne();

        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println(lvOne.lvOne03(lottos, win_nums));
    }
}
