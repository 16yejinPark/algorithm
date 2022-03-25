package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//녹색 옷 입은 애가 젤다지?
public class b4485 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = 1;
		while(true) {
			
			int N = Integer.parseInt(br.readLine());
			if(N==0)break;
			
			int[][] cave = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[][] visited = new boolean[N][N];
			int[][] minDist = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					minDist[i][j] = 10000;
				}
			}	
			visited[0][0]=true;
			visited[0][1]=true;
			visited[1][0]=true;
			minDist[0][0]=cave[0][0];
			minDist[1][0]=cave[1][0]+cave[0][0];
			minDist[0][1]=cave[0][1]+cave[0][0];
			PriorityQueue<Area> pq = new PriorityQueue<>();
			pq.add(new Area(0,1,minDist[0][1]));
			pq.add(new Area(1,0,minDist[1][0]));

			while(!pq.isEmpty()) {
				Area a = pq.poll();
				
				if(a.r==N-1&&a.c==N-1) {
					System.out.printf("Problem %d: %d\n",testCase++,minDist[N-1][N-1]);
					break;
				}
				
				for(int d=0;d<4;d++) {
					int nx = a.r+dx[d];
					int ny = a.c+dy[d];
					if(nx>=0&&nx<N&&ny>=0&&ny<N&&!visited[nx][ny]) {
						visited[nx][ny]=true;
						if(minDist[nx][ny]>minDist[a.r][a.c]+cave[nx][ny]) {
							minDist[nx][ny]=minDist[a.r][a.c]+cave[nx][ny];
							pq.add(new Area(nx,ny,minDist[nx][ny]));
						}
					}
				}
			}
		}
	}
	public static class Area implements Comparable<Area>{
		int r;
		int c;
		int rupee;
		public Area(int r, int c, int rupee) {
			super();
			this.r = r;
			this.c = c;
			this.rupee = rupee;
		}
		@Override
		public int compareTo(Area o) {
			return Integer.compare(this.rupee, o.rupee);
		}
	}
}
