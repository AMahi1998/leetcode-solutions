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
    private static final long MOD = 1_000_000_007L;
    private long totalSum = 0;
    private long best = 0;

    public int maxProduct(TreeNode root) {
        totalSum = treeSum(root);
        subtreeSumAndBest(root);
        return (int) (best % MOD);
    }

    private long treeSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + treeSum(node.left) + treeSum(node.right);
    }

    private long subtreeSumAndBest(TreeNode node) {
        if (node == null) return 0;

        long left = subtreeSumAndBest(node.left);
        long right = subtreeSumAndBest(node.right);

        long s = node.val + left + right;
        long product = s * (totalSum - s);
        if (product > best) best = product;

        return s;
    }
}