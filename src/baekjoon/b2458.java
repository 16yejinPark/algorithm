package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//키 순서
public class b2458 {
	static ArrayList<ArrayList<Integer>> adjList;
	static ArrayList<ArrayList<Integer>> rAdjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//학생 수
		int M = Integer.parseInt(st.nextToken());	//비교 횟수
		
		adjList = new ArrayList<ArrayList<Integer>>();
		rAdjList = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<=N;i++) {
			adjList.add(new ArrayList<Integer>());
			rAdjList.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList.get(a).add(b);
			rAdjList.get(b).add(a);
		}
		
		int cnt = 0;
		for(int i=1;i<=N;i++) {
			boolean[] visited = new boolean[N+1];
			bfs(i,visited);
			for(int j=1;j<=N;j++) {
				if(!visited[j])break;
				if(j==N)cnt++;
			}
		}
		System.out.println(cnt);
	}
	public static void bfs(int n,boolean[] visited) {
		Queue<Integer> q = new LinkedList<Integer>();
		visited[n]=true;
		q.add(n);
		while(!q.isEmpty()) {
			int x = q.remove();
			for(Integer y : adjList.get(x)) {
				if(!visited[y]) {
					visited[y]=true;
					q.add(y);
				}
			}
		}
		q.add(n);
		while(!q.isEmpty()) {
			int x = q.remove();
			for(Integer y : rAdjList.get(x)) {
				if(!visited[y]) {
					visited[y]=true;
					q.add(y);
				}
			}
		}
	}
}
