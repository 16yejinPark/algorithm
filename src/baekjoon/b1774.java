package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//우주신과의 교감
public class b1774 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] gods = new int[N+1][2];
		parent = new int[N+1];
		for(int i=1;i<=N;i++) {
			parent[i]=i;
		}
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			gods[i][0]=Integer.parseInt(st.nextToken());
			gods[i][1]=Integer.parseInt(st.nextToken());
		}
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		ArrayList<Channel> list = new ArrayList<>();
		for(int i=1;i<=N;i++) {
			for(int j=i+1;j<=N;j++) {
				list.add(new Channel(i,j,getDist(gods[i],gods[j])));
			}
		}
		Collections.sort(list);
		
		double totalDist=0;
		boolean allConnected = false;
		while(!allConnected) {
			Channel c = list.remove(0);
			if(find(c.a)!=find(c.b)) {
				union(c.a,c.b);
				totalDist+=c.len;
				//다 연결되었는지 검사
				allConnected=true;
				outer:for(int i=1;i<=N;i++) {
					for(int j=1;j<=N;j++) {
						if(find(i)!=find(j)){
							allConnected=false;
							break outer;
						}
					}
				}
			}
		}
		System.out.printf("%.2f",totalDist);	
	}
	static double getDist(int[] p1,int[] p2) {
		return Math.sqrt(Math.pow(p1[0]-p2[0], 2) + Math.pow(p1[1]-p2[1], 2));
	}
	
	static int find(int x) {
		if(x==parent[x]) {
			return x;
		}else {
			parent[x]=find(parent[x]);
			return parent[x];
		}
	}
	static void union(int x , int y) {
		x=find(x);
		y=find(y);
		if(x!=y) {
			parent[y]=x;
		}
	}
}


class Channel implements Comparable<Channel>{
	int a;
	int b;
	double len;
	public Channel(int a,int b, double len) {
		super();
		this.a = a;
		this.b = b;
		this.len = len;
	}
	@Override
	public int compareTo(Channel o) {
		return Double.compare(this.len, o.len);
	}
}
