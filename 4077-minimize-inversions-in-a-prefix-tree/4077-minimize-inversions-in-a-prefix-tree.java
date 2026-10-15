import java.util.*;

class Solution {
    static class TrieNode {
        int wordId = -1;
        TrieNode[] children = new TrieNode[10];
        int subtreeSize = 0;
        int[] childSizes = new int[10];
        int[][] cost = new int[10][10];
    }

    public int minInversions(String[] words, int[] target) {
        int n = words.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[target[i]] = i;
        }

        TrieNode root = new TrieNode();

        // Step 1: Build the Trie
        for (int i = 0; i < n; i++) {
            TrieNode curr = root;
            for (char ch : words[i].toCharArray()) {
                int c = ch - 'a';
                if (curr.children[c] == null) {
                    curr.children[c] = new TrieNode();
                }
                curr = curr.children[c];
            }
            curr.wordId = i;
        }

        // Step 2: Process words in increasing order of pos (target order)
        // This counts pair inversions and fixed ancestor-descendant inversions
        long fixedInversions = 0;

        for (int p = 0; p < n; p++) {
            int wordIdx = target[p];
            String w = words[wordIdx];
            TrieNode curr = root;

            for (char ch : w.toCharArray()) {
                int c = ch - 'a';

                // Fixed inversion: if curr itself is a word visited earlier (pos[curr.wordId] < p)
                // it is placed before curr's descendants in preorder traversal, which is correct
                // (no inversion). If curr was visited later, pos[curr] > p, an inversion occurs.
                // But by processing in increasing order of target, if curr.wordId != -1,
                // pos[curr.wordId] has already been seen if it was smaller, or will be seen later.
                // descendant w has pos[w] = p. If curr has pos[curr] > p, that's an inversion.
                if (curr.wordId != -1 && pos[curr.wordId] > p) {
                    fixedInversions++;
                }

                // For every other child d != c of curr:
                // The nodes in child d's subtree processed so far have target position < p.
                // If subtree c is ordered before subtree d, w (pos p) is before d's nodes (pos < p),
                // creating curr.childSizes[d] inversions.
                for (int d = 0; d < 10; d++) {
                    if (d != c && curr.children[d] != null) {
                        curr.cost[c][d] += curr.childSizes[d];
                    }
                }

                curr.childSizes[c]++;
                curr.subtreeSize++;
                curr = curr.children[c];
            }

            // Also check the terminal node itself for fixed inversions with ancestors handled above
        }

        // Step 3: Solve subset DP for each node to find minimum child-order inversions
        long totalInversions = fixedInversions;
        Queue<TrieNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TrieNode curr = queue.poll();

            List<Integer> validChildren = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (curr.children[i] != null) {
                    validChildren.add(i);
                    queue.add(curr.children[i]);
                }
            }

            int k = validChildren.size();
            if (k > 1) {
                int numStates = 1 << k;
                int[] dp = new int[numStates];
                Arrays.fill(dp, Integer.MAX_VALUE);
                dp[0] = 0;

                for (int mask = 0; mask < numStates; mask++) {
                    if (dp[mask] == Integer.MAX_VALUE) continue;

                    for (int i = 0; i < k; i++) {
                        if ((mask & (1 << i)) == 0) {
                            int nextMask = mask | (1 << i);
                            int childI = validChildren.get(i);
                            int addCost = 0;

                            // childJ was placed before childI
                            for (int j = 0; j < k; j++) {
                                if ((mask & (1 << j)) != 0) {
                                    int childJ = validChildren.get(j);
                                    addCost += curr.cost[childJ][childI];
                                }
                            }

                            dp[nextMask] = Math.min(dp[nextMask], dp[mask] + addCost);
                        }
                    }
                }

                totalInversions += dp[numStates - 1];
            }
        }

        return (int) totalInversions;
    }
}