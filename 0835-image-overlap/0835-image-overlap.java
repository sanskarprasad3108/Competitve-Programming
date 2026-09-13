class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        // Find all coordinates in both images where the value is 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{i, j});
                if (img2[i][j] == 1) ones2.add(new int[]{i, j});
            }
        }
        
        // 2D array to count the frequency of each translation vector.
        // We shift the indices by 'n' to handle negative translations.
        int[][] shiftCount = new int[2 * n + 1][2 * n + 1];
        int maxOverlap = 0;
        
        // Calculate the vector difference for every pair of 1s
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int dx = p1[0] - p2[0] + n;
                int dy = p1[1] - p2[1] + n;
                
                shiftCount[dx][dy]++;
                maxOverlap = Math.max(maxOverlap, shiftCount[dx][dy]);
            }
        }
        
        return maxOverlap;
    }
}