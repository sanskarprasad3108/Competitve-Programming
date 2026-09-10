class Solution {
    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return count;
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {sum of values, count of nodes}
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = left[0] + right[0] + node.val;
        int currentNodes = left[1] + right[1] + 1;

        if (currentSum / currentNodes == node.val) {
            count++;
        }

        return new int[]{currentSum, currentNodes};
    }
}