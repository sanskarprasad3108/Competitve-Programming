class Solution {
    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            this.prod = 1;
            this.count = new int[k];
        }
    }

    private Node[] tree;
    private int n;
    private int k;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (int) ((1L * left.prod * right.prod) % k);

        // Copy prefix counts from left child
        for (int i = 0; i < k; i++) {
            res.count[i] += left.count[i];
        }

        // Add counts from right child shifted by left.prod
        for (int i = 0; i < k; i++) {
            int newRem = (int) ((1L * left.prod * i) % k);
            res.count[newRem] += right.count[i];
        }

        return res;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            tree[node] = new Node(k);
            int val = nums[l] % k;
            tree[node].prod = val;
            tree[node].count[val] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % k;
            tree[node].prod = rem;
            java.util.Arrays.fill(tree[node].count, 0);
            tree[node].count[rem] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Helper class to accumulate running product across disjoint segments
    static class QueryState {
        int currProd = 1;
        int targetX;
        int totalWays = 0;
        int k;

        QueryState(int targetX, int k) {
            this.targetX = targetX;
            this.k = k;
        }

        void apply(Node node) {
            for (int r = 0; r < k; r++) {
                if (node.count[r] > 0) {
                    if ((1L * currProd * r) % k == targetX) {
                        totalWays += node.count[r];
                    }
                }
            }
            currProd = (int) ((1L * currProd * node.prod) % k);
        }
    }

    private void queryRange(int node, int l, int r, int ql, int qr, QueryState state) {
        if (ql <= l && r <= qr) {
            state.apply(tree[node]);
            return;
        }
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            queryRange(2 * node, l, mid, ql, qr, state);
        }
        if (qr > mid) {
            queryRange(2 * node + 1, mid + 1, r, ql, qr, state);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistent update
            update(1, 0, n - 1, idx, val);

            // 2. Query suffix [start, n - 1]
            QueryState state = new QueryState(x, k);
            queryRange(1, 0, n - 1, start, n - 1, state);

            ans[i] = state.totalWays;
        }

        return ans;
    }
}