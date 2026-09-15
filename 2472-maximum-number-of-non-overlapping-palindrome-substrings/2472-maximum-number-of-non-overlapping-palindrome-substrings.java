class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        
        // 1. Precompute all palindromes
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) {
                        isPal[i][j] = true;
                    } else {
                        isPal[i][j] = isPal[i + 1][j - 1];
                    }
                }
            }
        }
        
        int[] dp = new int[n + 1];
        
        // 2. DP to find the maximum number of non-overlapping palindromes
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i]; // Skip the current character
            
            // Check all valid starting positions for a palindrome ending at i
            for (int j = 0; j <= i - k + 1; j++) {
                if (isPal[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
        }
        
        return dp[n];
    }
}