class Solution {
    public boolean threeFibonacciSum(int n) {
        long a = 0;
        long b = 1;
        long c = 1;

        while (a + b + c <= n) {
            if (a + b + c == n) {
                return true;
            }
            
            long next = b + c;
            a = b;
            b = c;
            c = next;
        }

        return false;
    }
}