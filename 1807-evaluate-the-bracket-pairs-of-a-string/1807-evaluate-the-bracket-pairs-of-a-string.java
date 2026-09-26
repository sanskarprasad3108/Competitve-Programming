import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Pre-populate map for O(1) key lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> entry : knowledge) {
            map.put(entry.get(0), entry.get(1));
        }

        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean inside = false;

        // Step 2: Single pass parse
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                inside = true;
                key.setLength(0); // reset buffer
            } else if (c == ')') {
                inside = false;
                res.append(map.getOrDefault(key.toString(), "?"));
            } else if (inside) {
                key.append(c);
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}