package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

//피리 부는 사나이
public class b16724 {
	static int N;
	static int M;
	static Integer[] parent;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int find(int x) {
		if(x == parent[x]) {
			return x;
		}else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}
	
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		if(x!=y) {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new Integer[N*M+1];
		for(int i=1;i<=N*M;i++) {
			parent[i] = i;
		}
		
		Map<Character,Integer> dir = new HashMap<Character,Integer>();
		dir.put('U', 0);
		dir.put('D', 1);
		dir.put('L', 2);
		dir.put('R', 3);
		
		int[][] map = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			String temp = br.readLine();
			for(int j=1;j<=M;j++) {
				map[i][j] = dir.get(temp.charAt(j-1));
			}
		}
		
		
		int cnt=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				int idx = M*(i-1) + j;
				int nidx = M*(i+dx[map[i][j]]-1)+(j+dy[map[i][j]]);
				//System.out.printf("%d %d %d\n",idx,nidx,map[i][j]);
				union(idx,nidx);	
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				int idx = M*(i-1) + j;
				set.add(find(idx));
			}
		}
		System.out.println(set.size());
	}
}
