package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//보물섬
public class b2589 {
	private static int R;
	private static int C;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static char[][] map;
	private static int MAX = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c]=='L') {
					int max = bfs(r, c);
					if(MAX<max)
						MAX=max;
				}
//				for (int rr = 0; rr < R; rr++) {
//					for (int cc = 0; cc < C; cc++) {
//						System.out.println("");
//					}
//				}
			}
		}
		System.out.println(MAX);
	}

	private static int bfs(int r, int c) {
		int[][] visited = new int[R][C];
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[] { r, c });
		int max=0;
		//여기 1로 안해놓으면 시작점도 다시 탐색함 주의
		visited[r][c]=1;
		while (!q.isEmpty()) {
			Integer[] point = q.remove();
			int x = point[0];
			int y = point[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < R && ny >= 0 && ny < C && visited[nx][ny]==0 && map[nx][ny] == 'L') {
					visited[nx][ny] = visited[x][y]+1;
					q.add(new Integer[] { nx, ny });
					if(max<visited[nx][ny]-1) {
						max=visited[nx][ny]-1;
					}
				}
			}
		}
		return max;
	}
}
