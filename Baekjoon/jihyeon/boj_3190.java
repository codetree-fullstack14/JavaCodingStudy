import java.io.*;
import java.util.*;

//Deque를 쓰는 이유: 뱀의 머리와 꼬리 양쪽을 빠르게 조작해야하기때문
public class boj_3190 {
    static int N, K, L;
    static int[][] board;
    static Deque<int[]> snake = new LinkedList<>();
    static Map<Integer, Character> directionChanges = new HashMap<>();


    //시계방향으로 회전이 중요하므로 {-1,1,0,0}으로 써주지않음.
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            board[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir =  st.nextToken().charAt(0);
            directionChanges.put(time, dir);
        }

        System.out.println(simulate());


    }

    static int simulate() {
        int time = 0;
        int dir = 0; // 시작 방향: 오른쪽
        snake.offer(new int[]{0, 0}); //뒤쪽에다가
        board[0][0] = 2; // 뱀의 몸 표시=2, 사과가 있음 = 1, 아무것도 없음 = 0

        while (true) {
            time++;

            int[] head = snake.peekLast();
            int nx = head[0] + dx[dir];//0
            int ny = head[1] + dy[dir];//1

            // 벽에 부딪히거나 뱀 자신의몸과 부딪힘
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
                break;
            }

            // 사과가 없으면 꼬리 제거
            if (board[nx][ny] != 1) {
                int[] tail = snake.pollFirst();
                board[tail[0]][tail[1]] = 0;
            }

            // 머리 이동
            snake.offer(new int[]{nx, ny});
            board[nx][ny] = 2;

            // 방향 전환
            if (directionChanges.containsKey(time)) {//그 시간의 키가 있다면
                char c = directionChanges.get(time); //그 시간의 값을 갖고옴
                if (c == 'L') {
                    dir = (dir + 3) % 4; // 왼쪽 회전
                } else if (c == 'D') {
                    dir = (dir + 1) % 4; // 오른쪽 회전
                }
                //방향0: 오른쪽, 1:아래, 2:왼쪽, 3: 위쪾
            }
        }

        return time;
    }

}
