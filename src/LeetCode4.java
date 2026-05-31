public class LeetCode4 {

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
        // System.out.printf("firstMedian=%d, secondMedian=%d, mid=%d %n",firstMedian,
        // secondMedian, mid);
        double result = (double) (firstMedian + secondMedian) / 2;
        return result;
    }

    public double findMedianSortedArraysV7(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mid = (m + n) / 2;
        int left = mid;
        int right = mid;
        if ((m + n) % 2 == 0) {
            left = mid - 1;
        }
        left++;
        right++;
        // System.out.println("left=" + left + ", right=" + right);

        int count = 0;
        int i = 0;
        int j = 0;

        int leftValue = 0;
        int rightValue = 0;
        int val = 0;
        while (i < nums1.length || j < nums2.length) {

            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    val = nums1[i];
                    i++;

                } else {
                    val = nums2[j];
                    j++;
                }
            } else if (i < nums1.length) {
                val = nums1[i];
                i++;
            } else {
                val = nums2[j];
                j++;
            }
            if (mid == 0) {
                leftValue = val;
                rightValue = val;
                break;
            }
            count++;
            if (count == left) {
                leftValue = val;
            }
            if (count == right) {
                rightValue = val;
                break;
            }
        }
        // System.out.println("leftValue=" + leftValue + ", rightValue=" + rightValue);

        double median = (double) (leftValue + rightValue) / 2;

        return median;
    }

    public double findMedianSortedArraysV8(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysV8(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int halfSize = (m + n + 1) / 2;
        int left = 0;
        int right = m;
        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = halfSize - partition1;
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // found
                if ((m + n) % 2 == 1) {
                    return (double) Math.max(maxLeft1, maxLeft2);
                } else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }
        return 0;
    }

    public double findMedianSortedArraysV9(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int halfSize = (m + n + 1) / 2;
        int left = 0;
        int right = m;
        while (left <= right) {
            int p1 = (left + right) / 2;
            int p2 = halfSize - p1;

            int leftMax1 = p1 == 0 ? Integer.MIN_VALUE : nums1[p1 - 1];
            int rightMin1 = p1 == m ? Integer.MAX_VALUE : nums1[p1];
            int leftMax2 = p2 == 0 ? Integer.MIN_VALUE : nums2[p2 - 1];
            int rightMin2 = p2 == n ? Integer.MAX_VALUE : nums2[p2];

            if (leftMax1 <= rightMin2 && leftMax2 <= rightMin1) {
                if ((m + n) % 2 == 1) {
                    return (double) Math.max(leftMax1, leftMax2);
                } else {
                    return (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2.0;
                }
            } else if (leftMax1 > rightMin2) {
                right = p1 - 1;
            } else {
                left = p1 + 1;
            }
        }
        return 0;
    }

    public void test(int[] nums1, int[] nums2, double expect) {
        System.out.println(
                "Input: nums1 = " + java.util.Arrays.toString(nums1) + ", nums2 = " + java.util.Arrays.toString(nums2));

        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println("[V1] expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV2(nums1, nums2);
        System.out.println("[V2] expected: " + expect + ", actual: " + result);

        // result = findMedianSortedArraysV3(nums1, nums2);
        // System.out.println("[V3] expected: " + expect + ", actual: " + result);

        // result = findMedianSortedArraysV4(nums1, nums2);
        // System.out.println("[V4] expected: " + expect + ", actual: " + result);

        // result = findMedianSortedArraysV5(nums1, nums2);
        // System.out.println("[V5] expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV6(nums1, nums2);
        System.out.println("[V6] expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV7(nums1, nums2);
        System.out.println("[V7] expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV8(nums1, nums2);
        System.out.println("[V8] expected: " + expect + ", actual: " + result);

        result = findMedianSortedArraysV9(nums1, nums2);
        System.out.println("[V9] expected: " + expect + ", actual: " + result);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode4 utils = new LeetCode4();

        int[] nums1 = { 1, 3 };
        int[] nums2 = { 2 };
        utils.test(nums1, nums2, 2.00000);

        nums1 = new int[] { 1, 2 };
        nums2 = new int[] { 3, 4 };
        utils.test(nums1, nums2, 2.50000);

        nums1 = new int[] {};
        nums2 = new int[] { 1 };
        utils.test(nums1, nums2, 1.00000);
    }

}
