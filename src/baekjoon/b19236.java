package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//청소년 상어
public class b19236 {

	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] map = new int[4][4];		//{n}
	static Fish[] fishList = new Fish[17];
	static Fish shark;
	static int max=0;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken()); // 물고기 번호
				int d = Integer.parseInt(st.nextToken()); // 방향
				map[i][j]=n;
				fishList[n] = new Fish(n,d,i,j,true);
			}
		}

		int t = map[0][0];
		map[0][0] = 0;
		fishList[t].alive = false;
		shark = new Fish(0,fishList[t].d,0,0,true);
		dfs(t);
		System.out.println(max);
	}
	
	static void dfs(int sum) {
		//max 갱신
		if(max < sum) max = sum;
		
		//물고기 움직이기
		moveFishes();
		
		//copy 생성
		int[][] cmap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cmap[i][j] = map[i][j];
			}
		}
		Fish[] cfishList = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			cfishList[i] = new Fish(fishList[i].n,fishList[i].d,fishList[i].x,fishList[i].y,fishList[i].alive);
		}
		Fish cshark = new Fish(shark.n,shark.d,shark.x,shark.y,true);
		
		//상어 움직이기
		int nx = shark.x + dx[shark.d];
		int ny = shark.y + dy[shark.d];
		while(nx>=0&&nx<4&&ny>=0&&ny<4) {
			if(map[nx][ny]!=0) {
				//칸에 있는 물고기 먹기
				int victim = map[nx][ny];	
				shark.x = nx;
				shark.y = ny;
				shark.d = fishList[victim].d;
				fishList[victim].alive = false;
				map[nx][ny] = 0;
				//다음 사이클로 이동
				dfs(sum+victim);
				//백트레킹
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						map[i][j] = cmap[i][j];
					}
				}
				for (int i = 1; i <= 16; i++) {
					fishList[i] = new Fish(cfishList[i].n,cfishList[i].d,cfishList[i].x,cfishList[i].y,cfishList[i].alive);
				}
				shark = new Fish(cshark.n,cshark.d,cshark.x,cshark.y,true);
			}
			nx += dx[shark.d];
			ny += dy[shark.d];
		}
	}
	
	static void moveFishes() {
		for(int i=1;i<=16;i++) {
			if(fishList[i].alive) {
				fishList[i].moveFish();
			}
		}
	}
	
	static class Fish{
		int n;
		int x;
		int y;
		int d;
		boolean alive;
		public Fish(int n, int d, int x, int y, boolean alive) {
			super();
			this.n = n;
			this.x = x;
			this.y = y;
			this.d = d;
			this.alive = alive;
		}
		
		public void moveFish() {
			for(int i=0;i<8;i++) {
				int nx = this.x+dx[this.d];
				int ny = this.y+dy[this.d];
				if(nx>=0&&nx<4&&ny>=0&&ny<4&&!(shark.x==nx&&shark.y==ny)) {
					if(map[nx][ny]==0) {
						map[x][y] = 0;
						map[nx][ny] = this.n;
						this.x = nx;
						this.y = ny;
					}else {
						int tn = map[nx][ny];
						map[nx][ny] = this.n;
						map[x][y] = tn;
						fishList[tn].x = x;
						fishList[tn].y = y;
						this.x = nx;
						this.y = ny;
					}
					return;
				}
				this.d++;
				if(d==9)d=1;
			}
		}
	}
}
