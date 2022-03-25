package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//유기농 배추
public class b1012 {
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	public static int[][] map;
	public static int N;
	public static int M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			int K = Integer.parseInt(st.nextToken());
			for(int k=0;k<K;k++) {
				st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				
				map[n][m]=1;
			}
			
			int worm=0;
			for(int n=0;n<N;n++) {
				for(int m=0;m<M;m++) {
					if(map[n][m]==1) {
						dfs(n,m);
						worm++;
					}
				}
			}
			System.out.println(worm);
		}
		
		

	}
	public static void dfs(int x,int y) {
		
		for(int d=0;d<4;d++) {
			int rx = x+dx[d];
			int ry = y+dy[d];
			if(rx>=0&&rx<N&&ry>=0&&ry<M&&map[rx][ry]==1) {
				map[rx][ry]=2;
				dfs(rx,ry);
			}
		}
	}
}
