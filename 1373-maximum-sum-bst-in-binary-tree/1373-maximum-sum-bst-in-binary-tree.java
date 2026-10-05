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
    private int maxSum=0;
    private static class info{
        boolean isBST;
        int min;
        int max;
        int sum;
        info(boolean isBST,int min,int max,int sum){
            this.isBST=isBST;
            this.min=min;
            this.max=max;
            this.sum=sum;
        }
    }
    public int maxSumBST(TreeNode root) {
        maxSum=0;
        postorder(root);
        return maxSum;
    }
    private info postorder(TreeNode root){
        if(root==null){
            return new info(true,Integer.MAX_VALUE,Integer.MIN_VALUE,0);
        }
        info left=postorder(root.left);
        info right=postorder(root.right);

        if(left.isBST && right.isBST && root.val>left.max && root.val<right.min){
            int current=root.val + left.sum + right.sum;
            maxSum=Math.max(maxSum,current);
            int cmin=Math.min(root.val,left.min);
            int cmax=Math.max(root.val,right.max);
            return new info(true,cmin,cmax,current);
    }
    return new info(false,0,0,0);
}
}