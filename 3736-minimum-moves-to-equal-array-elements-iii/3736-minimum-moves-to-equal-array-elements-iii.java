class Solution {
    public int minMoves(int[] nums) {
        int n=nums.length;
        int moves=0;
        Arrays.sort(nums);
        int max=nums[n-1];
        for(int i=0;i<n;i++){
            moves+=max-nums[i];
        }
        return moves;
    }
}