package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//마법사 상어와 복제
public class b23290_final {

	static ArrayDeque<Fish> fishList = new ArrayDeque<Fish>();
	static ArrayDeque<Fish> R = new ArrayDeque<Fish>();
	static int[][] fishCnt = new int[5][5];
	static int[][] smell = new int[5][5];
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] ddx = { -1, 0, 1, 0 };
	static int[] ddy = { 0, -1, 0, 1 };
	static int[] selected = new int[3];
	static int sx;
	static int sy;
	static boolean[][] visited;
	static PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1, o2) -> {
		if(o1[1]==o2[1]) {
			return Integer.compare(o1[0], o2[0]);
		}
		return Integer.compare(o1[1], o2[1])*(-1);
	});

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken()); // 물고기 수
		int N = Integer.parseInt(st.nextToken()); // n년 후

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fishList.add(new Fish(fx, fy, d));
			fishCnt[fx][fy]++;
		}

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());

		for (int n = 1; n <= N; n++) {
			for (Fish f : fishList) {
				// 1.물고기가 알을 낳는다
				R.add(new Fish(f.x, f.y, f.d));
				
				// 2.물고기가 이동을 한다.
				int cnt = 0;
				fishCnt[f.x][f.y]--;
				while (true) {
					if (cnt == 8) {
						break;
					}
					int nx = f.x + dx[f.d];
					int ny = f.y + dy[f.d];
					if (nx > 0 && nx <= 4 && ny > 0 && ny <= 4 && !(nx == sx && ny == sy) && smell[nx][ny] == 0) {
						f.x = nx;
						f.y = ny;
						break;
					} else {
						cnt++;
						f.d--;
						if (f.d == 0)
							f.d = 8;
					}
				}
				fishCnt[f.x][f.y]++;
			}

			// 3.오리가 움직인다.
			visited = new boolean[5][5];
			dfs(0, 0, sx, sy,0);
			int[] result = q.poll();
			int[] move = new int[3];
			move[0] = result[0]/100;
			move[1] = result[0]%100/10;
			move[2] = result[0]%10;
			
			boolean[][] passed = new boolean[5][5];
			for (int i = 0; i < 3; i++) {
				sx += ddx[move[i]];
				sy += ddy[move[i]];
				passed[sx][sy] = true;
				if(fishCnt[sx][sy]>0) {
					smell[sx][sy] = 3;
					fishCnt[sx][sy]=0;
				}
			}
			
			int size = fishList.size();
	        while (size-- > 0) { 
				Fish f = fishList.poll();
				if (passed[f.x][f.y]) {
					continue;
				}else {
					fishList.add(f);
				}
			}
			
			// 4.물고기 시체가 사라진다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					if (smell[i][j] > 0) {
						smell[i][j]--;
					}
				}
			}

			// 5.알이 부화한다.
			for (Fish r : R) {
				fishList.add(new Fish(r.x, r.y, r.d));
				fishCnt[r.x][r.y]++;
			}
			R.clear();
			q.clear();
		}

		int total = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				total += fishCnt[i][j];
			}
		}
		System.out.println(total);
	}

	static void dfs(int cnt, int fish, int x, int y, int move) {
		if (cnt == 3) {
			q.add(new int[] {move,fish});
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + ddx[i];
			int ny = y + ddy[i];
			if (nx > 0 && nx <= 4 && ny > 0 && ny <= 4) {
				int nm = i * (int)Math.pow(10, 2-cnt);
				if (visited[nx][ny]) {
					dfs(cnt + 1, fish, nx, ny,move+nm);
				} else {
					visited[nx][ny] = true;
					dfs(cnt + 1, fish + fishCnt[nx][ny], nx, ny,move+nm);
					visited[nx][ny] = false;
				}
			}
		}
	}

	static class Fish {
		int x;
		int y;
		int d;

		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
