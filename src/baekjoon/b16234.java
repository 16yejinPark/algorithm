package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//인구 이동
public class b16234 {
	static int map[][];
	static boolean visited[][];
	static int N;
	static int L;
	static int R;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static boolean isChanged = false;
	static boolean[][] changed;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		changed=new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				changed[i][j]=true;
			}
		}
		while(true){
			isChanged = false;
			visited=new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]&&changed[i][j]) {
						changed[i][j] = false;
						visited[i][j] = true;
						bfs(i,j);					
					}
				}
			}
			if(!isChanged) {
				break;
			}
			cnt++;
		}
		
		System.out.println(cnt);

		
	}
	static void bfs(int r,int c) {
		int population = 0;
		int area = 0;
		boolean[][] union = new boolean[N][N];
		ArrayList<int[]> list = new ArrayList<int[]>();
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {r,c});
		union[r][c] = true;
		while(!q.isEmpty()) {
			int[] loc = q.remove();
			int x = loc[0];
			int y = loc[1];
			population += map[x][y];
			area++;
			for(int d=0;d<4;d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(nx>=0&&nx<N&&ny>=0&&ny<N&&!visited[nx][ny]) {
					if(Math.abs(map[x][y]-map[nx][ny])>=L&&Math.abs(map[x][y]-map[nx][ny])<=R) {
						
						visited[nx][ny]=true;
						union[nx][ny]=true;
						q.add(new int[] {nx,ny});
						list.add(new int[] {nx,ny});
					}
				}
			}
		}
		
		// mark union
		if(list.size()>0) {
			list.add(new int[] {r,c});
			isChanged=true;
			for(int[] point : list) {
				map[point[0]][point[1]] = population / area;
				changed[point[0]][point[1]] = true;
				
			}
		}
	}
}
