package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//숨바꼭질3
public class b13549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int memo[] = new int[200001];
		
		for(int i=0;i<=200000;i++) {
			memo[i] = Integer.MAX_VALUE;
		}
		memo[N]=0;

		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->{return Integer.compare(o1[1], o2[1]);});
		q.add(new int[] {N,0});
		while(!q.isEmpty()) {
			int[] point = q.poll();
			int p = point[0];
			int time = point[1];
			if(memo[p]<time)continue;
			if(p==K) {
				break;
			}
			if(p-1>=0&&memo[p-1]>time+1) {
				memo[p-1]=time+1;
				q.add(new int[] {p-1,time+1});
			}		
			if(p+1<=200000&&memo[p+1]>time+1){
				memo[p+1]=time+1;
				q.add(new int[] {p+1,time+1});
			}	
			if(p*2<=200000&&memo[p*2]>time){
				memo[p*2]=time;
				q.add(new int[] {p*2,time});
			}	
		}
		System.out.println(memo[K]);
	}
}
