import java.io.*;
import java.util.*;

public class boj_7576 {
    static int M, N;
    static int[][] box;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<int[]> q = new ArrayDeque<>(); //ArrayDeque는 LinkedList보다 빠르다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) q.offer(new int[]{i, j});
            }
        }

        bfs();

        int days = 0;
        for (int[] row : box) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.println(-1);
                    return;
                }
                days = Math.max(days, cell);
            }
        }
        System.out.println(days - 1);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && box[nx][ny] == 0) {
                    box[nx][ny] = box[curr[0]][curr[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
