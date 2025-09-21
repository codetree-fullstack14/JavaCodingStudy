import java.io.*;
import java.util.*;

public class boj_1377 {
    static class Element implements Comparable<Element> {
        int value;
        int originalIndex;

        Element(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Element[] arr = new Element[n];
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = new Element(val, i);
        }

        Arrays.sort(arr); // 값 기준 정렬

        int max = 0;
        for (int i = 0; i < n; i++) {
            // 정렬 후 인덱스 i, 원래 인덱스 arr[i].originalIndex
            int diff = arr[i].originalIndex - i;
            if (diff > max) max = diff;
        }

        System.out.println(max + 1);
    }
}
