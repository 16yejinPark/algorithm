package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//07:07 ~
//마법사 상어와 파이어스톰
public class b20058_2try {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int n = (int)Math.pow(2, N);
		int map[][] = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int q=0;q<Q;q++) {
			int l = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
			
			//90도 회전
			int temp[][] = new int[n+1][n+1];
			for(int i=1;i<=n;i+=l) {
				for(int j=1;j<=n;j+=l) {
					for(int x=1;x<=l;x++) {
						for(int y=1;y<=l;y++) {
							int nx = y;
							int ny = l-x+1;
							//System.out.printf("%d %d => %d %d\n",i+x-1,j+y-1,nx+i-1,ny+j-1);
							temp[nx+i-1][ny+j-1] = map[i+x-1][j+y-1];
						}
					}
				}
			}
			
			//얼음이 녹는다.
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					int cnt=0;
					for(int d=0;d<4;d++) {
						int ni = i + dx[d];
						int nj = j + dy[d];
						if(ni>0&&ni<=n&&nj>0&&nj<=n&&temp[ni][nj]>0) {
							cnt++;
						}
					}
					if(cnt<3&&temp[i][j]>0) {
						map[i][j] = temp[i][j]-1;
					}else {
						map[i][j] = temp[i][j];
					}
				}
			}
		}
		
		int total = 0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				total+=map[i][j];
			}
		}
		System.out.println(total);
		
		int maxSize = 0;
		boolean visited[][] = new boolean[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(map[i][j]>0&&!visited[i][j]) {
					int size = 0;
					Queue<int[]> q = new LinkedList<>();
					visited[i][j] = true;
					q.add(new int[] {i,j});
					while(!q.isEmpty()) {
						int x = q.peek()[0];
						int y = q.peek()[1];
						q.remove();
						size++;
						for(int d=0;d<4;d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if(nx>0&&nx<=n&&ny>0&&ny<=n&&map[nx][ny]>0&&!visited[nx][ny]) {
								visited[nx][ny] = true;
								q.add(new int[] {nx,ny});
							}
						}
					}
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		System.out.println(maxSize);
	}
}
