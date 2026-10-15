class Solution {
    public int resilientSubarray(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 1;
        
        int i = 0;
        while (i < n) {
            int r = nums[i] % k;
            int j = i;
            while (j < n && nums[j] % k == r) {
                j++;
            }
            
            int M = j - i; // length of contiguous block with identical remainder r
            
            if (r == 0) {
                maxLen = Math.max(maxLen, M);
            } else {
                int step = k / gcd(r, k);
                int bestL = 1 + ((M - 1) / step) * step;
                maxLen = Math.max(maxLen, bestL);
            }
            
            i = j;
        }
        
        return maxLen;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}