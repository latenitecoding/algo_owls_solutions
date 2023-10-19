import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FadingWind {

  private static final boolean DEBUG = false;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // ========================================================
  // Helpers
  // ========================================================

  private static class Tuple {
    int h, k, v, s;

    public Tuple(int h, int k, int v, int s) {
      this.h = h;
      this.k = k;
      this.v = v;
      this.s = s;
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
    String[] line = reader.readLine().split(" ");
    return new Tuple(
        Integer.parseInt(line[0]),
        Integer.parseInt(line[1]),
        Integer.parseInt(line[2]),
        Integer.parseInt(line[3]));
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
}
