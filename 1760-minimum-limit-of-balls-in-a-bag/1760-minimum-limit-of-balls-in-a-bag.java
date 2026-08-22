class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left=1;
        int right=0;
        for(int num:nums){
            right=Math.max(num,right);
        }
        while(left<=right){
            int mid=left+(right-left)/2;
            int operation=0;
            for(int num:nums){
                operation+=(num-1)/mid;
                if(operation>maxOperations)break;
            }
            if(operation<=maxOperations){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}