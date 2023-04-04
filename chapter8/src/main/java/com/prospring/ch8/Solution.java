package com.prospring.ch8;

import java.util.*;

class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int i = start + (end - start) / 2;
            if (nums[i] == target) {
                return i;
            }
            if (nums[i] > target) {
                end = i - 1;
            }
            if (nums[i] < target) {
                start = i + 1;
            }
        }
        return -1;
    }

    private boolean isBadVersion(int n) {
        int bad = 1;
        return n >= bad;
    }

    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        int result = n;
        while (start <= end) {
            n = start + (end - start) / 2;
            boolean isBad = isBadVersion(n);
            if (isBad) {
                end = n - 1;
                if (result > n) {
                    result = n;
                }
            }
            if (!isBad) {
                start = n + 1;
            }
        }
        return result;
    }

    public int searchInsert(int[] nums, int target) {
        return binarySearchInsert(nums, target, 0, nums.length - 1);
    }

    private int binarySearchInsert(int[] nums, int target, int start, int end) {
        if (start >= end) {
            if (end < 0) {
                return 0;
            }
            if (nums[end] < target) {
                return end + 1;
            }
            return end;
        }
        int i = start + (end - start) / 2;
        if (nums[i] > target) {
            return binarySearchInsert(nums, target, start, i - 1);
        }
        if (nums[i] < target){
            return binarySearchInsert(nums, target, i + 1, end);
        }
        return i;
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int i = 0;
        int j = n - 1;
        for (int k = 0; k < nums.length; k++) {
            int resultIndex = n - k - 1;
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                result[resultIndex] = nums[i] * nums[i];
                i++;
            } else {
                result[resultIndex] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % nums.length;
        flip(nums, 0, n - k - 1);
        flip(nums, n - k, n - 1);
        flip(nums, 0, n - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void flip(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void moveZeroes(int[] nums) {
        int zeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            } else {
                nums[i - zeros] = nums[i];
            }
        }
        for (int i = nums.length - zeros; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int[] result = new int[2];
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length() || (s.charAt(i) == ' ')) {
                end = i - 1;
                reverseWord(chars, start, end);
                start = i + 1;
            }
        }
        return String.valueOf(chars);
    }

    private void reverseWord(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode middleNode(ListNode head) {
        int count = 1;
        ListNode next = head;
        while (next.next != null) {
            next = next.next;
            count++;
        }
        next = head;
        for (int i = 0; i < count / 2; i++) {
            next = next.next;
        }
        return next.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 1;
        ListNode next = head;
        while (next.next != null) {
            next = next.next;
            count++;
        }
        next = head;
        for (int i = 1; i < count - n; i++) {
            next = next.next;
        }
        if (n == count) {
            return head.next;
        }
        next.next = next.next.next;

        return head;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (set.contains(ch)) {
                while (s.charAt(l) != ch) {
                    set.remove(s.charAt(l));
                    l++;
                }
                l++;
            }
            set.add(ch);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> chars = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            chars.merge(s1.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < s2.length(); i++) {
            if (chars.containsKey(s2.charAt(i))) {
                HashMap<Character, Integer> map = new HashMap<>(chars);
                int k = i;
                while (k < s2.length() && map.containsKey(s2.charAt(k))) {
                    Integer count = map.get(s2.charAt(k));
                    if (count == 1) {
                        map.remove(s2.charAt(k));
                    } else {
                        map.put(s2.charAt(k), count - 1);
                    }
                    k++;
                }
                if (map.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.checkInclusion("adc", "dcda");
        System.out.println(b);
    }
}
