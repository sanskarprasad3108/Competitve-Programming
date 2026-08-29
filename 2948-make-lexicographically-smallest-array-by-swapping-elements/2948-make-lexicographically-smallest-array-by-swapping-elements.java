import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store indices
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices based on nums values
        Arrays.sort(indices, (a, b) -> 
            Integer.compare(nums[a], nums[b])
        );

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            int j = i + 1;

            // Find all elements belonging to the same group
            while (j < n &&
                   nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            // Get indices of this group
            Integer[] groupIndices =
                Arrays.copyOfRange(indices, i, j);

            // Sort indices to fill smaller positions first
            Arrays.sort(groupIndices);

            // Values from indices[i...j-1] are already sorted
            for (int k = 0; k < groupIndices.length; k++) {
                ans[groupIndices[k]] = nums[indices[i + k]];
            }

            i = j;
        }

        return ans;
    }
}