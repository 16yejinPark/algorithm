package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//단어 섞기
//으어ㅓ어ㅠㅠㅠㅠ개어려워
public class b9177 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		for(int n=1;n<=N;n++) {
			st = new StringTokenizer(br.readLine());
			String word1 = st.nextToken();
			String word2 = st.nextToken();
			String word3 = st.nextToken();
			
			word1=" "+word1;
			word2=" "+word2;
			
			boolean[][] dp = new boolean[word1.length()][word2.length()];
			dp[0][0]=true;
			
			for(int i=1;i<word1.length();i++) {
				if(word3.charAt(i-1)==word1.charAt(i))
					dp[i][0]=dp[i-1][0];
				else
					dp[i][0]=false;
			}
			for(int i=1;i<word2.length();i++) {
				if(word3.charAt(i-1)==word2.charAt(i))
					dp[0][i]=dp[0][i-1];
				else
					dp[0][i]=false;
			}
			
			for(int i=1;i<word1.length();i++) {
				for(int j=1;j<word2.length();j++) {
					if(word3.charAt(i+j-1)==word1.charAt(i)&&word3.charAt(i+j-1)==word2.charAt(j)) {
						dp[i][j]=(dp[i-1][j]||dp[i][j-1]);
					}else if(word3.charAt(i+j-1)==word1.charAt(i)) {
						dp[i][j]=dp[i-1][j];
					}else if(word3.charAt(i+j-1)==word2.charAt(j)) {
						dp[i][j]=dp[i][j-1];
					}else {
						dp[i][j]=false;
					}
				}
			}
			String answer = (dp[word1.length()-1][word2.length()-1]?"yes":"no");
			System.out.printf("Data set %d: %s\n",n,answer);
		}
	}
}
