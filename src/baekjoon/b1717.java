package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//집합의 표현
public class b1717 {
	static int[] parent;
	static int find(int a ) {
		if(a == parent[a])
			return a;
		else {
			parent[a] = find(parent[a]);
			return parent[a];
		}
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			parent[b] = a;
		}
	}
	
	static boolean isSameParent(int a,int b) {
		return find(a)==find(b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(c) {
			case 0: 
				union(a,b);
				break;
			case 1: 
				if(isSameParent(a,b)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
				break;
			}
			
		}
	}

}
