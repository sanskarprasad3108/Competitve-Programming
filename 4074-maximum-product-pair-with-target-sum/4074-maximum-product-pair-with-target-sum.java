class Solution {
    public int[] maxProductPair(int[] nums, int target) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        int[] result = new int[]{-1, -1};
        boolean found = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && nums[i] + nums[j] == target && nums[i] > nums[j]) {
                    int product = nums[i] * nums[j];
                    if (!found || product > maxProduct) {
                        maxProduct = product;
                        result[0] = i;
                        result[1] = j;
                        found = true;
                    }
                }
            }
        }

        return result;
    }
}