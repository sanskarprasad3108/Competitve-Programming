class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // Find the index of 0
        int pos0 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }

        // Check if array is cyclically ascending
        boolean isAsc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                isAsc = false;
                break;
            }
        }

        if (isAsc) {
            if (pos0 == 0) return 0;
            return Math.min(pos0, (n - pos0) + 2);
        }

        // Check if array is cyclically descending
        boolean isDesc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 - i + n) % n] != i) {
                isDesc = false;
                break;
            }
        }

        if (isDesc) {
            int op1 = n - pos0; // Reverse then left shifts
            int op2 = ((pos0 + 1) % n) + 1; // Left shifts then reverse
            return Math.min(op1, op2);
        }

        return -1;
    }
}