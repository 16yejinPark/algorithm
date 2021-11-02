package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//레이저 통신
//https://bingorithm.tistory.com/2 <<테케
public class b6087 {
	static int H;
	static int W;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	static char map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int startX=-1;
		int startY=-1;
		map = new char[H][W];
		for(int h=0;h<H;h++) {
			map[h]=br.readLine().toCharArray();
			if(startX==-1)
				for(int w=0;w<W;w++) {
					if(map[h][w]=='C') {
						startX = h;
						startY = w;
					}
				}
		}
		
		//bfs
		int[][] visited = new int[H][W];
		for(int h=0;h<H;h++) {
			for(int w=0;w<W;w++) {
				visited[h][w]=1000000;
			}
		}
		
		System.out.println(bfs(startX,startY,visited));
	}
	static int bfs(int h, int w,int[][] visited) {
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(visited[o1[0]][o1[1]], visited[o2[0]][o2[1]]);
			}
		});
		q.add(new int[] {h,w,0});
		visited[h][w]=0;
		while(!q.isEmpty()) {

			int[] temp = q.remove();
			int x = temp[0];
			int y = temp[1];
			int d = temp[2];	//가고있는 방향	
			
			System.out.printf("%d %d %d %d\n",x,y,visited[x][y],d);		
			if(!(x==h&&y==w)&&map[x][y]=='C') {
				return visited[x][y];
			}
			
			for(int nd=1;nd<=4;nd++) {
				int nx = x + dx[nd];
				int ny = y + dy[nd];
				if(nx>=0&&nx<H&&ny>=0&&ny<W&&map[nx][ny]!='*') {
					int nn = 0;
					if(nd==d||d==0) {
						nn=visited[x][y];
					}else {
						nn=visited[x][y]+1;
					}
						
					if(visited[nx][ny]>=nn) {
						visited[nx][ny]=nn;
						q.add(new int[] {nx,ny,nd});
					}
					
				}
			}
		}
		return -1;
	}
}
