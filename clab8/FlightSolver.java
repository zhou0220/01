import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private int maxNumPeople = 0;

    public FlightSolver(ArrayList<Flight> flights) {

        PriorityQueue<Flight> startQueue = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.startTime(), o2.startTime());
            }
        });
        PriorityQueue<Flight> endQueue = new PriorityQueue<>(new Comparator<Flight>() {
            @Override
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.endTime(), o2.endTime());
            }
        });
        startQueue.addAll(flights);
        endQueue.addAll(flights);

        int result = 0;
        while (startQueue.size() != 0) {
            if (startQueue.peek().startTime() <= endQueue.peek().endTime()) {
                result += startQueue.poll().passengers();
                maxNumPeople = result > maxNumPeople ? result : maxNumPeople;
            } else {
                result -= endQueue.poll().passengers();
            }
        }
    }

    public int solve() {
        /* FIX ME */
        return maxNumPeople;
    }

}
