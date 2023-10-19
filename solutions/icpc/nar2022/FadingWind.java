import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FadingWind {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    Tuple in = nextTuple();
    int h = in.h, k = in.k, v = in.v, s = in.s, dist = 0;

    while (h > 0) {
      v += s;
      v -= Math.max(1, v / 10);
      if (v >= k) h++;
      if (0 < v && v < k) {
        h--;
        if (h == 0) v = 0;
      }
      if (v <= 0) h = v = 0;
      dist += v;
      if (s > 0) s--;
    }

    System.out.println(dist);
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int h, k, v, s;

    public Tuple(int... args) {
      this.h = args[0];
      this.k = args[1];
      this.v = args[2];
      this.s = args[3];
    }

    public String toString() {
      return String.format("Tuple(%d, %d, %d, %d)", h, k, v, s);
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
