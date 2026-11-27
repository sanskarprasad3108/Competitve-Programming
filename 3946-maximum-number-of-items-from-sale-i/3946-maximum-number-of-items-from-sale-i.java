class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] freq = new int[maxFactor + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // multiples[f] = total number of items whose factor is divisible by f
        int[] multiples = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multiples[f] += freq[m];
            }
        }

        // 0/1 Knapsack for buying at most 1 copy of each item
        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int price = item[1];
            int gain = multiples[item[0]];
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + gain);
            }
        }

        // Spend any leftover budget on extra copies of the cheapest item
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }

        return ans;
    }
}