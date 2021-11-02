package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//동전
public class b9084 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			st = new StringTokenizer(br.readLine());
			int minCoin = 0;
			for(int n=0;n<N;n++) {
				coins[n]=Integer.parseInt(st.nextToken());
				minCoin=Math.min(minCoin, coins[n]);
			}
			int M = Integer.parseInt(br.readLine());
			int[] dp = new int[M+1];
			dp[0]=1;
			
			for(int coin:coins) {
				for(int i=coin;i<=M;i++) {
					dp[i]+=dp[i-coin];
				}
			}
			System.out.println(dp[M]);
		}
	}

}
