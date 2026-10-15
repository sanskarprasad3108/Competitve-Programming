import java.util.*;

class Solution {
    public List<Integer> maxPrimeSubset(int n, int s) {
        // Step 1: Find all primes <= n
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        int m = primeList.size();
        final int INF = 1_000_000_000;

        // dp[i][t] = min primes needed from suffix [i...m-1] to get sum t
        int[][] dp = new int[m + 1][s + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[m][0] = 0;

        for (int i = m - 1; i >= 0; i--) {
            int p = primeList.get(i);
            for (int t = 0; t <= s; t++) {
                // Option 1: Do not include primeList[i]
                dp[i][t] = dp[i + 1][t];

                // Option 2: Include primeList[i]
                if (t >= p && dp[i + 1][t - p] != INF) {
                    dp[i][t] = Math.min(dp[i][t], 1 + dp[i + 1][t - p]);
                }
            }
        }

        // Step 2: Find the maximum sum <= s reachable using at least 1 prime
        int targetSum = -1;
        for (int t = s; t >= 1; t--) {
            if (dp[0][t] != INF) {
                targetSum = t;
                break;
            }
        }

        if (targetSum == -1) {
            return Collections.emptyList();
        }

        int targetCount = dp[0][targetSum];

        // Step 3: Reconstruct the lexicographically smallest subset
        List<Integer> result = new ArrayList<>();
        int currSum = targetSum;
        int currCount = targetCount;

        for (int i = 0; i < m && currCount > 0; i++) {
            int p = primeList.get(i);
            // Greedily pick the current prime if it leads to an optimal solution
            if (currSum >= p && dp[i + 1][currSum - p] == currCount - 1) {
                result.add(p);
                currSum -= p;
                currCount--;
            }
        }

        return result;
    }
}