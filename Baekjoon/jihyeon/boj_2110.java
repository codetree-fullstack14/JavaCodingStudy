import java.io.*;
import java.util.*;

public class boj_2110 {
    static int[] houses;
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int left = 1; // 최소 거리
        int right = houses[N - 1] - houses[0]; // 최대 거리
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(mid)) {
                answer = mid;      // 거리 늘릴 수 있음
                left = mid + 1;
            } else {
                right = mid - 1;   // 거리 너무 멀어서 설치 불가
            }
        }

        System.out.println(answer);
    }

    // 공유기를 주어진 거리 이상으로 설치할 수 있는지 판단
    static boolean canInstall(int distance) {
        int count = 1;
        int lastInstalled = houses[0];

        for (int i = 1; i < N; i++) {
            if (houses[i] - lastInstalled >= distance) {
                count++;
                lastInstalled = houses[i];
            }
        }

        return count >= C;
    }
}
