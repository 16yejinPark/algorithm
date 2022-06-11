package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//벼락치기
public class b14728 {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//단원 개수
		int T = Integer.parseInt(st.nextToken());	//시험까지 공부 할 수 있는 총 시간
		
		int dp[][] = new int[N+1][T+1];
		int time[] = new int[N+1];
		int value[] = new int[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int n=1;n<=N;n++) {
			for(int t=1;t<=T;t++) {
				if(time[n]<=t) {
					dp[n][t] = Math.max(dp[n-1][t], value[n]+dp[n-1][t-time[n]]);
				}else {
					dp[n][t] = dp[n-1][t];
				}
			}
		}
		System.out.println(dp[N][T]);
	}

}
