import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int len = s.length();
        int[][] cost = new int[len][len];

        // Step 1: Precompute minimum cost to make s[i...j] (length >= 2) a semi-palindrome
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int subLen = j - i + 1;
                int minChanges = subLen;

                // Check all proper divisors d of subLen (1 <= d < subLen)
                for (int d = 1; d < subLen; d++) {
                    if (subLen % d == 0) {
                        int currentChanges = 0;
                        for (int start = 0; start < d; start++) {
                            int left = i + start;
                            int right = j - (d - 1 - start);
                            while (left < right) {
                                if (s.charAt(left) != s.charAt(right)) {
                                    currentChanges++;
                                }
                                left += d;
                                right -= d;
                            }
                        }
                        minChanges = Math.min(minChanges, currentChanges);
                    }
                }
                cost[i][j] = minChanges;
            }
        }

        // Step 2: DP where each of the k substrings must have length >= 2
        int[][] dp = new int[len + 1][k + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= len; i++) {
                // prev <= i - 2 ensures the current substring s[prev...i-1] has length >= 2
                for (int prev = 2 * (parts - 1); prev <= i - 2; prev++) {
                    if (dp[prev][parts - 1] != 1000000) {
                        dp[i][parts] = Math.min(dp[i][parts], dp[prev][parts - 1] + cost[prev][i - 1]);
                    }
                }
            }
        }

        return dp[len][k];
    }
}