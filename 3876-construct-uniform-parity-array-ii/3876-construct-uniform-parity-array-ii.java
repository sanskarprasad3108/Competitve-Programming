import java.util.*;

class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        Integer smallestEven = null;
        Integer smallestOdd = null;

        for (int num : nums1) {
            if (num % 2 == 0) {
                if (smallestEven == null || num < smallestEven)
                    smallestEven = num;
            } else {
                if (smallestOdd == null || num < smallestOdd)
                    smallestOdd = num;
            }
        }

        // Try making all EVEN
        boolean canEven = true;
        if (smallestEven == null) canEven = false;
        else {
            for (int num : nums1) {
                if (num % 2 != 0) { // odd → need odd smaller
                    if (smallestOdd == null || smallestOdd >= num) {
                        canEven = false;
                        break;
                    }
                }
            }
        }

        // Try making all ODD
        boolean canOdd = true;
        if (smallestOdd == null) canOdd = false;
        else {
            for (int num : nums1) {
                if (num % 2 == 0) { // even → need smaller odd
                    if (smallestOdd >= num) {
                        canOdd = false;
                        break;
                    }
                }
            }
        }

        return canEven || canOdd;
    }
}