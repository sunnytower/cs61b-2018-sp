package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        this.size = tiles[0].length;
        this.tiles = new int[size][];
        for (int i = 0; i < size; ++i) {
            this.tiles[i] = tiles[i].clone();
        }
    }

    private boolean isInBound(int index) {
        return index >= 0 && index <= size - 1;
    }

    public int tileAt(int i, int j) {
        if (isInBound(i) && isInBound(j)) {
            return tiles[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public int manhattan() {
        int res = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                int val = tileAt(i, j);
                int realJ = (val - 1) % size;
                int realI = (val - 1 - realJ) / size;
                res += Math.abs(i - realI);
                res += Math.abs(j - realJ);
            }
        }
        return res;
    }

    public int hamming() {
        int res = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                int acc = size * i + j + 1;
                if (tileAt(i, j) != acc) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y == this) {
            return true;
        }
        if (y instanceof Board) {
            Board other = (Board) y;
            if (other.size != this.size) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (tiles[i][j] != other.tiles[i][j]) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
