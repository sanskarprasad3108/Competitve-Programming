class Solution {
    
    public int splitArray(int[] nums, int k) {
        int sum=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        int ans=-1;
        int st=0,end=sum;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(isValid(nums,n,k,mid)){
                ans=mid;
                end=mid-1;
            }
            else{
                st=mid+1;
            }
        }
        return ans;
    }
    private boolean isValid(int[] nums,int n,int k,int max){
        int paint=1,wall=0;
        for(int i=0;i<n;i++){
            if(nums[i]>max){
                return false;
            }
            if(wall+nums[i]<=max){
                wall+=nums[i];
            }
            else {
                paint++;
                wall=nums[i];
        }
        
    }
    return paint>k ? false:true;
}
}