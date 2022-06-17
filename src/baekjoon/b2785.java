package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//체인
public class b2785 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
		StringTokenizer st =new StringTokenizer(br.readLine());
		int rings[] = new int[N];
		for(int i=0;i<N;i++) {
			rings[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(rings);
		
		int cnt = 0;
		int idx = 0;
		int remain = N;
		while(true) {
			rings[idx]--;
			cnt++;
			if(rings[idx]==0) {
				idx++;
				remain--;
				if(remain==1) {
					break;
				}
			}
			remain--;
			if(remain==1) {
				break;
			}
		}
		System.out.println(cnt);
	}
}
