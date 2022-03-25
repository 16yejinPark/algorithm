package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//새로운 게임2
public class b17837 {

	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	//체스판의 크기
		int K = Integer.parseInt(st.nextToken());	//말의 개수
		
		//0은 흰색, 1은 빨간색, 2는 파란색
		Kan[][] map = new Kan[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = new Kan(i,j);
				map[i][j].color = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] hInfo = new int[K+1][3];
		for(int i=1;i<=K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			hInfo[i][0] = x;
			hInfo[i][1] = y;
			hInfo[i][2] = d;
			map[x][y].list.add(i);
		}
		
		int answer = 1;
		//횟수수정요함
		game: while(answer<=1000) {
			for(int i=1;i<=K;i++) {
				
				int x = hInfo[i][0];
				int y = hInfo[i][1];
				int d = hInfo[i][2];
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx>0&&nx<=N&&ny>0&&ny<=N&&map[nx][ny].color!=2) {
					if(map[nx][ny].color==0) {
						int size = map[x][y].list.size();
						boolean findHorse = false;
						for(int j=0;j<size;j++) {
							int idx = map[x][y].list.removeFirst();
							if(idx==i)findHorse=true;
							if(findHorse) {
								hInfo[idx][0] = nx;
								hInfo[idx][1] = ny;
								map[nx][ny].list.addLast(idx);
							}else {
								map[x][y].list.addLast(idx);
							}
						}
						
					}else if(map[nx][ny].color==1) {
						int size = map[x][y].list.size();
						boolean findHorse = false;
						for(int j=0;j<size;j++) {
							int idx = map[x][y].list.removeLast();
							if(!findHorse) {
								hInfo[idx][0] = nx;
								hInfo[idx][1] = ny;
								map[nx][ny].list.addLast(idx);
								if(idx==i)findHorse=true;
							}else {
								map[x][y].list.addLast(idx);
							}
						}
					}
				}else {
					//change dir
					switch(d) {
					case 1:d=2;break;
					case 2:d=1;break;
					case 3:d=4;break;
					case 4:d=3;break;
					}
					hInfo[i][2] = d;
					nx = x + dx[d];
					ny = y + dy[d];
					if(nx>0&&nx<=N&&ny>0&&ny<=N&&map[nx][ny].color!=2) {
						if(map[nx][ny].color==0) {
							int size = map[x][y].list.size();
							boolean findHorse = false;
							for(int j=0;j<size;j++) {
								int idx = map[x][y].list.removeFirst();
								if(idx==i)findHorse=true;
								if(findHorse) {
									hInfo[idx][0] = nx;
									hInfo[idx][1] = ny;
									map[nx][ny].list.addLast(idx);
								}else {
									map[x][y].list.addLast(idx);
								}
							}
						}else if(map[nx][ny].color==1) {
							int size = map[x][y].list.size();
							boolean findHorse = false;
							for(int j=0;j<size;j++) {
								int idx = map[x][y].list.removeLast();
								if(!findHorse) {
									hInfo[idx][0] = nx;
									hInfo[idx][1] = ny;
									map[nx][ny].list.addLast(idx);
									if(idx==i)findHorse=true;
								}else {
									map[x][y].list.addLast(idx);
								}
							}
						}
					}else {
						nx = x;
						ny = y;
					}
					
				}
				if(map[nx][ny].list.size()>=4) {
					break game;
				}
			}
			answer++;		
		}
		if(answer>1000)answer=-1;
		System.out.println(answer);
	}
	
	static class Kan{
		int x;
		int y;
		int color;
		ArrayDeque<Integer> list = new ArrayDeque<Integer>();
		public Kan(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
