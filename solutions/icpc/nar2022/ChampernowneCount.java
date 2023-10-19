import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ChampernowneCount {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    Tuple in = nextTuple();
    long n = in.n, k = in.k;

    long count = 0, word = 0, coef = 10;
    for (int i = 1; i <= n; i++) {
      if (i == coef) coef *= 10;
      word = (word * coef + i) % k;
      if (word == 0) count++;
    }

    System.out.println(count);
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    long n, k;

    public Tuple(long... args) {
      this.n = args[0];
      this.k = args[1];
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", n, k);
    }
  }

  @SuppressWarnings("unused")
  private static int next() throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static long[] nextArray() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
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