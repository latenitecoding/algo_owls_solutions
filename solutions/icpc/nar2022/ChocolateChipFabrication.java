import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ChocolateChipFabrication {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    Tuple in = nextTuple();
    int n = in.n, m = in.m;

    boolean[][] grid = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      String line = reader.readLine();
      for (int j = 0; j < m; j++) {
        if (line.charAt(j) == 'X') grid[i][j] = true;
      }
    }

    System.out.println(solve(grid));
  }

  private static int solve(boolean[][] grid) {
    Queue<Tuple> q = new LinkedList<>();

    int n = grid.length, m = grid[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!grid[i][j]) continue;
        if (i == 0
            || i == n - 1
            || j == 0
            || j == m - 1
            || !grid[i - 1][j]
            || !grid[i + 1][j]
            || !grid[i][j - 1]
            || !grid[i][j + 1]) {
          q.add(new Tuple(i, j));
          continue;
        }
      }
    }
    for (Tuple t : q) grid[t.n][t.m] = false;

    print("Q", q);
    print("Cookie", grid);

    int bakes = 0;
    while (!q.isEmpty()) {
      Queue<Tuple> tmp = q;
      q = new LinkedList<>();
      while (!tmp.isEmpty()) {
        Tuple t = tmp.poll();
        if (t.n > 0 && grid[t.n - 1][t.m]) {
          q.add(new Tuple(t.n - 1, t.m));
          grid[t.n - 1][t.m] = false;
        }
        if (t.n < n - 1 && grid[t.n + 1][t.m]) {
          q.add(new Tuple(t.n + 1, t.m));
          grid[t.n + 1][t.m] = false;
        }
        if (t.m > 0 && grid[t.n][t.m - 1]) {
          q.add(new Tuple(t.n, t.m - 1));
          grid[t.n][t.m - 1] = false;
        }
        if (t.m < m - 1 && grid[t.n][t.m + 1]) {
          q.add(new Tuple(t.n, t.m + 1));
          grid[t.n][t.m + 1] = false;
        }
      }
      bakes++;
      print(String.format("Bake %d", bakes), q);
      print("Cookie", grid);
    }

    return bakes;
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
  private static void print(String label, boolean[][] arr) {
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