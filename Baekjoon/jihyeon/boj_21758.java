import java.io.*;
import java.util.*;

public class boj_21758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] honey = new int[N];
        int[] prefix = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        honey[0] = Integer.parseInt(st.nextToken());
        prefix[0] = honey[0];
        for (int i = 1; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i - 1] + honey[i];
        }

        int max = 0;
        int total = prefix[N - 1];

        // 경우 1: 벌1 왼쪽 끝(0), 벌2 오른쪽 끝(N-1), 꿀통 i
        for (int i = 1; i < N - 1; i++) {
            int bee1 = prefix[i] - honey[0];               // 0 제외, 0~i까지
            int bee2 = total - prefix[i - 1] - honey[N - 1]; // N-1 제외, i~N-1까지 
            max = Math.max(max, bee1 + bee2);
        }

        // 경우 2: 벌1 왼쪽 끝(0), 꿀통 오른쪽 끝(N-1), 벌2 i
        for (int i = 1; i < N - 1; i++) {
            int bee1 = total - honey[0] - honey[i];   // i는 벌2 출발 위치라 제외
            int bee2 = prefix[N - 1] - prefix[i];
            max = Math.max(max, bee1 + bee2);
        }

        // 경우 3: 벌1 오른쪽 끝(N-1), 꿀통 왼쪽 끝(0), 벌2 i
        for (int i = 1; i < N - 1; i++) {
            int bee1 = total - honey[N - 1] - honey[i]; // i는 벌2 출발 위치 제외
            int bee2 = prefix[i - 1];
            max = Math.max(max, bee1 + bee2);
        }

        System.out.println(max);
    }
}

