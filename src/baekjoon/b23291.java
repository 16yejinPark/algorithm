package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//어항정리
public class b23291 {
	static int N, K;
	static int[][] map;
	static int[] dx = { 0, 1};
	static int[] dy = { 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 어항의 수
		K = Integer.parseInt(st.nextToken()); // 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K 이하
		st = new StringTokenizer(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			map[N][i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		while(true) {
			cnt++;
			addFish();
			roll();
			fishControl();
			putOneLine();
			fly();
			fishControl();
			putOneLine();
			int max = 0;
			int min = Integer.MAX_VALUE;
			for(int i=1;i<=N;i++) {
				max = Math.max(max, map[N][i]);
				min = Math.min(min, map[N][i]);
			}
			if(max - min <= K) {
				break;
			}
		}
		System.out.println(cnt);
	}
	public static void roll() {
		int startR = N;
		int startC = 1;
		int W = 1;
		int H = 1;
		int idx = 1;
		int left = N-1;
		while (true) {
			int prevR = startR;
			int prevC = startC;
			
			if(left < H) {
				break;
			}else {
				left -= H;
			}
			
			if (idx % 2 == 1) {
				startR--;
			}
			startC += W;
			
			for (int x = 0; x < H; x++) {
				for (int y = 0; y < W; y++) {
					int nx = y;
					int ny = H - x - 1;
					map[startR + nx][startC + ny] = map[prevR + x][prevC + y];
					map[prevR + x][prevC + y] = 0;
				}
			}
			
			if (idx % 2 == 1) {
				H++;
			} else {
				W++;
			}
			idx++;		
		}
	}
	
	public static void fishControl() {
		int temp[][] = new int[N + 1][N + 1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][j]!=0)
				for(int d=0;d<2;d++) {
					int ni = i + dx[d];
					int nj = j + dy[d];
					if(ni>0&&ni<=N&&nj>0&&nj<=N&&map[ni][nj]!=0) {
						int gap = Math.abs(map[i][j]-map[ni][nj])/5;
						if(map[i][j]>map[ni][nj]) {
							temp[i][j]-=gap;
							temp[ni][nj]+=gap;
						}else if(map[i][j]<map[ni][nj]) {
							temp[i][j]+=gap;
							temp[ni][nj]-=gap;
						}
					}
				}
			}
		}
		sum(temp,map);
	}
	public static void addFish() {
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(min>map[i][j]&&map[i][j]!=0) {
					min=map[i][j];
				}
			}
		}
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(min==map[i][j]) {
					map[i][j]++;
				}
			}
		}
	}
	public static void putOneLine() {
		int temp[][] = new int[N + 1][N + 1];
		int idx = 1;
		for(int j=1;j<=N;j++) {
			for(int i=N;i>=1;i--) {
				if(map[i][j]!=0) {
					temp[N][idx] = map[i][j];
					idx++;
				}
			}
		}
		copy(temp,map);
	}
	public static void fly() {
		int temp[][] = new int[N + 1][N + 1];
		for(int i=1;i<=N;i+=N/4) {
			int no = (i-1)/(N/4)+1;
			if(no%2==1) {
				no = (no+2)%4;
				for(int j=0;j<N/4;j++) {
					temp[N-4+no][N/4-j] = map[N][i+j];
				}
			}else {
				for(int j=0;j<N/4;j++) {
					temp[N-4+no][j+1] = map[N][i+j];
				}
			}
		}
		copy(temp,map);
	}
	public static void sum(int[][] from, int[][] to) {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				to[i][j] += from[i][j];
			}
		}
	}
	public static void copy(int[][] from, int[][] to) {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				to[i][j] = from[i][j];
			}
		}
	}
}
