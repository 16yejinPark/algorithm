package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//서강그라운드
public class b14938 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//정점의 수
		int M = Integer.parseInt(st.nextToken());	//수색 범위
		int R = Integer.parseInt(st.nextToken());	//간선의 수
		
		int[] items = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<int[]>());
		}
		
		for(int i=1;i<=R;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	
			int B = Integer.parseInt(st.nextToken());	
			int L = Integer.parseInt(st.nextToken());	//간선의 가중치
			graph.get(A).add(new int[] {B,L});
			graph.get(B).add(new int[] {A,L});
		}
		
		int[] dist = new int[N+1];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[1], o2[1]));
		
		int answer = 0;
		for(int i=1;i<=N;i++) {
			q.clear();
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[i] = 0;
			q.add(new int[] {i,0});
			
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int n = temp[0];
				int w = temp[1];
				
				if(dist[n]<w) {
					continue;
				}
				
				if(w>M) {
					continue;
				}
				
				for(int j=0;j<graph.get(n).size();j++) {
					int[] next = graph.get(n).get(j);
					int nn = next[0];
					int nw = next[1];
					if(dist[nn] > w + nw) {
						dist[nn] = w + nw;
						q.add(new int[] {nn,dist[nn]});
					}
				}
			}
			
			int cnt = 0;
			//System.out.printf("%d -> ",i);
			for(int j=1;j<=N;j++) {
				if(dist[j]<=M) {
					//System.out.printf("%d(j=%d,dist[%d]=%d) ",items[j],j,j,dist[j]);
					cnt += items[j];
				}
			}
			//System.out.printf("= %d\n",cnt);
			answer = Math.max(cnt, answer);
		}
		
		System.out.println(answer);
	}

}
