class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Check if a valid positive integer of length 'num' can have digit sum 'sum'
        if (sum < 1 || (long) num * 9 < sum) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);
        int remSum = sum;

        for (int i = 0; i < num; i++) {
            int d = Math.min(9, remSum);
            sb.append(d);
            remSum -= d;
        }

        return sb.toString();
    }
}