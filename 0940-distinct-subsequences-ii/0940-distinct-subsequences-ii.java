class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // Array to store the count of distinct subsequences ending with a specific character (a-z)
        long[] endsWith = new long[26];
        long currentTotal = 0;
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            
            // The previous number of subsequences that ended with this character
            long previousCount = endsWith[index];
            
            // The new number of subsequences ending with this character is 
            // the total current subsequences + 1 (for the character on its own)
            long newCount = (currentTotal + 1) % MOD;
            
            // Update the count for this specific character
            endsWith[index] = newCount;
            
            // Update the running total. 
            // We add the new subsequences and subtract the old ones to avoid double counting.
            currentTotal = (currentTotal + newCount - previousCount) % MOD;
            
            // Handle negative modulo results in Java
            if (currentTotal < 0) {
                currentTotal += MOD;
            }
        }
        
        return (int) currentTotal;
    }
}