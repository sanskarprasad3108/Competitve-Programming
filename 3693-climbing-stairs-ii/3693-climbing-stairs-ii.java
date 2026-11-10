class Solution {
    public int climbStairs(int n, int[] costs) {
        long dp[]=new long[n+1];
        Arrays.fill(dp,-1);
        return (int)solve(n,costs,dp);
    }
    private long solve(int j,int[]costs,long []dp){
        if(j==0){
            return 0;
        }
        if(dp[j]!=-1){
            return dp[j];
        }
        long mincost=Long.MAX_VALUE;
        for(int i=Math.max(0,j-3);i<j;i++){
            long jump=solve(i,costs,dp)+costs[j-1]+(long)(j-i)*(j-i);
            mincost=Math.min(mincost,jump);
        }
        return dp[j]=mincost;
    }
}