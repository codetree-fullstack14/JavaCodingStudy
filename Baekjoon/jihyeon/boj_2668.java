import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2668 {
    static int N;
    static int [] arr;
    static boolean[] visited;
    static boolean[] finished;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        finished = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) { //중복탐색 막기위해서.
                dfs(i);
            }
        }

        Collections.sort(result); //List 오름차순 정렬
        sb.append(result.size()).append('\n');
        for(int num : result){
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int start){
        visited[start] = true;
        int next = arr[start];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            for (int i = next; i != start; i = arr[i]) {
                result.add(i);
            }
            result.add(start);
        }
        finished[start] = true;
    }
}
















