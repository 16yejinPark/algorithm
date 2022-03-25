package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//감시
//개선 방법1 : cctv위치만 list에 포함시켜 map전체 탐색하지않고 cctv만 탐색.
//개선 방법2 : dfs할때 sum을 맨 마지막에 계산하지 않고 각각 넘김.(별로 효과없었음)

public class b15683 {
	static int X;
	static int Y;
	static int max = 0;
	static boolean[][] looking;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		looking = new boolean[X][Y];
		map = new int[X][Y];

		int total = 0;
		for (int i = 0; i < X; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < Y; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					list.add(new int[] { i, j, map[i][j] });
					looking[i][j] = true;
					total++;
				} else if (map[i][j] == 6) {
					looking[i][j] = true;
					total++;
				}
			}
		}

		dfs(0, total);
		System.out.println(X * Y - max);
	}

	public static void dfs(int n,int total) {

		if (n == list.size()) {
			max = Math.max(max, total);
			return;
		}

		for (int i = n; i < list.size(); i++) {
			boolean[][] copy = new boolean[X][Y];
			for (int k = 0; k < X; k++) {
				for (int l = 0; l < Y; l++) {
					copy[k][l] = looking[k][l];
				}
			}

			int x = list.get(i)[0];
			int y = list.get(i)[1];
			int type = list.get(i)[2];
			int nx = 0;
			int ny = 0;
			switch (type) {

			case 1:
				// 4가지 방향
				for (int d = 0; d < 4; d++) {
					int sum = 0;
					nx = x + dx[d];
					ny = y + dy[d];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d];
						ny += dy[d];
					}
					dfs(i+1,total+sum);
					for (int k = 0; k < X; k++) {
						for (int l = 0; l < Y; l++) {
							looking[k][l] = copy[k][l];
						}
					}
				}
				break;

			case 2:
				// 2가지 방향
				for (int d = 0; d < 2; d++) {
					int sum = 0;
					nx = x + dx[d];
					ny = y + dy[d];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d];
						ny += dy[d];
					}
					
					int d2 = d + 2;
					nx = x + dx[d2];
					ny = y + dy[d2];

					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d2];
						ny += dy[d2];
					}
					dfs(i+ 1,total+sum);
					for (int k = 0; k < X; k++) {
						for (int l = 0; l < Y; l++) {
							looking[k][l] = copy[k][l];
						}
					}
				}
				break;
			case 3:
				for (int d = 0; d < 4; d++) {
					int sum = 0;
					nx = x + dx[d];
					ny = y + dy[d];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d];
						ny += dy[d];
					}

					int d2 = (d + 1) % 4;
					nx = x + dx[d2];
					ny = y + dy[d2];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d2];
						ny += dy[d2];
					}
					dfs(i+ 1,total+sum);
					for (int k = 0; k < X; k++) {
						for (int l = 0; l < Y; l++) {
							looking[k][l] = copy[k][l];
						}
					}
				}
				break;
			case 4:
				for (int d = 0; d < 4; d++) {
					int sum = 0;
					nx = x + dx[d];
					ny = y + dy[d];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d];
						ny += dy[d];
					}

					int d2 = (d + 1) % 4;
					nx = x + dx[d2];
					ny = y + dy[d2];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d2];
						ny += dy[d2];
					}

					d2 = (d + 2) % 4;
					nx = x + dx[d2];
					ny = y + dy[d2];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d2];
						ny += dy[d2];
					}
					
					dfs(i+ 1,total+sum);
					for (int k = 0; k < X; k++) {
						for (int l = 0; l < Y; l++) {
							looking[k][l] = copy[k][l];
						}
					}
				}
				break;
			case 5:
				int sum = 0;
				for (int d = 0; d < 4; d++) {
					nx = x + dx[d];
					ny = y + dy[d];
					while (nx >= 0 && nx < X && ny >= 0 && ny < Y && map[nx][ny] != 6) {
						if(!looking[nx][ny]) {
							looking[nx][ny] = true;
							sum++;
						}
						nx += dx[d];
						ny += dy[d];
					}
				}
				dfs(i + 1, total+sum);
				for (int k = 0; k < X; k++) {
					for (int l = 0; l < Y; l++) {
						looking[k][l] = copy[k][l];
					}
				}
				break;
			}

		}
	}
}
