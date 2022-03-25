package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//내리막길
public class b1520 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				dp[n][m]=-1;
			}
		}
		

		System.out.println(dfs(0,0));
	}
	static long dfs(int x, int y) {
		if(x==N-1&&y==M-1) {
			dp[x][y]=1;
		}else if(dp[x][y]==-1) {
			dp[x][y]=0;
			if(x+1<N && map[x][y]>map[x+1][y]) {
				dp[x][y]+=dfs(x+1,y);
			}
			if(y+1<M && map[x][y]>map[x][y+1]) {
				dp[x][y]+=dfs(x,y+1);
			}
			if(y-1>=0 && map[x][y]>map[x][y-1]) {
				dp[x][y]+=dfs(x,y-1);
			}
			if(x-1>=0 && map[x][y]>map[x-1][y]) {
				dp[x][y]+=dfs(x-1,y);
			}
		}
//		for(int n=0;n<N;n++) {
//			for(int m=0;m<M;m++) {
//				System.out.printf("%2d ",dp[n][m]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		return dp[x][y];
	}
}
