class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] diff = new int[n];
        
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }
        
        // Count frequencies of each difference
        int[] count = new int[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }
        
        long k = (long) k1 + k2;
        
        // Greedily reduce the largest differences downwards
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (count[d] == 0) continue;
            
            if (k >= count[d]) {
                // We have enough k to reduce all elements of value `d` to `d - 1`
                k -= count[d];
                count[d - 1] += count[d];
                count[d] = 0;
            } else {
                // Reduce as many elements as k allows to `d - 1`
                count[d - 1] += (int) k;
                count[d] -= (int) k;
                k = 0;
            }
        }
        
        // Calculate the minimum sum of squared difference
        long ans = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                ans += (long) count[d] * (long) d * d;
            }
        }
        
        return ans;
    }
}