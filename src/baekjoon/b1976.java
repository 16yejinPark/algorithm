package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//여행 가자
public class b1976 {
	static int[] parent;
	static int find(int x) {
		if(x==parent[x]) {
			return x;
		}else{
			parent[x] = find(parent[x]);
			return parent[x];
		}
	}
	
	static void union(int x, int y) {
		x=find(x);
		y=find(y);
		if(x!=y) {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());	//도시의 수
		int M = Integer.parseInt(br.readLine());	//여행에 속한 도시의 수
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp==1) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int city = Integer.parseInt(st.nextToken());
		boolean is_connected = false;
		for(int i=1;i<M;i++) {
			int nextCity = Integer.parseInt(st.nextToken());
			if(find(city)!=find(nextCity)) {
				break;
			}
			if(i==M-1) {
				is_connected=true;
			}
		}
		System.out.println((is_connected?"YES":"NO"));
	}
}
