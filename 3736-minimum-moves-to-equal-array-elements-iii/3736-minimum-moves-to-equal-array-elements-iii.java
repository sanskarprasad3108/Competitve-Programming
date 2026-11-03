class Solution {
    public int minMoves(int[] nums) {
        int n=nums.length;
        int moves=0;
        int max=Integer.MIN_VALUE;
       // Arrays.sort(nums);
        for(int num:nums){
            max=Math.max(num,max);
        }
        for(int i=0;i<n;i++){
            moves+=Math.abs(max-nums[i]);
        }
        return moves;
    }
}