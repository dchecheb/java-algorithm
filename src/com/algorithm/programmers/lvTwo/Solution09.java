package com.algorithm.programmers.lvTwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
 * 다리를 지나는 트럭
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 */
public class Solution09 {

    @Test
    public void test() {
        Assert.assertEquals(8, solution(2, 10, new int[]{7,4,5,6}));
        Assert.assertEquals(101, solution(100, 100, new int[]{10}));
        Assert.assertEquals(110, solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> trucks = new LinkedList<>();
        for (int i=0; i<truck_weights.length; i++) { // O(truck 개수) -> 10000회
            trucks.offer(truck_weights[i]);
        }

        LinkedList<Integer> bridge = new LinkedList<>();
        bridge.add(trucks.poll());
        while (!trucks.isEmpty()) { // O(truck 개수 * 다리 길이) = 100,000,000 = 1억
            int sum=0;
            for (int i=0; i<Math.min(bridge_length-1, bridge.size()); i++) { // O(bridge.length) 10000
                sum += bridge.get(i);
            }
            if (weight >= sum+trucks.peek()) {
                bridge.addFirst(trucks.poll());
            } else {
                bridge.addFirst(0);
            }
        }

        return bridge.size()+bridge_length;
    }
}
