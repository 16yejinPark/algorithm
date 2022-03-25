package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//최소 스피닝 트리
public class b1197 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		ArrayList<Edge>[] adjList = new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		for(int i=0;i<E;i++) {
			st =new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[s].add(new Edge(e,w));
			adjList[e].add(new Edge(s,w));
		}
		
		int[] min_edge = new int[V+1];
		Arrays.fill(min_edge, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(1,0));
		
		int cnt=0;
		while(!q.isEmpty()) {
			Edge edge = q.poll();
			if(visited[edge.n])continue;
			
			visited[edge.n]=true;
			min_edge[edge.n] = edge.dist;
			for(Edge next : adjList[edge.n]) {
					if(!visited[next.n]) {
						q.add(next);
				}
			}
			if(++cnt==V)break;
		}
		int sum=0;
		for(int i=1;i<=V;i++) {
			sum+=min_edge[i];
		}
		System.out.println(sum);
	}
}


//class Edge implements Comparable<Edge>{
//	int n;
//	int dist;
//	public Edge(int n, int dist) {
//		super();
//		this.n = n;
//		this.dist = dist;
//	}
//
//	@Override
//	public int compareTo(Edge o) {
//		return Integer.compare(this.dist, o.dist);
//	}
//}
