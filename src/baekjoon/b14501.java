package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//퇴사
public class b14501 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[] dp=new int[N+1];
		ArrayList<ArrayList<int[]>> schedule = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			schedule.add(new ArrayList<int[]>());
		}
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); 
			int money = Integer.parseInt(st.nextToken());
			if(d+i<=N) {
				schedule.get(i+d).add(new int[] {d,money});
			}
		}
		for(int i=1;i<=N;i++) {
			dp[i]=dp[i-1];
			for(int[] reservation : schedule.get(i)) {
//				System.out.printf("%d %d %d\n",i,reservation[0],reservation[1]);
				dp[i]=Math.max(dp[i], dp[i-reservation[0]]+reservation[1]);
			}
		}
		System.out.println(dp[N]);
	}
}
