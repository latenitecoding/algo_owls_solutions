import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FadingWind {

  private static boolean DEBUG = false;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // ========================================================
  // StdIn Helpers
  // ========================================================

  @SuppressWarnings("unused")
  private static int next() throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static int[] nextTuple() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }

  @SuppressWarnings("unused")
  private static void print(int[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println(Arrays.toString(arr));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(int[][] arr) {
    if (!DEBUG) return;
    System.out.println("");
    for (int[] row : arr) System.out.println(Arrays.toString(row));
    System.out.println("");
  }

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    int[] hkvs = nextTuple();
    int h = hkvs[0], k = hkvs[1], v = hkvs[2], s = hkvs[3], dist = 0;
    while (h > 0) {
      v += s;
      v -= Math.max(1, v / 10);
      if (v >= k) {
        h++;
      }
      if (0 < v && v < k) {
        h--;
        if (h == 0) {
          v = 0;
        }
      }
      if (v <= 0) {
        h = 0;
        v = 0;
      }
      dist += v;
      if (s > 0) {
        s--;
      }
    }
    System.out.println(dist);
  }
}
