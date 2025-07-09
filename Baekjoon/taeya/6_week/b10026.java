import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b10026 {
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static char[][] mapRG;
    static boolean[][] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        mapRG = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
                if (c == 'G') mapRG[i][j] = 'R';
                else mapRG[i][j] = c;
            }
            //최적화 코드
            //char[] line = br.readLine().toCharArray();
            //for (int j = 0; j < N; j++) {
            //    map[i][j] = line[j];
            //    mapRG[i][j] = (line[j] == 'G') ? 'R' : line[j];
            //}
        }

        // 맵 탐색
        // 일반인
        int normal=0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map[i][j], map);
                    normal++;
                }
            }
        }
        // 적록색맹
        int rg=0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, mapRG[i][j], mapRG);
                    rg++;
                }
            }
        }
        System.out.println(normal + " " + rg);
    }
    // DFS 진행
    public static void dfs(int y, int x, char color, char[][] map) {
        // 맵을 넘는지 확인
        if (x<0 || y<0 || x>=N || y>=N) {
            return;
        }
        if(visited[y][x] || map[y][x] != color) return;
        visited[y][x] = true;
        // 맵 탐색 완료
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            dfs(ny, nx, color, map);
        }
    }
}

// 문제 풀이 - 1시간
// 맵 받고 탐색을 통해서 구역 분할
// dfs, bfs 문제 + 구역 탐색
// 전체 맵을 탐색해야함.

// 내 구현 방법
// 1. 입력값을 int형 양수값으로 받는다. - 계산하기 편하게
// 2. dfs 를 진행. 방문 여부는 현재 맵을 -1을 곱하여 넣어두기 - 공간효울성을 높일 수 있다.
// 3. dfs가 호출 될 때마다 일반 값 증가
// 4. 음수를 기준으로 -3이상이나 미만이냐를 통해 맵 재비교
// 5. dfs가 호출 될 때마다 적록 값 증가
// 6. 출력

// 입력 맵을 가지고 값을 조정하면서(양수, 음수) 두 번 탐색하면 공간효율성도 높이고 좋을 듯.
// 하지만 안정성을 따지면 입력 맵을 변경하는건 좋은 선택지는 아님.

// 구역 탐색 - 기존 dfs 와 달리 color를 매개변수로 넣어서 해당 색일 때만 맵을 탐색하도록 조건 추가

// 개선점
// 1. 입력값을 char형으로 받는다. = 코드 명확성을 높일 수 있다.
// 2. 입력값을 받을 때 적록 값과 일반 값을 동시에 받는다.
// 3. dfs가 호출 될 때마다 count 한다.
// 4. 방문 여부를 판단하는 맵을 만들고, 처음 입력받았던 색과 현재 탐색 중인 색을 비교해서 다르면 탈출

// 코드 오류
// 맵을 넘는지 확인하는 조건 코드
// if (map[y][x] < 0 || x<0 || y<0 || x>=N || y>=N)
// 이 조건식에서 map[y][x] < 0 체크가 x와 y의 경계 검사보다 먼저 와서, **ArrayIndexOutOfBoundsException**이 발생할 수 있음.
// if (x<0 || y<0 || x>=N || y>=N || map[y][x] < 0)
// x와 y가 먼저 값을 발견하기전에 map을 먼저 처리하기에 에러 발생







//🧩 Flood Fill 알고리즘과의 연결
//이 문제는 일종의 Flood Fill 알고리즘입니다.
//
//Flood Fill은 다음과 같은 데서 쓰입니다:
//그래픽 툴의 채우기 기능 (ex. 포토샵에서 'Paint Bucket')
//지형 분할, 섬의 개수 세기 (ex. 백준 4963 - 섬의 개수)
//구역 감지 문제 (색상 영역, 도시 분할, 전염 범위 등)
