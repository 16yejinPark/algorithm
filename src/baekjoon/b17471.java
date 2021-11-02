package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//게리맨더링
public class b17471 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		int[] ppl = new int[N+1];
		map = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			ppl[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int n =Integer.parseInt(st.nextToken());
			for(int j=1;j<=n;j++) {
				map[n][j] = 1;
				map[j][n] = 1;
			}
		}
		
		visited = new boolean[N];
		
	}
	
	public static void subset(int idx, int cnt, boolean contain) {
		
		
		
		
	}
	
	public static boolean bfs(int n1, int n2, int N) {
		boolean[] visited = new boolean[N+1];
		int cnt = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		visited[n1]=true;
		q.add(n1);
		while(!q.isEmpty()) {
			int x = q.remove();
			cnt++;
			for(int next = 1; next<=N;next++) {
				if(!visited[next]&&map[x][next]==1) {
					visited[next]=true;
					q.add(next);
				}
			}
		}
		return true;
	}
	
	
}
