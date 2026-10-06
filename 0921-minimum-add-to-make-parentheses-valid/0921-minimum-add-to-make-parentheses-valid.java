class Solution {
    public int minAddToMakeValid(String s) {
        int count=0;
        int count1=0;
        int n=s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                count++;
            }
            else{
                if(count>0){
                    count--;
                }else{
                    count1++;
                }
            }
        }
        return count+count1;
    }
}