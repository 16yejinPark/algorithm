package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//온풍기 안녕!
public class b23289 {

	static int dx[] = { 0, 0, 0, -1, 1 };
	static int dy[] = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 도달해야하는 온도

		//heater, checkPoint input
		ArrayList<int[]> checkList = new ArrayList<int[]>();
		ArrayList<int[]> heaterList = new ArrayList<>();
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 5) {
					checkList.add(new int[] { i, j });
				} else if (temp < 5 && temp > 0) {
					heaterList.add(new int[] { i, j, temp });
				}
			}
		}

		//벽 input
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		boolean[][][] walls = new boolean[R + 1][C + 1][2];
		for (int i = 1; i <= W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			walls[x][y][t] = true;
		}

		
		int map[][] = new int[R + 1][C + 1];
		int choco = 0;
		boolean fulfillTemp = false;
		//조건을 도달못하고 초콜릿이 100이하면 반복
		while (!fulfillTemp&&choco<=100) {
			// 집에 있는 모든 온풍기에서 바람이 한 번 나옴
			for (int i = 0; i < heaterList.size(); i++) {
				int[] temp = heaterList.get(i);
				int fx = temp[0];
				int fy = temp[1];
				int t = temp[2];

				fx += dx[t];
				fy += dy[t];

				boolean visited[][] = new boolean[R + 1][C + 1];
				Queue<int[]> q = new LinkedList<int[]>();
				q.add(new int[] { fx, fy, 5 });
				map[fx][fy] += 5;
				visited[fx][fy] = true;
				while (!q.isEmpty()) {
					int x = q.peek()[0];
					int y = q.peek()[1];
					int n = q.peek()[2];
					q.remove();

					int nx = x + dx[t];
					int ny = y + dy[t];
					switch (t) {
					case 1:
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[x][y][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						nx -= 1;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[x][y][0] && !walls[x - 1][y][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						nx += 2;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[x + 1][y][0] && !walls[x + 1][y][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}
						break;

					case 2:
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx][ny][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						nx -= 1;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {

							// 벽이 있는지 확인
							if (!walls[nx][ny][1] && !walls[x][y][0]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						nx += 2;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx][ny + 1][0] && !walls[nx][ny][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}
						break;
					case 3:
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[x][y][0]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						ny -= 1;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx + 1][ny][1] && !walls[nx + 1][ny][0]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						ny += 2;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx + 1][ny][0] && !walls[nx + 1][ny - 1][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}
						break;

					case 4:
						if (isRange(nx, ny, R, C) && ny <= C && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx][ny][0]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						ny -= 1;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx][ny][0] && !walls[nx - 1][ny][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}

						ny += 2;
						if (isRange(nx, ny, R, C) && !visited[nx][ny]) {
							// 벽이 있는지 확인
							if (!walls[nx][ny][0] && !walls[x][y][1]) {
								visited[nx][ny] = true;
								if (n - 1 == 1) {
									map[nx][ny] += n - 1;
								} else {
									map[nx][ny] += n - 1;
									q.add(new int[] { nx, ny, n - 1 });
								}
							}
						}
						break;
					}
				}
			}		
			
			// 온도가 조절됨
			int[][] copy = new int[R + 1][C + 1];
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {


					int nx = i + dx[1];
					int ny = j + dy[1];
					int remain = map[i][j];
					
					if (isRange(nx, ny, R, C) && map[nx][ny] < map[i][j] && !walls[i][j][1]) {
						int temp = (map[i][j] - map[nx][ny]) / 4;
						copy[nx][ny] += temp;
						remain -= temp;
					}

					nx = i + dx[2];
					ny = j + dy[2];
					if (isRange(nx, ny, R, C) && map[nx][ny] < map[i][j] && !walls[nx][ny][1]) {
						int temp = (map[i][j] - map[nx][ny]) / 4;
						copy[nx][ny] += temp;
						remain -= temp;
					}

					nx = i + dx[3];
					ny = j + dy[3];
					if (isRange(nx, ny, R, C) && map[nx][ny] < map[i][j] && !walls[i][j][0]) {
						int temp = (map[i][j] - map[nx][ny]) / 4;
						copy[nx][ny] += temp;
						remain -= temp;
					}

					nx = i + dx[4];
					ny = j + dy[4];
					if (isRange(nx, ny, R, C) && map[nx][ny] < map[i][j] && !walls[nx][ny][0]) {
						int temp = (map[i][j] - map[nx][ny]) / 4;
						copy[nx][ny] += temp;
						remain -= temp;
					}
					copy[i][j] += remain;
				}
			}
			map = copy;

			// 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if ((i == 1 || j == 1 || i == R || j == C) && map[i][j] > 0)
						map[i][j]--;
				}
			}

			// 초콜릿을 하나 먹는다.
			choco++;

			// 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
			fulfillTemp = true;
			for(int i=0;i<checkList.size();i++) {
				int[] point = checkList.get(i);	//{x,y}
				if(map[point[0]][point[1]]<K) {
					fulfillTemp = false;
					break;
				}
			}
		}
		System.out.println(choco);
	}

	static boolean isRange(int nx, int ny, int R, int C) {
		if (nx > 0 && nx <= R && ny > 0 && ny <= C)
			return true;
		return false;
	}
	
	static void print(int[][] map, int R, int C) {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}System.out.println();
	}
}
