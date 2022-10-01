package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) { timeAListConstruction(); }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE

        AList Ns = new AList<Integer>();
        AList times = new AList<Double>();
        AList opCounts = new AList<Integer>();

        Stopwatch sw = new Stopwatch();

        for (int n = 1000; n <= 128000; n *= 2) {
            int opCount = 0;
            AList timeAList = new AList<Integer>();
            for (int i = 0; i < n; i++) {
                timeAList.addLast(i);
                opCount++;
            }
            double time = sw.elapsedTime();
            Ns.addLast(n);
            times.addLast(time);
            opCounts.addLast(opCount);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
