package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//겹치는건 싫어
public class b20922 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int maxLen = 0;
		int start = 1;
		int end = 1;
		int[] memo = new int[100001];
		int[] map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=N;i++) {
			int n = map[i];
			memo[n]++;

			while(memo[n]>K) {
				memo[map[start]]--;
				start++;
			}
		
			end++;
			maxLen = Math.max(maxLen, end-start);
			//System.out.printf("%d %d %d\n",start,end,memo[n]);
		}
		System.out.println(maxLen);
	}
}
