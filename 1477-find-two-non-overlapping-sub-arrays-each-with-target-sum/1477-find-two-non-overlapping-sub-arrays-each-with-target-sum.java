class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // Stores the minimum length of a valid subarray ending at or before index i
        int[] minLens = new int[n]; 
        
        int left = 0;
        int sum = 0;
        int bestSoFar = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window if the sum exceeds the target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // If we find a valid subarray
            if (sum == target) {
                int currentLen = right - left + 1;
                
                // Check if there is a valid non-overlapping subarray before 'left'
                if (left > 0 && minLens[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currentLen + minLens[left - 1]);
                }
                
                // Update the best length found so far
                bestSoFar = Math.min(bestSoFar, currentLen);
            }
            
            // Record the best length up to the current index
            minLens[right] = bestSoFar;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}