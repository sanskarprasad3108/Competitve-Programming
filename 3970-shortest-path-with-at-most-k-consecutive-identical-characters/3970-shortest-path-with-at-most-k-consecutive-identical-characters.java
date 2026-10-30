import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        int u;
        int count;
        int dist;

        State(int u, int count, int dist) {
            this.u = u;
            this.count = count;
            this.dist = dist;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of (v, weight)
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // dist[u][count] stores the shortest distance to reach node u with `count` consecutive identical characters
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        // Starting at node 0 with consecutive count = 1
        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.u;
            int count = curr.count;
            int d = curr.dist;

            // If we reached the target, return the minimum distance
            if (u == n - 1) {
                return d;
            }

            // Stale entry check
            if (d > dist[u][count]) {
                continue;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];

                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? count + 1 : 1;

                if (nextCount <= k && d + weight < dist[v][nextCount]) {
                    dist[v][nextCount] = d + weight;
                    pq.offer(new State(v, nextCount, dist[v][nextCount]));
                }
            }
        }

        return -1;
    }
}