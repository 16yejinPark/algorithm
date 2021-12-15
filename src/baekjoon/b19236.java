package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//청소년 상어
public class b19236 {
	static int maxValue = 0;
	static HashMap<Integer, int[]> fishList = new HashMap<>();
	static int map[][];
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		map = new int[4][4];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken()); // 물고기 번호
				int dir = Integer.parseInt(st.nextToken()); // 방향
				map[i][j] = num;
				fishList.put(num, new int[] { dir, i, j });
			}
		}

		maxValue += map[0][0];
		int[] sharkInfo = fishList.get(map[0][0]);
		fishList.put(0, sharkInfo);
		fishList.remove(map[0][0]);
		map[0][0] = 0;

		dfs(maxValue);
		System.out.println(maxValue);
	}

	static void moveFish(int sx, int sy) {

		for (int n = 1; n <= 16; n++) {
			if (fishList.containsKey(n)) {
				int[] temp = fishList.get(n);
				;
				int d = temp[0];
				int x = temp[1];
				int y = temp[2];

				// 방향 설정
				int nx = 0;
				int ny = 0;
				int nn = 0;
				boolean findDir = false;
				for (int i = 1; i <= 8; i++) {
					nx = x + dx[d];
					ny = y + dy[d];
					if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !(nx == sx && ny == sy)) {
						findDir = true;
						nn = map[nx][ny];
						break;
					} else {
						d++;
						if (d == 9)
							d = 1;
					}
				}

				if (findDir) {
					// map 바꾸기
					map[x][y] = nn;
					map[nx][ny] = n;

					// hashmap 바꾸기
					int[] movingFish = fishList.get(n);
					int[] movedFish = fishList.get(nn);
					movingFish[0] = movedFish[0];
					movedFish[0] = d;
					fishList.replace(n, movedFish);
					fishList.replace(nn, movingFish);
				}

//				System.out.printf("#%d (d=%d)\n",n,d);
//				for(int i=0;i<4;i++) {
//					for(int j=0;j<4;j++) {
//						System.out.printf("%2d ",map[i][j]);
//					}System.out.println();
//				}System.out.println();
			}
		}
	}

	static void dfs(int v) {
		
		System.out.print(maxValue+" >> ");
		
		int sd = fishList.get(0)[0];
		int sx = fishList.get(0)[1];
		int sy = fishList.get(0)[2];

		System.out.printf("%d %d %d\n",sd,sx,sy);
		for (int r = 1; r <= 4; r++) {
			// 물고기 이동
			moveFish(sx, sy);
			// 상어이동
			int nx = sx + (dx[sd] * r);
			int ny = sy + (dy[sd] * r);
			if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
				if(map[nx][ny]!=0) {
					int[] sharkInfo = fishList.get(0);
					int victim = map[nx][ny];
					int[] victimInfo = fishList.get(victim);
					fishList.replace(0, new int[] {victimInfo[0],nx,ny});
					fishList.remove(victim);
					map[nx][ny] = 0;
					dfs(v+victim);
					map[nx][ny] = victim;
					fishList.replace(0, sharkInfo);
					fishList.put(victim, victimInfo);
					
				}
			} else {
				maxValue = Math.max(maxValue, v);
				break;
			}
		}
	}
}
