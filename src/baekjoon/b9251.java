package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//LCS
public class b9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = (" "+br.readLine()).toCharArray();
		char[] str2 = (" "+br.readLine()).toCharArray();
		int[][] dp = new int[str1.length][str2.length];
		for(int i=1;i<str1.length;i++) {
			for(int j=1;j<str2.length;j++) {
				if(str1[i]==str2[j]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}		
		System.out.println(dp[str1.length-1][str2.length-1]);
	}
}
