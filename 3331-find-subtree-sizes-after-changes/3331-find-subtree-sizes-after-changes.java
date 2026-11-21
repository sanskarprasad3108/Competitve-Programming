import java.util.*;

class Solution {
    private int[] newParent;
    private int[] subtreeSize;
    private List<Integer>[] adj;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Step 1: Determine new parents using DFS + backtracking
        dfsAssignParents(0, parent, s, origAdj, lastSeen);

        // Step 2: Build the final tree and compute subtree sizes
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        subtreeSize = new int[n];
        dfsSubtreeSize(0);
        return subtreeSize;
    }

    private void dfsAssignParents(int u, int[] parent, String s, List<Integer>[] origAdj, int[] lastSeen) {
        int charIdx = s.charAt(u) - 'a';
        if (u > 0) {
            newParent[u] = (lastSeen[charIdx] != -1) ? lastSeen[charIdx] : parent[u];
        }

        int prevNode = lastSeen[charIdx];
        lastSeen[charIdx] = u;

        for (int v : origAdj[u]) {
            dfsAssignParents(v, parent, s, origAdj, lastSeen);
        }

        // Restore ancestor state when backtracking
        lastSeen[charIdx] = prevNode;
    }

    private int dfsSubtreeSize(int u) {
        int size = 1;
        for (int v : adj[u]) {
            size += dfsSubtreeSize(v);
        }
        subtreeSize[u] = size;
        return size;
    }
}