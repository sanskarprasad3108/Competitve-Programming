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
    public TreeNode increasingBST(TreeNode root) {
        if(root==null)return null;
      inorder(root);  
      
      TreeNode node=new TreeNode(0);
      TreeNode curr=node;
      for(int i=0;i<ans.size();i++){
        curr.right=new TreeNode(ans.get(i));
        curr=curr.right;
      }
      return node.right;
    }
    private void inorder(TreeNode root){
        if(root==null){
            return;
        }
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }
}