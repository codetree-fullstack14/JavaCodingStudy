import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class b2668 {
    static int[] lst;
    static boolean[] visited;

    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        lst = new int[n+1]; // 0인덱스를 제외하고 1인덱스부터 시작 - 명확성
        for (int i = 1; i <= n; i++) {
            lst[i] = Integer.parseInt(br.readLine());
        }

        // 모든 리스트를 검토하면서 dfs 진행, 이미 접근했던 lst라면 제외하고 진행
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            // 방문 여부 확인 후 dfs
            if(visited[i]) {
                dfs(i, i);
            }
        }
        // 정렬
        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }

    }
    public static void dfs(int start, int now) {
        if(visited[now]) return;
        visited[now] = true;
        int next = lst[now];

        if (!visited[next]) {
            dfs(start, next);
        }
        else{
            if(next == start){ // 사이클 발생
                result.add(start);
            }
        }
        visited[now] = false; // 경로 초기화
    }
}
// 문제풀이 시간 - 1시간

// 문제 풀이
// 1~N 의 key 값과 랜덤 중복으로 입력되는 value 값이 있음.
// key값과 value값의 각각의 집합이 일치하는 최대치
// 정수의 개수와 뽑은 정수들을 오름차순으로 차례로 출력

// key를 뽑고 해당 value를 가진 key값의 value를 조사
// key == value는 패스

// 어떤 알고리즘?
// DP x - 이전 값을 계속해서 저장할 필요는 없음.
// dfs - key값의 value값을 key로 따라간 뒤, 마지막에 두 집합이 동일한지 비교
// 두 집합을 비교할 필요 없이 사이클이 형성되는지 판단하면 끝

// 구현
// 어떤 자료구조?
// 방법 1. stack을 이용해서 모든 value값 탐색 - bfs 방식이긴 하지만 dfs와 동일하게 동작(길이 하나뿐이기 때문)
// 방법 2. 방문 부울리언 배열 이용

