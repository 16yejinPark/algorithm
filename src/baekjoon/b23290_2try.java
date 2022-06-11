package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//마법사 상어와 복제
public class b23290_2try {
	static int M;
	static int S;
	static int map[][] = new int[5][5];
	static int smell[][] = new int[5][5];
	static int dx[] = {0,0,-1,-1,-1,0,1,1,1};
	static int dy[] = {0,-1,-1,0,1,1,1,0,-1};
	static int dsx[] = {0,-1,0,1,0};
	static int dsy[] = {0,0,-1,0,1};
	static int SX;
	static int SY;
	static int MAX = 0;
	static int W = Integer.MAX_VALUE;
	static ArrayDeque<Fish> fishList = new ArrayDeque<Fish>();
//	static PriorityQueue<int[]> q = new PriorityQueue<int[]>((o1,o2)->{
//		if(o1[0]==o2[0]) return Integer.compare(o1[1], o2[1])*(-1);
//		return Integer.compare(o1[0], o2[0]);
//	}) ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int FX = Integer.parseInt(st.nextToken());
			int FY = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			map[FX][FY]++;
			fishList.addLast(new Fish(FX,FY,D));
		}
		st = new StringTokenizer(br.readLine());
		SX = Integer.parseInt(st.nextToken());
		SY = Integer.parseInt(st.nextToken());
		
		for(int s=0;s<S;s++) {
			//상어가 모든 물고기에게 복제 마법을 시전한다. 복제 마법은 시간이 조금 걸리기 때문에, 아래 5번에서 물고기가 복제되어 칸에 나타난다.
			ArrayDeque<Fish> copyList = new ArrayDeque<Fish>();
			int size = fishList.size();
			for(int i=0;i<size;i++) {
				Fish f = fishList.removeFirst();
				Fish nf = new Fish(f.x,f.y,f.d);
				copyList.addLast(nf);
				fishList.addLast(f);
			}

			
			//모든 물고기가 한 칸 이동한다. 
			size = fishList.size();
			for(int i=0;i<size;i++) {
				Fish f = fishList.removeFirst();
				f.move();
				fishList.addLast(f);
			}

			
			//상어가 연속해서 3칸 이동한다. 
			MAX = 0;
			W = Integer.MAX_VALUE;
			dfs(0,0,0,SX,SY);
			int m1 = W/100;
			sharkMove(m1);
			W %= 100;
			int m2 = W/10;
			sharkMove(m2);
			W %= 10;
			int m3 = W;
			sharkMove(m3);
			
			
			//두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라진다.
			for(int i=1;i<=4;i++) {
				for(int j=1;j<=4;j++) {
					if(smell[i][j]>0)smell[i][j]--;
				}
			}
			
			//1에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.
			size = copyList.size();
			for(int i=0;i<size;i++) {
				Fish f = copyList.removeFirst();
				f.addToMap();
				fishList.addLast(f);
			}
		}
		int total = 0;
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				total += map[i][j];
			}
		}
		System.out.println(total);
	}
	static void print(int[][] map) {
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=4;j++) {
				System.out.printf("%d ",map[i][j]);
			}System.out.println();
		}System.out.println();
	}
	static void dfs(int cnt,int w,int tot,int x,int y) {
		if(cnt==3) {
			if(MAX < tot) {
				W = w;
				MAX = tot;
			}else if(MAX==tot){
				if(W > w) {
					W = w;
				}
			}
			return;
		}
		for(int d=1;d<=4;d++) {
			int nx = x +dsx[d];
			int ny = y +dsy[d];
			if(isRange(nx,ny)) {
				int f = map[nx][ny];
				map[nx][ny] = 0;
				int weight = w+(d*(int)Math.pow(10,2-cnt));
				dfs(cnt+1,weight,tot+f,nx,ny);
				map[nx][ny] = f;
			}
		}
		
	}
	
	static void sharkMove(int d) {
		SX += dsx[d];
		SY += dsy[d];
		map[SX][SY] = 0;
		int size = fishList.size();
		for(int i=0;i<size;i++) {
			Fish f = fishList.removeFirst();
			if(f.x==SX&&f.y==SY) {
				smell[SX][SY] = 3;
			}else {
				fishList.addLast(f);
			}
		}
	}
	
	static boolean isRange(int x,int y) {
		if(x>0&&x<=4&&y>0&&y<=4) return true;
		return false;
	}
	
	
	static class Fish{
		int x;
		int y;
		int d;
		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public void removeFromMap() {
			map[x][y]--;
		}
		public void addToMap() {
			map[x][y]++;
		}
		public void move() {
			for(int i=0;i<8;i++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(isRange(nx,ny)&&smell[nx][ny]==0&&!(SX==nx&&SY==ny)) {
					map[x][y]--;
					x = nx;
					y = ny;
					map[x][y]++;
					return;
				}
				d--;
				if(d==0)d=8;
			}
		}
	}
}
