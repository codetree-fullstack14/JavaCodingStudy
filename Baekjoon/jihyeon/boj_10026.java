import java.io.*;
import java.util.*;

public class boj_10026 {
    static int N; //맵의 크기
    static char[][] map;  //색깔을 저장할 2차원 배열
    static boolean[][] visited;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 일반 시야 기준
        visited = new boolean[N][N];
        int normal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map[i][j]);
                    normal++;
                }
            }
        }

        // 적록색약 시야 기준: R과 G를 같은 색으로 처리
        visited = new boolean[N][N];
        int colorBlind = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfsColorBlind(i, j, map[i][j]);
                    colorBlind++;
                }
            }
        }

        System.out.println(normal + " " + colorBlind);
    }

    static void dfs(int x, int y, char color) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (inBounds(nx, ny) && !visited[nx][ny] && map[nx][ny] == color) {
                dfs(nx, ny, color);
            }
        }
    }

    static void dfsColorBlind(int x, int y, char color) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (inBounds(nx, ny) && !visited[nx][ny] && isSameColor(color, map[nx][ny])) {
                dfsColorBlind(nx, ny, color);
            }
        }
    }

    static boolean isSameColor(char a, char b) {
        if (a == b) return true;
        if ((a == 'R' || a == 'G') && (b == 'R' || b == 'G')) return true;
        return false;
    }

    static boolean inBounds(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
