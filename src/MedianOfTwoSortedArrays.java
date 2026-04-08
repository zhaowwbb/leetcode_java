public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return -1d;
        }
        if (null == nums2 || nums2.length == 0) {
            return -1d;
        }
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        // Copy any remaining elements from either array
        while (i < nums1.length) {
            merged[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            merged[k++] = nums2[j++];
        }
        int mid = merged.length / 2;
        if (merged.length % 2 == 0) {
            return (merged[mid - 1] + merged[mid]) / 2.0;
        } else {
            return merged[mid];
        }

        // return 0d;

    }

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        for (i = 0; i < nums1.length && j < nums2.length; k++) {
            if (nums1[i] < nums2[j]) {
                merged[k] = nums1[i];
                i++;
            } else {
                merged[k] = nums2[j];
                j++;
            }
        }
        while (i < nums1.length) {
            merged[k] = nums1[i];
            i++;
            k++;
        }
        while (j < nums2.length) {
            merged[k] = nums2[j];
            j++;
            k++;
        }
        int mid = merged.length / 2;
        if (merged.length % 2 == 0) {
            return (merged[mid - 1] + merged[mid]) / 2.0;
        } else {
            return merged[mid];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 3 };
        int[] nums2 = { 2 };
        MedianOfTwoSortedArrays utils = new MedianOfTwoSortedArrays();
        double result = utils.findMedianSortedArrays(nums1, nums2);
        System.out.println("expected: 2.0, actual: " + result);
        result = utils.findMedianSortedArraysV2(nums1, nums2);
        System.out.println("expected: 2.0, actual: " + result);

        nums1 = new int[] { 1, 2 };
        nums2 = new int[] { 3, 4 };
        result = utils.findMedianSortedArrays(nums1, nums2);
        System.out.println("expected: 2.5, actual: " + result);

        result = utils.findMedianSortedArraysV2(nums1, nums2);
        System.out.println("expected: 2.5, actual: " + result);
    }
}
