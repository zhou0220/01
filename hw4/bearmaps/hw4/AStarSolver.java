package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bearmaps.hw4.SolverOutcome;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution = new LinkedList<Vertex>();
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;
    private final double INFINITE = Double.POSITIVE_INFINITY;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        DoubleMapPQ<Vertex> pq = new DoubleMapPQ<>();
        Map<Vertex, Double> distToStart = new HashMap<>();
        Map<Vertex, Double> distToEnd = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();

        Stopwatch sw = new Stopwatch();
        distToStart.put(start, 0.0);
        pq.add(start, distToStart.get(start));

        while (pq.size() != 0) {

            if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToStart.get(end);

                Vertex curr = pq.getSmallest();
                solution.addFirst(curr);
                while (!curr.equals(start)) {
                    solution.addFirst(edgeTo.get(curr));
                    curr = edgeTo.get(curr);
                }

                explorationTime = sw.elapsedTime();
                return;
            }

            List<WeightedEdge<Vertex>> neighborsEdges = input.neighbors(pq.removeSmallest());
            numStatesExplored += 1;

            explorationTime = sw.elapsedTime();
            if (explorationTime > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solution = new LinkedList<>();
                solutionWeight = 0;
                return;
            }

            for (WeightedEdge<Vertex> edge : neighborsEdges) {
                Vertex source = edge.from();
                Vertex target = edge.to();
                double weight = edge.weight();

                if (!distToStart.containsKey(target)) {
                    distToStart.put(target, INFINITE);
                }

                if (!distToEnd.containsKey(target)) {
                    distToEnd.put(target, input.estimatedDistanceToGoal(target, end));
                }

                if (distToStart.get(source) + weight < distToStart.get(target)) {
                    distToStart.put(target, distToStart.get(source) + weight);
                    edgeTo.put(target, source);

                    if (pq.contains(target)) {
                        pq.changePriority(target, distToStart.get(target) + distToEnd.get(target));
                    } else {
                        pq.add(target, distToStart.get(target) + distToEnd.get(target));
                    }
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        solutionWeight = 0;
        explorationTime = sw.elapsedTime();
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return solutionWeight;
    }

    public int numStatesExplored() {
        return numStatesExplored;
    }

    public double explorationTime() {
        return explorationTime;
    }

}
