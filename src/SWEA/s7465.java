package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class s7465 {
	private static int N;
	private static int M;
	private static boolean[][] map;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new boolean[N + 1][N + 1];
			visited = new boolean[N + 1];
			for (int i = 1; i <= M; i++) {

				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				//System.out.printf("####%d %d\n", n1, n2);
				map[n1][n2] = true;
				map[n2][n1] = true;
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					cnt++;
					bfs(i);
				}
			}
			System.out.printf("#%d %d\n", t,cnt);
		}

	}

	private static void bfs(int start) {

		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while (!q.isEmpty()) {
			int n = q.poll();
			if (!visited[n]) {
				visited[n] = true;
				for (int i = 1; i <= N; i++) {
					if (map[n][i]) {
						q.add(i);
					}
				}
			}
		}
	}
}
