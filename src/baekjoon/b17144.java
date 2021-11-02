package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//미세먼지 안녕!
public class b17144 {
	static int[][] room;
	static int R;
	static int C;
	static int[] dx = { 1, 0, -1, 0 }; // 하.우,상,좌
	static int[] dy = { 0, 1, 0, -1 };
	static int airCleaner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		boolean findAirCleaner = false;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if (room[r][c] == -1 && !findAirCleaner) {
					airCleaner = r;
				}
			}
		}
		for(int t=0;t<T;t++) {
			moveDust();
			runAirCleaner();
		}
		
		int sum=0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(room[r][c]!=-1)
					sum+=room[r][c];
			}
		}
		System.out.println(sum);
	}

	public static void runAirCleaner() {
		int x = 0;
		int y = 0;
		int[][] tempRoom = new int[R][C];
		boolean findAirCleaner = false;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tempRoom[r][c]=-1;
			}
		}	

		// 반시계 방향
		for (int d = 0; d < 4; d++) {
			while (true) {
				// System.out.printf("%d %d \n",x,y);
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (findAirCleaner) {
					findAirCleaner = false;
					break;
				}
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					break;
				}
				if (room[nx][ny] == -1) {
					x = nx;
					y = ny;
					findAirCleaner = true;
					continue;
				}

				if(room[x][y]==-1)
					tempRoom[nx][ny] = 0;
				else
					tempRoom[nx][ny] = room[x][y];
				x = nx;
				y = ny;
			}
		}

		// 시계방향
		x = R - 1;
		y = C - 1;
		for (int d = 3; d >= 0; d--) {
			while (true) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (findAirCleaner) {
					findAirCleaner = false;
					break;
				}
				if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
					break;
				}
				if (room[nx][ny] == -1) {
					x = nx;
					y = ny;
					findAirCleaner = true;
					continue;
				}
				if(room[x][y]==-1)
					tempRoom[nx][ny] = 0;
				else
					tempRoom[nx][ny] = room[x][y];
				x = nx;
				y = ny;

			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(room[r][c]==-1||tempRoom[r][c]==-1)
					continue;
				room[r][c]=tempRoom[r][c];
			}
		}
	}

	public static void moveDust() {
		int[][] tempRoom = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(room[r][c]==-1)
					continue;
				int dust = room[r][c] / 5;
				int cnt=0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dx[d];
					int nc = c + dy[d];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1) {
						tempRoom[nr][nc] += dust;
						cnt++;
					}
				}
				room[r][c]-=dust*cnt;
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				room[r][c] += tempRoom[r][c];
			}
		}
	}
}
