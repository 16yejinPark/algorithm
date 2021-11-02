package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//웜홀
public class b1865 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 정점의 수
			int M = Integer.parseInt(st.nextToken()); // 간선의 수
			int W = Integer.parseInt(st.nextToken()); // 웜홀의 수

			ArrayList<ArrayList<int[]>> adjList = new ArrayList<ArrayList<int[]>>();
			for (int i = 0; i <= N; i++) {
				adjList.add(new ArrayList<int[]>());
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				adjList.get(S).add(new int[] { E, T });
				adjList.get(E).add(new int[] { S, T });
			}

			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				adjList.get(S).add(new int[] { E, T * (-1) });
			}

			boolean possible = false;
			int[] minDist = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				minDist[i] = 30000000;
			}

			for (int j = 1; j < N; j++)
				for (int i = 1; i <= N; i++) {
					for (int[] node : adjList.get(i)) {
						int n = node[0];
						int t = node[1];
						if (minDist[n] > minDist[i] + t) {
							minDist[n] = minDist[i] + t;
						}
					}
				}

			findAnswer:for (int i = 1; i <= N; i++) {
				for (int[] node : adjList.get(i)) {
					int n = node[0];
					int t = node[1];
					if (minDist[n] > minDist[i] + t) {
						possible = true;
						break findAnswer;
					}
				}
			}

			System.out.println(possible ? "YES" : "NO");
		}
	}
}
