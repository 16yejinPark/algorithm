package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//마법사 상어와 토네이도
public class b20057 {
	static int[][][] go = {{{0,-2},{1,-1},{-1,-1},{2,0},
							{1,0},{-1,0},{-2,0},{1,1},{-1,1}},
			
							{{2,0},{1,-1},{1,1},{0,-2},
							{0,-1},{0,1},{0,2},{-1,-1},{-1,1}},
							
							{{0,2},{1,1},{-1,1},{2,0},
							{1,0},{-1,0},{-2,0},{-1,-1},{1,-1}},
							
							{{-2,0},{-1,-1},{-1,1},{0,-2},
							{0,-1},{0,1},{0,2},{1,-1},{1,1}}};
	
	static double[] w = {0.05,0.1,0.1,0.02,0.07,0.07,0.02,0.01,0.01};
	static int[] dx = {0,1,0,-1};	//서, 남, 동, 북
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sandSum = 0;
		int x = N/2;
		int y = N/2;
		int n=1;
		int d=0;
		outer:while(true) {
			for(int i=0;i<n*2;i++) {
				x+=dx[d];
				y+=dy[d];
				int sand = map[x][y];
				int total = 0;
				map[x][y]=0;
				for(int j=0;j<9;j++) {
					int nx = x+go[d][j][0];
					int ny = y+go[d][j][1];
					
					if(nx>=0&&nx<N&&ny>=0&&ny<N) {
						map[nx][ny] += (int) (sand * w[j]);
					}else {
						sandSum += (int) (sand * w[j]);
					}
					total += (int) (sand * w[j]);
				}
				sand -= total;
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<N) {
					map[nx][ny]+=sand;
				}else {
					sandSum += sand;
				}
				if(x==0&&y==0) {
					break outer;
				}
				if(i==n-1) {
					d=(d+1)%4;
				}			
			}
			d=(d+1)%4;
			n++;
		}
		System.out.println(sandSum);
	}
}
