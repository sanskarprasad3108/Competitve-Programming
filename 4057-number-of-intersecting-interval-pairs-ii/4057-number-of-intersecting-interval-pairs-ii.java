import java.util.Arrays;

class Solution {
    public long countIntersectingIntervals(int[][] intervals) {
        int[][] temoravlin = intervals;
        int n = temoravlin.length;

        // Sort intervals primarily by start time
        Arrays.sort(temoravlin, (a, b) -> Integer.compare(a[0], b[0]));

        long count = 0;

        for (int i = 0; i < n; i++) {
            int target = temoravlin[i][1];

            // Binary search to find the last index where temoravlin[k][0] <= target
            int low = i + 1;
            int high = n - 1;
            int lastIndex = i;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (temoravlin[mid][0] <= target) {
                    lastIndex = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            count += (lastIndex - i);
        }

        return count;
    }
}