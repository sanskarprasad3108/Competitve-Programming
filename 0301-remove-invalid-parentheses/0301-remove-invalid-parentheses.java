class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int remL = 0, remR = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                remL++;
            } else if (c == ')') {
                if (remL > 0) {
                    remL--;
                } else {
                    remR++;
                }
            }
        }
        
        Set<String> result = new HashSet<>();
        backtrack(s, 0, new StringBuilder(), remL, remR, 0, result);
        return new ArrayList<>(result);
    }
    
    private void backtrack(String s, int index, StringBuilder sb, 
                           int remL, int remR, int openCount, Set<String> result) {
        if (index == s.length()) {
            if (remL == 0 && remR == 0 && openCount == 0) {
                result.add(sb.toString());
            }
            return;
        }
        
        char c = s.charAt(index);
        int len = sb.length();
        
        if (c == '(' && remL > 0) {
            backtrack(s, index + 1, sb, remL - 1, remR, openCount, result);
        } else if (c == ')' && remR > 0) {
            backtrack(s, index + 1, sb, remL, remR - 1, openCount, result);
        }
        
        sb.append(c);
        if (c != '(' && c != ')') {
            backtrack(s, index + 1, sb, remL, remR, openCount, result);
        } else if (c == '(') {
            backtrack(s, index + 1, sb, remL, remR, openCount + 1, result);
        } else if (openCount > 0) {
            backtrack(s, index + 1, sb, remL, remR, openCount - 1, result);
        }
        sb.setLength(len);
    }
}