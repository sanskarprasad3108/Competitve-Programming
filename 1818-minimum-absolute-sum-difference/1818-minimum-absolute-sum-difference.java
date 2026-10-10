import java.util.Arrays;

class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long mod = 1_000_000_007L;

        // Create a sorted copy of nums1 for binary search
        int[] sorted1 = nums1.clone();
        Arrays.sort(sorted1);

        long totalSum = 0;
        int maxGain = 0;

        for (int i = 0; i < n; i++) {
            int origDiff = Math.abs(nums1[i] - nums2[i]);
            totalSum += origDiff;

            // Find closest element to nums2[i] in sorted1
            int idx = Arrays.binarySearch(sorted1, nums2[i]);
            if (idx < 0) {
                idx = -idx - 1; // Insertion point
            }

            // Check element at the insertion index (if within bounds)
            if (idx < n) {
                int newDiff = Math.abs(sorted1[idx] - nums2[i]);
                maxGain = Math.max(maxGain, origDiff - newDiff);
            }

            // Check element immediately preceding the insertion index (if within bounds)
            if (idx > 0) {
                int newDiff = Math.abs(sorted1[idx - 1] - nums2[i]);
                maxGain = Math.max(maxGain, origDiff - newDiff);
            }
        }

        return (int) ((totalSum - maxGain) % mod);
    }
}