package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//백조의 호수
public class b3197 {

	static Queue<int[]> q = new LinkedList<>();
	static Queue<int[]> nextQ = new LinkedList<>();
	static char[][] lake;
	static int[][] swan;
	static int[] parent;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int R;
	static int C;
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x]=find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		parent[x] = y;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lake = new char[R][C];
		swan = new int[2][2];
		parent = new int[R*C+1];

		for(int r=0;r<R;r++) {
			lake[r]=br.readLine().toCharArray();
		}
		
		int swan_num=0;
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(lake[r][c]=='L') {
					if(swan_num<2) {
						swan[swan_num][0] = r;
						swan[swan_num][1] = c;
					}
					swan_num++;
				}
				
				if(lake[r][c]=='.'||lake[r][c]=='L') {
					parent[r*C+c+1] = r*C+c+1;
					q.add(new int[] {r,c});
				}
			}
		}
		
		int day = 0;
		while(true) {
			bfs_union();
			if(find(swan[0][0]*C+swan[0][1]+1)==find(swan[1][0]*C+swan[1][1]+1)) {
				break;
			}
			bfs_melt();
			day++;
		}
		System.out.println(day);
	}
	
	static void bfs_union() {
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			nextQ.add(new int[] {x,y});
			q.remove();
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C && parent[nx*C+ny+1]>0) {
					union(x*C+y+1,nx*C+ny+1);
				}
			}
		}
	}
	
	static void bfs_melt() {
		while (!nextQ.isEmpty()) {
			int x = nextQ.peek()[0];
			int y = nextQ.peek()[1];
			nextQ.remove();
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C && parent[nx*C+ny+1]==0) {
					q.add(new int[] {nx,ny});
					parent[nx*C+ny+1] = nx*C+ny+1;
				}
			}
		}
	}
}
