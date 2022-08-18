package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        WorldState ws;
        int moves;
        SearchNode prev;
        int priority;

        public SearchNode(WorldState initial) {
            ws = initial;
            moves = 0;
            prev = null;
            priority = moves + this.ws.estimatedDistanceToGoal();
        }

        public SearchNode(WorldState ws, int moves, SearchNode prev) {
            this.ws = ws;
            this.moves = moves;
            this.prev = prev;
            priority = moves + this.ws.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode other) {
            return this.priority - other.priority;
        }
    }

    MinPQ<SearchNode> pq;
    int moves;
    private List<WorldState> solution;

    public Solver(WorldState initial) {
        //initialize.
        pq = new MinPQ<>();
        pq.insert(new SearchNode(initial));
        solution = new ArrayList<>();
        solverHelper();
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

    private void solverHelper() {
        while (!pq.isEmpty()) {
            SearchNode curr = pq.delMin();
            solution.add(curr.ws);
            if (curr.ws.isGoal()) {
                moves = curr.moves;
                return;
            }
            for (WorldState ws : curr.ws.neighbors()) {
                //optimization.
                if (curr.prev == null || !ws.equals(curr.prev.ws)) {
                    pq.insert(new SearchNode(ws, curr.moves + 1, curr));
                }
            }
        }
    }
}
