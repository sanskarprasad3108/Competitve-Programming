import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> maxPrimes(int n, int s) {
        List<Integer> result = new ArrayList<>();
        if (n < 2 || s < 2) {
            return result;
        }

        // Sieve of Eratosthenes to find primes up to n
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int multiple = p * p; multiple <= n; multiple += p) {
                    isPrime[multiple] = false;
                }
            }
        }

        // Greedily pick the smallest primes
        long currentSum = 0;
        for (int p = 2; p <= n; p++) {
            if (isPrime[p]) {
                if (currentSum + p <= s) {
                    currentSum += p;
                    result.add(p);
                } else {
                    // Adding any larger prime will also exceed s
                    break;
                }
            }
        }

        return result;
    }
}