class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        int totalAdjacentPairs = 0;
        
        // Count all equal adjacent pairs considering the string as circular
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == s.charAt((i + 1) % n)) {
                totalAdjacentPairs++;
            }
        }
        
        int validRotations = 0;
        
        // Check each rotation by seeing which circular pair gets "broken"
        for (int i = 0; i < n; i++) {
            boolean brokenPairIsEqual;
            if (i == 0) {
                brokenPairIsEqual = (s.charAt(n - 1) == s.charAt(0));
            } else {
                brokenPairIsEqual = (s.charAt(i - 1) == s.charAt(i));
            }
            
            int currentScore = totalAdjacentPairs - (brokenPairIsEqual ? 1 : 0);
            if (currentScore == k) {
                validRotations++;
            }
        }
        
        return validRotations;
    }
}