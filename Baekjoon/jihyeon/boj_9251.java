import java.io.*;

public class boj_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        int lenA = A.length();
        int lenB = B.length();

        // DP 배열을 2행만 사용해서 메모리 최적화 (rolling array)
        int[] prev = new int[lenB + 1];
        int[] curr = new int[lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        System.out.println(prev[lenB]);
    }
}
