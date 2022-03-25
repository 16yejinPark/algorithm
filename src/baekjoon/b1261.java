package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//알고스팟
public class b1261 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		
		int wn = 0;
		for(int n=0;n<N;n++) {
			String str = br.readLine();
			for(int m=0;m<M;m++) {
				map[n][m] = str.charAt(m); 
				if(map[n][m]=='1')
					wn++;
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->Integer.compare(o1[2],o2[2]));
		q.add(new int[] {0,0,0});
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int w = temp[2];

			if(x==N-1&&y==M-1) {
				System.out.println(w);
				break;
			}
			
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>=0&&nx<N&&ny>=0&&ny<M) {
					if(!visited[nx][ny]&&map[nx][ny]=='0') {
						visited[nx][ny]=true;
						q.add(new int[] {nx,ny,w});
					}
					if(!visited[nx][ny]&&map[nx][ny]=='1') {
						visited[nx][ny]=true;
						q.add(new int[] {nx,ny,w+1});
					}
				}
			}
		}
	}
}
