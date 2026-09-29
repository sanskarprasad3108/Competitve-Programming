class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Path length is m + n - 1. A valid bracket sequence must have an even length.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Start must be '(' and end must be ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxOpen = (m + n) / 2;
        memo = new Boolean[m][n][maxOpen + 1];

        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int r, int c, int bal, char[][] grid) {
        bal += (grid[r][c] == '(' ? 1 : -1);

        // Cannot have negative balance
        if (bal < 0) {
            return false;
        }

        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        // Prune: if open brackets exceed remaining steps, bal cannot reach 0
        if (bal > remainingSteps) {
            return false;
        }

        // Destination reached
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (memo[r][c][bal] != null) {
            return memo[r][c][bal];
        }

        boolean found = false;

        // Move Down
        if (r + 1 < m) {
            found = dfs(r + 1, c, bal, grid);
        }

        // Move Right
        if (!found && c + 1 < n) {
            found = dfs(r, c + 1, bal, grid);
        }

        return memo[r][c][bal] = found;
    }
}