package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//피보나치수열3
public class b2749 {
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		int rep = (int)(N%1500000);
		dp = new int[rep+1];
		dp[1]=1;
		for(int i=2;i<=rep;i++) {
			dp[i]=(dp[i-1]+dp[i-2])%1000000;
		}
		System.out.println(dp[rep]);
	}
}
