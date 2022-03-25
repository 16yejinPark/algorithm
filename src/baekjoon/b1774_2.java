package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


//우주신과의 교감
public class b1774_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] gods = new int[N+1][2];
		
		List<ArrayList<Channel2>> clist = new ArrayList<ArrayList<Channel2>>();//간선 정보
		
		//연결리스트 생성
		ArrayList<Channel2>[] adjList = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			adjList[i]=new ArrayList<Channel2>();
		}
		
		//모든 정점 저장
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			gods[i][0]=Integer.parseInt(st.nextToken());
			gods[i][1]=Integer.parseInt(st.nextToken());
		}
		
		//일단, 모든 정점간의 거리를 넣는다.
		for(int i=1;i<=N;i++) {
			for(int j=i+1;j<=N;j++) {
				adjList[i].add(new Channel2(j,getDist(gods[i],gods[j])));
				adjList[j].add(new Channel2(i,getDist(gods[i],gods[j])));
			}
		}
		//이미 연결되어있는 채널은 거리를 0으로 해서 넣자.(우선순위가 높아지니까)
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			adjList[a].add(new Channel2(b,0));
			adjList[b].add(new Channel2(a,0));
		}
		
		PriorityQueue<Channel2> q = new PriorityQueue<Channel2>();
		q.add(new Channel2(1,0));
		boolean[] visited = new boolean[N+1];
		double[] min_dist = new double[N+1];
		int cnt=0;
		while(!q.isEmpty()) {
			Channel2 c = q.poll();
			if(visited[c.a])continue;
			
			visited[c.a]=true;
			min_dist[c.a] = c.len;
			for(Channel2 next : adjList[c.a]) {
					if(!visited[next.a]) {
						q.add(next);
				}
			}
			if(++cnt==N)break;
		}
		
		double totalDist=0;
		for(double num:min_dist) {
			totalDist+=num;
		}
		System.out.printf("%.2f",totalDist);	
	}
	static double getDist(int[] p1,int[] p2) {
		return Math.sqrt(Math.pow(p1[0]-p2[0], 2) + Math.pow(p1[1]-p2[1], 2));
	}
}


class Channel2 implements Comparable<Channel2>{
	int a;
	double len;
	public Channel2(int a,double len) {
		super();
		this.a = a;
		this.len = len;
	}
	@Override
	public int compareTo(Channel2 o) {
		return Double.compare(this.len, o.len);
	}
}
