class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // Impossible case
        if (n == 2) return -1;
        
        // Find minimum and maximum characters
        char mn = 'z';
        char mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        
        // Can be sorted in 1 operation
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }
        
        // Check if mn appears in s[0 ... n - 2]
        boolean mnInPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == mn) {
                mnInPrefix = true;
                break;
            }
        }
        
        // Check if mx appears in s[1 ... n - 1]
        boolean mxInSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == mx) {
                mxInSuffix = true;
                break;
            }
        }
        
        // 3 operations if the only mn is at the end AND the only mx is at the beginning
        if (!mnInPrefix && !mxInSuffix) {
            return 3;
        }
        
        return 2;
    }
}