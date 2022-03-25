package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class b6087_sunggun {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int startR = -1, startC = -1, targetR = -1, targetC = -1, min = 123456789;
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		char[][] map = new char[H][W];
		int[][] visited = new int[H][W];

		for (int i = 0; i < H; i++) {
			String inp = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = inp.charAt(j);
				visited[i][j] = 123456;
				if (map[i][j] == 'C' && startR == -1) {
					startR = i;
					startC = j;
				} else if (map[i][j] == 'C' && startR != -1) {
					targetR = i;
					targetC = j;
				}
			}
		}
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { startR, startC, -1, -1 });

		visited[startR][startC] = 0;
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			if (tmp[2] > min)
				continue;
			if (tmp[0] == targetR && tmp[1] == targetC) {
				if (min > tmp[2])
					min = tmp[2];
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int rr = tmp[0] + dr[d];
				int cc = tmp[1] + dc[d];
				if (rr >= 0 && rr < H && cc >= 0 && cc < W && map[rr][cc] != '*') {
					if (tmp[3] == d && visited[rr][cc] >= tmp[2]) {
						q.add(new int[] { rr, cc, tmp[2], d });
						visited[rr][cc] = tmp[2];
					} else if (visited[rr][cc] >= tmp[2] + 1) {
						q.add(new int[] { rr, cc, tmp[2] + 1, d });
						visited[rr][cc] = tmp[2] + 1;
					}
				}
			}
		}

		System.out.println(min);

	}

}
