package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b23288 {
	static int d = 0;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int map[][] = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] score = bfs(map,N,M);
		Dice dice  = new Dice();
		int x = 1;
		int y = 1;
		int total = 0;
		
		for(int k=0;k<K;k++) {
			//주사위 굴려  //만약 못굴리면, 방향 변경
			int temp = d;
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(!isRange(nx,ny,N,M)) {
				d = temp;
				d = (d+2)%4;
				nx = x + dx[d];
				ny = y + dy[d];
			}
			x = nx;
			y = ny;
			dice.roll(d);

			//점수를 획득
			total += score[x][y]; 
			
			//이동방향 변경
			if(dice.bottom > map[x][y]) {
				d=(++d)%4;
			}else if(dice.bottom < map[x][y]) {
				d--;
				if(d<0)d=3;
			}
		}

		System.out.println(total);
		
	}

	static int[][] bfs(int[][] map, int N, int M) {
		int[][] score = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (score[i][j]==0) {
					boolean[][] visited = new boolean[N + 1][M + 1];
					Queue<int[]> q = new LinkedList<>();
					ArrayDeque<int[]> ad = new ArrayDeque<>();
					q.add(new int[] {i,j});
					visited[i][j] = true;
					while (!q.isEmpty()) {
						int[] temp = q.remove();
						int x = temp[0];
						int y = temp[1];
						ad.addLast(new int[] {x,y});
						for (int d = 0; d < 4; d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if (isRange(nx,ny,N,M) && !visited[nx][ny] && map[x][y]==map[nx][ny]) {
								visited[nx][ny] = true;
								q.add(new int[] { nx, ny });
							}
						}
					}
					int size = ad.size();
					while (!ad.isEmpty()) {
						int[] ii = ad.removeFirst();
						score[ii[0]][ii[1]] = size * map[ii[0]][ii[1]];
					}
				}
			}
		}

		return score;
	}

	static boolean isRange(int x,int y,int N,int M) {
		if(x > 0 && x <= N && y > 0 && y <= M)
			return true;
		return false;
	}
	static class Dice {
		int top = 1;
		int back = 2;
		int right = 3;
		int left = 4;
		int front = 5;
		int bottom = 6;

		public Dice() {
			super();
		}

		public void roll(int d) {
			int temp = 0;
			switch (d) {
			case 0:
				temp = top;
				top = left;
				left = bottom;
				bottom = right;
				right = temp;
				break;
			case 1:
				temp = back;
				back = bottom;
				bottom = front;
				front = top;
				top = temp;
				break;
			case 2:
				temp = top;
				top = right;
				right = bottom;
				bottom = left;
				left = temp;
				break;
			case 3:
				temp = back;
				back = top;
				top = front;
				front = bottom;
				bottom = temp;
				break;
			}
		}
	}

}
