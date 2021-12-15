package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//벽 부수고 이동하기 4
public class b16946 {
	static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	static int visited[][];
	static int map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		visited = new int[N][M];
		int n = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && visited[i][j] == 0) {
					visited[i][j] = n;
					bfs(i, j, n);
					n++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					sb.append("0");
				} else {
					HashSet<Integer> hs = new HashSet<>();
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
							hs.add(visited[nx][ny]);
						}
					}
					
					int sum = 1;
					for (int key : hs) {
						sum += hashMap.get(key);
					}
					sb.append(sum%10);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void bfs(int i, int j, int n) {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0 && visited[nx][ny] != n) {
					visited[nx][ny] = n;
					cnt++;
					q.add(new int[] { nx, ny });
				}
			}

		}
		hashMap.put(n, cnt);
	}
}
