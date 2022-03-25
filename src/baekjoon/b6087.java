package BaekJoon;

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
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static char map[][];
	static int mirror[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
	
		int sx = 0;
		int sy = 0;
		map = new char[H][W];
		mirror = new int[H][W][4];
		for(int i=0;i<H;i++) {
			String line = br.readLine();
			for(int j=0;j<W;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j]=='C') {
					sx = i;
					sy = j;
				}
			}
		}

		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				for(int k=0;k<4;k++) {
					mirror[i][j][k] = 20000;
				}
			}
		}
		
		bfs(sx,sy);
	}
	
	static void bfs(int r, int c) {
		PriorityQueue<int[]> q = new  PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(mirror[o1[0]][o1[1]][o1[2]], mirror[o2[0]][o2[1]][o2[2]]);
			}
		});
		int min = 20000;
		for(int i=0;i<4;i++) {
			mirror[r][c][i] = 0;
			int nx = r + dx[i];
			int ny = c + dy[i];
			if(nx>=0&&nx<H&&ny>=0&&ny<W&&map[nx][ny]=='.') {
				q.add(new int[] {nx,ny,i,0});
				mirror[nx][ny][i] = 0;
			}
		}
		
		while(!q.isEmpty()) {
			
			int temp[] = q.poll();
			int x = temp[0];
			int y = temp[1];
			int d = temp[2];

			//System.out.printf("%d %d %d => %d\n",x,y,d,mirror[x][y][d]);
			for(int i=0;i<4;i++) {
				if(Math.abs(d-i)==2) {
					continue;
				}else {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx>=0&&nx<H&&ny>=0&&ny<W&&map[nx][ny]!='*') {
						int nw = mirror[x][y][d];
						if(d==i) {}
						else {
							nw++;
						}
						
						if(mirror[nx][ny][i]>nw) {
							mirror[nx][ny][i]=nw;
							q.add(new int[] {nx,ny,i});
							if(map[nx][ny]=='C'&&!(r==nx&&c==ny)) {
								min = Math.min(min,nw);
							}
						}
					}
				}
			}	
		}
		System.out.println(min);
		return;
	}
}
