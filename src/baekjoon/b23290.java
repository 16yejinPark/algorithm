package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//마법사 상어와 복제
//시간 초과 풀이
public class b23290 {
	private static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	private static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int maxEatFish;
	private static int move;
	private static int sx;
	private static int sy;
	private static boolean[][] visited;
	private static Area[][] map = new Area[5][5];
	private static int[][] smell = new int[5][5]; 
	static HashMap<Integer,Integer> getDistR = new HashMap<Integer,Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 물고기 수
		int S = Integer.parseInt(st.nextToken()); // 상어가 마법을 연습한 횟수

		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				map[i][j] = new Area();
			}
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[x][y].liveFishes.add(new Fish(d));
		}

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		
		HashMap<Integer,Integer> getDist = new HashMap<Integer,Integer>();
		getDist.put(1, 3);
		getDist.put(2, 1);
		getDist.put(3, 7);
		getDist.put(4, 5);
		
		getDistR.put(3, 1);
		getDistR.put(1, 2);
		getDistR.put(7, 3);
		getDistR.put(5, 4);
		
		for (int s = 1; s <= S; s++) {

			// 1. 물고기가 알을 낳는다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					for (int r = 0; r < map[i][j].liveFishes.size(); r++) {
						int d = map[i][j].liveFishes.get(r).d;
						map[i][j].R.add(new Fish(d));
					}
				}
			}

			// 2. 물고기가 이동을 한다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					for (int r = 0; r < map[i][j].liveFishes.size(); r++) {

						int cnt = 0;
						while (true) {
							if (cnt == 8) {
								map[i][j].liveFishes.get(r).moved = true;
								break;
							}
							int ni = i + dx[map[i][j].liveFishes.get(r).d];
							int nj = j + dy[map[i][j].liveFishes.get(r).d];

							if (ni > 0 && ni <= 4 && nj > 0 && nj <= 4 && !map[i][j].liveFishes.get(r).moved
									&& !(sx == ni && sy == nj) && smell[ni][nj] == 0) {
								map[ni][nj].liveFishes.add(map[i][j].liveFishes.remove(r));
								map[ni][nj].liveFishes.get(map[ni][nj].liveFishes.size() - 1).moved = true;
								r--;
								break;
							} else {
								map[i][j].liveFishes.get(r).d--;
								if (map[i][j].liveFishes.get(r).d <= 0) {
									map[i][j].liveFishes.get(r).d += 8;
								}
								cnt++;
							}
						}
					}
				}
			}

			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					for (int r = 0; r < map[i][j].liveFishes.size(); r++) {
						map[i][j].liveFishes.get(r).moved = false;
					}
				}
			}

			// 3. 오리가 움직인다.
			maxEatFish = 0;
			move = Integer.MAX_VALUE;
			visited = new boolean[5][5];
			dfs(sx, sy, 0, 0, 0);
			int d = getDist.get(move / 100);
			moveDuck(d);

			move %= 100;
			d = getDist.get(move / 10);
			moveDuck(d);

			d = getDist.get(move % 10);
			moveDuck(d);

			// 4. 2년된 물고기 시체가 없어진다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					if(smell[i][j]>0) {
						smell[i][j]--;
					}
				}
			}

			// 5. 알이 부화한다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					for (int r = 0; r < map[i][j].R.size(); r++) {
						map[i][j].liveFishes.add(map[i][j].R.remove(r));
						r--;
					}
				}
			}
		}

		int total = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				total += map[i][j].liveFishes.size();
			}
		}
		System.out.println(total);
	}

	static void dfs(int x, int y, int cnt, int eatFish, int w) {
		if (cnt == 3) {
			if (maxEatFish < eatFish) {
				maxEatFish = eatFish;
				move = w;
			} else if (maxEatFish == eatFish) {
				if (move > w) {
					move = w;
				}
			}
			return;
		} else {
			for (int d = 1; d < 8; d += 2) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx > 0 && nx <= 4 && ny > 0 && ny <= 4) {
					int nw = (int) (Math.pow(10, 2 - cnt) * getDistR.get(d));
					if (visited[nx][ny]) {
						dfs(nx, ny, cnt + 1, eatFish, w + nw);
					} else {
						int f = map[nx][ny].liveFishes.size();
						visited[nx][ny] = true;
						dfs(nx, ny, cnt + 1, eatFish + f, w + nw);
						visited[nx][ny] = false;
					}
				}
			}
		}
	}

	static void moveDuck(int d) {
		sx += dx[d];
		sy += dy[d];
		if(map[sx][sy].liveFishes.size()>0) {
			map[sx][sy].liveFishes.clear();
			smell[sx][sy] = 3;
		}
	}

	static class Fish {
		int d;
		int year = 0;
		boolean moved = false;

		public Fish(int d) {
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fish [d=" + d + ", year=" + year + ", moved=" + moved + "]";
		}
	}

	static class Area {
		ArrayList<Fish> liveFishes;
		ArrayList<Fish> R;

		public Area() {
			liveFishes = new ArrayList<Fish>();
			R = new ArrayList<Fish>();
		}
	}
}