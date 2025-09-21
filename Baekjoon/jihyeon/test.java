import java.util.*;

public class test {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 방향 간선 저장
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph.get(s).add(e);
            edges.add(new int[]{s, e});
        }

        // 양방향인지 확인하고 union 수행
        for (int[] edge : edges) {
            int s = edge[0], e = edge[1];
            if (graph.get(e).contains(s)) { // 반대 방향 간선도 존재하면
                union(s, e);
            }
        }

        Set<Integer> unions = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            unions.add(find(i));
        }

        System.out.println(unions.size());
    }
}
