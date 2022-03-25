package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//벽 부수고 이동하기
public class b2206 {
	private static int N;
	private static int M;
	private static int[][][] visited;
	private static char[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Queue<Integer[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[N][M][2];
		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		visited[0][0][0]=1;
		queue.add(new Integer[] {0,0,0});
		while(!queue.isEmpty()) {
			Integer[] point = queue.remove();
			int x = point[0];
			int y = point[1];
			int breakWall = point[2];
			if(x==N-1&&y==M-1) {
				return visited[x][y][breakWall];
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&visited[nx][ny][breakWall]==0) {
					if(map[nx][ny]=='0') {
						visited[nx][ny][breakWall]=visited[x][y][breakWall]+1;
						queue.add(new Integer[] {nx,ny,breakWall});
					}else if(map[nx][ny]=='1'&&breakWall==0) {
						visited[nx][ny][1]=visited[x][y][breakWall]+1;
						queue.add(new Integer[] {nx,ny,1});
					}
				}
			}
		}
		return -1;
	}
	
	
/*	private static void dfs(int x, int y, int cnt) {	
		if (x == N-1 && y == M-1) {
			if(MIN > cnt||MIN==-1) {
				MIN = cnt;
			}
		} else {
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&!visited[nx][ny]) {
					if(map[nx][ny]=='0') {
						visited[nx][ny]=true;
						dfs(nx,ny,cnt+1);
						visited[nx][ny]=false;
					}else if(map[nx][ny]=='1'&&!breakWall) {
						breakWall=true;
						visited[nx][ny]=true;
						dfs(nx,ny,cnt+1);
						breakWall=false;
						visited[nx][ny]=false;
					}
				}
			}
		}
	}*/
}
