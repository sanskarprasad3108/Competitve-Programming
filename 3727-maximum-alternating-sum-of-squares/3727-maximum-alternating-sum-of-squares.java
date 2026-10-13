import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        // Take absolute values so sorting arranges them by magnitude
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        
        Arrays.sort(nums);
        
        long total = 0;
        int negCount = n / 2;
        
        // The smallest n / 2 elements get subtracted
        for (int i = 0; i < negCount; i++) {
            total -= (long) nums[i] * nums[i];
        }
        
        // The remaining largest elements get added
        for (int i = negCount; i < n; i++) {
            total += (long) nums[i] * nums[i];
        }
        
        return total;
    }
}