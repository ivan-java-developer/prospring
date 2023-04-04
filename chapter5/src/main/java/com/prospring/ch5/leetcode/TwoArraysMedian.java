package com.prospring.ch5.leetcode;

public class TwoArraysMedian {
    public static void main(String[] args) {
        TwoArraysMedian test = new TwoArraysMedian();
        System.out.println(test.findMedianOfArray(new int[] {1, 2}));
        System.out.println(test.findMedianOfArray(new int[] {1, 2, 3}));
    }
//   1 5 6
//   1 2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return findMedianOfArray(nums2);
        }
        if (nums2.length == 0) {
            return findMedianOfArray(nums1);
        }
        int i = (nums1.length + nums2.length) / 2;
        if (i < nums1.length) {
            int[] arr = nums1;
//            while ()
        }
        return 2.0;
    }

    public double findMedianOfArray(int[] nums) {
        int i = nums.length / 2;
        if (nums.length % 2 == 0) {
            return (nums[i - 1] + nums[i]) / 2.0;
        }
        return nums[i];
    }
}
