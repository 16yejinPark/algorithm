package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algorithm18 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		점화식을 세워서 풀기
//		int[] memo = new int[N+1];
//		memo[1]=2;
//		memo[2]=3;
//		for(int i=3;i<=N;i++) {
//			memo[i]=memo[i-1]+memo[i-2];
//		}
//		System.out.println(memo[N]);
		
		int[][] memo = new int[N+1][2];
		memo[1][0] = 1;
		memo[1][1] = 1;
		
		for(int i=2;i<N+1;i++) {
			memo[i][0]=memo[i-1][0]+memo[i-1][1];
			memo[i][1]=memo[i-1][0];
		}
		System.out.println(memo[N][0]+memo[N][1]);
	}

}
