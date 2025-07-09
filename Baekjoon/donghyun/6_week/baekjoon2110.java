import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 집의 수
        int c = sc.nextInt(); // 공유기의 수

        int[] house = new int[n];

        for (int i = 0; i < n; i++) {
            house[i] = sc.nextInt();
        }

        Arrays.sort(house); // 집 위치 정렬

        int left = 1; // 최소 거리
        int right = house[n - 1] - house[0]; // 최대 거리
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 현재 거리 간격

            // 공유기 설치 가능한 개수 계산
            int count = 1; // 첫 번째 집엔 항상 설치
            int lastPosition = house[0];

            for (int i = 1; i < n; i++) {
                if (house[i] - lastPosition >= mid) {
                    count++;
                    lastPosition = house[i];
                }
            }

            if (count >= c) {
                // 공유기를 충분히 설치할 수 있으면 더 넓은 거리 탐색
                result = mid;
                left = mid + 1;
            } else {
                // 공유기를 충분히 설치 못함 → 거리 좁혀야 함
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}

// 이분 탐색은 정렬된 데이터에서 원하는 값을 빠르게 찾기 위한 알고리즘
// 단순히 정렬된 배열에서 값을 찾는 것 뿐만 아니라, 최소값/최대값을 찾는 최적화 문제에서도 자주 사용됨
