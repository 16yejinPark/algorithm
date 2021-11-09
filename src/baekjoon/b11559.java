package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//Puyo Puyo
public class b11559 {
	static char map[][] = new char[12][6];
	static boolean visited[][] = new boolean[12][6];
	static boolean changed;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<12;i++) {
			String temp = br.readLine();
			for(int j=0;j<6;j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		int cnt = 0;
		while(true) {
			changed = false;
			visited = new boolean[12][6];
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(!visited[i][j] && map[i][j]!='.') {
						visited[i][j]=true;
						bfs(i,j);
					}
				}
			}
			if(changed) {
				gravity();
				
			}else {
				break;
			}
			cnt++;
//			for(int i=0;i<12;i++) {
//				for(int j=0;j<6;j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}System.out.println();
		}
		System.out.println(cnt);
	}
	
	private static void gravity() {
		boolean changed = false;
		while(true) {
			changed = false;
			for(int i=10;i>=0;i--) {
				for(int j=0;j<6;j++) {
					if(map[i][j]!='.'&&map[i+1][j]=='.') {
						changed = true;
						map[i+1][j] = map[i][j];
						map[i][j] = '.';
					}
				}
			}
			if(!changed) {
				return;
			}
		}
		
	}
	
	private static void bfs(int i, int j) {
		char color = map[i][j];
		Queue<int[]> q = new LinkedList<>();
		ArrayDeque<int[]> list = new ArrayDeque<int[]>();
		q.add(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			list.add(temp);
			int x = temp[0];
			int y = temp[1];

			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<12&&ny>=0&&ny<6&&!visited[nx][ny]&&map[nx][ny]==color) {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
		if(list.size()>=4) {
			changed = true;
			for(int[] point : list) {
				int x = point[0];
				int y = point[1];
				map[x][y]='.';
			}
		}
	}
}
