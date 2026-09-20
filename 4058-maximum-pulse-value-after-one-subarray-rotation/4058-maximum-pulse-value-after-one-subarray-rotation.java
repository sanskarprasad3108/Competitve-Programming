class Solution {
    public long maxValue(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return nums.length == 1 ? nums[0] : 0;
        }

        // P[t] stores alternating sum of the first t elements
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            long sign = (i % 2 == 0) ? 1L : -1L;
            P[i + 1] = P[i] + sign * nums[i];
        }

        long originalPulse = P[n];
        long maxDelta = 0; // At most one operation, so delta >= 0

        // Track best terms for l < r
        // maxP_next[0]: max P[l + 1] where l is even
        // maxP_next[1]: max P[l + 1] where l is odd
        // maxP_curr[0]: max P[l] where l is even
        // maxP_curr[1]: max P[l] where l is odd
        long[] maxP_next = new long[]{Long.MIN_VALUE, Long.MIN_VALUE};
        long[] maxP_curr = new long[]{Long.MIN_VALUE, Long.MIN_VALUE};

        for (int r = 0; r < n; r++) {
            // Before considering r as the right endpoint, add l = r - 1 to our candidate pools
            if (r > 0) {
                int l = r - 1;
                int parityL = l % 2;
                maxP_next[parityL] = Math.max(maxP_next[parityL], P[l + 1]);
                maxP_curr[parityL] = Math.max(maxP_curr[parityL], P[l]);

                int parityR = r % 2;
                // Same parity candidate: 2 * (P[l + 1] - P[r + 1])
                if (maxP_next[parityR] != Long.MIN_VALUE) {
                    maxDelta = Math.max(maxDelta, 2 * (maxP_next[parityR] - P[r + 1]));
                }
                // Different parity candidate: 2 * (P[l] - P[r + 1])
                int diffParity = 1 - parityR;
                if (maxP_curr[diffParity] != Long.MIN_VALUE) {
                    maxDelta = Math.max(maxDelta, 2 * (maxP_curr[diffParity] - P[r + 1]));
                }
            }
        }

        return originalPulse + maxDelta;
    }
}