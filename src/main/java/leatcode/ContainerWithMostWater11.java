package leatcode;

public class ContainerWithMostWater11 {

    public int maxArea(int[] height) {
        int maxSquare = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            if (height[left] <= height[right]) {
                maxSquare = Math.max(maxSquare, height[left] * (right-left));
                left++;
            } else {
                maxSquare = Math.max(maxSquare, height[right] * (right-left));
                right--;
            }
        }
        return maxSquare;
    }
}
