package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//불!
public class b4179 {
	static int R;
	static int C;
	static int min = Integer.MAX_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean fired[][];
	static boolean visited[][];
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		fired = new boolean[R][C];
		visited = new boolean[R][C];
		PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)-> {
			if(o1[2]==o2[2]) {
				return Integer.compare(o1[3], o2[3]);
			}
			return Integer.compare(o1[2], o2[2]);
		});

		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				char c = str.charAt(j);
				map[i][j] = c;
				if(c=='F') {
					q.add(new int[] {i,j,0,1});
					visited[i][j] = true;
				}
				if(c=='J') {
					map[i][j] = '.';
					q.add(new int[] {i,j,0,2});
					visited[i][j] = true;
				}
			}
		}
		
		outer:while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int n = temp[2];
			int type = temp[3];

			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx<0||nx>=R||ny<0||ny>=C) {
					if(type==2) {
						min = n+1;
						break outer;
					}
					continue;
				}else {
					if(!visited[nx][ny]&&map[nx][ny]=='.') {
						visited[nx][ny] = true;
						q.add(new int[] {nx,ny,n+1,type});
					}
				}
			}
		}
		
		System.out.println(min==Integer.MAX_VALUE?"IMPOSSIBLE":min);
		
	}
}
