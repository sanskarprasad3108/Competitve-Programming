import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            // If opening bracket, push to stack
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else {
                // If closing bracket but stack is empty
                if (st.isEmpty()) {
                    return false;
                }
                char top = st.pop();
                
                // Check matching pair
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // At the end, stack must be empty for a valid string
        return st.isEmpty();
    }
}
