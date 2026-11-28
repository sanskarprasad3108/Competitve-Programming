import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] count = new int[n + 1];
        long minPrice = Long.MAX_VALUE;

        for (int[] item : items) {
            count[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // multiples[f] = total number of items whose factor is a multiple of f
        int[] multiples = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (count[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multiples[f] += count[m];
            }
        }

        // Sort items by price ascending to greedily pick cheapest value-2 copies
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remBudget = budget;
        long totalItems = 0;

        for (int[] item : items) {
            long price = item[1];
            if (price >= 2 * minPrice || remBudget < price) {
                break;
            }

            long gain = multiples[item[0]] - 1; // exclude itself (i != j)
            if (gain <= 0) continue;

            long take = Math.min(gain, remBudget / price);
            totalItems += take * 2;
            remBudget -= take * price;
        }

        // Spend remaining budget on the cheapest item (value-1 copies)
        totalItems += remBudget / minPrice;

        return (int) totalItems;
    }
}