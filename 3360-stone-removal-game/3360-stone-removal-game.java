class Solution {
    public boolean canAliceWin(int n) {
       int count=0;
       int p=10;
       while(n>=p){
        n=n-p;
        p--;
        count++;
       } 
       if(count%2==0){
        return false;
       }
       return true;
       
    }
}