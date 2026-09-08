class Solution {
    public int countCommas(int n) {
        int commas = 0;
        int start = 1000;
        int commasPerNumber = 1;

        while (start <= n) {
            int end = start * 1000 - 1;
            int count = Math.min(n, end) - start + 1;
            commas += count * commasPerNumber;

            start *= 1000;
            commasPerNumber++;
        }

        return commas;
    }
}