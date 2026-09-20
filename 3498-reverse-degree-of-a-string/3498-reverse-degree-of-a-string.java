class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // 'z' -> 1, 'y' -> 2, ..., 'a' -> 26
            int revAlphabetIndex = 'z' - s.charAt(i) + 1;
            int stringIndex = i + 1; // 1-indexed position

            total += revAlphabetIndex * stringIndex;
        }

        return total;
    }
}