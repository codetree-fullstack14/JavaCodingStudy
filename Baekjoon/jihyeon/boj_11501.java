import java.util.*;
import java.io.*;

public class boj_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //문자열을 계속 더할때 String을 그냥 쓰면 매번 새 객체가 만들어져서 비효율적이다.
        //버퍼에 문자열을 모아주었다가 한꺼번에 출력할 수 있어서 성능이 좋다.
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] prices = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            long maxPrice = 0;
            long profit = 0;

            for (int i = N - 1; i >= 0; i--) {
                if (prices[i] > maxPrice) {
                    maxPrice = prices[i];
                } else {
                    profit += (maxPrice - prices[i]);
                }
            }

            sb.append(profit).append('\n');
        }

        System.out.print(sb);
    }
}
