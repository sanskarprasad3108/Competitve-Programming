class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long totalCost = 0;
        
        // 1. Fulfill the common requirement using the cheaper option between Type 3 and (Type 1 + Type 2)
        long common = Math.min(need1, need2);
        totalCost += common * Math.min((long) costBoth, (long) cost1 + cost2);
        need1 -= common;
        need2 -= common;
        
        // 2. Fulfill remaining need1 using the cheaper option between Type 1 and Type 3
        if (need1 > 0) {
            totalCost += (long) need1 * Math.min((long) costBoth, (long) cost1);
        }
        
        // 3. Fulfill remaining need2 using the cheaper option between Type 2 and Type 3
        if (need2 > 0) {
            totalCost += (long) need2 * Math.min((long) costBoth, (long) cost2);
        }
        
        return totalCost;
    }
}