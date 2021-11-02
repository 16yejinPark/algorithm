package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class b10026 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int cnt1 = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, false);
					cnt1++;
				}
			}
		}
		int cnt2=0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j]=false;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, true);
					cnt2++;
				}
			}
		}
		System.out.printf("%d %d", cnt1, cnt2);
	}

	private static void bfs(int x, int y, boolean isColorBlind) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.add(new Integer[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Integer[] point = q.poll();
			int n = point[0];
			int m = point[1];
			//System.out.printf("%d %d\n", n, m);


			for (int d = 0; d < 4; d++) {
				int nx = n + dx[d];
				int ny = m + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N&&!visited[nx][ny])
					if (isColorBlind) {
						if ((map[nx][ny] == map[x][y])||(map[nx][ny]=='R'&&map[x][y]=='G')||(map[nx][ny]=='G'&&map[x][y]=='R')) {
							visited[nx][ny] = true;
							q.add(new Integer[] { nx, ny });
						}
					} else {
						if (map[nx][ny] == map[x][y]) {
							visited[nx][ny] = true;
							q.add(new Integer[] { nx, ny });
						}
					}
			}
			
		}
	}
}
