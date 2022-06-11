package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//청소년 상어
public class b19236_2try {
	static int dx[] = {0,-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,0,-1,-1,-1,0,1,1,1};
	static int score = 0;
	static int map[][] = new int[5][5];
	static Fish[] fishes = new Fish[17];
	static int[] shark = {1,1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;	
		for(int i=1;i<=4;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=4;j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				fishes[a] = new Fish(a,b,i,j,true);
				map[i][j] = a;
			}
		}
		
		int i = map[1][1];
		shark[2] = fishes[i].dir;
		fishes[i].alive = false;
		map[1][1] = -1;
		dfs(i);
		System.out.println(score);
	}
	static void dfs(int sum) {
		if(sum>score) {
			score = sum;
		}
		//물고기 이동
		for(int i=1;i<=16;i++) {
			if(fishes[i].alive) {
				fishes[i].move();
			}
		}
		//상어가 이동
		int temp[][] = new int[5][5];
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				temp[i][j]=map[i][j];
			}
		}
		Fish[] cfishes = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			cfishes[i] = new Fish(fishes[i].no,fishes[i].dir,fishes[i].x,fishes[i].y,fishes[i].alive);
		}
		int[] cshark = {shark[0],shark[1],shark[2]};
		int x = shark[0];
		int y = shark[1];
		int d = shark[2];
		int r = 1;
		while(true) {
			int nx = x+ (dx[d]*r);
			int ny = y+ (dy[d]*r);
			if(nx>0&&nx<=4&&ny>0&&ny<=4) {
				if(map[nx][ny]!=0) {
					int idx = map[nx][ny];
					map[nx][ny] = -1;
					map[x][y] = 0;
					fishes[idx].alive = false;
					shark[0] = nx;
					shark[1] = ny;
					shark[2] = fishes[idx].dir;
					dfs(sum+idx);
					shark[0] = x;
					shark[1] = y;
					shark[2] = d;
					for(int i=1;i<=4;i++) {
						for(int j=1;j<=4;j++) {
							map[i][j]=temp[i][j];
						}
					}
					for (int i = 1; i <= 16; i++) {
						fishes[i] = new Fish(cfishes[i].no,cfishes[i].dir,cfishes[i].x,cfishes[i].y,cfishes[i].alive);
					}
				}
			}else {
				break;
			}
			r++;
		}
		
	}
	
	static class Fish{
		int no;
		int dir;
		int x;
		int y;
		boolean alive;
		public Fish(int no, int dir,int x,int y,boolean alive) {
			super();
			this.no = no;
			this.dir = dir;
			this.x = x;
			this.y = y;
			this.alive = alive;
		}
		public void move() {
			for(int i=0;i<8;i++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx>0&&nx<=4&&ny>0&&ny<=4&&map[nx][ny]!=-1) {
					if(map[nx][ny]==0) {
						map[nx][ny] = no;
						map[x][y] = 0;
						this.x = nx;
						this.y = ny;
					}else {
						int temp = map[nx][ny];
						map[nx][ny] = no;
						map[x][y] = temp;
						fishes[temp].x = x;
						fishes[temp].y = y;
						this.x = nx;
						this.y = ny;
					}
					return;
				}
				dir++;
				if(dir>8)dir=1;
			}
		}
	}
}
