import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[1001]; // 위치 0~1000까지의 기둥 높이 저장할 배열생성

        int maxHeight = 0;
        int maxIndex = 0;
//        int maxPosition = 0;

        // 입력 처리
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());//위치
            int H = Integer.parseInt(st.nextToken());//높이
            heights[L] = H;
            //입력받을때마다 해당 높이가 전의 높이보다 높으면 maxHeight에 저장
            if (H > maxHeight){
                maxHeight = H; // 기둥 높이
                maxIndex = L;  // 위치 저장
            }

//            if (L > maxPosition) {
//                maxPosition = L;
//            }
        }

        int area = 0; //넓이 저장할 변수

        // 왼쪽부터 최고 높이 기둥까지 누적
        int leftMax = 0;
        for (int i = 0; i < maxIndex; i++){
            if (heights[i] > leftMax){
                leftMax = heights[i];
            }
            area += leftMax;
        }

        // 오른쪽부터 최고 높이 기둥까지 누적
        int rightMax = 0;
        for (int i = 1000; i > maxIndex; i--){
        //for (int i = maxPosition; i > maxIndex; i--){

            if (heights[i] > rightMax) {
                rightMax = heights[i];
            }
            area += rightMax;
        }

        // 최고 높이 기둥 넓이 추가
        area += maxHeight;

        System.out.println(area);
    }
}
