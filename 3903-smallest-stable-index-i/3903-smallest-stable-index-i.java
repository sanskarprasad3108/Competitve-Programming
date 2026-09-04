class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Precompute the minimum values from index i to n - 1
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        int prefixMax = -1; 
        
        // Iterate to find the first index that satisfies the condition
        for (int i = 0; i < n; i++) {
            // Update the maximum value from index 0 to i
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Calculate instability score and check against k
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}