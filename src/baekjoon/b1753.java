package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//최단경로
public class b1753 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] adjList = new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			adjList[i]=new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v,w));
		}
		
		PriorityQueue<Edge> q = new PriorityQueue<>();
		int[] dist = new int[V+1];
		boolean[] visited = new boolean[V+1];
		for(int i=1;i<=V;i++) {
			if(i==K) {
				dist[i]=0;
			}
			else {
				dist[i]=Integer.MAX_VALUE;
			}
		}
		q.add(new Edge(K, 0));
		while(!q.isEmpty()) {
			Edge edge = q.poll();
			if(!visited[edge.n]) {
				visited[edge.n] = true;
				for(Edge next : adjList[edge.n]) {
					if(dist[next.n]>dist[edge.n]+next.dist) {
						dist[next.n]=dist[edge.n]+next.dist;
						q.add(new Edge(next.n,dist[next.n]));
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=V;i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				sb.append("INF\n");
			}else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}

class Edge implements Comparable<Edge>{
	int n;
	int dist;
	public Edge(int n, int dist) {
		super();
		this.n = n;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.dist, o.dist);
	}
}
