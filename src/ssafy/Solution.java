package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//비상구 탈출
public class Solution {
	static int map[][][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N][30];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j][0] = Integer.parseInt(st.nextToken());
				}
			}
			
			int minTime = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j][0]==1) {
						minTime = Math.max(bfs(i,j), minTime);
					}
				}
			}		
			System.out.printf("#%d %d\n",t,minTime);
		}
	}
	
	static int bfs(int r,int c) {
		int[][] visited = new int[N][N];
		visited[r][c] = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		int find = 0;
		int findescape[][] = new int[2][3];
		
		outer:while(!q.isEmpty()) {
			int temp[] = q.remove();
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<N&&visited[nx][ny]==0&&!(r==nx&&c==ny)) {
					visited[nx][ny]=visited[x][y]+1;
					q.add(new int[] {nx,ny});
					if(map[nx][ny][0]==2) {
						findescape[find][0]=nx;
						findescape[find][1]=ny;
						findescape[find][2]=visited[nx][ny]+1;
						find++;
						if(find==2) {
							break outer;
						}
					}
				}
				
			}
		}
		
		//시간 덜걸리는 문 찾기
		int minTime = 0;

		int ex1 = findescape[0][0];
		int ey1 = findescape[0][1];
		int et1 = findescape[0][2];	//탈출해야하는 시각
		while(map[ex1][ey1][et1]!=0) {
			//System.out.printf("%d %d %d %d\n",ex1,ey1,et1,map[ex1][ey1][et1]);
			et1++;
		}
		
		int ex2 = findescape[1][0];
		int ey2 = findescape[1][1];
		int et2 = findescape[1][2];	//탈출해야하는 시각
		while(map[ex2][ey2][et2]!=0) {
			et2++;
		}
		if(et1<et2) {
			minTime = et1;
			map[ex1][ey1][et1]=1;
		}else {
			minTime = et2;
			map[ex2][ey2][et2]=1;
		}
		//ystem.out.printf("%d %d >> minTime: %d\n",et1,et2,minTime);
		return minTime;
	}
	
}
