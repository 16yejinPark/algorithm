package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//숨바꼭질4
//StringBuilder에 insert써서 시간초과남
public class b13913 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken());	//동생이 있는 위치
		boolean[] visited = new boolean[100001];
		int[] trace = new int[100001];
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {N,0});
		
		int answer = 0;
		if(N==K) {
			System.out.println(answer);	
			System.out.println(N);	
		}else {
			while(!q.isEmpty()) {
				int[] temp = q.poll();
				int loc = temp[0];
				int n = temp[1];
				
				int nLoc = loc;
				
				if(loc-1>=0&&!visited[loc-1]) {
					nLoc = loc-1;
					visited[nLoc] = true;
					trace[nLoc] = loc;
					q.add(new int[] {nLoc,n+1});
					if(nLoc==K) {
						answer= n+1;
						break;
					}
				}
				
				if(loc+1<=100000&&!visited[loc+1]) {
					nLoc = loc+1;
					visited[nLoc] = true;
					trace[nLoc] = loc;
					q.add(new int[] {nLoc,n+1});
					if(nLoc==K) {
						answer= n+1;
						break;
					}
				}
				
				if(loc*2<=100000&&!visited[loc*2]) {
					nLoc = loc*2;
					visited[nLoc] = true;
					trace[nLoc] = loc;
					q.add(new int[] {nLoc,n+1});
					if(nLoc==K) {
						answer= n+1;
						break;
					}
				}
			}
			int t = K;
			System.out.println(answer);	
			Stack<Integer> s = new Stack<>();
			s.add(t);
			while(trace[t]!=N) {
				s.add(trace[t]);
				t = trace[t];
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(N).append(" ");
			while(!s.empty()) {
				int n = s.pop();
				sb.append(n).append(" ");
			}
			System.out.println(sb.toString());
		}
	}
}
