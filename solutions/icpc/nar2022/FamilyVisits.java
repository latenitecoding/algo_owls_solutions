import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FamilyVisits {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    Tuple in = nextTuple();
    int n = in.n, d = in.d;

    Day[] days = new Day[n];
    int[] visits = new int[d];

    for (int i = 0; i < n; i++) days[i] = Day.From(nextTuple());
    for (int j = 0; j < d; j++) visits[j] = next();

    System.out.println(solve(days, visits));
  }

  private static int solve(Day[] days, int[] visits) {
    // Java priority queues are always min heaps
    // we want days with higher cleaning to come earlier (i.e., return negative value)
    PriorityQueue<Day> heap = new PriorityQueue<>((d1, d2) -> d2.clean - d1.clean);

    int cap = 0, prevVisit = -1, cleans = 0;
    for (int visit : visits) {
      for (int di = visit - 1; di > prevVisit; di--) {
        Day day = days[di];

        if (day.clean > 0) heap.add(day);
        if (day.mess == 0) continue;

        while (cap < day.mess && !heap.isEmpty()) {
          cap += heap.poll().clean;
          cleans++;
        }

        cap -= day.mess;
        if (cap < 0) return -1;
      }

      heap.clear();
      cap = 0;
      prevVisit = visit - 1;
    }

    return cleans;
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int n, d;

    public Tuple(int... args) {
      this.n = args[0];
      this.d = args[1];
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", n, d);
    }
  }

  private static class Day {
    int mess, clean;

    static Day From(Tuple t) {
      return new Day(t.n, t.d);
    }

    public Day(int mess, int clean) {
      this.clean = clean;
      this.mess = mess;
    }

    public String toString() {
      return String.format("Day(%d, %d)", mess, clean);
    }
  }

  @SuppressWarnings("unused")
  private static int next() throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static int[] nextArray() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }

  @SuppressWarnings("unused")
  private static Tuple nextTuple() throws IOException {
    return new Tuple(nextArray());
  }

  @SuppressWarnings("unused")
  private static void print(String label, int[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(Arrays.toString(arr));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(String label, int[][] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (var row : arr) System.out.println(Arrays.toString(row));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(String label, Object o) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(o);
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(String label, Object[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (var o : arr) System.out.println(o);
    System.out.println("");
  }
}
