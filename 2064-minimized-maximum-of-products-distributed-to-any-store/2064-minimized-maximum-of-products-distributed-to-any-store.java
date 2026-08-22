class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
     int left=1;
     int right=0;
     for(int num:quantities){
        right=Math.max(right,num);
     }   
     while(left<=right){
        int mid=left+(right-left)/2;
        int store=0;
        for(int num:quantities){
            store+=(num+mid-1)/mid;
            if(store>n)break;
        }
        
     if(store<=n){
        right=mid-1;
     }else{
        left=mid+1;
     }
     }
     return left;
    }
}