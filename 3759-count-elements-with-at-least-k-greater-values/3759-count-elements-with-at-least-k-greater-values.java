class Solution {
    public int countElements(int[] nums, int k) {
        java.util.Arrays.sort(nums);
        int n = nums.length;
        int totalQualified = 0;
        
        int i = 0;
        while (i < n) {
            int j = i;
            // Find the end of the current group of identical elements
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            
            // Elements strictly greater than nums[i] are from index j to n - 1
            int greaterCount = n - j;
            
            if (greaterCount >= k) {
                totalQualified += (j - i); // Add the frequency of this element
            }
            
            i = j; // Move to the next unique element
        }
        
        return totalQualified;
    }
}