package BaekJoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//점프
public class b1890 {
	static int N;
	static int[][] map;
	static long[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				dp[i][j]=-1;
			}
		}
		System.out.println(dfs(0,0));
	}
	static long dfs(int i,int j) {
		if(i==N-1&&j==N-1) {
			return 1;
		}
		else if(dp[i][j]==-1) {
			dp[i][j]=0;
			int ni = i+map[i][j];
			if(ni<N) {
				dp[i][j]+=dfs(ni,j);
			}
			int nj = j+map[i][j];
			if(nj<N) {
				dp[i][j]+=dfs(i,nj);
			}
		}
//		for(int x=0;x<N;x++) {
//			for(int y=0;y<N;y++)
//				System.out.print(dp[x][y]+" ");
//			System.out.println();
//		}			
//		System.out.println();
		return dp[i][j];
	}
}
