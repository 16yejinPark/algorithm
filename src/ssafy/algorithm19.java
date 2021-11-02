package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algorithm19 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[N+1];
		memo[1]=2;
		memo[2]=5;
		for(int i=3;i<=N;i++) {
			memo[i]=(memo[i-1])*2+memo[i-2];
		}
		System.out.println(memo[N]);
	}
}
