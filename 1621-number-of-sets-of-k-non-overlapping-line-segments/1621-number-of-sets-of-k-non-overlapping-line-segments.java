class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        
        // We are choosing 2k points from n + k - 1 points
        int N = n + k - 1;
        int K = 2 * k;
        
        if (K > N) return 0;
        
        long numerator = 1;
        long denominator = 1;
        
        // Calculate C(N, K) % MOD
        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        
        // (numerator / denominator) % MOD is equivalent to (numerator * modular_inverse(denominator)) % MOD
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }
    
    // Function to calculate (a^-1) % m using Fermat's Little Theorem
    private long modInverse(long a, int m) {
        return power(a, m - 2, m);
    }
    
    // Function to calculate (base^exp) % mod
    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}