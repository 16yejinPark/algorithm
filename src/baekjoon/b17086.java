package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//아기상어2
public class b17086 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] visited;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
			}
		}
		int max=0;
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				visited = new int[N][M];
				max = Math.max(max, bfs(n,m));
			}
		}
		System.out.println(max);
	}
	static int bfs(int n,int m) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{n,m});
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			
			if(map[x][y]==1) {
				return visited[x][y];
			}
			
			for(int d=0;d<8;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&visited[nx][ny]==0) {
					visited[nx][ny]=visited[x][y]+1;
					q.add(new int[]{nx,ny});
				}
			}
		}
		return -1;
	}
}
