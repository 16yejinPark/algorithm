package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 파이어볼
public class b20056_2try {
	static int dx[] = {-1,-1,0,1,1,1,0,-1};
	static int dy[] = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayDeque<FireBall> fireList = new ArrayDeque<>();
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			fireList.add(new FireBall(r,c,m,d,s%N));
		}
		
		for(int k=0;k<K;k++) {
			int map[][] = new int[N+1][N+1];
			int size = fireList.size();
			
			//모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
			for(int i=0;i<size;i++) {
				FireBall fb = fireList.removeFirst();
				fb.r += (dx[fb.d]*fb.s);
				fb.c += (dx[fb.d]*fb.s);
				if(fb.r<1) {
					fb.r+=N;
				}else if(fb.r>N) {
					fb.r-=N;
				}
				if(fb.c<1) {
					fb.c+=N;
				}else if(fb.c>N) {
					fb.c-=N;
				}
				map[fb.r][fb.c]++;
				fireList.addLast(fb);
			}
			
			//이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
			int sum[][][] = new int[N+1][N+1][3];	//질량, 속력, 방향
			for(int i=0;i<size;i++) {
				FireBall fb = fireList.removeFirst();
				if(map[fb.r][fb.c]<2) {
					fireList.addLast(fb);
				}else {
					//2개 이상의 파이어볼이 있는 칸
					sum[fb.r][fb.c][0] += fb.m;
					sum[fb.r][fb.c][1] += fb.s;
					sum[fb.r][fb.c][0] += fb.m;
				}
			}
			
		}
		
		
		
		
		
	}

	static class FireBall{
		int r; 
		int c; 
		int m; //질량
		int d; //방향
		int s; //속력
		public FireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
}
