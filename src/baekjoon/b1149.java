package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//RGB거리
public class b1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] memo = new int[N+1][3];
		memo[0][0] = 0;
		memo[0][1] = 0;
		memo[0][2] = 0;
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(memo[i-1][1]<memo[i-1][2]) {
				memo[i][0] = memo[i-1][1] + r;
			}else {
				memo[i][0] = memo[i-1][2] + r;
			}
			
			if(memo[i-1][0]<memo[i-1][2]) {
				memo[i][1] = memo[i-1][0] + g;
			}else {
				memo[i][1] = memo[i-1][2] + g;
			}
			
			if(memo[i-1][1]<memo[i-1][0]) {
				memo[i][2] = memo[i-1][1] + b;
			}else {
				memo[i][2] = memo[i-1][0] + b;
			}
			System.out.print(Math.min(Math.min(memo[i][0],memo[i][1]),memo[i][2]));
		}
		
		int min=0;
		if(memo[N][1]<memo[N][0]) {
			if(memo[N][1]<memo[N][2]) {
				min = memo[N][1];
			}else {
				min = memo[N][2];
			}
		}else {
			if(memo[N][0]<memo[N][2]) {
				min = memo[N][0];
			}else {
				min = memo[N][2];
			}
		}
		System.out.println(min);
	}

}
