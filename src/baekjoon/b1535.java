package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//안녕
public class b1535 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] hp = new int[N+1];
		int[] happy = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			hp[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			happy[i]=Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][100];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<100;j++) {
				int maxHappy = Math.max(dp[i][j-1], dp[i-1][j]);
				if(j>=hp[i])
					maxHappy = Math.max(maxHappy, dp[i-1][j-hp[i]]+happy[i]);
				dp[i][j] = maxHappy;
			}
		}
		System.out.println(dp[N][99]);
	}
}
