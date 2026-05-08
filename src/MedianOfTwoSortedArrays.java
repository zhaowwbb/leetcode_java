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

    public double findMedianSortedArraysV3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysV3(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 == 0) {
                    return (double) (Math.max(maxLeftY, maxLeftX) + Math.min(minRightX, minRightY)) / 2.0;

                } else {
                    return (double) (Math.max(maxLeftY, maxLeftX));
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        return 0;
    }

    public double findMedianSortedArraysV4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) {
            merged[k++] = nums1[i++];
        }
        while (j < n) {
            merged[k++] = nums2[j++];
        }
        int mid = (m + n) / 2;
        if ((m + n) % 2 == 0) {
            return (long) (merged[mid - 1] + merged[mid]) / 2.0;
        } else {
            return (long) merged[mid];
        }
    }

    public double findMedianSortedArraysV5(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysV5(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;
        // int maxLeftX = 0, maxLeftY = 0, minRightX = 0, minRightY = 0;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((m + n) % 2 == 0) {
                    return (double) (Math.max(maxLeftY, maxLeftX) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return (double) Math.max(maxLeftY, maxLeftX);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        return 0;
    }

    public double findMedianSortedArraysV6(int[] nums1, int[] nums2) {
        if (null == nums1 || null == nums2)
            return 0;
        int m = nums1.length;
        int n = nums2.length;
        int mid = (m + n) / 2;
        int[] merged = new int[mid + 1];
        int i = 0;
        int j = 0;
        boolean isOdd = (m + n) % 2 != 0;
        int count = 0;

        while (count < mid + 1 && i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[count++] = nums1[i++];
            } else {
                merged[count++] = nums2[j++];
            }
        }
        // one array go to end
        if (count < mid + 1) {
            // handle nums1 left
            while (count < mid + 1 && i < m) {
                merged[count++] = nums1[i++];
            }
            // handle nums1 left
            while (count < mid + 1 && j < n) {
                merged[count++] = nums2[j++];
            }
        }
        int firstMedian = isOdd ? merged[mid] : merged[mid - 1];
        int secondMedian = isOdd ? merged[mid] : merged[mid];
        System.out.printf("firstMedian=%d, secondMedian=%d, mid=%d %n",firstMedian, secondMedian, mid);
        double result = (double)(firstMedian + secondMedian) / 2;
        return result;
    }

    public void test(int[] nums1, int[] nums2, double expect) {
        System.out.println(
                "Input: nums1 = " + java.util.Arrays.toString(nums1) + ", nums2 = " + java.util.Arrays.toString(nums2));

        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV2(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV3(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV4(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV5(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV6(nums1, nums2);
        System.out.println("expected: " + expect + ", actual: " + result);
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays utils = new MedianOfTwoSortedArrays();

        int[] nums1 = { 1, 3 };
        int[] nums2 = { 2 };
        utils.test(nums1, nums2, 2.00000);

        nums1 = new int[] { 1, 2 };
        nums2 = new int[] { 3, 4 };
        utils.test(nums1, nums2, 2.50000);
    }

}
