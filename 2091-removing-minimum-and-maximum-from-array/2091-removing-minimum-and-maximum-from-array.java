class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIdx = 0;
        int maxIdx = 0;

        // Find indices of minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }

            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Remove both from front
        int fromFront = right + 1;

        // Remove both from back
        int fromBack = n - left;

        // Remove one from front and one from back
        int fromBoth = (left + 1) + (n - right);

        return Math.min(fromFront, Math.min(fromBack, fromBoth));
    }
}