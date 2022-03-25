package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//탈주범 검거
public class s1953 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//세로
			int M = Integer.parseInt(st.nextToken());	//가로
			int R = Integer.parseInt(st.nextToken());	//맨홀 세로
			int C = Integer.parseInt(st.nextToken());	//맨홀 가로
			int L = Integer.parseInt(st.nextToken());	//시간
			
			int[][] map = new int[N][M];
			int[][] mindist = new int[N][M];
			HashMap<Integer,int[][]> pipe = new HashMap<>();
			pipe.put(1, new int[][] {{-1,0},{1,0},{0,-1},{0,1}});
			pipe.put(2, new int[][] {{-1,0},{1,0}});
			pipe.put(3, new int[][] {{0,-1},{0,1}});
			pipe.put(4, new int[][] {{-1,0},{0,1}});
			pipe.put(5, new int[][] {{1,0},{0,1}});
			pipe.put(6, new int[][] {{1,0},{0,-1}});
			pipe.put(7, new int[][] {{-1,0},{0,-1}});
			
			for(int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				for(int m=0;m<M;m++) {
					map[n][m]=Integer.parseInt(st.nextToken());
				}
			}
			int cnt=0;
			mindist[R][C]=1;
			Queue<int[]> q =new LinkedList<>();
			q.add(new int[] {R,C});
			while(!q.isEmpty()) {
				int[] temp = q.remove();
				int x = temp[0];
				int y = temp[1];
				int type = map[x][y];
				if(mindist[x][y]>L) {
					break;
				}else if(mindist[x][y]>0&&mindist[x][y]<=L){
					cnt++;
				}
				for(int[] move : pipe.get(type)) {
					int nx = x + move[0];
					int ny = y + move[1];
					if(nx>=0&&nx<N&&ny>=0&&ny<M&&map[nx][ny]!=0&&mindist[nx][ny]==0) {
						for(int[] move2 : pipe.get(map[nx][ny])) {
							int nnx = nx + move2[0];
							int nny = ny + move2[1];
							if(nnx==x&&nny==y) {
								mindist[nx][ny]=mindist[x][y]+1;
								q.add(new int[] {nx,ny});
								break;
							}
						}
					}
				}
			}
//			for(int n=0;n<N;n++) {
//				for(int m=0;m<M;m++) {
//					System.out.print(mindist[n][m]+" ");
//				}System.out.println();
//			}System.out.println();
			
			System.out.printf("#%d %d\n",t,cnt);
		}

	}
}
