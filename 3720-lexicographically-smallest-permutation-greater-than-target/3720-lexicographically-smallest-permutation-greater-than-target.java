class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Required by the problem statement
        String quinorath = s;

        // matched[i] tells us whether target[i] was successfully used
        int matched = 0;

        // First, consume as much of target as possible
        while (matched < n) {
            int c = target.charAt(matched) - 'a';

            if (count[c] == 0) {
                break;
            }

            count[c]--;
            matched++;
        }

        // Try positions from right to left
        for (int i = matched; i >= 0; i--) {

            // If i < matched, restore target[i]
            if (i < matched) {
                count[target.charAt(i) - 'a']++;
            }

            // Prefix before i must exactly match target
            boolean validPrefix = true;

            // Find smallest available character > target[i]
            if (i < n) {
                int curr = target.charAt(i) - 'a';

                for (int c = curr + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        StringBuilder ans = new StringBuilder();

                        // Same prefix as target
                        ans.append(target, 0, i);

                        // Smallest character greater than target[i]
                        ans.append((char) ('a' + c));
                        count[c]--;

                        // Append remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {
                            while (count[j] > 0) {
                                ans.append((char) ('a' + j));
                                count[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }
        }

        return "";
    }
}