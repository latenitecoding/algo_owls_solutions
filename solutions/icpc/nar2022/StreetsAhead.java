import java.io.*;
import java.util.*;
import java.util.stream.*;

public class StreetsAhead {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    Tuple in = nextTuple();
    int n = in.n, q = in.q;

    Map<String, Integer> streets = new HashMap<String, Integer>();
    for (int i = 1; i <= n; i++) {
      streets.put(nextLine(), i);
    }

    for (int j = 1; j <= q; j++) {
      String[] query = nextLine().split(" ");
      String s1 = query[0], s2 = query[1];
      int dist = Math.abs(streets.get(s1) - streets.get(s2)) - 1;
      System.out.println(dist);
    }
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int n, q;

    public Tuple(int... args) {
      this.n = args[0];
      this.q = args[1];
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", n, q);
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
  private static String nextLine() throws IOException {
    return reader.readLine();
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
    for (int[] row : arr) System.out.println(Arrays.toString(row));
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
    for (Object o : arr) System.out.println(o);
    System.out.println("");
  }
}
