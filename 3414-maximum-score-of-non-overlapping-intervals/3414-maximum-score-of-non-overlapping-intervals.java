import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int[] EMPTY_ARRAY = new int[0];

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // sorted[i] = {left, right, weight, original_index}
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        // Sort by right boundary. If equal, sort by left boundary, then by original index.
        Arrays.sort(sorted, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[3], b[3]);
        });

        long[][] dpW = new long[5][n];
        int[][][] dpI = new int[5][n][];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i < n; i++) {
                dpI[k][i] = EMPTY_ARRAY;
            }
        }

        for (int i = 0; i < n; i++) {
            // Find the largest index j where the right boundary is strictly less than current left boundary
            int j = find(sorted, i);

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip the current interval
                long w1 = (i > 0) ? dpW[k][i - 1] : 0;
                int[] list1 = (i > 0) ? dpI[k][i - 1] : EMPTY_ARRAY;

                // Option 2: Take the current interval
                long w2 = sorted[i][2];
                int[] prevList = EMPTY_ARRAY;
                if (j >= 0) {
                    w2 += dpW[k - 1][j];
                    prevList = dpI[k - 1][j];
                }

                // Construct and sort the new sequence of indices
                int[] list2 = new int[prevList.length + 1];
                if (prevList.length > 0) {
                    System.arraycopy(prevList, 0, list2, 0, prevList.length);
                }
                list2[list2.length - 1] = sorted[i][3];
                if (list2.length > 1) {
                    Arrays.sort(list2);
                }

                // Keep track of the best choice so far
                long bestW = w1;
                int[] bestList = list1;

                if (w2 > bestW || (w2 == bestW && isSmaller(list2, bestList))) {
                    bestW = w2;
                    bestList = list2;
                }

                // It's technically possible that using fewer intervals (k - 1) up to `i` yields an equally optimal tie
                long w3 = dpW[k - 1][i];
                int[] list3 = dpI[k - 1][i];
                
                if (w3 > bestW || (w3 == bestW && isSmaller(list3, bestList))) {
                    bestW = w3;
                    bestList = list3;
                }

                dpW[k][i] = bestW;
                dpI[k][i] = bestList;
            }
        }

        // Return the lexicographically smallest list representing at most 4 elements
        return dpI[4][n - 1];
    }

    // Standard Binary search to find valid non-overlapping predecessor 
    private int find(int[][] sorted, int i) {
        int left = 0, right = i - 1;
        int ans = -1;
        int target = sorted[i][0];
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid][1] < target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // Helper to evaluate if array 'a' is lexicographically smaller than 'b'
    private boolean isSmaller(int[] a, int[] b) {
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] < b[i]) return true;
            if (a[i] > b[i]) return false;
        }
        return a.length < b.length;
    }
}