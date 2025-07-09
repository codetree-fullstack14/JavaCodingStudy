import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class b1005 {
    static long[] totalTime;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        totalTime = new long[T];
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 갯수
            int K = Integer.parseInt(st.nextToken()); // 순서 규칙 갯수
            // 빌드 소요 시간
            long[] timeToBuild = new long[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) { // 1부터 N까지
                timeToBuild[i] = Long.parseLong(st.nextToken());
            }
            // 빌드 규칙 순서
            // 건물 순서 리스트 생성
            ArrayList<Integer>[] adj = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            int[] indegree = new int[N + 1]; // 각 건물의 진입 차수
            // 규칙 순서 저장
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); // 선행
                int y = Integer.parseInt(st.nextToken()); // 후행
                adj[x].add(y); // 간선 추가
                indegree[y]++; // Y의 진입 차수 증가
            }
            int W = Integer.parseInt(br.readLine()); // 목표 건물

            // DP 배열: totalTime[i]는 i번 건물을 완성하는 데 필요한 최소 총 시간
            long[] totalTime = new long[N + 1]; // long으로 변경
            Deque<Integer> q = new LinkedList<>();

            // totalTime 초기화 및 진입 차수가 0인 건물들을 큐에 추가
            for (int i = 1; i <= N; i++) {
                totalTime[i] = timeToBuild[i]; // 초기에는 자기 자신의 건설 시간만 고려
                if (indegree[i] == 0) {
                    q.offer(i); // 선행 건물이 없는 건물 큐에 추가
                }
            }

            // BFS (위상 정렬) 수행
            while (!q.isEmpty()) {
                int current = q.poll(); // 현재 완성된 건물

                // 현재 건물에 의존하는 다음 건물들을 처리
                for (int next : adj[current]) {
                    // next 건물을 완성하는 데 걸리는 시간은,
                    // (현재까지 계산된 next 건물의 완성 시간)과
                    // (현재 건물의 완성 시간 + next 건물의 자체 건설 시간) 중 최대값
                    // 이는 next 건물에 도달하는 여러 경로 중 가장 긴 시간을 반영하기 위함
                    totalTime[next] = Math.max(totalTime[next], totalTime[current] + timeToBuild[next]);

                    indegree[next]--; // next 건물의 진입 차수 감소
                    if (indegree[next] == 0) { // 진입 차수가 0이 되면 큐에 추가
                        q.offer(next);
                    }
                }
            }
            sb.append(totalTime[W]).append("\n"); // 목표 건물의 완성 시간 저장
        }
        System.out.print(sb.toString()); // 결과 출력
    }
}

// 문제풀이 시간 1시간
// 문제 풀이
// 목표 건물 짓는 최소 시간
// 사용되는 알고리즘은 그래프, 최소 거리 문제

// 그래프 문제는 자신이 갈 곳을 저장해야함.
// 그래프 문제는 리스트를 이용


