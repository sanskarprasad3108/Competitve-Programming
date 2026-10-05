/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer>ans=new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if(root==null)return new int[0];
       inorder(root);
       int maxfreq=0;
       int currentfreq=0;
      for(int i=0;i<ans.size();i++){
        if(i>0 && ans.get(i).equals(ans.get(i-1))){
            currentfreq++;
        }else{
            currentfreq=1;
        }
        maxfreq=Math.max(maxfreq,currentfreq);
      }
List<Integer>modes=new ArrayList<>();
currentfreq=0;
for(int i=0;i<ans.size();i++){
    if(i>0 && ans.get(i).equals(ans.get(i-1))){
        currentfreq++;
    }else{
        currentfreq=1;
    }
    if(currentfreq==maxfreq){
        modes.add(ans.get(i));
    }
}
int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;

    }
    private void inorder(TreeNode root){
        if(root==null)return ;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }
}