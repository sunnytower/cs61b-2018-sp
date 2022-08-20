package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean cycleFound = false;
    private int s;
    private int[] pathTo;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        pathTo = new int[maze.V()];
        Random rand = new Random();
        int startX = rand.nextInt(1, maze.N());
        int startY = rand.nextInt(1, maze.N());
        s = maze.xyTo1D(startX, startY);
        pathTo[s] = s;
        dfs(s);
        announce();
    }

    private void findPath(int v) {
        int curr = v;
        while (curr != s) {
            edgeTo[curr] = pathTo[curr];
            curr = pathTo[curr];
        }
    }
    private void dfs(int v) {
        marked[v] = true;
        for (int w : maze.adj(v)) {
            if (cycleFound) {
                return;
            }
            if (!marked[w]) {
                pathTo[w] = v;
                dfs(w);
            } else if (w != pathTo[v]) {
                //find a cycle.
                pathTo[w] = v;
                findPath(v);
                cycleFound = true;
                return;
            }
        }
    }
}

