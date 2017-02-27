class Solution {
    public static void main(String args[]) {
        assert isAtMostOneEditAway("a", "");
        assert isAtMostOneEditAway("a", "a");
        assert isAtMostOneEditAway("a", "ab");
        assert isAtMostOneEditAway("pale", "ple");
        assert isAtMostOneEditAway("pales", "pale");
        assert isAtMostOneEditAway("pale", "bale");
        assert !isAtMostOneEditAway("pale", "bake");
        System.out.println("Sucess!");
    }

    static boolean isAtMostOneEditAway(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (Math.abs(len1 - len2) > 1) return false; //how does the book avoid the usage of abs?
        if (len1 != len2) {
            char[] longer = len1 > len2 ? s1.toCharArray() : s2.toCharArray();
            char[] shorter = len1 > len2 ? s2.toCharArray() : s1.toCharArray();
            int i = 0;
            boolean diff = false;
            for (int j = 0; j < shorter.length; i++) {
                if ((longer[i] != shorter[j]) && !diff) diff = true;
                else if ((longer[i] != shorter[j]) && diff) return false;
                else j++;
            }
            return true;
        }
        else {
            char[] w1 = s1.toCharArray();
            char[] w2 = s2.toCharArray();
            boolean diff = false;
            for (int i = 0; i < len1; i++) {
                if ((w1[i] != w2[i]) && !diff) diff = true;
                else if ((w1[i] != w2[i]) && diff) return false;
            }
            return true;
        }
    }
}