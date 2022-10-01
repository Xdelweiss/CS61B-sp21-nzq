package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE

        AList Ns = new AList<Integer>();
        AList times = new AList<Double>();
        AList opCounts = new AList<Integer>();

        for (int n = 1000; n <= 128000; n *= 2) {
            int opCount = 0;
            SLList timeSLList = new SLList();
            for (int i = 0; i < n; i++) {
                timeSLList.addLast(i);
            }

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < 10000; i++) {
                timeSLList.getLast();
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
