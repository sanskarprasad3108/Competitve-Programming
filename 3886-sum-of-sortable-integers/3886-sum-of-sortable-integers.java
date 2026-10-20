import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            if (isSortable(nums, sortedNums, n, k)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int[] sortedNums, int n, int k) {
        for (int start = 0; start < n; start += k) {
            // Find drops within nums[start ... start + k - 1]
            int dropIndex = -1;
            int dropCount = 0;

            for (int i = 0; i < k - 1; i++) {
                if (nums[start + i] > nums[start + i + 1]) {
                    dropCount++;
                    dropIndex = i;
                }
            }

            int shift = 0;
            if (dropCount == 0) {
                shift = 0;
            } else if (dropCount == 1) {
                // If there is 1 drop, wrap-around must also be valid: last element <= first element
                if (nums[start + k - 1] > nums[start]) {
                    return false;
                }
                shift = dropIndex + 1;
            } else {
                // More than 1 drop means it cannot be a cyclic shift of a non-decreasing array
                return false;
            }

            // Verify that rotated block matches the sorted block
            for (int j = 0; j < k; j++) {
                int actual = nums[start + (shift + j) % k];
                int expected = sortedNums[start + j];
                if (actual != expected) {
                    return false;
                }
            }
        }

        return true;
    }
}