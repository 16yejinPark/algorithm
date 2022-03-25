package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class s1861 {
	static private int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};	//상하좌우
	static private int[][] map;
	static private int[][] visited;
	static private int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			N = Integer.parseInt(br.readLine());
			//map 받기
			map = new int[N][N];
			visited = new int[N][N];
			for(int r=0;r<N;r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max=0;
			int max_room=0;
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					
					if(visited[r][c]==0)
						findVisited(r, c);
					//만약 max와 현재 길이가 같다면
					if(max == visited[r][c]) {
						if(max_room>map[r][c])
							max_room=map[r][c];
					//만약 max보다 현재 길이가 더 길다면
					}else if(max < visited[r][c]) {
						max = visited[r][c];
						max_room = map[r][c];
					}
					
				}
			}
			System.out.printf("#%d %d %d\n",i+1,max_room,max);
		}

	}
	
	static private void findVisited(int x, int y) {
		int sum = 1;
		//4방향 확인
		for(int i=0;i<4;i++) {
			int nx = x + delta[i][0];
			int ny = y + delta[i][1];
			if(nx>=0&&nx<N&&ny>=0&&ny<N) {	
				//방 넘버가 현재보다 1 크다면
				if(map[x][y]+1==map[nx][ny]) {
					//아직 방문하지 않은 곳이라면
					if(visited[nx][ny]==0)
						findVisited(nx, ny);
					sum+=visited[nx][ny];
					if(visited[nx][ny]!=1)
						break;
				}
			}
		}
		visited[x][y]=sum;
	}

}
