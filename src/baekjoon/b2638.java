package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//치즈
public class b2638 {	
	static int X;
	static int Y;
	static int[][] map;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		map = new int[X][Y];
		for(int x=0;x<X;x++) {
			st = new StringTokenizer(br.readLine());
			for(int y=0;y<Y;y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt=0;	
		findAnswer: while(true) {
			findCheese : for(int x=0;x<X;x++) {
				for(int y=0;y<Y;y++) {
					if(map[x][y]==1) {
						break findCheese;
					}
					if(x==X-1&&y==Y-1) {
						break findAnswer;
					}
				}
			}
			bfs();
			cnt++;
		}
		System.out.println(cnt);
	}
	static void bfs() {
		int[][] visited = new int[X][Y];
		Queue<int[]> q = new LinkedList<int[]>(); 
		q.add(new int[] {0,0});
		visited[0][0]=1;
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<X&&ny>=0&&ny<Y) {
					if(visited[nx][ny]==0&&map[nx][ny]==0) {
						visited[nx][ny]=1;
						q.add(new int[] {nx,ny});
					}else if(visited[nx][ny]==0&&map[nx][ny]==1) {
						visited[nx][ny]=1;
					}else if(visited[nx][ny]==1&&map[nx][ny]==1) {
						map[nx][ny]=0;
					}
				}
			}
		}
	}
}
