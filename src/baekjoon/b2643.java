package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b2643 {

	//색종이 올려 놓기
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
			if(o1[0]==o2[0]) 
				return Integer.compare(o1[1],o2[1]);
			return Integer.compare(o1[0],o2[0]);
		});
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(x<y) {
				pq.add(new int[] {x,y});
			}else {
				pq.add(new int[] {y,x});
			}
		}
		
		int n = 1;
		int[][] dp = new int[N+1][2];
		while(!pq.isEmpty()) {
			int temp[] = pq.remove();
			dp[n][1]=temp[1];
			n++;
		}
		
		int max = 1;
		dp[1][0] = 1;
		for(int i=2;i<=N;i++) {
			dp[i][0] = 1;
			for(int j=1;j<i;j++) {
				if(dp[i][1]>=dp[j][1] && dp[i][0]<dp[j][0]+1) {
					dp[i][0] = dp[j][0] + 1;
					max = Math.max(dp[i][0], max);
				}
			}
		}
		
		for(int i=0;i<=1;i++) {
			for(int j=0;j<=N;j++) {
				System.out.printf("%d ",dp[j][i]);
			}
			System.out.println();
		}
		
		System.out.println(max);
	}

}
