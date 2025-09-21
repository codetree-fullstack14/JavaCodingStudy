import java.util.*;

public class programmers {
    static int n, m;
    static int[] oil;

    public static void main(String[] args) {
        int[][] land = {
                {1, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };

        programmers p = new programmers();
        int result = p.solution(land);
        System.out.println("최대 석유량: " + result);
    }

    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        oil = new int[m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(land, visited, i, j);
                }
            }
        }

        answer = Arrays.stream(oil).max().getAsInt();
        return answer;
    }

    public void bfs(int[][] land, boolean[][] visited, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int count = 1;
        Set<Integer> set = new HashSet<>();
        set.add(y);

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cx = current[0], cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && land[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    count++;
                    set.add(ny);
                }
            }
        }

        for (int col : set) {
            oil[col] += count;
        }
    }
}
