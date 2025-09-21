import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class boj_3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String Line = br.readLine();
            if (isGoodWord(Line)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
    private static boolean isGoodWord(String word){
        Deque<Character> stack = new ArrayDeque<>();
        int len = word.length();

        for (int i = 0; i < len; i++) {
            char ch = word.charAt(i);
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }


}
