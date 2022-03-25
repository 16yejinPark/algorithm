package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//아기상어

public class b16236 {
	public static int N;
	public static int[][] map;
	public static int[] dx = { -1, 0, 0, 1 };// 상, 좌, 우, 하
	public static int[] dy = { 0, -1, 1, 0 };// 상, 좌, 우, 하

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Fish shark = null;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] == 9) {
					shark = new Fish(n, m, 2);
					map[n][m] = 0;
				}
			}
		}

		System.out.println(bfs(shark));
	}

	private static int bfs(Fish shark) {
		while (true) {
			Queue<Integer[]> q = new LinkedList<>();
			int[][] visited = new int[N][N];
			List<Fish> fishList = new ArrayList<>();
			q.add(new Integer[] { shark.x, shark.y });
			boolean findFish = false;
			while (!q.isEmpty()) {
				Integer[] point = q.remove();
				int x = point[0];
				int y = point[1];
				// System.out.printf("%d %d\n",x,y);
				if (map[x][y] < shark.size && map[x][y] != 0) {
					Fish f = new Fish(x, y);
					f.dist = visited[x][y];
					fishList.add(f);
					findFish = true;
				}
				if (!findFish) {
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] <= shark.size
								&& visited[nx][ny] == 0) {
							visited[nx][ny] = visited[x][y] + 1;
							q.add(new Integer[] { nx, ny });
						}
					}
				}
			}

			if (fishList.size() == 0)
				return shark.time;
			else {
				Collections.sort(fishList);
				Fish f = fishList.remove(0);
				map[f.x][f.y] = 0;
				shark.cnt++;
				if (shark.size == shark.cnt) {
					shark.cnt = 0;
					shark.size++;
				}
				shark.x = f.x;
				shark.y = f.y;
				shark.time += visited[f.x][f.y];
				//System.out.printf("##%d %d %d\n", f.x, f.y, shark.time);
			}

		}
	}
}

class Fish implements Comparable<Fish> {
	public int size;
	public int x;
	public int y;
	public int cnt;
	public int time;
	public int dist;

	public Fish() {
	}

	public Fish(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Fish(int x, int y, int size) {
		this.cnt = 0;
		this.time = 0;
		this.size = size;
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Fish o) {
		if (this.dist == o.dist) {
			if (this.x == o.x) {
				return this.y - o.y;
			} else
				return this.x - o.x;
		} else
			return this.dist - o.dist;
	}
}