package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1로 만들기2
public class b12852 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N+1];
		int tracking[] = new int[N+1];
		for(int i=1;i<=N;i++) {
			if(i*3<=N&&(dp[i*3]==0||dp[i*3]>dp[i]+1)) {
				dp[i*3]=dp[i]+1;	
				tracking[i*3] = i;
			}
			if(i*2<=N&&(dp[i*2]==0||dp[i*2]>dp[i]+1)) {
				dp[i*2]=dp[i]+1;
				tracking[i*2] = i;
			}
			if(i+1<=N&&(dp[i+1]==0||dp[i+1]>dp[i]+1)) {
				dp[i+1]=dp[i]+1;
				tracking[i+1] = i;
			}
		}
		
		System.out.println(dp[N]);
		int idx = N;
		while(idx!=1) {
			System.out.print(idx+" ");
			idx = tracking[idx];
		}
		System.out.println(1);
	}
}
