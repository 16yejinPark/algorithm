package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//백조의 호수
public class b3197 {
	static char[][] lake;
	static boolean[][] visited;
	static int[][] howManyDaysTake;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int R;
	static int C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lake = new char[R][C];
		int swan_num=0;
		int swan_x=0;
		int swan_y=0;
		for(int r=0;r<R;r++) {
			lake[r]=br.readLine().toCharArray();
		}
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				if(lake[r][c]=='L') {
					if(swan_num==0) {
						swan_x=r;
						swan_y=c;
					}
					swan_num++;
				}
			}
		}
		
		int cnt=0;
		while(true) {	
			//swanMeet check
			visited = new boolean[R][C];
			if(swan_num==swanCanMeet(swan_x,swan_y)) {
				break;
			}
			//얼음이 녹는다.	
			visited = new boolean[R][C];
			for(int r=0;r<R;r++) {
				for(int c=0;c<C;c++) {
					if(!visited[r][c]&&lake[r][c]!='X') {	
						bfs(r,c);
					}
				}
			}
			cnt++;
		}
		System.out.println(cnt);
	}
	static int swanCanMeet(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r,c});
		visited[r][c]=true;
		int cnt=1;
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<R&&ny>=0&&ny<C&&!visited[nx][ny]&&lake[nx][ny]!='X') {
					if(lake[nx][ny]=='L') 
						cnt++;
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		return cnt;
	}
	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		visited[r][c]=true;
		q.add(new int[] {r,c});
		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<R&&ny>=0&&ny<C&&!visited[nx][ny]) {
					visited[nx][ny]=true;
					if(lake[nx][ny]=='X')
						lake[nx][ny]='.';
					else
						q.add(new int[] {nx,ny});
				}
			}
		}
	}
}
