package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    int s;
    int t;
    private Maze maze;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int curr = queue.dequeue();
            marked[curr] = true;
            if (curr == t) {
                return;
            }
            for (int w : maze.adj(curr)) {
                if (!marked[w]) {
                    edgeTo[w] = curr;
                    announce();
                    distTo[w] = distTo[curr] + 1;
                    queue.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

