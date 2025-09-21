import java.io.*;
import java.util.*;

public class boj_4195 {
    static int[] parent;
    static int[] size;

    // find: 부모 찾기 (경로 압축)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // union: 두 집합 합치기, 합친 후 크기 반환
    static int union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
            size[a] += size[b];
        }
        return size[a];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[2 * F];
            size = new int[2 * F];
            for (int i = 0; i < 2 * F; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            Map<String, Integer> map = new HashMap<>();
            int idx = 0;

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if (!map.containsKey(f1)) {
                    map.put(f1, idx++);
                }
                if (!map.containsKey(f2)) {
                    map.put(f2, idx++);
                }

                int result = union(map.get(f1), map.get(f2));
                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }
}