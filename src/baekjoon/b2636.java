package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//치즈
public class b2636 {
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] map = null;
	private static boolean[][] visited = null;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		int cheese_num=0;
		while(cheeseNum(R,C)>0) {
			cheese_num=cheeseNum(R,C);
			bfs(R, C);
			cnt++;
		}

		System.out.println(cnt);
		System.out.println(cheese_num);
	}
	static int cheeseNum(int R,int C) {
		int cheese=0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j]==1) {
					cheese++;
				}
			}
		}
		return cheese;
	}
	static void bfs(int r, int c) {
		visited = new boolean[r][c];
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.add(new Integer[] { 0, 0 });
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.remove();
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if (map[nx][ny] == 1) {
						map[nx][ny] = 0;
					} else {
						q.add(new Integer[] { nx, ny });
					}
				}
			}
		}
	}

}
