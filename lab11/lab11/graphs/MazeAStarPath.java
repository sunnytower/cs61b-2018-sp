package lab11.graphs;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private class Node implements Comparable<Node> {
        private int v;
        private int priority;

        public Node(int v) {
            this.v = v;
            this.priority = distTo[v] + h(v);
        }
        @Override
        public int compareTo(Node other)  {
            return this.priority - other.priority;
        }
    }
    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        return Math.abs(sourceX - maze.toX(t)) + Math.abs(sourceY - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node curr = new Node(s);
        queue.add(curr);
        while (!queue.isEmpty()) {
            int cur = queue.poll().v;
            marked[cur] = true;
            if (cur == t) {
                return;
            }
            for (int w : maze.adj(cur)) {
                if (!marked[w]) {
                    edgeTo[w] = cur;
                    announce();
                    distTo[w] = distTo[cur] + 1;
                    queue.add(new Node(w));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

