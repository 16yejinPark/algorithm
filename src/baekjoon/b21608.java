package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class b21608 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());

		int classRoom[][] = new int[N][N];
		int blank[][] = new int[N][N];
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						blank[i][j]++;
					}
				}
			}
		}
		int[][] list = new int[(int) Math.pow(N, 2)+1][4];
		for (int i = 0; i < Math.pow(N, 2); i++) {
			st = new StringTokenizer(br.readLine());
			int std = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 4; j++) {
				list[std][j] = Integer.parseInt(st.nextToken());
			}

			int[][] hubo = new int[N][N];
			int max = 0;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (classRoom[x][y] != 0) {
						for (int j = 0; j < 4; j++) {
							if (list[std][j] == classRoom[x][y]) {
								for (int d = 0; d < 4; d++) {
									int nx = x + dx[d];
									int ny = y + dy[d];
									if (nx >= 0 && nx < N && ny >= 0 && ny < N&&classRoom[nx][ny]==0) {
										hubo[nx][ny]++;
										max = Math.max(max, hubo[nx][ny]);
									}
								}
							}
						}
					}
				}
			}



			ArrayList<int[]> huboList = new ArrayList<int[]>();
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if ((hubo[x][y] == max)&&classRoom[x][y]==0) {
						huboList.add(new int[] { x, y });
					}
				}
			}
			
			Collections.sort(huboList, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(blank[o1[0]][o1[1]]!=blank[o2[0]][o2[1]]) {
						return Integer.compare(blank[o1[0]][o1[1]], blank[o2[0]][o2[1]])*(-1);
					}
					if(o1[0]!=o2[0]) {
						return Integer.compare(o1[0],o2[0]);
					}
					return Integer.compare(o1[1],o2[1]);
				}
			});;
			
			int[] index = huboList.get(0);
			int x = index[0];
			int y = index[1];
			blank[x][y]=0;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					blank[nx][ny]--;
				}
			}
			classRoom[x][y] = std;

		}
		
		int score = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int std = classRoom[i][j];
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
						for(int stds : list[std]) {
							if(classRoom[nx][ny]==stds) {
								cnt++;
							}
						}
					}
				}
				switch(cnt) {
				case 0: score+=0;break;
				case 1: score+=1;break;
				case 2: score+=10;break;
				case 3: score+=100;break;
				case 4: score+=1000;break;
				}
			}
		}
		System.out.println(score);
	}
}
