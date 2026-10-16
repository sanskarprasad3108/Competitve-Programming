import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    static class SegmentTree {
        int n;
        long[] sum;
        long[] sumSq;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sumSq = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void apply(int node, int l, int r, long val) {
            long len = r - l + 1;
            val %= MOD;

            // sumSq' = sumSq + 2 * val * sum + val^2 * len
            long term1 = (2 * val % MOD) * sum[node] % MOD;
            long term2 = (val * val % MOD) * len % MOD;
            sumSq[node] = (sumSq[node] + term1 + term2) % MOD;

            // sum' = sum + val * len
            sum[node] = (sum[node] + val * len) % MOD;

            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) >> 1;
                apply(2 * node, l, mid, lazy[node]);
                apply(2 * node + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void pushUp(int node) {
            sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = (l + r) >> 1;
            if (ql <= mid) {
                update(2 * node, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(2 * node + 1, mid + 1, r, ql, qr, val);
            }
            pushUp(node);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastPos = new HashMap<>();

        long totalAns = 0;

        for (int i = 0; i < n; i++) {
            int prevIdx = lastPos.getOrDefault(nums[i], -1);
            int left = prevIdx + 1;
            int right = i;

            // Range add +1 to all start positions in [left, right]
            tree.update(1, 0, n - 1, left, right, 1);

          
            totalAns = (totalAns + tree.sumSq[1]) % MOD;

            lastPos.put(nums[i], i);
        }

        return (int) totalAns;
    }
}