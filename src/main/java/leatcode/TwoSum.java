package leatcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i<nums.length; i++) {
            Integer index = numMap.get(target - nums[i]);
            if (index != null && index != i) {
                return new int[]{index, i};
            }
            numMap.put(nums[i], i);
        }
        throw new RuntimeException("Two sum was not found.");
    }

}
