import java.util.ArrayList;
import java.util.List;

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

    public double findMedianSortedArraysV10(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArraysV10(nums2, nums1);
        }
        int halfSize = (m + n + 1) / 2;
        double result = 0;

        int left = 0;
        int right = m;
        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = halfSize - partition1;

            int leftMax1 = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int rightMin1 = partition1 == m ? Integer.MAX_VALUE : nums1[partition1];
            int leftMax2 = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int rightMin2 = partition2 == n ? Integer.MAX_VALUE : nums2[partition2];
            if (leftMax1 <= rightMin2 && leftMax2 <= rightMin1) {
                if ((m + n) % 2 == 1) {
                    return (double) Math.max(leftMax1, leftMax2);
                } else {
                    return (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2.0;
                }
            } else if (leftMax1 > rightMin2) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }

        return result;
    }

    static class MedianTestCase {
        String description;
        int[] nums1;
        int[] nums2;
        double expected;

        public MedianTestCase(String description, int[] nums1, int[] nums2, double expected) {
            this.description = description;
            this.nums1 = nums1;
            this.nums2 = nums2;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode4 solver = new LeetCode4();
        System.out.println(
                "Running Median of Two Sorted Arrays Test Suite...\n------------------------------------------");

        // 1. Accumulate test structures mapping inputs to expected double precision
        // medians
        List<MedianTestCase> testCases = new ArrayList<>();

        // Example 1: Combined [1, 2, 3] -> Odd length median
        testCases.add(new MedianTestCase(
                "Odd combined length",
                new int[] { 1, 3 },
                new int[] { 2 },
                2.0));

        // Example 2: Combined [1, 2, 3, 4] -> Even length median (2 + 3) / 2
        testCases.add(new MedianTestCase(
                "Even combined length",
                new int[] { 1, 2 },
                new int[] { 3, 4 },
                2.5));

        // Edge Case: One completely empty array
        testCases.add(new MedianTestCase(
                "First array is empty",
                new int[] {},
                new int[] { 2, 3, 4, 5 },
                3.5));

        // Edge Case: Completely disjoint arrays
        testCases.add(new MedianTestCase(
                "Completely non-overlapping elements",
                new int[] { 10, 11, 12 },
                new int[] { 1, 2, 3, 4 },
                4.0));

        // Edge Case: Identical elements across arrays
        testCases.add(new MedianTestCase(
                "Arrays containing completely matching elements",
                new int[] { 2, 2, 2 },
                new int[] { 2, 2, 2, 2 },
                2.0));

        // 2. Run validations, invoking the method exactly once per configuration layout
        int passed = 0;
        double epsilon = 0.00001; // Small threshold tolerance for floating point comparisons

        for (int i = 0; i < testCases.size(); i++) {
            MedianTestCase tc = testCases.get(i);

            // Core invocation point
            double actual = solver.findMedianSortedArraysV10(tc.nums1, tc.nums2);

            if (Math.abs(actual - tc.expected) < epsilon) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Expected: " + tc.expected);
                System.err.println("       Actual:   " + actual);
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));

    }

}
