package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1260 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int V=Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> listGraph = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<=N;i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			listGraph.add(temp);
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());;
			int e=Integer.parseInt(st.nextToken());
			listGraph.get(s).add(e);
			listGraph.get(e).add(s);
		}
		
		for(int i=1;i<=N;i++) {
			Collections.sort(listGraph.get(i));
		}
		
		boolean visited[] = new boolean[N+1];
		visited[V]=true;
		DFS(V,listGraph,visited);
		System.out.print("\n");
		BFS(V,N,listGraph);
	}
	
	private static void BFS(int start,int N,ArrayList<ArrayList<Integer>> listGraph) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		boolean visited[] = new boolean[N+1];
		visited[start]=true;
		while(!q.isEmpty()) {
			int idx = q.remove();
			System.out.print(idx+" ");
			
			for(int nextIdx : listGraph.get(idx)) {
				if(!visited[nextIdx]) {
					visited[nextIdx]=true;
					q.add(nextIdx);
				}
			}
		}
	}
	private static void DFS(int idx,ArrayList<ArrayList<Integer>> listGraph,boolean[] visited) {
		System.out.print(idx+" ");
		for(int nextIdx : listGraph.get(idx)) {
			if(!visited[nextIdx]) {
				visited[nextIdx]=true;
				DFS(nextIdx,listGraph,visited);
			}
		}
	}
}
