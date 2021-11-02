package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//평범한 베낭
//짐을 쪼갤수 있는경우는 Fraction Knapsack Problem(Greedy)
//짐을 쪼갤수 없는경우는 0/1 Knapsack Problem(DP)
public class b12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] w = new int[N+1];
		int[] v = new int[N+1];
		int[][] memo =new int[N+1][K+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		int MAX=0;

		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				int pw = w[i];
				int pv = v[i];
				if(pw > j){
					memo[i][j] = memo[i-1][j];
	            }
	  			else{
	  				memo[i][j] = Math.max(memo[i-1][j], pv+memo[i][j-pw]);
	            }
			}
		}
		System.out.println(memo[N][K]);	
	}
}
