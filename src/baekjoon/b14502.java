package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//연구소
public class b14502 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int max=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//세로
		int M = Integer.parseInt(st.nextToken());	//가로
		map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m]=Integer.parseInt(st.nextToken());
			}
		}
		makeWall(N,M,0);
		System.out.println(max);
		
	}
	static void makeWall(int N,int M,int cnt) {
		if(cnt==3) {
			visited = new boolean[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==2)
						bfs(i,j,N,M);
				}
			}
	
			int total=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(!visited[i][j]&&map[i][j]==0) {
						total++;
					}
				}
			}
			max = Math.max(max, total);
			return;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					makeWall(N,M,cnt+1);
					map[i][j]=0;
				}
			}
		}
	}
	static void bfs(int X,int Y,int N, int M) {
		visited[X][Y]=true;
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] {X,Y});
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			for(int d=0;d<4;d++) {
				int nx=x+dx[d];
				int ny=y+dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M&&!visited[nx][ny]&&map[nx][ny]!=1) {
					visited[nx][ny]=true;
					q.add(new Integer[] {nx,ny});
				}
			}
		}
	}

}
