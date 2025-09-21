import java.io.*;
import java.util.*;


public class boj_1005 {
    static int[] buildTime; //건물의 건설 시간
    static int[] dp; // 건물을 짓기까지 걸리는 최소 시간
    static List<Integer>[] graph;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            buildTime = new int[N + 1];
            dp = new int[N + 1];
            inDegree = new int[N + 1];
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            // 건물 짓는 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프 입력 및 진입 차수 계산
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                inDegree[to]++;
            }

            int W = Integer.parseInt(br.readLine());

            topologicalSort(N, W);
            sb.append(dp[W]).append('\n');
        }

        System.out.print(sb);
    }

    static void topologicalSort(int N, int target) {
        Queue<Integer> q = new LinkedList<>();

        // 초기 진입 차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                dp[i] = buildTime[i];
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                // 다음 건물까지 걸리는 시간 = 현재 건물을 지은 시간 + next 건물의 소요 시간
                dp[next] = Math.max(dp[next], dp[current] + buildTime[next]);

                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}