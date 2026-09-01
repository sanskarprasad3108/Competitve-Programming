class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length(), b = s2.length(), c = s3.length();
        if (a + b != c) return false;
        boolean[] reach = new boolean[b + 1];
        reach[0] = true;
        for (int j = 1; j <= b; j++) reach[j] = reach[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        for (int i = 1; i <= a; i++) {
            reach[0] = reach[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= b; j++) {
                char need = s3.charAt(i + j - 1);
                reach[j] = (reach[j] && s1.charAt(i - 1) == need) || (reach[j - 1] && s2.charAt(j - 1) == need);
            }
            }
            return reach[b];
    }
    }
           