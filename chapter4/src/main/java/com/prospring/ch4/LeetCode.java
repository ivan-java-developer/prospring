package com.prospring.ch4;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        System.out.println(leetCode.lengthOfLongestSubstring("a"));
        System.out.println(leetCode.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(leetCode.lengthOfLongestSubstring("bbbbb"));
        System.out.println(leetCode.lengthOfLongestSubstring("pwwkew"));
        System.out.println(leetCode.lengthOfLongestSubstring("abba"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> chars = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (chars.containsKey(ch)) {
                left = Math.max(left, chars.get(ch) + 1);
            }
            chars.put(ch, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
