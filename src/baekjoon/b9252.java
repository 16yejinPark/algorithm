package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//LCS2
public class b9252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str1 = (" " + br.readLine()).toCharArray();
		char[] str2 = (" " + br.readLine()).toCharArray();
		int dp[][] = new int[str1.length][str2.length];
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {	
				if (str1[i] == str2[j]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int r=str1.length-1;
		int c=str2.length-1;
		System.out.println(dp[r][c]);
		if(dp[r][c]==0) {
		}else {
			while(dp[r][c]!=0) {
				if(str1[r] == str2[c]) {
					sb.insert(0,str1[r]);
					r--;c--;
				}else {
					if(dp[r-1][c]>dp[r][c-1]) {
						r--;
					}else {
						c--;
					}
				}
			}
		}
		System.out.println(sb.toString());
	}

}
