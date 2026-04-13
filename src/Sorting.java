public class Sorting {

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;

    }

    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(nums, low, high);
            quickSort(nums, low, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, high);
        }
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        quickSort(nums, 0, nums.length - 1);
    }

    public void sort2(int[] nums) {
        quickSort2(nums, 0, nums.length - 1);
    }

    public void quickSort2(int[] nums, int low, int high) {
        if (low < high) {
            int pivot = partition2(nums, low, high);
            quickSort2(nums, low, pivot - 1);
            quickSort2(nums, pivot + 1, high);
        }
    }

    public int partition2(int[] nums, int low, int high) {
        int highValue = nums[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums[j] < highValue) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, high, i + 1);

        return i + 1;
    }

    public void sort3(int[] nums) {
        quickSort3(nums, 0, nums.length - 1);
    }

    public void quickSort3(int[] nums, int low, int high) {
        if(low < high){
            int pivot = partition3(nums, low, high);
            quickSort3(nums, low, pivot - 1);
            quickSort3(nums, pivot + 1, high);
        }
    }

    public int partition3(int[] nums, int low, int high) {
        int highValue = nums[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(nums[j] < highValue){
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, high, i + 1);
        return i+ 1;
    }

    public static void main(String[] args) {
        Sorting util = new Sorting();
        int[] data = { 10, 7, 8, 9, 1, 5 };
        util.sort(data);

        for (int n : data)
            System.out.print(n + " ");
        System.out.println("");
        System.out.println("################");

        data = new int[] { 10, 7, 8, 9, 1, 5 };
        util.sort2(data);

        for (int n : data)
            System.out.print(n + " ");
        System.out.println("");
        System.out.println("################");

        data = new int[] { 10, 7, 8, 9, 1, 5 };
        util.sort3(data);

        for (int n : data)
            System.out.print(n + " ");

    }
}
