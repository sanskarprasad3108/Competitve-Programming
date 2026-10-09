class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openCount++;
            } else {
                // Check if the next character forms a pair "))"
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Consume the second ')'
                } else {
                    insertions++; // Insert a missing ')' to complete "))"
                }

                // Balance with an opening '('
                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++; // Insert a missing '('
                }
            }
        }

        // Each remaining '(' needs two ')'
        insertions += openCount * 2;

        return insertions;
    }
}