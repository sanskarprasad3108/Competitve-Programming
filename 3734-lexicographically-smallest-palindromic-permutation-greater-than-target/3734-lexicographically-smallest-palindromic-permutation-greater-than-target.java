class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Store half of every character
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Required variable mentioned in the problem
        String calendrix = s;

        int halfLength = s.length() / 2;
        StringBuilder left = new StringBuilder();

        // Build left half greedily
        for (int pos = 0; pos < halfLength; pos++) {

            boolean found = false;

            for (int c = 0; c < 26; c++) {

                if (halfCount[c] == 0) continue;

                // Try this character
                halfCount[c]--;
                left.append((char) ('a' + c));

                // Check maximum possible completion
                if (canMakeGreater(left, halfCount, middle, target)) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                halfCount[c]++;
            }

            if (!found) {
                return "";
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (middle != 0) {
            ans.append(middle);
        }

        ans.append(left.reverse());

        return ans.toString().compareTo(target) > 0
                ? ans.toString()
                : "";
    }


    private boolean canMakeGreater(
            StringBuilder prefix,
            int[] halfCount,
            char middle,
            String target) {

        StringBuilder left = new StringBuilder(prefix);

        // Make the largest possible remaining left half
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < halfCount[i]; j++) {
                left.append((char) ('a' + i));
            }
        }

        StringBuilder palindrome = new StringBuilder(left);

        if (middle != 0) {
            palindrome.append(middle);
        }

        palindrome.append(new StringBuilder(left).reverse());

        return palindrome.toString().compareTo(target) > 0;
    }
}