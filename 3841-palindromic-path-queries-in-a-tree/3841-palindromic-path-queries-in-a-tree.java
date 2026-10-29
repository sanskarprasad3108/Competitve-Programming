import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth;
    private int[] tin, tout;
    private int timer = 0;
    private int[] charMask;

    // Binary Indexed Tree for Range XOR update / Point query
    private static class FenwickTree {
        int n;
        int[] tree;

        FenwickTree(int n) {
            this.n = n;
            this.tree = new int[n + 2];
        }

        void updatePoint(int i, int val) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= val;
            }
        }

        // XOR in range [l, r]
        void updateRange(int l, int r, int val) {
            updatePoint(l, val);
            updatePoint(r + 1, val);
        }

        // Query point i
        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int maxLog = 1;
        while ((1 << maxLog) <= n) maxLog++;

        up = new int[maxLog][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
        }

        // Initial DFS to set up LCA and Euler tour timestamps
        dfs(0, 0, 0, maxLog);

        FenwickTree bit = new FenwickTree(n);

        // Populate initial prefix XOR values into the Fenwick tree
        for (int i = 0; i < n; i++) {
            bit.updateRange(tin[i], tout[i], charMask[i]);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                int newMask = 1 << (newChar - 'a');
                int delta = charMask[u] ^ newMask;

                if (delta != 0) {
                    charMask[u] = newMask;
                    bit.updateRange(tin[u], tout[u], delta);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v, maxLog);

                int prefU = bit.query(tin[u]);
                int prefV = bit.query(tin[v]);
                int pathXor = prefU ^ prefV ^ charMask[lca];

                // Check if pathXor has at most 1 bit set
                ans.add((pathXor & (pathXor - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d, int maxLog) {
        tin[u] = ++timer;
        up[0][u] = p;
        depth[u] = d;

        for (int k = 1; k < maxLog; k++) {
            up[k][u] = up[k - 1][up[k - 1][u]];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, maxLog);
            }
        }
        tout[u] = timer;
    }

    private int getLCA(int u, int v, int maxLog) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        for (int k = maxLog - 1; k >= 0; k--) {
            if (depth[u] - (1 << k) >= depth[v]) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = maxLog - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }
        return up[0][u];
    }
}