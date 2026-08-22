class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {

        int left = 1;
        int right = 10000000;

        int n = dist.length;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            double time = 0;

            // All trains except the last
            for (int i = 0; i < n - 1; i++) {
                time += Math.ceil((double) dist[i] / mid);
            }

            // Last train
            time += (double) dist[n - 1] / mid;

            if (time <= hour) {
                // Speed works
                // Try smaller speed
                right = mid - 1;
            } else {
                // Speed doesn't work
                // Need greater speed
                left = mid + 1;
            }
        }

        if (left > 10000000) {
            return -1;
        }

        return left;
    }
}