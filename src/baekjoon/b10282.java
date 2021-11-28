package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
			int n = Integer.parseInt(st.nextToken());	//컴퓨터 개수
			int d = Integer.parseInt(st.nextToken());	//의존성 개수
			int c = Integer.parseInt(st.nextToken());	//해킹당한 컴퓨터의 번호
			
			ArrayList<ArrayList<int[]>> dp = new ArrayList<>();
			for(int i=0;i<=n;i++) {
				dp.add(new ArrayList<int[]>());
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());	
				int b = Integer.parseInt(st.nextToken());	
				int s = Integer.parseInt(st.nextToken());	//b->a(w=s)
				dp.get(b).add(new int[] {a, s});
			}
			
			int totalT = 0;
			int totalN = 1;
			boolean visited[] = new boolean[n+1];
			int minDist[] = new int[n+1];
			PriorityQueue<int[]> q = new PriorityQueue<>();
			q.add(new int[] {c,0});
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int start = temp[0];
				int dist = temp[1];
				
				//boolean[]
				
				for(int[] next : dp.get(start)) {
					q.add(new int[] {next[0],next[1]});
				}
				
			}
			System.out.printf("%d %d\n",totalN,totalT);
		}
	}
}
