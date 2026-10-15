import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = 1_000_000_007L;
        int[] bitCounts = new int[31];

        // 1. Count the occurrences of each bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) == 1) {
                    bitCounts[bit]++;
                }
            }
        }

        long totalSum = 0;

        // 2. Greedily construct the top k largest numbers
        for (int i = 0; i < k; i++) {
            long currentVal = 0;
            for (int bit = 0; bit < 31; bit++) {
                if (bitCounts[bit] > 0) {
                    currentVal |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            totalSum = (totalSum + (currentVal * currentVal) % mod) % mod;
        }

        return (int) totalSum;
    }
}