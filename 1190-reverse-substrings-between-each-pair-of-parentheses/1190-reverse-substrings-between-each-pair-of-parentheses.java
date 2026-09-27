import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                StringBuilder temp = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '(') {
                    temp.append(stack.pop());
                }
                // Remove the matching '('
                stack.pop();

                // Push reversed characters back onto the stack
                for (int i = 0; i < temp.length(); i++) {
                    stack.push(temp.charAt(i));
                }
            } else {
                stack.push(c);
            }
        }

        // Build result
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pollLast());
        }
        return result.toString();
    }
}