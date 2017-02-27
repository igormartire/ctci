import java.lang.StringBuilder;

class Solution {
    public static void main(String[] args) {
        assert compress("aabcccccaaa").equals("a2b1c5a3");
        assert compress("abc").equals("abc");
        assert compress("aaa").equals("a3");
        assert compress("aa").equals("aa");
        assert compress("a").equals("a");
        assert compress("").equals("");
        System.out.println("Sucess!");
    }

    private static String compress(String s) {
        if (s.length() < 3) return s;
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) count++;
            else {
                sb.append(s.charAt(i-1) + String.valueOf(count));
                count = 1;
            }
        }
        sb.append(s.charAt(s.length()-1) + String.valueOf(count));
        if (sb.length() >= s.length()) return s;
        return sb.toString();
    }
}