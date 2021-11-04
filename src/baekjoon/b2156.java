package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//포도주 시식
public class b2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] memo=new int[N+1][3];
		for(int i=1;i<=N;i++) {
			int n = Integer.parseInt(br.readLine());
			memo[i][0] = Math.max(memo[i-1][0], Math.max(memo[i-1][1], memo[i-1][2]));
			memo[i][1] = memo[i-1][0] + n;
			memo[i][2] = memo[i-1][1] + n;
		}
		System.out.println(Math.max(memo[N][0], Math.max(memo[N][1], memo[N][2])));
	}
}
