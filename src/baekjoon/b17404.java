package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//RGB거리 2
public class b17404 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		int cost[][] = new int[N + 1][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			int memo[][] = new int[N + 1][3];
			memo[0][0] = 1000000;
			memo[0][1] = 1000000;
			memo[0][2] = 1000000;
			memo[0][i] = cost[0][i];
			for (int j = 1; j < N; j++) {
				memo[j][0] = Math.min(cost[j%N][0] + memo[j-1][1], cost[j%N][0] + memo[j-1][2]);
				memo[j][1] = Math.min(cost[j%N][1] + memo[j-1][0], cost[j%N][1] + memo[j-1][2]);
				memo[j][2] = Math.min(cost[j%N][2] + memo[j-1][1], cost[j%N][2] + memo[j-1][0]);
			}
			
			memo[N-1][i] = 1000000;
			
			result = Math.min(memo[N-1][0], result);
			result = Math.min(memo[N-1][1], result);
			result = Math.min(memo[N-1][2], result);
		}
		System.out.println(result);
	}
}
