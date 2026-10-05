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
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum=0;
        if(root==null)return sum;
        inorder(root);
        for(int i=0;i<ans.size();i++){
            if(ans.get(i)>=low && ans.get(i)<=high){
                sum+=ans.get(i);
            }
        }
        return sum;
        
        }
    private void inorder(TreeNode root){
        if(root==null)return;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }
}