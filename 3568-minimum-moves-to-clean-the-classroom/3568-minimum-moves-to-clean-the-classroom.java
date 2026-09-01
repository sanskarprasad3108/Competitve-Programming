
class Solution {
    public int minimumMoves(String[] classroom, int energy) {
        return grind(classroom, energy);
    }

    public int minMoves(String[] classroom, int energy) {
        return grind(classroom, energy);
    }

    private int grind(String[] classroom, int energy) {
        int rows = classroom.length, cols = classroom[0].length(), cells = rows * cols;
        char[] tile = new char[cells];
        int[] gift = new int[cells];
        int[] links = new int[cells << 2];
        java.util.Arrays.fill(links, -1);

        int start = -1;
        int bits = 0;

        for (int r = 0; r < rows; r++) {
            char[] line = classroom[r].toCharArray();
            for (int c = 0; c < cols; c++) {
                int p = r * cols + c;
                char ch = line[c];
                tile[p] = ch;
                if (ch == 'S') start = p;
                else if (ch == 'L') gift[p] = 1 << bits++;
                int z = p << 2;
                links[z] = r > 0 ? p - cols : -1;
                links[z + 1] = r + 1 < rows ? p + cols : -1;
                links[z + 2] = c > 0 ? p - 1 : -1;
                links[z + 3] = c + 1 < cols ? p + 1 : -1;
            }
        }

        if (bits == 0) return 0;

        int maskCap = 1 << bits;
        int target = maskCap - 1;
        int total = cells * maskCap * (energy + 1);
        int[] q = new int[total];
        long[] seen = new long[1 << 19];

        int seed = (start << 16) | (gift[start] << 6) | energy;
        q[0] = seed;
        seen[seed >>> 6] |= 1L << (seed & 63);

        int head = 0, tail = 1, dist = 0;

        while (head < tail) {
            int layerEnd = tail;
            while (head < layerEnd) {
                int cur = q[head++];
                int mask = (cur >>> 6) & target;
                if (mask == target) return dist;
                int rem = cur & 63;
                if (rem == 0) continue;
                int pos = cur >>> 16;
                int base = pos << 2;

                for (int i = 0; i < 4; i++) {
                    int np = links[base + i];
                    if (np < 0) continue;
                    char ch = tile[np];
                    if (ch == 'X') continue;
                    int nm = mask | gift[np];
                    int nr = ch == 'R' ? energy : rem - 1;
                    int nxt = (np << 16) | (nm << 6) | nr;
                    int slot = nxt >>> 6;
                    long bit = 1L << (nxt & 63);
                    if ((seen[slot] & bit) != 0) continue;
                    seen[slot] |= bit;
                    q[tail++] = nxt;
                }
            }
            dist++;
        }

        return -1;
    }
}