package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//연속합
public class b1912 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		
		int max = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++) {
			int n = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i-1]+n, n);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
