package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드
public class b11404 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // 도시(정점)
		int M = Integer.parseInt(br.readLine()); // 버스(간선)
		int[][] map = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				map[i][j] = -1;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 출발
			int b = Integer.parseInt(st.nextToken()); // 도착
			int c = Integer.parseInt(st.nextToken()); // 비용
			if(map[a][b]==-1) {
				map[a][b] = c;
			}else {
				map[a][b] = Math.min(map[a][b], c);
			}
			
		}	
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
		for (int n = 1; n <= N; n++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][n]==-1||map[n][j]==-1) {
						continue;
					}
					if(map[i][j]==-1||map[i][j]>map[i][n]+map[n][j]) {
						map[i][j]=map[i][n]+map[n][j];
					}
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j]==-1||i==j)
					map[i][j] = 0;
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}
