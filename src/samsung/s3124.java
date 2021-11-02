//package samsung;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.StringTokenizer;
//
//public class s3124 {
//	static int[] parent;
//	
//	static int find(int x) {
//		if(x==parent[x])
//			return x;
//		else {
//			parent[x]=find(parent[x]);
//			return parent[x];
//		}
//	}
//	
//	static void union(int x,int y) {
//		x= find(x);
//		y= find(y);
//		if(x!=y) {
//			parent[y]=x;
//		}
//	}
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;
//		int T = Integer.parseInt(br.readLine());
//		for(int t=1;t<=T;t++) {
//			st = new StringTokenizer(br.readLine());
//			int V = Integer.parseInt(st.nextToken());
//			int E = Integer.parseInt(st.nextToken());
//			
//			parent = new  int[V+1];
//			for(int i=1;i<=V;i++) {
//				parent[i]=i;
//			}
//			
//			ArrayList<Edge> edges = new ArrayList<Edge>();
//			for(int i=1;i<=E;i++) {
//				st = new StringTokenizer(br.readLine());
//				int a = Integer.parseInt(st.nextToken());
//				int b = Integer.parseInt(st.nextToken());
//				int w = Integer.parseInt(st.nextToken());
//				edges.add(new Edge(a,b,w));
//			}
//			Collections.sort(edges);
//			
//			long total=0;
//			for(int i=0;i<E;i++) {
//				Edge e = edges.get(i);
//				if(find(e.a)==find(e.b)) {
//					continue;
//				}
//				total+=e.weight;
//				union(e.a,e.b);
//			}
//			
//			System.out.printf("#%d %d\n",t,total);
//		}
//		
//	}
//
//}
//
////class Edge implements Comparable<Edge>{
////	int a;
////	int b;
////	long weight;
////	public Edge(int a,int b, long weight) {
////		this.a= a;
////		this.b= b;
////		this.weight = weight;
////	}
////	@Override
////	public int compareTo(Edge o) {
////		return Long.compare(this.weight, o.weight);
////	}
////}
