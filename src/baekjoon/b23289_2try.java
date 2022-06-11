package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b23289_2try {
	static int tx[] = {0,0,0,-1,1};
	static int ty[] = {0,1,-1,0,0};
	static int dx[][] = {{0,0,0},{-1,0,1},{-1,0,1},{-1,-1,-1},{1,1,1}};
	static int dy[][] = {{0,0,0},{1,1,1},{-1,-1,-1},{-1,0,1},{-1,0,1}};
	static int dw[][][] = {{{}},
			{{-1,0,1},{0,0,0},{0,0,1},{1,0,0},{1,0,1}},
			{{-1,-1,1},{0,0,0},{0,-1,1},{1,0,0},{1,-1,1}},
			{{0,-1,0},{0,-1,1},{0,0,0},{0,0,1},{0,1,0}},
			{{1,-1,0},{0,-1,1},{1,0,0},{0,0,1},{1,1,0}}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> aircons = new ArrayList<int[]>();
		ArrayList<int[]> points  = new ArrayList<int[]>();
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				int n = Integer.parseInt(st.nextToken()); 
				if(n==5) {
					points.add(new int[] {i,j});
				}else if(n>0) {
					aircons.add(new int[] {i,j,n});
				}
			}
		}
		
		boolean walls[][][] = new boolean[N+1][M+1][2];
		int W = Integer.parseInt(br.readLine());
		for(int i=0;i<W;i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			walls[X][Y][T] = true;
		}
		
		int map[][] = new int[N+1][M+1];
		int choco = 0;
		boolean check = false;
		while(!check&&choco<=100) {
			check = true;
			//1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
			for(int s=0;s<aircons.size();s++) {
				int i = aircons.get(s)[0];
				int j = aircons.get(s)[1];
				int dir = aircons.get(s)[2];
				int tt = 4;
				map[i+tx[dir]][j+ty[dir]]+=5;
				boolean visited[][] = new boolean[N+1][M+1];
				Queue<int[]> q = new LinkedList<int[]>();
				q.add(new int[] {i+tx[dir],j+ty[dir],tt});
				while(!q.isEmpty()) {
					int x = q.peek()[0];
					int y = q.peek()[1];
					int t = q.peek()[2];
					q.remove();
					for(int d=0;d<3;d++) {
						int nx = x + dx[dir][d];
						int ny = y + dy[dir][d];
						if(nx>0&&nx<=N&&ny>0&&ny<=M&&!visited[nx][ny]) {
							boolean wall = false;
							//벽이 있는지 확인
							int wx,wy,wn;
							if(d==0) {
								wx = x + dw[dir][0][0];
								wy = y + dw[dir][0][1];
								wn =  dw[dir][0][2];
								if(walls[wx][wy][wn])wall = true;
								wx = x + dw[dir][1][0];
								wy = y + dw[dir][1][1];
								wn =  dw[dir][1][2];
								if(walls[wx][wy][wn])wall = true;
							}else if(d==1) {
								wx = x + dw[dir][2][0];
								wy = y + dw[dir][2][1];
								wn = dw[dir][2][2];
								if(walls[wx][wy][wn])wall = true;
							}else {
								wx = x + dw[dir][3][0];
								wy = y + dw[dir][3][1];
								wn = dw[dir][3][2];
								if(walls[wx][wy][wn])wall = true;
								wx = x + dw[dir][4][0];
								wy = y + dw[dir][4][1];
								wn = dw[dir][4][2];
								if(walls[wx][wy][wn])wall = true;
							}
							//벽이 없다면,
							if(!wall) {
								visited[nx][ny] = true;
								map[nx][ny]+=t;
								if(t>1) {
									q.add(new int[] {nx,ny,t-1});
								}
							}
						}
					}
				}
			}			
			//2. 온도가 조절됨
			int temp[][] = new int[N+1][M+1];
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					for(int d=1;d<=4;d+=2) {
						int nx = i + tx[d];
						int ny = j + ty[d];
						if(nx>0&&nx<=N&&ny>0&&ny<=M) {
							int gap = Math.abs(map[i][j]-map[nx][ny])/4;
							if(d==1&&!walls[i][j][1]) {
								if(map[i][j]>map[nx][ny]) {
									temp[i][j]-=gap;
									temp[nx][ny]+=gap;
								}else {
									temp[i][j]+=gap;
									temp[nx][ny]-=gap;
								}
							}else if(d==3&&!walls[i][j][0]) {
								if(map[i][j]>map[nx][ny]) {
									temp[i][j]-=gap;
									temp[nx][ny]+=gap;
								}else {
									temp[i][j]+=gap;
									temp[nx][ny]-=gap;
								}
							}
						}
					}
					
				}
			}
			
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					map[i][j] = temp[i][j];
				}
			}
			//3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					if(i==1||j==1||i==N||j==M) {
						if(map[i][j]>0) {
							map[i][j]--;
						}
					}
				}
			}
			//4. 초콜릿을 하나 먹는다.
			choco++;
			
			//5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
			for(int s=0;s<points.size();s++) {
				int i = points.get(s)[0];
				int j = points.get(s)[1];
				if(map[i][j]<K) {
					check = false;
					break;
				}
			}
		}
		System.out.println(choco>100?101:choco);
	}
}
