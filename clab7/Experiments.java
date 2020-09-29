import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by hug.
 * Work Finished
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> bst = new BST<Integer>();
        Random r = new Random();
        List<Double> yValues = new ArrayList<Double>();
        List<Double> y2Values = new ArrayList<Double>();
        List<Integer> xValues = new ArrayList<Integer>();

        for (int i = 0; i <= 5000; i++) {
            int x = r.nextInt(5000);
            if (bst.contains(x)) {
                continue;
            }
            bst.add(x);
            double thisY = bst.averageDepth();
            xValues.add(x);
            yValues.add(thisY);
            y2Values.add(ExperimentHelper.optimalAverageDepth(x));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("Number of items").yAxisTitle("Average Depth").build();
        chart.addSeries("Random BST", xValues, yValues);
        chart.addSeries("Optimal BST", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
