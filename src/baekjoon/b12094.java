package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2048(easy)
public class b12094 {
	static int max = 0;
	static int N;
	static int[][] board;
	static boolean changed[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				board[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		int now = checkMax();
		dfs(0,now);
		System.out.println(max);
	}

	static void move(int x, int y, int d) {
		
		while (true) {
			// 범위가 아니거나 이미 바뀐 칸이라면
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || changed[nx][ny]) {
				break;
			}
			// 위에가 빈칸이 아니라면
			else if (board[nx][ny] != 0) {
				if (board[x][y] == board[nx][ny] && !changed[nx][ny]) {
					changed[nx][ny] = true;
					board[nx][ny] *= 2;
					board[x][y] = 0;
				}
				break;
			}
			// 위에가 빈칸이라면
			else {
				board[nx][ny] = board[x][y];
				board[x][y] = 0;
				x = nx;
				y = ny;
			}
		}
	}
	
	static void copyBoard(int[][] board, int[][] copyBoard) {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < N; m++) {
				copyBoard[n][m]=board[n][m];
			}
		}
	}
	
	static int checkMax() {
		int r=0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < N; m++) {
				r = Math.max(r, board[n][m]);
			}
		}
		return r;
	}
	
	static void dfs(int cnt,int now) {
		if(now*Math.pow(2, 10-cnt)<max) {
			return;
		}
		if (cnt == 10) {
			max = Math.max(max,checkMax());
			return;
		}
		//copyBoard에 원본을 보관해 놓자
		int copyBoard[][] = new int[N][N];
		copyBoard(board,copyBoard);
		
		// 위로
		changed = new boolean[N][N];
		for (int n = 1; n < N; n++) {
			for (int m = 0; m < N; m++) {
				if (board[n][m] != 0) {
					move(n, m, 0);
				}
			}
		}
		int nn = checkMax();
		dfs(cnt+1,nn);
		copyBoard(copyBoard,board);
		
		// 아래로
		changed = new boolean[N][N];
		for (int n = N - 2; n >= 0; n--) {
			for (int m = 0; m < N; m++) {
				if (board[n][m] != 0) {
					move(n, m, 1);
				}
			}
		}
		nn = checkMax();
		dfs(cnt+1,nn);
		copyBoard(copyBoard,board);
		
		// 왼쪽으로
		changed = new boolean[N][N];
		for (int m = 1; m < N; m++) {
			for (int n = 0; n < N; n++) {
				if (board[n][m] != 0) {
					move(n, m, 2);
				}
			}
		}
		nn = checkMax();
		dfs(cnt+1,nn);
		copyBoard(copyBoard,board);
		
		// 오른쪽으로
		changed = new boolean[N][N];
		for (int m = N - 2; m >= 0; m--) {
			for (int n = 0; n < N; n++) {
				if (board[n][m] != 0) {
					move(n, m, 3);
				}
			}
		}
		nn = checkMax();
		dfs(cnt+1,nn);
		copyBoard(copyBoard,board);
	}
}
