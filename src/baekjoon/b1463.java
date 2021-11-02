package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1로 만들기
public class b1463 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[N+1];

		memo[1] = 0;
		if(N>=2)
			memo[2] = 1;
		if(N>=3)
			memo[3] = 1;	
		for(int i=4;i<=N;i++) {
			int min = Integer.MAX_VALUE;
			if(i%3==0) {
				min = Math.min(min, memo[i/3]+1);
			}
			if(i%2==0) {
				min = Math.min(min, memo[i/2]+1);
			}
			min = Math.min(min, memo[i-1]+1);
			memo[i] = min;
		}
		System.out.println(memo[N]);
	}

}
