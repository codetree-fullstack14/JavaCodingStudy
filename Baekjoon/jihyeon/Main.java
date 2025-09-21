//구현, 시뮬레이션 문제

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 사람 수
        int[] input = new int[N];
        //각 사람 왼쪽에 몇 명의 키 큰 사람이 있어야하는지 저장
        int[] result = new int[N];
        //최종 줄 선 결과 저장


        //왼쪽에 있어야하는 키 큰 사람 수(공백을 기준으로 나눠서 배열에 저장)

        //입력이 적고 코드 가독성이 중요할때 (메모리사용량: 더큼, 속도: 보통, 가독성: 좋음)
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(tokens[i]);
        }
        //입력이 많고 빠른 처리가 필요할때 (메모리사용량: 더 적음, 속도: 조금 더 빠름, 가독성: 약간 덜 직관적)
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            input[i] = Integer.parseInt(st.nextToken());
//        }



        for (int i = 0; i < N; i++) {
            int tall = input[i];
            int cnt = 0; //현재까지 빈칸 또는 작은 사람을 세며 카운트

            for (int j = 0; j < N; j++) {
                if (result[j] == 0) { //자리가 비어있다면
                    if (cnt == tall) {
                        result[j] = i + 1; //사람번호는 1번부터기 떄문에 i+1
                        break;
                    }
                    cnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

//        이렇게 하면 맨뒤에 공백이 추가가됨. 다른 플랫폼에서는 오답일 수 있음
//        for (int i = 0; i < N; i++) {
//            System.out.print(result[i] + " ");
//        }


    }
}
