/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        ListNode temp=head;
        int count=0;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return buildBst(head,0,count-1);

    }
    private TreeNode buildBst(ListNode head,int left,int right){
        if(left>right)return null;
        ListNode temp=head;
        int mid=left+(right-left)/2;
        for(int i=0;i<mid;i++){
            temp=temp.next;
        }
        TreeNode root=new TreeNode(temp.val);
        root.left=buildBst(head,left,mid-1);
        root.right=buildBst(head,mid+1,right);
        return root;
    }
    
}