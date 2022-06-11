package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//주사위 굴리기2
public class b23288_2try {
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int map[][] = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		//---------------get score
		boolean visited[][] = new boolean[N+1][M+1];
		int score[][] = new int[N+1][M+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(!visited[i][j]) {
					Queue<int[]> q = new LinkedList<>();
					visited[i][j] = true;
					q.add(new int[] {i,j});
					int cnt = 0;
					ArrayDeque<int[]> list = new ArrayDeque<int[]>();
					list.add(new int[] {i,j});
					while(!q.isEmpty()) {
						int r = q.peek()[0];
						int c = q.peek()[1];
						q.remove();
						cnt++;
						visited[r][c] = true;
						for(int d=0;d<4;d++) {
							int nr = r + dx[d];
							int nc = c + dy[d];
							if(nr>0&&nr<=N&&nc>0&&nc<=M&&!visited[nr][nc]&&map[nr][nc]==map[r][c]) {
								visited[nr][nc] = true;
								q.add(new int[] {nr,nc});
								list.add(new int[] {nr,nc});
							}
						}
					}
					int size = list.size();
					for(int ii=0;ii<size;ii++) {
						int[] temp = list.removeFirst();
						int r = temp[0];
						int c = temp[1];
						score[r][c] = map[r][c] * cnt;
					}
				}
			}
		}
		//---------------start rolling dice
		int x = 1;
		int y = 1;
		Dice dice = new Dice();
		int total = 0;
		for(int k=0;k<K;k++) {
			int nx = x + dx[dice.dir];
			int ny = y + dy[dice.dir];
			if(nx>0&&nx<=N&&ny>0&&ny<=M) {	
				dice.move();
			}else {
				dice.dir=(dice.dir+2)%4;
				nx = x + dx[dice.dir];
				ny = y + dy[dice.dir];
				
				dice.move();
			}
			total += score[nx][ny];
			if(dice.btm > map[nx][ny] ) {
				dice.dir=(dice.dir+1)%4;
			}else if(dice.btm < map[nx][ny]) {
				dice.dir=dice.dir-1;
				if(dice.dir<0) {
					dice.dir=3;
				}
			}
			x = nx;
			y = ny;
		}
		System.out.println(total);
	}
	
	static class Dice{
		int top = 1;
		int bck = 2;
		int rgt = 3;
		int lft = 4;
		int frt = 5;
		int btm = 6;
		int dir = 0;
		void move() {
			int tmp = 0;
			switch(dir) {
			case 0:
				tmp = rgt;
				rgt = top;
				top = lft;
				lft = btm;
				btm = tmp;
				break;
			case 1:
				tmp = frt;
				frt = top;
				top = bck;
				bck = btm;
				btm = tmp;
				break;
			case 2:
				tmp = rgt;
				rgt = btm;
				btm = lft;
				lft = top;
				top = tmp;
				break;
			case 3:
				tmp = top;
				top = frt;
				frt = btm;
				btm = bck;
				bck = tmp;
				break;
			}
		}
	}

}
