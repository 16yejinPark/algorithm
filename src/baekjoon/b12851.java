package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//숨바꼭질 2
public class b12851 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//수빈 위치
		int K = Integer.parseInt(st.nextToken());	//동생 위치

		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->(Integer.compare(o1[1], o2[1])));
		q.add(new int[] {N,0});
		int visited[][] = new int[200001][2];
		for(int i=0;i<=200000;i++) {
			visited[i][0]=Integer.MAX_VALUE;
		}

		visited[N][0] = 0;
		visited[N][1] = 1;
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int loc = temp[0];
			int t = temp[1];
			
			if(visited[K][0]<t) {
				break;
			}
			
			if(loc-1>=0) {
				if(visited[loc-1][0]>t+1) {
					visited[loc-1][0] = t+1;
					visited[loc-1][1]+=visited[loc][1];
					q.add(new int[] {loc-1,t+1});
				}else if(visited[loc-1][0]==t+1) {
					visited[loc-1][1]+=visited[loc][1];
				}
				
			}
			
			if(loc+1<=200000 ) {
				if(visited[loc+1][0]>t+1) {
					visited[loc+1][0] = t+1;
					visited[loc+1][1]+=visited[loc][1];
					q.add(new int[] {loc+1,t+1});
				}else if(visited[loc+1][0]==t+1) {
					visited[loc+1][1]+=visited[loc][1];
				}
			}
			
			if(loc*2<=200000) {
				if(visited[loc*2][0]>t+1) {
					visited[loc*2][0] = t+1;
					visited[loc*2][1]+=visited[loc][1];
					q.add(new int[] {loc*2,t+1});
				}else if(visited[loc*2][0]==t+1) {
					visited[loc*2][1]+=visited[loc][1];
				}
			}
		}
		
		System.out.println(visited[K][0]);
		System.out.println(visited[K][1]);
		
	}

}
