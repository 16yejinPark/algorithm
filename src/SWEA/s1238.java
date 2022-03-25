package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Contact
public class s1238 {
	private static boolean[][] graph; 
	private static int[] visited; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int T=1;T<=10;T++) {
			st=new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int startIdx = Integer.parseInt(st.nextToken());
			
			graph = new boolean[101][101];
			visited = new int[101];
			st=new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph[s][d]=true;
			}
			
			int depth=0;
			Queue<Integer[]> q = new LinkedList<>();
			q.add(new Integer[] {startIdx,0});
			while(!q.isEmpty()) {
				Integer[] info = q.remove();
				int idx = info[0];
				int cnt = info[1];
				for(int i=1;i<=100;i++) {
					if(visited[i]==0&&graph[idx][i]) {
						visited[i]=cnt+1;
						q.add(new Integer[] {i,visited[i]});
						if(depth<visited[i])
							depth=visited[i];
					}
				}
			}
			
			int maxIdx=0;
			for(int x=1;x<=100;x++) {
				if(depth==visited[x]&&maxIdx<x)
						maxIdx=x;
			}

			System.out.printf("#%d %d\n",T,maxIdx);
		}

	}

}
