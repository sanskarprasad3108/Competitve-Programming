class Solution {
    public long minimumTime(int[] time, int totalTrips) {
     int minTime = Integer.MAX_VALUE;

        for (int t : time) {
            minTime = Math.min(minTime, t);
        }

        long left = 1;
        long right = (long) minTime * totalTrips;
     while(left<=right){
        long mid=left+(right-left)/2;
        long trip=0;
        for(int tm:time){
            trip+=mid/tm;
            if(trip>=totalTrips)break;
        }
        if(trip>=totalTrips){
            right=mid-1;
        }else{
            left=mid+1;
        }
     }
     return left;
    }
}