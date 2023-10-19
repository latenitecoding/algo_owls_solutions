import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FeedingTheCows {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    int T = next();

    for (int t = 0; t < T; t++) {
      Tuple in = nextTuple();
      int N = in.N, K = in.K;

      String line = nextLine();

      char[] out = new char[N];
      Arrays.fill(out, '.');

      int patches = slidingWindowMatch(line, K, 'G', out) + slidingWindowMatch(line, K, 'H', out);

      System.out.println(patches);
      System.out.println(String.valueOf(out));
    }
  }

  private static int slidingWindowMatch(String line, int K, char ch, char[] out) {
    int patches = 0, win = 0, winSize = 2 * K + 1;
    while (win < line.length()) {
      if (line.charAt(win) != ch) win++;
      else {
        int left = win, right = Math.min(line.length(), win + winSize);
        int mid = left + (right - left) / 2;
        if (out[mid] == '.') out[mid] = ch;
        else out[mid - 1] = ch;
        patches++;
        win += winSize;
      }
    }

    return patches;
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int N, K;

    public Tuple(int... args) {
      this.N = args[0];
      this.K = args[1];
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", N, K);
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
