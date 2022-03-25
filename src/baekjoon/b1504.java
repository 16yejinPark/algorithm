package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//특정한 최단 경로
//정점 두 개가 시작점이거나 라스트 정점일 경우를 고려하지 않아 틀림 ㅠ
public class b1504 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 수
		int E = Integer.parseInt(st.nextToken()); // 간선의 수

		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<int[]>());
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); // 간선의 가중치
			graph.get(A).add(new int[] { B, L });
			graph.get(B).add(new int[] { A, L });
		}

		// 반드시 방문해야하는 정점
		st = new StringTokenizer(br.readLine());
		int V1 = Integer.parseInt(st.nextToken());
		int V2 = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		int startDist[] = new int[N + 1];
		Arrays.fill(startDist, Integer.MAX_VALUE);
		q.add(new int[] { 1, 0 });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int n = temp[0];
			int w = temp[1];

			if (startDist[n] < w) {
				continue;
			}

			for (int i = 0; i < graph.get(n).size(); i++) {
				int[] next = graph.get(n).get(i);
				int nn = next[0];
				int nw = next[1];
				if (startDist[nn] > w + nw) {
					startDist[nn] = w + nw;
					q.add(new int[] { nn, startDist[nn] });
				}
			}
		}

		int finDist[] = new int[N + 1];
		Arrays.fill(finDist, Integer.MAX_VALUE);
		q.add(new int[] { N, 0 });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int n = temp[0];
			int w = temp[1];

			if (finDist[n] < w) {
				continue;
			}

			for (int i = 0; i < graph.get(n).size(); i++) {
				int[] next = graph.get(n).get(i);
				int nn = next[0];
				int nw = next[1];
				if (finDist[nn] > w + nw) {
					finDist[nn] = w + nw;
					q.add(new int[] { nn, finDist[nn] });
				}
			}
		}

		int vDist[] = new int[N + 1];
		Arrays.fill(vDist, Integer.MAX_VALUE);
		q.add(new int[] { V1, 0 });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int n = temp[0];
			int w = temp[1];

			if (vDist[n] < w) {
				continue;
			}

			for (int i = 0; i < graph.get(n).size(); i++) {
				int[] next = graph.get(n).get(i);
				int nn = next[0];
				int nw = next[1];
				if (vDist[nn] > w + nw) {
					vDist[nn] = w + nw;
					q.add(new int[] { nn, vDist[nn] });
				}
			}
		}

		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;
		if (1 == V1 || 1 == V2) { // 정점에 시작점 포함
			if (1 == V1) {
				if (N == V2) {
					if (startDist[N] != Integer.MAX_VALUE) {
						a = startDist[N];
					}
				} else {
					if (startDist[V2] != Integer.MAX_VALUE && finDist[V2] != Integer.MAX_VALUE) {
						a = startDist[V2] + finDist[V2];
					}
				}
			} else {
				if (N == V1) {
					if (startDist[N] != Integer.MAX_VALUE) {
						a = startDist[N];
					}
				} else {
					if (startDist[V1] != Integer.MAX_VALUE && finDist[V1] != Integer.MAX_VALUE) {
						a = startDist[V1] + finDist[V1];
					}
				}
			}
		} else if (N == V1 || N == V2) { // 정점에 끝점만 포함
			if (N == V1) {
				if (startDist[V2] != Integer.MAX_VALUE && finDist[V2] != Integer.MAX_VALUE) {
					a = startDist[V2] + finDist[V2];
				}
			} else {
				if (startDist[V1] != Integer.MAX_VALUE && finDist[V1] != Integer.MAX_VALUE) {
					a = startDist[V1] + finDist[V1];
				}
			}
		} else {
			if (startDist[V1] != Integer.MAX_VALUE && vDist[V2] != Integer.MAX_VALUE
					&& finDist[V2] != Integer.MAX_VALUE) {
				a = startDist[V1] + vDist[V2] + finDist[V2];
			}
			if (startDist[V2] != Integer.MAX_VALUE && vDist[V2] != Integer.MAX_VALUE
					&& finDist[V1] != Integer.MAX_VALUE) {
				b = startDist[V2] + vDist[V2] + finDist[V1];
			}
		}

		int answer = Math.min(a, b);
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}
}
