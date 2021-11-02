package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//네트워크 연결
public class b1922 {
	static int[] parent;
	static int find(int x) {
		if(x==parent[x])
			return x;
		else {
			parent[x]=find(parent[x]);
			return parent[x];
		}
	}
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		if(x!=y) {
			parent[y]=x;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());	//노드의 수
		int M = Integer.parseInt(br.readLine());	//간선의 수
		parent = new int[N+1];
		for(int i=1;i<N+1;i++) {
			parent[i] = i;
		}
		
		ArrayList<Edgee> edges = new ArrayList<Edgee>();
		for(int i=1;i<M+1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edgee(a,b,c));
		}
		
		Collections.sort(edges);
		int total = 0;
		while(!edges.isEmpty()) {
			Edgee e = edges.remove(0);
			if(find(e.a)==find(e.b)) continue;
			total += e.weight;
			union(e.a,e.b);
		}
		System.out.println(total);
	}
}

class Edgee implements Comparable<Edgee>{
	int a;
	int b;
	int weight;
	
	public Edgee(int a, int b, int weight) {
		super();
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edgee o) {
		return Integer.compare(this.weight, o.weight);
	}
}
