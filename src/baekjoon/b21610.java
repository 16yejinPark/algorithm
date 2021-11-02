package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 비바라기
public class b21610 {
	static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int map[][] = new int[N+1][N+1];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= N; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<int[]> cloud = new ArrayList<int[]>();
		cloud.add(new int[] {N,1});
		cloud.add(new int[] {N,2});
		cloud.add(new int[] {N-1,1});
		cloud.add(new int[] {N-1,2});

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			boolean[][] nowCloud = new boolean[N+1][N+1];
			for(int[] c : cloud) {
				int i = c[0];
				int j = c[1];
				int moveX = (dx[D] * S) % N;
				int moveY = (dy[D] * S) % N;
				int ni = i + moveX;
				if (ni < 1) {
					ni += N;
				} else if (ni > N) {
					ni -= N;
				}
				int nj = j + moveY;
				if (nj < 1) {
					nj += N;
				} else if (nj > N) {
					nj -= N;
				}
				map[ni][nj]++;
				nowCloud[ni][nj]=true;
			}
			
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(nowCloud[i][j]) {
						int cnt = 0;
						for(int d=2;d<=8;d+=2) {
							int ni = i + dx[d];
							int nj = j + dy[d];
							if(ni>0&&ni<=N&&nj>0&&nj<=N&&map[ni][nj]>0) {
								cnt++;
							}
						}
						map[i][j]+=cnt;
					}
				}
			}
			
			
			cloud.clear();
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					if(map[i][j]>=2&&!nowCloud[i][j]) {
						map[i][j]-=2;
						cloud.add(new int[] {i,j});
					}
				}
			}
		}

		int sum = 0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}

}
