class Solution {
    public boolean uniformArray(int[] nums1) {
        int min=Integer.MAX_VALUE;
        for(int i:nums1)min=Math.min(min,i);
        if(min%2!=0)return true;
        for(int i:nums1)if(i%2!=0)return false;
        return true;
    }
}