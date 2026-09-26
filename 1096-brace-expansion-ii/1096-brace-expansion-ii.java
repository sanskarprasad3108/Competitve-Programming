import java.util.*;

class Solution {
    private int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> resultSet = parseExpr(expression);
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    // Parses an expression: expr -> term (',' term)*
    private Set<String> parseExpr(String s) {
        Set<String> res = new TreeSet<>();
        res.addAll(parseTerm(s));

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // consume ','
            res.addAll(parseTerm(s));
        }

        return res;
    }

    // Parses a term (concatenation of factors): term -> factor+
    private Set<String> parseTerm(String s) {
        Set<String> res = new TreeSet<>();
        res.add(""); // identity for concatenation

        while (index < s.length() && s.charAt(index) != ',' && s.charAt(index) != '}') {
            Set<String> nextFactor = parseFactor(s);
            res = multiply(res, nextFactor);
        }

        return res;
    }

    // Parses a factor: factor -> letter+ | '{' expr '}'
    private Set<String> parseFactor(String s) {
        char c = s.charAt(index);
        if (c == '{') {
            index++; // consume '{'
            Set<String> res = parseExpr(s);
            index++; // consume '}'
            return res;
        } else {
            // Read consecutive lowercase letters
            StringBuilder sb = new StringBuilder();
            while (index < s.length() && Character.isLowerCase(s.charAt(index))) {
                sb.append(s.charAt(index++));
            }
            Set<String> res = new TreeSet<>();
            res.add(sb.toString());
            return res;
        }
    }

    // Computes the Cartesian product (concatenation) of two sets of strings
    private Set<String> multiply(Set<String> setA, Set<String> setB) {
        Set<String> res = new TreeSet<>();
        for (String a : setA) {
            for (String b : setB) {
                res.add(a + b);
            }
        }
        return res;
    }
}