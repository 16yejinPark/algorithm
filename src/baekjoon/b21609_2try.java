package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//상어 중학교
public class b21609_2try {
	static int N;
	static int M;
	static int board[][];
	static int dx[] = {0,0,-1,1};
	static int dy[] = {-1,1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//한 변의 크기
		M = Integer.parseInt(st.nextToken());	//색상의 개수
		
		board = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while(true) {
			//크기가 가장 큰 블록 그룹을 찾는다
			int info[] = getBiggestGroup();
			if(info[2]<2) break;
			
			//1에서 찾은 블록 그룹의 모든 블록을 제거한다.
			boolean visited[][] = new boolean[N][N];
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {info[0],info[1]});
			visited[info[0]][info[1]] = true;
			
			int color = board[info[0]][info[1]];
			board[info[0]][info[1]] = 9;
			while(!q.isEmpty()) {
				int[] temp = q.remove();
				int x = temp[0];
				int y = temp[1];
				for(int d=0;d<4;d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(nx>=0&&nx<N&&ny>=0&&ny<N&&!visited[nx][ny]&&(board[nx][ny]==color||board[nx][ny]==0)) {
						visited[nx][ny]=true;
						board[nx][ny] = 9;
						q.add(new int[] {nx,ny});
					}
				}
			}
			score += (info[2]*info[2]);
			//격자에 중력이 작용한다.
			gravity();
			//격자가 90도 반시계 방향으로 회전한다.
			turn();
			//다시 격자에 중력이 작용한다.
			gravity();
		}
		System.out.println(score);
	}
	
	static int[] getBiggestGroup() {
		int visited[][] = new int[N][N];
		//size,rainbow,minRow,minCol
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1,o2)->{
			if(o1[0]==o2[0]) {
				if(o1[1]==o2[1]) {
					if(o1[2]==o2[2]) {
						return Integer.compare(o1[3], o2[3])*(-1);
					}
					return Integer.compare(o1[2], o2[2])*(-1);
				}
				return Integer.compare(o1[1], o2[1])*(-1);
			}
			return Integer.compare(o1[0], o2[0])*(-1);
		});
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int color = board[i][j];
				if(color>=0&&color<=M&&visited[i][j]==0) {
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {i,j});
					visited[i][j] = color;
					
					int size = 1;
					int rainbow = 0;
					int minRow = i;
					int minCol = j;
					while(!q.isEmpty()) {
						int[] temp = q.remove();
						int x = temp[0];
						int y = temp[1];
						for(int d=0;d<4;d++) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if(nx>=0&&nx<N&&ny>=0&&ny<N&&visited[nx][ny]!=color) {
								if(board[nx][ny]==color) {
									visited[nx][ny] = color;
									q.add(new int[] {nx,ny});
									size++;
									if(minRow>nx) {
										minRow = nx;
										minCol = ny;
									}else if(minRow==nx&&minCol>ny) {
										minCol = ny;
									}
								}else if(board[nx][ny]==0) {
									visited[nx][ny] = color;
									q.add(new int[] {nx,ny});
									rainbow++;
									size++;
								}
							}
						}
					}
					pq.add(new int[] {size,rainbow,minRow,minCol});
				}
			}
		}
		
		int group[] = {0,0,0,0};
		if(!pq.isEmpty()) {
			group = pq.remove();
		}
		
		return new int[] {group[2],group[3],group[0]};
	}
	
	static void gravity() {
		for(int i=N-2;i>=0;i--) {
			for(int j=0;j<N;j++) {
				int x = i;
				if(board[i][j]==-1)continue;
				for(int k=i+1;k<N;k++) {
					if(board[k][j]!=9)break;
					board[k][j] = board[x][j];
					board[x][j] = 9;
					x++;
				}
			}
		}
	}
	
	static void turn() {
		int[][] copy = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				copy[N-j-1][i] = board[i][j];
			}
		}
		copy(copy,board);
	}
	
	static void copy(int[][] from, int[][] to) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				to[i][j] = from[i][j];
			}
		}
	}
}
