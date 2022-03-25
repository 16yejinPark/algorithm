package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리의 지름
public class b1167 {
	static ArrayList<ArrayList<int[]>> adjList;
	static boolean[] visited;
	static int farNode=-1;
	static int maxDist=-1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int V = Integer.parseInt(br.readLine());
		adjList = new ArrayList<ArrayList<int[]>>();
		visited = new boolean[V+1];
		for(int i=0;i<=V;i++) {
			adjList.add(new ArrayList<int[]>());
		}
		for(int i=1;i<=V;i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			while(true) {
				int n = Integer.parseInt(st.nextToken());
				if(n==-1) {
					break;
				}else {
					int d = Integer.parseInt(st.nextToken());
					adjList.get(N).add(new int[] {n,d});
				}
			}
		}
	
		visited[1]=true;
		dfs(1,0);
		visited = new boolean[V+1];
		visited[farNode]=true;
		dfs(farNode,0);
		System.out.println(maxDist);
	}
	
	static void dfs(int n, int dist) {
		if(maxDist<dist) {
			maxDist = dist;
			farNode = n;
		}
		for(int[] node:adjList.get(n)) {
			if(!visited[node[0]]) {
				visited[node[0]]=true;
				dfs(node[0],dist + node[1]);
			}
				
		} 
	}
	
}
