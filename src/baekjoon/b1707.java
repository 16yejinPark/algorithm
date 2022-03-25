package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//이분그래프
public class b1707 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<=V;i++) {
				adjList.add(new ArrayList<Integer>());
			}
			
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList.get(s).add(e);
				adjList.get(e).add(s);
				
			}
			
			boolean flag = true;
			Queue<Integer> q = new LinkedList<Integer>();
			int[] visited = new int[V+1];
			String result = "YES";
			for(int i=1;i<=V;i++) {
				q.add(i);
				if(visited[i]==0)
					visited[i]=1;
				System.out.printf("%d %d\n",i,visited[i]);
				while(!q.isEmpty()&&flag) {
					int v = q.remove();
					for(int nv : adjList.get(v)) {
						if(visited[nv]==0) {
							visited[nv]=visited[v] * -1;
							q.add(nv);
						}else if(visited[nv]==visited[v]) {
							result = "NO";
							flag = false;
							break;
						}
					}
				}
			}	
			System.out.println(result);
		}
	}
}