package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//마법사 상어와 블리자드
public class b21611_2try {
	static int dx[] = {0,-1,1,0,0};
	static int dy[] = {0,0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int map[][] = new int[N+1][N+1];
		ArrayDeque<Integer> marbles = new ArrayDeque<Integer>();
		marbles.add(9);
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());	
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int idx[][] = new int[N+1][N+1];
		int x = (N+1)/2;
		int y = (N+1)/2;
		int rep = 1;
		int n = 1;
		outer:while(true) {
			for(int i=0;i<rep;i++) {
				y--;
				if(x==1&&y==0) {
					break outer;
				}
				idx[x][y] = n;
				if(map[x][y]!=0)marbles.addLast(map[x][y]);
				n++;
			}
			for(int i=0;i<rep;i++) {
				x++;
				idx[x][y] = n;
				if(map[x][y]!=0)marbles.addLast(map[x][y]);
				n++;
			}
			rep++;
			for(int i=0;i<rep;i++) {
				y++;
				idx[x][y] = n;
				if(map[x][y]!=0)marbles.addLast(map[x][y]);
				n++;
			}
			for(int i=0;i<rep;i++) {
				x--;
				idx[x][y] = n;
				if(map[x][y]!=0)marbles.addLast(map[x][y]);
				n++;
			}
			rep++;
		}
		
		int one = 0;
		int two = 0;
		int three = 0;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			int r = (N+1)/2;
			int c = (N+1)/2;
			ArrayList<Integer> removeList = new ArrayList<>();
			for(int s=1;s<=S;s++) {
				r += dx[D];
				c += dy[D];
				removeList.add(idx[r][c]);
			}
			
			int i=1;
			marbles.addLast(marbles.removeFirst());
			while(marbles.peekFirst()!=9) {
				int marble = marbles.removeFirst();
				if(!removeList.contains(i)) {
					marbles.addLast(marble);
				}
				i++;
			}

			//4개 연속으로 있는 구슬이 폭발한다.
			boolean changed = true;
			while(changed) {
				changed = false;
				int prev = 9;
				int ctn = 1;
				marbles.addLast(marbles.removeFirst());
				while(marbles.peekFirst()!=9) {
					int marble = marbles.removeFirst();
				
					if(marble == prev) {
						ctn++;
					}else {
						if(ctn>=4) {
							switch(prev) {
							case 1:one+=ctn;break;
							case 2:two+=ctn;break;
							case 3:three+=ctn;break;
							}
							for(int ii=0;ii<ctn;ii++) {
								marbles.removeLast();
							}
							changed = true;
						}
						ctn=1;
						prev = marble;
					}
					marbles.addLast(marble);
				}
				if(ctn>=4) {
					switch(prev) {
						case 1:one+=ctn;break;
						case 2:two+=ctn;break;
						case 3:three+=ctn;break;
					}
					for(int ii=0;ii<ctn;ii++) {
						marbles.removeLast();
					}
					changed = true;
				}
			}

			//구슬이 변화한다.
			int prev = 9;
			int ctn = 1;
			marbles.addLast(marbles.removeFirst());
			while(marbles.peekFirst()!=9) {
				int marble = marbles.removeFirst();
				if(marble == prev) {
					ctn++;
				}else {
					if(prev!=9) {
						marbles.addLast(ctn);
						marbles.addLast(prev);
					}
					ctn=1;
					prev = marble;
				}
			}
			marbles.addLast(ctn);
			marbles.addLast(prev);
			
			if(marbles.size()>N*N) {
				int rr = marbles.size()-(N*N);
				for(int ii=0;ii<rr;ii++) {
					marbles.removeLast();
				}
			}

		}
		
		System.out.println((one*1)+(two*2)+(three*3));

	}
	static void print(int N,int[][] arr) {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				System.out.print(arr[i][j]+" ");
			}System.out.println();
		}System.out.println();
	}
	static void printDeque(ArrayDeque<Integer> dq) {
		int size = dq.size();
		for(int i=0;i<size;i++) {
			int n = dq.removeFirst();
			System.out.print(n+" ");
			dq.addLast(n);
		}
		System.out.println();
	}
}
