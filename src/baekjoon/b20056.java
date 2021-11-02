package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 파이어볼
public class b20056 {
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//map 크기
		int M = Integer.parseInt(st.nextToken());	//파이어볼 개수
		int K = Integer.parseInt(st.nextToken());	//명령의 수
		
		ArrayList<FireBall> fbs = new ArrayList<>();
		for(int i=0;i<M;i++) {	//파이어볼의 정보
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());	//질량
			int s = Integer.parseInt(st.nextToken());	//속력
			int d = Integer.parseInt(st.nextToken());	//방향
			fbs.add(new FireBall(r,c,m,s,d));
		}
		for(int k=0;k<K;k++) {
			int visited[][] = new int[N+1][N+1];
			ArrayList<int[]> check = new ArrayList<int[]>();
			for(int i=0;i<fbs.size();i++) {
				FireBall fb = fbs.get(i);
				fb.r = fb.r + (dx[fb.d]*fb.s);
				fb.r = fb.r>N? fb.r%N : fb.r;
				while(fb.r<1) {
					fb.r+=N;
				}
				fb.c = fb.c + (dy[fb.d]*fb.s);
				fb.c = fb.c>N? fb.c%N : fb.c;
				while(fb.c<1) {
					fb.c+=N;
				}
				visited[fb.r][fb.c]++;
			}
			
			//2개 이상의 파이어볼이 있는 곳 처리
			for(int x=1;x<=N;x++) {
				for(int y=1;y<=N;y++) {
					if(visited[x][y]>1) {
						ArrayList<FireBall> removeList = new ArrayList<FireBall>();
						int totalM = 0;
						int totalS = 0;
						int evenD = 0;
						int oddD = 0;
						int cnt=0;
						for(int j=0;j<fbs.size();j++) {
							FireBall fb = fbs.get(j);
							if(x==fb.r&&y==fb.c) {
								removeList.add(fb);
								cnt++;
								totalM+=fb.m;
								totalS+=fb.s;
								if(fb.d%2==1)
									oddD++;
								else evenD++;
							}
						}
						if(totalM/5!=0) {
							if(evenD==0||oddD==0) {
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,0));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,2));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,4));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,6));
							}else {
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,1));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,3));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,5));
								fbs.add(new FireBall(x,y,totalM/5,totalS/cnt,7));
							}
						}
						for(FireBall fb : removeList) {
							fbs.remove(fb);
						}
					}
				}
			}
		}
		int totalM=0;
		for(FireBall fb : fbs) {
			totalM+=fb.m;
		}
		System.out.println(totalM);
	}
	static class FireBall{
		int r;
		int c;
		int m;
		int s;
		int d;
		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
