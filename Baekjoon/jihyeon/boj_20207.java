import java.io.*;
import java.util.*;

public class boj_20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dayCount = new int[366]; //365일까지 사용

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                dayCount[j]++;
            }
        }

        int area = 0;
        int width = 0; //현재 연속된 날짜 수
        int height = 0;//해당 구간 내 최대 일정 수

        for (int day = 1; day <= 365; day++) {
            if (dayCount[day] > 0) {
                width++;
                height = Math.max(height, dayCount[day]);
            } else {
                area += width * height;
                width = 0;
                height = 0;
            }
        }

        area += width * height;

        System.out.println(area);


    }
}
