import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FamilyVisits {

  private static final boolean DEBUG = false;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // ========================================================
  // Helpers
  // ========================================================

  private static class Tuple {
    int n, d;

    public Tuple(int n, int d) {
      this.n = n;
      this.d = d;
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", n, d);
    }
  }

  private static class Day {
    int mess, clean;

    static Day From(Tuple tuple) {
      return new Day(tuple.n, tuple.d);
    }

    public Day(int mess, int clean) {
      this.clean = clean;
      this.mess = mess;
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", mess, clean);
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
    String[] line = reader.readLine().split(" ");
    return new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
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
    for (int[] row : arr) System.out.println(Arrays.toString(row));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static <E> void print(String label, E e) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(e);
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static <E> void print(String label, E[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (E e : arr) System.out.println(e);
    System.out.println("");
  }

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
}
