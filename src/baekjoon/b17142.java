package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//연구소3
public class b17142 {
	static int[] loc;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static ArrayList<int[]> viruses;
	static int N;
	static int M;
	static int vn;
	static int blank;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//지도크기
		M = Integer.parseInt(st.nextToken());//바이러스 활성화
		map = new int[N][N];
		loc = new int[M];
		viruses = new ArrayList<int[]>();
		
		blank = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					viruses.add(new int[] {i,j});
				}
				if(map[i][j]==0) {
					blank++;
				}
			}
		}
		
		vn = viruses.size();
		if(blank!=0) {
			combi(0,0);
		}else {
			min=0;
		}
		if(min==Integer.MAX_VALUE)min=-1;
		System.out.println(min);
	}
	
	static void combi(int cnt,int idx) {
		if(cnt==M) {
			bfs();
			return;
		}else {
			for(int i=idx;i<vn;i++) {
				loc[cnt] = i;
				combi(cnt+1,i+1);
			}
		}
	}
	static boolean checkAll(boolean[][] visited) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!visited[i][j]&&map[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void bfs() {
		boolean visited[][] = new boolean[N][N];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[2]==o2[2]) {
					return Integer.compare(map[o1[0]][o1[1]], map[o2[0]][o2[1]]);
				}
				return Integer.compare(o1[2],o2[2]);
			}});
		for(int i : loc) {
			int[] point = viruses.get(i);
			int r = point[0];
			int c = point[1];
			q.add(new int[] {r,c,0});
			visited[r][c] = true;
		}
		int cnt = 0;
		int dist = 0;
		while(!q.isEmpty()) {
			int[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];
			dist = temp[2];		
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<N&&map[nx][ny]!=1&&!visited[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny,dist+1});
					if(map[nx][ny]==0) {
						cnt++;
						if(cnt==blank) {
							min = Math.min(min, dist+1);
							return;
						}
					}
					if(dist+1>min) {
						return;
					}
				}
			}
		}
	}
}
