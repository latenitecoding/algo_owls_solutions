import java.io.*;
import java.util.*;
import java.util.stream.*;

public class CowCollege {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    long N = next();
    long[] c = nextArray();

    Arrays.sort(c);

    long maxPay = 0, bestTuition = 0;
    for (int i = 0; i < N; i++) {
      long pay = c[i] * (N - i);
      if (pay > maxPay) {
        maxPay = pay;
        bestTuition = c[i];
      }
    }

    System.out.printf("%d %d\n", maxPay, bestTuition);
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int n, m;

    public Tuple(int... args) {
      this.n = args[0];
      this.m = args[1];
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", n, m);
    }
  }

  @SuppressWarnings("unused")
  private static long next() throws IOException {
    return Long.parseLong(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static long[] nextArray() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
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
