package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//해킹
public class b10282 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//컴퓨터 개수
			int D = Integer.parseInt(st.nextToken());	//의존성 개수
			int C = Integer.parseInt(st.nextToken());	//해킹당한 컴퓨터의 번호
			
			ArrayList<ArrayList<int[]>> dp = new ArrayList<>();
			for(int i=0;i<=N;i++) {
				dp.add(new ArrayList<int[]>());
			}
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());	
				int b = Integer.parseInt(st.nextToken());	
				int s = Integer.parseInt(st.nextToken());	//b->a(w=s)
				dp.get(b).add(new int[] {a, s});
			}
			
			int[] dist = new int[N+1];
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[1], o2[1]));
			q.add(new int[] {C,0});
			dist[C] = 0;
			
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int n = temp[0];
				int w = temp[1];
				if(dist[n]<w) {
					continue;
				}
				for(int i=0;i<dp.get(n).size();i++) {
					int[] next = dp.get(n).get(i);
					int nn = next[0];
					int nw = next[1];
					if(dist[nn] > w + nw) {
						dist[nn] = w + nw;
						q.add(new int[] {nn, dist[nn]});
					}
				}
			}
			
			int maxDist = 0;
			int cnt = 0;
			for(int i=1;i<=N;i++) {
				if(dist[i]!=Integer.MAX_VALUE) {
					cnt++;
					maxDist = Math.max(maxDist, dist[i]);
				}
			}
			System.out.printf("%d %d\n",cnt,maxDist);
		}
	}
}
