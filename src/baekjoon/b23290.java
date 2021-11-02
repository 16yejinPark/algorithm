package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 복제
public class b23290 {
	private static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	private static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	private static int maxEatFish;
	private static int moveWeight;
	private static int move;
	private static int sx;
	private static int sy;
	private static boolean[][] visited;
	private static Area[][] map = new Area[5][5];

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
									&& !(sx == ni && sy == nj) && map[ni][nj].deadFishes.size() == 0) {
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
			moveWeight = Integer.MAX_VALUE;
			dfs(sx, sy, 0, 0, 0);
			int d = getDist(move / 100);
			moveDuck(d);

			move %= 100;
			d = getDist(move / 10);
			moveDuck(d);

			d = getDist(move % 10);
			moveDuck(d);

			// 4. 2년된 물고기 시체가 없어진다.
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					for (int r = 0; r < map[i][j].deadFishes.size(); r++) {
						if (map[i][j].deadFishes.get(r).year == 2) {
							map[i][j].deadFishes.remove(r);
							r--;
						}
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
					for (int r = 0; r < map[i][j].deadFishes.size(); r++) {
						map[i][j].deadFishes.get(r).year++;
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
				if (moveWeight > w) {
					moveWeight = w;
					move = w;
				}
			}
			return;
		} else {
			for (int d = 1; d < 8; d += 2) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx > 0 && nx <= 4 && ny > 0 && ny <= 4) {
					int nw = 0;
					if (d == 3) {
						nw = (int) (Math.pow(10, 2 - cnt) * 1);
					} else if (d == 1) {
						nw = (int) (Math.pow(10, 2 - cnt) * 2);
					} else if (d == 7) {
						nw = (int) (Math.pow(10, 2 - cnt) * 3);
					} else if (d == 5) {
						nw = (int) (Math.pow(10, 2 - cnt) * 4);
					}

					ArrayList<Fish> temp = new ArrayList<Fish>();
					for (int i = 0; i < map[nx][ny].liveFishes.size(); i++) {
						temp.add(map[nx][ny].liveFishes.get(i));
					}

					int f = map[nx][ny].liveFishes.size();
					map[nx][ny].liveFishes.clear();
					dfs(nx, ny, cnt + 1, eatFish + f, w + nw);
					map[nx][ny].liveFishes = temp;
				}
			}
		}
	}

	static int getDist(int d) {
		int nw = 0;
		if (d == 1) {
			nw = 3;
		} else if (d == 2) {
			nw = 1;
		} else if (d == 3) {
			nw = 7;
		} else if (d == 4) {
			nw = 5;
		}
		return nw;
	}

	static void moveDuck(int d) {
		sx += dx[d];
		sy += dy[d];
		for (int r = 0; r < map[sx][sy].liveFishes.size(); r++) {
			map[sx][sy].deadFishes.add(map[sx][sy].liveFishes.remove(0));
			r--;
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
		ArrayList<Fish> deadFishes;
		ArrayList<Fish> liveFishes;
		ArrayList<Fish> R;

		public Area() {
			deadFishes = new ArrayList<Fish>();
			liveFishes = new ArrayList<Fish>();
			R = new ArrayList<Fish>();
		}
	}
}
