package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//맥주마시면서 걸어가기
public class b9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 편의점의 개수
			int[][] point = new int[N + 2][2];
			st = new StringTokenizer(br.readLine());
			point[0][0] = Integer.parseInt(st.nextToken());
			point[0][1] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			point[N + 1][0] = Integer.parseInt(st.nextToken());
			point[N + 1][1] = Integer.parseInt(st.nextToken());

			System.out.println((bfs(N,point)?"happy":"sad"));
		}
	}

	static int getDist(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
	
	static boolean bfs(int N, int[][] point) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[][] visited = new boolean[N+2][N+2];
		q.add(0);
		while(!q.isEmpty()) {
			int n = q.remove();
			if(n==N+1) {
				return true;
			}
			for(int i=0;i<N+2;i++) {
				if(n!=i&&getDist(point[n],point[i])<=1000&&!visited[n][i]) {
					visited[n][i]=true;
					visited[i][n]=true;
					q.add(i);
				}
			}
		}
		return false;
	}
}
