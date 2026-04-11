public class Sorting {

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private int partition(int[] nums, int low, int high){
        int pivot = nums[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(nums[j] < pivot){
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;

    }

    private void quickSort(int[] nums, int low, int high){
        if(low < high){
            int pivotIndex =  partition(nums, low, high);
            quickSort(nums, low, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, high);
            
        }
    }

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        quickSort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        Sorting util = new Sorting();
        int[] data = { 10, 7, 8, 9, 1, 5 };
        util.sort(data);

        for (int n : data)
            System.out.print(n + " ");
    }
}
